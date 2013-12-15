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

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.servlet.http.HttpSession;

import br.com.hslife.imobiliaria.exception.BusinessException;
import br.com.hslife.imobiliaria.factory.LogicFactory;
import br.com.hslife.imobiliaria.logic.IUsuario;
import br.com.hslife.imobiliaria.model.Permissao;
import br.com.hslife.imobiliaria.model.Usuario;

public abstract class GenericController {
	
	String findValue;
	Date findDate;
	
	DataModel dadosModelo;
	
	String componente;
	
	IUsuario usuarioLogic;
	
	boolean canAdd;
	boolean canEdit;
	boolean canDelete;
	boolean canList;
	boolean canView;
	boolean onlyAdmin;
	
	public GenericController() {
		usuarioLogic = LogicFactory.createUsuarioLogic();
	}
	
	protected abstract void clearVariables();
	
	public abstract void simpleSearch();
	
	
	public String addView() {
		return "addView";
	}
	
	public String editView() {
		return "editView";
	}
	
	public String deleteView() {
		return "deleteView";
	}
	
	public String searchView() {
		return "searchView";
	}
	
	public String cancelAction() {
		return "cancelAction";
	}
	
	public String add() {	
		return "save";
	}
	
	public String edit() {
		return "edit";
	}
	
	public String delete() {
		return "delete";
	}
	
	public String search() {
		return "search";
	}
	
	public FacesContext getContext() {
		return FacesContext.getCurrentInstance();
	}
	
	public HttpSession getSession() {
		return (HttpSession) getContext().getExternalContext().getSession(false);
	}
	
	public void viewMessage(String msg) {
		getContext().addMessage(null, new FacesMessage(msg));
	}
	
	public void viewMessage(String msg, String component) {
		getContext().addMessage(component, new FacesMessage(msg));
	}
	
	public boolean isAuthorized(String module, String action) {
		try {
			// Resgata o usuário da sessão
			Usuario usuario = (Usuario)getSession().getAttribute("usuarioLogado");		
			Usuario u = usuarioLogic.buscar(usuario.getId());
			// Verifica se o usuário é ADMIN
			if (u.getAdmin()) {
				return true;
			} else {
				// Itera a lista de permissão do usuário a procura do módulo e ação desejados
				for (Permissao p : u.getGrupo().getPermissoes()) {
					// Verifica o módulo
					if (p.getModulo().equalsIgnoreCase(module)) {
						if (p.isAdd() && action.equalsIgnoreCase("add")) {
							return true;
						}
						if (p.isEdit() && action.equalsIgnoreCase("edit")) {
							return true;
						}
						if (p.isDelete() && action.equalsIgnoreCase("delete")) {
							return true;
						}
						if (p.isView() && action.equalsIgnoreCase("view")) {
							return true;
						}
						if (p.isList() && action.equalsIgnoreCase("list")) {
							return true;
						}
					}				
				}
			}
		} catch (Exception be) {
			be.printStackTrace();
			viewMessage("Erro ao processar permissões:" + be.getMessage());
		}
		return false;
	}
	
	public boolean isUserAdmin() {
		// Resgata o usuário da sessão
		Usuario u = (Usuario)getSession().getAttribute("usuarioLogado");
		return u.getAdmin();
	}

	public String getFindValue() {
		return findValue;
	}

	public void setFindValue(String findValue) {
		this.findValue = findValue;
	}

	public DataModel getDadosModelo() {
		return dadosModelo;
	}

	public void setDadosModelo(DataModel dadosModelo) {
		this.dadosModelo = dadosModelo;
	}

	public Date getFindDate() {
		return findDate;
	}

	public void setFindDate(Date findDate) {
		this.findDate = findDate;
	}

	public boolean isCanAdd() {
		return canAdd;
	}

	public void setCanAdd(boolean canAdd) {
		this.canAdd = canAdd;
	}

	public boolean isCanEdit() {
		return canEdit;
	}

	public void setCanEdit(boolean canEdit) {
		this.canEdit = canEdit;
	}

	public boolean isCanDelete() {
		return canDelete;
	}

	public void setCanDelete(boolean canDelete) {
		this.canDelete = canDelete;
	}

	public boolean isCanList() {
		return canList;
	}

	public void setCanList(boolean canList) {
		this.canList = canList;
	}

	public boolean isCanView() {
		return canView;
	}

	public void setCanView(boolean canView) {
		this.canView = canView;
	}

	public boolean isOnlyAdmin() {
		return onlyAdmin;
	}

	public void setOnlyAdmin(boolean onlyAdmin) {
		this.onlyAdmin = onlyAdmin;
	}
}
