package br.com.fiap.vota_e.microsservice_usuarios.dto;

import br.com.fiap.vota_e.microsservice_usuarios.model.Usuario;
import br.com.fiap.vota_e.microsservice_usuarios.model.UsuarioRole;

public record UsuarioExibicaoDTO(
        Long usuarioId,
        String nome,
        String email,
        String telefone,
        UsuarioRole role
) {
    public UsuarioExibicaoDTO (Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getTelefone(),
                usuario.getRole()
        );
    }
}
