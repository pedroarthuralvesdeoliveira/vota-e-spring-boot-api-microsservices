package br.com.fiap.vota_e.microsservice_projetos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record ProjetoCadastroDTO(
        Long projetoId,
        @NotBlank(message = "A descrição é obrigatória!")
        String descricao,
        @NotBlank(message = "O título é obrigatório!")
        String titulo,
        String status,
        Date dataCadastro,
        Date dataEnvio,
        Date dataAprovacao,
        @NotNull(message = "A sugestão é obrigatória!")
        Long sugestaoId
) {
}
