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

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.hslife.imobiliaria.exception.BusinessException;
import br.com.hslife.imobiliaria.factory.DaoFactory;
import br.com.hslife.imobiliaria.factory.LogicFactory;
import br.com.hslife.imobiliaria.logic.IImovel;
import br.com.hslife.imobiliaria.model.Cliente;
import br.com.hslife.imobiliaria.model.Endereco;
import br.com.hslife.imobiliaria.model.Imovel;

public class ImovelLogic {

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
		Imovel i = new Imovel();
		i.setMaisInfo("Detalhes adicionais");
		i.setSite(true);
		i.setSituacaoImovel("Disponivel");
		i.setTipoImovel("Casa");
		i.setValor(100000d);
		
		List<Cliente> listaCli = DaoFactory.createClienteDao().list(Cliente.class);
		i.setLocador(listaCli.get(0));
		
		Endereco e = new Endereco();
		e.setBairro("Bairro Imovel");
		e.setCep("11111111");
		e.setCidade("Cidade imóvel");
		e.setLogradouro("Dos imóveis");
		e.setNumero("0101");
		e.setTipoLogradouro("Avenida");
		e.setUf("IM");
		
		i.setEndereco(e);
		
		IImovel logic = LogicFactory.createImovelLogic();
		
		try {
			logic.cadastrar(i);
			System.out.println("Registro salvo com sucesso!");
		} catch (BusinessException be) {
			fail(be.getMessage());
		}
	}
	
	@Test
	public void testEdit() {
		IImovel logic = LogicFactory.createImovelLogic();
		try {
			List<Imovel> lista = logic.buscarTodos();
			List<Cliente> listaCli = LogicFactory.createClienteLogic().buscarTodos();
			Imovel i = lista.get(0);
			Cliente locador = listaCli.get(0);
			
			i.setSituacaoImovel("Indisponivel");
			i.setLocador(locador);
			
			logic.editar(i);
			System.out.println("Registro alterado com sucesso!");
		} catch (BusinessException be) {
			fail(be.getMessage());
		}		
	}
	
	@Test
	public void testEnabling() {
		IImovel logic = LogicFactory.createImovelLogic();
		try {
			List<Imovel> lista = logic.buscarTodos();			
			Imovel i = lista.get(0);			
			logic.habilitar(i.getId());
			System.out.println("Registro habilitado/desabilitado com sucesso!");
		} catch (BusinessException be) {
			fail(be.getMessage());
		}		
	}
	
	@Test
	public void testFindExample() {
		IImovel logic = LogicFactory.createImovelLogic();
		try {
			List<Cliente> listaCli = LogicFactory.createClienteLogic().buscarTodos();
			Cliente locador = listaCli.get(0);
			
			Imovel i = new Imovel();
			i.setSituacaoImovel("Indisponivel");
			i.setLocador(locador);
			
			List<Imovel> lista = logic.buscar(i);
			
			System.out.println("Registro carregados com sucesso!");
			
			for (Imovel im : lista) {
				System.out.println(im.getNumRegistro() + " - " + im.getTipoImovel());
			}
		} catch (BusinessException be) {
			fail(be.getMessage());
		}	
	}
	
	
}
