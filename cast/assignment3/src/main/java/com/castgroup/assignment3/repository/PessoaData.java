package com.castgroup.assignment3.repository;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.castgroup.assignment3.model.PessoaDTO;

public class PessoaData {

	private static HashMap<Long, PessoaDTO> pessoas;
	
	public static void inicializar() {		
		pessoas = new HashMap<>();
	}
	public static void anularDados() {
		pessoas = null;
	}
	
	public static List<PessoaDTO> obterTodasPessoas() {
		if(pessoas != null) {			
			return pessoas.values().stream().collect(Collectors.toList());
		}
		return null;
	}
	
	public static PessoaDTO obterPessoaPorId(Long id) {
		
		if(pessoas != null && pessoas.containsKey(id)) {
			return pessoas.get(id);
		}
		return null;
	}
	
	public static PessoaDTO adicionarAlterarPessoa(PessoaDTO pessoa) {
		
		if(pessoas != null) {
			
			if(pessoa.getId() == null) {
				Long idPessoa = 0L;
				idPessoa += pessoas.size() + 1;				
				pessoa.setId(idPessoa);								
			}
			pessoas.put(pessoa.getId(), pessoa);
			return pessoa;
		}
		return null;
	}
	
	public static PessoaDTO removerPessoa(Long id) {
		if(pessoas != null && pessoas.containsKey(id)) {
			return pessoas.remove(id);			
		}
		return null;
	}
}
