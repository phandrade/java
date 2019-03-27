package com.castgroup.assignment1.repository;

import java.util.HashMap;

public class DiffData {
	
	private static HashMap<String, String[]> diffs;
	public static final int INDICE_DIFF_LADO_ESQUERDO = 0;
	public static final int INDICE_DIFF_LADO_DIREITO = 1;
	
	public static void inicializar() {		
		diffs = new HashMap<>();
	}
	
	public static HashMap<String, String[]> obterTodosDiffs() {
		return diffs;
	}
	
	public static boolean existeDiffCadastrado(String id) {
		return diffs != null && id != null && diffs.containsKey(id);
	}
	
	public static String[] buscarDiffPorId(String id) {
		if(existeDiffCadastrado(id)) {		
			return diffs.get(id);
		}
		return null;
	}
	
	public static void adicionarDiff(String id, String diffEsquerdo, String diffDireito) {
		if(diffs != null) {
			diffs.put(id, new String[]{diffEsquerdo, diffDireito});
		}
	}
	
	public static void alterarDadosDiff(String id, String diffEsquerdo, String diffDireito) throws Exception {		
		if(existeDiffCadastrado(id)) {
			
			String[] diffAtual = buscarDiffPorId(id);
			String[] novoDiff = new String[2];
			
			if(diffEsquerdo != null) {
				novoDiff[INDICE_DIFF_LADO_ESQUERDO] = diffEsquerdo;
			} else {
				novoDiff[INDICE_DIFF_LADO_ESQUERDO] = diffAtual[INDICE_DIFF_LADO_ESQUERDO];
			}
			
			if(diffDireito != null) {
				novoDiff[INDICE_DIFF_LADO_DIREITO] = diffDireito;
			} else {
				novoDiff[INDICE_DIFF_LADO_DIREITO] = diffAtual[INDICE_DIFF_LADO_DIREITO];
			}
			
			diffs.put(id, novoDiff);
		} else if(id != null && (diffEsquerdo != null || diffDireito != null)){
			adicionarDiff(id, diffEsquerdo, diffDireito);
		}
	}
}
