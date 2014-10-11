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

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import br.com.hslife.imobiliaria.exception.BusinessException;
import br.com.hslife.imobiliaria.factory.LogicFactory;
import br.com.hslife.imobiliaria.logic.IContrato;
import br.com.hslife.imobiliaria.model.Aluguel;
import br.com.hslife.imobiliaria.model.Cliente;
import br.com.hslife.imobiliaria.model.ClientePJ;
import br.com.hslife.imobiliaria.model.Contrato;
import br.com.hslife.imobiliaria.model.Corretor;
import br.com.hslife.imobiliaria.model.Imovel;
import br.com.hslife.imobiliaria.model.IndiceReajuste;
import br.com.hslife.imobiliaria.model.ModeloContrato;
import br.com.hslife.imobiliaria.util.RelParams;

public class ContratoController extends GenericController {
	
	/*** Atributos da classe ***/
	
	// Modelo
	Contrato contrato;
	
	// Lógica de negócio
	IContrato logic;
	
	// Listas
	List<Contrato> listaContrato;
	
	// Armazena o id do objeto de modelo
	Long idImovel;
	Long idLocatario;
	Long idLocatarioPJ;
	Long idFiador;
	Long idCorretor;
	Long idIndiceReajuste;
	Long idModeloContrato;
	Long idContrato;
	
	// SelectItems dos combos
	private List<SelectItem> imoveis;
	private List<SelectItem> locatarios;
	private List<SelectItem> locatariosPJ;
	private List<SelectItem> fiadores;
	private List<SelectItem> corretores;
	private List<SelectItem> indices;
	private List<SelectItem> modelos;
	
	// Nova situação do contrato
	int novaSituacao;
	
	// Determina se os aluguéis serão gerados ou não
	boolean gerarAlugueis;
	
	// Variável que armazena o contrato de locação para ser impresso na tela
	String contratoLocacao;
	
	/*** Construtor ***/	
	
	public ContratoController() {
		contrato = new Contrato();
		listaContrato = new ArrayList<Contrato>();
		logic = LogicFactory.createContratoLogic();
		componente = "frmContrato";
		
		// Define as permissões para este controller
		canAdd = isAuthorized("contrato", "add");
		canEdit = isAuthorized("contrato", "edit");
		canDelete = isAuthorized("contrato", "delete");
		canList = isAuthorized("contrato", "list");
		canView = isAuthorized("contrato", "view");
	}
	
	/*** Métodos abstratos de AbstractController ***/

	@Override
	protected void clearVariables() {
		contrato = new Contrato();
		listaContrato = new ArrayList<Contrato>();
		dadosModelo = new ListDataModel(listaContrato);
		idCorretor = null;
		idFiador = null;
		idImovel = null;
		idIndiceReajuste = null;
		idLocatario = null;
		idLocatarioPJ = null;
		idModeloContrato = null;
	}

	@Override
	public void simpleSearch() {		
		try {
			if (findValue == null || findValue.isEmpty()) {
				dadosModelo = new ListDataModel(logic.buscarTodos());
			} else {
				dadosModelo = new ListDataModel(logic.buscarPorNumContrato(findValue));
			}							
			viewMessage("Busca realizada com sucesso!");
		} catch (BusinessException be) {
			viewMessage("Erro ao buscar: " +  be.getMessage());
		}
	}

	/*** Métodos concretos sobrescritos ***/
	
	@Override
	public String searchView() {
		contrato = new Contrato();
		carregaCombos();
		return super.searchView();
	}
	
	@Override
	public String search() {
		try {
			contrato.setImovel(LogicFactory.createImovelLogic().buscar(idImovel));
			dadosModelo = new ListDataModel(logic.buscar(contrato));
			viewMessage("Busca realizada com sucesso!");
		} catch (BusinessException be) {
			viewMessage("Erro ao buscar: " +  be.getMessage(), componente);
		}
		return super.search();
	}
	
	@Override
	public String cancelAction() {
		clearVariables();
		return super.cancelAction();
	}
	
