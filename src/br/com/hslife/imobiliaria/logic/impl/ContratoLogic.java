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

import br.com.hslife.imobiliaria.dao.IContratoDao;
import br.com.hslife.imobiliaria.db.HibernateUtility;
import br.com.hslife.imobiliaria.exception.BusinessException;
import br.com.hslife.imobiliaria.factory.LogicFactory;
import br.com.hslife.imobiliaria.logic.IContrato;
import br.com.hslife.imobiliaria.model.Contrato;
import br.com.hslife.imobiliaria.util.Util;

public class ContratoLogic implements IContrato {
	
	IContratoDao dao;
	
	public ContratoLogic(IContratoDao dao) {
		this.dao = dao;		
	}

	@Override
	public void cadastrar(Contrato contrato) throws BusinessException {
		if (contrato.getNumContrato() == null || contrato.getNumContrato().isEmpty()) {
			contrato.setNumContrato(String.format("%06d", buscarTodos().size() + 1));
		}
		
		contrato.setTerminoContrato(Util.calculaDataTermino(contrato.getInicioContrato(), contrato.getPrazo()));
		
		try {
			HibernateUtility.beginTransaction();
			dao.save(contrato);
			HibernateUtility.commitTransaction();
		} catch (Exception e) {
			HibernateUtility.rollbackTransaction();
			throw new BusinessException(e);
		}
		
		LogicFactory.createImovelLogic().habilitar(contrato.getImovel().getId());
	}

	@Override
	public void editar(Contrato contrato) throws BusinessException {
		
		contrato.setTerminoContrato(Util.calculaDataTermino(contrato.getInicioContrato(), contrato.getPrazo()));
		
		try {
			HibernateUtility.beginTransaction();
			dao.update(contrato);
			HibernateUtility.commitTransaction();
		} catch (Exception e) {
			HibernateUtility.rollbackTransaction();
			throw new BusinessException(e);
		}
	}

	@Override
	public Contrato buscar(Long id) throws BusinessException {
		return (Contrato) dao.getById(id, Contrato.class);	
	}

	@Override
	public List<Contrato> buscar(Contrato contrato) throws BusinessException {
		return dao.listByExample(contrato);
	}

	@Override
	public List<Contrato> buscarTodos() throws BusinessException {
		return dao.list(Contrato.class); 
	}

	@Override
	public List<Contrato> buscarPorNumContrato(String numContrato)	throws BusinessException {
		return dao.buscarPorNumContrato(numContrato);
	}

	@Override
	public void vigorarContrato(Long id) throws BusinessException {
		try {
			HibernateUtility.beginTransaction();
			dao.vigorarContrato(id);
			HibernateUtility.commitTransaction();
		} catch (Exception e) {
			HibernateUtility.rollbackTransaction();
			throw new BusinessException(e);
		}
	}

	@Override
	public void renovarContrato(Long id) throws BusinessException {
		try {
			HibernateUtility.beginTransaction();
			dao.renovarContrato(id);
			HibernateUtility.commitTransaction();
		} catch (Exception e) {
			HibernateUtility.rollbackTransaction();
			throw new BusinessException(e);
		}		
	}

	@Override
	public void encerrarContrato(Long id) throws BusinessException {
		try {
			HibernateUtility.beginTransaction();
			dao.encerrarContrato(id);
			HibernateUtility.commitTransaction();
		} catch (Exception e) {
			HibernateUtility.rollbackTransaction();
			throw new BusinessException(e);
		}		
	}

	@Override
	public List<Contrato> contratosEmVigorImovel(Long id)
			throws BusinessException {
		return dao.contratosEmVigorImovel(id);
	}
	
	@Override
	public void vigorarEncerrarContratos() throws BusinessException {
		dao.vigorarEncerrarContratos();		
	}
}
