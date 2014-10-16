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

package br.com.hslife.imobiliaria.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.ListDataModel;

import br.com.hslife.imobiliaria.exception.BusinessException;
import br.com.hslife.imobiliaria.factory.LogicFactory;
import br.com.hslife.imobiliaria.logic.IIndiceReajuste;
import br.com.hslife.imobiliaria.model.Cliente;
import br.com.hslife.imobiliaria.model.IndiceReajuste;

public class IndiceReajusteController extends GenericController {
	
	/*** Atributos da classe ***/
	
	// Modelo
	IndiceReajuste indiceReajuste;
	
	// Lógica de negócio
	IIndiceReajuste logic;
	
	// Listas
	List<Cliente> listaIndiceReajuste;
	
	// Armazena o id do objeto de modelo
	Long idIndiceReajuste;
				
	/*** Construtor ***/	
	
	public IndiceReajusteController() {
		this.indiceReajuste = new IndiceReajuste();
		this.listaIndiceReajuste = new ArrayList<Cliente>();
		logic = LogicFactory.createIndiceReajusteLogic();
		
		// Define as permissões para este controller
		canAdd = isAuthorized("indiceReajuste", "add");
		canEdit = isAuthorized("indiceReajuste", "edit");
		canDelete = isAuthorized("indiceReajuste", "delete");
		canList = isAuthorized("indiceReajuste", "list");
		canView = isAuthorized("indiceReajuste", "view");
	}
	
	/*** Métodos abstratos de AbstractController ***/

	@Override
	protected void clearVariables() {
		this.indiceReajuste = new IndiceReajuste();		
		this.idIndiceReajuste = null;
		this.listaIndiceReajuste = new ArrayList<Cliente>();
		dadosModelo = new ListDataModel(listaIndiceReajuste);
	}

	@Override
	public void simpleSearch() {		
		try {
			indiceReajuste.setDescricao(findValue);
			if (findValue == null || findValue.isEmpty()) {
				dadosModelo = new ListDataModel(logic.buscarTodos());
			} else {
				dadosModelo = new ListDataModel(logic.buscar(indiceReajuste));
			}							
			if (dadosModelo.isRowAvailable())
				viewMessage("Busca realizada com sucesso!");
			else
				viewMessage("Nenhum registro encontrado!");
			indiceReajuste.setDescricao("");
		} catch (BusinessException be) {
			viewMessage("Erro ao buscar: " +  be.getMessage());
		}
	}

	/*** Métodos concretos sobrescritos ***/
	
	@Override
	public String addView() {
		clearVariables();
		return null;
	}
	
	@Override
	public String add() {		
		try {
			if (indiceReajuste.getId() != null && indiceReajuste.getId() > 0) 
				logic.editar(indiceReajuste);
			else 
				logic.cadastrar(indiceReajuste);
			viewMessage("Registro cadastrado com sucesso!");
			clearVariables();
		} catch (BusinessException be) {
			viewMessage("Erro ao salvar: " + be.getMessage(), "frmIndiceReajuste");			
		}
		return null;
	}

	@Override
	public String editView() {
		IndiceReajuste c = (IndiceReajuste) dadosModelo.getRowData();
		try {
			indiceReajuste = logic.buscar(c.getId());
		} catch (BusinessException be) {
			viewMessage("Erro ao buscar: " + be.getMessage(), "frmIndiceReajuste");
		}
		return null;
	} 
	
	@Override
	public String edit() {
		try {
			logic.editar(indiceReajuste);
			viewMessage("Registro editado com sucesso!");
			clearVariables();			
		} catch (BusinessException be) {
			viewMessage("Erro ao editar: " + be.getMessage(), "frmIndiceReajuste");
		}
		return null;
	}
	
	@Override
	public String delete() {		
		try {
			logic.habilitar(idIndiceReajuste);
			if (indiceReajuste.getAtivo()) {
				viewMessage("Registro habilitado com sucesso!");
			} else {
				viewMessage("Registro desabilitado com sucesso!");
			}			
			clearVariables();
		} catch (BusinessException be) {
			viewMessage("Erro ao editar: " + be.getMessage(), "frmIndiceReajuste");
		}
		return null;
	}
	
	/*** Métodos da classe ***/
	
	// Não é necessário
		
	/*** Métodos Getters e Setters ***/
	
	public List<Cliente> getListaCliente() {
		return listaIndiceReajuste;
	}

	public void setListaCliente(List<Cliente> listaCliente) {
		this.listaIndiceReajuste = listaCliente;
	}

	public IndiceReajuste getIndiceReajuste() {
		return indiceReajuste;
	}

	public void setIndiceReajuste(IndiceReajuste indiceReajuste) {
		this.indiceReajuste = indiceReajuste;
	}

	public List<Cliente> getListaIndiceReajuste() {
		return listaIndiceReajuste;
	}

	public void setListaIndiceReajuste(List<Cliente> listaIndiceReajuste) {
		this.listaIndiceReajuste = listaIndiceReajuste;
	}

	public Long getIdIndiceReajuste() {
		return idIndiceReajuste;
	}

	public void setIdIndiceReajuste(Long idIndiceReajuste) {
		this.idIndiceReajuste = idIndiceReajuste;
	}

}