package br.com.les20131.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.les20131.model.Usuario;
import br.com.les20131.model.bean.ContatoBean;
import br.com.les20131.model.bean.ImagemViagemBean;
import br.com.les20131.model.bean.UsuarioBean;
import br.com.les20131.model.bean.ViagemBean;
import br.com.les20131.util.InvalidPageException;

/**
 * Servlet implementation class ModuloContatoController
 */
@WebServlet("/Contato")
public class ModuloContatoController extends BaseController {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModuloContatoController() {
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
			if (this.acao.equalsIgnoreCase("incluir")) {
				this.acaoIncluir();
			} else if (this.acao.equalsIgnoreCase("excluir")) {
				this.acaoExcluir();
			} else {
				throw new InvalidPageException();
			}
		} catch (Exception excecao) {
			this.tratarExcecao(excecao);
		}
	}
	
	/**
	 * Ação de inclusão de contato
	 * @access private
	 * @return void
	 * @throws Exception
	 */
	private void acaoIncluir() throws Exception {
		this.incluirContato();
	}	

	/**
	 * Ação de exclusão de contato
	 * @access private
	 * @return void
	 * @throws Exception
	 */
	private void acaoExcluir() throws Exception {
		this.excluirContato();
	}
	
    /**
     * Efetua a inclusão de novo registro
     * @access private
     * @return void
     * @throws Exception
     */
    private void incluirContato() throws Exception {
    	HttpSession sessao = this.requisicao.getSession();
    	ContatoBean contatoBean = new ContatoBean();
        this.validarIdUsuario(this.requisicao.getParameter("idUsuario"));
        contatoBean.incluir(((Usuario)sessao.getAttribute("usuario")).getIdUsuario()
        		, Integer.parseInt(this.requisicao.getParameter("idUsuario")));
        //this.requisicao.setAttribute("mensagemBean", new MensagemBean("Viagem inserida com sucesso!"));
    }
    
    /**
     * Efetua a exclusão de registro
     * @access private
     * @return void
     * @throws Exception
     */
    private void excluirContato() throws Exception {
    	HttpSession sessao = this.requisicao.getSession();
        ContatoBean contatoBean = new ContatoBean();
        this.validarIdUsuario(this.requisicao.getParameter("idUsuario"));
        contatoBean.excluir(((Usuario)sessao.getAttribute("usuario")).getIdUsuario(), Integer.parseInt(this.requisicao.getParameter("idUsuario")));
    }
    
    /**
     * Valida o id do usuário
     * @access private
     * @param String idUsuario
     * @return void
     * @throws Exception
     */
    private void validarIdUsuario(String idUsuario) throws Exception {
    	try {
			int id;
			id = Integer.parseInt(idUsuario);
    		UsuarioBean usuarioBean = new UsuarioBean();
            usuarioBean.consultar(id);
    		if (usuarioBean.getUsuario() == null) {
    			throw new Exception();
    		}
		} catch (Exception excecao) {
			throw new Exception("Usuário inválido.");
		}
	}    
	
}
