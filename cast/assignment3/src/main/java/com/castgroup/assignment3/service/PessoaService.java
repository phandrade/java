package com.castgroup.assignment3.service;

import com.castgroup.assignment3.model.MensagemRespostaPessoa;
import com.castgroup.assignment3.model.PessoaDTO;

public interface PessoaService {

	public MensagemRespostaPessoa listarTodasPessoas();
	public MensagemRespostaPessoa buscarPessoaPorId(Long id);
	public MensagemRespostaPessoa gravarPessoa(PessoaDTO pessoa);
	public MensagemRespostaPessoa removerPessoa(Long id);
	
}
