package br.com.les20131.model;

import java.io.InputStream;
import java.util.Date;

/**
 * Classe de empresa
 * @author 200920183
 *
 */
public class Empresa extends Usuario {
	
	/**
	 * Armazena a url
	 * @access private
	 * @var String
	 */
	private String url;
	
	/**
	 * Armazena a definicao
	 * @access private
	 * @var String
	 */
	private String definicao;
	
	/**
	 * Armazena a imagem
	 * @access private
	 * @var imagem
	 */
	private InputStream imagem;
	
	/**
	 * Construtor da classe
	 * @access public
	 * @param String email
	 * @param String nome
	 * @param String senha
	 */
	public Empresa(String email, String nome, String senha) {
		super(email, nome, senha);
	}
	
	/**
	 * Construtor da classe
	 * @access public
	 * @param int idUsuario
	 * @param String email
	 * @param String nome
	 * @param String senha
	 * @param String url
	 * @param String definicao
	 * @param InputStream imagem
	 * @param int bloqueado
	 * @param int excluido
	 */
	public Empresa(int idUsuario, String email, String nome, String senha, String url, String definicao, InputStream imagem, int bloqueado, int excluido) {
		super(idUsuario, email, nome, senha, excluido, bloqueado);
		this.url = url;
		this.definicao = definicao;
		this.imagem = imagem;
	}

	/**
	 * Construtor da classe
	 * @access public
	 * @param String email
	 * @param String nome
	 * @param String senha
	 * @param String url
	 * @param String definicao
	 * @param InputStream imagem
	 */
	public Empresa(String email, String nome, String senha, String url, String definicao, InputStream imagem) {
		super(email, nome, senha);
		this.url = url;
		this.definicao = definicao;
		this.imagem = imagem;
	}

	/**
	 * Retorna a url
	 * @access public
	 * @return String
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Define a url
	 * @access public
	 * @param String url
	 * @return void
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Retorna a definição
	 * @access public
	 * @return String
	 */
	public String getDefinicao() {
		return definicao;
	}

	/**
	 * Define a definição
	 * @access public
	 * @param String definicao
	 * @return void
	 */
	public void setDefinicao(String definicao) {
		this.definicao = definicao;
	}

	/**
	 * Retorna a imagem
	 * @access public
	 * @return InputStream
	 */
	public InputStream getImagem() {
		return imagem;
	}

	/**
	 * Define a imagem
	 * @access public
	 * @param InputStream imagem
	 * @return void
	 */
	public void setImagem(InputStream imagem) {
		this.imagem = imagem;
	}
	
}
