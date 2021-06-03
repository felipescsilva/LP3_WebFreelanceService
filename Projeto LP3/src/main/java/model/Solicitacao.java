package model;

import java.time.LocalDate;

public class Solicitacao {
	private int idSolicitacao;
	private int idAnuncio;
	private int qtdHoras;
	private LocalDate dataSolicitacao;
	private String status;
	private double valorTotal;
	private String CPF_User;
	private String CPF_Anunciante;
	public Solicitacao(int idSolicitacao, int idAnuncio, int qtdHoras, LocalDate dataSolicitacao, String status,
			double valorTotal, String cPF_User, String cPF_Anunciante) {
		super();
		this.idSolicitacao = idSolicitacao;
		this.idAnuncio = idAnuncio;
		this.qtdHoras = qtdHoras;
		this.dataSolicitacao = dataSolicitacao;
		this.status = status;
		this.valorTotal = valorTotal;
		CPF_User = cPF_User;
		CPF_Anunciante = cPF_Anunciante;
	}
	
	public String getCPF_User() {
		return CPF_User;
	}

	public void setCPF_User(String cPF_User) {
		CPF_User = cPF_User;
	}

	public String getCPF_Anunciante() {
		return CPF_Anunciante;
	}

	public void setCPF_Anunciante(String cPF_Anunciante) {
		CPF_Anunciante = cPF_Anunciante;
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


