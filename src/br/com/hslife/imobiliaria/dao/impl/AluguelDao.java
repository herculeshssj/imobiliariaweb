/*** 

    Copyright (c) 2011, 2014 Hércules S. S. José
    

    Este arquivo é parte do programa ImobiliáriaWeb.

    ImobiliáriaWeb é um software livre; você pode redistribui-lo e/ou 

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
    
    
    Para mais informações sobre o programa ImobiliáriaWeb e seus autores acesso o 

    endereço hslife.com.br, pelo e-mail contato@hslife.com.br ou escreva para 

    Hércules S. S. José, Av. Ministro Lafaeyte de Andrade, 1683 - Bl. 3 Apt 404, 

    Marco II - Nova Iguaçu, RJ, Brasil.

 ***/

package br.com.hslife.imobiliaria.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import br.com.hslife.imobiliaria.dao.IAluguelDao;
import br.com.hslife.imobiliaria.model.Aluguel;

public class AluguelDao extends HibernateGenericDao implements IAluguelDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Aluguel> buscarPorContrato(Long idContrato) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("idContrato", idContrato);
		return queryList("aluguel.buscarPorContrato", params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Aluguel> listByExample(Aluguel example) {
		List<Criterion> criterios = new ArrayList<Criterion>();
		if (example.getContrato() != null) {
			criterios.add(Restrictions.eq("contrato.id", example.getContrato().getId()));
		}
		if (example.getPeriodo() > 0) {
			criterios.add(Restrictions.eq("periodo", example.getPeriodo()));
		}
		if (example.getAno() > 0) {
			criterios.add(Restrictions.eq("ano", example.getAno()));
		}
		return listByCriteria(Aluguel.class, criterios);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Aluguel> listByContratoOrPeriodoOrAnoBeforeDataAndPago(Long idContrato, Integer periodo, Integer ano, Date data, Boolean pago) {
		List<Criterion> criterios = new ArrayList<Criterion>();
		if (idContrato != null) {
			criterios.add(Restrictions.eq("contrato.id", idContrato));
		}
		if (periodo != null) {
			criterios.add(Restrictions.eq("periodo", periodo));
		}
		if (ano != null) {
			criterios.add(Restrictions.eq("ano", ano));
		}
		if (data != null) {
			criterios.add(Restrictions.le("vencimento", data));
		}
		if (pago != null) {
			if (pago) {
				criterios.add(Restrictions.isNotNull("pagamento"));
			} else {
				criterios.add(Restrictions.isNull("pagamento"));
			}				
		}
		return listByCriteria(Aluguel.class, criterios);
	}
}
