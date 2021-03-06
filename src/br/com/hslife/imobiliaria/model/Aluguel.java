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

package br.com.hslife.imobiliaria.model;

import java.io.Serializable;
import java.util.Calendar;
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
import javax.persistence.Transient;

@Entity
@Table(name="aluguel")
@NamedQueries(value={
		@NamedQuery(name="aluguel.buscarPorContrato", query="from Aluguel a where a.contrato.id = :idContrato")
})
public class Aluguel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6558196958572144653L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private Integer periodo;
	
	@Column
	private Integer ano;
	
	@Column
	@Temporal(TemporalType.DATE)
	private Date vencimento;
	
	@Column
	@Temporal(TemporalType.DATE)
	private Date pagamento;
	
	@Column
	private double valorPago;
	
	@Column
	private double valor;
	
	@Column
	private double juros;
	
	@Column
	private double multa;
	
	@Column
	private double desconto;
	
	@ManyToOne
	@JoinColumn(name="idContrato")
	private Contrato contrato;
	
	@ManyToOne
	@JoinColumn(name="idFormaPagamento")
	private FormaPagamento formaPagamento;
	
	@ManyToOne
	@JoinColumn(name="idServico")
	private ServicoManutencao servico;
	
	@Column
	private double valorServico;
	
	@Transient
	private String situacaoAluguel;
	
	@Transient
	private String nomeLocatario;
	
	public Aluguel() {
		
	}

	/**
	 * Retorna a quantidade de dias que o aluguel está atrasado
	 * 
	 * @return dias de atraso do aluguel
	 */
	public int getDiasAtrasados() {
		if (this.pagamento == null) {
			Date hoje = new Date();
			if (this.vencimento.before(hoje)) {
				Calendar temp = Calendar.getInstance();
				temp.setTime(this.vencimento);
				int diasAtraso = 0;
				while (temp.getTime().before(hoje)) {
					temp.add(Calendar.DAY_OF_YEAR, 1);
					diasAtraso++;					
				}
				return diasAtraso;
			}
		} else {
			Calendar temp = Calendar.getInstance();
			temp.setTime(this.vencimento);
			int diasAtraso = 0;
			while (temp.getTime().before(this.pagamento)) {
				temp.add(Calendar.DAY_OF_YEAR, 1);
				diasAtraso++;
			}
			return diasAtraso;
		}
		return 0;
	}
	
	/* Sobrescrita dos métodos equals e hashCode */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Long.signum(getId() == null ? 0 : getId() ^ (getId() == null ? 1 : getId() >>> 32));
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getId() == null) return false;
		if (getClass() != obj.getClass()) return false;

		Aluguel other = (Aluguel) obj;
		if (!getId().equals(other.getId())) return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Integer periodo) {
		this.periodo = periodo;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Date getVencimento() {
		return vencimento;
	}

	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}

	public Date getPagamento() {
		return pagamento;
	}

	public void setPagamento(Date pagamento) {
		this.pagamento = pagamento;
	}

	public double getValorPago() {
		return valorPago;
	}

	public void setValorPago(double valorPago) {
		this.valorPago = valorPago;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public double getJuros() {
		return juros;
	}

	public void setJuros(double juros) {
		this.juros = juros;
	}

	public double getMulta() {
		return multa;
	}

	public void setMulta(double multa) {
		this.multa = multa;
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public String getSituacaoAluguel() {
		return situacaoAluguel;
	}

	public void setSituacaoAluguel(String situacaoAluguel) {
		this.situacaoAluguel = situacaoAluguel;
	}

	public String getNomeLocatario() {
		return nomeLocatario;
	}

	public void setNomeLocatario(String nomeLocatario) {
		this.nomeLocatario = nomeLocatario;
	}

	public ServicoManutencao getServico() {
		return servico;
	}

	public void setServico(ServicoManutencao servico) {
		this.servico = servico;
	}

	public double getValorServico() {
		return valorServico;
	}

	public void setValorServico(double valorServico) {
		this.valorServico = valorServico;
	}

	public double getDesconto() {
		return desconto;
	}

	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}
}