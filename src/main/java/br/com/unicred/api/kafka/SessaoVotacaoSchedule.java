package br.com.unicred.api.kafka;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.unicred.api.model.SessaoVotacao;
import br.com.unicred.api.service.SessaoVotacaoService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SessaoVotacaoSchedule {

	@Autowired
	private SessaoVotacaoService sessaoVotacaoService;
	
	@Autowired
	private SessaoVotacaoProducer sessaoVotacaoProducer;

	@Scheduled(fixedDelay = 60000, initialDelay = 30000)
	public void verificaSessoesEncerradas() {
		log.info("Executando o verificaSessoesEncerradas");

		List<SessaoVotacao> sessoes = sessaoVotacaoService.findAll();

		sessoes.stream().filter(sessao -> sessao.sessaoEncerrada()).forEach((SessaoVotacao s) -> {
			
			log.info("Sessão de Votação " + s.getPauta() + " status" + s.sessaoFechada());
			sessaoVotacaoProducer.send("Sessão de Votação " + s.getPauta() + " encerrada");
		});
		
		log.info("Fim da Execução");
	}

}
