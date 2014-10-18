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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.com.hslife.imobiliaria.dao.IContratoDao;
import br.com.hslife.imobiliaria.model.Contrato;

public class ContratoDao extends HibernateGenericDao implements IContratoDao {
	
	@Override
	public void save(Object objeto) {
		Contrato c = (Contrato)objeto;
		c.setDataCadastro(new Date());
		super.save(c);
	}

	@Override
	public List<Contrato> buscarPorNumContrato(String numContrato) {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("numContrato", numContrato);
		return queryList("contrato.buscarPorNumContrato", parametros);
	}
	
	public List<Contrato> buscarTodosEmVigor() {
		return queryList("contrato.buscarTodosEmVigor");
	}

	@Override
	public void vigorarContrato(Long id) {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", id);
		parametros.put("data", new Date());
		queryNoResult("contrato.vigorarContrato", parametros);				
	}

	@Override
	public void renovarContrato(Long id) {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", id);
		parametros.put("data", new Date());
		queryNoResult("contrato.renovarContrato", parametros);			
	}

	@Override
	public void encerrarContrato(Long id) {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", id);
		parametros.put("data", new Date());
		queryNoResult("contrato.encerrarContrato", parametros);			
	}
	
	@Override
	public List<Contrato> contratosEmVigorImovel(Long id) {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("idImovel", id);		
		return queryList("contrato.contratosEmVigorImovel", parametros);	
	}
	
	@Override
	public List listByExample(Object example) {
		Contrato c = (Contrato)example;
		List<Criterion> restricoes = new ArrayList<Criterion>();
		if (c.getNumContrato() != null && !c.getNumContrato().isEmpty()) {
			restricoes.add(Restrictions.ilike("numContrato", c.getNumContrato(), MatchMode.ANYWHERE));
		}
		if (c.getInicioContrato() != null) {
			restricoes.add(Restrictions.ge("inicioContrato", c.getInicioContrato()));
		}
		if (c.getTerminoContrato() != null) {
			restricoes.add(Restrictions.le("terminoContrato", c.getTerminoContrato()));
		}
		if (c.getImovel() != null && c.getImovel().getId() != null && c.getImovel().getId() > 0) {
			restricoes.add(Restrictions.eq("imovel.id", c.getImovel().getId()));
		}
		if (c.getLocatario() != null && c.getLocatario().getId() != null && c.getLocatario().getId() > 0) {
			restricoes.add(Restrictions.eq("locatario.id", c.getLocatario().getId()));
		}
		if (c.getLocatarioPJ() != null && c.getLocatarioPJ().getId() != null && c.getLocatarioPJ().getId() > 0) {
			restricoes.add(Restrictions.eq("locatarioPJ.id", c.getLocatarioPJ().getId()));
		}
		if (c.getFiador() != null && c.getFiador().getId() != null && c.getFiador().getId() > 0) {
			restricoes.add(Restrictions.eq("fiador.id", c.getFiador().getId()));
		}
		if (c.getSituacao() != null && c.getSituacao() > 0) {
			restricoes.add(Restrictions.eq("situacao", c.getSituacao()));
		}
		return super.listByCriteria(Contrato.class, restricoes);
	}
	
	@Override
	public void vigorarEncerrarContratos() {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("data", new Date());		
		queryNoResult("contrato.vigorarContratosAutomaticamente", parametros);
		queryNoResult("contrato.encerrarContratosAutomaticamente", parametros);
	}
}