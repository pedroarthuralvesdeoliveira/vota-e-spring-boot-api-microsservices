package br.com.fiap.vota_e.microsservice_usuarios.controller;

import br.com.fiap.vota_e.microsservice_usuarios.config.security.TokenService;
import br.com.fiap.vota_e.microsservice_usuarios.dto.LoginDTO;
import br.com.fiap.vota_e.microsservice_usuarios.dto.TokenDTO;
import br.com.fiap.vota_e.microsservice_usuarios.dto.UsuarioCadastroDTO;
import br.com.fiap.vota_e.microsservice_usuarios.dto.UsuarioExibicaoDTO;
import br.com.fiap.vota_e.microsservice_usuarios.model.Usuario;
import br.com.fiap.vota_e.microsservice_usuarios.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UsuarioService usuarioService;
    private final TokenService tokenService;

    public AuthController(AuthenticationManager authenticationManager, UsuarioService usuarioService, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.usuarioService = usuarioService;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDTO loginDTO) {
        log.info("Tentativa de login para o email: {}", loginDTO.email());
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(
                        loginDTO.email(),
                        loginDTO.senha()
                );

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        String token = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        log.info("Login realizado com sucesso para: {}", loginDTO.email());

        return ResponseEntity.ok(new TokenDTO(token));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioExibicaoDTO registrar(@RequestBody @Valid UsuarioCadastroDTO usuarioCadastroDTO) {
        try {
            log.info("Recebendo requisição de registro para usuario: {}", usuarioCadastroDTO.email());
            return usuarioService.salvarUsuario(usuarioCadastroDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
