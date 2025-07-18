package com.estudo.poc.kafkaprodutor.simples.service;

import org.springframework.beans.factory.annotation.Value; // Para injetar valores de propriedades
import org.springframework.kafka.core.KafkaTemplate; // Classe principal para enviar mensagens Kafka
import org.springframework.stereotype.Service; // Marca a classe como um serviço Spring

@Service
public class KafkaProducerService {

    @Value("${app.kafka.topic.name}") // Injeta o nome do tópico da propriedade.
    // Injeta o valor da propriedade app.kafka.topic.name (definida em application.properties ) na variável topicName
    private String topicName;

    private final KafkaTemplate<String, String> kafkaTemplate; // Template para enviar mensagens
    //É a classe central do Spring Kafka para enviar mensagens.
    //O primeiro tipo genérico ( String ) é para o tipo da chave da mensagem,
    // e o segundo ( String ) é para o tipo do valor da mensagem.
    // O Spring Boot auto-configura um KafkaTemplate para você com base nas propriedades definidas

    // Injeção de dependência do KafkaTemplate via construtor
    //Este método é usado para enviar a mensagem.
    //Ele recebe o nome do tópico e a mensagem (valor) a ser enviada. Você também pode
    //especificar uma chave ( kafkaTemplate.send(topicName, key, message) ), que é usada para
    //garantir que mensagens com a mesma chave vão para a mesma partição, mantendo a ordem
    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {
        // Envia a mensagem para o tópico especificado
        kafkaTemplate.send(topicName, message);
        System.out.println(String.format("Mensagem enviada para o tópico %s: %s", topicName, message));
    }
}
