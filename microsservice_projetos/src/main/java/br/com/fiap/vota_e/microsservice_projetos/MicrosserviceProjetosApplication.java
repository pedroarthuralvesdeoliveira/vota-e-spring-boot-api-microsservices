package br.com.fiap.vota_e.microsservice_projetos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class MicrosserviceProjetosApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicrosserviceProjetosApplication.class, args);
    }

}
