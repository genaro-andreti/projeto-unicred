package br.com.unicred.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import br.com.unicred.api.dto.RetornoVotacaoDto;
import br.com.unicred.api.exception.AssociadoNaoCadastradoException;
import br.com.unicred.api.exception.PautaNaoCadastradoException;
import br.com.unicred.api.exception.PautaNaoVotadaException;
import br.com.unicred.api.exception.SessaoVotacaoFechadaException;
import br.com.unicred.api.exception.SessaoVotacaoNaoCadastradoException;
import br.com.unicred.api.exception.VotoAssociadoCadastradoParaPautaException;
import br.com.unicred.api.model.SessaoVotacao;
import br.com.unicred.api.model.Voto;
import br.com.unicred.api.repository.VotoRepository;

@Service
public class VotoServiceImpl implements VotoService {

	@Autowired
	private VotoRepository votoRepository;

	@Autowired
	private AssociadoService associadoService;

	@Autowired
	private SessaoVotacaoService sessaoVotacaoService;

	@Autowired
	private PautaService pautaService;
	
	@Override
	public Voto cadastrar(Voto voto) {
		validaCadastrar(voto);
		return votoRepository.save(voto);
	}
	
	private void validaCadastrar(Voto voto) {
		if (!associadoService.associadoCadastrado(voto.getAssociado().getId())) {
			throw new AssociadoNaoCadastradoException();
		}

		SessaoVotacao sessaoCarregada = sessaoVotacaoService
				.sessaoVotacaoCadastrado(voto.getSessaoVotacao().getId()).get();

		if (ObjectUtils.isEmpty(sessaoCarregada)) {
			throw new SessaoVotacaoNaoCadastradoException();
		}

		if (sessaoCarregada.sessaoFechada()) {
			throw new SessaoVotacaoFechadaException();
		}
		
		if (votoAssociadoCadastradoParaPauta(voto.getAssociado().getId(), sessaoCarregada.getId())) {
			throw new VotoAssociadoCadastradoParaPautaException();
		}
	}
	
	@Override
	public RetornoVotacaoDto retornaVotacaoPorPauta(String idPauta) {
		if (!pautaService.pautaCadastrada(idPauta)) {
			throw new PautaNaoCadastradoException();
		}

		SessaoVotacao sessaoVotacao = sessaoVotacaoService.getByPauta(idPauta);
		
		List<Voto> votos = votoRepository.getBySessaoVotacao(sessaoVotacao.getId());
		
		if (votos.isEmpty()) {
			throw new PautaNaoVotadaException();
		}
		
		return new RetornoVotacaoDto(votos);
	}

	@Override
	public Boolean votoAssociadoCadastradoParaPauta(String idAssociado, String idSessaoVotacao) {
		return !votoRepository.getByAssociadoAndSessaoVotacao(idAssociado, idSessaoVotacao).isEmpty();
	}

}
