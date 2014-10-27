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
import br.com.hslife.imobiliaria.facade.ISeguradoraBusiness;
import br.com.hslife.imobiliaria.model.Seguradora;

@Component("seguradoraMB")
@Scope("session")
public class SeguradoraController extends GenericController {
	
	/*** Atributos da classe ***/
	
	// Modelo
	Seguradora seguradora;
	
	// Lógica de negócio
	@Autowired
	//ISeguradora logic;
	ISeguradoraBusiness logic;
	
	// Listas
	List<Seguradora> listaSeguradora;
	
	// Armazena o id do objeto de modelo
	Long idSeguradora;
				
	/*** Construtor ***/	
	
	public SeguradoraController() {
		this.seguradora = new Seguradora();
		this.listaSeguradora = new ArrayList<Seguradora>();
		//logic = LogicFactory.createSeguradoraLogic();
		
		// Define as permissões para este controller
		canAdd = isAuthorized("seguradora", "add");
		canEdit = isAuthorized("seguradora", "edit");
		canDelete = isAuthorized("seguradora", "delete");
		canList = isAuthorized("seguradora", "list");
		canView = isAuthorized("seguradora", "view");
	}
	
	/*** Métodos abstratos de AbstractController ***/

	@Override
	protected void clearVariables() {
		this.seguradora = new Seguradora();		
		this.idSeguradora = null;
		this.listaSeguradora = new ArrayList<Seguradora>();
		dadosModelo = new ListDataModel(listaSeguradora);
	}

	@Override
	public void simpleSearch() {		
		try {			
			seguradora.setDescricao(findValue);
			if (findValue == null || findValue.isEmpty()) {
				dadosModelo = new ListDataModel(logic.buscarTodos());
			} else {
				dadosModelo = new ListDataModel(logic.buscarPorDescricao(findValue));
			}							
			if (dadosModelo.isRowAvailable())
				viewMessage("Busca realizada com sucesso!");
			else
				viewMessage("Nenhum registro encontrado!");
			seguradora.setDescricao("");
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
			if (seguradora.getId() != null && seguradora.getId() > 0) 
				logic.alterar(seguradora);
			else 
				logic.cadastrar(seguradora);
			viewMessage("Registro cadastrado com sucesso!");
			clearVariables();
		} catch (BusinessException be) {
			viewMessage("Erro ao salvar: " + be.getMessage(), "frmSeguradora");			
		}
		return null;
	}

	@Override
	public String editView() {
		Seguradora c = (Seguradora) dadosModelo.getRowData();
		try {
			seguradora = logic.buscarPorID(c.getId());
		} catch (BusinessException be) {
			viewMessage("Erro ao buscar: " + be.getMessage(), "frmSeguradora");
		}
		return null;
	} 
	
	@Override
	public String edit() {
		try {
			logic.alterar(seguradora);
			viewMessage("Registro editado com sucesso!");
			clearVariables();			
		} catch (BusinessException be) {
			viewMessage("Erro ao editar: " + be.getMessage(), "frmSeguradora");
		}
		return null;
	}
	
	/*** Métodos da classe ***/
	
	// Não é necessário
		
	/*** Métodos Getters e Setters ***/

	public Seguradora getSeguradora() {
		return seguradora;
	}

	public void setSeguradora(Seguradora seguradora) {
		this.seguradora = seguradora;
	}

	public List<Seguradora> getListaSeguradora() {
		return listaSeguradora;
	}

	public void setListaSeguradora(List<Seguradora> listaSeguradora) {
		this.listaSeguradora = listaSeguradora;
	}

	public Long getIdSeguradora() {
		return idSeguradora;
	}

	public void setIdSeguradora(Long idSeguradora) {
		this.idSeguradora = idSeguradora;
	}

	public ISeguradoraBusiness getLogic() {
		return logic;
	}

	public void setLogic(ISeguradoraBusiness logic) {
		this.logic = logic;
	}
}