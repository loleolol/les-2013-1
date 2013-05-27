package br.com.les20131.model.bean;

import java.util.ArrayList;
import java.util.List;

import br.com.les20131.model.Viajante;

/**
 * Bean para pesquisas
 * @author 200920183
 */
public class PesquisaBean {

	/**
	 * Lista de itens de resultado de pesquisa
	 * @access private
	 * @var List<ItemPesquisadoBean>
	 */
	private List<ItemPesquisadoBean> listaResultado;
	
	/**
	 * Critério
	 * @access private
	 * @var String
	 */
	private String criterio;
	
	/**
	 * Construtor da classe
	 * @access public
	 */
	public PesquisaBean() {
		this.listaResultado = new ArrayList<ItemPesquisadoBean>();
	}
	
	/**
	 * Retorna a lista de resultado
	 * @access public
	 * @return List<ItemPesquisadoBean>
	 */
	public List<ItemPesquisadoBean> getListaResultado() {
		return this.listaResultado;
	}
	
	/**
	 * Retorna o critério da pesquisa
	 * @access public
	 * @return String
	 */
	public String getCriterio() {
		return this.criterio;
	}
	
	/**
	 * Define o critério da pesquisa
	 * @access public
	 * @param String criterio
	 * @return void
	 */
	public void setCriterio(String criterio) {
		this.criterio = criterio;
	}
	
	/**
	 * Realiza a pesquisa
	 * @access public
	 * @param String criterio
	 * @param int idUsuario
	 * @throws Exception
	 */
	public void pesquisar(String criterio, int idUsuario) throws Exception {
		this.criterio = criterio;
		ViajanteBean viajanteBean = new ViajanteBean();
		viajanteBean.consultar(criterio);
		int indice;
		List<Viajante> lista = viajanteBean.getListaViajante();
		for (indice = 0; indice < viajanteBean.getListaViajante().size(); indice++) {
			ContatoBean contatoBean = new ContatoBean();
			contatoBean.consultar(idUsuario, lista.get(indice).getIdUsuario());
			boolean flag = (contatoBean.getContato() != null ? true : false);
			this.listaResultado.add(new ItemPesquisadoBean(lista.get(indice).getIdUsuario()
					, lista.get(indice).getNome(), lista.get(indice).getEmail()
					, flag,"Viajante"));
		}
	}
	
	/**
	 * Realiza a pesquisa por contatos
	 * @access public
	 * @param int idUsuario
	 * @throws Exception
	 */
	public void pesquisarContatos(int idUsuario) throws Exception {
		ViajanteBean viajanteBean = new ViajanteBean();
		viajanteBean.consultarContatos(idUsuario);
		int indice;
		List<Viajante> lista = viajanteBean.getListaViajante();
		for (indice = 0; indice < viajanteBean.getListaViajante().size(); indice++) {
			this.listaResultado.add(new ItemPesquisadoBean(lista.get(indice).getIdUsuario()
					, lista.get(indice).getNome(), lista.get(indice).getEmail()
					, true,"Viajante"));
		}		
	}
	
}
