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

package br.com.hslife.imobiliaria.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="Cliente")
@Inheritance(strategy=InheritanceType.JOINED)
@NamedQueries(value={
		@NamedQuery(name="cliente.buscarPorCPF", query="from Cliente c where c.cpf = :cpf and not c.tipoCliente like '%funcionario%' and not c.tipoCliente like '%corretor%'"),
		@NamedQuery(name="cliente.buscarTodos", query="from Cliente c where not c.tipoCliente like '%funcionario%' and not c.tipoCliente like '%corretor%'"),
		@NamedQuery(name="cliente.buscarTodosAtivos", query="from Cliente c where c.ativo = true and not c.tipoCliente like '%funcionario%' and not c.tipoCliente like '%corretor%'"),
		@NamedQuery(name="cliente.buscarSociosAtivos", query="from Cliente c where c.ativo = true and c.tipoCliente like '%socio%' and not c.tipoCliente like '%funcionario%' and not c.tipoCliente like '%corretor%'"),
		@NamedQuery(name="cliente.buscarLocadores", query="from Cliente c where c.tipoCliente like '%locador%' and not c.tipoCliente like '%funcionario%' and not c.tipoCliente like '%corretor%'"),
		@NamedQuery(name="cliente.buscarLocadoresAtivos", query="from Cliente c where c.ativo = true and c.tipoCliente like '%locador%' and not c.tipoCliente like '%funcionario%' and not c.tipoCliente like '%corretor%'"),
		@NamedQuery(name="cliente.buscarLocatariosAtivos", query="from Cliente c where c.ativo = true and c.tipoCliente like '%locatario%' and not c.tipoCliente like '%funcionario%' and not c.tipoCliente like '%corretor%'"),
		@NamedQuery(name="cliente.buscarFiadoresAtivos", query="from Cliente c where c.ativo = true and c.tipoCliente like '%fiador%' and not c.tipoCliente like '%funcionario%' and not c.tipoCliente like '%corretor%'")
})
public class Cliente implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3345962041425800063L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;
	
	@Column(length=11, nullable=false)
	String cpf;
	
	@Column(length=11)
	String cpfConjuge;
	
	@Column(length=15)
	String rg;
	
	@Column(length=15)
	String rgConjuge;
	
	@Column(length=30)
	String orgaoEmissor;
	
	@Column(length=30)
	String orgaoEmissorConjuge;
	
	@Temporal(TemporalType.DATE)
	Date dataEmissao;
	
	@Temporal(TemporalType.DATE)
	Date dataEmissaoConjuge;
	
	@Column(length=100, nullable=false)
	private String nome;
	
	@Column(length=100)
	String nomeConjuge;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	Date dataNascimento;
	
	@Temporal(TemporalType.DATE)	
	Date dataNascimentoConjuge;
	
	@Column(length=1, nullable=false)
	String genero;
	
	@Column(length=1)
	String generoConjuge;
	
	@Column(length=50)
	String nacionalidade;
	
	@Column(length=50)
	String nacionalidadeConjuge;
	
	@Column(length=50)
	String naturalidade;
	
	@Column(length=50)
	String naturalidadeConjuge;
	
	@Column(length=100)
	String filiacaoPai;
	
	@Column(length=100)
	String filiacaoPaiConjuge;
	
	@Column(length=100)
	String filiacaoMae;
	
	@Column(length=100)
	String filiacaoMaeConjuge;
	
	@Column(length=30)
	String estadoCivil;
	
	@Column(length=100)	
	String email;
	
	@Column(length=50)
	String profissao;
	
	@Column(length=50)
	String profissaoConjuge;
	
	@Column(length=10)
	String numPasta;

	@OneToMany(fetch=FetchType.EAGER)
	@Cascade(CascadeType.ALL)
	List<Telefone> telefones;
	
	@OneToOne
	@Cascade(CascadeType.ALL)
	@JoinColumn(name="idEndereco")
	Endereco endereco;
	
	@Column(nullable=false)
	String tipoCliente; // cliente,locador,locatario,fiador,responsavel,procurador
	
	@OneToMany(mappedBy="locador", fetch=FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	List<Imovel> imoveis;
	
	@OneToMany(mappedBy="corretor", fetch=FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	List<Contrato> contratos;
	
	Boolean ativo;
	
	public Cliente() {
		ativo = true;
		endereco = new Endereco();
		telefones = new ArrayList<Telefone>();
		tipoCliente = "cliente";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCpfConjuge() {
		return cpfConjuge;
	}

	public void setCpfConjuge(String cpfConjuge) {
		this.cpfConjuge = cpfConjuge;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getRgConjuge() {
		return rgConjuge;
	}

	public void setRgConjuge(String rgConjuge) {
		this.rgConjuge = rgConjuge;
	}

	public String getOrgaoEmissor() {
		return orgaoEmissor;
	}

	public void setOrgaoEmissor(String orgaoEmissor) {
		this.orgaoEmissor = orgaoEmissor;
	}

	public String getOrgaoEmissorConjuge() {
		return orgaoEmissorConjuge;
	}

	public void setOrgaoEmissorConjuge(String orgaoEmissorConjuge) {
		this.orgaoEmissorConjuge = orgaoEmissorConjuge;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Date getDataEmissaoConjuge() {
		return dataEmissaoConjuge;
	}

	public void setDataEmissaoConjuge(Date dataEmissaoConjuge) {
		this.dataEmissaoConjuge = dataEmissaoConjuge;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeConjuge() {
		return nomeConjuge;
	}

	public void setNomeConjuge(String nomeConjuge) {
		this.nomeConjuge = nomeConjuge;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Date getDataNascimentoConjuge() {
		return dataNascimentoConjuge;
	}

	public void setDataNascimentoConjuge(Date dataNascimentoConjuge) {
		this.dataNascimentoConjuge = dataNascimentoConjuge;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getGeneroConjuge() {
		return generoConjuge;
	}

	public void setGeneroConjuge(String generoConjuge) {
		this.generoConjuge = generoConjuge;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public String getNacionalidadeConjuge() {
		return nacionalidadeConjuge;
	}

	public void setNacionalidadeConjuge(String nacionalidadeConjuge) {
		this.nacionalidadeConjuge = nacionalidadeConjuge;
	}

	public String getNaturalidade() {
		return naturalidade;
	}

	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}

	public String getNaturalidadeConjuge() {
		return naturalidadeConjuge;
	}

	public void setNaturalidadeConjuge(String naturalidadeConjuge) {
		this.naturalidadeConjuge = naturalidadeConjuge;
	}

	public String getFiliacaoPai() {
		return filiacaoPai;
	}

	public void setFiliacaoPai(String filiacaoPai) {
		this.filiacaoPai = filiacaoPai;
	}

	public String getFiliacaoPaiConjuge() {
		return filiacaoPaiConjuge;
	}

	public void setFiliacaoPaiConjuge(String filiacaoPaiConjuge) {
		this.filiacaoPaiConjuge = filiacaoPaiConjuge;
	}

	public String getFiliacaoMae() {
		return filiacaoMae;
	}

	public void setFiliacaoMae(String filiacaoMae) {
		this.filiacaoMae = filiacaoMae;
	}

	public String getFiliacaoMaeConjuge() {
		return filiacaoMaeConjuge;
	}

	public void setFiliacaoMaeConjuge(String filiacaoMaeConjuge) {
		this.filiacaoMaeConjuge = filiacaoMaeConjuge;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public String getProfissaoConjuge() {
		return profissaoConjuge;
	}

	public void setProfissaoConjuge(String profissaoConjuge) {
		this.profissaoConjuge = profissaoConjuge;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public List<Imovel> getImoveis() {
		return imoveis;
	}

	public void setImoveis(List<Imovel> imoveis) {
		this.imoveis = imoveis;
	}

	public List<Contrato> getContratos() {
		return contratos;
	}

	public void setContratos(List<Contrato> contratos) {
		this.contratos = contratos;
	}

	public String getNumPasta() {
		return numPasta;
	}

	public void setNumPasta(String numPasta) {
		this.numPasta = numPasta;
	}
	
	
}
