package br.com.les20131.model.dao;

import java.io.InputStream;
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

    public Viajante consultar(int intId) throws DAOException {
    	if (intId <= 0) {
            throw new DAOException("Usuário inválido!");
        }
        int indice = 0;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;

        String sql = "SELECT u.id_usuario, u.email, u.nome, u.senha, u.excluido, u.bloqueado"
        			+ "\n, v.sexo, v.data_nascimento, v.imagem"
                    + "\n FROM usuario u, viajante v"
                    + "\n WHERE u.id_usuario = ?"
                    + "\n AND u.id_usuario = v.id_usuario";

        try {
            stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(++indice, intId);
            resultSet = stmt.executeQuery();

            Viajante viajante = null;
            if (resultSet.next()) {
            	viajante = new Viajante(resultSet.getInt("id_usuario")
                                , resultSet.getString("email")
                                , resultSet.getString("nome")
                                , resultSet.getString("senha")
                                , resultSet.getInt("excluido")
                                , resultSet.getInt("bloqueado")
                                , resultSet.getString("sexo")
                                , resultSet.getDate("data_nascimento")
                                , resultSet.getBinaryStream("imagem"));
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
        int indice = 0;
        PreparedStatement stmt = null;

        String sql = "INSERT INTO viajante"
                    + "\n(id_usuario, sexo, data_nascimento)"
                    + "\n VALUES (?, ?, ?)";

        try {
            stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(++indice, obj.getIdUsuario());
            stmt.setString(++indice, obj.getSexo());
            stmt.setDate(++indice, new java.sql.Date(obj.getDataNascimento().getTime()));
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
        if (obj == null) {
            throw new DAOException("Viajante inválido para alterar!");
        }
        int indice = 0;
    	PreparedStatement stmt = null;

        try {
            String sql = "UPDATE viajante SET"
                        + "\n sexo = ?"
                        + "\n, data_nascimento = ?"
                        + "\n, imagem = ?"
                        + "\n WHERE id_usuario = ?";

            stmt = this.conexao.prepareStatement(sql);
            stmt.setString(++indice, obj.getSexo());
            stmt.setDate(++indice, new java.sql.Date(obj.getDataNascimento().getTime()));
            stmt.setBlob(++indice, obj.getImagem());
            stmt.setInt(++indice, obj.getIdUsuario());
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