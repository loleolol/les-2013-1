package br.com.les20131.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.les20131.model.Usuario;
import br.com.les20131.model.bean.UsuarioBean;
import br.com.les20131.util.InvalidPageException;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends BaseController {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
        	this.configurarController(request, response);
            if (this.acao.isEmpty()) {
            	this.acaoPadrao();
            } else if (this.acao.equalsIgnoreCase("login")) {
            	this.acaoLogin();
            } else if (this.acao.equalsIgnoreCase("logoff")) {
            	this.acaoLogoff();
            } else if (this.acao.equalsIgnoreCase("novo")) {
            	this.acaoNovo();
            } else {
               	throw new InvalidPageException();
            }
        } catch (Exception excecao) {
        	this.tratarExcecao(excecao);
        }
    }

    /**
     * Ação padrão do controlador
     * @access private
     * @return void
     * @throws Exception
     */
    private void acaoPadrao() throws Exception {
    	this.despachar("/view/index.jsp");
    }
    
    /**
     * Ação para criação de novo usuário
     * @access private
     * @return void
     * @throws Exception
     */
    private void acaoNovo() throws Exception {
    	this.despachar("/view/viajante/incluir.jsp");
    }
    
    /**
     * Ação que realiza o login
     * @access private
     * @return void
     * @throws Exception
     */
    private void acaoLogin() throws Exception {
        UsuarioBean usuarioBean = new UsuarioBean();
        usuarioBean.autenticaUsuario(this.requisicao.getParameter("loginEmail"), this.requisicao.getParameter("loginSenha"));
        HttpSession sessao = this.requisicao.getSession(true);
        sessao.setAttribute("usuario", usuarioBean.getUsuario());
        this.verificarSessao();
    	AtualizacaoController atualizacaoController = new AtualizacaoController();
    	atualizacaoController.requisicao = this.requisicao;
    	atualizacaoController.listarTodasAtualizacoes();    	
        this.despachar("/view/inicio.jsp");
    }
    
    /**
     * Ação que realiza o logoff
     * @access private
     * @return void
     * @throws Exception
     */
    private void acaoLogoff() throws Exception {
       	this.requisicao.getSession().invalidate();
       	this.despachar("/view/index.jsp");
    }

}
