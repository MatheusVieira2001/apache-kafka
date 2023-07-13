package com.matheus.strconsumer.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class StrConsumerListener {

	  private static final Logger log = LogManager.getLogger(StrConsumerListener.class);
	
	@KafkaListener(groupId = "group-1", topics = "str-topic", containerFactory = "strContainerFactory")
	public void listener(String message) {
		log.info("CREATE ::: Mensagem recebida {}",message);
	}
	/*
	 * GRUPOS DE CONSUMOS DIFERENTES PODEM processar A MENSAGEM IGUALMENTE (DEPENDENTE DAS PARTIÇÕES)!
	 * Neste exemplo temos um produtor com 2 partiçoes e estas irão ser consumidas igualmente por ambos os grupos 
	 * group-1: partitions assigned: [str-topic-0, str-topic-1] visualizará a particao 0 e 1
	 * group-2: partitions assigned: [str-topic-0, str-topic-1]
	 * caso possua 2 metodos para o mesmo grupo as partições serão divididas exemplo
	 * group-1: partitions assigned: [str-topic-1] - metodo 1
	 * group-1: partitions assigned: [str-topic-0] - metodo 2
	*/
	@KafkaListener(groupId = "group-2", topics = "str-topic", containerFactory = "strContainerFactory")
	public void create(String message) {
		log.info("CREATE ::: Mensagem recebida {}",message);
	}
	
}
