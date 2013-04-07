package br.com.les20131.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import br.com.les20131.model.Usuario;
import br.com.les20131.model.Viajante;

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

    public Usuario consultar(int idUsuario) throws DAOException {
        if (idUsuario <= 0) {
            throw new DAOException("Usuarío inválido!");
        }

        PreparedStatement stmt = null;
        ResultSet resultSet = null;

        String sql = "SELECT u.id_usuario, u.email, u.nome, u.senha"
                    + "\n FROM usuario u"
                    + "\n WHERE u.id_usuario = ?";

        try {
            stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(1, idUsuario);
            resultSet = stmt.executeQuery();

            Usuario usuario = null;
            if (resultSet.next()) {
            	usuario = new Usuario(resultSet.getInt("id_usuario")
                                , resultSet.getString("email")
                                , resultSet.getString("nome")
                                , resultSet.getString("senha"));
            }
            return usuario;
        } catch (Exception excecao) {
            throw new DAOException(excecao);
        }
    }

    /**
     * Consulta um email e senha
     * @access public
     * @param String email
     * @param String senha
     * @return Usuario
     * @throws Exception
     */
    public Usuario consultar(String email, String senha) throws DAOException {
        if (email.isEmpty() || senha.isEmpty()) {
            throw new DAOException("Email ou senha inválidos!");
        }

        PreparedStatement stmt = null;
        ResultSet resultSet = null;

        String sql = "SELECT u.id_usuario, u.email, u.nome, u.senha"
                    + "\n FROM usuario u"
                    + "\n WHERE u.email = ?"
                    + "\n AND u.senha = SHA1(?)";

        try {
            stmt = this.conexao.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, senha);
            resultSet = stmt.executeQuery();

            Usuario user = null;
            if (resultSet.next()) {
                user = new Usuario(resultSet.getInt("id_usuario")
                                , resultSet.getString("email")
                                , resultSet.getString("nome")
                                , resultSet.getString("senha"));
            }
            return user;
        } catch (Exception excecao) {
            throw new DAOException(excecao);
        }
    }    
    
    /**
     * Incluir um usuário no banco de dados
     * @access public
     * @param Usuario obj
     * @return void
     * @throws Exception
     */    
    public void incluir(Usuario obj) throws DAOException {
        if (obj == null) {
            throw new DAOException("Usuário inválido para incluir!");
        }

        PreparedStatement stmt = null;

        String sql = "INSERT INTO usuario"
                    + "\n(email, senha, nome)"
                    + "\n VALUES (?, SHA1(?), ?)";

        try {
            stmt = this.conexao.prepareStatement(sql);
            stmt.setString(1, obj.getEmail());
            stmt.setString(2, obj.getSenha());
            stmt.setString(3, obj.getNome());
            stmt.executeUpdate();
        } catch (Exception excecao) {
            throw new DAOException(excecao);
        }
    }
    
    public void alterar(Usuario obj) throws DAOException {
        PreparedStatement stmt = null;

        try {
            String sql = "UPDATE usuario SET"
                        + "\n email = ?"
                        + "\n, senha = ?"
                        + "\n, nome = ?"
                        + "\n WHERE id_usuario = ?";

            stmt = this.conexao.prepareStatement(sql);
            stmt.setString(1, obj.getEmail());
            stmt.setString(2, obj.getSenha());
            stmt.setString(3, obj.getNome());
            stmt.setInt(4, obj.getIdUsuario());
            stmt.executeUpdate();
        } catch (Exception excecao) {
            throw new DAOException(excecao);
        }    	
    }

    public void excluir(Usuario obj) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Usuario> consultarTodos() throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Retorna o hash da senha
     * @access public
     * @param String senha
     * @return String
     * @throws DAOException
     */
    public String retornarHashSenha(String senha) throws DAOException {
        if (senha.isEmpty()) {
            throw new DAOException("Senha inválida!");
        }

        PreparedStatement stmt = null;
        ResultSet resultSet = null;

        String sql = "SELECT SHA1(?) senha"
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
