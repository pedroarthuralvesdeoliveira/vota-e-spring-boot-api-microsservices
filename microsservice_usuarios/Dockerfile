FROM eclipse-temurin:21-alpine

RUN apk add --no-cache curl

VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/vota-e-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
LABEL authors="Pedro de Oliveira, Ana, Julia e Mayara"

HEALTHCHECK --interval=30s \
            --timeout=3s \
            --start-period=60s \
            --retries=3 \
            CMD curl -f http://localhost:8080/actuator/health || exit 1

ENTRYPOINT ["java", "-jar", "/app.jar"]