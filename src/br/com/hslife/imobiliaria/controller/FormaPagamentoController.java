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

package br.com.hslife.imobiliaria.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.ListDataModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.hslife.imobiliaria.exception.BusinessException;
import br.com.hslife.imobiliaria.logic.IFormaPagamento;
import br.com.hslife.imobiliaria.model.FormaPagamento;

@Component("formaPagamentoMB")
@Scope("session")
public class FormaPagamentoController extends GenericController {
	
	/*** Atributos da classe ***/
	
	// Modelo
	FormaPagamento formaPagamento;
	
	// Lógica de negócio
	@Autowired
	IFormaPagamento logic;
	
	// Listas
	List<FormaPagamento> listaFormaPagamento;
	
	// Armazena o id do objeto de modelo
	Long idFormaPagamento;
				
	/*** Construtor ***/	
	
	public FormaPagamentoController() {
		this.formaPagamento = new FormaPagamento();
		this.listaFormaPagamento = new ArrayList<FormaPagamento>();
		//logic = LogicFactory.createFormaPagamentoLogic();
		
		// Define as permissões para este controller
		canAdd = true;//isAuthorized("formaPagamento", "add");
		canEdit = true;//isAuthorized("formaPagamento", "edit");
		canDelete = true;//isAuthorized("formaPagamento", "delete");
		canList = true;//isAuthorized("formaPagamento", "list");
		canView = true;//isAuthorized("formaPagamento", "view");
	}
	
	/*** Métodos abstratos de AbstractController ***/

	@Override
	protected void clearVariables() {
		this.formaPagamento = new FormaPagamento();		
		this.idFormaPagamento = null;
		this.listaFormaPagamento = new ArrayList<FormaPagamento>();
		dadosModelo = new ListDataModel(listaFormaPagamento);
	}

	@Override
	public void simpleSearch() {		
		try {			
			formaPagamento.setDescricao(findValue);
			if (findValue == null || findValue.isEmpty()) {
				dadosModelo = new ListDataModel(logic.buscarTodos());
			} else {
				dadosModelo = new ListDataModel(logic.buscar(formaPagamento));
			}							
			viewMessage("Busca realizada com sucesso!");
			formaPagamento.setDescricao("");
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
			if (formaPagamento.getId() != null && formaPagamento.getId() > 0) 
				logic.editar(formaPagamento);
			else 
				logic.cadastrar(formaPagamento);
			viewMessage("Registro cadastrado com sucesso!");
			clearVariables();
		} catch (BusinessException be) {
			viewMessage("Erro ao salvar: " + be.getMessage(), "frmIndiceReajuste");			
		}
		return null;
	}

	@Override
	public String editView() {
		FormaPagamento c = (FormaPagamento) dadosModelo.getRowData();
		try {
			formaPagamento = logic.buscar(c.getId());
		} catch (BusinessException be) {
			viewMessage("Erro ao buscar: " + be.getMessage(), "frmFormaPagamento");
		}
		return null;
	} 
	
	@Override
	public String edit() {
		try {
			logic.editar(formaPagamento);
			viewMessage("Registro editado com sucesso!");
			clearVariables();			
		} catch (BusinessException be) {
			viewMessage("Erro ao editar: " + be.getMessage(), "frmIndiceReajuste");
		}
		return null;
	}
	
	/*** Métodos da classe ***/
	
	// Não é necessário
		
	/*** Métodos Getters e Setters ***/

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public IFormaPagamento getLogic() {
		return logic;
	}

	public void setLogic(IFormaPagamento logic) {
		this.logic = logic;
	}

	public List<FormaPagamento> getListaFormaPagamento() {
		return listaFormaPagamento;
	}

	public void setListaFormaPagamento(List<FormaPagamento> listaFormaPagamento) {
		this.listaFormaPagamento = listaFormaPagamento;
	}

	public Long getIdFormaPagamento() {
		return idFormaPagamento;
	}

	public void setIdFormaPagamento(Long idFormaPagamento) {
		this.idFormaPagamento = idFormaPagamento;
	}
}