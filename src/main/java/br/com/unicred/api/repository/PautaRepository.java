package br.com.unicred.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.unicred.api.model.Pauta;

@Repository
public interface PautaRepository extends MongoRepository<Pauta, String>{

}
