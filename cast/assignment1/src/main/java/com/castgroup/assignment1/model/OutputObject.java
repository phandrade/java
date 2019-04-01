package com.castgroup.assignment1.model;

import java.io.Serializable;

public class OutputObject implements Serializable {

	private static final long serialVersionUID = 7726555731613868779L;
	
	private String erro;
	private String mensagem;
	private String data;
	
	public String getErro() {
		return erro;
	}
	public void setErro(String erro) {
		this.erro = erro;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
}
