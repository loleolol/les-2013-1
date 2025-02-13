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
import br.com.les20131.model.bean.AtualizacaoBean;
import br.com.les20131.model.bean.ImagemViagemBean;
import br.com.les20131.model.bean.MensagemBean;
import br.com.les20131.model.bean.ViagemBean;
import br.com.les20131.model.bean.ViajanteBean;
import br.com.les20131.util.InvalidPageException;

/**
 * Servlet implementation class ModuloViagemController
 */
@WebServlet("/ModuloViagemController")
@MultipartConfig
public class ModuloViagemController extends BaseController {
       
    /**
	 * 
	 */
	private static final long serialVersionUID = -7228147691913995357L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public ModuloViagemController() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.configurarController(request, response);
		if (this.acao.equalsIgnoreCase("previrImagem")) {
			this.acaoExibirPreviaImagem();
		} else if (this.acao.equalsIgnoreCase("carregarImagem")) {
			this.acaoCarregarImagemPerfil();
		} else {
			super.doGet(request, response);
		}
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
			} else if (this.acao.equalsIgnoreCase("previrImagem")) {
				this.acaoCarregarPreviaImagem();				
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
		this.despachar("/view/viagem/incluir.jsp");
	}
	
	/**
	 * A��o de inclus�o de viagem
	 * @access private
	 * @return void
	 * @throws Exception
	 */
	private void acaoIncluir() throws Exception {
		this.incluirViagem();
    	AtualizacaoController atualizacaoController = new AtualizacaoController();
    	atualizacaoController.requisicao = this.requisicao;
    	atualizacaoController.listarTodasAtualizacoes(); 		
		this.despachar("/view/inicio.jsp");
	}
	
	/**
	 * A��o de sele��o de uma viagem
	 * @access private
	 * @return void
	 * @throws Exception
	 */
	private void acaoSelecionar() throws Exception {
		this.carregarViagem();
		this.despachar("/view/viagem/alterar.jsp");
	}
	
	/**
	 * A��o de alterar uma viagem
	 * @access private
	 * @return void
	 * @throws Exception
	 */
	private void acaoAlterar() throws Exception {
		this.alterarViagem();
		this.despachar("/view/viagem/alterar.jsp");
	}
	
	/**
	 * A��o de excluir uma viagem
	 * @access private
	 * @return void
	 * @throws Exception
	 */
	private void acaoExcluir() throws Exception {
		this.excluirViagem();
		HttpSession sessao = this.requisicao.getSession();
		if(sessao.getAttribute("administrador") == null){
	    	AtualizacaoController atualizacaoController = new AtualizacaoController();
	    	atualizacaoController.requisicao = this.requisicao;
	    	atualizacaoController.listarTodasAtualizacoes(); 
		}		
		this.despachar("/view/inicio.jsp");
	}
	
    /**
     * A��o de carregamento da imagem do perfil
     * @access private
     * @return void
     * @throws IOException
     */
    private void acaoCarregarImagemPerfil() throws IOException {
    	try {
    		this.validarIdImagemViagem(this.requisicao.getParameter("id"));
			ImagemViagemBean imagemViagemBean = new ImagemViagemBean();
			imagemViagemBean.consultar(Integer.parseInt(this.requisicao.getParameter("id")));
			this.acaoCarregarImagem(imagemViagemBean.getImagemViagem().getImagem()); 	
    	} catch (Exception excecao) {
        	this.acaoCarregarImagem(null);
    	}
    }	

    /**
     * Efetua a inclus�o de novo registro
     * @access private
     * @return void
     * @throws Exception
     */
    private void incluirViagem() throws Exception {
    	HttpSession sessao = this.requisicao.getSession();
        ViagemBean viagemBean = new ViagemBean();
        String dataInicial = this.requisicao.getParameter("dataInicialAno") 
        		+ '-' + this.requisicao.getParameter("dataInicialMes")
        		+ '-' + this.requisicao.getParameter("dataInicialDia");
        String dataFinal = this.requisicao.getParameter("dataFinalAno") 
        		+ '-' + this.requisicao.getParameter("dataFinalMes")
        		+ '-' + this.requisicao.getParameter("dataFinalDia");
        this.validarViagem(this.requisicao.getParameter("titulo"), this.requisicao.getParameter("descricao"), dataInicial, dataFinal);
        viagemBean.incluir(((Usuario)sessao.getAttribute("usuario")).getIdUsuario()
        		, this.requisicao.getParameter("titulo"), this.requisicao.getParameter("descricao"), dataInicial, dataFinal);
        
        this.incluirImagemViagem(viagemBean.getViagem().getIdViagem());
        //this.requisicao.setAttribute("mensagemBean", new MensagemBean("Viagem inserida com sucesso!"));
    }
    
    /**
     * Incluir imagens de viagem
     * @access private
     * @param int idViagem
     * @return void
     * @throws Exception
     */
    private void incluirImagemViagem(int idViagem) throws Exception {
    	ImagemViagemBean imagemViagemBean = new ImagemViagemBean();
    	int quantidade = Integer.parseInt(this.requisicao.getParameter("quantidadeImagem"));
       	for (int i = 1; i <= quantidade; i++) {
            Part imagemParte = this.requisicao.getPart("imagem"+i);
           	if (imagemParte != null){
           		if (imagemParte.getSize() > 0) {
           			imagemViagemBean.incluir(idViagem, imagemParte.getInputStream());
           		}
            }
       	}
    }
    
    /**
     * Altera uma viagem
	 * @access private
	 * @retrun void
     * @throws Exception
     */
    private void alterarViagem() throws Exception {
        ViagemBean viagemBean = new ViagemBean();
        ImagemViagemBean imagemViagemBean = new ImagemViagemBean();
        String dataInicial = this.requisicao.getParameter("dataInicialAno") 
        		+ '-' + this.requisicao.getParameter("dataInicialMes")
        		+ '-' + this.requisicao.getParameter("dataInicialDia");
        String dataFinal = this.requisicao.getParameter("dataFinalAno") 
        		+ '-' + this.requisicao.getParameter("dataFinalMes")
        		+ '-' + this.requisicao.getParameter("dataFinalDia");
        this.validarViagem(this.requisicao.getParameter("idViagem"), this.requisicao.getParameter("titulo"), this.requisicao.getParameter("descricao"), dataInicial, dataFinal);
        viagemBean.alterar(Integer.parseInt(this.requisicao.getParameter("idViagem"))
        		, this.requisicao.getParameter("titulo"), this.requisicao.getParameter("descricao"), dataInicial, dataFinal);
        
        this.alterarImagemViagem(viagemBean.getViagem().getIdViagem());
       	
       	imagemViagemBean.consultar(viagemBean.getViagem());
       	this.requisicao.setAttribute("viagemBean", viagemBean);
    	this.requisicao.setAttribute("imagemViagemBean", imagemViagemBean);
    }    
    
    /**
     * Alterar uma imagem de viagem
     * @access private
     * @param int idViagem
     * @return void
     * @throws Exception
     */
    private void alterarImagemViagem(int idViagem) throws Exception {
        ImagemViagemBean imagemViagemBean = new ImagemViagemBean();
        int quantidade = Integer.parseInt(this.requisicao.getParameter("quantidadeImagem"));
       	for (int i = 1; i <= quantidade; i++) {
            Part imagemParte = this.requisicao.getPart("imagem"+i);
           	if (imagemParte != null) {
           		if (imagemParte.getSize() > 0) {
           			imagemViagemBean.incluir(idViagem, imagemParte.getInputStream());
           		}
            }
       		String idImagemViagem = this.requisicao.getParameter("idImagemViagem"+i);
           	if (idImagemViagem != null) {
           		try {
	           		this.validarIdImagemViagem(idImagemViagem);
	           		imagemViagemBean.excluir(Integer.parseInt(idImagemViagem));
           		} catch (Exception excecao) {
           		}
           	}
       	}     	
    }
    
    /**
     * Carrega informa��es da viagem
     * @access private
     * @throws Exception 
     * @return void
     */
    private void carregarViagem() throws Exception {
    	ViagemBean viagemBean = new ViagemBean();
    	viagemBean.consultar(Integer.parseInt(this.requisicao.getParameter("id")));
    	this.requisicao.setAttribute("viagemBean", viagemBean);
    	ImagemViagemBean imagemViagemBean = new ImagemViagemBean();
    	imagemViagemBean.consultar(viagemBean.getViagem());
    	this.requisicao.setAttribute("imagemViagemBean", imagemViagemBean);
    }    
    
    /**
     * Efetua a exclus�o de novo registro
     * @access private
     * @return void
     * @throws Exception
     */
    private void excluirViagem() throws Exception {
        this.validarIdViagem(this.requisicao.getParameter("id"));
        ViagemBean viagemBean = new ViagemBean();
        viagemBean.excluir(Integer.parseInt(this.requisicao.getParameter("id")));
    }
    
    /**
     * Valida o viagem
     * @access private
     * @param String idViagem
     * @param String titulo
     * @param String descricao
     * @param String dataInicial
     * @param String dataFinal
     * @return void
     * @throws Exception
     */
    private void validarViagem(String idViagem, String titulo, String descricao, String dataInicial, String dataFinal) throws Exception {
    	this.validarIdViagem(idViagem);
        this.validarTitulo(titulo);
        this.validarDescricao(descricao);
        this.validarDataInicial(dataInicial);
        this.validarDataFinal(dataFinal);
    }    
    
    /**
     * Valida o viagem
     * @access private
     * @param String titulo
     * @param String descricao
     * @param String dataInicial
     * @param String dataFinal
     * @return void
     * @throws Exception
     */
    private void validarViagem(String titulo, String descricao, String dataInicial, String dataFinal) throws Exception {
        this.validarTitulo(titulo);
        this.validarDescricao(descricao);
        this.validarDataInicial(dataInicial);
        this.validarDataFinal(dataFinal);
    }
    
    /**
     * Validar id da viagem
     * @access private
     * @param String idViagem
     * @return void
     * @throws Exception
     */
    private void validarIdViagem(String idViagem) throws Exception {
    	try {
    		int id;
    		id = Integer.parseInt(idViagem);
    		ViagemBean viagemBean = new ViagemBean();
    		viagemBean.consultar(id);
    		if (viagemBean.getViagem() == null) {
    			throw new Exception();
    		}
    	} catch (Exception excecao) {
    		throw new Exception("Viagem inv�lida.");
    	}
    }
    
    /**
     * Valida o t�tulo
     * @access private
     * @param String titulo
     * @return void
     * @throws Exception
     */
    private void validarTitulo(String titulo) throws Exception {
    	if (titulo.isEmpty()) {
    		throw new Exception("T�tulo em branco.");
    	} else if (titulo.length() > 50) {
    		throw new Exception("T�tulo acima do limite de 50 caracteres.");
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
    		throw new Exception("Data inicial inv�lida.");
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
    		throw new Exception("Data final inv�lida.");
    	}
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
            throw new Exception("Descri��o em branco.");
        } else if (descricao.length() > 500) {
            throw new Exception("Descri��o acima do limite de 500 caracteres.");
        }
    }    
    
    /**
     * Valida o id da imagem de viagem
     * @access private
     * @param String idImagemViagem
     * @return void
     * @throws Exception
     */
    private void validarIdImagemViagem(String idImagemViagem) throws Exception {
    	try {
			int id;
			id = Integer.parseInt(idImagemViagem);
		} catch (Exception excecao) {
			throw new Exception("Imagem de viagem inv�lida.");
		}
	}    
	
	
}
