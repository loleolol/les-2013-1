package br.com.les20131.controller;

import br.com.les20131.model.Usuario;
import br.com.les20131.model.bean.UsuarioBean;
import br.com.les20131.model.bean.ViagemBean;
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
public class ModuloAdminUsuarioController extends BaseController {

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
            } else if (this.acao.equalsIgnoreCase("login")) {
            	this.acaoLogin();
            } else if (this.acao.equalsIgnoreCase("logoff")) {
            	this.acaoLogoff();
            } else if (this.acao.equalsIgnoreCase("novo")) {
            	this.acaoNovo();
            } else if (this.acao.equalsIgnoreCase("selecionar")) {
            	this.acaoSelecionar();
            } else if (this.acao.equalsIgnoreCase("alterar")) {
            	this.acaoAlterar();
            //ADMIN
            } else if (this.acao.equalsIgnoreCase("listarUsuarios")) {
            	this.acaoListarUsuarios();
            //ADMIN
            }else {
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
     * @throws Exception
     */
    private void acaoPadrao() throws Exception {
    	this.despachar("/view/index.jsp");
    }
    
    /**
     * A��o que realiza o login
     * @access private
     * @return void
     * @throws Exception
     */
    private void acaoLogin() throws Exception {
        UsuarioBean usuarioBean = new UsuarioBean();
        usuarioBean.autenticaUsuario(this.requisicao.getParameter("loginEmail"), this.requisicao.getParameter("loginSenha"));
        HttpSession sessao = this.requisicao.getSession(true);
        sessao.setAttribute("usuario", usuarioBean.getUsuario());
        this.verificarSessao();
        this.despachar("/view/viajante/inicio.jsp");
    }
    
    /**
     * A��o que realiza o logoff
     * @access private
     * @return void
     * @throws Exception
     */
    private void acaoLogoff() throws Exception {
       	this.requisicao.getSession().invalidate();
       	this.despachar("/view/index.jsp");
    }
    
    /**
     * A��o para cria��o de novo usu�rio
     * @access private
     * @return void
     * @throws Exception
     */
    private void acaoNovo() throws Exception {
    	this.despachar("/view/viajante/incluir.jsp");
    }
    
    /**
     * A��o para sele��o de usu�rio
     * @access private
     * @return void
     * @throws Exception
     */
    private void acaoSelecionar() throws Exception {
    	this.verificarSessao();
        this.despachar("/view/usuario/alterar.jsp");
    }
    
    /**
     * A��o de altera��o de usu�rio
     * @access private
     * @return void
     * @throws Exception
     */
    private void acaoAlterar() throws Exception {
    	this.verificarSessao();
		this.alterarUsuario();
		this.despachar("/view/usuario/alterar.jsp");
    }
    
    //ADMIN
    /**
     * A��o de listagem de usu�rios
     * @access private
     * @return void
     * @throws Exception
     */
    private void acaoListarUsuarios() throws Exception {
		this.listarUsuarios();
		this.despachar("/view/admin/listar-usuario.jsp");
    }
    //ADMIN
    
    /**
     * Altera um usu�rio
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
    	this.validarEmail(email);
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
            throw new Exception("E-mail em branco!");
        } else if (email.length() > 100) {
            throw new Exception("E-mail acima do limite de 100 caracteres!");
        }
    }
    
    //ADMIN
    /**
     * Lista os usu�rios
     * @access private
     * @return void
     * @throws Exception
     */
    private void listarUsuarios() throws Exception {
    	HttpSession sessao = this.requisicao.getSession();
        UsuarioBean usuarioBean = new UsuarioBean();
        usuarioBean.listarUsuarios();
        this.requisicao.setAttribute("usuarioBean", usuarioBean);
    }
    //ADMIN
    
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
