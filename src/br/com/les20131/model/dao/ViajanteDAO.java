package br.com.les20131.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

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

    public Viajante consultar(int codUsuario) {
        throw new UnsupportedOperationException("Not supported yet.");
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

    public void alterar(Viajante obj) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void excluir(Viajante obj) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Viajante> consultarTodos() throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}