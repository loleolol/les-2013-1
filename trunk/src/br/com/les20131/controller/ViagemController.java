package br.com.les20131.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.les20131.model.Usuario;
import br.com.les20131.model.bean.ViagemBean;
import br.com.les20131.model.bean.ViajanteBean;
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
			RequestDispatcher dispatcher;
			if (acao.isEmpty()) {
			   dispatcher = this.getServletContext().getRequestDispatcher("/view/viajante/inicio.jsp");
			   dispatcher.forward(request, response);
			} else if (acao.equalsIgnoreCase("registrar")) {
				this.incluirViagem(request, response);
				this.listarMinhaViagem(request, response);
				dispatcher = this.getServletContext().getRequestDispatcher("/view/viagem/listar.jsp");
				dispatcher.forward(request, response);
			} else if (acao.equalsIgnoreCase("minhas viagens")) {
				this.listarMinhaViagem(request, response);
				dispatcher = this.getServletContext().getRequestDispatcher("/view/viagem/listar.jsp");
				dispatcher.forward(request, response);
			} else if (acao.equalsIgnoreCase("selecionar")) {
        	   this.carregarViagem(request);
        	   dispatcher = this.getServletContext().getRequestDispatcher("/view/viagem/alterar.jsp");
        	   dispatcher.forward(request, response);
			} else if (acao.equalsIgnoreCase("alterar")) {
        	   this.alterarViagem(request, response);
        	   dispatcher = this.getServletContext().getRequestDispatcher("/view/viagem/alterar.jsp");
        	   dispatcher.forward(request, response);
			} else if (acao.equalsIgnoreCase("excluir")) {
				this.excluirViagem(request, response);
				dispatcher = this.getServletContext().getRequestDispatcher("/view/viagem/listar.jsp");
				dispatcher.forward(request, response);
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
     * Lista as viagens cadastradas
     * @access private
     * @param HttpServletRequest request
     * @param HttpServletResponse response
     * @return void
     * @throws Exception
     */
    private void listarMinhaViagem(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	HttpSession sessao = request.getSession();
        ViagemBean viagemBean = new ViagemBean();
        viagemBean.consultarPorViajante(((Usuario)sessao.getAttribute("usuario")).getIdUsuario());
        request.setAttribute("viagemBean", viagemBean);
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
    	HttpSession sessao = request.getSession();
        ViagemBean viagemBean = new ViagemBean();
        String dataInicial = request.getParameter("dataInicialAno") 
        		+ '-' + request.getParameter("dataInicialMes")
        		+ '-' + request.getParameter("dataInicialDia");
        String dataFinal = request.getParameter("dataFinalAno") 
        		+ '-' + request.getParameter("dataFinalMes")
        		+ '-' + request.getParameter("dataFinalDia");
        this.validarViagem(request.getParameter("descricao"), dataInicial, dataFinal);
        viagemBean.incluir(((Usuario)sessao.getAttribute("usuario")).getIdUsuario()
        		, request.getParameter("titulo"), request.getParameter("descricao"), dataInicial, dataFinal);
        //request.setAttribute("mensagemBean", new MensagemBean("Viagem inserida com sucesso!"));
    }
    
    /**
     * Altera uma viagem
     * @param request
     * @param response
     * @throws Exception
     */
    private void alterarViagem(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ViagemBean viagemBean = new ViagemBean();
        String dataInicial = request.getParameter("dataInicialAno") 
        		+ '-' + request.getParameter("dataInicialMes")
        		+ '-' + request.getParameter("dataInicialDia");
        String dataFinal = request.getParameter("dataFinalAno") 
        		+ '-' + request.getParameter("dataFinalMes")
        		+ '-' + request.getParameter("dataFinalDia");
        this.validarViagem(request.getParameter("descricao"), dataInicial, dataFinal);
        viagemBean.alterar(Integer.parseInt(request.getParameter("idViagem"))
        		, request.getParameter("titulo"), request.getParameter("descricao"), dataInicial, dataFinal);
        request.setAttribute("viagemBean", viagemBean);
    }    
    
    /**
     * Carrega informações da viagem
     * @access private
     * @throws Exception 
     * @return void
     */
    private void carregarViagem(HttpServletRequest request) throws Exception {
    	HttpSession sessao = request.getSession();
    	ViagemBean viagemBean = new ViagemBean();
    	viagemBean.consultar(Integer.parseInt(request.getParameter("idViagem")));
    	request.setAttribute("viagemBean", viagemBean);
    }    
    
    /**
     * Efetua a exclusão de novo registro
     * @access private
     * @param HttpServletRequest request
     * @param HttpServletResponse response
     * @return void
     * @throws Exception
     */
    private void excluirViagem(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ViagemBean viagemBean = new ViagemBean();
        viagemBean.excluir(Integer.parseInt(request.getParameter("idViagem")));
    }
    
    /**
     * Valida o viagem
     * @access private
     * @param String descricao
     * @return void
     * @throws Exception
     */
    private void validarViagem(String descricao, String dataInicial, String dataFinal) throws Exception {
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
