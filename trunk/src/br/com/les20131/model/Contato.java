package br.com.les20131.model;

import java.util.Date;

/**
 * Classe de contato
 * @author 200920183
 *
 */
public class Contato {

	/**
	 * Armazena o id do contato
	 * @access private
	 * @var int
	 */
	private int idContato;
	
	/**
	 * Armazena  um dos viajantes
	 * @access private
	 * @var Viajante
	 */
	private Viajante viajante1;
	
	/**
	 * Armazena outro dos viajantes
	 * @access private
	 * @var Viajante
	 */
	private Viajante viajante2;
	
	/**
	 * Armazena a data de início do contato
	 * @access private
	 * @var Date
	 */
	private Date dataInicio;
	
	/**
	 * Construtor da classe
	 * @access public
	 * @param Viajante viajante1
	 * @param Viajante viajante2
	 * @param Date dataInicio
	 */
	public Contato(Viajante viajante1, Viajante viajante2, Date dataInicio) {
		this.viajante1 = viajante1;
		this.viajante2 = viajante2;
		this.dataInicio = dataInicio;
	}
	
	/**
	 * Construtor da classe
	 * @access public
	 * @param int idContato
	 * @param Viajante viajante1
	 * @param Viajante viajante2
	 * @param Date dataInicio
	 */
	public Contato(int idContato, Viajante viajante1, Viajante viajante2, Date dataInicio) {
		this.idContato = idContato;
		this.viajante1 = viajante1;
		this.viajante2 = viajante2;
		this.dataInicio = dataInicio;
	}

	/**
	 * Retorna o id do contato
	 * @access public
	 * @return int
	 */
	public int getIdContato() {
		return idContato;
	}

	/**
	 * Define o id do contato
	 * @access public
	 * @param int idContato
	 * @return void
	 */
	public void setIdContato(int idContato) {
		this.idContato = idContato;
	}

	/**
	 * Retorna um dos viajantes
	 * @access public
	 * @return Viajante
	 */
	public Viajante getViajante1() {
		return viajante1;
	}

	/**
	 * Define um dos viajantes
	 * @access public
	 * @param Viajante viajante1
	 * @return void
	 */
	public void setViajante1(Viajante viajante1) {
		this.viajante1 = viajante1;
	}

	/**
	 * Retorna outro dos viajantes
	 * @access public
	 * @return Viajante
	 */
	public Viajante getViajante2() {
		return viajante2;
	}

	/**
	 * Define o id de outro dos viajantes
	 * @access public
	 * @param Viajante viajante2
	 * @return void
	 */
	public void setViajante2(Viajante viajante2) {
		this.viajante2 = viajante2;
	}

	/**
	 * Retorna a data de início
	 * @access public
	 * @return Date
	 */
	public Date getDataInicio() {
		return dataInicio;
	}

	/**
	 * Define a data de início
	 * @access public
	 * @param Date dataInicio
	 * @return void
	 */
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
		
}
