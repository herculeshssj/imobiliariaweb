/*** 

    Copyright (c) 2011, 2014 Hércules S. S. José
    

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
import java.util.Arrays;
import java.util.List;

import javax.faces.model.ListDataModel;

import br.com.hslife.imobiliaria.exception.BusinessException;
import br.com.hslife.imobiliaria.exception.ServiceException;
import br.com.hslife.imobiliaria.factory.LogicFactory;
import br.com.hslife.imobiliaria.logic.ICliente;
import br.com.hslife.imobiliaria.model.Cliente;
import br.com.hslife.imobiliaria.model.Endereco;
import br.com.hslife.imobiliaria.model.Telefone;
import br.com.hslife.imobiliaria.service.CEPService;

public class ClienteController extends GenericController {
	
	/*** Atributos da classe ***/
	
	// Modelo
	Cliente cliente;
	Telefone telefone;
	
	// Lógica de negócio
	ICliente logic;
	CEPService service;
	
	// Listas
	List<Cliente> listaCliente;
	List<Telefone> listaTelefone;
	
	// Armazena o id do objeto de modelo
	Long idCliente;
	
	// SelectItems dos combos
	//private List<SelectItem> horas;
	
	// Seleção dos telefones para exclusão
	String numTelefone;
	String tipoTelefone;
	String dddTelefone;
	
	// Armazena a lista de tipos de clientes selecionados
	//List<String> tipoCliente;
	String tipoCliente;
	
	/*** Construtor ***/	
	
	public ClienteController() {
		cliente = new Cliente();
		telefone = new Telefone();
		listaCliente = new ArrayList<Cliente>();
		listaTelefone = new ArrayList<Telefone>();
		logic = LogicFactory.createClienteLogic();
		service = new CEPService();
		//tipoCliente = new ArrayList<String>();
		
		// Define as permissões para este controller
		canAdd = isAuthorized("cliente", "add");
		canEdit = isAuthorized("cliente", "edit");
		canDelete = isAuthorized("cliente", "delete");
		canList = isAuthorized("cliente", "list");
		canView = isAuthorized("cliente", "view");
	}
	
	/*** Métodos abstratos de AbstractController ***/

	@Override
	protected void clearVariables() {
		cliente = new Cliente();
		telefone = new Telefone();
		idCliente = null;
		listaCliente = new ArrayList<Cliente>();
		listaTelefone = new ArrayList<Telefone>();
		//tipoCliente = new ArrayList<String>();
		dadosModelo = new ListDataModel(listaCliente);
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
		cliente = new Cliente();
		return super.searchView();
	}
	
	@Override
	public String search() {
		try {
			dadosModelo = new ListDataModel(logic.buscar(cliente));
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
		setaTipoCliente();
		cliente.setTelefones(listaTelefone);
		try {
			logic.cadastrar(cliente);
			viewMessage("Registro cadastrado com sucesso!");
			clearVariables();
			retorno = super.add();
		} catch (BusinessException be) {
			viewMessage("Erro ao salvar: " + be.getMessage(), "frmCliente");
			cliente.setTipoCliente(""); // Limpa em caso de erro
		}
		return retorno;
	}

	@Override
	public String editView() {
		try {
			cliente = logic.buscar(((Cliente) dadosModelo.getRowData()).getId());
			listaTelefone = cliente.getTelefones();
			resgataTipoCliente();
			// Instanciando a lista de imóveis e contratos para evitar NullPointerException
			//cliente.setImoveis(LogicFactory.createImovelLogic().buscar(new Imovel(cliente)));
			//cliente.setContratos(LogicFactory.createContratoLogic().buscar(new Contrato(cliente)));
		} catch (BusinessException be) {
			viewMessage("Erro ao buscar: " + be.getMessage());
		}
		return super.editView();
	} 
	
	@Override
	public String edit() {
		String retorno = null;
		setaTipoCliente();
		cliente.setTelefones(listaTelefone);
		try {
			logic.editar(cliente);
			viewMessage("Registro editado com sucesso!");
			clearVariables();
			retorno = super.edit();
		} catch (BusinessException be) {
			viewMessage("Erro ao editar: " + be.getMessage(), "frmCliente");
			cliente.setTipoCliente(""); // Limpa em caso de erro
		}
		return retorno;
	}
	
	@Override
	public String deleteView() {
		Cliente c = (Cliente) dadosModelo.getRowData();
		try {
			cliente = logic.buscar(c.getId());
			resgataTipoCliente();
		} catch (BusinessException be) {
			viewMessage("Erro ao buscar: " + be.getMessage());
		}
		return super.deleteView();
	}
	
	@Override
	public String delete() {		
		try {
			setaTipoCliente();
			logic.habilitar(cliente.getId());
			if (cliente.isAtivo()) {
				viewMessage("Registro desabilitado com sucesso!");
			} else {
				viewMessage("Registro habilitado com sucesso!");				
			}			
			clearVariables();
		} catch (BusinessException be) {
			viewMessage("Erro ao editar: " + be.getMessage(), "frmCliente");
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
			e = service.getEndereco(cliente.getEndereco().getCep());
			cliente.setEndereco(e);
		} catch (ServiceException se) {
			viewMessage(se.getMessage(), "txtCep");
		}
	}
	
	private void setaTipoCliente() {
		cliente.setTipoCliente("cliente");
		cliente.setTipoCliente(cliente.getTipoCliente() + "," + tipoCliente);
		/*for (String s : tipoCliente) {
			cliente.setTipoCliente(cliente.getTipoCliente() + "," + s);			
		}*/
	}
	
	private void resgataTipoCliente() {
		List<String> lista = new ArrayList<String>();
		lista.addAll(Arrays.asList(cliente.getTipoCliente().split(",")));
		lista.remove("cliente");
		tipoCliente = lista.get(0);
		
		/*String[] lista = cliente.getTipoCliente().split(",");
		for (String s : lista) {
			tipoCliente.add(s);
		}*/
	}
	
	/*** Métodos Getters e Setters ***/
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getListaCliente() {
		return listaCliente;
	}

	public void setListaCliente(List<Cliente> listaCliente) {
		this.listaCliente = listaCliente;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
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

	public String getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}
}