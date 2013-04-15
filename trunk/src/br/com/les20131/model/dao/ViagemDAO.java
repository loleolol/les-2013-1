package br.com.les20131.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import br.com.les20131.model.Viagem;

/**
 * 
 * @author 200920183
 */
public class ViagemDAO extends DAOBase<Viagem> {

    /**
     * Construtor da classe
     * @access public
     * @throws DAOException
     */
    public ViagemDAO() throws Exception {
    }

    public Viagem consultar(int idViagem) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Incluir uma viagem no banco de dados
     * @access public
     * @param Viagem obj
     * @return void
     * @throws Exception
     */    
    public void incluir(Viagem obj) throws DAOException {
        if (obj == null) {
            throw new DAOException("Viagem inválida para incluir!");
        }

        PreparedStatement stmt = null;

        String sql = "INSERT INTO viagem"
                    + "\n(id_usuario, descricao, data_inicial, data_final, excluido, bloqueado)"
                    + "\n VALUES (?, ?, ?, ?, ?, ?)";

        try {
            stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(1, obj.getViajante().getIdUsuario());
            stmt.setString(2, obj.getDescricao());
            stmt.setDate(3, new java.sql.Date(obj.getDataInicial().getTime()));
            stmt.setDate(4, new java.sql.Date(obj.getDataFinal().getTime()));
            stmt.setInt(5, obj.getExcluido());
            stmt.setInt(6, obj.getBloqueado());
            stmt.executeUpdate();
        } catch (Exception excecao) {
            throw new DAOException(excecao);
        }
    }

	@Override
	public void alterar(Viagem obj) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excluir(Viagem obj) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Viagem> consultarTodos() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}
}