# VOTA-E 🗳️

Sistema de votação e sugestões para melhorias municipais desenvolvido em Spring Boot.

## 📋 Sobre o Projeto

VOTA-E é uma plataforma que permite aos cidadãos participarem ativamente da política municipal através de um aplicativo mobile. Os usuários podem criar, votar e acompanhar sugestões de leis e melhorias para o município, que posteriormente podem ser convertidas em projetos oficiais para a Câmara dos Vereadores.

## 🚀 Funcionalidades Principais

- Cadastro e autenticação de usuários
- Criação e gerenciamento de sugestões
- Sistema de votação em sugestões
- Conversão de sugestões populares em projetos
- Acompanhamento de status das sugestões/projetos
- **Futuro** Integração com a Câmara dos Vereadores

## 🔧 Tecnologias Utilizadas

- **Backend:** Java com Spring Boot
- **Arquitetura:** Microsserviços
- **Banco de Dados:** MySQL e Oracle
- **Autenticação:** JWT

## 📦 Estrutura do Projeto

```
VOTA-E/
├── gateway/                    # API Gateway
├── microservice_sugestoes/     # Serviço de gestão de sugestões
├── microservice_projetos/     # Serviço de gestão de projetos
├── microservice_usuarios/      # Serviço de gestão de usuários
├── service_discovery/          # Serviço de descoberta
└── [outros microsserviços]
```

## 📲 Regras de Negócio

### Usuários
- Cada usuário pode criar múltiplas sugestões
- Perfil com informações básicas e histórico de participação

### Sugestões
- Cada sugestão está vinculada a um único usuário
- Sugestões podem receber votos da comunidade
- Sugestões populares podem ser convertidas em projetos

### Projetos
- Projetos são sugestões aprovadas em processo de análise
- Acompanhamento do status do projeto na Câmara
- Feedback da comunidade

## 🛠️ Configuração do Ambiente de Desenvolvimento

1. Pré-requisitos
   - Java JDK 11 ou superior
   - Maven
   - [Outros pré-requisitos]

2. Clone o repositório
```bash
git clone [URL_DO_REPOSITORIO]
```

3. Configure as variáveis de ambiente
```properties
# Exemplo de configurações necessárias
SPRING_PROFILES_ACTIVE=dev
DATABASE_URL=[URL_DO_BANCO]
```

4. Execute os serviços
```bash
cd VOTA-E
mvn spring-boot:run
```

## 📄 API Documentation

[Incluir documentação da API ou link para Swagger/OpenAPI]

## 🤝 Contribuição

1. Faça um Fork do projeto
2. Crie uma Branch para sua Feature (`git checkout -b feature/AmazingFeature`)
3. Faça o Commit de suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Faça o Push para a Branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📝 Licença

[Incluir informações sobre a licença]

## 👥 Autores

[Incluir informações dos desenvolvedores]

## 📞 Contato

[Incluir informações de contato do projeto]

---
⌨️ com ❤️ por []
