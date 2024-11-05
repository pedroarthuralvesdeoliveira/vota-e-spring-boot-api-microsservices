package br.com.fiap.vota_e.microsservice_projetos.dto;

import br.com.fiap.vota_e.microsservice_projetos.model.Projeto;
import br.com.fiap.vota_e.microsservice_projetos.model.StatusProjeto;

import java.util.Date;

public record ProjetoExibicaoDTO(
        Long projetoId,
        String descricao,
        String titulo,
        StatusProjeto status,
        Date dataAprovacao,
        Date dataCancelamento,
        Date dataCriacao,
        Date dataEnvio,
        Date dataRejeicao,
        Long sugestaoId
) {
    public ProjetoExibicaoDTO (Projeto projeto) {
        this(
                projeto.getId(),
                projeto.getDescricao(),
                projeto.getTitulo(),
                projeto.getStatus(),
                projeto.getDataAprovacao(),
                projeto.getDataCancelamento(),
                projeto.getDataCriacao(),
                projeto.getDataEnvio(),
                projeto.getDataRejeicao(),
                projeto.getSugestaoId()
        );
    }
}
