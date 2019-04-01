package com.castgroup.assignment3;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.castgroup.assignment3.model.MensagemRespostaPessoa;
import com.castgroup.assignment3.model.Pessoa;
import com.castgroup.assignment3.repository.PessoaData;
import com.castgroup.assignment3.service.PessoaService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Assignment3ApplicationTests {
	
	private PessoaService pessoaService;

	@Before
	public void inicializarAmbiente() {		
		PessoaData.inicializar();
		pessoaService = new PessoaService();
	}
	
	@Test
	public void testarBuscarTodasPessoasComSucesso() {
		
		Pessoa pessoaCadastrada1 = criarPessoaTeste(1L);
		Pessoa pessoaCadastrada2 = criarPessoaTeste(2L);
		Pessoa pessoaCadastrada3 = criarPessoaTeste(3L);
		
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
		
		Pessoa pessoaCadastrada1 = criarPessoaTeste(1L);
		Pessoa pessoaCadastrada2 = criarPessoaTeste(2L);
		Pessoa pessoaCadastrada3 = criarPessoaTeste(3L);
		
		pessoaService.gravarPessoa(pessoaCadastrada1);
		pessoaService.gravarPessoa(pessoaCadastrada2);
		pessoaService.gravarPessoa(pessoaCadastrada3);
		
		MensagemRespostaPessoa respostaPessoa = pessoaService.buscarPessoaPorId(2L);
		
		Assert.assertNull(respostaPessoa.getErro());
		Assert.assertNotNull(respostaPessoa.getPessoaSelecionada());
		Assert.assertEquals(respostaPessoa.getPessoaSelecionada().getId().longValue(), 2);
		Assert.assertEquals(respostaPessoa.getMensagem(), "Pessoa localizada");		
	}
	
	@Test
	public void testarBuscarPessoaSemId() {
		
		Pessoa pessoaCadastrada1 = criarPessoaTeste(1L);
		Pessoa pessoaCadastrada2 = criarPessoaTeste(2L);
		Pessoa pessoaCadastrada3 = criarPessoaTeste(3L);
		
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
		
		Pessoa pessoaCadastrada1 = criarPessoaTeste(1L);
		Pessoa pessoaCadastrada2 = criarPessoaTeste(2L);
		Pessoa pessoaCadastrada3 = criarPessoaTeste(3L);
		
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
		
		Pessoa pessoaCadastrada1 = criarPessoaTeste(1L);
		MensagemRespostaPessoa respostaPessoa = pessoaService.gravarPessoa(pessoaCadastrada1);
		
		Assert.assertNotNull(respostaPessoa.getPessoaSelecionada());
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
	public void testarAlterarPessoaComSucesso() {
		
		Pessoa pessoaCadastrada1 = criarPessoaTeste(1L);
		Pessoa pessoaCadastrada2 = criarPessoaTeste(2L);
		Pessoa pessoaCadastrada3 = criarPessoaTeste(3L);
		
		pessoaService.gravarPessoa(pessoaCadastrada1);
		pessoaService.gravarPessoa(pessoaCadastrada2);
		pessoaService.gravarPessoa(pessoaCadastrada3);
		
		pessoaCadastrada2.setName("Fulano de tal");
		
		MensagemRespostaPessoa respostaPessoa = pessoaService.gravarPessoa(pessoaCadastrada2);
		
		Assert.assertNotNull(respostaPessoa.getPessoaSelecionada());
		Assert.assertEquals(respostaPessoa.getPessoaSelecionada().getId(), pessoaCadastrada2.getId());
		Assert.assertEquals(respostaPessoa.getPessoaSelecionada().getName(), "Fulano de tal");
		Assert.assertNull(respostaPessoa.getErro());
		Assert.assertEquals(respostaPessoa.getMensagem(), "Pessoa gravada");
		
	}
	
	@Test
	public void testarRemoverPessoaComSucesso() {
		
		Pessoa pessoaCadastrada1 = criarPessoaTeste(1L);
		Pessoa pessoaCadastrada2 = criarPessoaTeste(2L);
		Pessoa pessoaCadastrada3 = criarPessoaTeste(3L);
		
		pessoaService.gravarPessoa(pessoaCadastrada1);
		pessoaService.gravarPessoa(pessoaCadastrada2);
		pessoaService.gravarPessoa(pessoaCadastrada3);
		
		MensagemRespostaPessoa respostaPessoa = pessoaService.removerPessoa(pessoaCadastrada2.getId());
		
		Assert.assertNotNull(respostaPessoa.getPessoaSelecionada());
		Assert.assertEquals(respostaPessoa.getPessoaSelecionada().getId(), pessoaCadastrada2.getId());
		Assert.assertNull(respostaPessoa.getErro());
		Assert.assertEquals(respostaPessoa.getMensagem(), "Pessoa removida");
	}
	
	@Test
	public void testarRemoverPessoaSemId() {
		
		MensagemRespostaPessoa removerPessoa = pessoaService.removerPessoa(null);
	}
	
	private Pessoa criarPessoaTeste(Long id) {
		
		Pessoa pessoaTeste = new Pessoa();
		pessoaTeste.setId(id);
		pessoaTeste.setCellPhone(id.intValue());
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
