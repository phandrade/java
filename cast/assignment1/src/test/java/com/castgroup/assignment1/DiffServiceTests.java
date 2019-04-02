package com.castgroup.assignment1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.castgroup.assignment1.model.OutputObject;
import com.castgroup.assignment1.repository.DiffData;
import com.castgroup.assignment1.service.DiffService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DiffServiceTests {
	
	@Autowired
	private DiffService service;

	private String[] diffLadoEsquerdoSomente;
	private String[] diffLadoDireitoSomente;
	private String[] diffLadosIguais;
	private String[] diffLadosDiferentesMesmoTamanho;
	private String[] diffLadosDiferentesTamanhoDiferente;
	
	@Before
	public void inicializarAmbiente() {
		DiffData.inicializar();
		diffLadoEsquerdoSomente = new String[]{"012012012", null};
		diffLadoDireitoSomente = new String[]{null, "012012012"};
		diffLadosIguais = new String[]{"123123123", "123123123"};
		diffLadosDiferentesMesmoTamanho = new String[]{"123123123", "121121121"};
		diffLadosDiferentesTamanhoDiferente = new String[]{"1212121", "11111111111"};
	}
	
	@Test
	public void testarGravacaoDiffLadoEsquerdoSomente() {
		
		OutputObject resposta = new OutputObject();
		String id = "123";
		service.gravarDadosDiff(resposta, id, diffLadoEsquerdoSomente[DiffData.INDICE_DIFF_LADO_ESQUERDO], diffLadoEsquerdoSomente[DiffData.INDICE_DIFF_LADO_DIREITO]);
		String[] dadosDiff = service.buscarDiffPorId(id);
		
		Assert.assertEquals("Diff gravado com sucesso!", resposta.getMensagem());
		Assert.assertNotNull(dadosDiff);
		Assert.assertNotNull(dadosDiff[DiffData.INDICE_DIFF_LADO_ESQUERDO]);
		Assert.assertNull(dadosDiff[DiffData.INDICE_DIFF_LADO_DIREITO]);		
		Assert.assertEquals(dadosDiff[DiffData.INDICE_DIFF_LADO_ESQUERDO], diffLadoEsquerdoSomente[DiffData.INDICE_DIFF_LADO_ESQUERDO]);
		Assert.assertEquals(dadosDiff[DiffData.INDICE_DIFF_LADO_DIREITO], diffLadoEsquerdoSomente[DiffData.INDICE_DIFF_LADO_DIREITO]);
	}
	
	@Test
	public void testarGravacaoDiffLadoDireitoSomente() {
		
		OutputObject resposta = new OutputObject();
		String id = "123";
		service.gravarDadosDiff(resposta, id, diffLadoDireitoSomente[DiffData.INDICE_DIFF_LADO_ESQUERDO], diffLadoDireitoSomente[DiffData.INDICE_DIFF_LADO_DIREITO]);
		String[] dadosDiff = service.buscarDiffPorId(id);
		
		Assert.assertEquals("Diff gravado com sucesso!", resposta.getMensagem());
		Assert.assertNotNull(dadosDiff);
		Assert.assertNull(dadosDiff[DiffData.INDICE_DIFF_LADO_ESQUERDO]);
		Assert.assertNotNull(dadosDiff[DiffData.INDICE_DIFF_LADO_DIREITO]);		
		Assert.assertEquals(dadosDiff[DiffData.INDICE_DIFF_LADO_ESQUERDO], diffLadoDireitoSomente[DiffData.INDICE_DIFF_LADO_ESQUERDO]);
		Assert.assertEquals(dadosDiff[DiffData.INDICE_DIFF_LADO_DIREITO], diffLadoDireitoSomente[DiffData.INDICE_DIFF_LADO_DIREITO]);
	}
	
	@Test
	public void testarGravacaoDiffLadosIguais() {
		
		OutputObject resposta = new OutputObject();
		String id = "123";
		service.gravarDadosDiff(resposta, id, diffLadosIguais[DiffData.INDICE_DIFF_LADO_ESQUERDO], diffLadosIguais[DiffData.INDICE_DIFF_LADO_DIREITO]);
		String[] dadosDiff = service.buscarDiffPorId(id);
		
		Assert.assertEquals("Diff gravado com sucesso!", resposta.getMensagem());
		Assert.assertNotNull(dadosDiff);
		Assert.assertNotNull(dadosDiff[DiffData.INDICE_DIFF_LADO_ESQUERDO]);
		Assert.assertNotNull(dadosDiff[DiffData.INDICE_DIFF_LADO_DIREITO]);		
		Assert.assertEquals(dadosDiff[DiffData.INDICE_DIFF_LADO_ESQUERDO], diffLadosIguais[DiffData.INDICE_DIFF_LADO_ESQUERDO]);
		Assert.assertEquals(dadosDiff[DiffData.INDICE_DIFF_LADO_DIREITO], diffLadosIguais[DiffData.INDICE_DIFF_LADO_DIREITO]);
	}
	
	@Test
	public void testarGravacaoDiffLadosDiferentesMesmoTamanho() {
		
		OutputObject resposta = new OutputObject();
		String id = "123";
		service.gravarDadosDiff(resposta, id, diffLadosDiferentesMesmoTamanho[DiffData.INDICE_DIFF_LADO_ESQUERDO], diffLadosDiferentesMesmoTamanho[DiffData.INDICE_DIFF_LADO_DIREITO]);
		String[] dadosDiff = service.buscarDiffPorId(id);
		
		Assert.assertEquals("Diff gravado com sucesso!", resposta.getMensagem());
		Assert.assertNotNull(dadosDiff);
		Assert.assertNotNull(dadosDiff[DiffData.INDICE_DIFF_LADO_ESQUERDO]);
		Assert.assertNotNull(dadosDiff[DiffData.INDICE_DIFF_LADO_DIREITO]);		
		Assert.assertEquals(dadosDiff[DiffData.INDICE_DIFF_LADO_ESQUERDO], diffLadosDiferentesMesmoTamanho[DiffData.INDICE_DIFF_LADO_ESQUERDO]);
		Assert.assertEquals(dadosDiff[DiffData.INDICE_DIFF_LADO_DIREITO], diffLadosDiferentesMesmoTamanho[DiffData.INDICE_DIFF_LADO_DIREITO]);
		Assert.assertNotEquals(dadosDiff[DiffData.INDICE_DIFF_LADO_ESQUERDO], dadosDiff[DiffData.INDICE_DIFF_LADO_DIREITO]);
		Assert.assertEquals(dadosDiff[DiffData.INDICE_DIFF_LADO_ESQUERDO].length(), dadosDiff[DiffData.INDICE_DIFF_LADO_DIREITO].length());
	}
	
	@Test
	public void testarGravacaoDiffLadosDiferentesTamanhoDiferente() {
		
		OutputObject resposta = new OutputObject();
		String id = "123";
		service.gravarDadosDiff(resposta, id, diffLadosDiferentesTamanhoDiferente[DiffData.INDICE_DIFF_LADO_ESQUERDO], diffLadosDiferentesTamanhoDiferente[DiffData.INDICE_DIFF_LADO_DIREITO]);
		String[] dadosDiff = service.buscarDiffPorId(id);
		
		Assert.assertEquals("Diff gravado com sucesso!", resposta.getMensagem());
		Assert.assertNotNull(dadosDiff);
		Assert.assertNotNull(dadosDiff[DiffData.INDICE_DIFF_LADO_ESQUERDO]);
		Assert.assertNotNull(dadosDiff[DiffData.INDICE_DIFF_LADO_DIREITO]);		
		Assert.assertEquals(dadosDiff[DiffData.INDICE_DIFF_LADO_ESQUERDO], diffLadosDiferentesTamanhoDiferente[DiffData.INDICE_DIFF_LADO_ESQUERDO]);
		Assert.assertEquals(dadosDiff[DiffData.INDICE_DIFF_LADO_DIREITO], diffLadosDiferentesTamanhoDiferente[DiffData.INDICE_DIFF_LADO_DIREITO]);
		Assert.assertNotEquals(dadosDiff[DiffData.INDICE_DIFF_LADO_ESQUERDO], dadosDiff[DiffData.INDICE_DIFF_LADO_DIREITO]);
		Assert.assertNotEquals(dadosDiff[DiffData.INDICE_DIFF_LADO_ESQUERDO].length(), dadosDiff[DiffData.INDICE_DIFF_LADO_DIREITO].length());
	}
	
	@Test
	public void testarComparacaoDiffLadosIguais() {
		
		OutputObject resposta = new OutputObject();
		String id = "123";
		service.gravarDadosDiff(resposta, id, diffLadosIguais[DiffData.INDICE_DIFF_LADO_ESQUERDO], diffLadosIguais[DiffData.INDICE_DIFF_LADO_DIREITO]);
		resposta = new OutputObject();
		service.compararDados(resposta, id);
		
		Assert.assertEquals("Os dados são iguais", resposta.getMensagem());
	}
	
	@Test
	public void testarComparacaoDiffLadosDiferentesTamanhosDiferentes() {
		
		OutputObject resposta = new OutputObject();
		String id = "123";
		service.gravarDadosDiff(resposta, id, diffLadosDiferentesTamanhoDiferente[DiffData.INDICE_DIFF_LADO_ESQUERDO], diffLadosDiferentesTamanhoDiferente[DiffData.INDICE_DIFF_LADO_DIREITO]);
		resposta = new OutputObject();
		service.compararDados(resposta, id);
		
		Assert.assertEquals("Os dados não possuem o mesmo tamanho", resposta.getMensagem());
	}
	
	@Test
	public void testarComparacaoDiffLadosDiferentesMesmoTamanho() {
		
		OutputObject resposta = new OutputObject();
		String id = "123";
		service.gravarDadosDiff(resposta, id, diffLadosDiferentesMesmoTamanho[DiffData.INDICE_DIFF_LADO_ESQUERDO], diffLadosDiferentesMesmoTamanho[DiffData.INDICE_DIFF_LADO_DIREITO]);
		resposta = new OutputObject();
		service.compararDados(resposta, id);
		
		Assert.assertTrue(resposta.getMensagem().contains("Os dados possuem o mesmo tamanho, mas seus índices são direferentes, índices:"));
	}
	
	@Test
	public void testarGravacaoEComparacaoSemId() {
		
		OutputObject resposta = new OutputObject();
		service.gravarDadosDiff(resposta, null, null, null);
		Assert.assertEquals("Favor informar o id", resposta.getMensagem());
		service.compararDados(resposta, null);
		Assert.assertEquals("Favor informar o id", resposta.getMensagem());
	}
	
	@Test
	public void testarGravacaoSemDados() {
		
		OutputObject resposta = new OutputObject();
		String id = "123";
		
		service.gravarDadosDiff(resposta, id, null, null);
		Assert.assertEquals("Favor informar os dados para comparacao", resposta.getMensagem());
	}
}
