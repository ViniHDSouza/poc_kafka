# Kafka Consumidor - Spring Boot

## Visão Geral

Este projeto é um exemplo de aplicação Java utilizando Spring Boot para consumir mensagens de um tópico Kafka. Ele demonstra como configurar um consumidor Kafka, realizar a leitura de mensagens e exibir logs no console.

---

## Estrutura do Projeto

```
kafka-consumidor/
│
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── estudo/
│   │   │           └── poc/
│   │   │               └── kafka/
│   │   │                   └── consumidor/
│   │   │                       ├── KafkaConsumidorApplication.java
│   │   │                       └── listener/
│   │   │                           └── KafkaConsumerListener.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── static/
│   │       └── templates/
│   └── test/
│       └── java/
│           └── com/
│               └── estudo/
│                   └── poc/
│                       └── kafka/
│                           └── consumidor/
│                               └── KafkaConsumidorApplicationTests.java
└── ...
```

### Principais Arquivos

- **KafkaConsumidorApplication.java**: Classe principal que inicializa a aplicação Spring Boot.
- **listener/KafkaConsumerListener.java**: Classe responsável por consumir mensagens do Kafka.
- **application.properties**: Arquivo de configuração da aplicação e do consumidor Kafka.

---

## Configuração do Kafka no Projeto

O arquivo `application.properties` contém as configurações necessárias para o consumidor Kafka:

```
spring.application.name=kafka-consumidor
spring.kafka.consumer.bootstrap-servers=localhost:9092
spring.kafka.consumer.group-id=my-group
spring.kafka.consumer.key-deserializer=org.apache.kafka.common.serialization.StringDeserializer
spring.kafka.consumer.value-deserializer=org.apache.kafka.common.serialization.StringDeserializer
spring.kafka.consumer.auto-offset-reset=earliest
app.kafka.topic.name=meu-topico
server.port=8082
```

- **bootstrap-servers**: Endereço do broker Kafka.
- **group-id**: Identificador do grupo de consumidores.
- **key-deserializer/value-deserializer**: Classes para desserializar chave e valor das mensagens.
- **auto-offset-reset**: Indica se o consumidor começa a ler do início (`earliest`) ou apenas novas mensagens (`latest`).
- **app.kafka.topic.name**: Nome do tópico Kafka a ser consumido.
- **server.port**: Porta HTTP da aplicação.

---

## Funcionamento do Consumidor

### Classe Principal

```java
@SpringBootApplication
public class KafkaConsumidorApplication {
    public static void main(String[] args) {
        SpringApplication.run(KafkaConsumidorApplication.class, args);
    }
}
```

### Listener de Mensagens Kafka

```java
@Component
public class KafkaConsumerListener {
    @KafkaListener(topics = "${app.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(String message) {
        System.out.println(String.format("Mensagem recebida do tópico %s no grupo %s: %s", "${app.kafka.topic.name}", "${spring.kafka.consumer.group-id}", message));
    }
}
```

- O método `listen` é chamado automaticamente a cada nova mensagem recebida do tópico configurado.
- As propriedades do tópico e do grupo são lidas do `application.properties`.

---

## Como Executar

1. Certifique-se de que o Apache Kafka está rodando em `localhost:9092`.
2. Ajuste o nome do tópico em `app.kafka.topic.name` se necessário.
3. Execute a aplicação:
   - Pela IDE: Rode a classe `KafkaConsumidorApplication`.
   - Pelo terminal:
     ```bash
     mvn spring-boot:run
     ```
4. As mensagens recebidas do tópico serão exibidas no console.

---

## Observações

- O projeto está configurado apenas para consumo de mensagens do Kafka.
- Não há tratamento avançado de erros, serialização customizada ou integração com outros sistemas.
- Para enviar mensagens ao tópico, utilize um produtor Kafka externo.

---

## Requisitos

- Java 17+
- Apache Kafka rodando localmente
- Maven

---

## Licença

Este projeto é apenas para fins de estudo.
a somente no que tem 