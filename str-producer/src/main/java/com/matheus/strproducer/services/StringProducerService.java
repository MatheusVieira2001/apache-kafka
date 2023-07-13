package com.matheus.strproducer.services;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StringProducerService {

    private static final Logger log = LogManager.getLogger(StringProducerService.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        kafkaTemplate.send("str-topic", message).addCallback(
                success -> {
                	if(success != null) {
                		log.info("Mensagem enviada com sucesso {}", message);
                		log.info("partition {}, offset{}", 
                				success.getRecordMetadata().partition(),
                				success.getRecordMetadata().offset()
                				);
                		}
                },
                error -> log.error("Falha no envio da mensagem")
        );
    }
}
