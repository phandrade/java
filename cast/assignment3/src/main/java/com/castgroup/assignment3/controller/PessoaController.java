package com.castgroup.assignment3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.castgroup.assignment3.model.MensagemRespostaPessoa;
import com.castgroup.assignment3.model.PessoaDTO;
import com.castgroup.assignment3.service.PessoaService;

@RestController
@RequestMapping("/rest")
public class PessoaController {
	
	@Autowired
	private PessoaService pessoaService;

	@RequestMapping(value = "/pessoas", method = RequestMethod.GET, produces = "application/json")
	public MensagemRespostaPessoa listarTodasPessoas() {
		return pessoaService.listarTodasPessoas();
	}
	
	@RequestMapping(value = "/pessoa/{id}", method = RequestMethod.GET, produces = "application/json")
	public MensagemRespostaPessoa buscarPessoaPorId(@PathVariable Long id) {
		return pessoaService.buscarPessoaPorId(id);
	}
	
	@RequestMapping(value = "/pessoa/save", method = RequestMethod.POST, produces = "application/json")
	public MensagemRespostaPessoa gravarPessoa(@RequestBody PessoaDTO pessoa) {
		return pessoaService.gravarPessoa(pessoa);
	}
	
	@RequestMapping(value = "/pessoa/remove/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public MensagemRespostaPessoa removerPessoaPorId(@PathVariable Long id) {
		return pessoaService.removerPessoa(id);
	}
}
