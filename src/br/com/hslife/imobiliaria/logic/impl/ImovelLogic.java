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

import br.com.hslife.imobiliaria.dao.IImovelDao;
import br.com.hslife.imobiliaria.db.HibernateUtility;
import br.com.hslife.imobiliaria.exception.BusinessException;
import br.com.hslife.imobiliaria.factory.LogicFactory;
import br.com.hslife.imobiliaria.logic.IImovel;
import br.com.hslife.imobiliaria.model.Imovel;

public class ImovelLogic implements IImovel {
	
	IImovelDao dao;
	
	public ImovelLogic(IImovelDao dao) {
		this.dao = dao;		
	}

	@Override
	public void cadastrar(Imovel imovel) throws BusinessException {
		// Gera um novo número de registro para associar ao imóvel caso não tenha sido informado
		if (imovel.getNumRegistro() == null || imovel.getNumRegistro().isEmpty()) {
			imovel.setNumRegistro(String.format("%06d", buscarTodos().size() + 1));
		}		
		
		try {
			HibernateUtility.beginTransaction();
			dao.save(imovel);
			HibernateUtility.commitTransaction();
		} catch (Exception e) {
			HibernateUtility.rollbackTransaction();
			throw new BusinessException(e);
		}		
	}

	@Override
	public void editar(Imovel imovel) throws BusinessException {
		
		try {
			HibernateUtility.beginTransaction();
			dao.update(imovel);
			HibernateUtility.commitTransaction();
		} catch (Exception e) {
			HibernateUtility.rollbackTransaction();
			throw new BusinessException(e);
		}		
	}

	@Override
	public void habilitar(Long id) throws BusinessException {		
		Imovel i = (Imovel) dao.getById(id, Imovel.class);
		if (i.getAtivo()) {
			i.setAtivo(false);
		} else {
			if (LogicFactory.createContratoLogic().contratosEmVigorImovel(id).size() >= 1) 
				throw new BusinessException("Existem contratos em vigor para este imóvel");
			 else 
				i.setAtivo(true);
		}
		
		try {
			HibernateUtility.beginTransaction();
			dao.update(i);
			HibernateUtility.commitTransaction();
		} catch (Exception e) {
			HibernateUtility.rollbackTransaction();
			throw new BusinessException(e);
		}
	}

	@Override
	public Imovel buscar(Long id) throws BusinessException {
		return (Imovel) dao.getById(id, Imovel.class);		
	}

	@Override
	public List<Imovel> buscar(Imovel imovel) throws BusinessException {
		return dao.listByExample(imovel);
	}

	@Override
	public List<Imovel> buscarTodos() throws BusinessException {
		return dao.list(Imovel.class);
	}
	
	public List<Imovel> buscarTodosAtivos() throws BusinessException {
		return dao.buscarTodosAtivos();
	}
	
	public List<Imovel> buscarPorNumRegistro(String numRegistro) throws BusinessException {
		return dao.buscarPorNumRegistro(numRegistro);
	}

}
