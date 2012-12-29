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
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.hslife.imobiliaria.factory.DaoFactory;
import br.com.hslife.imobiliaria.factory.LogicFactory;
import br.com.hslife.imobiliaria.logic.IClientePJ;
import br.com.hslife.imobiliaria.model.Cliente;
import br.com.hslife.imobiliaria.model.ClientePJ;
import br.com.hslife.imobiliaria.model.Endereco;
import br.com.hslife.imobiliaria.model.Socio;
import br.com.hslife.imobiliaria.model.Telefone;
import br.com.hslife.imobiliaria.util.CNPJGenerator;

public class ClientePJLogic {

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
		ClientePJ pj = new ClientePJ();
		pj.setCnpj(CNPJGenerator.generateRandom().toString());
		pj.setNomeFantasia("Empresa Fantasia Comércio Ltda");
		pj.setNumPasta("001");
		pj.setRamoAtividade("Comércio");
		
		Endereco e = new Endereco();
		e.setBairro("Bairro de teste");
		e.setCep("11111111");
		e.setCidade("Cidade de teste");
		e.setLogradouro("Logradouro de teste");
		e.setNumero("S/N");
		e.setTipoLogradouro("Rua");
		e.setUf("RJ");
		
		pj.setEndereco(e);
		
		Telefone t = new Telefone();
		t.setDdd("21");
		t.setNumero("12345678");
		t.setTipoTelefone("Residencial");
		
		List<Telefone> listaTelefones = new ArrayList<Telefone>();
		listaTelefones.add(t);
		
		pj.setTelefones(listaTelefones);
		
		Socio s = new Socio();
		List<Cliente> listaClientePF = new ArrayList<Cliente>();
		listaClientePF = DaoFactory.createClienteDao().buscarTodosAtivos();
		s.setClientePF(listaClientePF.get(0));
		s.setFuncao("Gerente");
		List<Socio> socios = new ArrayList<Socio>();
		socios.add(s);
		
		pj.setSocios(socios);
		
		IClientePJ logic = LogicFactory.createClientePJLogic();
		
		try {
			logic.cadastrar(pj);
		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
		System.out.println("Registro gravado com sucesso!");
	}
	
	@Test
	public void testList() {
		IClientePJ logic = LogicFactory.createClientePJLogic();
		List<ClientePJ> lista = new ArrayList<ClientePJ>();
		try {
			lista = logic.buscarTodos();
		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
		for (ClientePJ c : lista) {
			System.out.println(c.getNomeFantasia());
		}
	}

	@Test
	public void testEdit() {
		IClientePJ logic = LogicFactory.createClientePJLogic();
		List<ClientePJ> lista = new ArrayList<ClientePJ>();
		List<Telefone> listaTelefones = new ArrayList<Telefone>();
		try {
			lista = logic.buscarTodos();
		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
		ClientePJ c = lista.get(0);
		c.setNomeFantasia("Companhia de comercio exterior Ltda");
		c.setRamoAtividade("Comercio exterior");
		
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
		IClientePJ logic = LogicFactory.createClientePJLogic();
		List<ClientePJ> lista = new ArrayList<ClientePJ>();
		try {
			lista = logic.buscarTodos();
		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
		ClientePJ c = lista.get(0);
		
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
		IClientePJ logic = LogicFactory.createClientePJLogic();
		List<ClientePJ> lista = new ArrayList<ClientePJ>();
		try {
			lista = logic.buscarTodos();
		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
		ClientePJ c = lista.get(0);
		
		try {
			System.out.println("Nome encontrado: " + logic.buscar(c.getId()).getNomeFantasia());
		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
		System.out.println("Registro encontrado com sucesso");
	}
	
	@Test
	public void testFindByExample() {
		IClientePJ logic = LogicFactory.createClientePJLogic();
		ClientePJ c = new ClientePJ();
		c.setRamoAtividade("Comercio exterior");
		c.setAtivo(false);
		List<ClientePJ> lista = new ArrayList<ClientePJ>();
		try {
			lista = logic.buscar(c);
		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
		for (ClientePJ cl : lista) {
			System.out.println(cl.getNomeFantasia());
		}
		System.out.println("Registros compatíveis encontrados com sucesso");
	}
}
