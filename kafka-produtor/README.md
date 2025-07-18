# Projeto Kafka Produtor com Spring Boot

## Visão Geral

Este projeto é um exemplo de aplicação Spring Boot que atua como **produtor** de mensagens para o Apache Kafka. Ele demonstra como enviar mensagens para um tópico Kafka utilizando endpoints REST, com uma estrutura de código organizada e de fácil entendimento.

O projeto está estruturado para facilitar a extensão e manutenção, separando responsabilidades em pacotes distintos para controladores e serviços. O foco é a produção de mensagens simples (String) para um tópico Kafka configurável.

---

## Estrutura do Projeto

```
kafka-produtor/
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/estudo/poc/kafka/produtor/
│   │   │       ├── KafkaProdutorApplication.java
│   │   │       └── simples/
│   │   │           ├── controller/
│   │   │           │   └── MessageController.java
│   │   │           └── service/
│   │   │               └── KafkaProducerService.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── static/
│   │       └── templates/
│   └── test/
│       └── java/
│           └── com/estudo/poc/kafka/produtor/
│               └── KafkaProdutorApplicationTests.java
```

### Descrição dos Principais Arquivos e Pastas

- **pom.xml**: Arquivo de configuração do Maven, responsável por gerenciar as dependências do projeto, incluindo o Spring Boot e o Spring for Apache Kafka.

- **KafkaProdutorApplication.java**: Classe principal da aplicação, responsável por inicializar o contexto Spring Boot.

- **simples/controller/MessageController.java**: Controlador REST que expõe endpoints para envio de mensagens ao Kafka. Normalmente, há um endpoint `/kafka/publish` que recebe uma mensagem via parâmetro e aciona o serviço produtor.

- **simples/service/KafkaProducerService.java**: Serviço responsável por encapsular a lógica de envio de mensagens para o Kafka, utilizando o `KafkaTemplate` do Spring.

- **application.properties**: Arquivo de propriedades onde são configurados os parâmetros do Kafka, como endereço do broker, serializadores e nome do tópico.

- **test/**: Contém os testes automatizados da aplicação.

---

## Funcionamento do Produtor Kafka

A aplicação permite o envio de mensagens para um tópico Kafka através de uma chamada HTTP. O fluxo básico é:

1. O usuário faz uma requisição HTTP (GET ou POST) para o endpoint exposto pelo `MessageController`.
2. O controlador recebe a mensagem e delega o envio ao `KafkaProducerService`.
3. O serviço utiliza o `KafkaTemplate` para enviar a mensagem ao tópico Kafka configurado.

---

## Configuração do Kafka

As configurações do Kafka ficam centralizadas no arquivo `application.properties`. Exemplos de propriedades comuns:

```properties
spring.kafka.producer.bootstrap-servers=localhost:9092
spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
spring.kafka.producer.value-serializer=org.apache.kafka.common.serialization.StringSerializer
app.kafka.topic.name=meu-topico
```

- **spring.kafka.producer.bootstrap-servers**: Endereço do broker Kafka.
- **spring.kafka.producer.key-serializer/value-serializer**: Serializadores para chave e valor das mensagens.
- **app.kafka.topic.name**: Nome do tópico Kafka utilizado pelo produtor.

---

## Como Executar o Projeto

1. **Certifique-se de que o Apache Kafka está rodando** (pode ser localmente ou via Docker).
2. Configure o arquivo `application.properties` conforme o endereço do seu broker Kafka.
3. Execute a aplicação via IDE ou terminal:
   ```bash
   ./mvnw spring-boot:run
   ```
4. Envie uma mensagem para o endpoint REST, por exemplo:
   ```bash
   curl "http://localhost:8080/kafka/publish?message=OlaKafka"
   ```

---

## Sobre o Apache Kafka

O **Apache Kafka** é uma plataforma distribuída de streaming de eventos, utilizada para construir pipelines de dados em tempo real e sistemas orientados a eventos. Os principais conceitos são:

- **Produtor**: Envia mensagens para tópicos Kafka.
- **Consumidor**: Lê mensagens dos tópicos.
- **Tópico**: Canal lógico onde as mensagens são publicadas.
- **Broker**: Servidor Kafka que armazena e gerencia os tópicos.

Neste projeto, a aplicação atua como **produtor**, enviando mensagens para um tópico Kafka configurado.

---

## Observações

- O projeto está preparado para ser expandido, podendo receber novos endpoints, lógica de negócio ou integração com consumidores Kafka.
- Para exemplos de tratamento de erros, serialização customizada e outros cenários avançados, consulte a documentação oficial do Spring for Apache Kafka.

---

## Referências

- [Spring for Apache Kafka](https://spring.io/projects/spring-kafka)
- [Documentação do Apache Kafka](https://kafka.apache.org/documentation/)
