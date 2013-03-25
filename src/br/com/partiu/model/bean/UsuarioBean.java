package br.com.partiu.model.bean;

import br.com.partiu.model.Usuario;
import br.com.partiu.model.dao.UsuarioDAO;
import br.com.partiu.util.UserAuthenticationException;

/**
 *
 * @author 200920183
 */
public class UsuarioBean {

    /**
     * Armazena o objeto de persistência de usuário
     * @access private
     * @var UsuarioDAO
     */
    private UsuarioDAO usuarioDAO;

    /**
     * Armazena um usuário
     * @access private
     * @var Usuario
     */
    private Usuario usuario;

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
    public void autenticaUsuario(String usuario, String senha) throws UserAuthenticationException {
        try {
            this.usuarioDAO = new UsuarioDAO();
            this.usuario = usuarioDAO.consultarPorUsuarioSenha(usuario, senha);
            if (this.usuario == null) {
                throw new UserAuthenticationException();
            }
        } catch (Exception excecao) {
            throw new UserAuthenticationException();
        }
    }
}
