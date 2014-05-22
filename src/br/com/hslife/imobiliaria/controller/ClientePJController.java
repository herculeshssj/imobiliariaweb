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
import javax.faces.model.SelectItem;

import br.com.hslife.imobiliaria.exception.BusinessException;
import br.com.hslife.imobiliaria.exception.ServiceException;
import br.com.hslife.imobiliaria.factory.LogicFactory;
import br.com.hslife.imobiliaria.logic.IClientePJ;
import br.com.hslife.imobiliaria.model.Cliente;
import br.com.hslife.imobiliaria.model.ClientePJ;
import br.com.hslife.imobiliaria.model.Contrato;
import br.com.hslife.imobiliaria.model.Endereco;
import br.com.hslife.imobiliaria.model.Socio;
import br.com.hslife.imobiliaria.model.Telefone;
import br.com.hslife.imobiliaria.service.CEPService;

public class ClientePJController extends GenericController {
	
	/*** Atributos da classe ***/
	
	// Modelo
	ClientePJ clientePJ;
	Telefone telefone;
	Socio socio;
	
	// Lógica de negócio
	IClientePJ logic;
	CEPService service;
	
	// Listas
	List<ClientePJ> listaClientePJ;
	List<Telefone> listaTelefone;
	List<Socio> listaSocio = new ArrayList<Socio>();
	
	// Armazena o id do objeto de modelo
	Long idClientePJ;
	Long idSocio;
	
	// SelectItems dos combos
	private List<SelectItem> socios;
	
	// Seleção dos telefones para exclusão
	String numTelefone;
	String tipoTelefone;
	String dddTelefone;
	
	/*** Construtor ***/	
	
	public ClientePJController() {
		clientePJ = new ClientePJ();
		telefone = new Telefone();
		socio = new Socio();
		listaClientePJ = new ArrayList<ClientePJ>();
		listaTelefone = new ArrayList<Telefone>();
		logic = LogicFactory.createClientePJLogic();
		service = new CEPService();
		
		// Define as permissões para este controller
		canAdd = isAuthorized("clientepj", "add");
		canEdit = isAuthorized("clientepj", "edit");
		canDelete = isAuthorized("clientepj", "delete");
		canList = isAuthorized("clientepj", "list");
		canView = isAuthorized("clientepj", "view");
	}
	
	/*** Métodos abstratos de AbstractController ***/

	@Override
	protected void clearVariables() {
		clientePJ = new ClientePJ();
		telefone = new Telefone();
		socio = new Socio();
		idSocio = null;
		idClientePJ = null;
		listaClientePJ = new ArrayList<ClientePJ>();
		listaTelefone = new ArrayList<Telefone>();
		listaSocio = new ArrayList<Socio>();
		dadosModelo = new ListDataModel(listaClientePJ);
	}

	@Override
	public void simpleSearch() {		
		try {
			if (findValue == null || findValue.isEmpty()) {
				dadosModelo = new ListDataModel(logic.buscarTodosAtivos());
			} else {
				dadosModelo = new ListDataModel(logic.buscarPorCNPJ(findValue));
			}							
			viewMessage("Busca realizada com sucesso!");
		} catch (BusinessException be) {
			viewMessage("Erro ao buscar: " +  be.getMessage());
		}
	}

	/*** Métodos concretos sobrescritos ***/
	
	@Override
	public String searchView() {
		clientePJ = new ClientePJ();
		return super.searchView();
	}
	
	@Override
	public String search() {
		try {
			dadosModelo = new ListDataModel(logic.buscar(clientePJ));
			viewMessage("Busca realizada com sucesso!");
		} catch (BusinessException be) {
			viewMessage("Erro ao buscar: " +  be.getMessage(), "frmClientePJ");
		}
		return super.search();
	}
	
	@Override
	public String cancelAction() {
		clearVariables();
		return super.cancelAction();
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
		clientePJ.getTelefones().addAll(listaTelefone);
		clientePJ.getSocios().addAll(listaSocio);
		try {
			logic.cadastrar(clientePJ);
			viewMessage("Registro cadastrado com sucesso!");
			clearVariables();
			retorno = super.add();
		} catch (BusinessException be) {
			viewMessage("Erro ao salvar: " + be.getMessage(), "frmClientePJ");
			
		}
		return retorno;
	}

	@Override
	public String editView() {
		ClientePJ c = (ClientePJ) dadosModelo.getRowData();
		try {
			clientePJ = logic.buscar(c.getId());
			listaTelefone.addAll(clientePJ.getTelefones());
			listaSocio.addAll(clientePJ.getSocios());
			carregaCombos();
			clientePJ.setContratos(LogicFactory.createContratoLogic().buscar(new Contrato(clientePJ)));
		} catch (BusinessException be) {
			viewMessage("Erro ao buscar: " + be.getMessage());
		}
		return super.editView();
	} 
	
	@Override
	public String edit() {
		String retorno = null;		
		clientePJ.getTelefones().addAll(listaTelefone);
		clientePJ.getSocios().addAll(listaSocio);
		try {
			logic.editar(clientePJ);
			viewMessage("Registro editado com sucesso!");
			clearVariables();
			retorno = super.edit();
		} catch (BusinessException be) {
			viewMessage("Erro ao editar: " + be.getMessage(), "frmClientePJ");			
		}
		return retorno;
	}
	
	@Override
	public String deleteView() {
		ClientePJ c = (ClientePJ) dadosModelo.getRowData();
		try {
			clientePJ = logic.buscar(c.getId());
		} catch (BusinessException be) {
			viewMessage("Erro ao buscar: " + be.getMessage());
		}
		return super.deleteView();
	}
	
