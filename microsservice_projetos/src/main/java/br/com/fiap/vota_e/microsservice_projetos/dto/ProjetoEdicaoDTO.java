package br.com.fiap.vota_e.microsservice_projetos.dto;

import br.com.fiap.vota_e.microsservice_projetos.model.StatusProjeto;

import java.sql.Timestamp;

public record ProjetoEdicaoDTO (
        Long projetoId,
        String descricao,
        String titulo,
        String status,
        Timestamp dataAprovacao,
        Timestamp dataCancelamento,
        Timestamp dataEnvio,
        Timestamp dataRejeicao,
        Long sugestaoId,
        StatusProjeto statusProjeto
) {
}
