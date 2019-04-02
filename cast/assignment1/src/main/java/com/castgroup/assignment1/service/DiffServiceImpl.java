package com.castgroup.assignment1.service;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.castgroup.assignment1.model.OutputObject;
import com.castgroup.assignment1.repository.DiffData;

@Service
public class DiffServiceImpl implements DiffService {

	public void gravarDadosDiff(OutputObject resposta, String id, String diffEsquerdo, String diffDireito) {		
		try {
			
			if(id == null ) {
				resposta.setErro("Campos Obrigatórios");
				resposta.setMensagem("Favor informar o id");
				return;
			}
			if(diffEsquerdo == null && diffDireito == null) {
				resposta.setErro("Campos Obrigatórios");
				resposta.setMensagem("Favor informar os dados para comparacao");
				return;
			}
			
			DiffData.alterarDadosDiff(id, diffEsquerdo, diffDireito);
			resposta.setMensagem("Diff gravado com sucesso!");
			String[] dadosDiff = DiffData.buscarDiffPorId(id);
			resposta.setData("id=" + id + ", esquerdo=" + dadosDiff[DiffData.INDICE_DIFF_LADO_ESQUERDO] + ", direito=" + dadosDiff[DiffData.INDICE_DIFF_LADO_DIREITO]);
		} catch (Exception e) {
			e.printStackTrace();
			resposta.setErro("Erro ao gravar dados");
			resposta.setMensagem(e.getMessage());
		}
	}
	
	public void compararDados(OutputObject resposta, String id) {
		
		if(id == null ) {
			resposta.setErro("Campos Obrigatórios");
			resposta.setMensagem("Favor informar o id");
			return;
		}
		
		String[] dadosDiff = DiffData.buscarDiffPorId(id);		
		
		if(dadosDiff == null || dadosDiff[DiffData.INDICE_DIFF_LADO_ESQUERDO] == null || dadosDiff[DiffData.INDICE_DIFF_LADO_DIREITO] == null) {
			resposta.setMensagem("Não existem todos os dados para comparar");
			
			if(dadosDiff == null) {
				resposta.setData("id=" + id + ", esquerdo=null, direito=null");				
			} else {
				resposta.setData("id=" + id + ", esquerdo=" + dadosDiff[DiffData.INDICE_DIFF_LADO_ESQUERDO] + ", direito=" + dadosDiff[DiffData.INDICE_DIFF_LADO_DIREITO]);
			}
			return;
		}
		
		byte[] bytesEsquerdo = dadosDiff[DiffData.INDICE_DIFF_LADO_ESQUERDO].getBytes();
		byte[] bytesDireito = dadosDiff[DiffData.INDICE_DIFF_LADO_DIREITO].getBytes();
		
		if(Arrays.equals(bytesEsquerdo, bytesDireito)) {
			resposta.setMensagem("Os dados são iguais");
			resposta.setData("id=" + id + ", esquerdo=" + dadosDiff[DiffData.INDICE_DIFF_LADO_ESQUERDO] + ", direito=" + dadosDiff[DiffData.INDICE_DIFF_LADO_DIREITO]);
		} else if(bytesEsquerdo.length != bytesDireito.length) {
			resposta.setMensagem("Os dados não possuem o mesmo tamanho");
			resposta.setData("id=" + id + ", esquerdo=" + dadosDiff[DiffData.INDICE_DIFF_LADO_ESQUERDO] + ", direito=" + dadosDiff[DiffData.INDICE_DIFF_LADO_DIREITO]);
		} else {
			String indicesDiferentes = "";
			for(int indice=0; indice < bytesEsquerdo.length; indice++) {
				
				if(bytesEsquerdo[indice] != bytesDireito[indice]) {
					indicesDiferentes = indicesDiferentes + " " + indice;
				}
			}
			resposta.setMensagem("Os dados possuem o mesmo tamanho, mas seus índices são direferentes, índices:" + indicesDiferentes);
			resposta.setData("id=" + id + ", esquerdo=" + dadosDiff[DiffData.INDICE_DIFF_LADO_ESQUERDO] + ", direito=" + dadosDiff[DiffData.INDICE_DIFF_LADO_DIREITO]);
		}
	}
	
	public String[] buscarDiffPorId(String id) {
		return DiffData.buscarDiffPorId(id);
	}
}
