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
        	this.verificarSessao();
            if (this.acao.isEmpty()) {
            	this.acaoPadrao();
            } else if (this.acao.equalsIgnoreCase("listarUsuarios")) {
            	this.acaoListarUsuarios();
            } else if (this.acao.equalsIgnoreCase("bloquear")) {
            	this.acaoBloquear();
            } else if (this.acao.equalsIgnoreCase("excluir")) {
            	this.acaoExcluir();
            }else {
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
     * Ação de listagem de usuários
     * @access private
     * @return void
     * @throws Exception
     */
    private void acaoListarUsuarios() throws Exception {
		this.listarUsuarios();
		this.despachar("/view/admin/listar-usuario.jsp");
    }
    
    /**
     * Ação de bloqueio de usuários
     * @access private
     * @return void
     * @throws Exception
     */
    private void acaoBloquear() throws Exception {
    	this.bloquearUsuarios();
    	this.despachar("/view/admin/listar-usuario.jsp");
    }
    
    /**
     * Ação de exclusão de usuários
     * @access private
     * @return void
     * @throws Exception
     */
    private void acaoExcluir() throws Exception {
		this.listarUsuarios();
		this.despachar("/view/admin/listar-usuario.jsp");
    }

    /**
     * Lista os usuários
     * @access private
     * @return void
     * @throws Exception
     */
    private void listarUsuarios() throws Exception {
        UsuarioBean usuarioBeanLista = new UsuarioBean();
        usuarioBeanLista.listarUsuarios();
        this.requisicao.setAttribute("usuarioBeanLista", usuarioBeanLista);
    }
    
    /**
     * Bloqueia os usuários
     * @access private
     * @return void
     * @throws Exception
     */
    private void bloquearUsuarios() throws Exception {
    	UsuarioBean usuarioBean = new UsuarioBean();
    	for (int i = 0; i < Integer.parseInt(this.requisicao.getParameter("quantidade")); i++) {
    		String teste = this.requisicao.getParameter("bloqueado"+i);
    		if (teste != null){
    			usuarioBean.adicionarLista(Integer.parseInt(teste));
    		}
    		//Implementar duas listas para separar marcados de não marcados
    		//Depois iterar sobre cada lista comparando o valor atual e o valor antigo no banco, para saber se vai mudar ou não
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
