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

import java.util.HashMap;
import java.util.Map;

import br.com.hslife.imobiliaria.dao.IGrupoDao;
import br.com.hslife.imobiliaria.model.Grupo;

public class GrupoDao extends HibernateGenericDao implements IGrupoDao {
	
	@Override
	public void save(Object objeto) {
		Map<String, Object> parametros = new HashMap<String, Object>();
		super.save(objeto);
		parametros.put("id", ((Grupo)objeto).getId());
		queryNoResult("grupo.defineGrupoPadraoQ1");
		queryNoResult("grupo.defineGrupoPadraoQ2", parametros);
	}
	
	@Override
	public void update(Object objeto) {
		Map<String, Object> parametros = new HashMap<String, Object>();
		super.update(objeto);
		parametros.put("id", ((Grupo)objeto).getId());
		queryNoResult("grupo.defineGrupoPadraoQ1");
		queryNoResult("grupo.defineGrupoPadraoQ2", parametros);
	}
	
	@Override
	public void delete(Object objeto) {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", ((Grupo)objeto).getId());
		queryNoResult("grupo.alteraGrupoPadrao", parametros);
		super.delete(objeto);
	}

}
