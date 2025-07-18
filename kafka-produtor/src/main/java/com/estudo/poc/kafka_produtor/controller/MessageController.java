package com.estudo.poc.kafka_produtor.controller;

import com.estudo.poc.kafka_produtor.service.KafkaProducerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class MessageController {
    private final KafkaProducerService kafkaProducerService;

    public MessageController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @GetMapping("/publish") // Mapeia requisições GET para /kafka/publish
    public ResponseEntity<String> publishMessage(@RequestParam("message") String message) {
        // Recebe uma mensagem como parâmetro de consulta e a envia para o Kafka
        // Chama o serviço para enviar a mensagem ao Kafka.
        kafkaProducerService.sendMessage(message);
        return ResponseEntity.ok("Mensagem enviada com sucesso: " + message);
    }
}
