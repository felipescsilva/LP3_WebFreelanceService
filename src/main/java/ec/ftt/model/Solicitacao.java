package ec.ftt.model;

import java.time.LocalDate;

public class Solicitacao {
	private int idSolicitacao;
	private int idAnuncio;
	private int qtdHoras;
	private LocalDate dataSolicitacao;
	private String status;
	private double valorTotal;
	public Solicitacao(int idSolicitacao, int idAnuncio, int qtdHoras, LocalDate dataSolicitacao, String status,
			double valorTotal) {
		super();
		this.idSolicitacao = idSolicitacao;
		this.idAnuncio = idAnuncio;
		this.qtdHoras = qtdHoras;
		this.dataSolicitacao = dataSolicitacao;
		this.status = status;
		this.valorTotal = valorTotal;
	}
	public Solicitacao() {
		super();
	}
	public int getIdSolicitacao() {
		return idSolicitacao;
	}
	public void setIdSolicitacao(int idSolicitacao) {
		this.idSolicitacao = idSolicitacao;
	}
	public int getIdAnuncio() {
		return idAnuncio;
	}
	public void setIdAnuncio(int idAnuncio) {
		this.idAnuncio = idAnuncio;
	}
	public int getQtdHoras() {
		return qtdHoras;
	}
	public void setQtdHoras(int qtdHoras) {
		this.qtdHoras = qtdHoras;
	}
	public LocalDate getDataSolicitacao() {
		return dataSolicitacao;
	}
	public void setDataSolicitacao(LocalDate dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
}


