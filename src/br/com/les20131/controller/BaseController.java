package br.com.les20131.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import javax.servlet.http.HttpSession;

import br.com.les20131.model.Usuario;
import br.com.les20131.model.bean.AdministradorBean;
import br.com.les20131.model.bean.UsuarioBean;
import br.com.les20131.model.bean.ViajanteBean;
import br.com.les20131.util.InvalidPageException;
import br.com.les20131.util.UserAuthenticationException;


/**
 * Servlet implementation class BaseController
 */
@WebServlet("/BaseController")
public abstract class BaseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/**
	 * Armazena a requisição
	 * @access protected
	 * @var HttpServletRequest
	 */
	protected HttpServletRequest requisicao;

	/**
	 * Armazena a resposta
	 * @access protected
	 * @var HttpServletResponse
	 */
	protected HttpServletResponse resposta;
	
	/**
	 * Armazena o despachante
	 * @access protected
	 * @var RequestDispatcher
	 */
	protected RequestDispatcher despachante;
	
	/**
	 * Armazena a ação
	 * @access protected
	 * @var acao
	 */
	protected String acao;
	
    /**
     * Construtor do servlet
     * @see HttpServlet#HttpServlet()
     */
    public BaseController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        	if (request.getQueryString() == null) {
        		this.doPost(request, response);
        	} else {
        		throw new InvalidPageException();
        	}
        } catch (Exception excecao) {
			request.setAttribute("excecao", excecao);
			this.despachar("/Erro");
        }
	}
	
	/**
	 * Configura os atributos do controller
	 * @access protected
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return void
	 */
	protected void configurarController(HttpServletRequest request, HttpServletResponse response) {
    	this.requisicao = request;
    	this.resposta = response;
    	this.despachante = null;
    	this.acao = (request.getParameter("acao") == null ? "" : request.getParameter("acao"));
	}
	
	/**
	 * Configura o despachante para o caminho e despacha
	 * @access protected
	 * @param String caminho
	 * @return void
	 * @throws IOException 
	 * @throws ServletException 
	 */
	protected void despachar(String caminho) throws ServletException, IOException {
		this.despachante = this.getServletContext().getRequestDispatcher(caminho);
    	this.despachante.forward(this.requisicao, this.resposta);
	}
	
    /**
     * Verifica a sessão do usuário
     * @access protected
     * @return void
     * @throws Exception
     */
    protected void verificarSessao() throws Exception {
        HttpSession sessao = this.requisicao.getSession(false);
        if (sessao == null) {
            /**
             * Se não há sessão, retorna ao login
             */
            throw new UserAuthenticationException();
        } else if (sessao.getAttribute("usuario") == null) {
            /**
             * Valida usuario, jogando exceção caso inválido
             */
            throw new UserAuthenticationException();
        } else {
        	UsuarioBean usuarioBean = new UsuarioBean();
        	usuarioBean.setUsuario((Usuario)sessao.getAttribute("usuario"));
        	this.requisicao.setAttribute("usuarioBean", usuarioBean);
        	AdministradorBean administradorBean = new AdministradorBean();
            administradorBean.consultar(usuarioBean.getUsuario().getIdUsuario());
        	sessao.setAttribute("administrador", administradorBean.getAdministrador());
			this.requisicao.setAttribute("administradorBean", administradorBean);
        }
    }
    
    /**
     * Carrega uma prévia da imagem para a sessão
     * @access protected
     * @return void
     * @throws IOException
     * @throws ServletException
     */
    protected void acaoCarregarPreviaImagem() throws IOException, ServletException
    {
		HttpSession sessao = this.requisicao.getSession();
    	Part imagemParte = this.requisicao.getPart(this.requisicao.getParameter("nome"));
    	InputStream imagem = imagemParte.getInputStream();
    	ByteArrayOutputStream out = new ByteArrayOutputStream();
    	byte[] bytes = new byte[imagem.available()];
    	int b = imagem.read();
    	while (b != -1) {
    	    out.write(b);
    	    b = imagem.read();
    	}
    	bytes = out.toByteArray();    	
    	
       	sessao.setAttribute(this.requisicao.getParameter("nome"), bytes);    	
    }
    
    /**
     * Prévia de uma imagem via Servlet
     * @access protected
     * @throws IOException
     */
    protected void acaoExibirPreviaImagem() throws IOException 
    {
		HttpSession sessao = this.requisicao.getSession();
		byte[] b = (byte[])sessao.getAttribute(this.requisicao.getParameter("nome"));
		sessao.removeAttribute(this.requisicao.getParameter("nome"));
		this.acaoCarregarImagem(new ByteArrayInputStream(b));
    }
    
    /**
     * Carrega uma imagem para exibição
     * @access protected
     * @param InputStream imagem
     * @return void
     * @throws IOException
     */
    protected void acaoCarregarImagem(InputStream imagem) throws IOException
    {
    	String urlSemImagem = "/les20131/view/publico/imagens/semimagem.png";
		try {
			if (imagem != null) {
				this.resposta.setHeader("Cache-Control", "no-store");  
				this.resposta.setHeader("Pragma", "no-cache");  
				this.resposta.setDateHeader("Expires", 0);  
				this.resposta.setContentType("image/jpeg");
				BufferedImage buffer = ImageIO.read(imagem);
				ServletOutputStream out = this.resposta.getOutputStream(); 
				ImageIO.write(buffer, "jpeg", this.resposta.getOutputStream());  
				this.resposta.resetBuffer();
				this.resposta.reset();
				out.flush();  
				out.close();
			} else {
				this.resposta.sendRedirect(urlSemImagem);
			}
		} catch (Exception e) {
			this.resposta.sendRedirect(urlSemImagem);
		}
    }
    
    /**
     * Tratamento de exceção padrão
     * @access protected
     * @param Exception excecao
     * @return void
     */
    protected void tratarExcecao(Exception excecao)
    {
    	try {
    		this.requisicao.setAttribute("excecao", excecao);
    		this.despachar("/Erro");
    	} catch (Exception tratamentoExcecao) {
    		
    	}
    }
}
