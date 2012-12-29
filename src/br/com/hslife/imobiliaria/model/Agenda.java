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
