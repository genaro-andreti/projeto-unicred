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

import br.com.unicred.api.dto.PautaDto;
import br.com.unicred.api.model.Pauta;
import br.com.unicred.api.response.Response;
import br.com.unicred.api.service.PautaService;

@RestController
@RequestMapping("/unicred/pauta")
public class PautaController {

	@Autowired
	private PautaService pautaService;

	@ResponseBody
	@PostMapping(path = "/cadastrar", produces = "application/json")
	public ResponseEntity<Response<Pauta>> cadastrar(@Valid @RequestBody PautaDto pautaDto, BindingResult result) {
		Response<Pauta> response = new Response<Pauta>();


		if (result.hasErrors()) {
			result.getAllErrors().forEach(
					error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		Pauta pautaCadastro = new Pauta();
		pautaCadastro.setDescricao(pautaDto.getDescricao());

		Pauta retorno = pautaService
				.cadastrar(pautaCadastro);
		response.setData(retorno);

		return ResponseEntity.ok(response);
	}

}
