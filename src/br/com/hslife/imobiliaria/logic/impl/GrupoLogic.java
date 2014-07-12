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

import br.com.hslife.imobiliaria.dao.IGrupoDao;
import br.com.hslife.imobiliaria.db.HibernateUtility;
import br.com.hslife.imobiliaria.exception.BusinessException;
import br.com.hslife.imobiliaria.logic.IGrupo;
import br.com.hslife.imobiliaria.model.Grupo;

public class GrupoLogic implements IGrupo {
	
	IGrupoDao dao;
	
	public GrupoLogic(IGrupoDao dao) {
		this.dao = dao;
	}

	@Override
	public void cadastrar(Grupo grupo) throws BusinessException {
		try {
			HibernateUtility.beginTransaction();
			dao.save(grupo);
			HibernateUtility.commitTransaction();
		} catch (Exception e) {
			HibernateUtility.rollbackTransaction();
			throw new BusinessException(e);
		}		
	}

	@Override
	public void editar(Grupo grupo) throws BusinessException {
		try {
			HibernateUtility.beginTransaction();
			dao.update(grupo);
			HibernateUtility.commitTransaction();
		} catch (Exception e) {
			HibernateUtility.rollbackTransaction();
			throw new BusinessException(e);
		}		
	}

	@Override
	public void excluir(Grupo grupo) throws BusinessException {
		try {
			HibernateUtility.beginTransaction();
			dao.delete(grupo);
			HibernateUtility.commitTransaction();
		} catch (Exception e) {
			HibernateUtility.rollbackTransaction();
			throw new BusinessException(e);
		}
		
	}

	@Override
	public Grupo buscar(Long id) throws BusinessException {
		return (Grupo) dao.getById(id, Grupo.class);
	}

	@Override
	public List<Grupo> buscar(Grupo grupo) throws BusinessException {
		return dao.listByExample(grupo);
	}

	@Override
	public List<Grupo> buscarTodos() throws BusinessException {
		return dao.list(Grupo.class);
	}
	
	@Override
	public List<Grupo> buscarPorDescricao(String descricao)
			throws BusinessException {
		return dao.findByDescricao(descricao);
	}
}
