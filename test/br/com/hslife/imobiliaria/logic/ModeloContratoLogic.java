/*** 

    Copyright (c) 2011, 2014 Hércules S. S. José
    

    Este arquivo é parte do programa ImobiliáriaWeb.

    ImobiliáriaWeb é um software livre; você pode redistribui-lo e/ou 

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
    
    
    Para mais informações sobre o programa ImobiliáriaWeb e seus autores acesso o 

    endereço hslife.com.br, pelo e-mail contato@hslife.com.br ou escreva para 

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
import br.com.hslife.imobiliaria.logic.IModeloContrato;
import br.com.hslife.imobiliaria.model.ModeloContrato;

public class ModeloContratoLogic {

	@Test
	public void testSave() {
		ModeloContrato m = new ModeloContrato();
		IModeloContrato logic = LogicFactory.createModeloContratoLogic();
		m.setDescricao("Modelo de teste");
		//m.setArquivo("arquivoteste.jasper");
		//m.setTamanhoArquivo("QWERTYUIOPASDFGHJKLZXCVBNM".length());
		//m.setDados("QWERTYUIOPASDFGHJKLZXCVBNM".getBytes());
		try {
			logic.cadastrar(m);
			System.out.println("Registro salvo com sucesso!");
		} catch (BusinessException be) {
			fail(be.getMessage());
			be.printStackTrace();
		}
	}
	
	@Test
	public void testEdit() {
		ModeloContrato m;
		List<ModeloContrato> lista = new ArrayList<ModeloContrato>();
		IModeloContrato logic = LogicFactory.createModeloContratoLogic();
		try {
			lista = logic.buscarTodos();
			m = lista.get(0);
			m.setDescricao("Teste com modelo");			
			logic.editar(m);
			System.out.println("Registro alterado com sucesso!");
		} catch (BusinessException be) {
			fail(be.getMessage());
			be.printStackTrace();
		}
	}
	
	@Test
	public void testDelete() {
		ModeloContrato m;
		List<ModeloContrato> lista = new ArrayList<ModeloContrato>();
		IModeloContrato logic = LogicFactory.createModeloContratoLogic();
		try {
			lista = logic.buscarTodos();
			m = lista.get(0);			
			logic.habilitar(m.getId());
			System.out.println("Registro habilitado/desabilitado com sucesso!");
		} catch (BusinessException be) {
			fail(be.getMessage());
			be.printStackTrace();
		}
	}

	@Test
	public void testList() {
		ModeloContrato m = new ModeloContrato();
		m.setDescricao("com");
		m.setAtivo(false);
		List<ModeloContrato> lista = new ArrayList<ModeloContrato>();
		IModeloContrato logic = LogicFactory.createModeloContratoLogic();
		try {
			lista = logic.buscar(m);
			System.out.println("Registros carregados com sucesso!");
		} catch (BusinessException be) {
			fail(be.getMessage());
			be.printStackTrace();
		}
		for (ModeloContrato mc :  lista) {
			System.out.println(mc.getDescricao());
		}
	}
}
