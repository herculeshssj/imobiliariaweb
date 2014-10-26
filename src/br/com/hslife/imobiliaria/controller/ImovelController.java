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

package br.com.hslife.imobiliaria.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.swing.ImageIcon;

import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import br.com.hslife.imobiliaria.exception.BusinessException;
import br.com.hslife.imobiliaria.exception.ServiceException;
import br.com.hslife.imobiliaria.factory.LogicFactory;
import br.com.hslife.imobiliaria.logic.IImovel;
import br.com.hslife.imobiliaria.model.Cliente;
import br.com.hslife.imobiliaria.model.Endereco;
import br.com.hslife.imobiliaria.model.Foto;
import br.com.hslife.imobiliaria.model.Imovel;
import br.com.hslife.imobiliaria.service.CEPService;
import br.com.hslife.imobiliaria.util.ViewUtil;

public class ImovelController extends GenericController {
	
	/*** Atributos da classe ***/
	
	// Modelo
	Imovel imovel;
	Foto foto;
	
	// Lógica de negócio
	IImovel logic;	
	CEPService service;
	
	// Listas
	List<Imovel> listaImovel;
	List<Foto> listaFoto;
	
	// Armazena o id do objeto de modelo
	Long idLocador;
	
	// Conta a quantidade de arquivos restantes
	int contaArquivos;
	
	// Armazena o nome do arquivo a ser excluído da lista
	String nomeFoto;
	
	// SelectItems dos combos
	private List<SelectItem> tipoImoveis;
	private List<SelectItem> locadores;

	/*** Construtor ***/	
	
	public ImovelController() {
		imovel = new Imovel();
		foto = new Foto();
		logic = LogicFactory.createImovelLogic();	
		service = new CEPService();
		listaImovel = new ArrayList<Imovel>();
		listaFoto = new ArrayList<Foto>();
		contaArquivos = 20;		
		service = new CEPService();		
		componente = "frmImovel";
		
		// Define as permissões para este controller
		canAdd = isAuthorized("imovel", "add");
		canEdit = isAuthorized("imovel", "edit");
		canDelete = isAuthorized("imovel", "delete");
		canList = isAuthorized("imovel", "list");
		canView = isAuthorized("imovel", "view");
	}
	
	/*** Métodos abstratos de AbstractController ***/

	@Override
	protected void clearVariables() {
		imovel = new Imovel();
		listaFoto = new ArrayList<Foto>();
		listaImovel = new ArrayList<Imovel>();
		dadosModelo = new ListDataModel(listaImovel);
		contaArquivos = 20;
		idLocador = null;
	}

