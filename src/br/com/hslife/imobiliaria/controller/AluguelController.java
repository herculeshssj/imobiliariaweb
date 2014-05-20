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

package br.com.hslife.imobiliaria.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import br.com.hslife.imobiliaria.exception.BusinessException;
import br.com.hslife.imobiliaria.factory.LogicFactory;
import br.com.hslife.imobiliaria.logic.IAluguel;
import br.com.hslife.imobiliaria.model.Aluguel;
import br.com.hslife.imobiliaria.model.Contrato;
import br.com.hslife.imobiliaria.model.FormaPagamento;

public class AluguelController extends GenericController {
	
	/*** Atributos da classe ***/
	
	// Modelo
	Aluguel aluguel;
	
	// Lógica de negócio
	IAluguel logic;
	
	// Listas
	List<Aluguel> listaAluguel;
	
	// Armazena o id do objeto de modelo
	Long idAluguel;
	Long idContrato;
	Long idFormaPagamento;
	
	/*** Construtor ***/	
	
	public AluguelController() {
		aluguel = new Aluguel();
		logic = LogicFactory.createAluguelLogic();
		listaAluguel = new ArrayList<Aluguel>();
		
		// Define as permissões para este controller
		canAdd = isAuthorized("aluguel", "add");
		canEdit = isAuthorized("aluguel", "edit");
		canDelete = isAuthorized("aluguel", "delete");
		canList = isAuthorized("aluguel", "list");
		canView = isAuthorized("aluguel", "view");
	}
	
	/*** Métodos abstratos de AbstractController ***/

	@Override
	protected void clearVariables() {		
		aluguel = new Aluguel();
		listaAluguel = new ArrayList<Aluguel>();
		idAluguel = null;
		idContrato = null;
		dadosModelo = new ListDataModel(listaAluguel);
	}

	@Override
	public void simpleSearch() {		
		try {
			if (idContrato != null) {
				dadosModelo = new ListDataModel(logic.buscarPorContrato(idContrato));
			}
			viewMessage("Busca realizada com sucesso!");
		} catch (BusinessException be) {
			viewMessage("Erro ao buscar: " +  be.getMessage());
		}
	}

	/*** Métodos concretos sobrescritos ***/
	
	@Override
	public String cancelAction() {
		clearVariables();
		return super.cancelAction();
	}
	
	@Override
	public String addView() {
		clearVariables();
		return super.addView();
	}
	
	@Override
	public String add() {
		String retorno = null;
		try {
			aluguel.setContrato(LogicFactory.createContratoLogic().buscar(idContrato));
			logic.cadastrar(aluguel);
			viewMessage("Registro cadastrado com sucesso!");
			clearVariables();
			retorno = super.add();
		} catch (BusinessException be) {
			viewMessage("Erro ao salvar: " + be.getMessage(), "frmAluguel");
		}
		return retorno;
	}

	@Override
	public String editView() {
		Aluguel a = (Aluguel) dadosModelo.getRowData();
		try {
			aluguel = logic.buscar(a.getId());
		} catch (BusinessException be) {
			viewMessage("Erro ao buscar: " + be.getMessage());
		}
		return super.editView();
	} 
	
	@Override
	public String edit() {
		String retorno = null;
		try {
			aluguel.setContrato(LogicFactory.createContratoLogic().buscar(idContrato));
			logic.editar(aluguel);
			viewMessage("Registro editado com sucesso!");
			clearVariables();
			retorno = super.edit();
		} catch (BusinessException be) {
			viewMessage("Erro ao editar: " + be.getMessage(), "frmAluguel");
		}
		return retorno;
	}
	
	/*** Métodos da classe ***/
	
	public String registrarView() {
		Aluguel a = (Aluguel) dadosModelo.getRowData();
		try {
			aluguel = logic.buscar(a.getId());
			// Determina o valor dos juros e a multa por atraso
			if (aluguel.getVencimento().before(new Date())) {
				aluguel.setJuros((aluguel.getValor() * aluguel.getContrato().getJuros()) / 100);
				aluguel.setMulta((aluguel.getValor() * aluguel.getContrato().getMulta()) / 100);
			}
			aluguel.setValorPago(aluguel.getValor() + aluguel.getJuros() + aluguel.getMulta());
		} catch (BusinessException be) {
			viewMessage("Erro ao buscar: " + be.getMessage());
		}
		return "registrar";
	}
	
	public String registrar() {		
		try {
			aluguel.setContrato(LogicFactory.createContratoLogic().buscar(idContrato));
			aluguel.setFormaPagamento(LogicFactory.createFormaPagamentoLogic().buscar(idFormaPagamento));
			logic.editar(aluguel);
			viewMessage("Pagamento registrado com sucesso!");
			clearVariables();
		} catch (BusinessException be) {
			viewMessage("Erro ao registrar: " + be.getMessage(), "frmAluguel");
		}
		return super.edit();
	}
	
	public List<SelectItem> getListaContrato() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		try {
			for (Contrato c : LogicFactory.createContratoLogic().buscarTodosEmVigor()) {
				lista.add(new SelectItem(c.getId(), "Contrato nº " + c.getNumContrato()));
			}
		} catch (BusinessException be) {
			viewMessage("Erro ao carregar: " + be.getMessage(), "frmAluguel");
		} catch (Exception e) {
			viewMessage("Erro ao carregar: " + e.getMessage(), "frmAluguel");
		}
		return lista;
	}
	
	public List<SelectItem> getListaFormaPagamento() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		try {
			for (FormaPagamento f : LogicFactory.createFormaPagamentoLogic().buscarTodos()) {
				lista.add(new SelectItem(f.getId(), f.getDescricao()));
			}
		} catch (BusinessException be) {
			viewMessage("Erro ao carregar: " + be.getMessage(), "frmAluguel");
		} catch (Exception e) {
			viewMessage("Erro ao carregar: " + e.getMessage(), "frmAluguel");
		}
		return lista;
	}
	
	/**
	 * Obtém as informações de vigência do contrato e data de vencimento
	 * do aluguel para calcular a multa e juros correspondentes.
	 */
	public void atualizaValorAluguel() {
		aluguel.setValorPago(aluguel.getValor() + aluguel.getJuros() + aluguel.getMulta());
	}
	
	/*** Métodos Getters e Setters ***/

	public Aluguel getAluguel() {
		return aluguel;
	}

	public void setAluguel(Aluguel aluguel) {
		this.aluguel = aluguel;
	}

	public IAluguel getLogic() {
		return logic;
	}

	public void setLogic(IAluguel logic) {
		this.logic = logic;
	}

	public List<Aluguel> getListaAluguel() {
		return listaAluguel;
	}

	public void setListaAluguel(List<Aluguel> listaAluguel) {
		this.listaAluguel = listaAluguel;
	}

	public Long getIdAluguel() {
		return idAluguel;
	}

	public void setIdAluguel(Long idAluguel) {
		this.idAluguel = idAluguel;
	}

	public Long getIdContrato() {
		return idContrato;
	}

	public void setIdContrato(Long idContrato) {
		this.idContrato = idContrato;
	}

	public Long getIdFormaPagamento() {
		return idFormaPagamento;
	}

	public void setIdFormaPagamento(Long idFormaPagamento) {
		this.idFormaPagamento = idFormaPagamento;
	}
}