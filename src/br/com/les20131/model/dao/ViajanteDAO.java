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

    public void incluir(Viajante obj) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
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