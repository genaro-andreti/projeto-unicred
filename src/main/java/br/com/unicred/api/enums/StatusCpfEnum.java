package br.com.unicred.api.enums;

import lombok.Getter;

@Getter
public enum StatusCpfEnum {
	
	ABLE_TO_VOTE("Voto permitido"), UNABLE_TO_VOTE("Voto negado");
	
	private StatusCpfEnum(String valor) {
		this.valor = valor;
	}
	
	private String valor;
	
}
