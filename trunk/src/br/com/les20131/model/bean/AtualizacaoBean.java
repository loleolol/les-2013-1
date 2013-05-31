package br.com.les20131.model.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.les20131.model.Avaliacao;
import br.com.les20131.model.Empresa;
import br.com.les20131.model.ImagemViagem;
import br.com.les20131.model.Usuario;
import br.com.les20131.model.Viagem;
import br.com.les20131.model.Viajante;

/**
 * Classe de atualizações
 * @author 200920183
 *
 */
public class AtualizacaoBean {

	/**
	 * Armazena lista de itens de atualização
	 * @access private
	 * @var List<ItemAtualizacaoBean>
	 */
	private List<ItemAtualizacaoBean> listaAtualizacao;
	
	/**
	 * Construtor da classe
	 * @access public
	 */
	public AtualizacaoBean() {
		this.listaAtualizacao = new ArrayList<ItemAtualizacaoBean>();
	}

	/**
	 * Retorna a lista de itens de atualização
	 * @access public
	 * @return List<ItemAtualizacaoBean>
	 */
	public List<ItemAtualizacaoBean> getListaAtualizacao() {
		return listaAtualizacao;
	}

	/**
	 * Define a lista de itens de atualização
	 * @accessp public
	 * @param List<ItemAtualizacaoBean> listaAtualizacao
	 * @return void
	 */
	public void setListaAtualizacao(List<ItemAtualizacaoBean> listaAtualizacao) {
		this.listaAtualizacao = listaAtualizacao;
	}
	
	/**
	 * Lista todas as atualizações
	 * @access public
	 * @param Usuario usuario
	 * @throws Exception
	 */
	public void listarTodasAtualizacoes(Usuario usuario) throws Exception {
		ViajanteBean viajanteBean = new ViajanteBean();
		viajanteBean.consultarContatos(usuario.getIdUsuario());
		this.listarViagens(usuario);
		this.listarAvaliacoes(usuario);
		int indice;
		for (indice = 0; indice < viajanteBean.getListaViajante().size(); indice++) {
			this.listarViagens(viajanteBean.getListaViajante().get(indice));
			this.listarContatos(viajanteBean.getListaViajante().get(indice));
			this.listarAvaliacoes(viajanteBean.getListaViajante().get(indice));
		}
		this.ordernarAtualizacoes();
	}

	/**
	 * Ordena as atualizações
	 * @access private
	 * @return void
	 */
	private void ordernarAtualizacoes() {
		Collections.sort(this.listaAtualizacao, new Comparator<ItemAtualizacaoBean>() {
		    public int compare(ItemAtualizacaoBean o1, ItemAtualizacaoBean o2) {
		        return o2.getDataInclusao().compareTo(o1.getDataInclusao());
		    }
		});		
	}
	
	/**
	 * Lista as atualizações do usuário
	 * @access public
	 * @param Usuario usuario
	 * @return void
	 * @throws Exception
	 */
	public void listarAtualizacoes(Usuario usuario) throws Exception {
		this.listarViagens(usuario);
		this.listarContatos(usuario);
		this.listarAvaliacoes(usuario);
		this.ordernarAtualizacoes();
	}
	
	/**
	 * Lista de contatos do usuário
	 * @access public
	 * @param Usuario usuario
	 * @return void
	 * @throws Exception
	 */
	public void listarContatos(Usuario usuario) throws Exception {
		ViajanteBean viajanteBean = new ViajanteBean();
		viajanteBean.consultarContatos(usuario.getIdUsuario());
		int indice;
		List<Viajante> lista = viajanteBean.getListaViajante();
		ContatoBean contatoBean = new ContatoBean();
		for (indice = 0; indice < lista.size(); indice++) {
			contatoBean.consultar(usuario.getIdUsuario(), lista.get(indice).getIdUsuario());
			this.listaAtualizacao.add(new ItemAtualizacaoBean(lista.get(indice).getIdUsuario()
					, "Contato", usuario.getNome(), usuario.getIdUsuario()
					, lista.get(indice).getNome(), null, lista.get(indice).getEmail()
					, contatoBean.getContato().getDataInclusao()));
		}
	}
	
	/**
	 * Lista as viagens do usuário
	 * @access public
	 * @param Usuario usuario
	 * @return void
	 * @throws Exception
	 */
	public void listarViagens(Usuario usuario) throws Exception {
		ViagemBean viagemBean = new ViagemBean();
		viagemBean.consultar(usuario, true);
        List<Viagem> lista = viagemBean.getListaViagem();
        int indice1;
        int indice2;
        List<Integer> listaItem;
        List<ImagemViagem> listaImagemViagem;
        for (indice1 = 0; indice1 < lista.size(); indice1++) {
        	listaItem = new ArrayList<Integer>();
        	listaImagemViagem = lista.get(indice1).getImagemViagem();
        	if (listaImagemViagem.size() > 0) {
	        	for (indice2 = 0; indice2 < listaImagemViagem.size(); indice2++) {
	        		listaItem.add(listaImagemViagem.get(indice2).getIdImagemViagem());
	        	}
        	} else {
        		listaItem = null;
        	}
        	this.listaAtualizacao.add(new ItemAtualizacaoBean(lista.get(indice1).getIdViagem()
        			, "Viagem", usuario.getNome(), usuario.getIdUsuario()
        			, lista.get(indice1).getTitulo(), listaItem, lista.get(indice1).getDescricao()
        			, lista.get(indice1).getDataInclusao()));
        }
	}
	
	/**
	 * Lista as avaliações do usuário
	 * @access public
	 * @param Usuario usuario
	 * @return void
	 * @throws Exception
	 */
	public void listarAvaliacoes(Usuario usuario) throws Exception {
		AvaliacaoBean avaliacaoBean = new AvaliacaoBean();
		ViajanteBean viajanteBean = new ViajanteBean();
		viajanteBean.consultar(usuario.getIdUsuario());
		if (viajanteBean.getViajante() != null) {
			avaliacaoBean.consultar(viajanteBean.getViajante());
		} else {
			EmpresaBean empresaBean = new EmpresaBean();
			empresaBean.consultar(usuario.getIdUsuario());
			avaliacaoBean.consultar(empresaBean.getEmpresa());
		}
        List<Avaliacao> lista = avaliacaoBean.getListaAvaliacao();
        List<Integer> listaId = new ArrayList<Integer>();
        int indice;
        int indice2;
        for (indice = 0; indice < lista.size(); indice++) {
        	for (indice2 = 1; indice2 <= lista.get(indice).getAvaliacao(); indice2++) {
        		listaId.add(indice2);
        	}
        	this.listaAtualizacao.add(new ItemAtualizacaoBean(lista.get(indice).getIdAvaliacao()
        			, "Avaliacao", usuario.getNome(), usuario.getIdUsuario()
        			, lista.get(indice).getEmpresa().getNome(), listaId, lista.get(indice).getDescricao()
        			, lista.get(indice).getDataInclusao()));
        }
	}	
			
}
