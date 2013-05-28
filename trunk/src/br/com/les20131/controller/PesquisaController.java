package br.com.les20131.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import br.com.les20131.model.Usuario;
import br.com.les20131.model.bean.PesquisaBean;

/**
 * Servlet implementation class Pesquisa
 */
@WebServlet("/Pesquisa")
public class PesquisaController extends BaseController {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PesquisaController() {
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
			if (this.acao.equalsIgnoreCase("pesquisarPrevia")) {
				this.acaoPesquisarPrevia();
			} else if (this.acao.equalsIgnoreCase("pesquisar")) {
				this.acaoPesquisar();
			} else if (this.acao.equalsIgnoreCase("contatos")) {
				this.acaoPesquisarContatos();
			}
		} catch (Exception excecao) {
		    this.tratarExcecao(excecao);
		}
	}
	
	/**
	 * Ação de pesquisa
	 * @access private
	 * @return void
	 * @throws Exception
	 */
	private void acaoPesquisar() throws Exception {
		HttpSession sessao = this.requisicao.getSession();
		PesquisaBean pesquisaBean = new PesquisaBean();
		String criterio = this.requisicao.getParameter("criterio");
		if (criterio != null && criterio.length() > 0) {
			pesquisaBean.pesquisar(criterio, ((Usuario)sessao.getAttribute("usuario")).getIdUsuario());
			this.requisicao.setAttribute("pesquisaBean", pesquisaBean);
		}
		this.despachar("/view/pesquisa/listar.jsp");
	}
	
	/**
	 * Ação de pesquisa prévia
	 * @access private
	 * @return void
	 * @throws Exception
	 */
	private void acaoPesquisarPrevia() throws Exception {
		HttpSession sessao = this.requisicao.getSession();
		PesquisaBean pesquisaBean = new PesquisaBean();
		String criterio = this.requisicao.getParameter("criterio");
		if (criterio != null && criterio.length() > 0) {
			int idUsuario = ((Usuario)sessao.getAttribute("usuario")).getIdUsuario();
			pesquisaBean.pesquisar(criterio, idUsuario);
			this.resposta.setContentType("application/json");
			this.resposta.setCharacterEncoding("UTF-8");
			Gson gson = new Gson();
			String json = gson.toJson(pesquisaBean.getListaResultado());
			json = "{ \"idUsuario\":\""+idUsuario+"\", \"lista\":"+json+" }";
			this.resposta.getWriter().write(json);			
		}
	}
	
	/**
	 * Ação de pesquisa de contatos
	 * @access private
	 * @return void
	 * @throws Exception
	 */
	private void acaoPesquisarContatos() throws Exception {
		HttpSession sessao = this.requisicao.getSession();
		PesquisaBean pesquisaBean = new PesquisaBean();
		String criterio = this.requisicao.getParameter("criterio");
		int id = ((Usuario)sessao.getAttribute("usuario")).getIdUsuario();
		if (criterio != null && criterio.length() > 0) {
			id = Integer.parseInt(criterio);
		}
		pesquisaBean.pesquisarContatos(id);
		this.requisicao.setAttribute("pesquisaBean", pesquisaBean);
		this.despachar("/view/pesquisa/listar.jsp");		
	}
}
