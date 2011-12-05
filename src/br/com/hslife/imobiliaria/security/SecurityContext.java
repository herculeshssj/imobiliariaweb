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

package br.com.hslife.imobiliaria.security;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.hslife.imobiliaria.db.HibernateUtility;
import br.com.hslife.imobiliaria.exception.BusinessException;
import br.com.hslife.imobiliaria.factory.LogicFactory;
import br.com.hslife.imobiliaria.logic.IUsuario;
import br.com.hslife.imobiliaria.model.Usuario;
import br.com.hslife.imobiliaria.util.Util;

public class SecurityContext implements ISecurity {

	@Override
	public void authenticate(String login, String password)
			throws BusinessException {
		// Instanciação dos objetos
		FacesContext contexto = FacesContext.getCurrentInstance();
		IUsuario logic = LogicFactory.createUsuarioLogic();
		Usuario u = new Usuario();

		// Obtém o usuário a partir do login informado
		try {
			u = logic.buscarPorLogin(login);
		} catch (BusinessException be) {
			throw new BusinessException("Login não encontrado!", be);
		}

		// Verifica se o objeto não é nulo, a senha está correta e o usuário
		// está ativo
		if (u != null && u.getSenha().equals(Util.SHA1(password))
				&& u.getAtivo()) {
			// Seta a sessão com os dados do usuário
			HttpSession sessao = (HttpSession) contexto.getExternalContext()
					.getSession(true);
			sessao.setAttribute("usuarioLogado", u);
		} else {
			throw new BusinessException("Login e/ou senha inválidos!");
		}

	}

	@Override
	public void isAutheticated(String login) throws BusinessException {
		// Instanciação dos objetos
		FacesContext contexto = FacesContext.getCurrentInstance();

		// Resgata o usuário da sessão
		HttpSession sessao = (HttpSession) contexto.getExternalContext()
				.getSession(false);
		// Verifica se o usuário é nulo. Se for lança uma exceção
		if (sessao == null
				|| sessao.getAttribute("usuarioLogado") == null
				|| !((Usuario) sessao.getAttribute("usuarioLogado")).getLogin()
						.equals(login)) {

			throw new BusinessException("Usuário não logado!");
		}

	}

	@Override
	public void userSystemLogout() throws BusinessException {
		FacesContext contexto = FacesContext.getCurrentInstance();
		HttpSession sessao = (HttpSession) contexto.getExternalContext()
				.getSession(false);
		sessao.invalidate();
		HibernateUtility.closeSession();
	}

	@Override
	public String getUsername() {
		// Instanciação dos objetos
		FacesContext contexto = FacesContext.getCurrentInstance();
		Usuario u = new Usuario();

		// Resgata o usuário da sessão
		HttpSession sessao = (HttpSession) contexto.getExternalContext()
				.getSession(false);

		// Retorna o nome do usuário logado
		if (sessao == null || sessao.getAttribute("usuarioLogado") == null) {
			return "";
		} else {
			u = (Usuario) sessao.getAttribute("usuarioLogado");
			return u.getLogin();
		}
	}

}
