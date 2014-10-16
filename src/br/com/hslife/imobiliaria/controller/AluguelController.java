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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import br.com.hslife.imobiliaria.db.HibernateUtility;
import br.com.hslife.imobiliaria.exception.BusinessException;
import br.com.hslife.imobiliaria.facade.IServicoManutencaoBusiness;
import br.com.hslife.imobiliaria.factory.LogicFactory;
import br.com.hslife.imobiliaria.logic.IAluguel;
import br.com.hslife.imobiliaria.model.Aluguel;
import br.com.hslife.imobiliaria.model.Contrato;
import br.com.hslife.imobiliaria.model.FormaPagamento;
import br.com.hslife.imobiliaria.model.HistoricoAluguel;
import br.com.hslife.imobiliaria.model.ServicoManutencao;
import br.com.hslife.imobiliaria.util.CurrencyWriter;

@Component("aluguelMB")
@Scope("session")
public class AluguelController extends GenericController {
	
	/*** Atributos da classe ***/
	
	// Modelo
	Aluguel aluguel;
	
	// Lógica de negócio
	IAluguel logic;
	
	@Autowired
	IServicoManutencaoBusiness servicoManutencaoBusiness;
	
	// Listas
	List<Aluguel> listaAluguel;
	
	// Armazena o id do objeto de modelo
	Long idAluguel;
	Long idContrato;
	Long idFormaPagamento;
	Long idServicoManutencao;
	
