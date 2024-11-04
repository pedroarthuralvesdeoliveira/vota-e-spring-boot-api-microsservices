package br.com.fiap.vota_e.microsservice_projetos.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@FeignClient("sugestoes-ms")
public interface SugestaoClient {
    @RequestMapping(method = RequestMethod.GET, value = "/sugestoes", params = "id")
    public Optional<Long> buscarSugestaoPorId(@RequestParam Long id);
}
