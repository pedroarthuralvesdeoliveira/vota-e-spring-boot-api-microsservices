package br.com.fiap.vota_e.microsservice_projetos.controller;

import br.com.fiap.vota_e.microsservice_projetos.dto.ProjetoCadastroDTO;
import br.com.fiap.vota_e.microsservice_projetos.dto.ProjetoEdicaoDTO;
import br.com.fiap.vota_e.microsservice_projetos.dto.ProjetoExibicaoDTO;
import br.com.fiap.vota_e.microsservice_projetos.service.ProjetoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProjetoController {
    private final ProjetoService projetoService;

    public ProjetoController(ProjetoService projetoService) {
        this.projetoService = projetoService;
    }

    @PostMapping("/projetos")
    @ResponseStatus(HttpStatus.CREATED)
    public ProjetoExibicaoDTO salvar(@RequestBody @Valid ProjetoCadastroDTO projetoCadastroDTO) {
        return projetoService.salvar(projetoCadastroDTO);
    }

    @GetMapping("/projetos")
    @ResponseStatus(HttpStatus.OK)
    public Page<ProjetoExibicaoDTO> listar(Pageable pageable) {
        return projetoService.listarTodos(pageable);
    }

    @RequestMapping(value = "/projetos", params = "id")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProjetoExibicaoDTO> buscarPorId(@RequestParam Long id) {
        return ResponseEntity.ok(projetoService.buscarPorId(id));
    }

    @DeleteMapping("/projetos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        projetoService.excluir(id);
    }

    @PutMapping("/projetos")
    public ResponseEntity<ProjetoExibicaoDTO> atualizar(@RequestBody ProjetoEdicaoDTO projetoEdicaoDTO) {
        return ResponseEntity.ok(projetoService.atualizar(projetoEdicaoDTO));
    }

    @RequestMapping(value = "/projetos", params = {"dataInicio", "dataFim"})
    @ResponseStatus(HttpStatus.OK)
    public List<ProjetoExibicaoDTO> listarSugestoesPorPeriodoDeCriacao(
        @RequestParam Date dataInicio,
        @RequestParam Date dataFim
    ) {
        return projetoService.listarSugestoesPorPeriodoDeCriacao(dataInicio, dataFim);
    }
}
