package br.com.les20131.model.bean;

/**
 * 
 * @author 200920183
 *
 */
public class ItemPesquisadoBean {

	/**
	 * Armazena o id do item pesquisado
	 * @access private
	 * @var int
	 */
	private int id;
	
	/**
	 * Armazena a identificacao do item pesquisado
	 * @access private
	 * @var String
	 */
	private String identificacao;
	
	/**
	 * Armazena a previa do item pesquisado
	 * @access private
	 * @var String
	 */
	private String previa;
	
	/**
	 * Tipo de item de pesquisa
	 * @access private
	 * @var String
	 */
	private String tipo;
	
	/**
	 * Construtor da classe
	 * @access public
	 */
	public ItemPesquisadoBean() {
		
	}
	
	/**
	 * Construtor da classe
	 * @access public
	 * @param int id
	 * @param String identificacao
	 * @param String previa
	 */
	public ItemPesquisadoBean(int id, String identificacao, String previa, String tipo) {
		this.id = id;
		this.identificacao = identificacao;
		this.previa = previa;
		this.tipo = tipo;
	}
	
	/**
	 * Retorna o id do item pesquisado
	 * @access public
	 * @return int
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Define o id do item pesquisado
	 * @access public
	 * @param int id
	 * @return void
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Retorna a identificação do item pesquisado
	 * @access public
	 * @return String
	 */
	public String getIdentificacao() {
		return identificacao;
	}
	
	/**
	 * Define a identificação do item pesquisado
	 * @access public
	 * @param String identificacao
	 * @return void
	 */
	public void setIdentificacao(String identificacao) {
		this.identificacao = identificacao;
	}

	/**
	 * Retorna a prévia do item pesquisado
	 * @access public
	 * @return String
	 */
	public String getPrevia() {
		return previa;
	}

	/**
	 * Define a prévia do item pesquisado
	 * @access public
	 * @param String previa
	 * @return void
	 */
	public void setPrevia(String previa) {
		this.previa = previa;
	}

	/**
	 * Retorna o tipo do item pesquisado
	 * @access public
	 * @return String
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Define o tipo do item pesquisado
	 * @access public
	 * @param String tipo
	 * @return void
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
}