	@Override
	public String addView() {
		clearVariables();
		carregaCombos();
		return super.addView();
	}
	
	@Override
	public String add() {
		String retorno = null;
		try {
			if (idCorretor != null && idCorretor > 0) {
				contrato.setCorretor(LogicFactory.createCorretorLogic().buscar(idCorretor));
			}
			if (idFiador != null && idFiador > 0) {
				contrato.setFiador(LogicFactory.createClienteLogic().buscar(idFiador));
			}
			if (idLocatario != null && idLocatario > 0) {
				contrato.setLocatario(LogicFactory.createClienteLogic().buscar(idLocatario));
			}
			if (idLocatarioPJ != null && idLocatarioPJ > 0) {
				contrato.setLocatarioPJ(LogicFactory.createClientePJLogic().buscar(idLocatarioPJ));
			}
			
			contrato.setImovel(LogicFactory.createImovelLogic().buscar(idImovel));
			contrato.setIndiceReajuste(LogicFactory.createIndiceReajusteLogic().buscar(idIndiceReajuste));
			contrato.setModeloContrato(LogicFactory.createModeloContratoLogic().buscar(idModeloContrato));
			
			logic.cadastrar(contrato);
			
			viewMessage("Registro cadastrado com sucesso!");
			clearVariables();
			retorno = super.add();
		} catch (BusinessException be) {
			viewMessage(be.getMessage(), componente);
		} catch (Exception e) {
			viewMessage(e.getMessage(), componente);
		}
		return retorno;
	}

	@Override
	public String editView() {
		//Contrato c = (Contrato) dadosModelo.getRowData();
		try {
			contrato = logic.buscar(((Contrato) dadosModelo.getRowData()).getId());
			carregaCombos();
			
			idImovel = contrato.getImovel().getId();
			idIndiceReajuste = contrato.getIndiceReajuste().getId();
			idModeloContrato = contrato.getModeloContrato().getId();
			
			if (contrato.getFiador() != null) {
				idFiador = contrato.getFiador().getId();
			}
			if (contrato.getLocatario() != null) {
				idLocatario = contrato.getLocatario().getId();
			}
			if (contrato.getLocatarioPJ() != null) {
				idLocatarioPJ = contrato.getLocatarioPJ().getId();
			}
		} catch (BusinessException be) {
			viewMessage("Erro ao buscar: " + be.getMessage());
		}
		return super.editView();
	} 
	
	@Override
	public String edit() {
		String retorno = null;
		try {
			contrato.setIndiceReajuste(LogicFactory.createIndiceReajusteLogic().buscar(idIndiceReajuste));
			contrato.setModeloContrato(LogicFactory.createModeloContratoLogic().buscar(idModeloContrato));
			
			logic.editar(contrato);
			viewMessage("Registro editado com sucesso!");
			clearVariables();
			retorno = super.edit();
		} catch (BusinessException be) {
			viewMessage("Erro ao editar: " + be.getMessage(), componente);
		}
		return retorno;
	}
	
	@Override
	public String delete() {		
		try {
			switch (novaSituacao) {
				case 2 : logic.vigorarContrato(contrato.getId()); contrato.setSituacao(2); viewMessage("Contrato entrou em vigor com sucesso!"); break;
				case 3 : logic.renovarContrato(contrato.getId()); contrato.setSituacao(3); viewMessage("Contrato foi renovado com sucesso!"); break;
				case 4 : logic.encerrarContrato(contrato.getId()); contrato.setSituacao(4); viewMessage("Contrato foi encerrado com sucesso!");break;
			}
			
			if (gerarAlugueis) {
				Aluguel aluguel;
				int prazo = contrato.getPrazo();
				int periodo = 1;
				Calendar termino = Calendar.getInstance();
		    	termino.setTime(contrato.getInicioContrato());
		    	while (periodo <= prazo) {
		    		aluguel = new Aluguel();
		    		aluguel.setAno(termino.get(Calendar.YEAR));
		    		aluguel.setContrato(contrato);
		    		aluguel.setPeriodo(periodo);
		    		aluguel.setValor(contrato.getValor());
		    		aluguel.setVencimento(termino.getTime());
		    		LogicFactory.createAluguelLogic().cadastrar(aluguel);
		    		termino.add(Calendar.MONTH, 1);		    		
		    		periodo++;
		    	}
			}
			
			clearVariables();
		} catch (BusinessException be) {
			viewMessage("Erro ao editar: " + be.getMessage(), componente);
		}
		return super.delete();
	}
	
