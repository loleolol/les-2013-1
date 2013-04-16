package br.com.les20131.model;

import java.util.Date;

/**
 * Classe de viagem
 * @author 200920183
 */
public class Viagem {

	/**
	 * Armazena o código da viagem
	 * @access private
	 * @var int
	 */
	private int idViagem;
	
	/**
	 * Armazena o viajante da viagem
	 * @access private
	 * @var Viajante
	 */
	private Viajante viajante;
	
	/**
	 * Armazena o título da viagem
	 * @access private
	 * @var String
	 */
	private String titulo;
	
	/**
	 * Armazena a descrição da viagem
	 * @access private
	 * @var String
	 */
	private String descricao;
	
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
     * Armazena se o registro foi excluido
     * @access private
     * @var int
     */
    private int excluido;

    /**
     * Armazena se o registro foi bloqueado
     * @access private
     * @var int
     */
    private int bloqueado;	
	
	/**
	 * Construtor da classe
	 * @access public
	 * @param Viajante viajante
	 * @param String titulo
	 * @param String descricao
	 * @param String dataInicial
	 * @param String dataFinal
	 */
	public Viagem(Viajante viajante, String titulo, String descricao, Date dataInicial, Date dataFinal) {
		this.setViajante(viajante);
		this.setTitulo(titulo);
		this.descricao = descricao;
		this.setDataInicial(dataInicial);
		this.setDataFinal(dataFinal);
	}

	/**
	 * Construtor da classe
	 * @access public
	 * @param integer idViagem
	 * @param Viajante viajante
	 * @param String titulo
	 * @param String descricao
	 * @param String dataInicial
	 * @param String dataFinal
	 */
	public Viagem(int idViagem, Viajante viajante, String titulo, String descricao, Date dataInicial, Date dataFinal) {
		this.idViagem = idViagem;
		this.setViajante(viajante);
		this.setTitulo(titulo);
		this.descricao = descricao;
		this.setDataInicial(dataInicial);
		this.setDataFinal(dataFinal);
	}

	/**
	 * Retorna se o registro foi excluido
	 * @access public
	 * @return int
	 */
	public int getExcluido() {
		return excluido;
	}

	/**
	 * Define se o registro foi excluido
	 * @access public
	 * @param int excluido
	 * @return void
	 */
	public void setExcluido(int excluido) {
		this.excluido = excluido;
	}

	/**
	 * Retorna se o registro foi bloqueado
	 * @access public
	 * @return int bloqueado
	 */
	public int getBloqueado() {
		return bloqueado;
	}

	/**
	 * Define se o registro foi bloqueado
	 * @access public
	 * @param int bloqueado
	 * @return void
	 */
	public void setBloqueado(int bloqueado) {
		this.bloqueado = bloqueado;
	}	
	
	/**
	 * Retorna o código da viagem
	 * @access public
	 * @return int
	 */
	public int getIdViagem() {
		return idViagem;
	}

	/**
	 * Define o código da viagem
	 * @access public
	 * @param int idViagem
	 * @return void
	 */
	public void setIdViagem(int idViagem) {
		this.idViagem = idViagem;
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
	 * Retorna a descrição
	 * @access public
	 * @return void
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Armazena a descrição
	 * @access public
	 * @param descricao
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Retorna a data inicial da viagem
	 * @access public
	 * @return Date
	 */
	public Date getDataInicial() {
		return dataInicial;
	}

	/**
	 * Armazena a data inicial
	 * @access public
	 * @param Date dataInicial
	 * @return void
	 */
	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	/**
	 * Retorna a data inicial
	 * @access public
	 * @return void
	 */
	public Date getDataFinal() {
		return dataFinal;
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

	/**
	 * Retorna o título
	 * @access public
	 * @return String
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Define o títuli
	 * @access public
	 * @param String titulo
	 * @return void
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
}
