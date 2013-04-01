package br.com.les20131.model;

import java.util.Date;

/**
 * Classe de viajante
 * @author 200920183
 */
public class Viajante extends Usuario {

	private String sexo;
	
	private Date dataNascimento;

	public Viajante(String email, String nome, String senha, String sexo, Date dataNascimento) {
		super(email, nome, senha);
		this.sexo = sexo;
		this.dataNascimento = dataNascimento;
	}
	
	public Viajante(int idUsuario, String email, String nome, String senha, String sexo, Date dataNascimento) {
		super(idUsuario, email, nome, senha);
		this.sexo = sexo;
		this.dataNascimento = dataNascimento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	
	
}
