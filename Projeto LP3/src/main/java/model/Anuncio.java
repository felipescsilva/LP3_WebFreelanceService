package model;

import java.time.LocalDate;

public class Anuncio {
	private int idAnuncio;
	private String CPF;
	private String titulo;
	private String descricao;
	private double valorHora;
	private LocalDate dataAnuncio;
	public Anuncio(int idAnuncio, String cPF, String titulo, String descricao, double valorHora,
			LocalDate dataAnuncio) {
		super();
		this.idAnuncio = idAnuncio;
		CPF = cPF;
		this.titulo = titulo;
		this.descricao = descricao;
		this.valorHora = valorHora;
		this.dataAnuncio = dataAnuncio;
	}
	public Anuncio() {
		super();
	}
	public int getIdAnuncio() {
		return idAnuncio;
	}
	public void setIdAnuncio(int idAnuncio) {
		this.idAnuncio = idAnuncio;
	}
	public String getCPF() {
		return CPF;
	}
	public void setCPF(String cPF) {
		CPF = cPF;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getValorHora() {
		return valorHora;
	}
	public void setValorHora(double valorHora) {
		this.valorHora = valorHora;
	}
	public LocalDate getDataAnuncio() {
		return dataAnuncio;
	}
	public void setDataAnuncio(LocalDate dataAnuncio) {
		this.dataAnuncio = dataAnuncio;
	}
}
