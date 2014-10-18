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
@Table(name="Agenda")
@NamedQueries(value={
		@NamedQuery(name="agenda.buscarAgendaCorretor", query="from Agenda where visitado = false and corretor.id = :id")
})
public class Agenda implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6569589154885879259L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;
	
	@ManyToOne
	@JoinColumn(name="idImovel", nullable=false)
	Imovel imovel;
	
	@ManyToOne
	@JoinColumn(name="idCorretor", nullable=false)
	Corretor corretor;
	
	@Column(length=100, nullable=false)
	String nomeCliente;
	
	@Column(length=100, nullable=false)
	String emailCliente;
	
	@Column(length=50, nullable=false)
	String telefoneCliente;
	
	@Temporal(TemporalType.DATE)
	Date data;
	
	@Column
	Integer horaInicio;
	
	@Column
	Integer horaFim;
	
	@Column
	Boolean visitado;
	
	@Column(columnDefinition="text")
	String observacao;
	
	public Agenda() {
		corretor = new Corretor();
		imovel = new Imovel();
		visitado = false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public Corretor getCorretor() {
		return corretor;
	}

	public void setCorretor(Corretor corretor) {
		this.corretor = corretor;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getEmailCliente() {
		return emailCliente;
	}

	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}

	public String getTelefoneCliente() {
		return telefoneCliente;
	}

	public void setTelefoneCliente(String telefoneCliente) {
		this.telefoneCliente = telefoneCliente;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Integer getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Integer horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Integer getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(Integer horaFim) {
		this.horaFim = horaFim;
	}

	public Boolean getVisitado() {
		return visitado;
	}

	public void setVisitado(Boolean visitado) {
		this.visitado = visitado;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
}
