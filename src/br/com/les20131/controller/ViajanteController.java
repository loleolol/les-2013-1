package br.com.les20131.controller;

import br.com.les20131.model.bean.ViajanteBean;
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
public class ViajanteController extends BaseController {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	
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
        try {
        	if (acao.isEmpty()) {
               RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/view/login/login.jsp");
               dispatcher.forward(request, response);
           } else if (acao.equalsIgnoreCase("cadastrar")) {
        	   this.incluirViajante(request, response);
        	   response.sendRedirect("/les20131/view/viajante/incluir.jsp");
           } else if (acao.equalsIgnoreCase("alterar perfil")) {
        	   this.verificarSessao(request);
        	   response.sendRedirect("/les20131/view/viajante/alterar.jsp");
           } else if (acao.equalsIgnoreCase("alterar")) {
        	   this.verificarSessao(request);
        	   this.alterarViajante(request, response);
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
     * Efetua a inclusão de novo registro
     * @access private
     * @param HttpServletRequest request
     * @param HttpServletResponse response
     * @return void
     * @throws Exception
     */
    private void incluirViajante(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ViajanteBean viajanteBean = new ViajanteBean();
        this.validarViajante(request.getParameter("email"), request.getParameter("senha")
        		, request.getParameter("confirmaEmail"), request.getParameter("confirmaSenha")
        		, request.getParameter("nome"), request.getParameter("sexo"), request.getParameter("dataNascimento"));
        viajanteBean.incluir(request.getParameter("email"), request.getParameter("nome")
        		, request.getParameter("senha"), request.getParameter("sexo"), request.getParameter("dataNascimento"));
        //request.setAttribute("mensagemBean", new MensagemBean("Marca inserida com sucesso!"));
    }    
    
    private void alterarViajante(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	HttpSession sessao = request.getSession();
        ViajanteBean viajanteBean = new ViajanteBean();
//         this.validarViajante((Usuario)sessao.getAttribute("usuario").getIdUsuario(), request.getParameter("nome"), request.getParameter("sexo"), request.getParameter("dataNascimento"));
//        viajanteBean.alterar(request.getParameter("email"), request.getParameter("nome")
 //       		, request.getParameter("senha"), request.getParameter("sexo"), request.getParameter("dataNascimento"));
    	
    }
    
    /**
     * Valida o viajante
     * @access private
     * @param String email
     * @param String senha
     * @param String confirmaEmail
     * @param String confirmaSenha
     * @param String nome
     * @param String sexo
     * @param String dataNascimento
     * @return void
     * @throws Exception
     */
    private void validarViajante(String email, String senha, String confirmaEmail, String confirmaSenha, String nome, String sexo, String dataNascimento) throws Exception {
        this.validarEmail(email);
    }

    /**
     * Valida o viajante
     * @access private
     * @param String idUsuario
     * @param String nome
     * @param String sexo
     * @param String dataNascimento
     * @return void
     * @throws Exception
     */
    private void validarViajante(String idUsuario, String nome, String sexo, String dataNascimento) throws Exception {

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
