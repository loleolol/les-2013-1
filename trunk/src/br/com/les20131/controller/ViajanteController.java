package br.com.les20131.controller;

import br.com.les20131.model.Usuario;
import br.com.les20131.model.bean.ViajanteBean;
import br.com.les20131.util.InvalidPageException;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author 200920183
 */
@MultipartConfig
public class ViajanteController extends BaseController {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4226184306349339560L;

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
		this.despachar("/view/viajante/inicio.jsp");
    }
    
    /**
     * Ação de seleção do registro
     * @access private
     * @return void
     * @throws Exception
     */
    private void acaoSelecionar() throws Exception {
    	this.verificarSessao();
    	this.carregarViajante();
    	this.despachar("/view/viajante/alterar.jsp");
    }
    
    /**
     * Ação de inserção do registro
     * @access private
     * @return void
     * @throws Exception
     */
    private void acaoInserir() throws Exception {
    	this.incluirViajante();
    	this.despachar("/view/viajante/inicio.jsp");
    }
    
    /**
     * Ação de alteração do registro
     * @access private
     * @return void
     * @throws Exception
     */
    private void acaoAlterar() throws Exception {
    	this.verificarSessao();
		this.alterarViajante();
		this.despachar("/view/viajante/alterar.jsp");
    }
    
    /**
     * Efetua a inclusão de novo registro
     * @access private
     * @return void
     * @throws Exception
     */
    private void incluirViajante() throws Exception {
    	HttpSession sessao = this.requisicao.getSession(true);
        ViajanteBean viajanteBean = new ViajanteBean();
        String dataNascimento = this.requisicao.getParameter("dataNascimentoAno") 
        		+ '-' + this.requisicao.getParameter("dataNascimentoMes")
        		+ '-' + this.requisicao.getParameter("dataNascimentoDia");
        this.validarViajante(this.requisicao.getParameter("email"), this.requisicao.getParameter("senha")
        		, this.requisicao.getParameter("emailConfirma"), this.requisicao.getParameter("senhaConfirma")
        		, this.requisicao.getParameter("nome"), this.requisicao.getParameter("sexo"), dataNascimento);
        viajanteBean.incluir(this.requisicao.getParameter("email"), this.requisicao.getParameter("nome")
        		, this.requisicao.getParameter("senha"), this.requisicao.getParameter("sexo"), dataNascimento);
        sessao.setAttribute("usuario", (Usuario)viajanteBean.getViajante());
        this.verificarSessao();
        //request.setAttribute("mensagemBean", new MensagemBean("Viajante inserido com sucesso!"));
    }    
    
    /**
     * Carrega informações do viajante
     * @access private
     * @throws Exception 
     * @return void
     */
    private void carregarViajante() throws Exception {
    	HttpSession sessao = this.requisicao.getSession();
    	ViajanteBean viajanteBean = new ViajanteBean();
    	viajanteBean.consultar(((Usuario)sessao.getAttribute("usuario")).getIdUsuario());
    	this.requisicao.setAttribute("viajanteBean", viajanteBean);
    }
    
    /**
     * Altera um viajante
     * @access private
     * @return void
     * @throws Exception
     */
    private void alterarViajante() throws Exception {
    	HttpSession sessao = this.requisicao.getSession();
        ViajanteBean viajanteBean = new ViajanteBean();
        String dataNascimento = this.requisicao.getParameter("dataNascimentoAno") 
        		+ '-' + this.requisicao.getParameter("dataNascimentoMes")
        		+ '-' + this.requisicao.getParameter("dataNascimentoDia");
	    Part imagemParte = this.requisicao.getPart("imagem");
	    InputStream imagem = imagemParte.getInputStream();
        this.validarViajante(((Usuario)sessao.getAttribute("usuario")).getIdUsuario(), this.requisicao.getParameter("nome"), this.requisicao.getParameter("sexo"), dataNascimento);
        viajanteBean.alterar(((Usuario)sessao.getAttribute("usuario")).getIdUsuario(), this.requisicao.getParameter("nome"), this.requisicao.getParameter("sexo"), dataNascimento, imagem);
        this.requisicao.setAttribute("viajanteBean", viajanteBean);
    }
    
    
    /**
     * Valida o viajante
     * @access private
     * @param String email
     * @param String senha
     * @param String emailConfirma
     * @param String senhaConfirma
     * @param String nome
     * @param String sexo
     * @param String dataNascimento
     * @return void
     * @throws Exception
     */
    private void validarViajante(String email, String senha, String emailConfirma, String senhaConfirma, String nome, String sexo, String dataNascimento) throws Exception {
        this.validarEmail(email);
        this.validarEmail(emailConfirma);
        this.validarNome(nome);
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
    private void validarViajante(int idUsuario, String nome, String sexo, String dataNascimento) throws Exception {
    	this.validarNome(nome);
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
    
    /**
     * Valida o nome
     * @access private
     * @param String nome
     * @return void
     * @throws Exception
     */
    private void validarNome(String nome) throws Exception {
        if (nome.isEmpty()) {
            throw new Exception("Nome em branco!");
        } else if (nome.length() > 100) {
            throw new Exception("Nome acima do limite de 100 caracteres!");
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
