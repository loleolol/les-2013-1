package br.com.les20131.controller;

import br.com.les20131.model.Usuario;
import br.com.les20131.model.bean.AvaliacaoBean;
import br.com.les20131.model.bean.ContatoBean;
import br.com.les20131.model.bean.UsuarioBean;
import br.com.les20131.model.bean.EmpresaBean;
import br.com.les20131.util.InvalidPageException;
import br.com.les20131.util.UserAuthenticationException;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author 200910001
 */
@MultipartConfig
public class ModuloEmpresaController extends BaseController {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4226184306349339560L;

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
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
		try {
			this.configurarController(request, response);
			if (this.acao.isEmpty()) {
				this.acaoPadrao();
			} else if (this.acao.equalsIgnoreCase("incluir")) {
				this.acaoInserir();
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
     * @throws Exception
     */
    private void acaoPadrao() throws Exception {
		this.verificarSessao();
    	AtualizacaoController atualizacaoController = new AtualizacaoController();
    	atualizacaoController.requisicao = this.requisicao;
    	atualizacaoController.listarTodasAtualizacoes();    	
		this.despachar("/view/inicio.jsp");
    }
    
    /**
     * Ação de seleção do registro
     * @access private
     * @return void
     * @throws Exception
     */
    private void acaoSelecionar() throws Exception {
    	this.verificarSessao();
    	this.carregarEmpresa();
    	this.despachar("/view/empresa/alterar.jsp");
    }
    
    /**
     * Ação de inserção do registro
     * @access private
     * @return void
     * @throws Exception
     */
    private void acaoInserir() throws Exception {
    	this.incluirEmpresa();
    	AtualizacaoController atualizacaoController = new AtualizacaoController();
    	atualizacaoController.requisicao = this.requisicao;
    	atualizacaoController.listarTodasAtualizacoes();    	
    	this.despachar("/view/inicio.jsp");
    }
    
    /**
     * Ação de alteração do registro
     * @access private
     * @return void
     * @throws Exception
     */
    private void acaoAlterar() throws Exception {
    	this.verificarSessao();
		this.alterarEmpresa();
		this.despachar("/view/empresa/alterar.jsp");
    }
    
    /**
     * Ação de exclusão do registro
     * @access private
     * @return void
     * @throws Exception
     */
    private void acaoExcluir() throws Exception {
    	this.verificarSessao();
		this.excluirEmpresa();
		this.requisicao.getSession().invalidate();
       	this.despachar("/view/index.jsp");
    }
    
    /**
     * Ação de carregamento da imagem do perfil
     * @access private
     * @return void
     * @throws IOException
     */
    private void acaoCarregarImagemPerfil() throws IOException {
    	InputStream imagem = null;
    	try {
    		this.validarIdUsuario(this.requisicao.getParameter("id"));
			EmpresaBean empresaBean = new EmpresaBean();
	    	empresaBean.consultar(Integer.parseInt(this.requisicao.getParameter("id")));
	    	imagem = empresaBean.getEmpresa().getImagem(); 	
    	} catch (Exception excecao) {
    	}
    	this.acaoCarregarImagem(imagem);
    }
    
    /**
     * Efetua a inclusão de novo registro
     * @access private
     * @return void
     * @throws Exception
     */
    private void incluirEmpresa() throws Exception {
    	HttpSession sessao = this.requisicao.getSession(true);
    	EmpresaBean empresaBean = new EmpresaBean();
        this.validarEmpresa(this.requisicao.getParameter("emailEmpresa"), this.requisicao.getParameter("senhaEmpresa")
        		, this.requisicao.getParameter("emailEmpresaConfirma"), this.requisicao.getParameter("senhaEmpresaConfirma")
        		, this.requisicao.getParameter("nomeEmpresa"));
        empresaBean.incluir(this.requisicao.getParameter("emailEmpresa"), this.requisicao.getParameter("nomeEmpresa")
        		, this.requisicao.getParameter("senhaEmpresa"));
        sessao.setAttribute("usuario", (Usuario)empresaBean.getEmpresa());
        this.verificarSessao();
    }    
    
    /**
     * Carrega informações da empresa
     * @access private
     * @throws Exception 
     * @return void
     */
    private void carregarEmpresa() throws Exception {
    	HttpSession sessao = this.requisicao.getSession();
    	EmpresaBean empresaBean = new EmpresaBean();
    	int id = ((Usuario)sessao.getAttribute("usuario")).getIdUsuario();

    	if (this.requisicao.getParameter("id") != null) {
    		this.validarIdUsuario(this.requisicao.getParameter("id"));
    		id = Integer.parseInt(this.requisicao.getParameter("id"));
    		empresaBean.consultar(id);
    		
        	AvaliacaoBean avaliacaoBean = new AvaliacaoBean();
        	avaliacaoBean.consultar(empresaBean.getEmpresa());
        	if (avaliacaoBean.getAvaliacao() != null) {
        		this.requisicao.setAttribute("idAvaliacao", avaliacaoBean.getAvaliacao().getIdAvaliacao());
        	}
        	AtualizacaoController atualizacaoController = new AtualizacaoController();
        	atualizacaoController.requisicao = this.requisicao;
        	atualizacaoController.listarAtualizacoes((Usuario)empresaBean.getEmpresa());
    	} else {
    		empresaBean.consultar(id);
    	}
    	
    	this.requisicao.setAttribute("empresaBean", empresaBean);
    }
    
    /**
     * Altera uma empresa
     * @access private
     * @return void
     * @throws Exception
     */
    private void alterarEmpresa() throws Exception {
    	HttpSession sessao = this.requisicao.getSession();
    	EmpresaBean empresaBean = new EmpresaBean();
    	InputStream imagem = null;
       	Part imagemParte = this.requisicao.getPart("imagem");
       	if (imagemParte.getSize() > 0) {
    	    imagem = imagemParte.getInputStream();
        }
        this.validarEmpresa(((Usuario)sessao.getAttribute("usuario")).getIdUsuario(), this.requisicao.getParameter("nomeEmpresa"), this.requisicao.getParameter("url"), this.requisicao.getParameter("definicao"));
        empresaBean.alterar(((Usuario)sessao.getAttribute("usuario")).getIdUsuario(), this.requisicao.getParameter("nomeEmpresa"), this.requisicao.getParameter("url"), this.requisicao.getParameter("definicao"), imagem);
        this.requisicao.setAttribute("empresaBean", empresaBean);      		
    }
    
    /**
     * Exclui uma empresa
     * @access private
     * @return void
     * @throws Exception
     */
    private void excluirEmpresa() throws Exception {
    	HttpSession sessao = this.requisicao.getSession();
    	EmpresaBean empresaBean = new EmpresaBean();
    	this.validarEmpresa(((Usuario)sessao.getAttribute("usuario")).getIdUsuario(), this.requisicao.getParameter("nomeEmpresa"), this.requisicao.getParameter("url"), this.requisicao.getParameter("definicao"));
        empresaBean.excluir(((Usuario)sessao.getAttribute("usuario")).getIdUsuario());
        this.requisicao.setAttribute("empresaBean", null);
        this.requisicao.setAttribute("usuarioBean", null);      		
    }
    
    
    /**
     * Valida a empresa
     * @access private
     * @param String email
     * @param String senha
     * @param String emailConfirma
     * @param String senhaConfirma
     * @param String nome
     * @return void
     * @throws Exception
     */
    private void validarEmpresa(String email, String senha, String emailConfirma, String senhaConfirma, String nome) throws Exception {
        this.validarEmail(email);
        this.validarEmail(emailConfirma);
        this.validarConfirmacaoEmail(email, emailConfirma);
        this.validarExisteEmail(email);
        this.validarSenha(senha);
        this.validarSenha(senhaConfirma);
        this.validarConfirmacaoSenha(senha, senhaConfirma);
        this.validarNome(nome);
    }

    /**
     * Valida se o email existe
     * @access private
     * @param String email
     * @return void
     * @throws Exception
     */
    private void validarExisteEmail(String email) throws UserAuthenticationException {
    	try {
	    	UsuarioBean usuarioBean = new UsuarioBean();
	    	usuarioBean.consultarPorEmail(email);
	    	if (usuarioBean.getUsuario() != null) {
	            throw new UserAuthenticationException("E-mail já registrado no sistema.");
	    	}
    	} catch (Exception excecao) {
    		throw new UserAuthenticationException("E-mail já registrado no sistema.");
    	}
    }
    
    /**
     * Valida a empresa
     * @access private
     * @param int idUsuario
     * @param String nome
     * @param String url
     * @param String definicao
     * @return void
     * @throws Exception
     */
    private void validarEmpresa(int idUsuario, String nome, String url, String definicao) throws Exception {
        this.validarIdUsuario(idUsuario);
    	this.validarNome(nome);
        this.validarUrl(url);
        this.validarDefinicao(definicao);
    }
    
    /**
     * Valida o id do usuário
     * @access private
     * @param int idUsuario
     * @return void
     * @throws Exception
     */
    private void validarIdUsuario(int idUsuario) throws Exception {
    	try {
    		UsuarioBean usuarioBean = new UsuarioBean();
            usuarioBean.consultar(idUsuario);
    		if (usuarioBean.getUsuario() == null) {
    			throw new Exception();
    		}
    	} catch (Exception excecao) {
    		throw new Exception("Usuário inválido.");
    	} 
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
			this.validarIdUsuario(id);
		} catch (Exception excecao) {
			throw new Exception("Usuário inválido.");
		}
	}
    
    /**
     * Valida o email
     * @access private
     * @param String email
     * @return void
     * @throws Exception
     */
    private void validarEmail(String email) throws Exception {
        if (email.isEmpty()) {
            throw new Exception("E-mail em branco.");
        } else if (email.length() > 100) {
            throw new Exception("E-mail acima do limite de 100 caracteres.");
        }
    }
    
    /**
     * Valida a senha
     * @access private
     * @param String senha
     * @return void
     * @throws Exception
     */
    private void validarSenha(String senha) throws Exception {
    	if (senha.isEmpty()) {
    		throw new Exception("Senha em branco.");
    	} else if (senha.length() > 40) {
    		throw new Exception("Senha acima do limite de 40 caracteres.");
    	}
    }
    
    /**
     * Valida a confirmação de senha
     * @access private
     * @param String senha
     * @param String senhaConfirma
     * @return void
     * @throws Exception
     */
    private void validarConfirmacaoSenha(String senha, String senhaConfirma) throws Exception {
    	if (senha.equals(senhaConfirma) == false) {
    		throw new Exception("Senhas informadas são diferentes.");
    	}
    }
    
    /**
     * Valida o nome
     * @access private
     * @param String nome
     * @return void
     * @throws Exception
     */
    private void validarNome(String nome) throws Exception {
        if (nome.isEmpty()) {
            throw new Exception("Nome em branco.");
        } else if (nome.length() > 100) {
            throw new Exception("Nome acima do limite de 100 caracteres.");
        }
    }
    
    /**
     * Valida a confirmação de email
     * @access private
     * @param String email
     * @param String emailConfirma
     * @return void
     * @throws Exception
     */
    private void validarConfirmacaoEmail(String email, String emailConfirma) throws Exception {
    	if (email.equals(emailConfirma) == false) {
    		throw new Exception("E-mails informados são diferentes.");
    	}
    }
    
    /**
     * Valida a url
     * @access private
     * @param String url
     * @return void
     * @throws Exception
     */
    private void validarUrl(String url) throws Exception {
    	if (!url.isEmpty()) {
        	if (url.length() > 100) {
        		throw new Exception("Url inválida.");
        	}
    	} 
    }
    
    /**
     * Valida a definicao
     * @access private
     * @param String definicao
     * @return void
     * @throws Exception
     */
    private void validarDefinicao(String definicao) throws Exception {
    	if (!definicao.isEmpty()) {
        	if (definicao.length() > 100) {
        		throw new Exception("Definição inválida.");
        	}
    	} 
    }
 
    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        //TODO 
    	return "Short description";
    }// </editor-fold>

}