	/*** Métodos da classe ***/
	
	public String vigorarContrato() {
		novaSituacao = 2;
		return "vigorar";
	}
	
	public String renovarContrato() {
		novaSituacao = 3;
		return "renovar";
	}
	
	public String encerrarContrato() {
		novaSituacao = 4;
		return "encerrar";
	}
	
	private void carregaCombos() {
		corretores = new ArrayList<SelectItem>();
		fiadores = new ArrayList<SelectItem>();
		imoveis = new ArrayList<SelectItem>();
		indices = new ArrayList<SelectItem>();
		locatarios = new ArrayList<SelectItem>();
		locatariosPJ = new ArrayList<SelectItem>();
		modelos = new ArrayList<SelectItem>();
		
		try {
			for (Imovel i : LogicFactory.createImovelLogic().buscarTodos()) {
				imoveis.add(new SelectItem(i.getId(), i.getNumRegistro() + " - " + i.getTipoImovel(), i.getTipoImovel(), !i.getAtivo()));
			}
			for (Corretor c : LogicFactory.createCorretorLogic().buscarTodos()) {
				corretores.add(new SelectItem(c.getId(), c.getNome(), c.getNome(), !c.isAtivo()));
			}
			for (Cliente c : LogicFactory.createClienteLogic().buscarFiadoresAtivos()) {
				fiadores.add(new SelectItem(c.getId(), c.getNome(), c.getNome(), !c.isAtivo()));
			}
			for (Cliente c : LogicFactory.createClienteLogic().buscarLocatariosAtivos()) {
				locatarios.add(new SelectItem(c.getId(), c.getNome(), c.getNome(), !c.isAtivo()));
			}
			for (ClientePJ pj : LogicFactory.createClientePJLogic().buscarTodos()) {
				locatariosPJ.add(new SelectItem(pj.getId(), pj.getNomeFantasia(), pj.getNomeFantasia(), !pj.getAtivo()));
			}
			for (IndiceReajuste i : LogicFactory.createIndiceReajusteLogic().buscarTodos()) {
				indices.add(new SelectItem(i.getId(), i.getDescricao(), i.getDescricao(), !i.getAtivo()));
			}
			for (ModeloContrato m : LogicFactory.createModeloContratoLogic().buscarTodos()) {
				modelos.add(new SelectItem(m.getId(), m.getDescricao(), m.getDescricao(), !m.isAtivo()));
			}
		} catch (BusinessException be) {
			viewMessage(be.getMessage(), componente);
		} catch (Exception e) {
			viewMessage(e.getMessage(), componente);
		}
		
	}
	
