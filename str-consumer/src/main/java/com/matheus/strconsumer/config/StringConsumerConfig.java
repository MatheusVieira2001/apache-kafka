package com.matheus.strconsumer.config;

import java.util.HashMap;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.RecordInterceptor;

import com.matheus.strconsumer.listeners.StrConsumerListener;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class StringConsumerConfig {

	private final KafkaProperties properties = new KafkaProperties();
	private static final Logger log = LogManager.getLogger(StrConsumerListener.class);

	@Bean
	public ConsumerFactory<String, String> consumerFactory() {
		var configs = new HashMap<String, Object>();
		configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServers()); // pegar properties do																						// yml
		configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class); // Serializador do Kafka para																					// a chave
		configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class); // definir o serializador																							// do valor
		return new DefaultKafkaConsumerFactory<>(configs);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> strContainerFactory(
			ConsumerFactory<String, String> consumerFactory) {
		var factory = new ConcurrentKafkaListenerContainerFactory<String, String>();
		factory.setConsumerFactory(consumerFactory);
		return factory;
	}
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> validMessageContainerFactory(
			ConsumerFactory<String, String> consumerFactory) {
		var factory = new ConcurrentKafkaListenerContainerFactory<String, String>();
		factory.setConsumerFactory(consumerFactory);
		factory.setRecordInterceptor(validMessage());
		return factory;
	}

	private RecordInterceptor<String, String> validMessage() {
		return record -> {
			if(record.value().contains("teste")) {
				log.info("Possui a palavra teste");
				return record;
			}
			return record;
		};
	}

}
