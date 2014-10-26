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

import javax.faces.model.ListDataModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.hslife.imobiliaria.exception.BusinessException;
import br.com.hslife.imobiliaria.facade.IModeloContratoBusiness;
import br.com.hslife.imobiliaria.model.ModeloContrato;

@Component("modeloContratoMB")
@Scope("session")
public class ModeloContratoController extends GenericController {
	
	/*** Atributos da classe ***/
	
	// Modelo
	ModeloContrato modeloContrato;
	
	// Lógica de negócio
	@Autowired
	IModeloContratoBusiness logic;
	
	// Armazena o id do objeto de modelo
	Long idModeloContrato;
				
	/*** Construtor ***/	
	
	public ModeloContratoController() {
		this.modeloContrato = new ModeloContrato();
	
		// Define as permissões para este controller
		canAdd = isAuthorized("modeloContrato", "add");
		canEdit = isAuthorized("modeloContrato", "edit");
		canDelete = isAuthorized("modeloContrato", "delete");
		canList = isAuthorized("modeloContrato", "list");
		canView = isAuthorized("modeloContrato", "view");
	}
	
	/*** Métodos abstratos de AbstractController ***/

	@Override
	protected void clearVariables() {
		this.modeloContrato = new ModeloContrato();		
		this.idModeloContrato = null;
		dadosModelo = new ListDataModel(new ArrayList<ModeloContrato>());
	}

	@Override
	public void simpleSearch() {		
		try {
			if (findValue != null) {
				dadosModelo = new ListDataModel(logic.buscarPorDescricao(findValue));
			} else {
				dadosModelo = new ListDataModel(logic.buscarPorDescricao(""));
			}
			
			if (dadosModelo.isRowAvailable())
				viewMessage("Busca realizada com sucesso!");
			else
				viewMessage("Nenhum registro encontrado!");
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
			if (modeloContrato.getModelo() == null || modeloContrato.getModelo().isEmpty()) {
				viewMessage("Insira o modelo do contrato!");
				return null;
			}
			if (modeloContrato.getId() != null && modeloContrato.getId() > 0) 
				logic.alterar(modeloContrato);
			else 
				logic.cadastrar(modeloContrato);
			viewMessage("Registro salvo com sucesso!");
		} catch (BusinessException be) {
			viewMessage("Erro ao salvar: " + be.getMessage(), "frmModeloContrato");			
		}
		return null;
	}

	@Override
	public String editView() {
		ModeloContrato c = (ModeloContrato) dadosModelo.getRowData();
		try {
			modeloContrato = logic.buscarPorID(c.getId());
		} catch (BusinessException be) {
			viewMessage("Erro ao buscar: " + be.getMessage(), "frmModeloContrato");
		}
		return null;
	} 
	
	@Override
	public String delete() {		
		try {
			ModeloContrato c = (ModeloContrato) dadosModelo.getRowData();
			ModeloContrato modelo = logic.buscarPorID(c.getId());
			logic.excluir(modelo);
			viewMessage("Registro excluído com sucesso!");
			clearVariables();				
		} catch (BusinessException be) {
			viewMessage("Erro ao excluir: " + be.getMessage(), "frmModeloContrato");
		}
		return null;
	}
	
	/*** Métodos da classe ***/
	
	public void habilitar() {		
		try {
			ModeloContrato modelo = logic.buscarPorID(idModeloContrato);
			logic.habilitar(modelo);
			if (modelo.isAtivo()) {
				viewMessage("Registro desabilitado com sucesso!");
			} else {
				viewMessage("Registro habilitado com sucesso!");
			}			
			clearVariables();
		} catch (BusinessException be) {
			viewMessage("Erro ao habilitar: " + be.getMessage(), "frmModeloContrato");
		}
	}
	
	public String visualizar() {
		return null;
	}

	/*** Métodos Getters e Setters ***/
	
	public ModeloContrato getModeloContrato() {
		return modeloContrato;
	}

	public void setModeloContrato(ModeloContrato modeloContrato) {
		this.modeloContrato = modeloContrato;
	}

	public Long getIdModeloContrato() {
		return idModeloContrato;
	}

	public void setIdModeloContrato(Long idModeloContrato) {
		this.idModeloContrato = idModeloContrato;
	}
}