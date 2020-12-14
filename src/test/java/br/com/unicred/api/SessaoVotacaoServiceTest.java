package br.com.unicred.api;

import java.time.LocalDateTime;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.unicred.api.model.Pauta;
import br.com.unicred.api.model.SessaoVotacao;
import br.com.unicred.api.repository.SessaoVotacaoRepository;
import br.com.unicred.api.service.SessaoVotacaoService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SessaoVotacaoServiceTest {

	@Autowired
	private SessaoVotacaoService sessaoVotacaoService;

	@MockBean
	private SessaoVotacaoRepository sessaoVotacaoRepository;

	@Test
	public void quandoCadastraSessaoVotacao() {
		SessaoVotacao sessaoVotacaoMock = new SessaoVotacao();
		sessaoVotacaoMock.setId("5e4ace768b4167031d2b72f5");
		sessaoVotacaoMock.setInicioSessaoVotacao(LocalDateTime.now());
		sessaoVotacaoMock.setFimSessaoVotacao(LocalDateTime.now().plusMinutes(2L));

		Pauta pautaMock = new Pauta();
		pautaMock.setId("5e4ace668b4167031d2b72f4");

		sessaoVotacaoMock.setPauta(pautaMock);

		Mockito.when(sessaoVotacaoService.cadastrar(sessaoVotacaoMock)).thenReturn(sessaoVotacaoMock);

		SessaoVotacao sessaoVotacao = sessaoVotacaoService.cadastrar(sessaoVotacaoMock);

		Assertions.assertThat(sessaoVotacaoMock.getId().equals(sessaoVotacao.getId()));
		Assertions.assertThat(sessaoVotacaoMock.getPauta().equals(sessaoVotacao.getPauta()));
		Assertions.assertThat(
				sessaoVotacaoMock.getInicioSessaoVotacao().equals(sessaoVotacao.getInicioSessaoVotacao()));
		Assertions.assertThat(
				sessaoVotacaoMock.getFimSessaoVotacao().equals(sessaoVotacao.getFimSessaoVotacao()));
	}

}
