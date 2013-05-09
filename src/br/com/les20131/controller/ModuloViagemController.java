package br.com.les20131.controller;

import java.io.IOException;
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
import br.com.les20131.model.bean.ImagemViagemBean;
import br.com.les20131.model.bean.MensagemBean;
import br.com.les20131.model.bean.ViagemBean;
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
			//this.acaoCarregarImagemPerfil();
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
			} else if (this.acao.equalsIgnoreCase("listar")) {
				this.acaoListar();
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
	 * Ação padrão do controlador
	 * @access private
	 * @return void
	 * @throws ServletException
	 * @throws IOException
	 */
	private void acaoPadrao() throws Exception {
		this.despachar("/view/viajante/inicio.jsp");
	}
	
	/**
	 * Ação de inclusão de viagem
	 * @access private
	 * @return void
	 * @throws Exception
	 */
	private void acaoIncluir() throws Exception {
		this.incluirViagem();
		this.listarMinhaViagem();
		this.despachar("/view/viagem/listar.jsp");
	}
	
	/**
	 * Ação de listagem de viagem
	 * @access private
	 * @return void
	 * @throws Exception
	 */
	private void acaoListar() throws Exception {
		this.listarMinhaViagem();
		this.despachar("/view/viagem/listar.jsp");
	}
	
	/**
	 * Ação de seleção de uma viagem
	 * @access private
	 * @return void
	 * @throws Exception
	 */
	private void acaoSelecionar() throws Exception {
		this.carregarViagem();
		this.despachar("/view/viagem/alterar.jsp");
	}
	
	/**
	 * Ação de alterar uma viagem
	 * @access private
	 * @return void
	 * @throws Exception
	 */
	private void acaoAlterar() throws Exception {
		this.alterarViagem();
		this.despachar("/view/viagem/alterar.jsp");
	}
	
	/**
	 * Ação de excluir uma viagem
	 * @access private
	 * @return void
	 * @throws Exception
	 */
	private void acaoExcluir() throws Exception {
		this.excluirViagem();
		this.listarMinhaViagem();
		this.despachar("/view/viagem/listar.jsp");
	}
	
    /**
     * Lista as viagens cadastradas
     * @access private
     * @return void
     * @throws Exception
     */
    private void listarMinhaViagem() throws Exception {
    	HttpSession sessao = this.requisicao.getSession();
        ViagemBean viagemBean = new ViagemBean();
        viagemBean.consultarPorViajante(((Usuario)sessao.getAttribute("usuario")).getIdUsuario());
        this.requisicao.setAttribute("viagemBean", viagemBean);
    }	
	
    /**
     * Efetua a inclusão de novo registro
     * @access private
     * @return void
     * @throws Exception
     */
    private void incluirViagem() throws Exception {
    	HttpSession sessao = this.requisicao.getSession();
        ViagemBean viagemBean = new ViagemBean();
        ImagemViagemBean imagemViagemBean = new ImagemViagemBean();
        String dataInicial = this.requisicao.getParameter("dataInicialAno") 
        		+ '-' + this.requisicao.getParameter("dataInicialMes")
        		+ '-' + this.requisicao.getParameter("dataInicialDia");
        String dataFinal = this.requisicao.getParameter("dataFinalAno") 
        		+ '-' + this.requisicao.getParameter("dataFinalMes")
        		+ '-' + this.requisicao.getParameter("dataFinalDia");
        this.validarViagem(this.requisicao.getParameter("titulo"), this.requisicao.getParameter("descricao"), dataInicial, dataFinal);
        viagemBean.incluir(((Usuario)sessao.getAttribute("usuario")).getIdUsuario()
        		, this.requisicao.getParameter("titulo"), this.requisicao.getParameter("descricao"), dataInicial, dataFinal);
        
        int quantidade = Integer.parseInt(this.requisicao.getParameter("quantidadeImagem"));
       	for (int i = 1; i <= quantidade; i++) {
            Part imagemParte = this.requisicao.getPart("imagem"+i);
           	if (imagemParte.getSize() > 0) {
           		imagemViagemBean.incluir(viagemBean.getViagem().getIdViagem(), imagemParte.getInputStream());
            }
       	}
        //this.requisicao.setAttribute("mensagemBean", new MensagemBean("Viagem inserida com sucesso!"));
    }
    
    /**
     * Altera uma viagem
	 * @access private
	 * @retrun void
     * @throws Exception
     */
    private void alterarViagem() throws Exception {
        ViagemBean viagemBean = new ViagemBean();
        String dataInicial = this.requisicao.getParameter("dataInicialAno") 
        		+ '-' + this.requisicao.getParameter("dataInicialMes")
        		+ '-' + this.requisicao.getParameter("dataInicialDia");
        String dataFinal = this.requisicao.getParameter("dataFinalAno") 
        		+ '-' + this.requisicao.getParameter("dataFinalMes")
        		+ '-' + this.requisicao.getParameter("dataFinalDia");
        this.validarViagem(this.requisicao.getParameter("idViagem"), this.requisicao.getParameter("titulo"), this.requisicao.getParameter("descricao"), dataInicial, dataFinal);
        viagemBean.alterar(Integer.parseInt(this.requisicao.getParameter("idViagem"))
        		, this.requisicao.getParameter("titulo"), this.requisicao.getParameter("descricao"), dataInicial, dataFinal);
        this.requisicao.setAttribute("viagemBean", viagemBean);
    }    
    
    /**
     * Carrega informações da viagem
     * @access private
     * @throws Exception 
     * @return void
     */
    private void carregarViagem() throws Exception {
    	ViagemBean viagemBean = new ViagemBean();
    	viagemBean.consultar(Integer.parseInt(this.requisicao.getParameter("idViagem")));
    	this.requisicao.setAttribute("viagemBean", viagemBean);
    	ImagemViagemBean imagemViagemBean = new ImagemViagemBean();
    	imagemViagemBean.consultarPorViagem(Integer.parseInt(this.requisicao.getParameter("idViagem")));
    	this.requisicao.setAttribute("imagemViagemBean", imagemViagemBean);
    }    
    
    /**
     * Efetua a exclusão de novo registro
     * @access private
     * @return void
     * @throws Exception
     */
    private void excluirViagem() throws Exception {
        ViagemBean viagemBean = new ViagemBean();
        viagemBean.excluir(Integer.parseInt(this.requisicao.getParameter("idViagem")));
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
    		throw new Exception("Viagem inválida.");
    	}
    }
    
    /**
     * Valida o título
     * @access private
     * @param String titulo
     * @return void
     * @throws Exception
     */
    private void validarTitulo(String titulo) throws Exception {
    	if (titulo.isEmpty()) {
    		throw new Exception("Título em branco.");
    	} else if (titulo.length() > 50) {
    		throw new Exception("Título acima do limite de 50 caracteres.");
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
     * Valida o descricao
     * @access private
     * @param String desc
     * @return void
     * @throws Exception
     */
    private void validarDescricao(String descricao) throws Exception {
        if (descricao.isEmpty()) {
            throw new Exception("Descrição em branco.");
        } else if (descricao.length() > 500) {
            throw new Exception("Descrição acima do limite de 500 caracteres.");
        }
    }    
	
	
}
