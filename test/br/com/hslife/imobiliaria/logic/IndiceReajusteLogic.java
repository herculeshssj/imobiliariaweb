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

import org.junit.Test;

import br.com.hslife.imobiliaria.exception.BusinessException;
import br.com.hslife.imobiliaria.factory.LogicFactory;
import br.com.hslife.imobiliaria.logic.IIndiceReajuste;
import br.com.hslife.imobiliaria.model.IndiceReajuste;

public class IndiceReajusteLogic {

	@Test
	public void testSave() {
		IndiceReajuste i = new IndiceReajuste();
		IIndiceReajuste logic = LogicFactory.createIndiceReajusteLogic();
		i.setDescricao("Índice de teste");
		i.setPercentual(10d);
		i.setPeriodo("Anual");		
		try {
			logic.cadastrar(i);
			System.out.println("Registro salvo com sucesso!");
		} catch (BusinessException be) {
			fail(be.getMessage());
			be.printStackTrace();
		}
	}
	
	@Test
	public void testEdit() {
		IndiceReajuste i;
		List<IndiceReajuste> lista = new ArrayList<IndiceReajuste>();
		IIndiceReajuste logic = LogicFactory.createIndiceReajusteLogic();
		try {
			lista = logic.buscarTodos();
			i = lista.get(0);
			i.setDescricao("Índice de teste alterado");
			i.setPercentual(20d);			
			logic.editar(i);
			System.out.println("Registro alterado com sucesso!");
		} catch (BusinessException be) {
			fail(be.getMessage());
			be.printStackTrace();
		}
	}
	
	@Test
	public void testDelete() {
		IndiceReajuste i;
		List<IndiceReajuste> lista = new ArrayList<IndiceReajuste>();
		IIndiceReajuste logic = LogicFactory.createIndiceReajusteLogic();
		try {
			lista = logic.buscarTodos();
			i = lista.get(0);			
			logic.habilitar(i.getId());
			System.out.println("Registro habilitado/desabilitado com sucesso!");
		} catch (BusinessException be) {
			fail(be.getMessage());
			be.printStackTrace();
		}
	}

	@Test
	public void testList() {
		IndiceReajuste i = new IndiceReajuste();
		i.setDescricao("alterado");
		List<IndiceReajuste> lista = new ArrayList<IndiceReajuste>();
		IIndiceReajuste logic = LogicFactory.createIndiceReajusteLogic();
		try {
			lista = logic.buscar(i);
			System.out.println("Registros carregados com sucesso!");
		} catch (BusinessException be) {
			fail(be.getMessage());
			be.printStackTrace();
		}
		for (IndiceReajuste in :  lista) {
			System.out.println(in.getDescricao());
		}
	}
}
