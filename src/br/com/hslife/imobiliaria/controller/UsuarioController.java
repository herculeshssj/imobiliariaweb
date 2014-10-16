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
import br.com.hslife.imobiliaria.factory.LogicFactory;
import br.com.hslife.imobiliaria.logic.IUsuario;
import br.com.hslife.imobiliaria.model.Funcionario;
import br.com.hslife.imobiliaria.model.Grupo;
import br.com.hslife.imobiliaria.model.Usuario;
import br.com.hslife.imobiliaria.security.ISecurity;
import br.com.hslife.imobiliaria.security.SecurityContext;
import br.com.hslife.imobiliaria.util.Util;

public class UsuarioController extends GenericController {

	/*** Atributos da classe ***/

	// Modelo
	Usuario usuario;

	// Lógica de negócio
	IUsuario logic;
	ISecurity security;

	// Listas
	List<Usuario> listaUsuario;

	// Senha nova e confirma senha
	String senhaAtual;
	String novaSenha;
	String confirmaSenha;

	// Lista de SelectItems
	List<SelectItem> funcionarios;
	List<SelectItem> grupos;

	// Armazena o id do grupo
	Long idGrupo;
	Long idFuncionario;

	/*** Construtor ***/

	public UsuarioController() {
		usuario = new Usuario();
		listaUsuario = new ArrayList<Usuario>();
		logic = LogicFactory.createUsuarioLogic();
		security = new SecurityContext();
		componente = "frmUsuario";

		// Define as permissões para este controller
		canAdd = isAuthorized("usuario", "add");
		canEdit = isAuthorized("usuario", "edit");
		canDelete = isAuthorized("usuario", "delete");
		canList = isAuthorized("usuario", "list");
		canView = isAuthorized("usuario", "view");
	}

	/*** Métodos abstratos de AbstractController ***/

	@Override
	protected void clearVariables() {
		usuario = new Usuario();
		listaUsuario = new ArrayList<Usuario>();
		dadosModelo = new ListDataModel(listaUsuario);
		idGrupo = null;
		idFuncionario = null;
	}

	@Override
	public void simpleSearch() {
		try {
			usuario.setLogin(findValue);
			if (findValue == null || findValue.isEmpty()) {
				dadosModelo = new ListDataModel(logic.buscarTodos());
			} else {
				dadosModelo = new ListDataModel(logic.buscarTodosPorLogin(usuario.getLogin()));
			}
			
			if (dadosModelo.isRowAvailable())
				viewMessage("Busca realizada com sucesso!");
			else
				viewMessage("Nenhum registro encontrado!");
			usuario.setLogin("");
		} catch (BusinessException be) {
			viewMessage("Erro ao buscar: " + be.getMessage());
		}
	}

