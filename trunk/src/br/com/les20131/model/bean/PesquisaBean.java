package br.com.les20131.model.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author 200920183
 */
public class PesquisaBean {

	private List<ItemPesquisadoBean> listaResultado;
	
	public PesquisaBean() {
		this.listaResultado = new ArrayList<ItemPesquisadoBean>();
	}
	
	public List getListaResultado() {
		return this.listaResultado;
	}
	
	public void pesquisar(String criterio) throws Exception {
		ViajanteBean viajanteBean = new ViajanteBean();
		viajanteBean.consultar(criterio);
		int indice;
		for (indice = 0; indice < viajanteBean.getListaViajante().size(); indice++) {
			
		}
	}
	
}
