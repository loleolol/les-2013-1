package br.com.les20131.controller;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import br.com.les20131.model.bean.AnuncioBean;
import br.com.les20131.model.bean.AtualizacaoBean;
import br.com.les20131.model.bean.MensagemBean;
import br.com.les20131.util.InvalidPageException;

/**
 * Servlet implementation class ModuloAnuncioController
 */
@WebServlet("/ModuloAnuncioController")
public class ModuloAnuncioController extends BaseController {
       
    /**
	 * 
	 */
	private static final long serialVersionUID = -7228147691913995357L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public ModuloAnuncioController() {
        super();
        // TODO Auto-generated constructor stub
    }   
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			this.configurarController(request, response);
			this.verificarSessao();
			if (this.acao.isEmpty()) {
			} else {
				throw new InvalidPageException();
			}
		} catch (Exception excecao) {
			this.tratarExcecao(excecao);
		}

	}


    /**
     * Listar anúncios ativos
     * @access private
     * @return void
     * @throws Exception
     */
    private void listarAnuncioAtivos() throws Exception {
        AnuncioBean anuncioBean = new AnuncioBean();
        anuncioBean.consultarAtivos();
        this.requisicao.setAttribute("anuncioBean", anuncioBean);    	
    }

	
}
