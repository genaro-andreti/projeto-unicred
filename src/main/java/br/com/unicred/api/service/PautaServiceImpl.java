package br.com.unicred.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.unicred.api.model.Pauta;
import br.com.unicred.api.repository.PautaRepository;

@Service
public class PautaServiceImpl implements PautaService {
	
	@Autowired
	private PautaRepository pautaRepository;

	@Override
	public Pauta cadastrar(Pauta pauta) {
		return pautaRepository.save(pauta);
	}

	@Override
	public Boolean pautaCadastrada(String idPauta) {
		return pautaRepository.existsById(idPauta);
	}

}
