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

package br.com.hslife.imobiliaria.dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;

import br.com.hslife.imobiliaria.dao.GenericDao;
import br.com.hslife.imobiliaria.db.HibernateUtility;

public class HibernateGenericDao implements GenericDao {

	@Override
	public void save(final Object objeto) {
		HibernateUtility.getSession().persist(objeto);
		HibernateUtility.getSession().flush();
		HibernateUtility.getSession().clear();
	}

	@Override
	public void update(final Object objeto) {
		HibernateUtility.getSession().merge(objeto);
		HibernateUtility.getSession().flush();
		HibernateUtility.getSession().clear();
	}

	@Override
	public void delete(final Object objeto) {
		HibernateUtility.getSession().delete(HibernateUtility.getSession().merge(objeto));
		HibernateUtility.getSession().flush();
		HibernateUtility.getSession().clear();
	}

	@Override
	public List list(Class clazz) {
		HibernateUtility.getSession().clear();
		return HibernateUtility.getSession().createCriteria(clazz).list();
	}

	@Override
	public List list(Class clazz, int firstResult, int maxResults) {
		HibernateUtility.getSession().clear();
		Criteria criteria = HibernateUtility.getSession().createCriteria(clazz);
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResults);
		return criteria.list();
	}

	@Override
	public List listByExample(Object example) {
		HibernateUtility.getSession().clear();
		Criteria criteria = HibernateUtility.getSession().createCriteria( example.getClass() );
		Example sample = Example.create( example );		
		sample.enableLike(MatchMode.ANYWHERE);
		sample.ignoreCase();
		criteria.add( sample );
		return criteria.list();
	}
	
	public List listByCriteria(Class classModel, List<Criterion> restrictions) {
		HibernateUtility.getSession().clear();
		List<Object> result = null;
		Criteria criteria = HibernateUtility.getSession().createCriteria(classModel);
		for (int i = 0; i < restrictions.size(); i++) {
			criteria.add(restrictions.get(i));
		}
		result = criteria.list();
		return result;
	}

	@Override
	public Object getById(Serializable id, Class clazz) {
		HibernateUtility.getSession().clear();
		return HibernateUtility.getSession().get(clazz, id);
	}
	
	public List queryList(String namedQuery) {
		HibernateUtility.getSession().clear();
		return HibernateUtility.getSession().getNamedQuery(namedQuery).list();
	}
	
	public List queryList(String namedQuery, Map<String, Object> params) {
		HibernateUtility.getSession().clear();
		Query query = HibernateUtility.getSession().getNamedQuery(namedQuery);
		for (String key : params.keySet()){
			if (params.get(key) instanceof String) {
				query.setString(key, (String)params.get(key));
			}
			if (params.get(key) instanceof Long) {
				query.setLong(key, (Long)params.get(key));
			}
			if (params.get(key) instanceof Integer) {
				query.setInteger(key, (Integer)params.get(key));
			}
			if (params.get(key) instanceof Boolean) {
				query.setBoolean(key, (Boolean)params.get(key));
			}
			if (params.get(key) instanceof Double) {
				query.setDouble(key, (Double)params.get(key));
			}
			if (params.get(key) instanceof Date) {
				query.setDate(key, (Date)params.get(key));
			}
		}
		return query.list();
	}
	
	
	public void queryNoResult(String namedQuery) {
		HibernateUtility.getSession().getNamedQuery(namedQuery).executeUpdate();
		HibernateUtility.getSession().flush();
		HibernateUtility.getSession().clear();
	}

	public void queryNoResult(String namedQuery, Map<String, Object> params) {
		Query query = HibernateUtility.getSession().getNamedQuery(namedQuery);
		for (String key : params.keySet()){
			if (params.get(key) instanceof String) {
				query.setString(key, (String)params.get(key));
			}
			if (params.get(key) instanceof Long) {
				query.setLong(key, (Long)params.get(key));
			}
			if (params.get(key) instanceof Integer) {
				query.setInteger(key, (Integer)params.get(key));
			}
			if (params.get(key) instanceof Boolean) {
				query.setBoolean(key, (Boolean)params.get(key));
			}
			if (params.get(key) instanceof Double) {
				query.setDouble(key, (Double)params.get(key));
			}
			if (params.get(key) instanceof Date) {
				query.setDate(key, (Date)params.get(key));
			}
		}
		query.executeUpdate();
		HibernateUtility.getSession().flush();
		HibernateUtility.getSession().clear();
	}
	
	public Object queryUnique(String namedQuery) {
		HibernateUtility.getSession().clear();
		return HibernateUtility.getSession().getNamedQuery(namedQuery).uniqueResult();
	}
	
	public Object queryUnique(String namedQuery, Map<String, Object> params) {
		HibernateUtility.getSession().clear();
		Query query = HibernateUtility.getSession().getNamedQuery(namedQuery);
		for (String key : params.keySet()){
			if (params.get(key) instanceof String) {
				query.setString(key, (String)params.get(key));
			}
			if (params.get(key) instanceof Long) {
				query.setLong(key, (Long)params.get(key));
			}
			if (params.get(key) instanceof Integer) {
				query.setInteger(key, (Integer)params.get(key));
			}
			if (params.get(key) instanceof Boolean) {
				query.setBoolean(key, (Boolean)params.get(key));
			}
			if (params.get(key) instanceof Double) {
				query.setDouble(key, (Double)params.get(key));
			}
			if (params.get(key) instanceof Date) {
				query.setDate(key, (Date)params.get(key));
			}
		}
		return query.uniqueResult();
	}
}
