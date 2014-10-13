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
import br.com.hslife.imobiliaria.facade.IServicoManutencaoBusiness;
import br.com.hslife.imobiliaria.model.ServicoManutencao;

@Component("servicoManutencaoMB")
@Scope("session")
public class ServicoManutencaoController extends GenericController {
	
	/*** Atributos da classe ***/
	
	// Modelo
	ServicoManutencao servicoManutencao;
	
	// Lógica de negócio
	@Autowired
	//IServicoManutencao logic;
	IServicoManutencaoBusiness logic;
	
	// Listas
	List<ServicoManutencao> listaServicoManutencao;
	
	// Armazena o id do objeto de modelo
	Long idServicoManutencao;
				
	/*** Construtor ***/	
	
	public ServicoManutencaoController() {
		this.servicoManutencao = new ServicoManutencao();
		this.listaServicoManutencao = new ArrayList<ServicoManutencao>();
		//logic = LogicFactory.createServicoManutencaoLogic();
		
		// Define as permissões para este controller
		canAdd = isAuthorized("servicomanutencao", "add");
		canEdit = isAuthorized("servicomanutencao", "edit");
		canDelete = isAuthorized("servicomanutencao", "delete");
		canList = isAuthorized("servicomanutencao", "list");
		canView = isAuthorized("servicomanutencao", "view");
	}
	
	/*** Métodos abstratos de AbstractController ***/

	@Override
	protected void clearVariables() {
		this.servicoManutencao = new ServicoManutencao();		
		this.idServicoManutencao = null;
		this.listaServicoManutencao = new ArrayList<ServicoManutencao>();
		dadosModelo = new ListDataModel(listaServicoManutencao);
	}

	@Override
	public void simpleSearch() {		
		try {			
			servicoManutencao.setDescricao(findValue);
			if (findValue == null || findValue.isEmpty()) {
				dadosModelo = new ListDataModel(logic.buscarTodos());
			} else {
				dadosModelo = new ListDataModel(logic.buscarPorDescricao(findValue));
			}							
			if (dadosModelo.isRowAvailable())
				viewMessage("Busca realizada com sucesso!");
			else
				viewMessage("Nenhum registro encontrado!");
			servicoManutencao.setDescricao("");
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
			if (servicoManutencao.getId() != null && servicoManutencao.getId() > 0) 
				logic.alterar(servicoManutencao);
			else 
				logic.cadastrar(servicoManutencao);
			viewMessage("Registro cadastrado com sucesso!");
			clearVariables();
		} catch (BusinessException be) {
			viewMessage("Erro ao salvar: " + be.getMessage(), "frmServicoManutencao");			
		}
		return null;
	}

	@Override
	public String editView() {
		ServicoManutencao c = (ServicoManutencao) dadosModelo.getRowData();
		try {
			servicoManutencao = logic.buscarPorID(c.getId());
		} catch (BusinessException be) {
			viewMessage("Erro ao buscar: " + be.getMessage(), "frmServicoManutencao");
		}
		return null;
	} 
	
	@Override
	public String edit() {
		try {
			logic.alterar(servicoManutencao);
			viewMessage("Registro editado com sucesso!");
			clearVariables();			
		} catch (BusinessException be) {
			viewMessage("Erro ao editar: " + be.getMessage(), "frmServicoManutencao");
		}
		return null;
	}
	
	/*** Métodos da classe ***/
	
	// Não é necessário
		
	/*** Métodos Getters e Setters ***/

	public ServicoManutencao getServicoManutencao() {
		return servicoManutencao;
	}

	public void setServicoManutencao(ServicoManutencao servicoManutencao) {
		this.servicoManutencao = servicoManutencao;
	}

	public List<ServicoManutencao> getListaServicoManutencao() {
		return listaServicoManutencao;
	}

	public void setListaServicoManutencao(List<ServicoManutencao> listaServicoManutencao) {
		this.listaServicoManutencao = listaServicoManutencao;
	}

	public Long getIdServicoManutencao() {
		return idServicoManutencao;
	}

	public void setIdServicoManutencao(Long idServicoManutencao) {
		this.idServicoManutencao = idServicoManutencao;
	}

	public IServicoManutencaoBusiness getLogic() {
		return logic;
	}

	public void setLogic(IServicoManutencaoBusiness logic) {
		this.logic = logic;
	}
}