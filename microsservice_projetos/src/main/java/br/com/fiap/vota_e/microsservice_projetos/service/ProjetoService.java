package br.com.fiap.vota_e.microsservice_projetos.service;


import br.com.fiap.vota_e.microsservice_projetos.dto.ProjetoCadastroDTO;
import br.com.fiap.vota_e.microsservice_projetos.dto.ProjetoExibicaoDTO;
import br.com.fiap.vota_e.microsservice_projetos.exception.ProjetoNaoEncontradoException;
import br.com.fiap.vota_e.microsservice_projetos.http.SugestaoClient;
import br.com.fiap.vota_e.microsservice_projetos.model.Projeto;
import br.com.fiap.vota_e.microsservice_projetos.repository.ProjetoRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProjetoService {
    private final ProjetoRepository projetoRepository;
    private final SugestaoClient sugestaoClient;

    public ProjetoService(ProjetoRepository projetoRepository, SugestaoClient sugestaoClient) {
        this.projetoRepository = projetoRepository;
        this.sugestaoClient = sugestaoClient;
    }

    @PostMapping(value = "/sugestoes", params = "id")
    public Optional<Long> buscarSugestaoPorId(@RequestParam @NotNull Long id) {
        return sugestaoClient.buscarSugestaoPorId(id);
    }

    public ProjetoExibicaoDTO salvar(br.com.fiap.vota_e.microsservice_projetos.dto.ProjetoCadastroDTO projetoCadastroDTO) {
        Projeto projeto = new Projeto();
        BeanUtils.copyProperties(projetoCadastroDTO, projeto);
        Optional<Long> sugestaoId = buscarSugestaoPorId(projeto.getSugestaoId());
        sugestaoId.ifPresent(projeto::setSugestaoId);

        Projeto projetoSalvo = projetoRepository.save(projeto);

        return new ProjetoExibicaoDTO(projetoSalvo);
    }

    public ProjetoExibicaoDTO buscarPorId(Long id) {
        Optional<Projeto> projeto = projetoRepository.findById(id);

        if (projeto.isPresent()) {
            return new ProjetoExibicaoDTO(projeto.get());
        } else {
            throw new ProjetoNaoEncontradoException("Projeto não encontrado!");
        }
    }

    public Page<ProjetoExibicaoDTO> listarTodos(Pageable pageable) {
        return projetoRepository
                .findAll(pageable)
                .map(ProjetoExibicaoDTO::new)
                ;
    }

    public void excluir(Long id) {
        Optional<Projeto> projeto = projetoRepository.findById(id);
        if (projeto.isPresent()) {
            projetoRepository.delete(projeto.get());
        } else {
            throw new ProjetoNaoEncontradoException("Projeto não encontrado!");
        }
    }

    public ProjetoExibicaoDTO atualizar(ProjetoCadastroDTO projetoCadastroDTO) {
        Optional<Projeto> projetoOptional = projetoRepository.findById(projetoCadastroDTO.projetoId());

        if (projetoOptional.isPresent()) {
            Projeto projeto = new Projeto();
            BeanUtils.copyProperties(projetoCadastroDTO, projeto);
            Optional<Long> sugestaoId = buscarSugestaoPorId(projeto.getSugestaoId());
            sugestaoId.ifPresent(projeto::setSugestaoId);
            return new ProjetoExibicaoDTO(projetoRepository.save(projeto));
        } else {
            throw new ProjetoNaoEncontradoException("Projeto não encontrado!");
        }
    }

    public List<ProjetoExibicaoDTO> listarSugestoesPorPeriodoDeCriacao(
            Date dataInicio,
            Date dataFim
    ) {
        return projetoRepository
                .findByDataCadastroBetween(
                        dataInicio, dataFim
                )
                .stream()
                .map(ProjetoExibicaoDTO::new)
                .toList();
    }
}
