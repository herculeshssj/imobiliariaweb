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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="ClientePJ")
@NamedQueries(value={
		@NamedQuery(name="clientePJ.buscarPorCNPJ", query="from ClientePJ c where c.cnpj = :cnpj"),
		@NamedQuery(name="clientePJ.buscarTodosAtivos", query="from ClientePJ c where c.ativo = true")
})
public class ClientePJ implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5469286873618686957L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;
	
	@Column(length=200, nullable=false)
	String nomeFantasia;
	
	@Column
	String ramoAtividade;
	
	@Column(length=14, nullable=false)
	String cnpj;
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	
	Set<Telefone> telefones;
	
	@OneToOne(cascade=CascadeType.ALL)
	
	Endereco endereco;
	
	@Column
	String numPasta;
	
	@Column
	Boolean ativo;
	
	@OneToMany(mappedBy="locatarioPJ")
	List<Contrato> contratos;
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	Set<Socio> socios;
	
	public ClientePJ() {
		ativo = true;
		telefones = new HashSet<Telefone>();
		endereco = new Endereco();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getRamoAtividade() {
		return ramoAtividade;
	}

	public void setRamoAtividade(String ramoAtividade) {
		this.ramoAtividade = ramoAtividade;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getNumPasta() {
		return numPasta;
	}

	public void setNumPasta(String numPasta) {
		this.numPasta = numPasta;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
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

	public Set<Socio> getSocios() {
		return socios;
	}

	public void setSocios(Set<Socio> socios) {
		this.socios = socios;
	}

	public Set<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<Telefone> telefones) {
		this.telefones = telefones;
	}
}
