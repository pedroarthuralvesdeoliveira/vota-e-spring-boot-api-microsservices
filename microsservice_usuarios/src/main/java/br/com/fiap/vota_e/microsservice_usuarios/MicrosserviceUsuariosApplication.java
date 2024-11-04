package br.com.fiap.vota_e.microsservice_usuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class MicrosserviceUsuariosApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicrosserviceUsuariosApplication.class, args);
    }

}
