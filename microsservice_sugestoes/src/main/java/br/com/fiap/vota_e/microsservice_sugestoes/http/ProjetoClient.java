package br.com.fiap.vota_e.microsservice_sugestoes.http;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("projetos-ms")
public interface ProjetoClient {

}
