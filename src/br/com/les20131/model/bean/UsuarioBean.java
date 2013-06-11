package br.com.les20131.model.bean;

import java.util.ArrayList;
import java.util.List;

import br.com.les20131.model.Usuario;
import br.com.les20131.model.dao.UsuarioDAO;
import br.com.les20131.model.dao.ViajanteDAO;
import br.com.les20131.util.UserAuthenticationException;

/**
 * Classe bean de usuário
 * @author 200920183
 */
public class UsuarioBean {

    /**
     * Armazena o objeto de persistência de usuário
     * @access protected
     * @var UsuarioDAO
     */
    protected UsuarioDAO usuarioDAO;

    /**
     * Armazena um usuário
     * @access protected
     * @var Usuario
     */
    protected Usuario usuario;
    
    /**
     * Armazena uma lista de usuários
     * @access private
     * @var List<Usuario>
     */
    private List<Usuario> listaUsuario;

    /**
     * Construtor da classe
     * @access public
     */
    public UsuarioBean() throws Exception{
    	this.listaUsuario = new ArrayList<Usuario>();
    	this.usuarioDAO = new UsuarioDAO();
    }

    /**
     * Retorna o usuário
     * @access public
     * @return Usuario
     */
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    /**
     * Define o usuário
     * @param Usuario usuario
     * @access public
     * @return void
     */
    public void setUsuario(Usuario usuario) {
    	this.usuario = usuario;
    }
    
    /**
     * Retorna a lista de usuários
     * @access public
     * @return List<Usuario>
     */
    public List<Usuario> getListaUsuario() {
        return this.listaUsuario;
    }

    /**
     * Autentica um usuario e senha
     * @access public
     * @param String email
     * @param String senha
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
     * Altera um usuário
     * @access public
     * @return void
     * @throws Exception
     */
    public void alterar(int idUsuario, String email, String senha, Integer bloqueado, Integer excluido) throws Exception {
    	this.usuarioDAO = new UsuarioDAO();
      	this.usuario = this.usuarioDAO.consultar(idUsuario);
      	this.usuario.setEmail(email);
      	if (!senha.isEmpty()) {
      		this.usuario.setSenha(this.usuarioDAO.retornarHashSenha(senha));
      	}
      	if(bloqueado != null){
      		this.usuario.setBloqueado(bloqueado);
      	}
      	if(excluido != null){
      		this.usuario.setExcluido(excluido);
      	}
      	this.usuarioDAO.alterar(this.usuario);
    }
    
    /**
     * Consulta um usuário pelo id
     * @access public
     * @param int idUsuario
     * @return void
     * @throws Exception
     */
    public void consultar(int idUsuario) throws Exception {
    	this.usuarioDAO = new UsuarioDAO();
      	this.usuario = this.usuarioDAO.consultar(idUsuario);
    }
    
    /**
     * Consultar viajante por email
     * @access public
     * @param String email
     * @throws Exception
     */
    public void consultarPorEmail(String email) throws Exception {
    	this.usuario = this.usuarioDAO.consultarEmail(email);
    }
    /**
     * Consulta os usuários cadastrados
     * @access public
     * @return void
     * @throws Exception
     */
    public void listarUsuarios() throws Exception {
        this.usuarioDAO = new UsuarioDAO();
        this.listaUsuario = this.usuarioDAO.listarUsuarios();
    }
        
}
