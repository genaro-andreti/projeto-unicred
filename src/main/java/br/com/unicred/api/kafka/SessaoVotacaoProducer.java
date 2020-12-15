package br.com.unicred.api.kafka;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class SessaoVotacaoProducer {

	@Value("${sessaovotacao.topic}")
	private String sessaoVotacaoTopic;

	private final KafkaTemplate<String, String> kafkaTemplate;

	public SessaoVotacaoProducer(final KafkaTemplate<String, String> kafkaTemplate) {
	        this.kafkaTemplate = kafkaTemplate;
	}

	public void send(String sessaoVotacao) {
		final String mensageKey = UUID.randomUUID().toString();
		kafkaTemplate.send(sessaoVotacaoTopic, mensageKey, sessaoVotacao);
	}

}
