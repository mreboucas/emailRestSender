package br.com.mreboucas.emailRest.rest.dto;

import br.com.mreboucas.emailUtil.dto.DtoEmail;

/**
 * @author Marcelo Rebouças 10 de jul de 2018 - 15:41:36 [marceloreboucas10@gmail.com]
 */
public class RetornoDto {
	
	String dataGeracao;
	DtoEmail emailDto;

	public RetornoDto(String dataGeracao, DtoEmail emailDto) {
		super();
		this.dataGeracao = dataGeracao;
		this.emailDto = emailDto;
	}

	/**
	 * @return the dataGeracao
	 */
	public String getDataGeracao() {
		return dataGeracao;
	}

	/**
	 * @param dataGeracao the dataGeracao to set
	 */
	public void setDataGeracao(String dataGeracao) {
		this.dataGeracao = dataGeracao;
	}

	/**
	 * @return the emailDto
	 */
	public DtoEmail getEmailDto() {
		return emailDto;
	}

	/**
	 * @param emailDto the emailDto to set
	 */
	public void setEmailDto(DtoEmail emailDto) {
		this.emailDto = emailDto;
	}
}
