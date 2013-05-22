package br.com.les20131.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.les20131.model.Usuario;
import br.com.les20131.model.Viagem;
import br.com.les20131.model.Viajante;

/**
 * Classe DAO de usuário
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
    
    /**
     * Consultar um usuário pelo id
     * @access public
     * @param int idUsuario
     * @return Usuario
     */
    public Usuario consultar(int idUsuario) throws DAOException {
        if (idUsuario <= 0) {
            throw new DAOException("Usuarío inválido.");
        }
        int indice = 0;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;

        String sql = "SELECT u.id_usuario, u.email, u.nome, u.senha, u.excluido, u.bloqueado"
                    + "\n FROM usuario u"
                    + "\n WHERE u.id_usuario = ?";

        try {
            stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(++indice, idUsuario);
            resultSet = stmt.executeQuery();

            Usuario usuario = null;
            if (resultSet.next()) {
            	usuario = new Usuario(resultSet.getInt("id_usuario")
                                , resultSet.getString("email")
                                , resultSet.getString("nome")
                                , resultSet.getString("senha")
                                , resultSet.getInt("excluido")
                                , resultSet.getInt("bloqueado"));
            }
            return usuario;
        } catch (Exception excecao) {
            throw new DAOException(excecao);
        }
    }

    /**
     * Consultar um email e senha
     * @access public
     * @param String email
     * @param String senha
     * @return Usuario
     * @throws Exception
     */
    public Usuario consultar(String email, String senha) throws DAOException {
        if (email.isEmpty() || senha.isEmpty()) {
            throw new DAOException("Email ou senha inválidos.");
        }
        int indice = 0;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;

        String sql = "SELECT u.id_usuario, u.email, u.nome, u.senha, u.excluido, u.bloqueado"
                    + "\n FROM usuario u"
                    + "\n WHERE u.email = ?"
                    + "\n AND u.senha = SHA1(?)";

        try {
            stmt = this.conexao.prepareStatement(sql);
            stmt.setString(++indice, email);
            stmt.setString(++indice, senha);
            resultSet = stmt.executeQuery();

            Usuario user = null;
            if (resultSet.next()) {
                user = new Usuario(resultSet.getInt("id_usuario")
                                , resultSet.getString("email")
                                , resultSet.getString("nome")
                                , resultSet.getString("senha")
                                , resultSet.getInt("excluido")
                                , resultSet.getInt("bloqueado"));
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
            throw new DAOException("Usuário inválido para incluir.");
        }
        int indice = 0;
        PreparedStatement stmt = null;

        String sql = "INSERT INTO usuario"
                    + "\n(email, senha, nome, excluido, bloqueado)"
                    + "\n VALUES (?, SHA1(?), ?, ?, ?)";

        try {
            stmt = this.conexao.prepareStatement(sql);
            stmt.setString(++indice, obj.getEmail());
            stmt.setString(++indice, obj.getSenha());
            stmt.setString(++indice, obj.getNome());
            stmt.setInt(++indice, obj.getExcluido());
            stmt.setInt(++indice, obj.getBloqueado());
            stmt.executeUpdate();
        } catch (Exception excecao) {
            throw new DAOException(excecao);
        }
    }
    
    /**
     * Alterar um usuário
     * @access public
     * @param Usuario obj
     * @return void
     */
    public void alterar(Usuario obj) throws DAOException {
        if (obj == null) {
            throw new DAOException("Usuário inválido para alterar.");
        }
    	int indice = 0;
    	PreparedStatement stmt = null;

        try {
            String sql = "UPDATE usuario SET"
                        + "\n email = ?"
                        + "\n, senha = ?"
                        + "\n, nome = ?"
                        + "\n, excluido = ?"
                        + "\n, bloqueado = ?"
                        + "\n WHERE id_usuario = ?";

            stmt = this.conexao.prepareStatement(sql);
            stmt.setString(++indice, obj.getEmail());
            stmt.setString(++indice, obj.getSenha());
            stmt.setString(++indice, obj.getNome());
            stmt.setInt(++indice, obj.getExcluido());
            stmt.setInt(++indice, obj.getBloqueado());
            stmt.setInt(++indice, obj.getIdUsuario());
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
        int indice = 0;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;

        String sql = "SELECT SHA1(?) senha"
                    + "\n FROM dual";

        try {
            stmt = this.conexao.prepareStatement(sql);
            stmt.setString(++indice, senha);
            resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                senha = resultSet.getString("senha");
            }
            return senha;
        } catch (Exception excecao) {
            throw new DAOException(excecao);
        }
    }
    
    //ADMIN
    /**
     * Consulta todos os usuários
     * @access public
     * @return List<Usuario>
     * @throws Exception
     */
    public List<Usuario> listarUsuarios() throws DAOException {
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        int indice = 0;
        
        String sql = "SELECT u.id_usuario, u.email, u.nome, u.senha, u.excluido, u.bloqueado"
                + "\n FROM usuario u";

        try {
            stmt = this.conexao.prepareStatement(sql);
            resultSet = stmt.executeQuery();

            List<Usuario> listaUsuario = new ArrayList<Usuario>();

            while (resultSet.next()) {
                listaUsuario.add( new Usuario(resultSet.getInt("id_usuario")
                , resultSet.getString("email")
                , resultSet.getString("nome")
                , resultSet.getString("senha")
                , resultSet.getInt("excluido")
                , resultSet.getInt("bloqueado")));
            }

            return listaUsuario;
        } catch (Exception excecao) {
            throw new DAOException(excecao);
        }
    }
    
    /**
     * Bloquear um usuário
     * @access public
     * @param Usuario obj
     * @return void
     */
    public void bloquear(Usuario obj, int bloqueado) throws DAOException {
        if (obj == null) {
            throw new DAOException("Usuário inválido para bloquear.");
        }
    	int indice = 0;
    	obj.setBloqueado(bloqueado);
    	PreparedStatement stmt = null;

        try {
            String sql = "UPDATE usuario SET"
                        + "\n bloqueado = ?"
                        + "\n WHERE id_usuario = ?";

            stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(++indice, obj.getBloqueado());
            stmt.setInt(++indice, obj.getIdUsuario());
            stmt.executeUpdate();
        } catch (Exception excecao) {
            throw new DAOException(excecao);
        }    	
    }
    //ADMIN

}
