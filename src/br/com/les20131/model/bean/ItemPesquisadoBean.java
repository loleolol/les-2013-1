package br.com.les20131.model.bean;

/**
 * 
 * @author 200920183
 *
 */
public class ItemPesquisadoBean {

	/**
	 * Armazena a identificacao do item pesquisado
	 */
	private String identificacao;
	
	/**
	 * Armazena a previa do item pesquisado
	 * @access private
	 * @var String
	 */
	private String previa;
	
	//private IMAGEM
	
	/**
	 * Construtor da class
	 * @access public
	 */
	public ItemPesquisadoBean() {
		
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
	
}
