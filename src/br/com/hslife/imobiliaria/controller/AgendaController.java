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
import java.util.Date;
import java.util.List;

import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import br.com.hslife.imobiliaria.exception.BusinessException;
import br.com.hslife.imobiliaria.factory.LogicFactory;
import br.com.hslife.imobiliaria.logic.IAgenda;
import br.com.hslife.imobiliaria.model.Agenda;
import br.com.hslife.imobiliaria.model.Corretor;
import br.com.hslife.imobiliaria.model.Imovel;
import br.com.hslife.imobiliaria.util.ViewUtil;

public class AgendaController extends GenericController {

	/*** Atributos da classe ***/

	// Modelo
	Agenda agenda;

	// Lógica de negócio
	IAgenda logic;

	// Listas
	List<Agenda> listaAgenda;

	// Armazena o id do corretor e do imóvel
	Long idCorretor;
	Long idImovel;

	// SelectItems dos combos
	private List<SelectItem> horas;
	private List<SelectItem> imoveis;
	private List<SelectItem> corretores;

	// Objeto que armazenará o objeto de busca
	Date findDate;

	/*** Construtor ***/

	public AgendaController() {
		agenda = new Agenda();
		logic = LogicFactory.createAgendaLogic();
		listaAgenda = new ArrayList<Agenda>();
		componente = "frmAgenda";

		// Define as permissões para este controller
		canAdd = isAuthorized("agenda", "add");
		canEdit = isAuthorized("agenda", "edit");
		canDelete = isAuthorized("agenda", "delete");
		canList = isAuthorized("agenda", "list");
		canView = isAuthorized("agenda", "view");
	}

	/*** Métodos abstratos de AbstractController ***/

	@Override
	protected void clearVariables() {
		agenda = new Agenda();		
		listaAgenda = new ArrayList<Agenda>();
		dadosModelo = new ListDataModel(listaAgenda);
		idImovel = null;
		idCorretor = null;
	}
	
	/*** Métodos concretos sobrescritos ***/

	@Override
	public void simpleSearch() {
		try {
			agenda.setData(findDate);
			if (findDate == null) {
				dadosModelo = new ListDataModel(logic.buscarTodos());
			} else {
				dadosModelo = new ListDataModel(logic.buscar(agenda));
			}
			if (dadosModelo.isRowAvailable())
				viewMessage("Busca realizada com sucesso!");
			else
				viewMessage("Nenhum registro encontrado!");
		} catch (BusinessException be) {
			viewMessage("Erro ao buscar: " + be.getMessage());
		}
	}
	
	@Override
	public String searchView() {
		agenda = new Agenda();
		idCorretor = null;
		idImovel = null;
		carregaCombos();
		return super.searchView();
	}
	
	@Override
	public String search() {
		try {
			if (idImovel != null) {				
				agenda.setImovel(LogicFactory.createImovelLogic().buscar(idImovel));
			}
			if (idCorretor != null) {
				agenda.setCorretor(LogicFactory.createCorretorLogic().buscar(idCorretor));
			}
			dadosModelo = new ListDataModel(logic.buscar(agenda));
			if (dadosModelo.isRowAvailable())
				viewMessage("Busca realizada com sucesso!");
			else
				viewMessage("Nenhum registro encontrado!");
		} catch (BusinessException be) {
			viewMessage("Erro ao buscar: " +  be.getMessage(), "frmCliente");
		}
		return super.search();
	}

	@Override
	public String addView() {
		clearVariables();
		carregaCombos();
		return super.addView();
	}

	@Override
	public String add() {
		String retorno = null;
		try {
			agenda.setImovel(LogicFactory.createImovelLogic().buscar(idImovel));
			agenda.setCorretor(LogicFactory.createCorretorLogic().buscar(idCorretor));
			logic.agendar(agenda);
			viewMessage("Registro cadastrado com sucesso!");
			clearVariables();
			retorno = super.add();
		} catch (BusinessException be) {
			viewMessage("Erro ao salvar: " + be.getMessage(), componente);
		}
		return retorno;
	}

	@Override
	public String editView() {
		String retorno = null;
		Agenda a = (Agenda) dadosModelo.getRowData();
		try {
			agenda = logic.buscar(a.getId());
			idCorretor = agenda.getCorretor().getId();
			idImovel = agenda.getImovel().getId();
			carregaCombos();
			retorno = super.editView();
		} catch (BusinessException be) {
			viewMessage("Erro ao buscar: " + be.getMessage(), componente);
		}
		return retorno;
	}

