package br.com.unicred.api.response;

import br.com.unicred.api.enums.StatusCpfEnum;

public class CpfResponse {
	
	private StatusCpfEnum status;
	
	public CpfResponse(StatusCpfEnum status) {
		this.status = status;
	}

	public StatusCpfEnum getStatus() {
		return status;
	}

	public void setStatus(StatusCpfEnum status) {
		this.status = status;
	}
	
}
