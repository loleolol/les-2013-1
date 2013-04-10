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
public class ViajanteDAO extends DAOBase<Viajante> {

    /**
     * Construtor da classe
     * @access public
     * @throws DAOException
     */
    public ViajanteDAO() throws Exception {
        super();
    }

    public Viajante consultar(int idUsuario) throws DAOException {
    	if (idUsuario <= 0) {
            throw new DAOException("Usuário inválido!");
        }

        PreparedStatement stmt = null;
        ResultSet resultSet = null;

        String sql = "SELECT u.id_usuario, u.email, u.nome, u.senha, v.sexo, v.data_nascimento"
                    + "\n FROM usuario u, viajante v"
                    + "\n WHERE u.id_usuario = ?"
                    + "\n AND u.id_usuario = v.id_usuario";

        try {
            stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(1, idUsuario);
            resultSet = stmt.executeQuery();

            Viajante viajante = null;
            if (resultSet.next()) {
            	viajante = new Viajante(resultSet.getInt("id_usuario")
                                , resultSet.getString("email")
                                , resultSet.getString("nome")
                                , resultSet.getString("senha")
                                , resultSet.getString("sexo")
                                , resultSet.getDate("data_nascimento"));
            }
            return viajante;
        } catch (Exception excecao) {
            throw new DAOException(excecao);
        }
    }

    /**
     * Incluir um viajante no banco de dados
     * @access public
     * @param Viajante obj
     * @return void
     * @throws Exception
     */    
    public void incluir(Viajante obj) throws DAOException {
        if (obj == null) {
            throw new DAOException("Viajante inválido para incluir!");
        }

        PreparedStatement stmt = null;

        String sql = "INSERT INTO viajante"
                    + "\n(id_usuario, sexo, data_nascimento)"
                    + "\n VALUES (?, ?, ?)";

        try {
            stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(1, obj.getIdUsuario());
            stmt.setString(2, obj.getSexo());
            stmt.setDate(3, new java.sql.Date(obj.getDataNascimento().getTime()));
            stmt.executeUpdate();
        } catch (Exception excecao) {
            throw new DAOException(excecao);
        }
    }

    /**
     * alterar um viajante no banco de dados
     * @access public
     * @param Viajante obj
     * @return void
     * @throws Exception
     */ 
    public void alterar(Viajante obj) throws DAOException {
        PreparedStatement stmt = null;

        try {
            String sql = "UPDATE viajante SET"
                        + "\n sexo = ?"
                        + "\n, data_nascimento = ?"
                        + "\n WHERE id_usuario = ?";

            stmt = this.conexao.prepareStatement(sql);
            stmt.setString(1, obj.getSexo());
            stmt.setDate(2, new java.sql.Date(obj.getDataNascimento().getTime()));
            stmt.setInt(3, obj.getIdUsuario());
            stmt.executeUpdate();
        } catch (Exception excecao) {
            throw new DAOException(excecao);
        }
    }

    public void excluir(Viajante obj) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Viajante> consultarTodos() throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}