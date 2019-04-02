package com.castgroup.assignment1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.castgroup.assignment1.model.InputObject;
import com.castgroup.assignment1.model.OutputObject;
import com.castgroup.assignment1.service.DiffServiceImpl;

@RestController
@RequestMapping("/v1/diff/{id}")
public class DiffController {
	
	@Autowired
	private DiffServiceImpl diffService;
	
	@RequestMapping(value = "/left", method = RequestMethod.POST, produces = "application/json")
	public OutputObject diffLadoEsquerdo(@PathVariable String id, @RequestBody InputObject input) {
		OutputObject resposta = new OutputObject();
		
		if(input != null && input.getData() != null) {
			diffService.gravarDadosDiff(resposta, id, input.getData(), null);
		} else {
			resposta.setErro("Campos Obrigatórios");
			resposta.setMensagem("Favor informar o campo data");
		}
		return resposta;
	}
	
	@RequestMapping(value = "/right", method = RequestMethod.POST, produces = "application/json")
	public OutputObject diffLadoDireito(@PathVariable String id, @RequestBody InputObject input) {
		OutputObject resposta = new OutputObject();
		
		if(input != null && input.getData() != null) {
			diffService.gravarDadosDiff(resposta, id, null, input.getData());
		} else {
			resposta.setErro("Campos Obrigatórios");
			resposta.setMensagem("Favor informar o campo data");
		}
		return resposta;
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public OutputObject compararDados(@PathVariable String id) {
		
		OutputObject resposta = new OutputObject();
		
		diffService.compararDados(resposta, id);
		return resposta;		
	}
}
