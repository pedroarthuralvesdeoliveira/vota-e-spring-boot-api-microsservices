package br.com.fiap.vota_e.microsservice_usuarios.http;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("sugestoes-ms")
public interface SugestaoClient {
}
