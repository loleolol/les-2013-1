package br.com.les20131.model;

import java.util.Date;

/**
 * Classe de anúncio
 * @author 200920183
 *
 */
public class Anuncio {
	
	/**
	 * Armazena o código de anúncio
	 * @access private
	 * @var int
	 */
	private int idAnuncio;
	
	/**
	 * Armazena a empresa
	 * @access private
	 * @var Empresa
	 */
	private Empresa empresa;
	
	/**
	 * Armazena a anúncio
	 * @access private
	 * @var String
	 */
	private String anuncio;
	
	/**
	 * Armazena a data inicial
	 * @access private
	 * @var Date
	 */
	private Date dataInicial;
	
	/**
	 * Armazena a data final
	 * @access private
	 * @var Date
	 */
	private Date dataFinal;
	
	/**
	 * Construtor da classe
	 * @access public
	 * @param int idAnuncio
	 * @param Empresa empresa
	 * @param String anuncio
	 * @param Date dataInicial
	 * @param Date dataFinal
	 */
	public Anuncio(int idAnuncio, Empresa empresa, String anuncio, Date dataInicial, Date dataFinal) {
		this.idAnuncio = idAnuncio;
		this.empresa = empresa;
		this.anuncio = anuncio;
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
	}
	
	/**
	 * Construtor da classe
	 * @access public
	 * @param Empresa empresa
	 * @param String anuncio
	 * @param Date dataInicial
	 * @param Date dataFinal
	 */
	public Anuncio(Empresa empresa, String anuncio, Date dataInicial, Date dataFinal) {
		this.empresa = empresa;
		this.anuncio = anuncio;
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
	}

	/**
	 * Retorna o id de anúncio da empresa
	 * @access public
	 * @return int
	 */
	public int getIdAnuncio() {
		return this.idAnuncio;
	}

	/**
	 * Define o id de anúncio da empresa
	 * @access public
	 * @param int idAnuncio
	 * @return void
	 */
	public void setIdAnuncio(int idAnuncio) {
		this.idAnuncio = idAnuncio;
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
	 * Retorna o anúncio
	 * @access public
	 * @return String
	 */
	public String getAnuncio() {
		return this.anuncio;
	}

	/**
	 * Define o anúncio
	 * @access public
	 * @param String anuncio
	 * @return void
	 */
	public void setAnuncio(String anuncio) {
		this.anuncio = anuncio;
	}

	/**
	 * Retorna a data inicial
	 * @access public
	 * @return Date
	 */
	public Date getDataInicial() {
		return this.dataInicial;
	}

	/**
	 * Define a data inicial
	 * @access public
	 * @param Date dataInicial
	 * @return void
	 */
	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	/**
	 * Retorna a data final
	 * @access public
	 * @return Date
	 */
	public Date getDataFinal() {
		return this.dataFinal;
	}

	/**
	 * Define a data final
	 * @access public
	 * @param Date dataFinal
	 * @return void
	 */
	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
}
