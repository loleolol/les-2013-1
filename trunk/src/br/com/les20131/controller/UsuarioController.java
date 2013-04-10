package br.com.les20131.controller;

import br.com.les20131.model.bean.UsuarioBean;
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
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        //TODO 
    	return "Short description";
    }// </editor-fold>

}
