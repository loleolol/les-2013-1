package br.com.les20131.controller;

import br.com.les20131.model.Usuario;
import br.com.les20131.model.bean.UsuarioBean;
import br.com.les20131.model.bean.ViajanteBean;
import br.com.les20131.util.InvalidPageException;
import br.com.les20131.util.UserAuthenticationException;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 200920183
 */
public class ModuloUsuarioController extends BaseController {

    /**
	 * 
	 */
	private static final long serialVersionUID = 411902382744835366L;

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
            } else if (this.acao.equalsIgnoreCase("selecionar")) {
            	this.acaoSelecionar();
            } else if (this.acao.equalsIgnoreCase("alterar")) {
            	this.acaoAlterar();
            } else if (this.acao.equalsIgnoreCase("bloquear")) {
            	this.acaoBloquear();
            } else if (this.acao.equalsIgnoreCase("desbloquear")) {
            	this.acaoDesbloquear();
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
     * @throws Exception
     */
    private void acaoPadrao() throws Exception {
        this.despachar("/view/index.jsp");
    }
       
    /**
     * Ação para seleção de usuário
     * @access private
     * @return void
     * @throws Exception
     */
    private void acaoSelecionar() throws Exception {
		this.verificarSessao();
        this.despachar("/view/usuario/alterar.jsp");
    }
    
    /**
     * Ação de alteração de usuário
     * @access private
     * @return void
     * @throws Exception
     */
    private void acaoAlterar() throws Exception {
		this.verificarSessao();
		this.alterarUsuario();
		this.despachar("/view/usuario/alterar.jsp");
    }
    
    /**
     * Ação de bloqueio de usuário
     * @access private
     * @return void
     * @throws Exception
     */
    private void acaoBloquear() throws Exception {
		this.gerenciarBloqueioUsuario(1);
    }
    
    /**
     * Ação de desbloqueio de usuário
     * @access private
     * @return void
     * @throws Exception
     */
    private void acaoDesbloquear() throws Exception {
		this.gerenciarBloqueioUsuario(0);
    }
    
    /**
     * Ação de exclusão de usuário
     * @access private
     * @return void
     * @throws Exception
     */
    private void acaoExcluir() throws Exception {
		this.excluirUsuario();
    	HttpSession sessao = this.requisicao.getSession(false);
    	if (sessao != null) {
    		sessao.invalidate();
    		this.requisicao.setAttribute("usuarioBean", null);
    	}
       	this.despachar("/view/index.jsp");
    }
    
    /**
     * Altera um usuário
     * @access private
     * @return void
     * @throws Exception
     */
    private void alterarUsuario() throws Exception {
    	HttpSession sessao = this.requisicao.getSession();
        UsuarioBean usuarioBean = new UsuarioBean();
        this.validarUsuario(((Usuario)sessao.getAttribute("usuario")).getIdUsuario(), this.requisicao.getParameter("email"), this.requisicao.getParameter("emailConfirma"), this.requisicao.getParameter("senha"), this.requisicao.getParameter("senhaConfirma"));
        usuarioBean.alterar(((Usuario)sessao.getAttribute("usuario")).getIdUsuario(), this.requisicao.getParameter("email"), this.requisicao.getParameter("senha"), null, null);
        sessao.setAttribute("usuario", usuarioBean.getUsuario());
        this.requisicao.setAttribute("usuarioBean", usuarioBean);
    }
    
    /**
     * Bloqueia um usuário
     * @access private
     * @return void
     * @throws Exception
     */
    private void gerenciarBloqueioUsuario(int bloqueado) throws Exception {
        UsuarioBean usuarioBean = new UsuarioBean();
        this.validarIdUsuario(this.requisicao.getParameter("idUsuario"));
        usuarioBean.consultar(Integer.parseInt(this.requisicao.getParameter("idUsuario")));        
        usuarioBean.alterar(usuarioBean.getUsuario().getIdUsuario(), usuarioBean.getUsuario().getEmail(), usuarioBean.getUsuario().getSenha(), bloqueado, usuarioBean.getUsuario().getExcluido());
    }
    
    /**
     * Exclui um usuário
     * @access private
     * @return void
     * @throws Exception
     */
    private void excluirUsuario() throws Exception {
    	HttpSession sessao = this.requisicao.getSession();
    	UsuarioBean usuarioBean = new UsuarioBean();
    	Usuario usuario;
    	if (sessao.getAttribute("administrador") != null && this.requisicao.getParameter("idUsuario") != null) {
	        this.validarIdUsuario(this.requisicao.getParameter("idUsuario"));
	        usuarioBean.consultar(Integer.parseInt(this.requisicao.getParameter("idUsuario")));        
	        usuario = usuarioBean.getUsuario();
    	} else {
    		usuario = ((Usuario)sessao.getAttribute("usuario"));
    	}
    	
    	usuarioBean.alterar(usuario.getIdUsuario(), usuario.getEmail(), "", usuario.getBloqueado(), 1);
    }
    
    /**
     * Valida o viajante
     * @access private
     * @param int idUsuario
     * @param String nome
     * @param String sexo
     * @param String dataNascimento
     * @return void
     * @throws Exception
     */
    private void validarUsuario(int idUsuario, String email, String emailConfirma, String senha, String senhaConfirma) throws Exception {
    	this.validarIdUsuario(idUsuario);
    	this.validarEmail(email);
    	this.validarEmail(emailConfirma);
    	this.validarConfirmacaoEmail(email, emailConfirma);
        this.validarExisteEmail(email);
    	if (senha.isEmpty() == false) {
    		this.validarSenha(senha);
    	}
    	if (senha.isEmpty() == false) {
    		this.validarSenha(senhaConfirma);
    	}
    	this.validarConfirmacaoSenha(senha, senhaConfirma);
    }
    
    /**
     * Valida se o email existe
     * @access private
     * @param String email
     * @return void
     * @throws Exception
     */
    private void validarExisteEmail(String email) throws Exception {
    	try {
    		HttpSession sessao = this.requisicao.getSession();
	    	UsuarioBean usuarioBean = new UsuarioBean();
	    	usuarioBean.consultarPorEmail(email);
	    	if (usuarioBean.getUsuario() != null && usuarioBean.getUsuario().getIdUsuario() != ((Usuario)sessao.getAttribute("usuario")).getIdUsuario()) {
	            throw new Exception("E-mail já registrado no sistema.");
	    	}
    	} catch (Exception excecao) {
    		throw new Exception("E-mail já registrado no sistema.");
    	}
    }    
    
    /**
     * Valida o código do usuário
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
     * Valida o código do usuário
     * @access private
     * @param String idUsuario
     * @return void
     * @throws Exception
     */
    private void validarIdUsuario(String idUsuario) throws Exception {
    	try {
    		this.validarIdUsuario(Integer.parseInt(idUsuario));
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
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        //TODO 
    	return "Short description";
    }// </editor-fold>

}
