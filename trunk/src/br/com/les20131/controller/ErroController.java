/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.les20131.controller;

import br.com.les20131.model.bean.MensagemBean;
import br.com.les20131.util.InvalidPageException;
import br.com.les20131.util.UserAuthenticationException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 200920183
 */
public class ErroController extends HttpServlet {
   
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    	tratarErro(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    	tratarErro(request, response);
    }

    /**
     * Trata erros da aplicação
     * @param HttpServletRequest request
     * @param HttpServletResponse response
     */
    public void tratarErro(HttpServletRequest request, HttpServletResponse response)
    {
    	try {
	        RequestDispatcher dispatcher;
	        MensagemBean mensagemBean;
	        if (request.getAttribute("excecao") == null) {
	            mensagemBean = new MensagemBean((new UserAuthenticationException()).getMessage());
	            dispatcher = this.getServletContext().getRequestDispatcher("/view/index.jsp");
	        } else {
	           // Exception excecao = ((Exception)request.getAttribute("excecao"));
	            mensagemBean = new MensagemBean(request.getAttribute("excecao").toString());
	            //if (excecao instanceof UserAuthenticationException) {
	                dispatcher = this.getServletContext().getRequestDispatcher("/view/index.jsp");
	            //} else {
	                dispatcher = this.getServletContext().getRequestDispatcher("/view/erro/erro.jsp");
	            //}
	        }
	        request.setAttribute("mensagemBean", mensagemBean);
	        dispatcher.forward(request, response);
    	} catch (Exception excecao) {
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
