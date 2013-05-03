package br.com.les20131.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import br.com.les20131.model.bean.ViajanteBean;

/**
 * Servlet implementation class ImagemPreviaController
 */
@WebServlet("/ImagemPreviaController")
public class ImagemPreviaController extends BaseController {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImagemPreviaController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	this.configurarController(request, response);
		HttpSession sessao = this.requisicao.getSession();
    	InputStream imagem = null;
       	Part imagemParte = this.requisicao.getPart("imagem");
       	if (imagemParte.getSize() > 0) {
    	    imagem = imagemParte.getInputStream();
        }
       	sessao.setAttribute("imagem", imagem);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
