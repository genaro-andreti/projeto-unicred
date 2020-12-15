package br.com.unicred.api.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SessaoVotacaoConsumer {

	@KafkaListener(topics = "${sessaovotacao.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumer(String sessao) {
        log.info("Sessão : " + sessao);
    }
	
}