	@Override
	public void simpleSearch() {		
		try {
			if (findValue == null || findValue.isEmpty()) {
				dadosModelo = new ListDataModel(logic.buscarTodosAtivos());
			} else {
				dadosModelo = new ListDataModel(logic.buscarPorNumRegistro(findValue));
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
		imovel = new Imovel();
		carregaCombos();
		return super.searchView();
	}
	
	@Override
	public String search() {
		try {
			if (idLocador != null && idLocador > 0)
				imovel.setLocador(LogicFactory.createClienteLogic().buscar(idLocador));
			dadosModelo = new ListDataModel(logic.buscar(imovel));
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
		imovel.setFotos(listaFoto);
		try {
			imovel.setLocador(LogicFactory.createClienteLogic().buscar(idLocador));
			logic.cadastrar(imovel);
			viewMessage("Registro cadastrado com sucesso!");
			clearVariables();
			retorno = super.add();
		} catch (BusinessException be) {
			viewMessage("Erro ao salvar: " + be.getMessage(), componente);
		}
		return retorno;
	}

	@Override
	public String editView() {
		Imovel i = (Imovel) dadosModelo.getRowData();
		try {
			imovel = logic.buscar(i.getId());
			listaFoto = imovel.getFotos();
			idLocador = imovel.getLocador().getId();
			contaArquivos = 20 - listaFoto.size();
			carregaCombos();
			//imovel.setContratos(LogicFactory.createContratoLogic().buscar(new Contrato(imovel.getLocador())));		
		} catch (BusinessException be) {
			viewMessage("Erro ao buscar: " + be.getMessage());
		}
		return super.editView();
	} 
	
	@Override
	public String edit() {
		String retorno = null;
		imovel.setFotos(listaFoto);
		try {
			imovel.setLocador(LogicFactory.createClienteLogic().buscar(idLocador));			
			logic.editar(imovel);
			viewMessage("Registro editado com sucesso!");
			clearVariables();
			retorno = super.edit();
		} catch (BusinessException be) {
			viewMessage("Erro ao editar: " + be.getMessage(), componente);			
		}
		return retorno;
	}
	
	@Override
	public String deleteView() {
		Imovel i = (Imovel) dadosModelo.getRowData();
		try {
			imovel = logic.buscar(i.getId());
		} catch (BusinessException be) {
			viewMessage("Erro ao buscar: " + be.getMessage());
		}
		return super.deleteView();
	}
	
	@Override
	public String delete() {		
		try {
			logic.habilitar(imovel.getId());
			if (imovel.getAtivo()) {				
				viewMessage("Registro desabilitado com sucesso!");
			} else {
				viewMessage("Registro habilitado com sucesso!");
			}			
			clearVariables();
		} catch (BusinessException be) {
			viewMessage("Erro ao editar: " + be.getMessage(), componente);
		}
		return super.delete();
	}
	
	/*** Métodos da classe ***/
	
	public String startUp() {
		this.simpleSearch();
		return "listView";
	}
	
	private void carregaCombos() {
		locadores = new ArrayList<SelectItem>();
		tipoImoveis = new ArrayList<SelectItem>();
		
		
		// Carrega a combobox com os tipos de imóveis
        for (int i = 0; i < ViewUtil.getSize(ViewUtil.TIPO_IMOVEL); i++) {
            tipoImoveis.add(i, new SelectItem(ViewUtil.getData(i, ViewUtil.TIPO_IMOVEL), ViewUtil.getData(i, ViewUtil.TIPO_IMOVEL)));
        }
        
		try {
			for (Cliente pf : LogicFactory.createClienteLogic().buscarLocadores()) {
				locadores.add(new SelectItem(pf.getId(), pf.getCpf() + " - " + pf.getNome(), pf.getNome(), !pf.isAtivo()));
			}
		} catch (BusinessException be) {
			viewMessage("Erro ao carregar: " + be.getMessage());
		}
	}
	
	public void removerFoto() {
		for (Foto f : listaFoto) {
			if (f.getArquivo().equals(nomeFoto)) {
				listaFoto.remove(f);
				break;
			}
		}
		
		// Limpa o objeto
		foto = new Foto();
		 
		if (contaArquivos < 20) {
			contaArquivos++;
		}
	}
	
	public void paint (OutputStream stream, Object object) throws IOException {
		stream.write(listaFoto.get((Integer)object).getDados());
	}
	
	public void carregaFoto(UploadEvent event) throws Exception {
		UploadItem item = event.getUploadItem();
		foto = new Foto();
		foto.setTamanho(item.getData().length);
		foto.setArquivo(item.getFileName());
		foto.setDados(item.getData());
		ImageIcon temp = new ImageIcon(item.getData());
		foto.setLargura(temp.getIconWidth());
		foto.setAltura(temp.getIconHeight());
		listaFoto.add(foto);
		contaArquivos--;
	}

	public void buscarEndereco() {
		try {
			Endereco e = new Endereco();
			e = service.getEndereco(imovel.getEndereco().getCep());
			imovel.setEndereco(e);
		} catch (ServiceException se) {
			viewMessage(se.getMessage(), "txtCep");
		}
	}
	
	/*** Métodos Getters e Setters ***/

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public Foto getFoto() {
		return foto;
	}

	public void setFoto(Foto foto) {
		this.foto = foto;
	}

	public List<Imovel> getListaImovel() {
		return listaImovel;
	}

	public void setListaImovel(List<Imovel> listaImovel) {
		this.listaImovel = listaImovel;
	}

	public List<Foto> getListaFoto() {
		return listaFoto;
	}

	public void setListaFoto(List<Foto> listaFoto) {
		this.listaFoto = listaFoto;
	}

	public Long getIdLocador() {
		return idLocador;
	}

	public void setIdLocador(Long idLocador) {
		this.idLocador = idLocador;
	}

	public int getContaArquivos() {
		return contaArquivos;
	}

	public void setContaArquivos(int contaArquivos) {
		this.contaArquivos = contaArquivos;
	}

	public String getNomeFoto() {
		return nomeFoto;
	}

	public void setNomeFoto(String nomeFoto) {
		this.nomeFoto = nomeFoto;
	}

	public List<SelectItem> getTipoImoveis() {
		return tipoImoveis;
	}

	public void setTipoImoveis(List<SelectItem> tipoImoveis) {
		this.tipoImoveis = tipoImoveis;
	}

	public List<SelectItem> getLocadores() {
		return locadores;
	}

	public void setLocadores(List<SelectItem> locadores) {
		this.locadores = locadores;
	}
}