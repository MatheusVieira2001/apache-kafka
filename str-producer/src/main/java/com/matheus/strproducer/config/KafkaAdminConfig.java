package com.matheus.strproducer.config;

import java.util.HashMap;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class KafkaAdminConfig {

	public final KafkaProperties properties = new KafkaProperties();

	//config geral do adm Kafka (Vide o app.yml)
	@Bean
	public KafkaAdmin kafkaadmin() {
		var configs = new HashMap<String, Object>();
		configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServers());

		return new KafkaAdmin(configs);
	}

	//criação de um topico no AdminKafka
	@Bean
	public KafkaAdmin.NewTopics topics() {
		return new KafkaAdmin.NewTopics(TopicBuilder
				.name("str-topic")
				.partitions(2)
				.replicas(1)
				.build());
	}

}
