package br.com.fiap.vota_e.microsservice_usuarios.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginDTO(
        @NotBlank(message = "O e-mail é obrigatório!")
        @Email(message = "Formato de e-mail incorreto!")
        String email,
        @NotBlank(message = "Informe a senha!")
        @Size(min = 6, max = 20, message = "A senha deve conter entre 6 e 20 caracteres!")
        String senha
) {
}
