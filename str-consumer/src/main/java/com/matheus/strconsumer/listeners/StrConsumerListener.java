package com.matheus.strconsumer.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class StrConsumerListener {

	private static final Logger log = LogManager.getLogger(StrConsumerListener.class);
	
	@StrConsumerCustomListener(groupId = "group-1")
	public void listener(String message) {
		log.info("LISTENER (0) ::: Mensagem recebida {}",message);
	}
	
	@StrConsumerCustomListener(groupId = "group-1")
	public void create(String message) {
		log.info("CREATE (1) ::: Mensagem recebida {}",message);
	}
	
	@StrConsumerCustomListener(groupId = "group-2")
	public void history(String message) {
		log.info("HISTORY (0-1) ::: Mensagem recebida {}",message);
	}
	
}
