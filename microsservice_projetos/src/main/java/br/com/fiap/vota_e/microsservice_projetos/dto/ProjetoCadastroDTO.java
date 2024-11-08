package br.com.fiap.vota_e.microsservice_projetos.dto;

import br.com.fiap.vota_e.microsservice_projetos.model.StatusProjeto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.sql.Timestamp;

public record ProjetoCadastroDTO(
        Long projetoId,
        @NotBlank(message = "A descrição é obrigatória!")
        String descricao,
        @NotBlank(message = "O título é obrigatório!")
        String titulo,
        String status,
        Timestamp dataAprovacao,
        Timestamp dataCancelamento,
        Timestamp dataCriacao,
        Timestamp dataEnvio,
        Timestamp dataRejeicao,
        @NotNull(message = "A sugestão é obrigatória!")
        Long sugestaoId,
        StatusProjeto statusProjeto
) {
}
