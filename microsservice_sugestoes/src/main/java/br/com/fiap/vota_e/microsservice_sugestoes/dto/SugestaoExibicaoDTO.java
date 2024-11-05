package br.com.fiap.vota_e.microsservice_sugestoes.dto;

import br.com.fiap.vota_e.microsservice_sugestoes.model.Sugestao;

import java.util.Date;

public record SugestaoExibicaoDTO(
        Long sugestaoId,
        String descricao,
        String observacao,
        String localizacao,
        Date dataCriacao,
        Long usuarioId
) {
    public SugestaoExibicaoDTO (Sugestao sugestao) {
        this(
                sugestao.getId(),
                sugestao.getDescricao(),
                sugestao.getObservacao(),
                sugestao.getLocalizacao(),
                sugestao.getDataCriacao(),
                sugestao.getUsuarioId()
        );
    }
}
