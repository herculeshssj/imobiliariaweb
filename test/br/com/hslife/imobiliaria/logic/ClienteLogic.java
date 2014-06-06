/*** 

    Copyright (c) 2011 Hércules S. S. José
    

    Este arquivo é parte do programa Imobiliária Web.

    Imobiliária Web é um software livre; você pode redistribui-lo e/ou 

    modificá-lo dentro dos termos da Licença Pública Geral Menor GNU como 

    publicada pela Fundação do Software Livre (FSF); na versão 2.1 da 

    Licença.
    
    
    Este programa é distribuído na esperança que possa ser util, 

    mas SEM NENHUMA GARANTIA; sem uma garantia implicita de ADEQUAÇÂO a qualquer

    MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral Menor GNU em 
    
    português para maiores detalhes.
    
    
    Você deve ter recebido uma cópia da Licença Pública Geral Menor GNU sob o 

    nome de "LICENSE.TXT" junto com este programa, se não, acesse o site HSlife no 

    endereco www.hslife.com.br ou escreva para a Fundação do Software Livre(FSF) Inc., 

    51 Franklin St, Fifth Floor, Boston, MA  02110-1301, USA.
    
    
    Para mais informações sobre o programa Imobiliária Web e seus autores acesso o 

    endereço www.hslife.com.br, pelo e-mail contato@hslife.com.br ou escreva para 

    Hércules S. S. José, Av. Ministro Lafaeyte de Andrade, 1683 - Bl. 3 Apt 404, 

    Marco II - Nova Iguaçu, RJ, Brasil.

 ***/

package br.com.hslife.imobiliaria.logic;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.hslife.imobiliaria.factory.LogicFactory;
import br.com.hslife.imobiliaria.logic.ICliente;
import br.com.hslife.imobiliaria.model.Cliente;
import br.com.hslife.imobiliaria.model.Endereco;
import br.com.hslife.imobiliaria.model.Telefone;
import br.com.hslife.imobiliaria.util.CPFGenerator;

public class ClienteLogic {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSave() {
		Cliente c = new Cliente();
		c.setNome("Nome de teste");
		c.setCpf(CPFGenerator.generateRandom().toString());
		c.setGenero("M");
		c.setDataNascimento(new Date());
		c.setTipoCliente("cliente");
		c.setNumPasta("000");
		
		Endereco e = new Endereco();
		e.setBairro("Bairro de teste");
		e.setCep("11111111");
		e.setCidade("Cidade de teste");
		e.setLogradouro("Logradouro de teste");
		e.setNumero("S/N");
		e.setTipoLogradouro("Rua");
		e.setUf("RJ");
		
		c.setEndereco(e);
		
		Telefone t = new Telefone();
		t.setDdd("21");
		t.setNumero("12345678");
		t.setTipoTelefone("Residencial");
		
		List<Telefone> listaTelefones = new ArrayList<Telefone>();
		listaTelefones.add(t);
		
		c.setTelefones(listaTelefones);
		
		ICliente logic = LogicFactory.createClienteLogic();
		
		try {
			logic.cadastrar(c);
		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
		System.out.println("Registro gravado com sucesso!");
	}
	
	@Test
	public void testList() {
		ICliente logic = LogicFactory.createClienteLogic();
		List<Cliente> lista = new ArrayList<Cliente>();
		try {
			lista = logic.buscarTodos();
		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
		for (Cliente c : lista) {
			System.out.println(c.getNome());
		}
	}

	@Test
	public void testEdit() {
		ICliente logic = LogicFactory.createClienteLogic();
		List<Cliente> lista = new ArrayList<Cliente>();
		List<Telefone> listaTelefones = new ArrayList<Telefone>();
		try {
			lista = logic.buscarTodos();
		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
		Cliente c = lista.get(0);
		c.setNome("Nome de teste alterado");
		
		listaTelefones = c.getTelefones();
		
		Telefone t = new Telefone();
		t.setDdd("21");
		t.setNumero("98765432");
		t.setTipoTelefone("Celular");		
		
		listaTelefones.add(t);
		
		c.setTelefones(listaTelefones);
		
		try {
			logic.editar(c);
		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
		System.out.println("Registro alterado com sucesso");
	}
	
	@Test
	public void testDelete() {
		ICliente logic = LogicFactory.createClienteLogic();
		List<Cliente> lista = new ArrayList<Cliente>();
		try {
			lista = logic.buscarTodos();
		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
		Cliente c = lista.get(0);
		
		try {
			logic.habilitar(c.getId());
		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
		System.out.println("Registro habilitado/desabilitado com sucesso");
	}
	
	@Test
	public void testFind() {
		ICliente logic = LogicFactory.createClienteLogic();
		List<Cliente> lista = new ArrayList<Cliente>();
		try {
			lista = logic.buscarTodos();
		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
		Cliente c = lista.get(0);
		
		try {
			System.out.println("Nome encontrado: " + logic.buscar(c.getId()).getNome());
		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
		System.out.println("Registro encontrado com sucesso");
	}
	
	@Test
	public void testFindByExample() {
		ICliente logic = LogicFactory.createClienteLogic();
		Cliente c = new Cliente();
		c.setNome("Nome de teste alterado");
		c.setAtivo(false);
		List<Cliente> lista = new ArrayList<Cliente>();
		try {
			lista = logic.buscar(c);
		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
		for (Cliente cl : lista) {
			System.out.println(cl.getNome());
		}
		System.out.println("Registros compatíveis encontrados com sucesso");
	}
}
