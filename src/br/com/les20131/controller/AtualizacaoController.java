package br.com.les20131.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.les20131.model.Usuario;
import br.com.les20131.model.bean.AtualizacaoBean;
import br.com.les20131.model.bean.PesquisaBean;

/**
 * Servlet implementation class AtualizacaoController
 */
@WebServlet("/AtualizacaoController")
public class AtualizacaoController extends BaseController {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AtualizacaoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			this.configurarController(request, response);
			this.verificarSessao();
			if (this.acao.equalsIgnoreCase("todasAtualizacoes")) {
				this.acaoListarTodasAtualizacoes();
			} else if (this.acao.equalsIgnoreCase("atualizacoes")) {
				this.acaoListarAtualizacoes();
			} else if (this.acao.equalsIgnoreCase("viagens")) {
				this.acaoListarViagens();
			} else if (this.acao.equalsIgnoreCase("avaliacoes")) {
				this.acaoListarAvaliacoes();
			} else if (this.acao.equalsIgnoreCase("anuncios")) {
				this.acaoListarAnuncios();
			}
		} catch (Exception excecao) {
		    this.tratarExcecao(excecao);
		}
	}
	
	
	/**
	 * Lista todas as atualiza��es
	 * @access private
	 * @return void
	 * @throws Exception
	 */
	private void acaoListarTodasAtualizacoes() throws Exception {
		HttpSession sessao = this.requisicao.getSession();
		this.listarTodasAtualizacoes();
		this.despachar("/view/atualizacao/listar.jsp");
	}
	
	/**
	 * A��o para listar atualiza��es
	 * @access private
	 * @return void
	 * @throws Exception
	 */
	private void acaoListarAtualizacoes() throws Exception {
		HttpSession sessao = this.requisicao.getSession();
		this.listarAtualizacoes((Usuario)sessao.getAttribute("usuario"));
        this.despachar("/view/atualizacao/listar.jsp");		
	}
	
	/**
	 * A��o para listar viagens
	 * @access private
	 * @return void
	 * @throws Exception
	 */
	private void acaoListarViagens() throws Exception {
		HttpSession sessao = this.requisicao.getSession();
		this.listarViagens((Usuario)sessao.getAttribute("usuario"));
		this.requisicao.setAttribute("criterio", "Viagem");
        this.despachar("/view/atualizacao/listar.jsp");
	}
	
	/**
	 * A��o para listar avalia��es
	 * @access private
	 * @return void
	 * @throws Exception
	 */
	private void acaoListarAvaliacoes() throws Exception {
		HttpSession sessao = this.requisicao.getSession();
		this.listarAvaliacoes((Usuario)sessao.getAttribute("usuario"));
		this.requisicao.setAttribute("criterio", "Avaliacao");
        this.despachar("/view/atualizacao/listar.jsp");
	}
	
	/**
	 * A��o para listar anuncios
	 * @access private
	 * @return void
	 * @throws Exception
	 */
	private void acaoListarAnuncios() throws Exception {
		HttpSession sessao = this.requisicao.getSession();
		this.listarAnuncios((Usuario)sessao.getAttribute("usuario"));
		this.requisicao.setAttribute("criterio", "Anuncio");
        this.despachar("/view/atualizacao/listar.jsp");
	}
	
	
	/**
	 * Lista as atualiza��es de todos os usu�rios
	 * @access public
	 * @return void
	 * @throws Exception
	 */
	public void listarTodasAtualizacoes() throws Exception {
		HttpSession sessao = this.requisicao.getSession();
        AtualizacaoBean atualizacaoBean = new AtualizacaoBean();
        atualizacaoBean.listarTodasAtualizacoes((Usuario)sessao.getAttribute("usuario"));
        this.requisicao.setAttribute("atualizacaoBean", atualizacaoBean);	
	}
	
	/**
	 * Lista as atualiza��es do usu�rio passado por par�metro
	 * @access public
	 * @param Usuario usuario
	 * @return void
 	 * @throws Exception
	 */
	public void listarAtualizacoes(Usuario usuario) throws Exception {
        AtualizacaoBean atualizacaoBean = new AtualizacaoBean();
        atualizacaoBean.listarAtualizacoes(usuario);
        this.requisicao.setAttribute("atualizacaoBean", atualizacaoBean);			
	}
	
	/**
	 * Lista as viagens do usu�rio passado por par�metro
	 * @access public
	 * @param Usuario usuario
	 * @return void
	 * @throws Exception
	 */
	public void listarViagens(Usuario usuario) throws Exception {
        AtualizacaoBean atualizacaoBean = new AtualizacaoBean();
        atualizacaoBean.listarViagens(usuario);
        this.requisicao.setAttribute("atualizacaoBean", atualizacaoBean);		
	}

	/**
	 * Lista as avalia��es do usu�rio passado por par�metro
	 * @access public
	 * @param Usuario usuario
	 * @return void
	 * @throws Exception
	 */
	public void listarAvaliacoes(Usuario usuario) throws Exception {
        AtualizacaoBean atualizacaoBean = new AtualizacaoBean();
        atualizacaoBean.listarAvaliacoes(usuario);
        this.requisicao.setAttribute("atualizacaoBean", atualizacaoBean);		
	}
	
	/**
	 * Lista os an�ncios do usu�rio passado por par�metro
	 * @access public
	 * @param Usuario usuario
	 * @return void
	 * @throws Exception
	 */
	public void listarAnuncios(Usuario usuario) throws Exception {
        AtualizacaoBean atualizacaoBean = new AtualizacaoBean();
        atualizacaoBean.listarAnuncios(usuario);
        this.requisicao.setAttribute("atualizacaoBean", atualizacaoBean);		
	}
	
}
