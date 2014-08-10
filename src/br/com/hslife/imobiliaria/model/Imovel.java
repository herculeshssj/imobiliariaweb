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

package br.com.hslife.imobiliaria.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="Imovel")
@NamedQueries(value={		
		@NamedQuery(name="imovel.buscarTodosAtivos", query="from Imovel i where i.ativo = true"),
		@NamedQuery(name="imovel.buscarPorNumRegistro", query="from Imovel i where i.numRegistro = :numRegistro")
})
public class Imovel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2770496350740390315L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;
	
	@Column(length=20, nullable=false)
	String numRegistro;
	
	@OneToOne
	@Cascade(CascadeType.ALL)
	@JoinColumn(name="idEndereco", nullable=false)
	Endereco endereco;
	
	@Column
	Double valor;
	
	@OneToMany
	@Cascade(CascadeType.ALL)
	List<Foto> fotos;
	
	@ManyToOne
	@JoinColumn(name="idLocador", nullable=false)
	Cliente locador;
	
	@OneToMany(mappedBy="imovel", fetch=FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	List<Contrato> contratos;
	
	@Column
	Boolean ativo; // diz se o imóvel está ativo (disponível para divulgação)
	
	@Column
	Boolean site;// diz se o imóvel estará disponível para exibição no site
	
	@Column(columnDefinition="text")
	String maisInfo;
	
	@Column(length=20)
	String tipoImovel;
	
	@Column(length=20)
	String situacaoImovel;
	
	public Imovel() {
		ativo = true;
		site = true;
		endereco = new Endereco();
		fotos = new ArrayList<Foto>();
		locador = new Cliente();
	}
	
	public Imovel(Cliente cliente) {
		this.locador = cliente;
		ativo = true;
		site = true;
		endereco = new Endereco();
		fotos = new ArrayList<Foto>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumRegistro() {
		return numRegistro;
	}

	public void setNumRegistro(String numRegistro) {
		this.numRegistro = numRegistro;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public List<Foto> getFotos() {
		return fotos;
	}

	public void setFotos(List<Foto> fotos) {
		this.fotos = fotos;
	}

	public Cliente getLocador() {
		return locador;
	}

	public void setLocador(Cliente locador) {
		this.locador = locador;
	}

	public List<Contrato> getContratos() {
		return contratos;
	}

	public void setContratos(List<Contrato> contratos) {
		this.contratos = contratos;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Boolean getSite() {
		return site;
	}

	public void setSite(Boolean site) {
		this.site = site;
	}

	public String getMaisInfo() {
		return maisInfo;
	}

	public void setMaisInfo(String maisInfo) {
		this.maisInfo = maisInfo;
	}

	public String getTipoImovel() {
		return tipoImovel;
	}

	public void setTipoImovel(String tipoImovel) {
		this.tipoImovel = tipoImovel;
	}

	public String getSituacaoImovel() {
		return situacaoImovel;
	}

	public void setSituacaoImovel(String situacaoImovel) {
		this.situacaoImovel = situacaoImovel;
	}
	
}
