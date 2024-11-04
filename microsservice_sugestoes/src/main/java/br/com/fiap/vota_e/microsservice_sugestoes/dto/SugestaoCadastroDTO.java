package br.com.fiap.vota_e.microsservice_sugestoes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SugestaoCadastroDTO(
        Long sugestaoId,
        @NotBlank(message = "A descrição é obrigatória!")
        String descricao,
        String observacao,
        String localizacao,
        @NotNull(message = "O usuário que criou a sugestão é obrigatório referenciar!")
        Long usuarioId
) { }
