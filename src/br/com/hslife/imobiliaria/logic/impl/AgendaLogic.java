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

package br.com.hslife.imobiliaria.logic.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.hslife.imobiliaria.dao.IAgendaDao;
import br.com.hslife.imobiliaria.db.HibernateUtility;
import br.com.hslife.imobiliaria.exception.BusinessException;
import br.com.hslife.imobiliaria.logic.IAgenda;
import br.com.hslife.imobiliaria.model.Agenda;

public class AgendaLogic implements IAgenda {
	
	IAgendaDao dao;
	
	public AgendaLogic(IAgendaDao dao) {
		this.dao = dao;
	}

	@Override
	@SuppressWarnings("deprecation")
	public void agendar(Agenda agenda) throws BusinessException {
		// Ajusta a data e hora da agenda para poder passar na validação
		Calendar dataExtraida = Calendar.getInstance();
		dataExtraida.set(agenda.getData().getYear() + 1900, agenda.getData().getMonth(), agenda.getData().getDate(), agenda.getHoraInicio(), 0, 0);		
		agenda.setData(dataExtraida.getTime());
		
		// Verifica se existem agendamento equivalentes ao informado
		if (buscar(agenda).size() > 0) {
			throw new BusinessException("Existem agendamentos marcados com as informações fornecidas.");
		}
		if (agenda.getData().before(new Date())) {
			throw new BusinessException("Não é possível marcar com data anterior a data atual.");
		}
		if (agenda.getHoraFim() < agenda.getHoraInicio()) {
			throw new BusinessException("Hora de término não pode ser anterior a hora de início.");
		}
		try {
			// Verifica se existem agendamento equivalentes ao informado
			if (buscar(agenda).size() != 0) 
				throw new BusinessException("Existem agendamentos marcados com as informações fornecidas.");
			HibernateUtility.beginTransaction();
			dao.save(agenda);
			HibernateUtility.commitTransaction();
		} catch (Exception e) {
			HibernateUtility.rollbackTransaction();
			throw new BusinessException(e);
		}		
	}

	@Override
	@SuppressWarnings("deprecation")
	public void remarcar(Agenda agenda) throws BusinessException {
		// Ajusta a data e hora da agenda para poder passar na validação
		Calendar dataExtraida = Calendar.getInstance();
		dataExtraida.set(agenda.getData().getYear() + 1900, agenda.getData().getMonth(), agenda.getData().getDate(), agenda.getHoraInicio(), 0, 0);		
		agenda.setData(dataExtraida.getTime());
		
		// Verifica se existem agendamento equivalentes ao informado
		if (buscar(agenda).size() > 1) {
			throw new BusinessException("Existem agendamentos marcados com as informações fornecidas.");
		}
		if (agenda.getData().before(new Date())) {
			throw new BusinessException("Não é possível remarcar com data anterior a data atual.");
		}
		if (agenda.getHoraFim() < agenda.getHoraInicio()) {
			throw new BusinessException("Hora de término não pode ser anterior a hora de início.");
		}
		try {
			HibernateUtility.beginTransaction();
			dao.update(agenda);
			HibernateUtility.commitTransaction();
		} catch (Exception e) {
			HibernateUtility.rollbackTransaction();
			throw new BusinessException(e);
		}		
	}

	@Override
	public void desmarcar(Agenda agenda) throws BusinessException {
		try {
			HibernateUtility.beginTransaction();
			dao.delete(agenda);
			HibernateUtility.commitTransaction();
		} catch (Exception e) {
			HibernateUtility.rollbackTransaction();
			throw new BusinessException(e);
		}		
	}

	@Override
	public void registrarVisita(Agenda agenda) throws BusinessException {
		if (agenda.getVisitado()) {
			agenda.setVisitado(false);			
		} else {
			agenda.setVisitado(true);			
		}
		
		try {
			HibernateUtility.beginTransaction();
			dao.update(agenda);
			HibernateUtility.commitTransaction();
		} catch (Exception e) {
			HibernateUtility.rollbackTransaction();
			throw new BusinessException(e);
		}// TODO Auto-generated method stub
		
	}

	@Override
	public List<Agenda> buscar(Agenda agenda) throws BusinessException {
		// TODO Auto-generated method stub
		return dao.listByExample(agenda);
	}

	@Override
	public Agenda buscar(Long id) throws BusinessException {
		return (Agenda) dao.getById(id, Agenda.class);
	}

	@Override
	public List<Agenda> buscarTodos() throws BusinessException {
		return dao.list(Agenda.class);
	}
	
	@Override
	public List<Agenda> buscarAgendaCorretor(Long id) throws BusinessException {
		return dao.buscarAgendaCorretor(id);
	}
	
