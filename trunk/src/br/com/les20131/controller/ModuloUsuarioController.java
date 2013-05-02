package br.com.les20131.controller;

import br.com.les20131.model.Usuario;
import br.com.les20131.model.bean.UsuarioBean;
import br.com.les20131.util.InvalidPageException;

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
     * Altera um usuário
     * @access private
     * @return void
     * @throws Exception
     */
    private void alterarUsuario() throws Exception {
    	HttpSession sessao = this.requisicao.getSession();
        UsuarioBean usuarioBean = new UsuarioBean();
        this.validarUsuario(((Usuario)sessao.getAttribute("usuario")).getIdUsuario(), this.requisicao.getParameter("email"), this.requisicao.getParameter("emailConfirma"), this.requisicao.getParameter("senha"), this.requisicao.getParameter("senhaConfirma"));
        usuarioBean.alterar(((Usuario)sessao.getAttribute("usuario")).getIdUsuario(), this.requisicao.getParameter("email"), this.requisicao.getParameter("senha"));
        sessao.setAttribute("usuario", usuarioBean.getUsuario());
        this.requisicao.setAttribute("usuarioBean", usuarioBean);
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
    	this.validarSenha(senha);
    	this.validarSenha(senhaConfirma);
    	this.validarConfirmacaoSenha(senha, senhaConfirma);
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
