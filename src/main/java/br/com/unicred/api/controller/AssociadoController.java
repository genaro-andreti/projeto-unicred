package br.com.unicred.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.unicred.api.dto.AssociadoDto;
import br.com.unicred.api.dto.StatusCpf;
import br.com.unicred.api.enums.StatusCpfEnum;
import br.com.unicred.api.model.Associado;
import br.com.unicred.api.response.Response;
import br.com.unicred.api.service.AssociadoService;
import br.com.unicred.api.service.SessaoVotacaoService;
import br.com.unicred.api.service.ValidaCpfService;

@RestController
@RequestMapping("/unicred/associado")
public class AssociadoController {

	@Autowired
	private AssociadoService associadoService;
	
	@Autowired
	private ValidaCpfService validaCpfService;
	
	@Autowired
	SessaoVotacaoService sessaoVotacaoService;
	
	@ResponseBody
	@PostMapping(path = "/cadastrar", produces = "application/json")
	public ResponseEntity<Response<Associado>> cadastrar(@Valid @RequestBody AssociadoDto associadoDto, BindingResult result) {
		Response<Associado> response = new Response<Associado>();
		
		if (result.hasErrors()) {
			result.getAllErrors().forEach(
					error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		Associado associadoCadastro = new Associado();
		associadoCadastro.setLogin(associadoDto.getLogin());
		associadoCadastro.setNome(associadoDto.getNome());
		associadoCadastro.setCpf(associadoDto.getCpf());
		associadoCadastro.setSenha(associadoDto.getSenha());
		
		try {
			
			StatusCpf statusCpf = validaCpfService.validaCpf(associadoCadastro.getCpf());
			
			if(!StatusCpfEnum.ABLE_TO_VOTE.toString().equals(statusCpf.getStatus())) {
				return ResponseEntity.notFound().build();
			}
			
		} catch (Exception e) {
				return ResponseEntity.notFound().build();
		}
		
		Associado retorno = associadoService.cadastrar(associadoCadastro);
		response.setData(retorno);
		return ResponseEntity.ok(response);
	}

}
