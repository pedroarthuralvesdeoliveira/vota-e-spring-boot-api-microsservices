package br.com.fiap.vota_e.microsservice_projetos.http;

import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@FeignClient(name = "sugestoes-ms")
public interface SugestaoClient {
    @Retry(name = "sugestoes-ms")
    @GetMapping("/api/sugestoes/{id}")
    Optional<Long> buscarSugestaoPorId(@PathVariable Long id);
}
