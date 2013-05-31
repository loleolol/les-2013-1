package br.com.les20131.model;

import java.util.Date;

/**
 * Classe de avaliação
 * @author 200920183
 *
 */
public class Avaliacao {
	
	/**
	 * Armazena o código da avaliação da empresa
	 * @access private
	 * @var int
	 */
	private int idAvaliacao;
	
	/**
	 * Armazena a empresa avaliada
	 * @access private
	 * @var Empresa
	 */
	private Empresa empresa;
	
	/**
	 * Armazena o viajante que avaliou
	 * @access private
	 * @var Viajante
	 */
	private Viajante viajante;
	
	/**
	 * Armazena a avaliação
	 * @access private
	 * @var int
	 */
	private int avaliacao;
	
	/**
	 * Armazena a descrição
	 * @access private
	 * @var String
	 */
	private String descricao;
	
	/**
	 * Armazena a data de inclusão
	 * @access private
	 * @var Date
	 */
	private Date dataInclusao;
	
	/**
	 * Construtor da classe
	 * @access public
	 * @param int idAvaliacao
	 * @param Empresa empresa
	 * @param Viajante viajante
	 * @param int avaliacao
	 * @param String descricao
	 * @param Date dataInclusao
	 */
	public Avaliacao(int idAvaliacao, Empresa empresa, Viajante viajante, int avaliacao, String descricao, Date dataInclusao) {
		this.idAvaliacao = idAvaliacao;
		this.empresa = empresa;
		this.viajante = viajante;
		this.avaliacao = avaliacao;
		this.descricao = descricao;
		this.dataInclusao = dataInclusao;
	}
	
	/**
	 * Construtor da classe
	 * @access public
	 * @param Empresa empresa
	 * @param Viajante viajante
	 * @param int avaliacao
	 * @param String descricao
	 * @param Date dataInclusao
	 */
	public Avaliacao(Empresa empresa, Viajante viajante, int avaliacao, String descricao, Date dataInclusao) {
		this.empresa = empresa;
		this.viajante = viajante;
		this.avaliacao = avaliacao;
		this.descricao = descricao;
		this.dataInclusao = dataInclusao;
	}

	/**
	 * Retorna o id de avaliação da empresa
	 * @access public
	 * @return int
	 */
	public int getIdAvaliacao() {
		return idAvaliacao;
	}

	/**
	 * Define o id de avaliação da empresa
	 * @access public
	 * @param int idAvaliacao
	 * @return void
	 */
	public void setIdAvaliacao(int idAvaliacao) {
		this.idAvaliacao = idAvaliacao;
	}

	/**
	 * Retorna a empresa
	 * @access public
	 * @return Empresa
	 */
	public Empresa getEmpresa() {
		return empresa;
	}

	/**
	 * Define a empresa
	 * @access public
	 * @param Empresa empresa
	 * @return void
	 */
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	/**
	 * Retorna o viajante
	 * @access public
	 * @return Viajante
	 */
	public Viajante getViajante() {
		return viajante;
	}

	/**
	 * Define o viajante
	 * @access public
	 * @param Viajante viajante
	 * @return void
	 */
	public void setViajante(Viajante viajante) {
		this.viajante = viajante;
	}

	/**
	 * Retorna a avaliação
	 * @access public
	 * @return int
	 */
	public int getAvaliacao() {
		return avaliacao;
	}

	/**
	 * Define a avaliação
	 * @access public
	 * @param int avaliacao
	 * @return void
	 */
	public void setAvaliacao(int avaliacao) {
		this.avaliacao = avaliacao;
	}

	/**
	 * Retorna a descricao
	 * @access public
	 * @return String
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Define a descricao
	 * @access public
	 * @param String descricao
	 * @return void
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Retorna a data de inclusão
	 * @access public
	 * @return Date
	 */
	public Date getDataInclusao() {
		return dataInclusao;
	}

	/**
	 * Define a data de inclusão
	 * @access public
	 * @param Date dataInclusao
	 * @return void
	 */
	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}
}
