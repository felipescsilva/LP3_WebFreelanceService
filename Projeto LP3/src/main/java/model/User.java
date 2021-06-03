package model;

import java.time.LocalDate;

public class User {
	private String CPF;
	private String login;
	private String password;
	private LocalDate dataNascimento;
	private String telefone;
	private String email;
	
	public User(String cPF, String login, String password, LocalDate dataNascimento, String telefone, String email) {
		super();
		CPF = cPF;
		this.login = login;
		this.password = password;
		this.dataNascimento = dataNascimento;
		this.telefone = telefone;
		this.email = email;
	}

	public User() {
		super();
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
