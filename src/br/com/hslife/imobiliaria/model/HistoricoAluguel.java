package br.com.hslife.imobiliaria.model;

import java.util.Date;

public class HistoricoAluguel {
	
	private Integer periodo;
	
	private Integer ano;
	
	private Double valor;
	
	private Date vencimento;
	
	private Date pagamento;
	
	private Integer diasAtraso;

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

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
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

	public Integer getDiasAtraso() {
		return diasAtraso;
	}

	public void setDiasAtraso(Integer diasAtraso) {
		this.diasAtraso = diasAtraso;
	}
}
