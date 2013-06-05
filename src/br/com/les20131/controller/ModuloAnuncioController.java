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

import br.com.les20131.model.Usuario;
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
	 * Ação padrão do controlador
	 * @access private
	 * @return void
	 * @throws ServletException
	 * @throws IOException
	 */
	private void acaoPadrao() throws Exception {
		this.despachar("/view/anuncio/incluir.jsp");
	}
	
	/**
	 * Ação de inclusão de anuncio
	 * @access private
	 * @return void
	 * @throws Exception
	 */
	private void acaoIncluir() throws Exception {
		this.incluirAnuncio();
    	AtualizacaoController atualizacaoController = new AtualizacaoController();
    	atualizacaoController.requisicao = this.requisicao;
    	atualizacaoController.listarTodasAtualizacoes(); 		
		this.despachar("/view/inicio.jsp");
	}
	
	/**
	 * Ação de seleção de um anuncio
	 * @access private
	 * @return void
	 * @throws Exception
	 */
	private void acaoSelecionar() throws Exception {
		this.carregarAnuncio();
		this.despachar("/view/anuncio/alterar.jsp");
	}
	
	/**
	 * Ação de alterar uma anuncio
	 * @access private
	 * @return void
	 * @throws Exception
	 */
	private void acaoAlterar() throws Exception {
		this.alterarAnuncio();
		this.despachar("/view/anuncio/alterar.jsp");
	}
	
	/**
	 * Ação de excluir um anuncio
	 * @access private
	 * @return void
	 * @throws Exception
	 */
	private void acaoExcluir() throws Exception {
		this.excluirAnuncio();
		HttpSession sessao = this.requisicao.getSession();
		if(sessao.getAttribute("administrador") == null){
	    	AtualizacaoController atualizacaoController = new AtualizacaoController();
	    	atualizacaoController.requisicao = this.requisicao;
	    	atualizacaoController.listarTodasAtualizacoes(); 
		}		
		this.despachar("/view/inicio.jsp");
	}    

    /**
     * Efetua a inclusão de novo registro
     * @access private
     * @return void
     * @throws Exception
     */
    private void incluirAnuncio() throws Exception {
    	HttpSession sessao = this.requisicao.getSession();
        AnuncioBean anuncioBean = new AnuncioBean();
        String dataInicial = this.requisicao.getParameter("dataInicialAno") 
        		+ '-' + this.requisicao.getParameter("dataInicialMes")
        		+ '-' + this.requisicao.getParameter("dataInicialDia");
        String dataFinal = this.requisicao.getParameter("dataFinalAno") 
        		+ '-' + this.requisicao.getParameter("dataFinalMes")
        		+ '-' + this.requisicao.getParameter("dataFinalDia");
        this.validarAnuncio(this.requisicao.getParameter("anuncio"), dataInicial, dataFinal);
        anuncioBean.incluir(((Usuario)sessao.getAttribute("usuario")).getIdUsuario()
        		, this.requisicao.getParameter("anuncio"), dataInicial, dataFinal);
    }   
    
    /**
     * Altera um anuncio
	 * @access private
	 * @retrun void
     * @throws Exception
     */
    private void alterarAnuncio() throws Exception {
        AnuncioBean anuncioBean = new AnuncioBean();        
        String dataInicial = this.requisicao.getParameter("dataInicialAno") 
        		+ '-' + this.requisicao.getParameter("dataInicialMes")
        		+ '-' + this.requisicao.getParameter("dataInicialDia");
        String dataFinal = this.requisicao.getParameter("dataFinalAno") 
        		+ '-' + this.requisicao.getParameter("dataFinalMes")
        		+ '-' + this.requisicao.getParameter("dataFinalDia");
        this.validarAnuncio(this.requisicao.getParameter("idAnuncio"), this.requisicao.getParameter("anuncio"), dataInicial, dataFinal);
        anuncioBean.alterar(Integer.parseInt(this.requisicao.getParameter("idAnuncio"))
        		, this.requisicao.getParameter("anuncio"), dataInicial, dataFinal);
        
       	this.requisicao.setAttribute("anuncioBean", anuncioBean);
    }        
    
    /**
     * Carrega informações do anuncio
     * @access private
     * @throws Exception 
     * @return void
     */
    private void carregarAnuncio() throws Exception {
    	AnuncioBean anuncioBean = new AnuncioBean();
    	anuncioBean.consultar(Integer.parseInt(this.requisicao.getParameter("id")));
    	this.requisicao.setAttribute("anuncioBean", anuncioBean);
    }    
    
    /**
     * Efetua a exclusão de novo registro
     * @access private
     * @return void
     * @throws Exception
     */
    private void excluirAnuncio() throws Exception {
        this.validarIdAnuncio(this.requisicao.getParameter("id"));
        AnuncioBean anuncioBean = new AnuncioBean();
        anuncioBean.excluir(Integer.parseInt(this.requisicao.getParameter("id")));
    }
    
    /**
     * Valida o anuncio
     * @access private
     * @param String idAnuncio
     * @param String anuncio
     * @param String dataInicial
     * @param String dataFinal
     * @return void
     * @throws Exception
     */
    private void validarAnuncio(String idAnuncio, String anuncio, String dataInicial, String dataFinal) throws Exception {
    	this.validarIdAnuncio(idAnuncio);
        this.validarAnuncio(anuncio);
        this.validarDataInicial(dataInicial);
        this.validarDataFinal(dataFinal);
    }    
    
    /**
     * Valida o anuncio
     * @access private
     * @param String anuncio
     * @param String dataInicial
     * @param String dataFinal
     * @return void
     * @throws Exception
     */
    private void validarAnuncio(String anuncio, String dataInicial, String dataFinal) throws Exception {
        this.validarAnuncio(anuncio);
        this.validarDataInicial(dataInicial);
        this.validarDataFinal(dataFinal);
    }
    
    /**
     * Validar id do anuncio
     * @access private
     * @param String idAnuncio
     * @return void
     * @throws Exception
     */
    private void validarIdAnuncio(String idAnuncio) throws Exception {
    	try {
    		int id;
    		id = Integer.parseInt(idAnuncio);
    		AnuncioBean anuncioBean = new AnuncioBean();
    		anuncioBean.consultar(id);
    		if (anuncioBean.getAnuncio() == null) {
    			throw new Exception();
    		}
    	} catch (Exception excecao) {
    		throw new Exception("Anuncio inválido.");
    	}
    }
    
    /**
     * Valida a data inicial
     * @access private
     * @param String dataInicial
     * @return void
     * @throws Exception
     */
    private void validarDataInicial(String dataInicial) throws Exception {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	try {
    		dateFormat.parse(dataInicial);
    	} catch (Exception excecao) {
    		throw new Exception("Data inicial inválida.");
    	}
    }

    /**
     * Valida a data final
     * @access private
     * @param String dataFinal
     * @return void
     * @throws Exception
     */
    private void validarDataFinal(String dataFinal) throws Exception {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	try {
    		dateFormat.parse(dataFinal);
    	} catch (Exception excecao) {
    		throw new Exception("Data final inválida.");
    	}
    }
        
    /**
     * Valida o anuncio
     * @access private
     * @param String desc
     * @return void
     * @throws Exception
     */
    private void validarAnuncio(String anuncio) throws Exception {
        if (anuncio.isEmpty()) {
            throw new Exception("Anúncio em branco.");
        } else if (anuncio.length() > 500) {
            throw new Exception("Anúncio acima do limite de 500 caracteres.");
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
        this.requisicao.setAttribute("anuncioEmpresaBean", anuncioBean);    	
    }

	
}
