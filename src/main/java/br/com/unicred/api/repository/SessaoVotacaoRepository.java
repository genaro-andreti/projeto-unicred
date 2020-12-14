package br.com.unicred.api.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.unicred.api.model.SessaoVotacao;

@Repository
public interface SessaoVotacaoRepository extends MongoRepository<SessaoVotacao, String> {
	
	Optional<SessaoVotacao> getByPauta(String idPauta);

}