	// Armazena a situação do aluguel para a pesquisa
	String situacaoAluguel;
	Integer periodo;
	Integer ano;
	String numContrato;
	
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
		idServicoManutencao = null;
		ano = null;
		periodo = null;
		numContrato = null;
		dadosModelo = new ListDataModel(listaAluguel);
	}

	@Override
	public void simpleSearch() {		
		try {
			if (idContrato != null) {
				aluguel.setContrato(LogicFactory.createContratoLogic().buscar(idContrato));
			}
			aluguel.setSituacaoAluguel(situacaoAluguel);
			aluguel.setPeriodo(null);
			aluguel.setAno(null);
			dadosModelo = new ListDataModel(logic.buscarPorExemplo(aluguel));
			if (dadosModelo.isRowAvailable())
				viewMessage("Busca realizada com sucesso!");
			else
				viewMessage("Nenhum registro encontrado!");
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
	
	@Override
	public String delete() {
		String retorno = null;
		try {
			logic.excluir(aluguel);
			viewMessage("Registro excluído com sucesso!");
			clearVariables();
			retorno = super.delete();
		} catch (BusinessException be) {
			viewMessage("Erro ao excluir: " + be.getMessage(), "frmAluguel");
		}
		return retorno;
	}
	
	@Override
	public String searchView() {
		aluguel = new Aluguel();
		return super.searchView();
	}
	
	@Override
	public String search() {
		try {
			if (numContrato != null && !numContrato.trim().isEmpty()) {
				aluguel.setContrato(LogicFactory.createContratoLogic().buscarPorNumContrato(numContrato).get(0));
			}
			aluguel.setSituacaoAluguel(situacaoAluguel);
			aluguel.setPeriodo(aluguel.getPeriodo() == 0 ? null : aluguel.getPeriodo());
			aluguel.setAno(aluguel.getAno() == 0 ? null : aluguel.getAno());
			dadosModelo = new ListDataModel(logic.buscarPorExemplo(aluguel));
			if (dadosModelo.isRowAvailable())
				viewMessage("Busca realizada com sucesso!");
			else
				viewMessage("Nenhum registro encontrado!");
		} catch (BusinessException be) {
			viewMessage("Erro ao buscar: " +  be.getMessage(), componente);
		} catch (IndexOutOfBoundsException in) {
			viewMessage("Erro ao buscar: Contrato nº " +  numContrato + " não foi localizado!", componente);
		}
		return super.search();
	}
	
	/*** Métodos da classe ***/
	
	public String registrarView() {
		Aluguel a = (Aluguel) dadosModelo.getRowData();
		try {
			aluguel = logic.buscar(a.getId());
			// Determina o valor dos juros e a multa por atraso
			if (aluguel.getVencimento().before(new Date())) {
				aluguel.setMulta((aluguel.getValor() * aluguel.getContrato().getMulta()) / 100);
				aluguel.setJuros((aluguel.getValor() * aluguel.getContrato().getJuros()) / 100);
				aluguel.setPagamento(new Date());
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
			if (idServicoManutencao != null)
				aluguel.setServico(servicoManutencaoBusiness.buscarPorID(idServicoManutencao));
			logic.editar(aluguel);
			viewMessage("Pagamento registrado com sucesso!");
			clearVariables();
		} catch (BusinessException be) {
			viewMessage("Erro ao registrar: " + be.getMessage(), "frmAluguel");
		}
		return super.edit();
	}
	
	public void gerarRecibo() {
		// Obtem a resposta da requisição
		HttpServletResponse response = (HttpServletResponse) getContext().getExternalContext().getResponse();
		
		CurrencyWriter extenso = new CurrencyWriter();
		
		try {
			// Entidade obtido do componente dataTable
			Aluguel a = (Aluguel)dadosModelo.getRowData();
			
			// Definição dos valores para passar para o relatório			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("id", a.getId());
			params.put("valorpagoextenso", extenso.write(new BigDecimal(a.getValorPago())));
			
			// Define o texto para o parâmetro de observação
			StringBuilder sb = new StringBuilder();
			if (a.getDesconto() > 0.0) {
				sb.append("Desconto: R$ ");
				sb.append(Double.toString(a.getDesconto()));
				sb.append("\n");
			}
			if (a.getServico() != null) {
				sb.append("Serviço de manutenção realizado: ");
				sb.append(a.getServico().getDescricao());
				sb.append("\nValor do serviço: R$ ");
				sb.append(Double.toString(a.getValorServico()));
			}
			params.put("observacao", sb.toString());
			
			// Obtém o caminho para o relatório
			ServletContext servletContexto = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
			String caminhoRelOS = servletContexto.getRealPath("/jasper/recibo.jasper");
			
			// Passa os dados para o relatório e realiza a impressão do mesmo.			
			JasperPrint impressao = JasperFillManager.fillReport(caminhoRelOS, params, HibernateUtility.getConnection());
			
			// Faz a conversão para PDF
			byte[] dadosSaida = JasperExportManager.exportReportToPdf(impressao);
			
			// Complementa a resposta para exibir o relatório gerado
			response.setHeader("Content-Disposition","attachment; filename=\"recibo.pdf\";");
			response.setContentLength(dadosSaida.length);
			ServletOutputStream output = response.getOutputStream();
			output.write(dadosSaida, 0, dadosSaida.length);
			getContext().responseComplete();

			viewMessage("Recibo gerado com sucesso!", componente);
		} catch (Exception e) {
			viewMessage("Erro ao gerar o recibo: " + e.getMessage(), componente);
			e.printStackTrace();
		}
	}
	
	public void imprimir() {
		if (idContrato == null) {
			viewMessage("Selecione um contrato!", componente);
			return;
		}
		
		// Obtem a resposta da requisição
		HttpServletResponse response = (HttpServletResponse) getContext().getExternalContext().getResponse();
		
		try {
			aluguel.setContrato(LogicFactory.createContratoLogic().buscar(idContrato));
			
			List<HistoricoAluguel> historicos = new ArrayList<HistoricoAluguel>();
			HistoricoAluguel ha;
			for (Aluguel a : logic.buscarPorExemplo(aluguel)) {
				ha = new HistoricoAluguel();
				ha.setAno(a.getAno());
				ha.setPeriodo(a.getPeriodo());
				ha.setVencimento(a.getVencimento());
				ha.setPagamento(a.getPagamento());
				ha.setDiasAtraso(a.getDiasAtrasados());
				historicos.add(ha);
			}
			
			// Definição dos valores para passar para o relatório			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("locatario", aluguel.getContrato().getLocatario().getNome());
			
			// Obtém o caminho para o relatório
			ServletContext servletContexto = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
			String caminhoRelOS = servletContexto.getRealPath("/jasper/historicoaluguel.jasper");
			
			// Passa os dados para o relatório e realiza a impressão do mesmo.			
			JasperPrint impressao = JasperFillManager.fillReport(caminhoRelOS, params, new JRBeanCollectionDataSource(historicos));
			
			// Faz a conversão para PDF
			byte[] dadosSaida = JasperExportManager.exportReportToPdf(impressao);
			
			// Complementa a resposta para exibir o relatório gerado
			response.setHeader("Content-Disposition","attachment; filename=\"historicoaluguel.pdf\";");
			response.setContentLength(dadosSaida.length);
			ServletOutputStream output = response.getOutputStream();
			output.write(dadosSaida, 0, dadosSaida.length);
			getContext().responseComplete();

			viewMessage("Relatório gerado com sucesso!", componente);
		} catch (Exception e) {
			viewMessage("Erro ao gerar o relatório: " + e.getMessage(), componente);
			e.printStackTrace();
		}
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
	
	public List<SelectItem> getListaServicoManutencao() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		try {
			for (ServicoManutencao s : servicoManutencaoBusiness.buscarTodos()) {
				lista.add(new SelectItem(s.getId(), s.getDescricao()));
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
		aluguel.setValorPago(aluguel.getValor() + aluguel.getJuros() + aluguel.getMulta() + aluguel.getValorServico() - aluguel.getDesconto());
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

	public String getSituacaoAluguel() {
		return situacaoAluguel;
	}

	public void setSituacaoAluguel(String situacaoAluguel) {
		this.situacaoAluguel = situacaoAluguel;
	}

	public Integer getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Integer periodo) {
		this.periodo = periodo;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public String getNumContrato() {
		return numContrato;
	}

	public void setNumContrato(String numContrato) {
		this.numContrato = numContrato;
	}

	public Long getIdServicoManutencao() {
		return idServicoManutencao;
	}

	public void setIdServicoManutencao(Long idServicoManutencao) {
		this.idServicoManutencao = idServicoManutencao;
	}

	public void setServicoManutencaoBusiness(
			IServicoManutencaoBusiness servicoManutencaoBusiness) {
		this.servicoManutencaoBusiness = servicoManutencaoBusiness;
	}
}