package br.com.les20131.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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

    /**
     * Exclui uma locacao no banco de dados
     * @access public
     * @param Locacao locacao
     * @return void
     * @throws Exception
     */	
	@Override
	public void excluir(Viagem obj) throws DAOException {
        if (obj == null) {
            throw new DAOException("Viagem inválida para excluir!");
        }

        PreparedStatement stmt = null;

        String sql = "DELETE FROM viagem"
                    + "\n WHERE id_viagem = ?";

        try {
            stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(1, obj.getIdViagem());
            stmt.executeUpdate();
        } catch (Exception excecao) {
            throw new DAOException(excecao);
        }
    }
	
	
	@Override
	public List<Viagem> consultarTodos() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}
	
    /**
     * Consulta as viagens pelo viajante
     * @access public
     * @param int idUsuario
     * @return List<Viajante>
     * @throws Exception
     */
    public List<Viagem> consultarPorViajante(int idUsuario) throws DAOException {
        PreparedStatement stmt = null;
        ResultSet resultSet = null;

        String sql = "SELECT v.id_viagem, v.id_usuario, v.descricao, v.data_inicial, v.data_final"
                    + "\n FROM viagem v"
                    + "\n WHERE v.id_usuario = ?";

        try {
            stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(1, idUsuario);
            resultSet = stmt.executeQuery();

            List<Viagem> listaViagem = new ArrayList<Viagem>();
            ViajanteDAO viajanteDAO = new ViajanteDAO();

            while (resultSet.next()) {
                listaViagem.add( new Viagem(resultSet.getInt("id_viagem")
                , viajanteDAO.consultar(resultSet.getInt("id_usuario"))
                , resultSet.getString("descricao")
                , resultSet.getDate("data_inicial")
                , resultSet.getDate("data_final")));
            }

            return listaViagem;
        } catch (Exception excecao) {
            throw new DAOException(excecao);
        }
    }
	
	
}