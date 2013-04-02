package br.com.les20131.model.bean;

import br.com.les20131.model.Usuario;
import br.com.les20131.model.dao.UsuarioDAO;
import br.com.les20131.util.UserAuthenticationException;

/**
 *
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
     * Construtor da classe
     * @access public
     */
    public UsuarioBean() {

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
}
