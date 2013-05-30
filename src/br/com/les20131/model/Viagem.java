package br.com.les20131.model;

import java.util.Date;
import java.util.List;

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
	 * Armazena a data de inclusão
	 * @access private
	 * @var Date
	 */
	private Date dataInclusao;
	
    /**
     * Armazena a lista de imagem de viagem
     * @access private
     * @var List<ImagemViagem>
     */
    private List<ImagemViagem> imagemViagem;
	
	/**
	 * Construtor da classe
	 * @access public
	 * @param Viajante viajante
	 * @param String titulo
	 * @param String descricao
	 * @param Date dataInicial
	 * @param Date dataFinal
	 * @param Date dataInclusao
	 */
	public Viagem(Viajante viajante, String titulo, String descricao, Date dataInicial, Date dataFinal, Date dataInclusao) {
		this.setViajante(viajante);
		this.setTitulo(titulo);
		this.setDescricao(descricao);
		this.setDataInicial(dataInicial);
		this.setDataFinal(dataFinal);
		this.setDataInclusao(dataInclusao);
	}

	/**
	 * Construtor da classe
	 * @access public
	 * @param integer idViagem
	 * @param Viajante viajante
	 * @param String titulo
	 * @param String descricao
	 * @param Date dataInicial
	 * @param Date dataFinal
	 * @param Date dataInclusao
	 */
	public Viagem(int idViagem, Viajante viajante, String titulo, String descricao, Date dataInicial, Date dataFinal, Date dataInclusao) {
		this.idViagem = idViagem;
		this.setViajante(viajante);
		this.setTitulo(titulo);
		this.setDescricao(descricao);
		this.setDataInicial(dataInicial);
		this.setDataFinal(dataFinal);
		this.setDataInclusao(dataInclusao);
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
	 * Retorna o título
	 * @access public
	 * @return String
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Define o título
	 * @access public
	 * @param String titulo
	 * @return void
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
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

	/**
	 * Define a lista de imagens de viagem
	 * @access public
	 * @param ImagemViagem imagemViagem
	 * @return void
	 */
	public void setImagemViagem(List<ImagemViagem> imagemViagem) {
		this.imagemViagem = imagemViagem;
	}
	
	/**
	 * Retorna a lista de imagens de viagem
	 * @access public
	 * @return List<ImagemViagem>
	 */
	public List<ImagemViagem> getImagemViagem() {
		return this.imagemViagem;
	}
}
