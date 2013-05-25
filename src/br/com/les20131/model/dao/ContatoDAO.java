package br.com.les20131.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import br.com.les20131.model.Contato;
import br.com.les20131.model.ImagemViagem;
import br.com.les20131.model.Viagem;
import br.com.les20131.model.Viajante;

public class ContatoDAO extends DAOBase<Contato> {
	
	public ContatoDAO() throws Exception {
		super();
	}

    /**
     * Consulta um contato pelo id
     * @access public
     * @param int id
     * @return Contato
     */
    public Contato consultar(int id) throws DAOException {
    	if (id <= 0) {
            throw new DAOException("Contato inválido.");
        }
        int indice = 0;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;

        String sql = "SELECT c.id_contato, c.id_usuario1, c.id_usuario2, c.data_inicio"
        		    + "\n FROM contato c"
                    + "\n WHERE c.id_contato = ?";
        
        try {
            stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(++indice, id);
            resultSet = stmt.executeQuery();

            Contato contato = null;
            if (resultSet.next()) {
            	ViajanteDAO viajanteDAO = new ViajanteDAO();
            	contato = new Contato(resultSet.getInt("id_contato")
                                , viajanteDAO.consultar(resultSet.getInt("id_usuario1"))
                                , viajanteDAO.consultar(resultSet.getInt("id_usuario2"))
                                , resultSet.getDate("data_inicio"));
            }
            return contato;
        } catch (Exception excecao) {
            throw new DAOException(excecao);
        }
    }
    
    /**
     * Consulta um contato pelo usuário e pelo seu contato
     * @access public
     * @param int idUsuario1
     * @param int idUsuario2
     * @return Contato
     */
    public Contato consultar(int idUsuario1, int idUsuario2) throws DAOException {
    	if (idUsuario1 <= 0 && idUsuario2 <= 0) {
            throw new DAOException("Usuário e contato inválido.");
        }
        int indice = 0;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;

        String sql = "SELECT c.id_contato, c.id_usuario1, c.id_usuario2, c.data_inicio"
        		    + "\n FROM contato c"
                    + "\n WHERE c.id_usuario1 = ?"
        		    + "\n AND c.id_usuario2 = ?";
        
        try {
            stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(++indice, idUsuario1);
            stmt.setInt(++indice, idUsuario2);
            resultSet = stmt.executeQuery();

            Contato contato = null;
            if (resultSet.next()) {
            	ViajanteDAO viajanteDAO = new ViajanteDAO();
            	contato = new Contato(resultSet.getInt("id_contato")
                                , viajanteDAO.consultar(resultSet.getInt("id_usuario1"))
                                , viajanteDAO.consultar(resultSet.getInt("id_usuario2"))
                                , resultSet.getDate("data_inicio"));
            }
            return contato;
        } catch (Exception excecao) {
            throw new DAOException(excecao);
        }
    }    
    
    /**
     * Incluir um contato no banco de dados
     * @access public
     * @param Contato obj
     * @return void
     * @throws Exception
     */
    @Override
    public void incluir(Contato obj) throws DAOException {
        if (obj == null) {
            throw new DAOException("Contato inválido para incluir.");
        }
        int indice = 0;
        PreparedStatement stmt = null;

        String sql = "INSERT INTO contato"
                    + "\n(id_contato, id_usuario1, id_usuario2, data_inicio)"
                    + "\n VALUES (?, ?, ?, ?)";

        try {
            stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(++indice, obj.getIdContato());
            stmt.setInt(++indice, obj.getViajante1().getIdUsuario());
            stmt.setInt(++indice, obj.getViajante2().getIdUsuario());
            stmt.setDate(++indice, new java.sql.Date(obj.getDataInicio().getTime()));
            stmt.executeUpdate();
        } catch (Exception excecao) {
            throw new DAOException(excecao);
        }
    } 

    /**
     * Exclui um contato no banco de dados
     * @access public
     * @param Contato contato
     * @return void
     * @throws Exception
     */	
	@Override
	public void excluir(Contato obj) throws DAOException {
        if (obj == null) {
            throw new DAOException("Contato para excluir.");
        }
        int indice = 0;
        PreparedStatement stmt = null;

        String sql = "DELETE FROM contato"
                    + "\n WHERE id_contato = ?";

        try {
            stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(++indice, obj.getIdContato());
            stmt.executeUpdate();
        } catch (Exception excecao) {
            throw new DAOException(excecao);
        }
    }    
    
    @Override
    public void alterar(Contato obj) throws DAOException {
    	throw new UnsupportedOperationException("Inválido.");
    }

	@Override
	public List<Contato> consultarTodos() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}
    
    
	
}
