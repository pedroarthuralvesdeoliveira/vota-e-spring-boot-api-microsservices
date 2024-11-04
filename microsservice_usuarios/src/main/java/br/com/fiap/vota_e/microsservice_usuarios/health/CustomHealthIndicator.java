package br.com.fiap.vota_e.microsservice_usuarios.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        try {
            return Health.up()
                    .withDetail("status", "OK")
                    .withDetail("service", "OK")
                    .build();
        } catch (Exception e) {
            return Health.down()
                    .withDetail("message", e.getMessage())
                    .build();
        }
    }
}
