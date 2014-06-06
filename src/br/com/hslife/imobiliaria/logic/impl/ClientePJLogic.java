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

import br.com.hslife.imobiliaria.dao.IClientePJDao;
import br.com.hslife.imobiliaria.db.HibernateUtility;
import br.com.hslife.imobiliaria.exception.BusinessException;
import br.com.hslife.imobiliaria.logic.IClientePJ;
import br.com.hslife.imobiliaria.model.ClientePJ;

public class ClientePJLogic implements IClientePJ {
	
	IClientePJDao dao;
	
	public ClientePJLogic(IClientePJDao dao) {
		this.dao = dao;		
	}

	@Override
	public void cadastrar(ClientePJ clientePJ) throws BusinessException {
		// Verifica se o CPF já está cadastrado
		if (dao.buscarPorCNPJ(clientePJ.getCnpj()).size() > 0) {
			throw new BusinessException("CNPJ já cadastrado!");
		}
		
		try {
			HibernateUtility.beginTransaction();
			dao.save(clientePJ);
			HibernateUtility.commitTransaction();
		} catch (Exception e) {
			HibernateUtility.rollbackTransaction();
			throw new BusinessException(e);
		}		
	}

	@Override
	public void editar(ClientePJ clientePJ) throws BusinessException {
		// Verifica se o CPF já está cadastrado
		if (dao.buscarPorCNPJ(clientePJ.getCnpj()).size() > 1) {
			throw new BusinessException("CNPJ já cadastrado!");
		}
		
		try {
			HibernateUtility.beginTransaction();
			dao.update(clientePJ);
			HibernateUtility.commitTransaction();
		} catch (Exception e) {
			HibernateUtility.rollbackTransaction();
			throw new BusinessException(e);
		}		
	}

	@Override
	public void habilitar(Long id) throws BusinessException {		
		ClientePJ c = (ClientePJ) dao.getById(id, ClientePJ.class);
		if (c.getAtivo()) {
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
	public ClientePJ buscar(Long id) throws BusinessException {
		return (ClientePJ) dao.getById(id, ClientePJ.class);		
	}

	@Override
	public List<ClientePJ> buscar(ClientePJ clientePJ) throws BusinessException {
		return dao.listByExample(clientePJ);
	}

	@Override
	public List<ClientePJ> buscarTodos() throws BusinessException {
		return dao.list(ClientePJ.class);
	}
	
	@Override
	public List<ClientePJ> buscarTodosAtivos() throws BusinessException {
		return dao.buscarTodosAtivos();
	}
	
	@Override
	public List<ClientePJ> buscarPorCNPJ(String cnpj) throws BusinessException {
		return dao.buscarPorCNPJ(cnpj);
	}
}
