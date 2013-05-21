package br.com.les20131.model.bean;

import java.util.ArrayList;
import java.util.List;

import br.com.les20131.model.Viajante;

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
		List<Viajante> lista = viajanteBean.getListaViajante();
		for (indice = 0; indice < viajanteBean.getListaViajante().size(); indice++) {
			this.listaResultado.add(new ItemPesquisadoBean(lista.get(indice).getIdUsuario()
					, lista.get(indice).getNome(), lista.get(indice).getEmail()));
		}
	}
	
}
