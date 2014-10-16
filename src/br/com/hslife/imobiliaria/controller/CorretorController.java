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
import br.com.hslife.imobiliaria.exception.ServiceException;
import br.com.hslife.imobiliaria.factory.LogicFactory;
import br.com.hslife.imobiliaria.logic.ICorretor;
import br.com.hslife.imobiliaria.model.Corretor;
import br.com.hslife.imobiliaria.model.Endereco;
import br.com.hslife.imobiliaria.model.Imovel;
import br.com.hslife.imobiliaria.model.Telefone;
import br.com.hslife.imobiliaria.service.CEPService;

public class CorretorController extends GenericController {
	
	/*** Atributos da classe ***/
	
	// Modelo
	Corretor corretor;
	Telefone telefone;
	
	// Lógica de negócio
	ICorretor logic;
	CEPService service;
	
	// Listas
	List<Corretor> listaCorretor;
	List<Telefone> listaTelefone;
	
	// Armazena o id do objeto de modelo
	Long idCorretor;
	
	// SelectItems dos combos
	//private List<SelectItem> horas;
	
	// Seleção dos telefones para exclusão
	String numTelefone;
	String tipoTelefone;
	String dddTelefone;
	
	/*** Construtor ***/	
	
	public CorretorController() {
		corretor = new Corretor();
		telefone = new Telefone();
		listaCorretor = new ArrayList<Corretor>();
		listaTelefone = new ArrayList<Telefone>();
		logic = LogicFactory.createCorretorLogic();
		service = new CEPService();
		
		// Define as permissões para este controller
		canAdd = isAuthorized("corretor", "add");
		canEdit = isAuthorized("corretor", "edit");
		canDelete = isAuthorized("corretor", "delete");
		canList = isAuthorized("corretor", "list");
		canView = isAuthorized("corretor", "view");
	}
	
	/*** Métodos abstratos de AbstractController ***/

	@Override
	protected void clearVariables() {
		corretor = new Corretor();
		telefone = new Telefone();
		idCorretor = null;
		listaCorretor = new ArrayList<Corretor>();
		listaTelefone = new ArrayList<Telefone>();
		dadosModelo = new ListDataModel(listaCorretor);
	}

	@Override
	public void simpleSearch() {		
		try {
			if (findValue == null || findValue.isEmpty()) {
				dadosModelo = new ListDataModel(logic.buscarTodosAtivos());
			} else {
				dadosModelo = new ListDataModel(logic.buscarPorCPF(findValue));
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
	public String searchView() {
		corretor = new Corretor();
		return super.searchView();
	}
	
	@Override
	public String search() {
		try {
			dadosModelo = new ListDataModel(logic.buscar(corretor));
			if (dadosModelo.isRowAvailable())
				viewMessage("Busca realizada com sucesso!");
			else
				viewMessage("Nenhum registro encontrado!");
		} catch (BusinessException be) {
			viewMessage("Erro ao buscar: " +  be.getMessage(), "frmCorretor");
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
		corretor.setTelefones(listaTelefone);
		try {
			logic.cadastrar(corretor);
			viewMessage("Registro cadastrado com sucesso!");
			clearVariables();
			retorno = super.add();
		} catch (BusinessException be) {
			viewMessage("Erro ao salvar: " + be.getMessage(), "frmCorretor");
		}
		return retorno;
	}

	@Override
	public String editView() {
		Corretor c = (Corretor) dadosModelo.getRowData();
		try {
			corretor = logic.buscar(c.getId());
			listaTelefone = corretor.getTelefones();
			// Instanciando a lista de imóveis e contratos para evitar NullPointerException
			corretor.setImoveis(new ArrayList<Imovel>());
			//corretor.setContratos(new ArrayList<Contrato>());
		} catch (BusinessException be) {
			viewMessage("Erro ao buscar: " + be.getMessage());
		}
		return super.editView();
	} 
	
	@Override
	public String edit() {
		String retorno = null;
		corretor.setTelefones(listaTelefone);
		try {
			logic.editar(corretor);
			viewMessage("Registro editado com sucesso!");
			clearVariables();
			retorno = super.edit();
		} catch (BusinessException be) {
			viewMessage("Erro ao editar: " + be.getMessage(), "frmCorretor");
		}
		return retorno;
	}
	
	@Override
	public String deleteView() {
		Corretor c = (Corretor) dadosModelo.getRowData();
		try {
			corretor = logic.buscar(c.getId());

		} catch (BusinessException be) {
			viewMessage("Erro ao buscar: " + be.getMessage());
		}
		return super.deleteView();
	}
	
	@Override
	public String delete() {		
		try {
			logic.habilitar(corretor.getId());
			if (corretor.isAtivo()) {
				viewMessage("Registro habilitado com sucesso!");
			} else {
				viewMessage("Registro desabilitado com sucesso!");
			}			
			clearVariables();
		} catch (BusinessException be) {
			viewMessage("Erro ao editar: " + be.getMessage(), "frmCorretor");
		}
		return super.delete();
	}
	
	/*** Métodos da classe ***/
	
	private void carregaCombos() {
		// Implemente aqui
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

	public void buscarEndereco() {
		try {
			Endereco e = new Endereco();
			e = service.getEndereco(corretor.getEndereco().getCep());
			corretor.setEndereco(e);
		} catch (ServiceException se) {
			viewMessage(se.getMessage(), "txtCep");
		}
	}
	
	
	/*** Métodos Getters e Setters ***/
	
	public Corretor getCorretor() {
		return corretor;
	}

	public void setCorretor(Corretor corretor) {
		this.corretor = corretor;
	}

	public List<Corretor> getListaCorretor() {
		return listaCorretor;
	}

	public void setListaCorretor(List<Corretor> listaCorretor) {
		this.listaCorretor = listaCorretor;
	}

	public Long getIdCorretor() {
		return idCorretor;
	}

	public void setIdCorretor(Long idCorretor) {
		this.idCorretor = idCorretor;
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
}