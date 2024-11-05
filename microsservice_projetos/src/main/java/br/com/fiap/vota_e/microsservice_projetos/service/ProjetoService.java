package br.com.fiap.vota_e.microsservice_projetos.service;


import br.com.fiap.vota_e.microsservice_projetos.dto.ProjetoCadastroDTO;
import br.com.fiap.vota_e.microsservice_projetos.dto.ProjetoEdicaoDTO;
import br.com.fiap.vota_e.microsservice_projetos.dto.ProjetoExibicaoDTO;
import br.com.fiap.vota_e.microsservice_projetos.exception.ProjetoNaoEncontradoException;
import br.com.fiap.vota_e.microsservice_projetos.http.SugestaoClient;
import br.com.fiap.vota_e.microsservice_projetos.model.Projeto;
import br.com.fiap.vota_e.microsservice_projetos.model.StatusProjeto;
import br.com.fiap.vota_e.microsservice_projetos.repository.ProjetoRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.time.Instant;
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

    public Optional<Long> buscarSugestaoPorId(@RequestParam @NotNull Long id) {
        return sugestaoClient.buscarSugestaoPorId(id);
    }

    public ProjetoExibicaoDTO salvar(ProjetoCadastroDTO projetoCadastroDTO) {
        Projeto projeto = new Projeto();
        BeanUtils.copyProperties(projetoCadastroDTO, projeto);

        projeto.setDataCriacao(Timestamp.from(Instant.now()));
        projeto.setDataAprovacao(null);
        projeto.setDataCancelamento(null);
        projeto.setDataEnvio(null);
        projeto.setDataRejeicao(null);
        projeto.setStatus(StatusProjeto.EM_ELABORACAO);

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

    public ProjetoExibicaoDTO atualizar(ProjetoEdicaoDTO projetoEdicaoDTO) {
        Optional<Projeto> projetoOptional = projetoRepository.findById(projetoEdicaoDTO.projetoId());

        if (projetoOptional.isPresent()) {
            Projeto projeto = projetoOptional.get();
            BeanUtils.copyProperties(projetoEdicaoDTO, projeto, "dataCriacao");
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
                .findByDataCriacaoBetween(
                        dataInicio, dataFim
                )
                .stream()
                .map(ProjetoExibicaoDTO::new)
                .toList();
    }
}