	public void gerarContrato() {
		// Obtem a resposta da requisição
		HttpServletResponse response = (HttpServletResponse) getContext().getExternalContext().getResponse();
				
		try {
			// Obtém o contrato que será gerado
			Contrato c = (Contrato) dadosModelo.getRowData();
			contrato = logic.buscar(c.getId());
			
			// Definição dos valores para passar para o relatório
			Map<String, Object> params = RelParams.popular(contrato);
			
			// Passa os dados para o relatório e realiza a impressão do mesmo.
			InputStream inputBytes = new ByteArrayInputStream(contrato.getModeloContrato().getModelo().getBytes());
			//ByteArrayOutputStream outputBytes =  new ByteArrayOutputStream();
			
			JasperPrint impressao = JasperFillManager.fillReport(inputBytes, params, new JREmptyDataSource());
			
			/*
			 * Código para realizar a substituição dos campos
			 * 
			 * 
	/**
	 * @param args
	 *
	public static void main(String[] args) throws DocumentTemplateException, FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		DocumentTemplateFactory documentTemplateFactory = new DocumentTemplateFactory();
		DocumentTemplate template = documentTemplateFactory.getTemplate(new File("helloworld.odt"));
		Map model = new HashMap(); 
		model.put("nome", "Mundo");
		template.createDocument(model, new FileOutputStream("hello-gerado.odt"));

	}
			 */
			
			
			/*
			 *  Links para acessar:
			 *  http://jodreports.sourceforge.net/docs/apidocs/index.html
			 *  http://jodconverter.sourceforge.net/api/
			 *  
			 *  O código para auxiliar encontra-se no final do arquivo
			 */
			// Faz a conversão para PDF
			byte[] dadosSaida = JasperExportManager.exportReportToPdf(impressao);
			
			// Complementa a resposta para exibir o relatório gerado
			response.setHeader("Content-Disposition","attachment; filename=\"contrato.pdf\";");
			response.setContentLength(dadosSaida.length);
			ServletOutputStream output = response.getOutputStream();
			output.write(dadosSaida, 0, dadosSaida.length);
			getContext().responseComplete();

			viewMessage("Contrato gerado com sucesso!", componente);
		} catch (Exception e) {
			viewMessage("Erro ao gerar contrato: " + e.getMessage(), componente);
			e.printStackTrace();
		}
		
	}
	
	public String visualizarContrato() {
		System.out.println("Passei por aqui.");
		return "visualizarContrato";
	}
	
	/*** Métodos Getters e Setters ***/

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	public List<Contrato> getListaContrato() {
		return listaContrato;
	}

	public void setListaContrato(List<Contrato> listaContrato) {
		this.listaContrato = listaContrato;
	}

	public Long getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(Long idImovel) {
		this.idImovel = idImovel;
	}

	public Long getIdLocatario() {
		return idLocatario;
	}

	public void setIdLocatario(Long idLocatario) {
		this.idLocatario = idLocatario;
	}

	public Long getIdLocatarioPJ() {
		return idLocatarioPJ;
	}

	public void setIdLocatarioPJ(Long idLocatarioPJ) {
		this.idLocatarioPJ = idLocatarioPJ;
	}

	public Long getIdFiador() {
		return idFiador;
	}

	public void setIdFiador(Long idFiador) {
		this.idFiador = idFiador;
	}

	public Long getIdCorretor() {
		return idCorretor;
	}

	public void setIdCorretor(Long idCorretor) {
		this.idCorretor = idCorretor;
	}

	public Long getIdModeloContrato() {
		return idModeloContrato;
	}

	public void setIdModeloContrato(Long idModeloContrato) {
		this.idModeloContrato = idModeloContrato;
	}

	public List<SelectItem> getImoveis() {
		return imoveis;
	}

	public void setImoveis(List<SelectItem> imoveis) {
		this.imoveis = imoveis;
	}

	public List<SelectItem> getLocatarios() {
		return locatarios;
	}

	public void setLocatarios(List<SelectItem> locatarios) {
		this.locatarios = locatarios;
	}

	public List<SelectItem> getLocatariosPJ() {
		return locatariosPJ;
	}

	public void setLocatariosPJ(List<SelectItem> locatariosPJ) {
		this.locatariosPJ = locatariosPJ;
	}

	public List<SelectItem> getFiadores() {
		return fiadores;
	}

	public void setFiadores(List<SelectItem> fiadores) {
		this.fiadores = fiadores;
	}

	public List<SelectItem> getCorretores() {
		return corretores;
	}

	public void setCorretores(List<SelectItem> corretores) {
		this.corretores = corretores;
	}

	public List<SelectItem> getIndices() {
		return indices;
	}

	public void setIndices(List<SelectItem> indices) {
		this.indices = indices;
	}

	public List<SelectItem> getModelos() {
		return modelos;
	}

	public void setModelos(List<SelectItem> modelos) {
		this.modelos = modelos;
	}

	public Long getIdIndiceReajuste() {
		return idIndiceReajuste;
	}

