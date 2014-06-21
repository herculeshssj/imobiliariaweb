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
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Contrato")
@NamedQueries(value={
		@NamedQuery(name="contrato.buscarPorNumContrato", query="from Contrato c where c.numContrato = :numContrato"),
		@NamedQuery(name="contrato.buscarTodosEmVigor", query="from Contrato c where c.situacao = 2"),
		@NamedQuery(name="contrato.vigorarContrato", query="update Contrato c set situacao = 2, dataVigoracao = :data where c.id = :id"),
		@NamedQuery(name="contrato.renovarContrato", query="update Contrato c set situacao = 3, dataRenovacao = :data where c.id = :id"),
		@NamedQuery(name="contrato.encerrarContrato", query="update Contrato c set situacao = 4, dataEncerramento = :data where c.id = :id"),
		@NamedQuery(name="contrato.contratosEmVigorImovel", query="from Contrato c where c.imovel.id = :idImovel and situacao in (1,2,3)"),
		@NamedQuery(name="contrato.vigorarContratosAutomaticamente", query="update Contrato c set situacao = 2 where inicioContrato = :data and situacao = 1"),
		@NamedQuery(name="contrato.encerrarContratosAutomaticamente", query="update Contrato c set situacao = 4 where terminoContrato = :data and situacao = 2")
})
public class Contrato implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5328525291903739446L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;
	
	@Column(length=10, nullable=false)
	String numContrato;
	
	@Column(nullable=false)
	@Temporal(TemporalType.DATE)
	Date inicioContrato;
	
	@Column(nullable=false)
	@Temporal(TemporalType.DATE)
	Date terminoContrato;
	
	@Temporal(TemporalType.TIMESTAMP)
	Date dataCadastro;
	
	@Temporal(TemporalType.TIMESTAMP)
	Date dataVigoracao;
	
	@Temporal(TemporalType.TIMESTAMP)
	Date dataRenovacao;
	
	@Temporal(TemporalType.TIMESTAMP)
	Date dataEncerramento;
	
	@Column(nullable=false)
	Double valor;
	
	@Column
	Double deposito;

	@Column(nullable=false)
	Integer diaVencimento;

	@Column(nullable=false)
	Integer prazo;
	
	@Column
	Double desconto;
	
	@Column
	Integer periodoDesconto;
	
	@Column
	Double multa;
	
	@Column
	Double juros;
	
	@Column
	Double comissaoCorretor;
	
	@Column
	Double comissaoImobiliaria;
	
	@Column
	String seguradora;
	
	@Column
	String foro;
	
	@Column(nullable=false)
	Integer situacao; // situação do contrato : 1 - Novo; 2 - Em vigor; 3 -  Em renovação; 4 - Encerrado
	
	@ManyToOne
	@JoinColumn(name="idImovel", nullable=false)
	Imovel imovel;
	
	@ManyToOne
	@JoinColumn(name="idIndice", nullable=false)
	IndiceReajuste indiceReajuste;
	
	@ManyToOne
	@JoinColumn(name="idLocatario", nullable=true)
	private Cliente locatario;
	
	@ManyToOne
	@JoinColumn(name="idLocatarioPJ", nullable=true)
	ClientePJ locatarioPJ;
	
	@ManyToOne
	@JoinColumn(name="idFiador", nullable=true)
	Cliente fiador;
	
	@ManyToOne
	@JoinColumn(name="idCorretor", nullable=true)
	Corretor corretor;
	
	@ManyToOne
	@JoinColumn(name="idModelo")
	ModeloContrato modeloContrato;
	
	public Contrato() {
		situacao = 1;
		imovel = new Imovel();		
		indiceReajuste = new IndiceReajuste();
	}
	
	public Contrato(Cliente cliente) {
		imovel = new Imovel();		
		indiceReajuste = new IndiceReajuste();
		if (cliente.getTipoCliente().indexOf("locatario") >= 0)
			locatario = cliente;
		else if (cliente.getTipoCliente().indexOf("fiador") >= 0)
			fiador = cliente;
	}

	public Contrato(ClientePJ cliente) {
		imovel = new Imovel();		
		indiceReajuste = new IndiceReajuste();
		locatarioPJ = cliente;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumContrato() {
		return numContrato;
	}

	public void setNumContrato(String numContrato) {
		this.numContrato = numContrato;
	}

	public Date getInicioContrato() {
		return inicioContrato;
	}

	public void setInicioContrato(Date inicioContrato) {
		this.inicioContrato = inicioContrato;
	}

	public Date getTerminoContrato() {
		return terminoContrato;
	}

	public void setTerminoContrato(Date terminoContrato) {
		this.terminoContrato = terminoContrato;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataVigoracao() {
		return dataVigoracao;
	}

	public void setDataVigoracao(Date dataVigoracao) {
		this.dataVigoracao = dataVigoracao;
	}

	public Date getDataRenovacao() {
		return dataRenovacao;
	}

	public void setDataRenovacao(Date dataRenovacao) {
		this.dataRenovacao = dataRenovacao;
	}

	public Date getDataEncerramento() {
		return dataEncerramento;
	}

	public void setDataEncerramento(Date dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Double getDeposito() {
		return deposito;
	}

	public void setDeposito(Double deposito) {
		this.deposito = deposito;
	}

	public Integer getDiaVencimento() {
		return diaVencimento;
	}

	public void setDiaVencimento(Integer diaVencimento) {
		this.diaVencimento = diaVencimento;
	}

	public Integer getPrazo() {
		return prazo;
	}

	public void setPrazo(Integer prazo) {
		this.prazo = prazo;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Integer getPeriodoDesconto() {
		return periodoDesconto;
	}

	public void setPeriodoDesconto(Integer periodoDesconto) {
		this.periodoDesconto = periodoDesconto;
	}

	public Double getMulta() {
		return multa;
	}

	public void setMulta(Double multa) {
		this.multa = multa;
	}

	public Double getJuros() {
		return juros;
	}

	public void setJuros(Double juros) {
		this.juros = juros;
	}

	public Double getComissaoCorretor() {
		return comissaoCorretor;
	}

	public void setComissaoCorretor(Double comissaoCorretor) {
		this.comissaoCorretor = comissaoCorretor;
	}

	public Double getComissaoImobiliaria() {
		return comissaoImobiliaria;
	}

	public void setComissaoImobiliaria(Double comissaoImobiliaria) {
		this.comissaoImobiliaria = comissaoImobiliaria;
	}

	public String getSeguradora() {
		return seguradora;
	}

	public void setSeguradora(String seguradora) {
		this.seguradora = seguradora;
	}

	public String getForo() {
		return foro;
	}

	public void setForo(String foro) {
		this.foro = foro;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public IndiceReajuste getIndiceReajuste() {
		return indiceReajuste;
	}

	public void setIndiceReajuste(IndiceReajuste indiceReajuste) {
		this.indiceReajuste = indiceReajuste;
	}

	public Cliente getLocatario() {
		return locatario;
	}

	public void setLocatario(Cliente locatario) {
		this.locatario = locatario;
	}

	public ClientePJ getLocatarioPJ() {
		return locatarioPJ;
	}

	public void setLocatarioPJ(ClientePJ locatarioPJ) {
		this.locatarioPJ = locatarioPJ;
	}

	public Cliente getFiador() {
		return fiador;
	}

	public void setFiador(Cliente fiador) {
		this.fiador = fiador;
	}

	public Corretor getCorretor() {
		return corretor;
	}

	public void setCorretor(Corretor corretor) {
		this.corretor = corretor;
	}

	public ModeloContrato getModeloContrato() {
		return modeloContrato;
	}

	public void setModeloContrato(ModeloContrato modeloContrato) {
		this.modeloContrato = modeloContrato;
	}

	public Integer getSituacao() {
		return situacao;
	}

	public void setSituacao(Integer situacao) {
		this.situacao = situacao;
	}
}