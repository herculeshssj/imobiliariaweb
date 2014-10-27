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
import java.util.Calendar;
import java.util.List;

import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.hslife.imobiliaria.exception.BusinessException;
import br.com.hslife.imobiliaria.facade.ISeguradoraBusiness;
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
import br.com.hslife.imobiliaria.model.Seguradora;
import br.com.hslife.imobiliaria.util.RelParams;

@Component("contratoMB")
@Scope("session")
public class ContratoController extends GenericController {
	
	/*** Atributos da classe ***/
	
	// Modelo
	Contrato contrato;
	
	// Lógica de negócio
	IContrato logic;
	
	// Listas
	List<Contrato> listaContrato;
	
	@Autowired
	ISeguradoraBusiness seguradoraBusiness;
	
	// Armazena o id do objeto de modelo
	Long idImovel;
	Long idLocatario;
	Long idLocatarioPJ;
	Long idFiador;
	Long idCorretor;
	Long idIndiceReajuste;
	Long idModeloContrato;
	Long idContrato;
	Long idSeguradora;
	
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
		idSeguradora = null;
	}

	@Override
	public void simpleSearch() {		
		try {
			if (findValue == null || findValue.isEmpty()) {
				dadosModelo = new ListDataModel(logic.buscarTodos());
			} else {
				dadosModelo = new ListDataModel(logic.buscarPorNumContrato(findValue));
			}							
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
			if (dadosModelo.isRowAvailable())
				viewMessage("Busca realizada com sucesso!");
			else
				viewMessage("Nenhum registro encontrado!");
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
			if (idSeguradora != null && idSeguradora > 0) {
				contrato.setSeguradora(seguradoraBusiness.buscarPorID(idSeguradora));
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
			if (contrato.getSeguradora() != null) {
				idSeguradora = contrato.getSeguradora().getId();
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
			contrato.setSeguradora(seguradoraBusiness.buscarPorID(idSeguradora));
			
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
	
	public String gerarContrato() {
		try {
			Contrato c = (Contrato)dadosModelo.getRowData();
			contrato = logic.buscar(c.getId());
			contratoLocacao = RelParams.popular(c);
			viewMessage("Contrato gerado com sucesso!");
			return "visualizarContrato";
		} catch (BusinessException be) {
			viewMessage("Erro ao gerar contrato: " + be.getMessage(), componente);
		}
		return null;
	}
	
	public List<SelectItem> getListaSeguradora() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		try {
			for (Seguradora s : seguradoraBusiness.buscarTodos()) {
				lista.add(new SelectItem(s.getId(), s.getDescricao()));
			}
		} catch (BusinessException be) {
			viewMessage("Erro ao carregar: " + be.getMessage(), componente);
		} catch (Exception e) {
			viewMessage("Erro ao carregar: " + e.getMessage(), componente);
		}
		return lista;
	}
	
	public String visualizarContrato() {
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

	public Long getIdSeguradora() {
		return idSeguradora;
	}

	public void setIdSeguradora(Long idSeguradora) {
		this.idSeguradora = idSeguradora;
	}

	public void setSeguradoraBusiness(ISeguradoraBusiness seguradoraBusiness) {
		this.seguradoraBusiness = seguradoraBusiness;
	}
}