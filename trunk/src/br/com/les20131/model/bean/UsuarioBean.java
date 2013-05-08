package br.com.les20131.model.bean;

import java.util.List;

import br.com.les20131.model.Usuario;
import br.com.les20131.model.dao.UsuarioDAO;
import br.com.les20131.model.dao.ViajanteDAO;
import br.com.les20131.util.UserAuthenticationException;

/**
 *
 * @author 200920183
 */
public class UsuarioBean {

    /**
     * Armazena o objeto de persist�ncia de usu�rio
     * @access protected
     * @var UsuarioDAO
     */
    protected UsuarioDAO usuarioDAO;

    /**
     * Armazena um usu�rio
     * @access protected
     * @var Usuario
     */
    protected Usuario usuario;
    
    //ADMIN
    /**
     * Armazena uma lista de usu�rios
     * @access private
     * @var List<Usuario>
     */
    private List<Usuario> listaUsuario;
    //ADMIN

    /**
     * Construtor da classe
     * @access public
     */
    public UsuarioBean() {

    }

    /**
     * Retorna o usu�rio
     * @access public
     * @return Usuario
     */
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    /**
     * Define o usu�rio
     * @param usuario
     * @access public
     * @return void
     */
    public void setUsuario(Usuario usuario) {
    	this.usuario = usuario;
    }
    
    //ADMIN
    /**
     * Retorna a lista de usu�rios
     * @access public
     * @return List<Usuario>
     */
    public List<Usuario> getListaUsuario() {
        return this.listaUsuario;
    }
    
    /**
     * Retorna a lista de viagens
     * @access public
     * @return List<Viagem>
     */
    public int getTamanhoListaUsuario() {
        return this.listaUsuario.size();
    }
    //ADMIN

    /**
     * Autentica um usuario e senha
     * @access public
     * @return void
     * @throws Exception
     */
    public void autenticaUsuario(String email, String senha) throws UserAuthenticationException {
        try {
            this.usuarioDAO = new UsuarioDAO();
            this.usuario = usuarioDAO.consultar(email, senha);
            if (this.usuario == null) {
                throw new UserAuthenticationException();
            }
        } catch (Exception excecao) {
            throw new UserAuthenticationException();
        }
    }

    /**
     * Altera um usu�rio
     * @access public
     * @return void
     * @throws Exception
     */
    public void alterar(int idUsuario, String email, String senha) throws Exception {
    	this.usuarioDAO = new UsuarioDAO();
      	this.usuario = this.usuarioDAO.consultar(idUsuario);
      	this.usuario.setEmail(email);
      	if (!senha.isEmpty()) {
      		this.usuario.setSenha(this.usuarioDAO.retornarHashSenha(senha));
      	}
      	this.usuarioDAO.alterar(this.usuario);
    }
    
    /**
     * Consulta um usu�rio pelo id
     * @access public
     * @param int idUsuario
     * @return void
     * @throws Exception
     */
    public void consultar(int idUsuario) throws Exception {
    	this.usuarioDAO = new UsuarioDAO();
      	this.usuario = this.usuarioDAO.consultar(idUsuario);
    }
    
    //ADMIN
    /**
     * Consulta os usu�rios cadastrados
     * @access public
     * @return void
     * @throws Exception
     */
    public void listarUsuarios() throws Exception {
        this.usuarioDAO = new UsuarioDAO();
        this.listaUsuario = this.usuarioDAO.listarUsuarios();
    }
    //ADMIN
    
}
