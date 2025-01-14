
# 🎟️ Sistemas de Ingressos - Microsserviços 🎉

Este projeto consiste em dois microsserviços principais para gerenciar eventos e ingressos, construído com Java Spring Boot. Ele incorpora boas práticas como mensageria com RabbitMQ, documentação com Swagger, testes unitários e integração com APIs externas.

---

## 🏗️ Estrutura dos Microsserviços

### 1️⃣ **ms_event_manager**
- **Descrição**: Gerencia eventos, permitindo operações de CRUD (Criar, Ler, Atualizar, Deletar).
- **Funcionalidades**:
    - CRUD de eventos.
    - Integração com a API ViaCep para buscar informações de endereço a partir de um CEP.
    - Documentação da API com Swagger.
- **Porta Padrão**: `8080`.

### 2️⃣ **ms_ticket_manager**
- **Descrição**: Gerencia tickets relacionados aos eventos, incluindo mensageria e comunicação com o `ms_event_manager`.
- **Funcionalidades**:
    - CRUD de tickets.
    - Comunicação com o microsserviço de eventos para validação de eventos.
    - Mensageria com RabbitMQ para processamento assíncrono.
    - Documentação da API com Swagger.
- **Porta Padrão**: `8081`.

---

## 🚀 Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot**
    - Spring Web
    - Spring Data JPA
    - Spring Boot Test
- **RabbitMQ** (mensageria)
- **Swagger/OpenAPI** (documentação)
- **Testcontainers** (para testes de integração)
- **Maven** (gerenciamento de dependências)
- **API ViaCep** (para consulta de endereços)

---

## 🛠️ Como Executar o Projeto

### Pré-requisitos
- Java 17+ instalado
- Maven configurado
- RabbitMQ em execução na porta padrão

### Passos
1. Clone o repositório.
2. Navegue até a pasta de cada microsserviço.
3. Execute o comando abaixo em cada microsserviço para inicializá-los:

```bash
./mvnw spring-boot:run
```

4. Os microsserviços estarão disponíveis nas seguintes URLs:
    - **ms_event_manager**: [http://18.117.173.186/:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) 
    - **ms_ticket_manager**: [http://18.117.173.186/:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html)

### Configurações Adicionais
- 🐇 Certifique-se de que o RabbitMQ está configurado corretamente para enviar/receber mensagens entre os microsserviços.
- 🛠️ Atualize os arquivos `application.properties` se necessário, incluindo as credenciais do RabbitMQ.

---

## ✅ Testes

Ambos os microsserviços possuem testes unitários e de integração. Para executar os testes, utilize o comando:

```bash
./mvnw test
```

Os testes incluem:
- Testes unitários para serviços e controladores.
- Testes de integração utilizando Testcontainers.

---

## 📂 Estrutura de Diretórios

- **ms_event_manager**: Contém código relacionado à gestão de eventos.
- **ms_ticket_manager**: Contém código relacionado à gestão de ingressos.

Cada microsserviço possui sua própria configuração e é independente, mas eles se comunicam por meio de chamadas REST e RabbitMQ.

---

