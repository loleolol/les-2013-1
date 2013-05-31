package br.com.les20131.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import br.com.les20131.model.Usuario;
import br.com.les20131.model.bean.AvaliacaoBean;
import br.com.les20131.model.bean.ImagemViagemBean;
import br.com.les20131.model.bean.UsuarioBean;
import br.com.les20131.model.bean.ViagemBean;
import br.com.les20131.util.InvalidPageException;

/**
 * Servlet implementation class ModuloAvaliacaoController
 */
@WebServlet("/ModuloAvaliacaoController")
public class ModuloAvaliacaoController extends BaseController {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModuloAvaliacaoController() {
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
				this.acaoPadrao();
			} else if (this.acao.equalsIgnoreCase("incluir")) {
				this.acaoIncluir();
			} else if (this.acao.equalsIgnoreCase("selecionar")) {
				this.acaoSelecionar();
			} else if (this.acao.equalsIgnoreCase("alterar")) {
				this.acaoAlterar();
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
	 * A��o padr�o do controlador
	 * @access private
	 * @return void
	 * @throws ServletException
	 * @throws IOException
	 */
	private void acaoPadrao() throws Exception {
		this.validarIdUsuario(this.requisicao.getParameter("idEmpresa"));
		this.requisicao.setAttribute("idEmpresa", this.requisicao.getParameter("idEmpresa"));
		this.despachar("/view/avaliacao/incluir.jsp");
	}
	
	/**
	 * A��o de inclus�o de avalia��o
	 * @access private
	 * @return void
	 * @throws Exception
	 */
	private void acaoIncluir() throws Exception {
		this.incluirAvaliacao();
    	AtualizacaoController atualizacaoController = new AtualizacaoController();
    	atualizacaoController.requisicao = this.requisicao;
    	atualizacaoController.listarTodasAtualizacoes(); 		
		this.despachar("/view/inicio.jsp");
	}
	
	/**
	 * A��o de sele��o de uma avalia��o
	 * @access private
	 * @return void
	 * @throws Exception
	 */
	private void acaoSelecionar() throws Exception {
		this.carregarAvaliacao();
		this.despachar("/view/avaliacao/alterar.jsp");
	}
	
	/**
	 * A��o de alterar uma avalia��o
	 * @access private
	 * @return void
	 * @throws Exception
	 */
	private void acaoAlterar() throws Exception {
		this.alterarAvaliacao();
		this.despachar("/view/avaliacao/alterar.jsp");
	}
	
	/**
	 * A��o de excluir uma avalia��o
	 * @access private
	 * @return void
	 * @throws Exception
	 */
	private void acaoExcluir() throws Exception {
		this.excluirAvaliacao();
		HttpSession sessao = this.requisicao.getSession();
		if(sessao.getAttribute("administrador") == null){
	    	AtualizacaoController atualizacaoController = new AtualizacaoController();
	    	atualizacaoController.requisicao = this.requisicao;
	    	atualizacaoController.listarTodasAtualizacoes(); 
		}		
		this.despachar("/view/inicio.jsp");
	}
	
    /**
     * Efetua a inclus�o de novo registro
     * @access private
     * @return void
     * @throws Exception
     */
    private void incluirAvaliacao() throws Exception {
    	HttpSession sessao = this.requisicao.getSession();
    	AvaliacaoBean avaliacaoBean = new AvaliacaoBean();
        this.validarAvaliacao(this.requisicao.getParameter("idUsuario"), this.requisicao.getParameter("avaliacao"), this.requisicao.getParameter("descricao"));
        avaliacaoBean.incluir(((Usuario)sessao.getAttribute("usuario")).getIdUsuario()
        		, Integer.parseInt(this.requisicao.getParameter("idUsuario")), Integer.parseInt(this.requisicao.getParameter("avaliacao")), this.requisicao.getParameter("descricao"));
    }
    
    /**
     * Altera uma avalia��o
	 * @access private
	 * @retrun void
     * @throws Exception
     */
    private void alterarAvaliacao() throws Exception {
    	HttpSession sessao = this.requisicao.getSession();
        AvaliacaoBean avaliacaoBean = new AvaliacaoBean();
        this.validarIdAvaliacao(this.requisicao.getParameter("idAvaliacao"));
        this.validarAvaliacao(this.requisicao.getParameter("avaliacao"), this.requisicao.getParameter("descricao"));
        avaliacaoBean.alterar(Integer.parseInt(this.requisicao.getParameter("idAvaliacao"))
        		, Integer.parseInt(this.requisicao.getParameter("avaliacao"))
        		, this.requisicao.getParameter("descricao"));
       	
       	this.requisicao.setAttribute("avaliacaoBean", avaliacaoBean);
    }    
    
    /**
     * Carrega informa��es da avalia��o
     * @access private
     * @throws Exception 
     * @return void
     */
    private void carregarAvaliacao() throws Exception {
    	AvaliacaoBean avaliacaoBean = new AvaliacaoBean();
    	avaliacaoBean.consultar(Integer.parseInt(this.requisicao.getParameter("id")));
    	this.requisicao.setAttribute("avaliacaoBean", avaliacaoBean);
    }    
    
    /**
     * Efetua a exclus�o de novo registro
     * @access private
     * @return void
     * @throws Exception
     */
    private void excluirAvaliacao() throws Exception {
        this.validarIdAvaliacao(this.requisicao.getParameter("id"));
        AvaliacaoBean avaliacaoBean = new AvaliacaoBean();
        avaliacaoBean.excluir(Integer.parseInt(this.requisicao.getParameter("id")));
    }
    
    /**
     * Valida a avalia��o
     * @access private
     * @param String idUsuario
     * @param String avaliacao
     * @param String descricao
     * @return void
     * @throws Exception
     */
    private void validarAvaliacao(String idUsuario, String avaliacao, String descricao) throws Exception {
        this.validarIdUsuario(idUsuario);
        this.validarAvaliacao(avaliacao);
        this.validarDescricao(descricao);
    }    
    
    /**
     * Valida o avalia��o
     * @access private
     * @param String avaliacao
     * @param String descricao
     * @return void
     * @throws Exception
     */
    private void validarAvaliacao(String avaliacao, String descricao) throws Exception {
        this.validarAvaliacao(avaliacao);
        this.validarDescricao(descricao);
    }
    
    /**
     * Validar id da avalia��o
     * @access private
     * @param String idViagem
     * @return void
     * @throws Exception
     */
    private void validarIdAvaliacao(String idAvaliacao) throws Exception {
    	try {
    		int id;
    		id = Integer.parseInt(idAvaliacao);
    		AvaliacaoBean avaliacaoBean = new AvaliacaoBean();
    		avaliacaoBean.consultar(id);
    		if (avaliacaoBean.getAvaliacao() == null) {
    			throw new Exception();
    		}
    	} catch (Exception excecao) {
    		throw new Exception("Avalia��o inv�lida.");
    	}
    }
    
    /**
     * Valida o id do usu�rio
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
			throw new Exception("Usu�rio inv�lido.");
		}
	}   
    
    /**
     * Valida a avalia��o
     * @access private
     * @param String avaliacao
     * @return void
     * @throws Exception
     */
    private void validarAvaliacao(String avaliacao) throws Exception {
    	if (avaliacao.isEmpty() == false) {
	    	try {
	    		Double.parseDouble(avaliacao);
	    	} catch (Exception excecao) {
	    		throw new Exception("Avalia��o inv�lida.");
	    	}
    	}    	
    }
    
    /**
     * Valida a descri��o
     * @access private
     * @param String descri��o
     * @return void
     * @throws Exception
     */
    private void validarDescricao(String descricao) throws Exception {
        if (descricao.isEmpty()) {
            throw new Exception("Descri��o em branco.");
        } else if (descricao.length() > 500) {
            throw new Exception("Descri��o acima do limite de 500 caracteres.");
        }
    }

}
