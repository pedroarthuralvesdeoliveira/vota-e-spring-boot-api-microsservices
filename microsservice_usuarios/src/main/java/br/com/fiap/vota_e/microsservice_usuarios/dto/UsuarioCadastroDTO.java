package br.com.fiap.vota_e.microsservice_usuarios.dto;

import br.com.fiap.vota_e.microsservice_usuarios.model.UsuarioRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioCadastroDTO(
        Long usuarioId,
        @NotBlank(message = "O nome é obrigatório!")
        String nome,
        @NotBlank(message = "O e-mail é obrigatório!")
        @Email(message = "O e-mail não é válido!")
        String email,
        @NotBlank(message = "A senha é obrigatória!")
        @Size(min = 6, max = 20, message = "A senha deve conter entre 6 e 20 caracteres!")
        String senha,
        @NotBlank(message = "O telefone é obrigatório!")
        String telefone,
        UsuarioRole role
) { }
