package com.castgroup.assignment3.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.castgroup.assignment3.model.MensagemRespostaPessoa;
import com.castgroup.assignment3.model.Pessoa;
import com.castgroup.assignment3.repository.PessoaData;

@Service
public class PessoaServiceImpl implements PessoaService {

	public MensagemRespostaPessoa listarTodasPessoas() {
		
		MensagemRespostaPessoa resposta = new MensagemRespostaPessoa();
		List<Pessoa> listaTodasPessoas = PessoaData.obterTodasPessoas();
		
		if(listaTodasPessoas == null) {
			resposta.setErro("Erro ao acessar dados");
			resposta.setMensagem("Não foi possível acessar a base de dados");
		} else {
			resposta.setListaTodasPessoas(listaTodasPessoas);
			resposta.setMensagem("Localizado o cadastro de todas as pessoas");
		}
		
		return resposta;
	}
	
	public MensagemRespostaPessoa buscarPessoaPorId(Long id) {		
		
		MensagemRespostaPessoa resposta = new MensagemRespostaPessoa();
		Pessoa pessoaSelecionada = PessoaData.obterPessoaPorId(id);
		
		if(id == null) {
			resposta.setErro("Dados obrigatórios");
			resposta.setMensagem("Favor informar o Id da Pessoa");
		} else if(pessoaSelecionada == null) {
			resposta.setErro("Dados não localizados");
			resposta.setMensagem("Não foi localizada nenhuma Pessoa com o Id informado");
		} else {
			resposta.setPessoaSelecionada(pessoaSelecionada);
			resposta.setMensagem("Pessoa localizada");
		}
		
		return resposta;
		
	}
	
	public MensagemRespostaPessoa gravarPessoa(Pessoa pessoa) {
		MensagemRespostaPessoa resposta = new MensagemRespostaPessoa();
		
		if(pessoa == null || pessoa.getName() == null) {
			resposta.setErro("Dados obrigatórios");
			resposta.setMensagem("Favor informar os dados da pessoa");
			return resposta;
		}
		
		Pessoa pessoaSelecionada = PessoaData.adicionarAlterarPessoa(pessoa);
		
		if(pessoaSelecionada == null) {
			resposta.setErro("Erro ao gravar dados");
			resposta.setMensagem("Erro ao tentar gravar as informações da pessoa");
		} else {
			resposta.setPessoaSelecionada(pessoaSelecionada);
			resposta.setMensagem("Pessoa gravada");
		}
		return resposta;
	}
	
	public MensagemRespostaPessoa removerPessoa(Long id) {
		MensagemRespostaPessoa resposta = new MensagemRespostaPessoa();
		
		if(id == null) {
			resposta.setErro("Dados obrigatórios");
			resposta.setMensagem("Favor informar o id da pessoa");
			return resposta;
		}
		
		Pessoa pessoaSelecionada = PessoaData.removerPessoa(id);
		
		if(pessoaSelecionada == null) {
			resposta.setErro("Erro ao remover dados");
			resposta.setMensagem("A pessoa não foi localizada ou não foi possível acessar os dados da pessoa");
		} else {
			resposta.setPessoaSelecionada(pessoaSelecionada);
			resposta.setMensagem("Pessoa removida");
		}
		
		return resposta;
	}
}
