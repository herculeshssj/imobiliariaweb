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

package br.com.hslife.imobiliaria.controller.listener;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.hslife.imobiliaria.exception.BusinessException;
import br.com.hslife.imobiliaria.security.ISecurity;
import br.com.hslife.imobiliaria.security.SecurityContext;

public class SessaoListener implements PhaseListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8002349401115390502L;
	
	ISecurity security;
	
	public SessaoListener() {
		super();
		security = new SecurityContext();
	}
	
    @Override
    public void afterPhase(PhaseEvent event) {    	
    	// Obtém o contexto atual
        FacesContext contexto = event.getFacesContext();
        
        // Obter o caminho do contexto
        String caminho = contexto.getExternalContext().getRequestServletPath();
        
        try {
        	// Verifica se o caminho contém o diretório 'admin'
        	if (caminho.indexOf("admin") > -1 && !(caminho.indexOf("menu") > -1)) {
        		security.isAutheticated(security.getUsername());
        	}
        } catch (BusinessException be) {
        	// Em caso de exceção redireciona para a página de login
        	NavigationHandler nh = contexto.getApplication().getNavigationHandler();
            nh.handleNavigation(contexto, null, "logoutSucesso");
        }
    }

    @Override
    public void beforePhase(PhaseEvent event) {

    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

}
