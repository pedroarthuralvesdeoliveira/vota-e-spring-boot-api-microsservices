package br.com.fiap.vota_e.microsservice_usuarios.controller;

import br.com.fiap.vota_e.microsservice_usuarios.dto.UsuarioCadastroDTO;
import br.com.fiap.vota_e.microsservice_usuarios.dto.UsuarioExibicaoDTO;
import br.com.fiap.vota_e.microsservice_usuarios.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/usuarios")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioExibicaoDTO salvar(@RequestBody @Valid UsuarioCadastroDTO usuarioDTO) {
        return usuarioService.salvarUsuario(usuarioDTO);
    }

    @GetMapping("/usuarios")
    @ResponseStatus(HttpStatus.OK)
    public Page<UsuarioExibicaoDTO> listar(Pageable pageable) {
        return usuarioService.listarTodos(pageable);
    }

    @DeleteMapping("/usuarios/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long usuarioId) {
        usuarioService.excluir(usuarioId);
    }

    @PutMapping("/usuarios")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UsuarioExibicaoDTO> atualizar(@RequestBody @Valid UsuarioCadastroDTO usuario) {
        return ResponseEntity.ok(usuarioService.atualizarUsuario(usuario));
    }

    @GetMapping(value = "/usuarios", params = "email")
    public ResponseEntity<UsuarioExibicaoDTO> buscarUsuarioPorEmail(@RequestParam String email) {
        return ResponseEntity.ok(usuarioService.buscarPeloEmail(email));
    }

    @RequestMapping(value = "/usuarios", params = "id")
    public ResponseEntity<UsuarioExibicaoDTO> buscarUsuarioPorId(@RequestParam Long id) {
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

    @GetMapping("/usuarios/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Long buscarPorId(@PathVariable Long id) {
        return usuarioService.buscarPorId(id).usuarioId();
    }

    @RequestMapping(value = "/usuarios", params = "telefone")
    public ResponseEntity<UsuarioExibicaoDTO> buscarUsuarioPorTelefone(@RequestParam String telefone) {
        return ResponseEntity.ok(usuarioService.buscarPeloTelefone(telefone));
    }
}
