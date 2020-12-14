package br.com.unicred.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.unicred.api.model.Associado;
import br.com.unicred.api.repository.AssociadoRepository;

@Service
public class AssociadoServiceImpl implements AssociadoService {
	
	@Autowired
	private AssociadoRepository associadoRepository;

	@Override
	public Associado cadastrar(Associado associado) {
		return associadoRepository.save(associado);
	}

	@Override
	public Boolean associadoCadastrado(String idAssociado) {
		return associadoRepository.existsById(idAssociado);
	}
	
	@Override
	public Optional<Associado> findById(String idAssociado) {
		return associadoRepository.findById(idAssociado);
	}

}
