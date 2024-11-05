package br.com.fiap.vota_e.microsservice_sugestoes.controller;

import br.com.fiap.vota_e.microsservice_sugestoes.dto.SugestaoCadastroDTO;
import br.com.fiap.vota_e.microsservice_sugestoes.dto.SugestaoExibicaoDTO;
import br.com.fiap.vota_e.microsservice_sugestoes.service.SugestaoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class SugestaoController {
    private final SugestaoService sugestaoService;

    public SugestaoController(SugestaoService sugestaoService) {
        this.sugestaoService = sugestaoService;
    }

    @PostMapping("/sugestoes")
    @ResponseStatus(HttpStatus.CREATED)
    public SugestaoExibicaoDTO salvar(@RequestBody @Valid SugestaoCadastroDTO sugestao) {
        return sugestaoService.salvarSugestao(sugestao);
    }

    @GetMapping("/sugestoes")
    @ResponseStatus(HttpStatus.OK)
    public Page<SugestaoExibicaoDTO> listar(Pageable pageable) {
        return sugestaoService.listarSugestoes(pageable);
    }

    @RequestMapping(value = "/sugestoes", params = "id")
    public ResponseEntity<SugestaoExibicaoDTO> buscar(@RequestParam Long id) {
        return ResponseEntity.ok(sugestaoService.buscarPorId(id));
    }

    @GetMapping("/sugestoes/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Long> buscarPorId(@PathVariable Long id) {
        return Optional.of(sugestaoService.buscarPorId(id).sugestaoId());
    }

    @DeleteMapping("/sugestoes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        sugestaoService.excluir(id);
    }

    @PutMapping("/sugestoes")
    public ResponseEntity<SugestaoExibicaoDTO> atualizar(@RequestBody @Valid SugestaoCadastroDTO sugestao) {
        return ResponseEntity.ok(sugestaoService.atualizar(sugestao));
    }

    @RequestMapping(value = "/sugestoes", params = {"dataInicio", "dataFim"})
    @ResponseStatus(HttpStatus.OK)
    public List<SugestaoExibicaoDTO> listarSugestoesPorPeriodoDeCriacao(
            @RequestParam Date dataInicio,
            @RequestParam Date dataFim
    ) {
        return sugestaoService.listarSugestoesPorPeriodoDeCriacao(dataInicio, dataFim);
    }
}
