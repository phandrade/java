package com.castgroup.assignment3.model;

import java.io.Serializable;
import java.util.List;

public class MensagemRespostaPessoa implements Serializable {
	
	private static final long serialVersionUID = -6805097157314162841L;
	
	private String erro;
	private String mensagem;
	private Pessoa pessoaSelecionada;
	private List<Pessoa> listaTodasPessoas;
	
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
	public List<Pessoa> getListaTodasPessoas() {
		return listaTodasPessoas;
	}
	public void setListaTodasPessoas(List<Pessoa> listaTodasPessoas) {
		this.listaTodasPessoas = listaTodasPessoas;
	}
	public Pessoa getPessoaSelecionada() {
		return pessoaSelecionada;
	}
	public void setPessoaSelecionada(Pessoa pessoaSelecionada) {
		this.pessoaSelecionada = pessoaSelecionada;
	}	
}
