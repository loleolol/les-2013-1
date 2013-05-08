package br.com.les20131.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.les20131.model.ImagemViagem;
import br.com.les20131.util.bd.FabricaConexao;

/**
 *
 * @author 200920183
 */
public abstract class DAOBase<classe> implements DAOInterface<classe> {

    /**
     * Armazena uma conexão
     * @access private
     * @var Connection
     */
    protected Connection conexao;

    /**
     * Construtor da classe
     * @access public
     */
    public DAOBase() throws Exception {
        this.conexao = FabricaConexao.getObjConexao();
    }
    
    /**
     * Retorna o último id
     * @access public
     * @return int
     * @throws DAOException
     */
    public int retornarUltimoId() throws DAOException {
        PreparedStatement stmt = null;
        ResultSet resultSet = null;

        String sql = "SELECT LAST_INSERT_ID() ultimo";

        try {
            stmt = this.conexao.prepareStatement(sql);
            resultSet = stmt.executeQuery();

            if (resultSet.next()) {
            	return resultSet.getInt("ultimo");
            }
            return 0;
        } catch (Exception excecao) {
            throw new DAOException(excecao);
        }
    }

}

