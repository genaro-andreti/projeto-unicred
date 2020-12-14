package br.com.unicred.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.unicred.api.model.Associado;

@Repository
public interface AssociadoRepository extends MongoRepository<Associado, String>{
	
}
