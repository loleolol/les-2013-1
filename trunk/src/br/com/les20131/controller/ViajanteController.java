package br.com.les20131.controller;

import br.com.les20131.model.Usuario;
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
    	String acao = (request.getParameter("acao") == null ? "alterar perfil" : request.getParameter("acao"));
    	RequestDispatcher dispatcher;
        try {
        	if (acao.isEmpty()) {
        		this.verificarSessao(request);
                dispatcher = this.getServletContext().getRequestDispatcher("/view/viajante/inicio.jsp");
                dispatcher.forward(request, response);
           } else if (acao.equalsIgnoreCase("cadastrar")) {
        	   this.incluirViajante(request, response);
        	   this.verificarSessao(request);
        	   dispatcher = this.getServletContext().getRequestDispatcher("/view/viajante/inicio.jsp");
        	   dispatcher.forward(request, response);
           } else if (acao.equalsIgnoreCase("alterar perfil")) {
        	   this.verificarSessao(request);
        	   this.carregarViajante(request);
        	   dispatcher = this.getServletContext().getRequestDispatcher("/view/viajante/alterar.jsp");
        	   dispatcher.forward(request, response);
           } else if (acao.equalsIgnoreCase("alterar conta")) {
        	   this.verificarSessao(request);
        	   //request.setAttribute("origem", "viajante");
        	   dispatcher = this.getServletContext().getRequestDispatcher("/UsuarioController");
        	   dispatcher.forward(request, response);
           } else if (acao.equalsIgnoreCase("alterar")) {
        	   this.verificarSessao(request);
        	   this.alterarViajante(request, response);
        	   dispatcher = this.getServletContext().getRequestDispatcher("/view/viajante/alterar.jsp");
        	   dispatcher.forward(request, response);
           } else if (acao.equalsIgnoreCase("início")) {
        	   this.verificarSessao(request);
        	   dispatcher = this.getServletContext().getRequestDispatcher("/view/viajante/inicio.jsp");
        	   dispatcher.forward(request, response);
           } else if (acao.equalsIgnoreCase("minhas viagens")) {
        	   this.verificarSessao(request);
        	   dispatcher = this.getServletContext().getRequestDispatcher("/ViagemController");
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
     * Efetua a inclusão de novo registro
     * @access private
     * @param HttpServletRequest request
     * @param HttpServletResponse response
     * @return void
     * @throws Exception
     */
    private void incluirViajante(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	HttpSession sessao = request.getSession(true);
        ViajanteBean viajanteBean = new ViajanteBean();
        String dataNascimento = request.getParameter("dataNascimentoAno") 
        		+ '-' + request.getParameter("dataNascimentoMes")
        		+ '-' + request.getParameter("dataNascimentoDia");
        this.validarViajante(request.getParameter("email"), request.getParameter("senha")
        		, request.getParameter("confirmaEmail"), request.getParameter("confirmaSenha")
        		, request.getParameter("nome"), request.getParameter("sexo"), dataNascimento);
        viajanteBean.incluir(request.getParameter("email"), request.getParameter("nome")
        		, request.getParameter("senha"), request.getParameter("sexo"), dataNascimento);
        sessao.setAttribute("usuario", (Usuario)viajanteBean.getViajante());
        //request.setAttribute("mensagemBean", new MensagemBean("Viajante inserido com sucesso!"));
    }    
    
    /**
     * @throws Exception 
     * 
     */
    private void carregarViajante(HttpServletRequest request) throws Exception {
    	HttpSession sessao = request.getSession();
    	ViajanteBean viajanteBean = new ViajanteBean();
    	viajanteBean.consultar(((Usuario)sessao.getAttribute("usuario")).getIdUsuario());
    	request.setAttribute("viajanteBean", viajanteBean);
    }
    
    /**
     * Altera um viajante
     * @param request
     * @param response
     * @throws Exception
     */
    private void alterarViajante(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	HttpSession sessao = request.getSession();
        ViajanteBean viajanteBean = new ViajanteBean();
        String dataNascimento = request.getParameter("dataNascimentoAno") 
        		+ '-' + request.getParameter("dataNascimentoMes")
        		+ '-' + request.getParameter("dataNascimentoDia");
        this.validarViajante(((Usuario)sessao.getAttribute("usuario")).getIdUsuario(), request.getParameter("nome"), request.getParameter("sexo"), dataNascimento);
        viajanteBean.alterar(((Usuario)sessao.getAttribute("usuario")).getIdUsuario(), request.getParameter("nome"), request.getParameter("sexo"), dataNascimento);
        request.setAttribute("viajanteBean", viajanteBean);
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
     * @param int idUsuario
     * @param String nome
     * @param String sexo
     * @param String dataNascimento
     * @return void
     * @throws Exception
     */
    private void validarViajante(int idUsuario, String nome, String sexo, String dataNascimento) throws Exception {
    	this.validarNome(nome);
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
     * Valida o nome
     * @access private
     * @param String nome
     * @return void
     * @throws Exception
     */
    private void validarNome(String nome) throws Exception {
        if (nome.isEmpty()) {
            throw new Exception("Nome em branco!");
        } else if (nome.length() > 100) {
            throw new Exception("Nome acima do limite de 100 caracteres!");
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
