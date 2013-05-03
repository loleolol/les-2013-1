package br.com.les20131.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.les20131.model.bean.ViajanteBean;

/**
 * Servlet implementation class ImagemController
 */
@WebServlet("/ImagemController")
public class ImagemController extends BaseController {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImagemController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			this.configurarController(request, response);
			String id = request.getParameter("id");
			if (id.isEmpty() == false) {
				response.setHeader("Cache-Control", "no-store");  
				response.setHeader("Pragma", "no-cache");  
				response.setDateHeader("Expires", 0);  
				response.setContentType("image/jpeg");  
				ViajanteBean viajanteBean = new ViajanteBean();
				BufferedImage buffer = null;
				try {
					viajanteBean.consultar(Integer.parseInt(id));
					if (viajanteBean.getViajante().getImagem() != null) {
						buffer = ImageIO.read(viajanteBean.getViajante().getImagem());  
					} else {
						response.sendRedirect("/les20131/view/publico/imagens/semimagem.jpg");
					}
				} catch (Exception e) {
					response.sendRedirect("/les20131/view/publico/imagens/semimagem.jpg");
				}
				ServletOutputStream out = response.getOutputStream(); 
				ImageIO.write(buffer, "jpeg", response.getOutputStream());  
				out.flush();  
				out.close();
			} else if (this.acao.isEmpty() == false) {
				HttpSession sessao = this.requisicao.getSession();
				InputStream imagem = (InputStream)sessao.getAttribute("imagem");
				BufferedImage buffer = null;
				buffer = ImageIO.read(imagem);
				ServletOutputStream out = response.getOutputStream(); 
				ImageIO.write(buffer, "jpeg", response.getOutputStream());  
				out.flush();  
				out.close();				
			}
		} catch (Exception excecao) {
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
