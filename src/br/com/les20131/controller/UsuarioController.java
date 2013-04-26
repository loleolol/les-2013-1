package br.com.les20131.controller;

import br.com.les20131.model.Usuario;
import br.com.les20131.model.bean.UsuarioBean;
import br.com.les20131.util.InvalidPageException;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 200920183
 */
public class UsuarioController extends BaseController {

    /**
	 * 
	 */
	private static final long serialVersionUID = 411902382744835366L;

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
            } else if (this.acao.equalsIgnoreCase("selecionar")) {
            	this.acaoSelecionar();
            } else if (this.acao.equalsIgnoreCase("alterar")) {
            	this.acaoAlterar();
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
        this.despachar("/view/viajante/inicio.jsp");
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
     * Ação para seleção de usuário
     * @access private
     * @return void
     * @throws Exception
     */
    private void acaoSelecionar() throws Exception {
    	this.verificarSessao();
        this.despachar("/view/usuario/alterar.jsp");
    }
    
    /**
     * Ação de alteração de usuário
     * @access private
     * @return void
     * @throws Exception
     */
    private void acaoAlterar() throws Exception {
    	this.verificarSessao();
		this.alterarUsuario();
		this.despachar("/view/usuario/alterar.jsp");
    }
    
    /**
     * Altera um usuário
     * @access private
     * @return void
     * @throws Exception
     */
    private void alterarUsuario() throws Exception {
    	HttpSession sessao = this.requisicao.getSession();
        UsuarioBean usuarioBean = new UsuarioBean();
        this.validarUsuario(((Usuario)sessao.getAttribute("usuario")).getIdUsuario(), this.requisicao.getParameter("email"), this.requisicao.getParameter("emailConfirma"), this.requisicao.getParameter("senha"), this.requisicao.getParameter("senhaConfirma"));
        usuarioBean.alterar(((Usuario)sessao.getAttribute("usuario")).getIdUsuario(), this.requisicao.getParameter("email"), this.requisicao.getParameter("senha"));
        sessao.setAttribute("usuario", usuarioBean.getUsuario());
        this.requisicao.setAttribute("usuarioBean", usuarioBean);
    }
    
    /**
     * Valida o viajante
     * @access private
     * @param int idUsuario
     * @param String nome
     * @param String sexo
     * @param String dataNascimento
     * @return void
     * @throws Exception
     */
    private void validarUsuario(int idUsuario, String email, String emailConfirma, String senha, String senhaConfirma) throws Exception {
    	this.validarEmail(email);
    }
    
    /**
     * Valida o email
     * @access private
     * @param String email
     * @return void
     * @throws Exception
     */
    private void validarEmail(String email) throws Exception {
        if (email.isEmpty()) {
            throw new Exception("E-mail em branco!");
        } else if (email.length() > 100) {
            throw new Exception("E-mail acima do limite de 100 caracteres!");
        }
    }
    
    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        //TODO 
    	return "Short description";
    }// </editor-fold>

}
