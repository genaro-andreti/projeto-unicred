package br.com.unicred.api.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.unicred.api.dto.StatusCpf;

@FeignClient(url= "https://user-info.herokuapp.com/users/" , name = "viacpf")
public interface ValidaCpfService {

	@RequestMapping("{cpf}")
	public StatusCpf validaCpf(@PathVariable("cpf") String cpf);
}
