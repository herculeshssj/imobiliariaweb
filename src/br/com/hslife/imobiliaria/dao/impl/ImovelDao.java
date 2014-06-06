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

package br.com.hslife.imobiliaria.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.com.hslife.imobiliaria.dao.IImovelDao;
import br.com.hslife.imobiliaria.model.Imovel;

public class ImovelDao extends HibernateGenericDao implements IImovelDao {
	
	@Override
	public List<Imovel> buscarTodosAtivos() {
		return queryList("imovel.buscarTodosAtivos");
	}

	@Override
	public List<Imovel> buscarPorNumRegistro(String numRegistro) {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("numRegistro", numRegistro);
		return queryList("imovel.buscarPorNumRegistro", parametros);
	}

	@Override
	public List listByExample(Object example) {
		Imovel i = (Imovel)example;
		List<Criterion> restricoes = new ArrayList<Criterion>();
		if (i.getNumRegistro() != null && !i.getNumRegistro().isEmpty()) {
			restricoes.add(Restrictions.ilike("numRegistro", i.getNumRegistro(), MatchMode.ANYWHERE));
		}
		if (i.getLocador() != null && i.getLocador().getId() != null && i.getLocador().getId() > 0) {
			restricoes.add(Restrictions.eq("locador.id", i.getLocador().getId()));
		}
		if (i.getValor() != null) {
			restricoes.add(Restrictions.ge("valor", i.getValor()));
		}
		if (i.getTipoImovel() != null && !i.getTipoImovel().isEmpty()) {
			restricoes.add(Restrictions.ilike("tipoImovel", i.getTipoImovel(), MatchMode.ANYWHERE));
		}
		if (i.getSituacaoImovel() != null && !i.getSituacaoImovel().isEmpty()) {
			restricoes.add(Restrictions.ilike("situacaoImovel", i.getSituacaoImovel(), MatchMode.ANYWHERE));
		}
		if (i.getSite() != null) {
			restricoes.add(Restrictions.eq("site", i.getSite()));
		}
		if (i.getAtivo() != null) {
			restricoes.add(Restrictions.eq("ativo", i.getAtivo()));
		}
		return super.listByCriteria(Imovel.class, restricoes);
	}
}
