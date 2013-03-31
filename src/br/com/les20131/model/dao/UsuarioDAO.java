package br.com.les20131.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import br.com.les20131.model.Usuario;

/**
 * 
 * @author 200920183
 */
public class UsuarioDAO extends DAOBase<Usuario> {

    /**
     * Construtor da classe
     * @access public
     * @throws DAOException
     */
    public UsuarioDAO() throws Exception {
        super();
    }

    public Usuario consultar(int codUsuario) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void inserir(Usuario obj) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void alterar(Usuario obj) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void excluir(Usuario obj) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Usuario> consultarTodos() throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Consulta um usuário e senha
     * @access public
     * @param String usuario
     * @param String senha
     * @return Usuario
     * @throws Exception
     */
    public Usuario consultarPorUsuarioSenha(String usuario, String senha) throws DAOException {
        if (usuario.isEmpty() || senha.isEmpty()) {
            throw new DAOException("Usuário ou senha inválidos!");
        }

        PreparedStatement stmt = null;
        ResultSet resultSet = null;

        String sql = "SELECT u.cod_usuario, u.usuario, u.senha"
                    + "\n FROM usuario u"
                    + "\n WHERE u.usuario = ?"
                    + "\n AND u.senha = MD5(?)";

        try {
            stmt = this.conexao.prepareStatement(sql);
            stmt.setString(1, usuario);
            stmt.setString(2, senha);
            resultSet = stmt.executeQuery();

            Usuario user = null;
            if (resultSet.next()) {
                user = new Usuario(resultSet.getInt("cod_usuario")
                                , resultSet.getString("usuario")
                                , resultSet.getString("senha"));
            }
            return user;
        } catch (Exception excecao) {
            throw new DAOException(excecao);
        }
    }

    /**
     * Retorna o hash da senha
     * @access public
     * @param String senha
     * @return String
     * @throws DAOException
     */
    public String retornaHashSenha(String senha) throws DAOException {
        if (senha.isEmpty()) {
            throw new DAOException("Senha inválida!");
        }

        PreparedStatement stmt = null;
        ResultSet resultSet = null;

        String sql = "SELECT MD5(?) senha"
                    + "\n FROM dual";

        try {
            stmt = this.conexao.prepareStatement(sql);
            stmt.setString(1, senha);
            resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                senha = resultSet.getString("senha");
            }
            return senha;
        } catch (Exception excecao) {
            throw new DAOException(excecao);
        }
    }

}
