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

import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import br.com.hslife.imobiliaria.exception.BusinessException;
import br.com.hslife.imobiliaria.factory.LogicFactory;
import br.com.hslife.imobiliaria.logic.IModeloContrato;
import br.com.hslife.imobiliaria.model.Cliente;
import br.com.hslife.imobiliaria.model.ModeloContrato;

public class ModeloContratoController extends GenericController {
	
	/*** Atributos da classe ***/
	
	// Modelo
	ModeloContrato modeloContrato;
	
	// Lógica de negócio
	IModeloContrato logic;
	
	// Listas
	List<Cliente> listaModeloContrato;
	
	// Armazena o id do objeto de modelo
	Long idModeloContrato;
				
	/*** Construtor ***/	
	
	public ModeloContratoController() {
		this.modeloContrato = new ModeloContrato();
		this.listaModeloContrato = new ArrayList<Cliente>();
		logic = LogicFactory.createModeloContratoLogic();
		
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
		this.listaModeloContrato = new ArrayList<Cliente>();
		dadosModelo = new ListDataModel(listaModeloContrato);
	}

	@Override
	public void simpleSearch() {		
		try {
			modeloContrato.setDescricao(findValue);
			if (findValue == null || findValue.isEmpty()) {
				dadosModelo = new ListDataModel(logic.buscarTodos());
			} else {
				dadosModelo = new ListDataModel(logic.buscar(modeloContrato));
			}							
			viewMessage("Busca realizada com sucesso!");
			modeloContrato.setDescricao("");
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
			if (modeloContrato.getId() != null && modeloContrato.getId() > 0) 
				logic.editar(modeloContrato);
			else
				logic.cadastrar(modeloContrato);
			viewMessage("Registro cadastrado com sucesso!");
			clearVariables();
		} catch (BusinessException be) {
			viewMessage("Erro ao salvar: " + be.getMessage(), "frmModeloContrato");			
		}
		return null;
	}

	@Override
	public String editView() {
		ModeloContrato c = (ModeloContrato) dadosModelo.getRowData();
		try {
			modeloContrato = logic.buscar(c.getId());
		} catch (BusinessException be) {
			viewMessage("Erro ao buscar: " + be.getMessage(), "frmModeloContrato");
		}
		return null;
	} 
	
	@Override
	public String edit() {
		try {
			logic.editar(modeloContrato);
			viewMessage("Registro editado com sucesso!");
			clearVariables();			
		} catch (BusinessException be) {
			viewMessage("Erro ao editar: " + be.getMessage(), "frmModeloContrato");
		}
		return null;
	}
	
	@Override
	public String delete() {		
		try {
			logic.habilitar(idModeloContrato);
			if (modeloContrato.getAtivo()) {
				viewMessage("Registro habilitado com sucesso!");
			} else {
				viewMessage("Registro desabilitado com sucesso!");
			}			
			clearVariables();
		} catch (BusinessException be) {
			viewMessage("Erro ao editar: " + be.getMessage(), "frmModeloContrato");
		}
		return null;
	}
	
	/*** Métodos da classe ***/
	
	public void carregaModelo(UploadEvent event) throws Exception {
		UploadItem item = event.getUploadItem();
		modeloContrato.setTamanhoArquivo(item.getData().length);
		modeloContrato.setArquivo(item.getFileName());
		modeloContrato.setDados(item.getData());
	}
		
	/*** Métodos Getters e Setters ***/
	
	public List<Cliente> getListaCliente() {
		return listaModeloContrato;
	}

	public void setListaCliente(List<Cliente> listaCliente) {
		this.listaModeloContrato = listaCliente;
	}

	public ModeloContrato getModeloContrato() {
		return modeloContrato;
	}

	public void setModeloContrato(ModeloContrato modeloContrato) {
		this.modeloContrato = modeloContrato;
	}

	public List<Cliente> getListaModeloContrato() {
		return listaModeloContrato;
	}

	public void setListaModeloContrato(List<Cliente> listaModeloContrato) {
		this.listaModeloContrato = listaModeloContrato;
	}

	public Long getIdModeloContrato() {
		return idModeloContrato;
	}

	public void setIdModeloContrato(Long idModeloContrato) {
		this.idModeloContrato = idModeloContrato;
	}

}