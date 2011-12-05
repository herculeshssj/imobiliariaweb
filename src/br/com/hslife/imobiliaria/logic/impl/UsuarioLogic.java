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

package br.com.hslife.imobiliaria.logic.impl;

import java.util.List;

import br.com.hslife.imobiliaria.dao.IUsuarioDao;
import br.com.hslife.imobiliaria.db.HibernateUtility;
import br.com.hslife.imobiliaria.exception.BusinessException;
import br.com.hslife.imobiliaria.logic.IUsuario;
import br.com.hslife.imobiliaria.model.Usuario;

public class UsuarioLogic implements IUsuario {
	
	IUsuarioDao dao;
	
	public UsuarioLogic(IUsuarioDao dao) {
		this.dao = dao;
	}

	@Override
	public void cadastrar(Usuario usuario) throws BusinessException {
		try {
			HibernateUtility.beginTransaction();
			dao.save(usuario);
			HibernateUtility.commitTransaction();
		} catch (Exception e) {
			HibernateUtility.rollbackTransaction();
			throw new BusinessException(e);
		}		
	}

	@Override
	public void editar(Usuario usuario) throws BusinessException {
		try {
			HibernateUtility.beginTransaction();
			dao.update(usuario);
			HibernateUtility.commitTransaction();
		} catch (Exception e) {
			HibernateUtility.rollbackTransaction();
			throw new BusinessException(e);
		}		
	}

	@Override
	public void habilitar(Long id) throws BusinessException {
		Usuario u = (Usuario) dao.getById(id, Usuario.class);
		if (u.getAtivo()) {
			u.setAtivo(false);
		} else {
			u.setAtivo(true);
		}
		
		try {
			HibernateUtility.beginTransaction();
			dao.update(u);
			HibernateUtility.commitTransaction();
		} catch (Exception e) {
			HibernateUtility.rollbackTransaction();
			throw new BusinessException(e);
		}
		
	}

	@Override
	public Usuario buscar(Long id) throws BusinessException {
		return (Usuario) dao.getById(id, Usuario.class);
	}

	@Override
	public List<Usuario> buscar(Usuario usuario) throws BusinessException {
		return dao.listByExample(usuario);
	}

	@Override
	public List<Usuario> buscarTodos() throws BusinessException {
		return dao.list(Usuario.class);
	}

	@Override
	public Usuario buscarPorLogin(String login) throws BusinessException {
		return dao.buscarPorLogin(login);
	}

	
	
	/*
	Map<String, Object> criterio;
	
	public UsuarioLogic(IDao<Usuario> dao) {
		this.dao = dao;
	}

	@Override
	public void cadastrarUsuario(Usuario usuario) throws GlobalException {
		// Declara o Map que será passado para o Dao
		criterio = new HashMap<String, Object>();

		// Define os parâmetros para a busca
		criterio.put(SEARCH_FUNCIONARIO, usuario.getFuncionario().getId());
		criterio.put(SEARCH_ATIVO, true);
				
		// Verifica se já existe um login cadastrado e ativo
		if (dao.findByCriteria(criterio).size() > 0) {
			throw new LogicException("Já existe login cadastrado e ativo para este usuário!");
		}
		
		// Salva o usuario
		dao.save(usuario);
	}

	@Override
	public void editarUsuario(Usuario usuario) throws GlobalException {
		// Declara o Map que será passado para o Dao
		criterio = new HashMap<String, Object>();

		// Define os parâmetros para a busca
		criterio.put(SEARCH_ADMIN, true);
		criterio.put(SEARCH_ATIVO, true);
		
		// Verifica se existe mais de um usuário Admin para permitir a alteração da flag ADMIN
		if (!usuario.isAdmin() && dao.findByCriteria(criterio).size() <= 1) {
			throw new LogicException("É necessário existir pelo menos um usuário ADMIN no sistema");
		}
		
		// Seta o grupo no usuário
		usuario.setGrupo(new GrupoDao().findById(usuario.getGrupo().getId()));
		
		// Altera o usuario informado
		dao.update(usuario);
	}

	@Override
	public void habilitarUsuario(long id) throws GlobalException {
		// Habilita/desabilita o usuário informado		
		Usuario usuario = (Usuario) dao.findById(id);
		
		if (usuario.isAtivo()) {
			usuario.setAtivo(false);			
		} else {
			temLoginAtivoFuncionario(usuario.getFuncionario().getId());
			usuario.setAtivo(true);
		}
		dao.update(usuario);		
	}

	@Override
	public Usuario buscarUsuario(long id) throws GlobalException {		
		return (Usuario)dao.findById(id);
	}

	@Override
	public List<Usuario> buscarUsuarios(Usuario usuario) throws GlobalException {
		
		// Declara o Map que será passado para o Dao
		Map<String, Object> criterio = new HashMap<String, Object>();

		// Localiza o login
		if (usuario.getLogin() != null && !usuario.getLogin().trim().isEmpty()) {
			criterio.put(SEARCH_LOGIN, usuario.getLogin());
		}
		return dao.findByCriteria(criterio);
	}
	
	public void temLoginAtivoFuncionario(long id) throws GlobalException {
		// Declara o Map que será passado para o Dao
		criterio = new HashMap<String, Object>();

		// Define os parâmetros para a busca
		criterio.put(SEARCH_FUNCIONARIO, id);
		criterio.put(SEARCH_ATIVO, true);
				
		// Verifica se existe logins ativos para o funcionário selecionado
		if (dao.findByCriteria(criterio).size() > 0) {
			throw new LogicException("Existe login ativo para este funcionário");
		}
	}

	public IDao<Usuario> getDao() {
		return dao;
	}

	public void setDao(IDao<Usuario> dao) {
		this.dao = dao;
	}
*/
}