	public void setIdIndiceReajuste(Long idIndiceReajuste) {
		this.idIndiceReajuste = idIndiceReajuste;
	}

	public Long getIdContrato() {
		return idContrato;
	}

	public void setIdContrato(Long idContrato) {
		this.idContrato = idContrato;
	}

	public boolean isGerarAlugueis() {
		return gerarAlugueis;
	}

	public void setGerarAlugueis(boolean gerarAlugueis) {
		this.gerarAlugueis = gerarAlugueis;
	}

	public String getContratoLocacao() {
		return contratoLocacao;
	}

	public void setContratoLocacao(String contratoLocacao) {
		this.contratoLocacao = contratoLocacao;
	}

	/*
	 * //
// JOOReports - The Open Source Java/OpenOffice Report Engine
// Copyright (C) 2004-2006 - Mirko Nasato <mirko@artofsolving.com>
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
// http://www.gnu.org/copyleft/lesser.html
//
package net.sf.jooreports.web.spring.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.DocumentFormat;
import com.artofsolving.jodconverter.DocumentFormatRegistry;
import net.sf.jooreports.templates.DocumentTemplate;
import net.sf.jooreports.templates.DocumentTemplateException;
import net.sf.jooreports.templates.DocumentTemplateFactory;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
* Base class for predefined document generators.
*
* Predefined generators load a template with the same name as the request URI,
* build a model from the request and generate the response document.
*
public abstract class AbstractDocumentGenerator extends AbstractController {

    protected abstract Object getModel(HttpServletRequest request) throws Exception;
    
protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
request.setCharacterEncoding("UTF-8");
        renderDocument(getModel(request), request, response);
        return null;
}

    private Resource getTemplateDirectory(String documentName) throws IOException {
        String directoryName = "WEB-INF/templates/"+ documentName +"-template";
        return getApplicationContext().getResource(directoryName);
    }

    private Resource getTemplateFile(String documentName) throws IOException {
        String templateName = "WEB-INF/templates/"+ documentName +"-template.odt";
        return getApplicationContext().getResource(templateName);
    }

    private void renderDocument(Object model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DocumentConverter converter = (DocumentConverter) getApplicationContext().getBean("documentConverter");
        DocumentFormatRegistry formatRegistry = (DocumentFormatRegistry) getApplicationContext().getBean("documentFormatRegistry");
        String outputExtension = FilenameUtils.getExtension(request.getRequestURI());
DocumentFormat outputFormat = formatRegistry.getFormatByFileExtension(outputExtension);
        if (outputFormat == null) {
         throw new ServletException("unsupported output format: "+ outputExtension);
        }
        File templateFile = null;
        String documentName = FilenameUtils.getBaseName(request.getRequestURI());
        Resource templateDirectory = getTemplateDirectory(documentName);
        if (templateDirectory.exists()) {
            templateFile = templateDirectory.getFile();
        } else {
            templateFile = getTemplateFile(documentName).getFile();
            if (!templateFile.exists()) {
                throw new ServletException("template not found: "+ documentName);
            }
        }
        
        DocumentTemplateFactory documentTemplateFactory = new DocumentTemplateFactory();
        DocumentTemplate template = documentTemplateFactory.getTemplate(templateFile);
        
        ByteArrayOutputStream odtOutputStream = new ByteArrayOutputStream();
        try {
template.createDocument(model, odtOutputStream);
} catch (DocumentTemplateException exception) {
throw new ServletException(exception);
}
        response.setContentType(outputFormat.getMimeType());
        response.setHeader("Content-Disposition", "inline; filename="+ documentName +"."+ outputFormat.getFileExtension());
        
        if ("odt".equals(outputFormat.getFileExtension())) {
         // no need to convert
response.getOutputStream().write(odtOutputStream.toByteArray());
        } else {
ByteArrayInputStream odtInputStream = new ByteArrayInputStream(odtOutputStream.toByteArray());
DocumentFormat inputFormat = formatRegistry.getFormatByFileExtension("odt");
converter.convert(odtInputStream, inputFormat, response.getOutputStream(), outputFormat);
        }
    }
*/

}