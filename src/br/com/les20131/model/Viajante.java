package br.com.les20131.model;

import java.io.InputStream;
import java.util.Date;

import java.sql.Blob;

/**
 * Classe de viajante
 * @author 200920183
 */
public class Viajante extends Usuario {

	/**
	 * Armazena sexo do viajante
	 * @access private
	 * @var String
	 */
	private String sexo;

	/**
	 * Armazena data de nascimento do viajante
	 * @access private
	 * @var Date
	 */
	private Date dataNascimento;

	/**
	 * Armazena imagem do viajante
	 * @access private
	 * @var InputStream
	 */
	private InputStream imagem;
	
	/**
	 * Construtor da classe
	 * @access public
	 * @param String email
	 * @param String nome
	 * @param String senha
	 * @param String sexo
	 * @param Date dataNascimento
	 */
	public Viajante(String email, String nome, String senha, String sexo, Date dataNascimento) {
		super(email, nome, senha);
		this.sexo = sexo;
		this.dataNascimento = dataNascimento;
	}
	
	/**
	 * Construtor da classe
	 * @access public
	 * @param int idUsuario
	 * @param String email
	 * @param String nome
	 * @param String senha
	 * @param String sexo
	 * @param Date dataNascimento
	 * @param InputStream imagem
	 */
	public Viajante(int idUsuario, String email, String nome, String senha, int excluido, int bloqueado, String sexo, Date dataNascimento) {
		super(idUsuario, email, nome, senha, excluido, bloqueado);
		this.sexo = sexo;
		this.dataNascimento = dataNascimento;
	}
	
	/**
	 * Retorna o sexo do viajante
	 * @access public
	 * @return String
	 */
	public String getSexo() {
		return sexo;
	}

	/**
	 * Define o sexo do viajante
	 * @access public
	 * @param String sexo
	 * @return String
	 */
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	/**
	 * Retorna a data de nascimento do viajante
	 * @access public
	 * @return Date
	 */
	public Date getDataNascimento() {
		return dataNascimento;
	}

	/**
	 * Define a data de nascimento do viajante
	 * @access public
	 * @param dataNascimento
	 */
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	/**
	 * Retorna a imagem do viajante
	 * @access public
	 * @return InputStream
	 */
	public InputStream getImagem() {
		return imagem;
	}

	/**
	 * Define a imagem do viajante
	 * @access public
	 * @param InputStream imagem
	 * @return void
	 */
	public void setImagem(InputStream imagem) {
		this.imagem = imagem;
	}
	
	
	
}
