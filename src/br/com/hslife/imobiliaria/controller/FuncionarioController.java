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
import br.com.hslife.imobiliaria.logic.IFuncionario;
import br.com.hslife.imobiliaria.model.Contrato;
import br.com.hslife.imobiliaria.model.Endereco;
import br.com.hslife.imobiliaria.model.Funcionario;
import br.com.hslife.imobiliaria.model.Imovel;
import br.com.hslife.imobiliaria.model.Telefone;
import br.com.hslife.imobiliaria.service.CEPService;

public class FuncionarioController extends GenericController {
	
	/*** Atributos da classe ***/
	
	// Modelo
	Funcionario funcionario;
	Telefone telefone;
	
	// Lógica de negócio
	IFuncionario logic;
	CEPService service;
	
	// Listas
	List<Funcionario> listaFuncionario;
	List<Telefone> listaTelefone;
	
	// Armazena o id do objeto de modelo
	Long idFuncionario;
	
	// SelectItems dos combos
	//private List<SelectItem> horas;
	
	// Seleção dos telefones para exclusão
	String numTelefone;
	String tipoTelefone;
	String dddTelefone;
	
	/*** Construtor ***/	
	
	public FuncionarioController() {
		funcionario = new Funcionario();
		telefone = new Telefone();
		listaFuncionario = new ArrayList<Funcionario>();
		listaTelefone = new ArrayList<Telefone>();
		logic = LogicFactory.createFuncionarioLogic();
		service = new CEPService();
		
		// Define as permissões para este controller
		canAdd = isAuthorized("funcionario", "add");
		canEdit = isAuthorized("funcionario", "edit");
		canDelete = isAuthorized("funcionario", "delete");
		canList = isAuthorized("funcionario", "list");
		canView = isAuthorized("funcionario", "view");
	}
	
	/*** Métodos abstratos de AbstractController ***/

	@Override
	protected void clearVariables() {
		funcionario = new Funcionario();
		telefone = new Telefone();
		idFuncionario = null;
		listaFuncionario = new ArrayList<Funcionario>();
		listaTelefone = new ArrayList<Telefone>();
		dadosModelo = new ListDataModel(listaFuncionario);
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
		funcionario = new Funcionario();
		return super.searchView();
	}
	
	@Override
	public String search() {
		try {
			dadosModelo = new ListDataModel(logic.buscar(funcionario));
			if (dadosModelo.isRowAvailable())
				viewMessage("Busca realizada com sucesso!");
			else
				viewMessage("Nenhum registro encontrado!");
		} catch (BusinessException be) {
			viewMessage("Erro ao buscar: " +  be.getMessage(), "frmFuncionario");
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
		funcionario.setTelefones(listaTelefone);
		try {
			logic.cadastrar(funcionario);
			viewMessage("Registro cadastrado com sucesso!");
			clearVariables();
			retorno = super.add();
		} catch (BusinessException be) {
			viewMessage("Erro ao salvar: " + be.getMessage(), "frmFuncionario");
		}
		return retorno;
	}

	@Override
	public String editView() {
		Funcionario c = (Funcionario) dadosModelo.getRowData();
		try {
			funcionario = logic.buscar(c.getId());
			listaTelefone = funcionario.getTelefones();
			// Instanciando a lista de imóveis e contratos para evitar NullPointerException
			funcionario.setImoveis(new ArrayList<Imovel>());
			funcionario.setContratos(new ArrayList<Contrato>());
		} catch (BusinessException be) {
			viewMessage("Erro ao buscar: " + be.getMessage());
		}
		return super.editView();
	} 
	
	@Override
	public String edit() {
		String retorno = null;
		funcionario.setTelefones(listaTelefone);
		try {
			logic.editar(funcionario);
			viewMessage("Registro editado com sucesso!");
			clearVariables();
			retorno = super.edit();
		} catch (BusinessException be) {
			viewMessage("Erro ao editar: " + be.getMessage(), "frmFuncionario");
		}
		return retorno;
	}
	
	@Override
	public String deleteView() {
		Funcionario c = (Funcionario) dadosModelo.getRowData();
		try {
			funcionario = logic.buscar(c.getId());

		} catch (BusinessException be) {
			viewMessage("Erro ao buscar: " + be.getMessage());
		}
		return super.deleteView();
	}
	
	@Override
	public String delete() {		
		try {
			logic.habilitar(funcionario.getId());
			if (funcionario.isAtivo()) {
				viewMessage("Registro habilitado com sucesso!");
			} else {
				viewMessage("Registro desabilitado com sucesso!");
			}			
			clearVariables();
		} catch (BusinessException be) {
			viewMessage("Erro ao editar: " + be.getMessage(), "frmFuncionario");
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
			e = service.getEndereco(funcionario.getEndereco().getCep());
			funcionario.setEndereco(e);
		} catch (ServiceException se) {
			viewMessage(se.getMessage(), "txtCep");
		}
	}
	
	
	/*** Métodos Getters e Setters ***/
	
	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<Funcionario> getListaFuncionario() {
		return listaFuncionario;
	}

	public void setListaFuncionario(List<Funcionario> listaFuncionario) {
		this.listaFuncionario = listaFuncionario;
	}

	public Long getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(Long idFuncionario) {
		this.idFuncionario = idFuncionario;
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