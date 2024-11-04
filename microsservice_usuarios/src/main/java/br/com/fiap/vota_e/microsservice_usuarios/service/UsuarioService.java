package br.com.fiap.vota_e.microsservice_usuarios.service;

import br.com.fiap.vota_e.microsservice_usuarios.dto.UsuarioCadastroDTO;
import br.com.fiap.vota_e.microsservice_usuarios.dto.UsuarioExibicaoDTO;
import br.com.fiap.vota_e.microsservice_usuarios.exception.UsuarioNaoEncontradoException;
import br.com.fiap.vota_e.microsservice_usuarios.model.Usuario;
import br.com.fiap.vota_e.microsservice_usuarios.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioExibicaoDTO salvarUsuario(UsuarioCadastroDTO usuarioDTO) {
        String senhaCriptografada = new BCryptPasswordEncoder().encode(usuarioDTO.senha());

        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioDTO, usuario);
        usuario.setSenha(senhaCriptografada);
        usuario.setRole(usuarioDTO.role());
        log.info("Salvando usuário: {}", usuario.getEmail());
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        log.info("Usuário salvo com role: {}", usuarioSalvo.getRole());
        return new UsuarioExibicaoDTO(usuarioSalvo);
    }

    public UsuarioExibicaoDTO buscarPorId(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        if (usuario.isPresent()) {
            return new UsuarioExibicaoDTO(usuario.get());
        } else {
            throw new UsuarioNaoEncontradoException("Usuario não encontrado!");
        }
    }

    public Page<UsuarioExibicaoDTO> listarTodos(Pageable paginacao) {
        return usuarioRepository
                .findAll(paginacao)
                .map(UsuarioExibicaoDTO::new)
                ;
    }

    public void excluir(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        if (usuario.isPresent()) {
            usuarioRepository.delete(usuario.get());
        } else {
            throw new UsuarioNaoEncontradoException("Usuario não encontrado!");
        }
    }

    public UsuarioExibicaoDTO atualizarUsuario(UsuarioCadastroDTO usuarioDTO) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(usuarioDTO.usuarioId());

        if (usuarioExistente.isPresent()) {
            Usuario usuario = new Usuario();
            BeanUtils.copyProperties(usuarioDTO, usuario);
            return new UsuarioExibicaoDTO(usuarioRepository.save(usuario));
        } else {
            throw new UsuarioNaoEncontradoException("Usuario não encontrado!");
        }
    }

    public UsuarioExibicaoDTO buscarPeloEmail(String email) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);

        if (usuario.isPresent()) {
            return new UsuarioExibicaoDTO(usuario.get());
        } else {
            throw new UsuarioNaoEncontradoException("Usuário com este e-mail não existe!");
        }
    }

    public UsuarioExibicaoDTO buscarPeloTelefone(String telefone) {
        Optional<Usuario> usuario = usuarioRepository.findByTelefone(telefone);

        if (usuario.isPresent()) {
            return new UsuarioExibicaoDTO(usuario.get());
        } else {
            throw new UsuarioNaoEncontradoException("Usuario com este telefone não encontrado!");
        }
    }
}
