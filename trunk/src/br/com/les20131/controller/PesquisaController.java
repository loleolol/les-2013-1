package br.com.les20131.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			this.configurarController(request, response);
			this.verificarSessao();
			if (this.acao.equalsIgnoreCase("pesquisar")) {
				this.acaoPesquisar();
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
		PesquisaBean pesquisaBean = new PesquisaBean();
		pesquisaBean.pesquisar(this.requisicao.getParameter("pesquisar"));
		
	}

}
