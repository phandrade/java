package com.castgroup.assignment3;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.castgroup.assignment3.model.MensagemRespostaPessoa;
import com.castgroup.assignment3.model.PessoaDTO;
import com.castgroup.assignment3.repository.PessoaData;
import com.castgroup.assignment3.service.PessoaService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PessoaServiceTests {
	
	@Autowired
	private PessoaService pessoaService;

	@Before
	public void inicializarAmbiente() {		
		PessoaData.inicializar();
	}
	
	@Test
	public void testarBuscarTodasPessoasComSucesso() {
		
		PessoaDTO pessoaCadastrada1 = criarPessoaTeste(1L);
		PessoaDTO pessoaCadastrada2 = criarPessoaTeste(2L);
		PessoaDTO pessoaCadastrada3 = criarPessoaTeste(3L);
		
		pessoaService.gravarPessoa(pessoaCadastrada1);
		pessoaService.gravarPessoa(pessoaCadastrada2);
		pessoaService.gravarPessoa(pessoaCadastrada3);
		
		MensagemRespostaPessoa respostaPessoa = pessoaService.listarTodasPessoas();
		
		Assert.assertNull(respostaPessoa.getErro());
		Assert.assertTrue(respostaPessoa.getListaTodasPessoas() != null && respostaPessoa.getListaTodasPessoas().size() == 3);
		Assert.assertEquals(respostaPessoa.getMensagem(), "Localizado o cadastro de todas as pessoas");
	}
	
	@Test
	public void testarBuscarTodasPessoasComErro() {
		
		PessoaData.anularDados();		
		MensagemRespostaPessoa respostaPessoa = pessoaService.listarTodasPessoas();
		
		Assert.assertNull(respostaPessoa.getListaTodasPessoas());
		Assert.assertEquals(respostaPessoa.getErro(), "Erro ao acessar dados");
		Assert.assertEquals(respostaPessoa.getMensagem(), "Não foi possível acessar a base de dados");
	}
	
	@Test
	public void testarBuscarPessoaPorIdComSucesso() {
		
		PessoaDTO pessoaCadastrada1 = criarPessoaTeste(1L);
		PessoaDTO pessoaCadastrada2 = criarPessoaTeste(2L);
		PessoaDTO pessoaCadastrada3 = criarPessoaTeste(3L);
		
		pessoaService.gravarPessoa(pessoaCadastrada1);
		pessoaService.gravarPessoa(pessoaCadastrada2);
		pessoaService.gravarPessoa(pessoaCadastrada3);
		
		MensagemRespostaPessoa respostaPessoa = pessoaService.buscarPessoaPorId(2L);
		
		Assert.assertNull(respostaPessoa.getErro());
		Assert.assertNotNull(respostaPessoa.getPessoaSelecionada());
		Assert.assertNotNull(respostaPessoa.getListaTodasPessoas());
		Assert.assertEquals(respostaPessoa.getPessoaSelecionada().getId().longValue(), 2);
		Assert.assertEquals(respostaPessoa.getMensagem(), "Pessoa localizada");		
	}
	
	@Test
	public void testarBuscarPessoaSemId() {
		
		PessoaDTO pessoaCadastrada1 = criarPessoaTeste(1L);
		PessoaDTO pessoaCadastrada2 = criarPessoaTeste(2L);
		PessoaDTO pessoaCadastrada3 = criarPessoaTeste(3L);
		
		pessoaService.gravarPessoa(pessoaCadastrada1);
		pessoaService.gravarPessoa(pessoaCadastrada2);
		pessoaService.gravarPessoa(pessoaCadastrada3);
		
		MensagemRespostaPessoa respostaPessoa = pessoaService.buscarPessoaPorId(null);
		
		Assert.assertNull(respostaPessoa.getPessoaSelecionada());
		Assert.assertEquals(respostaPessoa.getErro(), "Dados obrigatórios");
		Assert.assertEquals(respostaPessoa.getMensagem(), "Favor informar o Id da Pessoa");
	}
	
	@Test
	public void testarBuscarPessoaComIdSemCadastro() {
		
		PessoaDTO pessoaCadastrada1 = criarPessoaTeste(1L);
		PessoaDTO pessoaCadastrada2 = criarPessoaTeste(2L);
		PessoaDTO pessoaCadastrada3 = criarPessoaTeste(3L);
		
		pessoaService.gravarPessoa(pessoaCadastrada1);
		pessoaService.gravarPessoa(pessoaCadastrada2);
		pessoaService.gravarPessoa(pessoaCadastrada3);
		
		MensagemRespostaPessoa respostaPessoa = pessoaService.buscarPessoaPorId(4L);
		
		Assert.assertNull(respostaPessoa.getPessoaSelecionada());
		Assert.assertEquals(respostaPessoa.getErro(), "Dados não localizados");
		Assert.assertEquals(respostaPessoa.getMensagem(), "Não foi localizada nenhuma Pessoa com o Id informado");
	}
	
	@Test
	public void testarAdicionarPessoaComSucesso() {
		
		PessoaDTO pessoaCadastrada1 = criarPessoaTeste(1L);
		MensagemRespostaPessoa respostaPessoa = pessoaService.gravarPessoa(pessoaCadastrada1);
		
		Assert.assertNotNull(respostaPessoa.getPessoaSelecionada());
		Assert.assertNotNull(respostaPessoa.getListaTodasPessoas());
		Assert.assertNull(respostaPessoa.getErro());
		Assert.assertEquals(respostaPessoa.getMensagem(), "Pessoa gravada");
		
	}
	
	@Test
	public void testarAdicionarAlterarPessoaSemDados() {
		
		MensagemRespostaPessoa respostaPessoa = pessoaService.gravarPessoa(null);
		
		Assert.assertNull(respostaPessoa.getPessoaSelecionada());
		Assert.assertEquals(respostaPessoa.getErro(), "Dados obrigatórios");
		Assert.assertEquals(respostaPessoa.getMensagem(), "Favor informar os dados da pessoa");
	}
	
	@Test
	public void testarAdicionarAlterarPessoaSemBaseDados() {
		
		PessoaData.anularDados();
		MensagemRespostaPessoa respostaPessoa = pessoaService.gravarPessoa(criarPessoaTeste(1L));
		
		Assert.assertNull(respostaPessoa.getPessoaSelecionada());
		Assert.assertEquals(respostaPessoa.getErro(), "Erro ao gravar dados");
		Assert.assertEquals(respostaPessoa.getMensagem(), "Erro ao tentar gravar as informações da pessoa");
	}
	
	@Test
	public void testarAdicionarAlterarPessoaSemNome() {
		
		PessoaDTO pessoaTeste = new PessoaDTO();
		MensagemRespostaPessoa respostaPessoa = pessoaService.gravarPessoa(pessoaTeste);
		
		Assert.assertNull(respostaPessoa.getPessoaSelecionada());
		Assert.assertEquals(respostaPessoa.getErro(), "Dados obrigatórios");
		Assert.assertEquals(respostaPessoa.getMensagem(), "Favor informar os dados da pessoa");
		
		pessoaTeste.setId(1L);
		
		respostaPessoa = pessoaService.gravarPessoa(pessoaTeste);
		
		Assert.assertNull(respostaPessoa.getPessoaSelecionada());
		Assert.assertEquals(respostaPessoa.getErro(), "Dados obrigatórios");
		Assert.assertEquals(respostaPessoa.getMensagem(), "Favor informar os dados da pessoa");
	}
	
	@Test
	public void testarAlterarPessoaComSucesso() {
		
		PessoaDTO pessoaCadastrada1 = criarPessoaTeste(1L);
		PessoaDTO pessoaCadastrada2 = criarPessoaTeste(2L);
		PessoaDTO pessoaCadastrada3 = criarPessoaTeste(3L);
		
		pessoaService.gravarPessoa(pessoaCadastrada1);
		pessoaService.gravarPessoa(pessoaCadastrada2);
		pessoaService.gravarPessoa(pessoaCadastrada3);
		
		pessoaCadastrada2.setName("Fulano de tal");
		
		MensagemRespostaPessoa respostaPessoa = pessoaService.gravarPessoa(pessoaCadastrada2);
		
		Assert.assertNotNull(respostaPessoa.getPessoaSelecionada());
		Assert.assertNotNull(respostaPessoa.getListaTodasPessoas());
		Assert.assertEquals(respostaPessoa.getPessoaSelecionada().getId(), pessoaCadastrada2.getId());
		Assert.assertEquals(respostaPessoa.getPessoaSelecionada().getName(), "Fulano de tal");
		Assert.assertNull(respostaPessoa.getErro());
		Assert.assertEquals(respostaPessoa.getMensagem(), "Pessoa gravada");
		
	}
	
	@Test
	public void testarRemoverPessoaComSucesso() {
		
		PessoaDTO pessoaCadastrada1 = criarPessoaTeste(1L);
		PessoaDTO pessoaCadastrada2 = criarPessoaTeste(2L);
		PessoaDTO pessoaCadastrada3 = criarPessoaTeste(3L);
		
		pessoaService.gravarPessoa(pessoaCadastrada1);
		pessoaService.gravarPessoa(pessoaCadastrada2);
		pessoaService.gravarPessoa(pessoaCadastrada3);
		
		MensagemRespostaPessoa respostaPessoa = pessoaService.removerPessoa(pessoaCadastrada2.getId());
		
		Assert.assertNotNull(respostaPessoa.getPessoaSelecionada());
		Assert.assertNotNull(respostaPessoa.getListaTodasPessoas());
		Assert.assertEquals(respostaPessoa.getPessoaSelecionada().getId(), pessoaCadastrada2.getId());
		Assert.assertNull(respostaPessoa.getErro());
		Assert.assertEquals(respostaPessoa.getMensagem(), "Pessoa removida");
	}
	
	@Test
	public void testarRemoverPessoaSemId() {
		
		MensagemRespostaPessoa respostaPessoa = pessoaService.removerPessoa(null);
		
		Assert.assertNull(respostaPessoa.getPessoaSelecionada());
		Assert.assertEquals(respostaPessoa.getErro(), "Dados obrigatórios");
		Assert.assertEquals(respostaPessoa.getMensagem(), "Favor informar o id da pessoa");
	}
	
	@Test
	public void testaRemoverPessoaSemBaseDeDados() {
		
		PessoaDTO pessoaCadastrada1 = criarPessoaTeste(1L);
		PessoaDTO pessoaCadastrada2 = criarPessoaTeste(2L);
		PessoaDTO pessoaCadastrada3 = criarPessoaTeste(3L);
		
		pessoaService.gravarPessoa(pessoaCadastrada1);
		pessoaService.gravarPessoa(pessoaCadastrada2);
		pessoaService.gravarPessoa(pessoaCadastrada3);
		
		PessoaData.anularDados();
		
		MensagemRespostaPessoa respostaPessoa = pessoaService.removerPessoa(pessoaCadastrada2.getId());
		
		Assert.assertNull(respostaPessoa.getPessoaSelecionada());
		Assert.assertEquals(respostaPessoa.getErro(), "Erro ao remover dados");
		Assert.assertEquals(respostaPessoa.getMensagem(), "A pessoa não foi localizada ou não foi possível acessar os dados da pessoa");
		
	}
	
	@Test
	public void testarRemoverPessoaNaoCadastrada() {
		
		PessoaDTO pessoaCadastrada1 = criarPessoaTeste(1L);
		PessoaDTO pessoaCadastrada2 = criarPessoaTeste(2L);
		PessoaDTO pessoaCadastrada3 = criarPessoaTeste(3L);
		
		pessoaService.gravarPessoa(pessoaCadastrada1);
		pessoaService.gravarPessoa(pessoaCadastrada2);
		pessoaService.gravarPessoa(pessoaCadastrada3);
		
		MensagemRespostaPessoa respostaPessoa = pessoaService.removerPessoa(4L);
		
		Assert.assertNull(respostaPessoa.getPessoaSelecionada());
		Assert.assertEquals(respostaPessoa.getErro(), "Erro ao remover dados");
		Assert.assertEquals(respostaPessoa.getMensagem(), "A pessoa não foi localizada ou não foi possível acessar os dados da pessoa");
		
		
	}
	
	private PessoaDTO criarPessoaTeste(Long id) {
		
		PessoaDTO pessoaTeste = new PessoaDTO();
		pessoaTeste.setId(id);
		pessoaTeste.setCellphone(id.intValue());
		pessoaTeste.setCity("Cidade " + id);
		pessoaTeste.setName("Nome " + id);
		pessoaTeste.setNeighborhood("Bairro " + id);
		pessoaTeste.setNumber(id.intValue());
		pessoaTeste.setPhone(id.intValue());
		pessoaTeste.setState("Estado " + id);
		pessoaTeste.setStreet("Rua " + id);
		
		return pessoaTeste;
	}

}
