package br.com.les20131.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.les20131.model.bean.ViajanteBean;

/**
 * Servlet implementation class ImagemController
 */
@WebServlet("/ImagemController")
public class ImagemController extends HttpServlet {
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
			String id = request.getParameter("id");
			if (id.isEmpty() == false) {
				response.setHeader("Cache-Control", "no-store");  
				response.setHeader("Pragma", "no-cache");  
				response.setDateHeader("Expires", 0);  
				response.setContentType("image/jpeg");  
				ViajanteBean viajanteBean = new ViajanteBean();
				try {
				viajanteBean.consultar(Integer.parseInt(id));
				} catch (Exception e) {
					viajanteBean = null;
				}
				ServletOutputStream out = response.getOutputStream();  
				BufferedImage buffer = ImageIO.read(viajanteBean.getViajante().getImagem());  
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
