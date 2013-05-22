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
    
    //ADMIN
    /**
     * Armazena uma lista de usuários
     * @access private
     * @var List<Usuario>
     */
    private List<Usuario> listaUsuario;
    private List<Usuario> listaUsuarioBloquear;
    private List<Usuario> listaUsuarioDesbloquear;
    private List<Usuario> listaUsuarioExcluir;
    private List<Usuario> listaUsuarioAdicionar;
    
    /**
     * Constantes para bloqueio e desbloqueio, exclusão e adição
     * @access private
     * @var int
     */
    private int BLOQUEAR = 1;
    private int DESBLOQUEAR = 0;
    private int EXCLUIR = 1;
    private int ADICIONAR = 0;
    //ADMIN

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
    
    //ADMIN
    /**
     * Retorna a lista de usuários
     * @access public
     * @return List<Usuario>
     */
    public List<Usuario> getListaUsuario() {
        return this.listaUsuario;
    }
    //ADMIN

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
    
    //ADMIN
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
    
    /**
     * Inicia listas para bloqueio/desbloqueio
     * @access public
     * @return void
     * @throws Exception
     */
    public void iniciaListasBloquear() throws Exception{
    	this.listaUsuarioBloquear = new ArrayList<Usuario>();
    	this.listaUsuarioDesbloquear = new ArrayList<Usuario>();
    }
    
    /**
     * Inicia listas para bloqueio/desbloqueio
     * @access public
     * @return void
     * @throws Exception
     */
    public void iniciaListasExcluir() throws Exception{
    	this.listaUsuarioExcluir = new ArrayList<Usuario>();
    	this.listaUsuarioAdicionar = new ArrayList<Usuario>();
    }
    
    /**
     * Adiciona usuários selecionados na lista
     * @access public
     * @return void
     * @throws Exception
     */
    public void adicionarListaBloquear(int id) throws Exception {
    	usuario = usuarioDAO.consultar(id);
        this.listaUsuarioBloquear.add(usuario);
    }
    
    /**
     * Adiciona usuários não selecionados na lista
     * @access public
     * @return void
     * @throws Exception
     */
    public void adicionarListaDesbloquear(int id) throws Exception {
    	usuario = usuarioDAO.consultar(id);
        this.listaUsuarioDesbloquear.add(usuario);
    }
    
    /**
     * Adiciona usuários selecionados na lista
     * @access public
     * @return void
     * @throws Exception
     */
    public void adicionarListaExcluir(int id) throws Exception {
    	usuario = usuarioDAO.consultar(id);
        this.listaUsuarioExcluir.add(usuario);
    }
    
    /**
     * Adiciona usuários não selecionados na lista
     * @access public
     * @return void
     * @throws Exception
     */
    public void adicionarListaAdicionar(int id) throws Exception {
    	usuario = usuarioDAO.consultar(id);
        this.listaUsuarioAdicionar.add(usuario);
    }
    
    /**
     * Submete bloqueios/desbloqueios
     * @access public
     * @return void
     * @throws Exception
     */
    public void bloquearDesbloquear() throws Exception {
    	for(int i=0; i<this.listaUsuarioBloquear.size(); i++){
    		this.usuarioDAO.bloquear(listaUsuarioBloquear.get(i), BLOQUEAR);
    	}
    	for(int i=0; i<this.listaUsuarioDesbloquear.size(); i++){
    		this.usuarioDAO.bloquear(listaUsuarioDesbloquear.get(i), DESBLOQUEAR);
    	}
    }
    //ADMIN
    
}