	/*
	
	private IDao dao;
	
	Map<String, Object> criterio;
	
	public AgendaLogic(IDao dao) {
		this.dao = dao;		
	}
	
	// Esta classe realiza os agendamentos de visitas aos imóveis
	
	public void agendar(Agenda agenda) throws GlobalException {
		// Verifica a validade do agendamento
		validaAgendamento(agenda);
		
		// Recebe o objeto agenda populado para cadastrar
		dao.save(agenda);
	}
	
	public void remarcar(Agenda agenda) throws GlobalException {
		// Verifica a validade do agendamento
		validaAgendamento(agenda);
		
		// Recebe o objeto agenda com os dados alterados e prontos para salvar
		dao.update(agenda);
	}
	
	public void desmarcar(Agenda agenda) throws GlobalException {
		// Recebe o objeto agenda para excluir da base
		dao.delete(agenda);
	}
	
	public void registrarVisita(Agenda agenda) throws GlobalException {
		// Recebe o agendamento para alterar o estado dele		
		if (agenda.isVisitado()) {
			agenda.setVisitado(false);
			dao.update(agenda);
		} else {
			agenda.setVisitado(true);
			dao.update(agenda);
		}		
	}
	
	public List<Agenda> buscarAgendamentos(Agenda agenda) throws GlobalException {
		// Retorna a lista de agendamentos encontrados a partir dos dados informados
		
		// Declara o Map que será passado para o Dao
		criterio = new HashMap<String, Object>();
	
		// Verifica cada campo em busca de valores válidos para a busca
		if (agenda.getData() != null) {
			criterio.put(SEARCH_DATA, agenda.getData());
		}
		if (agenda.getCorretor() != null && !(agenda.getCorretor().getId() == 0)) {
			criterio.put(SEARCH_CORRETOR, agenda.getCorretor().getId());
		}
		if (agenda.getImovel() != null && !(agenda.getImovel().getId() == 0)) {
			criterio.put(SEARCH_IMOVEL, agenda.getImovel().getId());
		}
		if (agenda.getNomeCliente() != null  && !agenda.getNomeCliente().trim().isEmpty()) {
			criterio.put(SEARCH_CLIENTE, agenda.getNomeCliente());
		}
		
		criterio.put(SEARCH_VISITADO, agenda.isVisitado());
		
		return dao.findByCriteria(criterio);
		
	}
	
	public Agenda buscarAgenda(long id) throws GlobalException {
		return (Agenda)dao.findById(id);
	}
	
	public void corretorTemAgendamentos(long id) throws GlobalException {
		// Declara o Map que será passado para o Dao
		criterio = new HashMap<String, Object>();
		
		// Seta os campos necessários
		criterio.put(SEARCH_CORRETOR, id);
		criterio.put(SEARCH_VISITADO, false);
		
		if (dao.findByCriteria(criterio).size() > 0) {
			throw new LogicException("Corretor possui agendamentos marcados!");
		}
			
		
	}
	
	private void validaAgendamento(Agenda agenda) throws GlobalException {
		
		// Declara o Map que será passado para o Dao
		criterio = new HashMap<String, Object>();
		
		// Valida a hora de início e hora de término
		if (agenda.getHoraFim() < agenda.getHoraInicio()) {
			throw new LogicException("Horário de término não deve ser menor que horário de início");
		}
		
		// Valida se a data marcada é anterior a data de hora
		Date hoje = new Date();
		hoje.setDate(hoje.getDate() - 1);
		if (agenda.getData().before(hoje)) {
			throw new LogicException("Não é possível agendar com data anterior ao dia de hoje");
		}
		
		// Verifica se o agendamento está conflitando com outro existente
		// Foi necessário incluir este método pois a busca por Criteria não está retornando resultado
		//if ((new AgendaDao().existeAgendamento(agenda)).size() > 0) {
			//throw new LogicException("Já existe agendamento marcado para este dia e horário");
		//}
		
		// Verifica se o agendamento está conflitando com outro existente para o imóvel
		criterio.put(SEARCH_DATA, agenda.getData());
		criterio.put(SEARCH_HORAINICIO, agenda.getHoraInicio());
		criterio.put(SEARCH_IMOVEL, agenda.getImovel().getId());
		criterio.put(SEARCH_VISITADO, false);
		if (dao.findByCriteria(criterio).size() > 0) {
			throw new LogicException("Já existe agendamento neste dia e horário para o imóvel selecionado");
		}
		
		// Limpa o Map
		criterio = new HashMap<String, Object>();
		
		// Verifica se o agendamento está conflitando com outro existente para o corretor
		criterio.put(SEARCH_DATA, agenda.getData());
		criterio.put(SEARCH_HORAINICIO, agenda.getHoraInicio());
		criterio.put(SEARCH_CORRETOR, agenda.getCorretor().getId());
		criterio.put(SEARCH_VISITADO, false);
		if (dao.findByCriteria(criterio).size() > 0) {
			throw new LogicException("Já existe agendamento neste dia e horário para o corretor selecionado");
		}
	}
*/
}
