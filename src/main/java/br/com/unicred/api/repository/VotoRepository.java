package br.com.unicred.api.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.unicred.api.model.Voto;

@Repository
public interface VotoRepository extends MongoRepository<Voto, String> {
	
	List<Voto> getByAssociadoAndSessaoVotacao(String idAssociado, String idSessaoVotacao);
	
	List<Voto> getByAssociado(String idAssociado);

	List<Voto> getBySessaoVotacao(String idSessaoVotacao);
	
}
