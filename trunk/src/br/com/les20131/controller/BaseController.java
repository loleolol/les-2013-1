package br.com.les20131.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import br.com.les20131.model.Usuario;
import br.com.les20131.model.bean.UsuarioBean;
import br.com.les20131.util.InvalidPageException;
import br.com.les20131.util.UserAuthenticationException;


/**
 * Servlet implementation class BaseController
 */
@WebServlet("/BaseController")
public abstract class BaseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/**
	 * Armazena a requisição
	 * @access protected
	 * @var HttpServletRequest
	 */
	protected HttpServletRequest requisicao;

	/**
	 * Armazena a resposta
	 * @access protected
	 * @var HttpServletResponse
	 */
	protected HttpServletResponse resposta;
	
	/**
	 * Armazena o despachante
	 * @access protected
	 * @var RequestDispatcher
	 */
	protected RequestDispatcher despachante;
	
	/**
	 * Armazena a ação
	 * @access protected
	 * @var acao
	 */
	protected String acao;
	
    /**
     * Construtor do servlet
     * @see HttpServlet#HttpServlet()
     */
    public BaseController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        	if (request.getQueryString() == null) {
        		this.doPost(request, response);
        	} else {
        		throw new InvalidPageException();
        	}
        } catch (Exception excecao) {
			request.setAttribute("excecao", excecao);
			this.despachar("/Erro");
        }
	}
	
	/**
	 * Configura os atributos do controller
	 * @access protected
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return void
	 */
	protected void configurarController(HttpServletRequest request, HttpServletResponse response) {
    	this.requisicao = request;
    	this.resposta = response;
    	this.despachante = null;
    	this.acao = (request.getParameter("acao") == null ? "" : request.getParameter("acao"));
	}
	
	/**
	 * Configura o despachante para o caminho e despacha
	 * @access protected
	 * @param String caminho
	 * @return void
	 * @throws IOException 
	 * @throws ServletException 
	 */
	protected void despachar(String caminho) throws ServletException, IOException {
		this.despachante = this.getServletContext().getRequestDispatcher(caminho);
    	this.despachante.forward(this.requisicao, this.resposta);
	}
	
    /**
     * Verifica a sessão do usuário
     * @access protected
     * @return void
     * @throws Exception
     */
    protected void verificarSessao() throws Exception {
        HttpSession sessao = this.requisicao.getSession(false);
        if (sessao == null) {
            /**
             * Se não há sessão, retorna ao login
             */
            throw new UserAuthenticationException();
        } else if (sessao.getAttribute("usuario") == null) {
            /**
             * Valida usuario, jogando exceção caso inválido
             */
            throw new UserAuthenticationException();
        } else {
        	UsuarioBean usuarioBean = new UsuarioBean();
        	usuarioBean.setUsuario((Usuario)sessao.getAttribute("usuario"));
        	this.requisicao.setAttribute("usuarioBean", usuarioBean);
        }
    }
    
    /**
     * Tratamento de exceção padrão
     * @access protected
     * @param Exception excecao
     * @return void
     */
    protected void tratarExcecao(Exception excecao)
    {
    	try {
    		this.requisicao.setAttribute("excecao", excecao);
    		this.despachar("/Erro");
    	} catch (Exception tratamentoExcecao) {
    		
    	}
    }
}
