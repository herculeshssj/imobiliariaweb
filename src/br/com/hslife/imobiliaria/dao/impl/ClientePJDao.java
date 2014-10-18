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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;

import br.com.hslife.imobiliaria.dao.IClientePJDao;
import br.com.hslife.imobiliaria.db.HibernateUtility;
import br.com.hslife.imobiliaria.model.ClientePJ;
import br.com.hslife.imobiliaria.model.Socio;

public class ClientePJDao extends HibernateGenericDao implements IClientePJDao {
	
	@Override
	public void save(Object objeto) {		
		for (Socio s : ((ClientePJ)objeto).getSocios()) {
			if (s != null) {
				super.save(s);
			}			
		}
		super.save(objeto);
	}
	
	@Override
	public void update(Object objeto) {
		for (Socio s : ((ClientePJ)objeto).getSocios()) {
			if (s != null && s.getId() != null && s.getId() != 0) {
				super.update(s);
			} else if (s != null) {
				super.save(s);
			}
		}
		super.update(objeto);
	}
	
	@Override
	public List<ClientePJ> buscarPorCNPJ(String cnpj) {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("cnpj", cnpj);
		return queryList("clientePJ.buscarPorCNPJ", parametros);
	}
	
	@Override
	public List<ClientePJ> buscarTodosAtivos() {
		return queryList("clientePJ.buscarTodosAtivos");
	}

	@Override
	public List<ClientePJ> list(Class clazz) {
		return HibernateUtility.getSession().createCriteria(clazz).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}
}
