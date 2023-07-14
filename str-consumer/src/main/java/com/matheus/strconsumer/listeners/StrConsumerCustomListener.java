package com.matheus.strconsumer.listeners;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;
import org.springframework.kafka.annotation.KafkaListener;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@KafkaListener
public @interface StrConsumerCustomListener {

	@AliasFor(annotation = KafkaListener.class, attribute = "topics") 
	String[] topics() default "str-topic";
	
	@AliasFor(annotation = KafkaListener.class, attribute = "containerFactory")
	String containerFactory() default "strContainerFactory";

	@AliasFor(annotation = KafkaListener.class, attribute = "groupId")
	String groupId() default "";

	@AliasFor(annotation = KafkaListener.class, attribute = "errorHandler")
	String errorHandler() default "errorCustomHandler";

	
	/*
	 * GRUPOS DE CONSUMOS DIFERENTES PODEM processar A MENSAGEM IGUALMENTE (DEPENDENTE DAS PARTIÇÕES)!
	 * Neste exemplo temos um produtor com 2 partiçoes e estas irão ser consumidas igualmente por ambos os grupos 
	 * group-1: partitions assigned: [str-topic-0, str-topic-1] visualizará a particao 0 e 1
	 * group-2: partitions assigned: [str-topic-0, str-topic-1]
	 * caso possua 2 metodos para o mesmo grupo as partições serão divididas exemplo
	 * group-1: partitions assigned: [str-topic-1] - metodo 1
	 * group-1: partitions assigned: [str-topic-0] - metodo 2
	 *também é possivel definir qual  a partição deseja que o listener atue
	 *topicPartitions = {@TopicPartition(topic = "str-topic", partitions = {"0"})}, nesse caso verifica a 0 ou o numero desejado de acordo com as partições existentes
	*/
}
