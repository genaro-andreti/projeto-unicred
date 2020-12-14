package br.com.unicred.api.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.unicred.api.dto.SessaoVotacaoDto;
import br.com.unicred.api.model.Pauta;
import br.com.unicred.api.model.SessaoVotacao;
import br.com.unicred.api.response.Response;
import br.com.unicred.api.service.SessaoVotacaoService;

@RestController
@RequestMapping("/unicred/sessaoVotacao")
public class SessaoVotacaoController {

	@Autowired
	private SessaoVotacaoService sessaoVotacaoService;

	@ResponseBody
	@PostMapping(path = "/cadastrar", produces = "application/json")
	public ResponseEntity<Response<SessaoVotacao>> cadastrar(@Valid @RequestBody SessaoVotacaoDto sessaoVotacaoDto,
			BindingResult result) {
		Response<SessaoVotacao> response = new Response<SessaoVotacao>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(
					error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		LocalDateTime dataInicio = LocalDateTime.now();
		
		SessaoVotacao sessaoVotacaoCadastro = new SessaoVotacao();
		sessaoVotacaoCadastro.setInicioSessaoVotacao(dataInicio);
		sessaoVotacaoCadastro.setFimSessaoVotacao(dataInicio);
		
		Pauta pautaCadastro = new Pauta();
		pautaCadastro.setId(sessaoVotacaoDto.getIdPauta());
		
		sessaoVotacaoCadastro.setPauta(pautaCadastro);
		
		SessaoVotacao retorno = sessaoVotacaoService.cadastrar(sessaoVotacaoCadastro);

		response.setData(retorno);
		return ResponseEntity.ok(response);
	}
	
	@ResponseBody
	@GetMapping(value = "/retornar/sessoesvotacao", produces = "application/json")
	public ResponseEntity<Response<List<SessaoVotacao>>> retornaSessoesVotacao() {
		Response<List<SessaoVotacao>> response = new Response<List<SessaoVotacao>>();
		List<SessaoVotacao> listaSessaoVotacao = sessaoVotacaoService.findAll();
		response.setData(listaSessaoVotacao);
		return ResponseEntity.ok(response);
	}

}
