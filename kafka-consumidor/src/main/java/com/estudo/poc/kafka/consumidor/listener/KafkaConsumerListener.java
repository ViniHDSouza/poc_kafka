package com.estudo.poc.kafka.consumidor.listener;

import org.springframework.kafka.annotation.KafkaListener; // Anotação para marcar métodos como listeners Kafka
import org.springframework.stereotype.Component; // Marca a classe como um componente Spring

@Component
public class KafkaConsumerListener {

    //Esta é a anotação mágica do Spring Kafka. Ela transforma o método listen em um listener de mensagens Kafka
    //topics = "${app.kafka.topic.name}" : Define o tópico do qual este listener irá consumir mensagens. O valor é injetado da propriedade app.kafka.topic.name
    //groupId = "${spring.kafka.consumer.group-id}" : Define o ID do grupo de consumidores. O valor é injetado da propriedade spring.kafka.consumer.group-id
    @KafkaListener(topics = "${app.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(String message) {
        System.out.println(String.format("Mensagem recebida do tópico %s no grupo %s: %s", "${app.kafka.topic.name}", "${spring.kafka.consumer.group-id}", message));
    }
}