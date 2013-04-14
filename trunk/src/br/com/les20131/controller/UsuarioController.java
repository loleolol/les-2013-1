package br.com.les20131.controller;

import br.com.les20131.model.Usuario;
import br.com.les20131.model.bean.UsuarioBean;
import br.com.les20131.model.bean.ViajanteBean;
import br.com.les20131.model.dao.UsuarioDAO;
import br.com.les20131.model.dao.ViajanteDAO;
import br.com.les20131.util.InvalidPageException;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
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
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String acao = (request.getParameter("acao") == null ? "" : request.getParameter("acao"));
        RequestDispatcher dispatcher;
        try {
            if (acao.isEmpty()) {
			    dispatcher = this.getServletContext().getRequestDispatcher("/view/index.jsp");
			    dispatcher.forward(request, response);
            } else if (acao.equalsIgnoreCase("login")) {
                UsuarioBean usuarioBean = new UsuarioBean();
                usuarioBean.autenticaUsuario(request.getParameter("loginEmail"), request.getParameter("loginSenha"));
                HttpSession sessao = request.getSession(true);
                sessao.setAttribute("usuario", usuarioBean.getUsuario());
                this.verificarSessao(request);
                dispatcher = this.getServletContext().getRequestDispatcher("/view/viajante/inicio.jsp");
                dispatcher.forward(request, response);
            } else if (acao.equalsIgnoreCase("logoff")) {
               	request.getSession().invalidate();
                dispatcher = this.getServletContext().getRequestDispatcher("/view/index.jsp");
                dispatcher.forward(request, response);
            } else if (acao.equalsIgnoreCase("cadastre-se")) {
                dispatcher = this.getServletContext().getRequestDispatcher("/view/viajante/incluir.jsp");
                dispatcher.forward(request, response);
            } else if (acao.equalsIgnoreCase("alterar conta")) {
                dispatcher = this.getServletContext().getRequestDispatcher("/view/usuario/alterar.jsp");
                dispatcher.forward(request, response);
            } else if (acao.equalsIgnoreCase("alterar")) {
            	this.verificarSessao(request);
				this.alterarUsuario(request, response);
				dispatcher = this.getServletContext().getRequestDispatcher("/view/usuario/alterar.jsp");
				dispatcher.forward(request, response);
            } else {
               	throw new InvalidPageException();
            }
        } catch (Exception excecao) {
        	request.setAttribute("excecao", excecao);
            dispatcher = this.getServletContext().getRequestDispatcher("/ErroController");
            dispatcher.forward(request, response);
        }
    }

    /**
     * Altera um usuário
     * @param request
     * @param response
     * @throws Exception
     */
    private void alterarUsuario(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	HttpSession sessao = request.getSession();
        UsuarioBean usuarioBean = new UsuarioBean();
        this.validarUsuario(((Usuario)sessao.getAttribute("usuario")).getIdUsuario(), request.getParameter("email"), request.getParameter("confirmaEmail"), request.getParameter("senha"), request.getParameter("confirmaSenha"));
        usuarioBean.alterar(((Usuario)sessao.getAttribute("usuario")).getIdUsuario(), request.getParameter("email"), request.getParameter("senha"));
        sessao.setAttribute("usuario", usuarioBean.getUsuario());
        request.setAttribute("usuarioBean", usuarioBean);
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
    private void validarUsuario(int idUsuario, String email, String confirmaEmail, String senha, String confirmaSenha) throws Exception {
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
