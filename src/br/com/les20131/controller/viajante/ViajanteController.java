package br.com.les20131.controller.viajante;

import br.com.les20131.model.bean.UsuarioBean;
import br.com.les20131.util.InvalidPageException;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 200920183
 */
public class ViajanteController extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.setAttribute("excecao", new InvalidPageException());
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/ErroController");
        dispatcher.forward(request, response);
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
        String acao = (request.getParameter("acao") == null ? "" : request.getParameter("acao"));
        UsuarioBean usuarioBean = new UsuarioBean();
        try {
            if (acao.isEmpty()) {
               RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/view/login/login.jsp");
               dispatcher.forward(request, response);
           } else if (acao.equalsIgnoreCase("cadastrar")) {
        	   response.sendRedirect("/les20131/view/viajante/cadastro.jsp");
           } else {
               throw new InvalidPageException();
           }
        } catch (Exception excecao) {
            request.setAttribute("excecao", excecao);
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/ErroController");
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
