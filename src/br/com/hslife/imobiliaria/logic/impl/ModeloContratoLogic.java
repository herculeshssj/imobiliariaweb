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

package br.com.hslife.imobiliaria.logic.impl;

import java.util.List;

import br.com.hslife.imobiliaria.dao.IModeloContratoDao;
import br.com.hslife.imobiliaria.db.HibernateUtility;
import br.com.hslife.imobiliaria.exception.BusinessException;
import br.com.hslife.imobiliaria.logic.IModeloContrato;
import br.com.hslife.imobiliaria.model.ModeloContrato;

public class ModeloContratoLogic implements IModeloContrato {
	
	IModeloContratoDao dao;
	
	public ModeloContratoLogic(IModeloContratoDao dao) {
		this.dao = dao;		
	}

	@Override
	public void cadastrar(ModeloContrato modeloContrato) throws BusinessException {
		try {
			HibernateUtility.beginTransaction();
			dao.save(modeloContrato);
			HibernateUtility.commitTransaction();
		} catch (Exception e) {
			HibernateUtility.rollbackTransaction();
			throw new BusinessException(e);
		}		
	}

	@Override
	public void editar(ModeloContrato modeloContrato) throws BusinessException {
		try {
			HibernateUtility.beginTransaction();
			dao.update(modeloContrato);
			HibernateUtility.commitTransaction();
		} catch (Exception e) {
			HibernateUtility.rollbackTransaction();
			throw new BusinessException(e);
		}		
	}

	@Override
	public void habilitar(Long id) throws BusinessException {		
		ModeloContrato c = (ModeloContrato) dao.getById(id, ModeloContrato.class);
		if (c.isAtivo()) {
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
	public ModeloContrato buscar(Long id) throws BusinessException {
		return (ModeloContrato) dao.getById(id, ModeloContrato.class);		
	}

	@Override
	public List<ModeloContrato> buscar(ModeloContrato modeloContrato) throws BusinessException {
		return dao.listByExample(modeloContrato);
	}

	@Override
	public List<ModeloContrato> buscarTodos() throws BusinessException {
		return dao.list(ModeloContrato.class);
	}
	
	public List<ModeloContrato> buscarTodosAtivos() throws BusinessException {
		return dao.buscarTodosAtivos();
	}
}
