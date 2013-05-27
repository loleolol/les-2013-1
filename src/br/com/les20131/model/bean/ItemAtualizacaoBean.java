package br.com.les20131.model.bean;

import java.util.Date;
import java.util.List;

/**
 * Classe que armazena item de atualização
 * @author 200920183
 *
 */
public class ItemAtualizacaoBean {

	/**
	 * Armazena id do item de atualização
	 * @access private
	 * @var int
	 */
	private int id;
	
	/**
	 * Armazena a ação do item de atualização
	 * @access private
	 * @var String
	 */
	private String acao;
	
	/**
	 * Armazena o autor do item de atualização
	 * @access private
	 * @var String
	 */
	private String autor;
	
	/**
	 * Armazena o id do autor do item de atualização
	 * @access private
	 * @var int
	 */
	private int idAutor;
	
	/**
	 * Armazena o nome do item de atualização
	 * @access private
	 * @var String
	 */
	private String nome;
	
	/**
	 * Armazena a lista de subitens do item de atualização
	 * @access private
	 * @var List<Integer>
	 */
	private List<Integer> listaId;
	
	/**
	 * Armazena o texto do item de atualização
	 * @access private
	 * @var String
	 */
	private String texto;
	
	/**
	 * Armazena a data de inclusão
	 */
	private Date dataInclusao;

	/**
	 * Construtor da classe
	 * @access public
	 * @param int id
	 * @param String acao
	 * @param String autor
	 * @param int idAutor
	 * @param String nome
	 * @param List<Integer> listaId
	 * @param String texto
	 * @para Date dataInclusao
	 */
	public ItemAtualizacaoBean(int id, String acao, String autor, int idAutor, String nome, List<Integer> listaId, String texto, Date dataInclusao) {
		this.id = id;
		this.acao = acao;
		this.autor = autor;
		this.idAutor = idAutor;
		this.nome = nome;
		this.listaId = listaId;
		this.texto = texto;
		this.dataInclusao = dataInclusao;
	}

	/**
	 * Retorna id do item de atualização
	 * @access public
	 * @return int
	 */
	public int getId() {
		return id;
	}

	/**
	 * Define id do item de atualização
	 * @access public
	 * @param int id
	 * @return void
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Retorna ação do item de atualização
	 * @access public
	 * @return String
	 */
	public String getAcao() {
		return acao;
	}

	/**
	 * Define a ação do item de atualização
	 * @access public
	 * @param String acao
	 * @return void
	 */
	public void setAcao(String acao) {
		this.acao = acao;
	}

	/**
	 * Retorna o autor do item de atualização
	 * @access public
	 * @return String
	 */
	public String getAutor() {
		return autor;
	}

	/**
	 * Define o autor do item de atualização
	 * @access public
	 * @param String autor
	 * @return void
	 */
	public void setAutor(String autor) {
		this.autor = autor;
	}

	/**
	 * Retorna o id do autor do item de atualização
	 * @access public
	 * @return int
	 */
	public int getIdAutor() {
		return idAutor;
	}

	/**
	 * Define o id do autor do item de atualização 
	 * @access public
	 * @param int idAutor
	 * @return void
	 */
	public void setIdAutor(int idAutor) {
		this.idAutor = idAutor;
	}

	/**
	 * Retorna o nome do item de atualização
	 * @access public
	 * @return String
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Define o nome do item de atualização
	 * @access public
	 * @param String nome
	 * @return void
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Retorna a lista de sub itens do item de atualização
	 * @access public
	 * @return List<Integer>
	 */
	public List<Integer> getListaId() {
		return listaId;
	}

	/**
	 * Define a lista de sub itens do item de atualização
	 * @access public
	 * @param List<Integer> listaId
	 * @return void
	 */
	public void setListaId(List<Integer> listaId) {
		this.listaId = listaId;
	}

	/**
	 * Retorna o texto do item de atualização
	 * @access public
	 * @return String
	 */
	public String getTexto() {
		return texto;
	}
	
	/**
	 * Define o texto do item de atualização
	 * @access public
	 * @param String texto
	 * @return void
	 */
	public void setTexto(String texto) {
		this.texto = texto;
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
