package br.com.unicred.api;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.unicred.api.model.Associado;
import br.com.unicred.api.repository.AssociadoRepository;
import br.com.unicred.api.service.AssociadoService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AssociadoServiceTest {

	@Autowired
	private AssociadoService associadoService;
	
    @MockBean
    private AssociadoRepository repository;
	
	@Test
	public void quandoInserimosUmAssociado() {

		Associado associadoMock = new Associado();
		associadoMock.setId("5e4ace478b4167031d2b72f3");
		associadoMock.setNome("William Wallace");
		associadoMock.setCpf("74966363047");
		associadoMock.setLogin("89888765678");
		associadoMock.setSenha("123456");
		
		Mockito.when(associadoService.cadastrar(associadoMock)).thenReturn(associadoMock);
		
		Associado associado = associadoService.cadastrar(associadoMock);

		Assertions.assertThat(associado.getId()).isEqualTo(associadoMock.getId());
		Assertions.assertThat(associado.getNome()).isEqualTo(associadoMock.getNome());
		Assertions.assertThat(associado.getCpf()).isEqualTo(associadoMock.getCpf());
		Assertions.assertThat(associado.getLogin()).isEqualTo(associadoMock.getLogin());
		Assertions.assertThat(associado.getSenha()).isEqualTo(associadoMock.getSenha());
	}

}
