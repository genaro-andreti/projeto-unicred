package br.com.unicred.api;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.unicred.api.model.Pauta;
import br.com.unicred.api.repository.PautaRepository;
import br.com.unicred.api.service.PautaService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PautaServiceTest {

	@Autowired
	private PautaService pautaService;

	@MockBean
	private PautaRepository pautaRepository;
	
	@Test
	public void quandoInserimosUmaPauta() {

		Pauta pautaMock = new Pauta();
		pautaMock.setId("5e4ace668b4167031d2b72f4");
		pautaMock.setDescricao("Pauta 1");
		
		Mockito.when(pautaService.cadastrar(pautaMock)).thenReturn(pautaMock);

		Pauta pauta = pautaService.cadastrar(pautaMock);

		Assertions.assertThat(pauta.getId()).isEqualTo(pautaMock.getId());
		Assertions.assertThat(pauta.getDescricao()).isEqualTo(pautaMock.getDescricao());
	}

}
