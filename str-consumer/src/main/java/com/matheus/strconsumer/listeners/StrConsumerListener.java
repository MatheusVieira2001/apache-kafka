package com.matheus.strconsumer.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class StrConsumerListener {

	private static final Logger log = LogManager.getLogger(StrConsumerListener.class);
	
	@StrConsumerCustomListener(groupId = "group-1")
	public void listener(String message) {
		log.info("LISTENER ::: Mensagem recebida {}",message);
		throw new IllegalArgumentException("EXCEPTION...");
	}
	
	@StrConsumerCustomListener(groupId = "group-1")
	public void create(String message) {
		log.info("CREATE ::: Mensagem recebida {}",message);
	}
	
	@KafkaListener(groupId = "group-2", containerFactory = "validMessageContainerFactory", topics = "str-topic")
	public void history(String message) {
		log.info("validMessageContainerFactory (0-1) ::: Mensagem recebida {}",message);
	}
	
}
