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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.hslife.imobiliaria.dao.IAluguelDao;
import br.com.hslife.imobiliaria.db.HibernateUtility;
import br.com.hslife.imobiliaria.exception.BusinessException;
import br.com.hslife.imobiliaria.logic.IAluguel;
import br.com.hslife.imobiliaria.model.Aluguel;

public class AluguelLogic implements IAluguel {

	IAluguelDao dao;

	public AluguelLogic(IAluguelDao dao) {
		this.dao = dao;
	}

	@Override
	public void cadastrar(Aluguel aluguel) throws BusinessException {
		try {
			
			// Verifica se o aluguel já existe
			if (dao.listByExample(aluguel) != null && !dao.listByExample(aluguel).isEmpty()) {
				throw new BusinessException("Já existe aluguel cadastrado com estas informações!");
			}
			
			HibernateUtility.beginTransaction();
			dao.save(aluguel);
			HibernateUtility.commitTransaction();
		} catch (Exception e) {
			HibernateUtility.rollbackTransaction();
			throw new BusinessException(e);
		}

	}

	@Override
	public void editar(Aluguel aluguel) throws BusinessException {
		try {
			
			// Verifica se o aluguel está com os mesmos dados de um já cadastrado
			List<Aluguel> alugueis = dao.listByExample(aluguel);
			for (Aluguel a : alugueis) {
				if (!a.equals(aluguel)) {
					if (a.getPeriodo().equals(aluguel.getPeriodo()) && a.getAno().equals(aluguel.getAno()) && a.getContrato().equals(aluguel.getContrato())) {
						throw new BusinessException("Já existe aluguel cadastrado com estas informações!");
					}
				}
			}
			
			HibernateUtility.beginTransaction();
			dao.update(aluguel);
			HibernateUtility.commitTransaction();
		} catch (Exception e) {
			HibernateUtility.rollbackTransaction();
			throw new BusinessException(e);
		}	

	}
	
	@Override
	public void excluir(Aluguel aluguel) throws BusinessException {
		try {
			HibernateUtility.beginTransaction();
			dao.delete(aluguel);
			HibernateUtility.commitTransaction();
		} catch (Exception e) {
			HibernateUtility.rollbackTransaction();
			throw new BusinessException(e);
		}	

	}

	@Override
	public Aluguel buscar(Long id) throws BusinessException {
		return (Aluguel) dao.getById(id, Aluguel.class);	
	}

	@Override
	public List<Aluguel> buscarPorContrato(Long idContrato) throws BusinessException {
		return dao.buscarPorContrato(idContrato);
	}
	
	@Override
	public List<Aluguel> buscar(Aluguel aluguel) throws BusinessException {
		return dao.listByExample(aluguel);
	}
	
	@Override
	public List<Aluguel> buscarPorExemplo(Aluguel aluguel) {
		List<Aluguel> resultado = new ArrayList<Aluguel>();
		
		Long idContrato = aluguel.getContrato() == null ? null : aluguel.getContrato().getId();
		Date data = null;
		Boolean pago = null;
		if (aluguel.getSituacaoAluguel().equals("TODOS_PAGOS")) {
			pago = true;
		}
		if (aluguel.getSituacaoAluguel().equals("TODOS_ATRASO")) {
			pago = false;
			data = new Date();
		}
		if (aluguel.getSituacaoAluguel().equals("ATRASO_10DIAS")) {
			pago = false;
			Calendar temp = Calendar.getInstance();
			temp.add(Calendar.DAY_OF_YEAR, -10);
			data = temp.getTime();
			for (Aluguel a : dao.listByNomeLocatarioOrContratoOrPeriodoOrAnoBeforeDataAndPago(aluguel.getNomeLocatario(), idContrato, aluguel.getPeriodo(), aluguel.getAno(), data, pago)) {
				if (a.getDiasAtrasados() > 10 && a.getDiasAtrasados() <= 20) {
					resultado.add(a);
				}
			}
		} else 
		if (aluguel.getSituacaoAluguel().equals("ATRASO_20DIAS")) {
			pago = false;
			Calendar temp = Calendar.getInstance();
			temp.add(Calendar.DAY_OF_YEAR, -20);
			data = temp.getTime();
			for (Aluguel a : dao.listByNomeLocatarioOrContratoOrPeriodoOrAnoBeforeDataAndPago(aluguel.getNomeLocatario(), idContrato, aluguel.getPeriodo(), aluguel.getAno(), data, pago)) {
				if (a.getDiasAtrasados() > 20 && a.getDiasAtrasados() <= 30) {
					resultado.add(a);
				}
			}
		} else
		if (aluguel.getSituacaoAluguel().equals("ATRASO_30DIAS")) {
			pago = false;
			Calendar temp = Calendar.getInstance();
			temp.add(Calendar.DAY_OF_YEAR, -30);
			data = temp.getTime();
			for (Aluguel a : dao.listByNomeLocatarioOrContratoOrPeriodoOrAnoBeforeDataAndPago(aluguel.getNomeLocatario(), idContrato, aluguel.getPeriodo(), aluguel.getAno(), data, pago)) {
				if (a.getDiasAtrasados() > 30) {
					resultado.add(a);
				}
			}
		} else {
			resultado = dao.listByNomeLocatarioOrContratoOrPeriodoOrAnoBeforeDataAndPago(aluguel.getNomeLocatario(), idContrato, aluguel.getPeriodo(), aluguel.getAno(), data, pago);
		}
		return resultado;
	}
}