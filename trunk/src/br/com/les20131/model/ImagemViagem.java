package br.com.les20131.model;

import java.io.InputStream;

/**
 * Classe de imagem de viagem
 * @author 200920183 *
 */
public class ImagemViagem {

	/**
	 * Armazena a imagem de viagem
	 * @access private
	 * @var int
	 */
	private int idImagemViagem;
	
	/**
	 * Armazena a viagem da imagem
	 * @access private
	 * @var int
	 */
	private int idViagem;
	
	/**
	 * Armazena a imagem
	 * @access private
	 * @var InputStream
	 */
	private InputStream imagem;
	
	/**
	 * Construtor da classe
	 * @access public
	 * @param int idImagemViagem
	 * @param int idViagem
	 * @param InputStream imagem
	 */
	public ImagemViagem(int idImagemViagem, int idViagem, InputStream imagem) {
		this.idImagemViagem = idImagemViagem;
		this.idViagem = idViagem;
		this.imagem = imagem;
	}
	
	/**
	 * Construtor da classe
	 * @access public
	 * @param int idViagem
	 * @param InputStream imagem
	 */
	public ImagemViagem(int idViagem, InputStream imagem) {
		this.idViagem = idViagem;
		this.imagem = imagem;
	}

	/**
	 * Retorna id da imagem de viagem
	 * @access public
	 * @return int
	 */
	public int getIdImagemViagem() {
		return idImagemViagem;
	}

	/**
	 * Define o id de imagem de viagem
	 * @access public
	 * @param int idImagemViagem
	 * @return void
	 */
	public void setIdImagemViagem(int idImagemViagem) {
		this.idImagemViagem = idImagemViagem;
	}

	/**
	 * Retorna o id da viagem
	 * @access public
	 * @return int
	 */
	public int getIdViagem() {
		return idViagem;
	}

	/**
	 * Define o id da viagem
	 * @access public
	 * @param int idViagem
	 * @return void
	 */
	public void setIdViagem(int idViagem) {
		this.idViagem = idViagem;
	}

	/**
	 * Retorna imagem
	 * @access public
	 * @return InputStream
	 */
	public InputStream getImagem() {
		return imagem;
	}
	
	/**
	 * Define a image
	 * @access public
	 * @param InputStream imagem
	 * @return void
	 */
	public void setImagem(InputStream imagem) {
		this.imagem = imagem;
	}
		
}
