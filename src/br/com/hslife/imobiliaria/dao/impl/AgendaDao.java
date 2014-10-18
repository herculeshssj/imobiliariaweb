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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.com.hslife.imobiliaria.dao.IAgendaDao;
import br.com.hslife.imobiliaria.model.Agenda;
import br.com.hslife.imobiliaria.model.Contrato;

public class AgendaDao extends HibernateGenericDao implements IAgendaDao {

	@Override
	public List listByExample(Object example) {
		Agenda a = (Agenda)example;
		List<Criterion> restricoes = new ArrayList<Criterion>();
				
		if (a.getCorretor() != null && a.getCorretor().getId() != null && a.getCorretor().getId() > 0) {
			restricoes.add(Restrictions.eq("corretor.id", a.getCorretor().getId()));
		}
				
		if (a.getImovel() != null && a.getImovel().getId() != null && a.getImovel().getId() > 0) {
			restricoes.add(Restrictions.eq("imovel.id", a.getImovel().getId()));
		}
				
		if (a.getData() != null) {
			restricoes.add(Restrictions.eq("data", a.getData()));
		}
		
		if (a.getHoraInicio() != null) {
			restricoes.add(Restrictions.eq("horaInicio", a.getHoraInicio()));
		}
				
		// Localiza os agendamentos que foram visitados
		restricoes.add(Restrictions.eq("visitado", a.getVisitado()));
		
		return super.listByCriteria(Agenda.class, restricoes);
	}
	
	@Override
	public List<Agenda> buscarAgendaCorretor(Long id) {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", id);
		return queryList("agenda.buscarAgendaCorretor", parametros);
	}
}