	@Override
	public String edit() {
		String retorno = null;
		try {
			agenda.setImovel(LogicFactory.createImovelLogic().buscar(idImovel));
			agenda.setCorretor(LogicFactory.createCorretorLogic().buscar(idCorretor));
			logic.remarcar(agenda);
			viewMessage("Registro editado com sucesso!");
			clearVariables();
			retorno = super.edit();
		} catch (BusinessException be) {
			viewMessage("Erro ao editar: " + be.getMessage(), componente);
		}
		return retorno;
	}

	@Override
	public String deleteView() {
		String retorno = null;
		Agenda a = (Agenda) dadosModelo.getRowData();
		try {
			agenda = logic.buscar(a.getId());
			retorno = super.deleteView();
		} catch (BusinessException be) {
			viewMessage("Erro ao buscar: " + be.getMessage(), componente);
		}
		return retorno;
	}

	@Override
	public String delete() {
		String retorno = null;
		try {
			logic.desmarcar(agenda);
			viewMessage("Registro excluído com sucesso!");
			clearVariables();
			retorno = super.delete();
		} catch (BusinessException be) {
			viewMessage("Erro ao editar: " + be.getMessage(), componente);
		}
		return retorno;
	}

	/*** Métodos da classe ***/
	
	public String registerView() {
		String retorno = null;		
		try {			
			// Seta no objeto de modole a agenda encontrada
			Agenda a = (Agenda) dadosModelo.getRowData();			
			agenda = logic.buscar(a.getId());
			
			// Define o retorno
			retorno = "registerView";
		} catch (BusinessException be) {
			viewMessage("Erro ao editar: " + be.getMessage(), componente);
		}
		
		return retorno;
	}
	
	public String registrarVisita() {
		String retorno = null;
		try {
			// Manda o agendamento a ser registrado
			logic.registrarVisita(agenda);
						
			// Define mensagem de sucesso
			viewMessage("Agendamento registrado com sucesso!");
						
			// Define o retorno padrão
			retorno = "register";
						
			// Limpa o objeto de modelo
			clearVariables();
			
		} catch (BusinessException be) {
			viewMessage("Erro ao editar: " + be.getMessage(), componente);
		}
		
		return retorno;
	}

	void carregaCombos() {
		imoveis = new ArrayList<SelectItem>();
		corretores = new ArrayList<SelectItem>();
		horas = new ArrayList<SelectItem>();
		
		for (int i = 0; i < ViewUtil.getSize(ViewUtil.HORAS); i++) {
			horas.add(i, new SelectItem(ViewUtil.getData(i, ViewUtil.HORAS), ViewUtil.getData(i, ViewUtil.HORAS)));
		}
		
		try {
		   	for (Imovel i : LogicFactory.createImovelLogic().buscarTodos()) {
		   		imoveis.add(new SelectItem(i.getId(), i.getNumRegistro() + " - " + i.getTipoImovel(), i.getTipoImovel(), !i.getAtivo()));
		   	}
		   	for (Corretor c : LogicFactory.createCorretorLogic().buscarTodos()) {
		   		corretores.add(new SelectItem(c.getId(), c.getNome(), c.getNome(), !c.isAtivo()));
		   	}
		} catch (BusinessException be) {
			viewMessage("Erro ao carregar: " + be.getMessage(), componente);
		} 
	}
	
	/*** Métodos Getters e Setters ***/

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

	public List<Agenda> getListaAgenda() {
		return listaAgenda;
	}

	public void setListaAgenda(List<Agenda> listaAgenda) {
		this.listaAgenda = listaAgenda;
	}

	public Long getIdCorretor() {
		return idCorretor;
	}

	public void setIdCorretor(Long idCorretor) {
		this.idCorretor = idCorretor;
	}

	public Long getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(Long idImovel) {
		this.idImovel = idImovel;
	}

	public List<SelectItem> getHoras() {
		return horas;
	}

	public void setHoras(List<SelectItem> horas) {
		this.horas = horas;
	}

	public List<SelectItem> getImoveis() {
		return imoveis;
	}

	public void setImoveis(List<SelectItem> imoveis) {
		this.imoveis = imoveis;
	}

	public List<SelectItem> getCorretores() {
		return corretores;
	}

	public void setCorretores(List<SelectItem> corretores) {
		this.corretores = corretores;
	}

	public Date getFindDate() {
		return findDate;
	}

	public void setFindDate(Date findDate) {
		this.findDate = findDate;
	}

}