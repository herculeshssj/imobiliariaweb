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
import java.util.List;

import javax.faces.model.ListDataModel;

import br.com.hslife.imobiliaria.exception.BusinessException;
import br.com.hslife.imobiliaria.factory.LogicFactory;
import br.com.hslife.imobiliaria.model.Agenda;
import br.com.hslife.imobiliaria.model.Usuario;
import br.com.hslife.imobiliaria.security.ISecurity;
import br.com.hslife.imobiliaria.security.SecurityContext;

public class HomeController extends GenericController {

	private Usuario usuario;
	private List<Agenda> agendaDeHoje;
	
	ISecurity security;
	
	public HomeController() {
		
		usuario = new Usuario();
		security = new SecurityContext();
		
		Date hoje = new Date();
		
		Agenda a = new Agenda();
		
		a.setData(hoje);
		
		try {
			// Busca os agendamentos do dia
			agendaDeHoje = LogicFactory.createAgendaLogic().buscar(a);
			
			dadosModelo = new ListDataModel(agendaDeHoje);
			
		} catch (Exception g) {
			g.printStackTrace(); // Quando eu tiver um tempo penso em um algoritmo melhor
		}
	
	}
	/*
	public String remarcar() {
		String retorno = null;
		FacesContext contexto = FacesContext.getCurrentInstance();
		// Pega a instância de AgendaController para setar dadosModelo e chamar o editView()
		// Informação obtida em http://balusc.blogspot.com/2006/06/communication-in-jsf.html
		AgendaController agendaController = (AgendaController) contexto.getApplication().evaluateExpressionGet(contexto, "#{agendaMB}", AgendaController.class);
		if (agendaController != null) {
			agendaController.setDadosModelo(dadosModelo);
			retorno = agendaController.editView();
		}
		return retorno; 
	}
	*/
	
	public String atualizaAgenda() {
		Date hoje = new Date();
		
		Agenda a = new Agenda();
		
		a.setData(hoje);
		
		try {
			// Busca os agendamentos do dia
			agendaDeHoje = LogicFactory.createAgendaLogic().buscar(a);
			
			dadosModelo = new ListDataModel(agendaDeHoje);
			
		} catch (Exception g) {
			g.printStackTrace(); // Quando eu tiver um tempo penso em um algoritmo melhor
		}
		return "menuHome";
	}
	
	public String login() {
		String retorno = null;
		try {
			security.authenticate(usuario.getLogin(), usuario.getSenha());
			
			usuario =  new Usuario();
			
			// Chama o método que realiza a vigoração e encerramento dos contratos
			LogicFactory.createContratoLogic().vigorarEncerrarContratos();
			
			retorno = "loginSucesso";
			
		} catch (BusinessException be) {
			viewMessage("Erro ao autenticar: " + be.getMessage());
		}
		return retorno;
	}
	
	public String logout() {
		String retorno = null;
		try {
			security.userSystemLogout();
			
			retorno = "logoutSucesso";
	
		} catch (BusinessException be) {
			viewMessage("Erro ao autenticar: " + be.getMessage());
		}
		return retorno;
	}

	@Override
	public void simpleSearch() {
		// TODO Auto-generated method stub
		
	}
	
	public List<Agenda> getAgendaDeHoje() {
		return agendaDeHoje;
	}

	public void setAgendaDeHoje(List<Agenda> agendaDeHoje) {
		this.agendaDeHoje = agendaDeHoje;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getUsuarioLogado() {
		return security.getUsername();
	}

	@Override
	protected void clearVariables() {
		// TODO Auto-generated method stub
		
	}
	
}
