/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.partiu.controller.erro;

import br.com.partiu.model.bean.MensagemBean;
import br.com.partiu.util.UserAuthenticationException;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 200920183
 */
public class ErroController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        RequestDispatcher dispatcher;
        MensagemBean mensagemBean;
        if (request.getAttribute("excecao") == null) {
            mensagemBean = new MensagemBean((new UserAuthenticationException()).getMessage());
            dispatcher = this.getServletContext().getRequestDispatcher("/view/login/login.jsp");
        } else {
            Exception excecao = (Exception)request.getAttribute("excecao");
            mensagemBean = new MensagemBean(excecao.getMessage());
            if (excecao instanceof UserAuthenticationException) {
                dispatcher = this.getServletContext().getRequestDispatcher("/view/login/login.jsp");
            } else {
                dispatcher = this.getServletContext().getRequestDispatcher("/view/erro/erro.jsp");
            }
        }
        request.setAttribute("mensagemBean", mensagemBean);
        dispatcher.forward(request, response);
    } 

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
        processRequest(request, response);
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
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
