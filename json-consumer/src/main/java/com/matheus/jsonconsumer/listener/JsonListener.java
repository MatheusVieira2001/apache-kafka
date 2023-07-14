package com.matheus.jsonconsumer.listener;

import com.matheus.jsonconsumer.model.Payment;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static java.lang.Thread.sleep;

@Log4j2
@Component
public class JsonListener {

    @SneakyThrows
    @KafkaListener(topics = "payment-topic", groupId = "create-group", containerFactory = "jsonContainerFactory")
    public void  antiFraude(@Payload Payment payment){
        log.info("Pagamento recebido {}", payment.toString());
        sleep(1000);

        log.info("Validando fraude...");
        sleep(1500);

        log.info("Compra aprovada...");
        sleep(2000);
    }

    @SneakyThrows
    @KafkaListener(topics = "payment-topic", groupId = "pdf-group", containerFactory = "jsonContainerFactory")
    public void  pdfGenerator(@Payload Payment payment){
        sleep(3000);
        log.info("Gerando PDF do produto...", payment.getIdProduct());
        sleep(3000);
    }

    @SneakyThrows
    @KafkaListener(topics = "payment-topic", groupId = "email-group", containerFactory = "jsonContainerFactory")
    public void  emailSender(){
        sleep(3500);
        log.info("Enviando email de confirmação...");
    }

}