	/*** Métodos concretos sobrescritos ***/

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
			// Verifica se a nova senha informada confere com a senha confirmada
			if (!confirmaSenha.equals(novaSenha)) {
				viewMessage("As senhas informadas não conferem!", componente);
			} else {
				usuario.setFuncionario(LogicFactory.createFuncionarioLogic()
						.buscar(idFuncionario));
				usuario.setGrupo(LogicFactory.createGrupoLogic()
						.buscar(idGrupo));
				usuario.setSenha(confirmaSenha);
				logic.cadastrar(usuario);
				viewMessage("Registro cadastrado com sucesso!");
				clearVariables();
				retorno = super.add();
			}
		} catch (BusinessException be) {
			viewMessage("Erro ao salvar: " + be.getMessage(), componente);
		}
		return retorno;
	}

	@Override
	public String editView() {
		String retorno = null;
		Usuario u = (Usuario) dadosModelo.getRowData();
		try {
			usuario = logic.buscar(u.getId());
			idFuncionario = usuario.getFuncionario().getId();
			idGrupo = usuario.getGrupo().getId();
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
			// Verifica se o campo senha foi preenchido para realizar a
			// alteração da senha
			if (novaSenha != null && !novaSenha.isEmpty()
					&& confirmaSenha != null && !confirmaSenha.isEmpty()) {
				if (!confirmaSenha.equals(novaSenha)) {
					throw new BusinessException(
							"As senhas informadas não conferem!");
				} else {
					usuario.setSenha(confirmaSenha);
				}
			}
			usuario.setFuncionario(LogicFactory.createFuncionarioLogic()
					.buscar(idFuncionario));
			usuario.setGrupo(LogicFactory.createGrupoLogic().buscar(idGrupo));
			logic.editar(usuario);
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
		Usuario u = (Usuario) dadosModelo.getRowData();
		try {
			usuario = logic.buscar(u.getId());
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
			logic.habilitar(usuario.getId());
			if (usuario.getAtivo()) {
				viewMessage("Registro habilitado com sucesso!");
			} else {
				viewMessage("Registro desabilitado com sucesso!");
			}
			clearVariables();
			retorno = super.delete();
		} catch (BusinessException be) {
			viewMessage("Erro ao editar: " + be.getMessage(), componente);
		}
		return retorno;
	}

	/*** Métodos da classe ***/

	void carregaCombos() {
		// Instancia as listas de SelectItem
		funcionarios = new ArrayList<SelectItem>();
		grupos = new ArrayList<SelectItem>();

		// Carrega a combobox com os funcionários e os grupos
		try {
			for (Grupo g : LogicFactory.createGrupoLogic().buscarTodos()) {
				grupos.add(new SelectItem(g.getId(), g.getDescricao()));
			}
			for (Funcionario f : LogicFactory.createFuncionarioLogic()
					.buscarTodos()) {
				funcionarios.add(new SelectItem(f.getId(), f.getNome(), f.getNome(), !f.isAtivo()));
			}
		} catch (BusinessException be) {
			viewMessage("Erro ao carregar: " + be.getMessage(), componente);
		}
	}
	
	public void alterarSenha() {
		try {
			usuario = logic.buscarPorLogin(security.getUsername());
			if (!senhaAtual.equals(usuario.getSenha())) {
				throw new BusinessException("Senha atual não confere!");
			}
			if (novaSenha != null && !novaSenha.isEmpty()
					&& confirmaSenha != null && !confirmaSenha.isEmpty()) {
				if (!confirmaSenha.equals(novaSenha)) {
					throw new BusinessException(
							"As senhas informadas não conferem!");
				} else {
					usuario.setSenha(confirmaSenha);
					logic.editar(usuario);
					viewMessage("Senha alterada com sucesso!", componente);
					clearVariables();
				}
			}			
		} catch (BusinessException be) {
			viewMessage("Erro ao alterar: " + be.getMessage(), componente);
		}
		
	}

	/*** Métodos Getters e Setters ***/
	
	/* O método criptografa a senha do usuário antes de setar no objeto */
	public void setConfirmaSenha(String confirmaSenha) {
		if (confirmaSenha != null && !confirmaSenha.isEmpty()) {
			this.confirmaSenha = Util.SHA1(confirmaSenha);
		}		
	}
	
	/* O método criptografa a senha do usuário antes de setar no objeto */
	public void setNovaSenha(String novaSenha) {
		if (novaSenha != null && !novaSenha.isEmpty()) {
			this.novaSenha = Util.SHA1(novaSenha);
		}		
	}
	
	/* O método criptografa a senha do usuário antes de setar no objeto */
	public void setSenhaAtual(String senhaAtual) {
		if (senhaAtual != null && !senhaAtual.isEmpty()) {
			this.senhaAtual = Util.SHA1(senhaAtual);
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public List<SelectItem> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<SelectItem> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public List<SelectItem> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<SelectItem> grupos) {
		this.grupos = grupos;
	}

	public Long getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(Long idGrupo) {
		this.idGrupo = idGrupo;
	}

	public Long getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(Long idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public String getSenhaAtual() {
		return senhaAtual;
	}

	
}