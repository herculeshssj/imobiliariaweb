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
import br.com.hslife.imobiliaria.logic.IGrupo;
import br.com.hslife.imobiliaria.model.Grupo;
import br.com.hslife.imobiliaria.model.Permissao;
import br.com.hslife.imobiliaria.util.ViewUtil;

public class GrupoController extends GenericController {
	
	/*** Atributos da classe ***/
	
	// Modelo
	Grupo grupo;
	Permissao permissao;
	
	// Lógica de negócio
	IGrupo logic;
	
	// Listas
	List<Grupo> listaGrupo;
	List<Permissao> listaPermissao;	
	
	// Seleção do módulo para exclusão
	String modulo;
		
	// Lista de SelectItems
	List<SelectItem> modulos;
				
	/*** Construtor ***/	
	
	public GrupoController() {
		grupo = new Grupo();
		permissao = new Permissao();
		listaGrupo = new ArrayList<Grupo>();
		listaPermissao = new ArrayList<Permissao>();
		logic = LogicFactory.createGrupoLogic();
		componente = "frmGrupo";
		
		// Define as permissões para este controller
		canAdd = isAuthorized("grupo", "add");
		canEdit = isAuthorized("grupo", "edit");
		canDelete = isAuthorized("grupo", "delete");
		canList = isAuthorized("grupo", "list");
		canView = isAuthorized("grupo", "view");
	}
	
	/*** Métodos abstratos de AbstractController ***/

	@Override
	protected void clearVariables() {
		grupo = new Grupo();
		permissao = new Permissao();
		listaPermissao = new ArrayList<Permissao>();
		listaGrupo = new ArrayList<Grupo>();
		dadosModelo = new ListDataModel(listaGrupo);
	}

	@Override
	public void simpleSearch() {		
		try {
			grupo.setDescricao(findValue);
			if (findValue == null || findValue.isEmpty()) {
				dadosModelo = new ListDataModel(logic.buscarTodos());
			} else {
				dadosModelo = new ListDataModel(logic.buscar(grupo));
			}							
			viewMessage("Busca realizada com sucesso!");
			grupo.setDescricao("");
		} catch (BusinessException be) {
			viewMessage("Erro ao buscar: " +  be.getMessage());
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
		grupo.setPermissoes(listaPermissao);
		try {
			logic.cadastrar(grupo);
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
		Grupo g = (Grupo) dadosModelo.getRowData();
		try {
			carregaCombos();
			grupo = logic.buscar(g.getId());
			listaPermissao = grupo.getPermissoes();
			retorno = super.editView();
		} catch (BusinessException be) {
			viewMessage("Erro ao buscar: " + be.getMessage(), componente);
		}
		return retorno;
	} 
	
	@Override
	public String edit() {
		String retorno = null;
		grupo.setPermissoes(listaPermissao);
		try {
			logic.editar(grupo);
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
		Grupo g = (Grupo) dadosModelo.getRowData();
		try {
			grupo = logic.buscar(g.getId());
			listaPermissao = grupo.getPermissoes();
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
			logic.excluir(grupo);
			viewMessage("Registro excluído com sucesso!");						
			clearVariables();
			retorno = super.delete();
		} catch (BusinessException be) {
			viewMessage("Erro ao excluir: " + be.getMessage(), componente);
		}
		return retorno;
	}
	
	/*** Métodos da classe ***/
	
	public void adicionarPermissao() {
		// Itera a lista de permissões a procura do módulo informado
		int count = 0;
		for (Permissao p : listaPermissao) {
			if (p.getModulo().equalsIgnoreCase(permissao.getModulo())) {
				count++;				
			}
		}
				
		// Verifica se encontrou alguma ocorrência do módulo na lista. Se encontrou retorna uma mensagem de aviso
		// Caso contrário, adiciona o telefone na lista
		if (count == 0) {
			listaPermissao.add(permissao);			
		} else {
			viewMessage("O módulo informado já foi adicionado!", componente);
		}
				
		// Limpa os objetos
		permissao = new Permissao();
	}
	
	public void removerPermissao() {
		// Procura o módulo na lista de permissões para remover
		for (Permissao p : listaPermissao) {
			if (p.getModulo().equals(modulo) ) {
				listaPermissao.remove(p);
				break;
			}
		}
		
		// Limpa o objeto
		permissao = new Permissao();
	}
	
	void carregaCombos() {
		// Instancia as listas de SelectItem
		modulos = new ArrayList<SelectItem>();
		
		// Carrega a combobox com os módulos
        for (int i = 0; i < ViewUtil.getSize(ViewUtil.MODULOS); i++) {
            modulos.add(i, new SelectItem(ViewUtil.getData(i, ViewUtil.MODULOS), ViewUtil.getData(i, ViewUtil.MODULOS)));
        }
	}
	
	/*** Métodos Getters e Setters ***/

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public Permissao getPermissao() {
		return permissao;
	}

	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}

	public IGrupo getLogic() {
		return logic;
	}

	public void setLogic(IGrupo logic) {
		this.logic = logic;
	}

	public List<Grupo> getListaGrupo() {
		return listaGrupo;
	}

	public void setListaGrupo(List<Grupo> listaGrupo) {
		this.listaGrupo = listaGrupo;
	}

	public List<Permissao> getListaPermissao() {
		return listaPermissao;
	}

	public void setListaPermissao(List<Permissao> listaPermissao) {
		this.listaPermissao = listaPermissao;
	}

	public String getModulo() {
		return modulo;
	}

	public void setModulo(String modulo) {
		this.modulo = modulo;
	}

	public List<SelectItem> getModulos() {
		return modulos;
	}

	public void setModulos(List<SelectItem> modulos) {
		this.modulos = modulos;
	}

}