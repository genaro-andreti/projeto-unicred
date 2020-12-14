package br.com.unicred.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import br.com.unicred.api.exception.PautaNaoCadastradoException;
import br.com.unicred.api.exception.SessaoVotacaoNaoCadastradoException;
import br.com.unicred.api.model.SessaoVotacao;
import br.com.unicred.api.repository.SessaoVotacaoRepository;

@Service
public class SessaoVotacaoServiceImpl implements SessaoVotacaoService {
	
	@Autowired
	private SessaoVotacaoRepository sessaoVotacaoRepository;
	
	@Autowired
	private PautaService pautaService;

	@Override
	public SessaoVotacao cadastrar(SessaoVotacao sessaoVotacao) {
		if (!pautaService.pautaCadastrada(sessaoVotacao.getPauta().getId())) {
			throw new PautaNaoCadastradoException();
		}
		return sessaoVotacaoRepository.save(sessaoVotacao);
	}

	@Override
	public Optional<SessaoVotacao> sessaoVotacaoCadastrado(String idSessaoVotacao) {
		return sessaoVotacaoRepository.findById(idSessaoVotacao);
	}
	
	@Override
	public SessaoVotacao getByPauta(String idPauta) {
		Optional<SessaoVotacao> sessaoVotacao = sessaoVotacaoRepository.getByPauta(idPauta);
		
		if(ObjectUtils.isEmpty(sessaoVotacao)) {
			throw new SessaoVotacaoNaoCadastradoException();
		}
		return sessaoVotacaoRepository.getByPauta(idPauta).get();
	}
	
	@Override
	public List<SessaoVotacao> findAll() {
		List<SessaoVotacao> sessaoVotacao = sessaoVotacaoRepository.findAll();
		
		if(ObjectUtils.isEmpty(sessaoVotacao)) {
			throw new SessaoVotacaoNaoCadastradoException();
		}

		return sessaoVotacaoRepository.findAll();
	}

}
