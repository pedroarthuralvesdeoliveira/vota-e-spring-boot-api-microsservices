package br.com.fiap.vota_e.microsservice_sugestoes.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@FeignClient("usuarios-ms")
public interface UsuarioClient {
    @GetMapping("/usuarios/{id}")
    Optional<Long> buscarUsuarioPorId(@PathVariable Long id);
}
