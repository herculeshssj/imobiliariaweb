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

package br.com.hslife.imobiliaria.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.model.ListDataModel;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import br.com.hslife.imobiliaria.db.HibernateUtility;
import br.com.hslife.imobiliaria.exception.BusinessException;
import br.com.hslife.imobiliaria.factory.LogicFactory;
import br.com.hslife.imobiliaria.logic.IRelatorio;
import br.com.hslife.imobiliaria.model.Cliente;
import br.com.hslife.imobiliaria.model.Relatorio;

public class RelatorioController extends GenericController {
	
	/*** Atributos da classe ***/
	
	// Modelo
	Relatorio relatorio;
	
	// Lógica de negócio
	IRelatorio logic;
	
	// Listas
	List<Cliente> listaRelatorio;
	
	// Armazena o id do objeto de modelo
	Long idRelatorio;
				
	/*** Construtor ***/	
	
	public RelatorioController() {
		this.relatorio = new Relatorio();
		this.listaRelatorio = new ArrayList<Cliente>();
		logic = LogicFactory.createRelatorioLogic();
		
		// Define as permissões para este controller
		canAdd = isAuthorized("relatorio", "add");
		canEdit = isAuthorized("relatorio", "edit");
		canDelete = isAuthorized("relatorio", "delete");
		canList = isAuthorized("relatorio", "list");
		canView = isAuthorized("relatorio", "view");
	}
	
	/*** Métodos abstratos de AbstractController ***/

	@Override
	protected void clearVariables() {
		this.relatorio = new Relatorio();		
		this.idRelatorio = null;
		this.listaRelatorio = new ArrayList<Cliente>();
		dadosModelo = new ListDataModel(listaRelatorio);
	}

	@Override
	public void simpleSearch() {		
		try {
			relatorio.setDescricao(findValue);
			if (findValue == null || findValue.isEmpty()) {
				dadosModelo = new ListDataModel(logic.buscarTodos());
			} else {
				dadosModelo = new ListDataModel(logic.buscar(relatorio));
			}							
			viewMessage("Busca realizada com sucesso!");
			relatorio.setDescricao("");
		} catch (BusinessException be) {
			viewMessage("Erro ao buscar: " +  be.getMessage());
		}
	}

	/*** Métodos concretos sobrescritos ***/
	
	@Override
	public String addView() {
		clearVariables();
		return null;
	}
	
	@Override
	public String add() {		
		try {
			if (relatorio.getId() != null && relatorio.getId() > 0) 
				logic.editar(relatorio);
			else
				logic.cadastrar(relatorio);
			viewMessage("Registro cadastrado com sucesso!");
			clearVariables();
		} catch (BusinessException be) {
			viewMessage("Erro ao salvar: " + be.getMessage(), "frmRelatorio");			
		}
		return null;
	}

	@Override
	public String editView() {
		Relatorio c = (Relatorio) dadosModelo.getRowData();
		try {
			relatorio = logic.buscar(c.getId());
		} catch (BusinessException be) {
			viewMessage("Erro ao buscar: " + be.getMessage(), "frmRelatorio");
		}
		return null;
	} 
	
	@Override
	public String edit() {
		try {
			logic.editar(relatorio);
			viewMessage("Registro editado com sucesso!");
			clearVariables();			
		} catch (BusinessException be) {
			viewMessage("Erro ao editar: " + be.getMessage(), "frmRelatorio");
		}
		return null;
	}
	
	@Override
	public String delete() {		
		try {
			logic.habilitar(idRelatorio);
			if (relatorio.getAtivo()) {
				viewMessage("Registro habilitado com sucesso!");
			} else {
				viewMessage("Registro desabilitado com sucesso!");
			}			
			clearVariables();
		} catch (BusinessException be) {
			viewMessage("Erro ao editar: " + be.getMessage(), "frmRelatorio");
		}
		return null;
	}
	
	/*** Métodos da classe ***/
	
	public void carregaModelo(UploadEvent event) throws Exception {
		UploadItem item = event.getUploadItem();
		relatorio.setTamanhoArquivo(item.getData().length);
		relatorio.setArquivo(item.getFileName());
		relatorio.setDados(item.getData());
	}
	
	public void gerarRelatorio() {
		// Obtem a resposta da requisição
		HttpServletResponse response = (HttpServletResponse) getContext().getExternalContext().getResponse();
				
		try {
			// Obtém o contrato que será gerado
			Relatorio r = (Relatorio) dadosModelo.getRowData();
			relatorio = logic.buscar(r.getId());
			
			// Definição dos valores para passar para o relatório
			Map<String, Object> params = new HashMap<String, Object>();
			
			// Passa os dados para o relatório e realiza a impressão do mesmo.
			InputStream inputBytes = new ByteArrayInputStream(relatorio.getDados());
			
			JasperPrint impressao = JasperFillManager.fillReport(inputBytes, params, HibernateUtility.getConnection());
			
			// Faz a conversão para PDF
			byte[] dadosSaida = JasperExportManager.exportReportToPdf(impressao);
			
			// Complementa a resposta para exibir o relatório gerado
			response.setHeader("Content-Disposition","attachment; filename=\"relatorio.pdf\";");
			response.setContentLength(dadosSaida.length);
			ServletOutputStream output = response.getOutputStream();
			output.write(dadosSaida, 0, dadosSaida.length);
			getContext().responseComplete();

			viewMessage("Relatório gerado com sucesso!", componente);
		} catch (Exception e) {
			viewMessage("Erro ao gerar contrato: " + e.getMessage(), componente);
			e.printStackTrace();
		}
		
	}
		
	/*** Métodos Getters e Setters ***/
	
	public List<Cliente> getListaCliente() {
		return listaRelatorio;
	}

	public void setListaCliente(List<Cliente> listaCliente) {
		this.listaRelatorio = listaCliente;
	}

	public Relatorio getRelatorio() {
		return relatorio;
	}

	public void setRelatorio(Relatorio relatorio) {
		this.relatorio = relatorio;
	}

	public List<Cliente> getListaRelatorio() {
		return listaRelatorio;
	}

	public void setListaRelatorio(List<Cliente> listaRelatorio) {
		this.listaRelatorio = listaRelatorio;
	}

	public Long getIdRelatorio() {
		return idRelatorio;
	}

	public void setIdRelatorio(Long idRelatorio) {
		this.idRelatorio = idRelatorio;
	}

}