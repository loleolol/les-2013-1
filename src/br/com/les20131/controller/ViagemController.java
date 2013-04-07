package br.com.les20131.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.les20131.model.bean.ViagemBean;
import br.com.les20131.util.InvalidPageException;

/**
 * Servlet implementation class ViagemController
 */
@WebServlet("/ViagemController")
public class ViagemController extends BaseController {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViagemController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acao = (request.getParameter("acao") == null ? "" : request.getParameter("acao"));
        try {
            this.verificarSessao(request);
        	if (acao.isEmpty()) {
               RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/view/viagem/cadastro.jsp");
               dispatcher.forward(request, response);
           } else if (acao.equalsIgnoreCase("cadastrar")) {
        	   this.incluirViagem(request, response);
        	   response.sendRedirect("/les20131/view/viagem/incluir.jsp");
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
    private void incluirViagem(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ViagemBean viagemBean = new ViagemBean();
        this.validarViagem(request.getParameter("descricao"));
        viagemBean.incluir(request.getParameter("descricao"));
        //request.setAttribute("mensagemBean", new MensagemBean("Marca inserida com sucesso!"));
    }    
    
    /**
     * Valida o viagem
     * @access private
     * @param String descricao
     * @return void
     * @throws Exception
     */
    private void validarViagem(String descricao) throws Exception {
        this.validarDescricao(descricao);
    }
   
    /**
     * Valida o descricao
     * @access private
     * @param String desc
     * @return void
     * @throws Exception
     */
    private void validarDescricao(String descricao) throws Exception {
        if (descricao.isEmpty()) {
            throw new Exception("Descrição em branco!");
        } else if (descricao.length() > 500) {
            throw new Exception("Descrição acima do limite de 500 caracteres!");
        }
    }    
	
	
}
