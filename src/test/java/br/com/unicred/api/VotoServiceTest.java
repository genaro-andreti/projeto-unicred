package br.com.unicred.api;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.unicred.api.enums.VotoEnum;
import br.com.unicred.api.model.Associado;
import br.com.unicred.api.model.SessaoVotacao;
import br.com.unicred.api.model.Voto;
import br.com.unicred.api.repository.VotoRepository;
import br.com.unicred.api.service.VotoService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class VotoServiceTest {

	@MockBean
	private VotoRepository votoRepository;
	
	@Autowired
	private VotoService votoService;
	
	@Test
	public void quandoCadastraVoto() {
		Associado associadoMock = new Associado();
		associadoMock.setId("5e4ad17039d0123376bc026b");

		SessaoVotacao sessaoVotacaoMock = new SessaoVotacao();
		sessaoVotacaoMock.setId("5e4ace768b4167031d2b72f5");//5e47101f558efb2b98a876be

		Voto votoMock = new Voto();
		votoMock.setId("5e470f2d558efb2b98a876bc");
		votoMock.setAssociado(associadoMock);
		votoMock.setSessaoVotacao(sessaoVotacaoMock);
		votoMock.setDecisaoVoto(VotoEnum.SIM);
		
		Mockito.when(votoRepository.save(votoMock)).thenReturn(votoMock);

		Voto voto = votoService.cadastrar(votoMock);

		Assertions.assertThat(votoMock.getId().equals(voto.getId()));
		Assertions.assertThat(votoMock.getAssociado().equals(voto.getAssociado()));
		Assertions.assertThat(votoMock.getSessaoVotacao().equals(voto.getSessaoVotacao()));
		Assertions.assertThat(votoMock.getDecisaoVoto().equals(voto.getDecisaoVoto()));
	}

}
