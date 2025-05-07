# Desafio API

Projeto backend em **Spring Boot 3.4.5** com **Java 21**, utilizando **Spring Data JPA**, **Liquibase**, **MapStruct** e **PostgreSQL**. O banco de dados roda via **Docker Compose**.

---

## 🔧 Pré-requisitos

- Java 21
- Maven 3.8+
- Docker e Docker Compose instalados

---

## 🐘 Subindo o banco de dados

Com o Docker instalado, execute o comando abaixo na pasta `docker` presente na raiz do projeto:

```bash
docker compose up -d
```


## ▶️ Executando o projeto
```bash
./mvnw spring-boot:run
```

## ✅ Testes
```bash
mvn test
```
