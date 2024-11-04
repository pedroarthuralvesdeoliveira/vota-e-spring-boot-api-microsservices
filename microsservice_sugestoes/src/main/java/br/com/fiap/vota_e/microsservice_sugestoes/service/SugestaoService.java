package br.com.fiap.vota_e.microsservice_sugestoes.service;

import br.com.fiap.vota_e.microsservice_sugestoes.dto.SugestaoCadastroDTO;
import br.com.fiap.vota_e.microsservice_sugestoes.dto.SugestaoExibicaoDTO;
import br.com.fiap.vota_e.microsservice_sugestoes.exception.SugestaoNaoEncontradaException;
import br.com.fiap.vota_e.microsservice_sugestoes.http.UsuarioClient;
import br.com.fiap.vota_e.microsservice_sugestoes.model.Sugestao;
import br.com.fiap.vota_e.microsservice_sugestoes.repository.SugestaoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SugestaoService {
    private final SugestaoRepository sugestaoRepository;
    private final UsuarioClient usuarioClient;

    public SugestaoService(SugestaoRepository sugestaoRepository, UsuarioClient usuarioClient) {
        this.sugestaoRepository = sugestaoRepository;
        this.usuarioClient = usuarioClient;
    }

    public Optional<Long> buscarUsuarioPorId(Long id) {
        return usuarioClient.buscarUsuarioPorId(id);
    }

    public SugestaoExibicaoDTO salvarSugestao(SugestaoCadastroDTO sugestaoDTO) {
        Sugestao sugestao = new Sugestao();
        BeanUtils.copyProperties(sugestaoDTO, sugestao);

        Optional<Long> usuarioId = buscarUsuarioPorId(sugestao.getUsuarioId());
        usuarioId.ifPresent(sugestao::setUsuarioId);
        sugestao.setDataCriacao(new Date());
        Sugestao sugestaoSalvo = sugestaoRepository.save(sugestao);

        return new SugestaoExibicaoDTO(sugestaoSalvo);
    }

    public SugestaoExibicaoDTO buscarPorId(Long id) {
        Optional<Sugestao> sugestao = sugestaoRepository.findById(id);
        if (sugestao.isPresent()) {
            return new SugestaoExibicaoDTO(sugestao.get());
        } else {
            throw new SugestaoNaoEncontradaException("Sugestão não existe!");
        }
    }

    public Page<SugestaoExibicaoDTO> listarSugestoes(Pageable pageable) {
        return sugestaoRepository
                .findAll(pageable)
                .map(SugestaoExibicaoDTO::new)
                ;
    }

    public void excluir(Long id) {
        Optional<Sugestao> sugestao = sugestaoRepository.findById(id);
        if (sugestao.isPresent()) {
            sugestaoRepository.delete(sugestao.get());
        } else {
            throw new SugestaoNaoEncontradaException("Sugestão não encontrada!");
        }
    }

    public SugestaoExibicaoDTO atualizar(SugestaoCadastroDTO sugestaoDTO) {
        Optional<Sugestao> sugestaoOptional = sugestaoRepository.findById(sugestaoDTO.sugestaoId());

        if (sugestaoOptional.isPresent()) {
            Sugestao sugestao = new Sugestao();
            BeanUtils.copyProperties(sugestaoDTO, sugestao);
            Optional<Long> usuarioId = buscarUsuarioPorId(sugestaoDTO.usuarioId());
            usuarioId.ifPresent(sugestao::setUsuarioId);
            return new SugestaoExibicaoDTO(sugestaoRepository.save(sugestao));
        } else {
            throw new SugestaoNaoEncontradaException("Sugestão não encontrada!");
        }
    }

    public List<SugestaoExibicaoDTO> listarSugestoesPorPeriodoDeCriacao(
            Date dataInicio,
            Date dataFim
    ) {
        return sugestaoRepository
                .findByDataCriacaoBetween(
                        dataInicio, dataFim
                )
                .stream()
                .map(SugestaoExibicaoDTO::new)
                .toList();
    }
}
