package br.com.fiap.vota_e.microsservice_sugestoes.http;

import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@FeignClient(name = "usuarios-ms")
public interface UsuarioClient {
    @Retry(name = "usuarios-ms")
    @GetMapping("/api/usuarios/{id}")
    Optional<Long> buscarUsuarioPorId(@PathVariable Long id);
}
