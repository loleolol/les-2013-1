package br.com.les20131.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.les20131.model.Usuario;
import br.com.les20131.model.Viagem;
import br.com.les20131.model.Viajante;

/**
 * Classe DAO de viagem
 * @author 200920183
 */
public class ViagemDAO extends DAOBase<Viagem> {

    /**
     * Construtor da classe
     * @access public
     * @throws DAOException
     */
    public ViagemDAO() throws Exception {
    	super();
    }

    /**
     * Consulta uma viagem pelo id
     * @access public
     * @param int idViagem
     * @return Viagem
     */
    public Viagem consultar(int idViagem) throws DAOException {
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        int indice = 0;
        String sql = "SELECT v.id_viagem, v.id_usuario, v.titulo, v.descricao, v.data_inicial, v.data_final, v.data_inclusao"
                    + "\n FROM viagem v"
                    + "\n WHERE v.id_viagem = ?";

        try {
            stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(++indice, idViagem);
            resultSet = stmt.executeQuery();
            
            ViajanteDAO viajanteDAO = new ViajanteDAO();
            Viagem viagem = null;
            if (resultSet.next()) {
            	viagem = new Viagem(resultSet.getInt("id_viagem")
                , viajanteDAO.consultar(resultSet.getInt("id_usuario"))
                , resultSet.getString("titulo")
                , resultSet.getString("descricao")
                , resultSet.getDate("data_inicial")
                , resultSet.getDate("data_final")
                , resultSet.getTimestamp("data_inclusao"));
            }

            return viagem;
        } catch (Exception excecao) {
            throw new DAOException(excecao);
        }
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
            throw new DAOException("Viagem inválida para incluir.");
        }
        int indice = 0;
        PreparedStatement stmt = null;

        String sql = "INSERT INTO viagem"
                    + "\n(id_usuario, titulo, descricao, data_inicial, data_final, data_inclusao, excluido, bloqueado)"
                    + "\n VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(++indice, obj.getViajante().getIdUsuario());
            stmt.setString(++indice, obj.getTitulo());
            stmt.setString(++indice, obj.getDescricao());
            stmt.setDate(++indice, new java.sql.Date(obj.getDataInicial().getTime()));
            stmt.setDate(++indice, new java.sql.Date(obj.getDataFinal().getTime()));
            stmt.setTimestamp(++indice, new java.sql.Timestamp(obj.getDataInclusao().getTime()));
            stmt.setInt(++indice, obj.getExcluido());
            stmt.setInt(++indice, obj.getBloqueado());
            stmt.executeUpdate();
        } catch (Exception excecao) {
            throw new DAOException(excecao);
        }
    }

    /**
     * Altera uma viagem
     * @access public
     * @param Viagem obj
     * @return void
     * @throws DAOException
     */
	@Override
	public void alterar(Viagem obj) throws DAOException {
        if (obj == null) {
            throw new DAOException("viagem inválido para alterar.");
        }
        int indice = 0;
    	PreparedStatement stmt = null;

        String sql = "UPDATE viagem SET"
                + "\n id_usuario = ?"
        		+ "\n, titulo = ?"
                + "\n, descricao = ?"
        		+ "\n, data_inicial = ?"
                + "\n, data_final = ?"
        		+ "\n, excluido = ?"
                + "\n, bloqueado = ?"
                + "\n WHERE id_viagem = ?";

        try {
	        stmt = this.conexao.prepareStatement(sql);
	        stmt.setInt(++indice, obj.getViajante().getIdUsuario());
	        stmt.setString(++indice, obj.getTitulo());
	        stmt.setString(++indice, obj.getDescricao());
	        stmt.setDate(++indice, new java.sql.Date(obj.getDataInicial().getTime()));
	        stmt.setDate(++indice, new java.sql.Date(obj.getDataFinal().getTime()));
	        stmt.setInt(++indice, obj.getExcluido());
	        stmt.setInt(++indice, obj.getBloqueado());
	        stmt.setInt(++indice, obj.getIdViagem());
            stmt.executeUpdate();
        } catch (Exception excecao) {
            throw new DAOException(excecao);
        }
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
            throw new DAOException("Viagem inválida para excluir.");
        }
        int indice = 0;
        PreparedStatement stmt = null;

        String sql = "DELETE FROM viagem"
                    + "\n WHERE id_viagem = ?";

        try {
            stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(++indice, obj.getIdViagem());
            stmt.executeUpdate();
        } catch (Exception excecao) {
            throw new DAOException(excecao);
        }
    }
		
    /**
     * Consulta as viagens pelo viajante
     * @access public
     * @param Usuario viajante
     * @return List<Viajante>
     * @throws Exception
     */
    public List<Viagem> consultar(Usuario viajante) throws DAOException {
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        int indice = 0;
        String sql = "SELECT v.id_viagem, v.id_usuario, v.titulo, v.descricao, v.data_inicial, v.data_final, v.data_inclusao"
                    + "\n FROM viagem v"
                    + "\n WHERE v.id_usuario = ?";

        try {
            stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(++indice, viajante.getIdUsuario());
            resultSet = stmt.executeQuery();

            List<Viagem> listaViagem = new ArrayList<Viagem>();
            ViajanteDAO viajanteDAO = new ViajanteDAO();

            while (resultSet.next()) {
                listaViagem.add( new Viagem(resultSet.getInt("id_viagem")
                , viajanteDAO.consultar(resultSet.getInt("id_usuario"))
                , resultSet.getString("titulo")
                , resultSet.getString("descricao")
                , resultSet.getDate("data_inicial")
                , resultSet.getDate("data_final")
                , resultSet.getTimestamp("data_inclusao")));
            }

            return listaViagem;
        } catch (Exception excecao) {
            throw new DAOException(excecao);
        }
    }
	
	
}