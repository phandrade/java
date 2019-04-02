package com.castgroup.assignment1.service;

import com.castgroup.assignment1.model.OutputObject;

public interface DiffService {

	public void gravarDadosDiff(OutputObject resposta, String id, String diffEsquerdo, String diffDireito);
	public void compararDados(OutputObject resposta, String id);
	public String[] buscarDiffPorId(String id);
}
