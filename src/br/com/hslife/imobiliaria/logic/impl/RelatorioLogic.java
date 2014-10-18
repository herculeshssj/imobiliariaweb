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

package br.com.hslife.imobiliaria.logic.impl;

import java.util.List;

import br.com.hslife.imobiliaria.dao.IRelatorioDao;
import br.com.hslife.imobiliaria.db.HibernateUtility;
import br.com.hslife.imobiliaria.exception.BusinessException;
import br.com.hslife.imobiliaria.logic.IRelatorio;
import br.com.hslife.imobiliaria.model.Relatorio;

public class RelatorioLogic implements IRelatorio {
	
	IRelatorioDao dao;
	
	public RelatorioLogic(IRelatorioDao dao) {
		this.dao = dao;		
	}

	@Override
	public void cadastrar(Relatorio relatorio) throws BusinessException {
		try {
			HibernateUtility.beginTransaction();
			dao.save(relatorio);
			HibernateUtility.commitTransaction();
		} catch (Exception e) {
			HibernateUtility.rollbackTransaction();
			throw new BusinessException(e);
		}		
	}

	@Override
	public void editar(Relatorio relatorio) throws BusinessException {
		try {
			HibernateUtility.beginTransaction();
			dao.update(relatorio);
			HibernateUtility.commitTransaction();
		} catch (Exception e) {
			HibernateUtility.rollbackTransaction();
			throw new BusinessException(e);
		}		
	}

	@Override
	public void habilitar(Long id) throws BusinessException {		
		Relatorio c = (Relatorio) dao.getById(id, Relatorio.class);
		if (c.getAtivo()) {
			c.setAtivo(false);
		} else {
			c.setAtivo(true);
		}
		
		try {
			HibernateUtility.beginTransaction();
			dao.update(c);
			HibernateUtility.commitTransaction();
		} catch (Exception e) {
			HibernateUtility.rollbackTransaction();
			throw new BusinessException(e);
		}
	}

	@Override
	public Relatorio buscar(Long id) throws BusinessException {
		return (Relatorio) dao.getById(id, Relatorio.class);		
	}

	@Override
	public List<Relatorio> buscar(Relatorio relatorio) throws BusinessException {
		return dao.listByExample(relatorio);
	}

	@Override
	public List<Relatorio> buscarTodos() throws BusinessException {
		return dao.list(Relatorio.class);
	}
	
	public List<Relatorio> buscarTodosAtivos() throws BusinessException {
		return dao.buscarTodosAtivos();
	}
}