	@Override
	public String delete() {		
		try {
			logic.habilitar(clientePJ.getId());
			if (clientePJ.getAtivo()) {
				viewMessage("Registro habilitado com sucesso!");
			} else {
				viewMessage("Registro desabilitado com sucesso!");
			}			
			clearVariables();
		} catch (BusinessException be) {
			viewMessage("Erro ao editar: " + be.getMessage(), "frmClientePJ");
		}
		return super.delete();
	}
	
	/*** Métodos da classe ***/
	
	private void carregaCombos() {
		socios = new ArrayList<SelectItem>();
		try {
			for (Cliente pf : LogicFactory.createClienteLogic().buscarSociosAtivos()) {
				socios.add(new SelectItem(pf.getId(), pf.getCpf() + " - " + pf.getNome()));
			}
		} catch (BusinessException be) {
			viewMessage("Erro ao carregar: " + be.getMessage());
		}
		
	}
	
	public void adicionarTelefone() {
		
		// Itera a lista de telefones a procura do telefone informado
		int count = 0;
		for (Telefone t : listaTelefone) {
			if (t.getNumero().equals(telefone.getNumero()) && t.getTipoTelefone().equals(telefone.getTipoTelefone()) && t.getDdd().equals(telefone.getDdd())) {
				count++;				
			}
		}
		
		// Verifica se encontrou alguma ocorrência do telefone na lista. Se encontrou retorna uma mensagem de aviso
		// Caso contrário, adiciona o telefone na lista
		if (count == 0) {
			listaTelefone.add(telefone);
		} else {
			viewMessage("O telefone informado já foi adicionado!", "msgTelefone");
		}
		
		// Limpa o objeto
		telefone = new Telefone();
	}
	
	public void removerTelefone() {
		for (Telefone t : listaTelefone) {
			if (t.getNumero().equals(numTelefone) && t.getTipoTelefone().equals(tipoTelefone) && t.getDdd().equals(dddTelefone)) {
				listaTelefone.remove(t);
				break;
			}
		}
		
		// Limpa o objeto
		telefone = new Telefone();
	}
	
	public void adicionarSocio() {
		
		// Itera a lista de sócios a procura do sócio informado
		int count = 0;
		for (int i = 0; i < listaSocio.size(); i++) {
			if (listaSocio.get(i).getClientePF().getId().equals(idSocio)) {
				count++;
			}
		}		
				
		// Verifica se encontrou alguma ocorrência do sócio na lista. Se encontrou retorna uma mensagem de aviso
		// Caso contrário, adiciona o sócio na lista
		if (count == 0) {
			try {
				socio.setClientePF(LogicFactory.createClienteLogic().buscar(idSocio));
				listaSocio.add(socio);
			} catch (BusinessException be) {
				viewMessage("Erro ao adicionar: ", be.getMessage());
			}
			
		} else {
			viewMessage("O sócio informado já foi adicionado!", "frmClientePJ");
		}
				
		// Limpa os objetos
		socio = new Socio();
		idSocio = null;
	}
	
	public void removerSocio() {
		// Procura o sócio na lista para remover
		for (Socio s : listaSocio) {
			if (s.getClientePF().getId() == idSocio) {
				listaSocio.remove(s);
				break;
			}
		}
		
		// Limpa o objeto
		socio = new Socio();
	}

	public void buscarEndereco() {
		try {
			Endereco e = new Endereco();
			e = service.getEndereco(clientePJ.getEndereco().getCep());
			clientePJ.setEndereco(e);
		} catch (ServiceException se) {
			viewMessage(se.getMessage(), "txtCep");
		}
	}
	
	/*** Métodos Getters e Setters ***/
	
	public ClientePJ getClientePJ() {
		return clientePJ;
	}

	public void setClientePJ(ClientePJ clientePJ) {
		this.clientePJ = clientePJ;
	}

	public List<ClientePJ> getListaClientePJ() {
		return listaClientePJ;
	}

	public void setListaClientePJ(List<ClientePJ> listaClientePJ) {
		this.listaClientePJ = listaClientePJ;
	}

	public Long getIdClientePJ() {
		return idClientePJ;
	}

	public void setIdClientePJ(Long idClientePJ) {
		this.idClientePJ = idClientePJ;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public List<Telefone> getListaTelefone() {
		return listaTelefone;
	}

	public void setListaTelefone(List<Telefone> listaTelefone) {
		this.listaTelefone = listaTelefone;
	}

	public String getNumTelefone() {
		return numTelefone;
	}

	public void setNumTelefone(String numTelefone) {
		this.numTelefone = numTelefone;
	}

	public String getTipoTelefone() {
		return tipoTelefone;
	}

	public void setTipoTelefone(String tipoTelefone) {
		this.tipoTelefone = tipoTelefone;
	}

	public String getDddTelefone() {
		return dddTelefone;
	}

	public void setDddTelefone(String dddTelefone) {
		this.dddTelefone = dddTelefone;
	}

	public Socio getSocio() {
		return socio;
	}

	public void setSocio(Socio socio) {
		this.socio = socio;
	}

	public Long getIdSocio() {
		return idSocio;
	}

	public void setIdSocio(Long idSocio) {
		this.idSocio = idSocio;
	}

	public List<SelectItem> getSocios() {
		return socios;
	}

	public void setSocios(List<SelectItem> socios) {
		this.socios = socios;
	}

	public List<Socio> getListaSocio() {
		return listaSocio;
	}

	public void setListaSocio(List<Socio> listaSocio) {
		this.listaSocio = listaSocio;
	}
}