package br.com.les20131.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.les20131.model.Avaliacao;
import br.com.les20131.model.Empresa;
import br.com.les20131.model.Usuario;
import br.com.les20131.model.Anuncio;

/**
 * Classe DAO de anúncio
 * @author 200910001
 */
public class AnuncioDAO extends DAOBase<Anuncio> {

    /**
     * Construtor da classe
     * @access public
     * @throws DAOException
     */
    public AnuncioDAO() throws Exception {
        super();
    }

    /**
     * Consulta um anúncio pelo id
     * @access public
     * @param int id
     * @return Anuncio
     * @throws DAOException
     */
    public Anuncio consultar(int id) throws DAOException {
    	if (id <= 0) {
            throw new DAOException("Anúncio inválido.");
        }
        int indice = 0;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;

        String sql = "SELECT a.id_anuncio, a.id_usuario, a.anuncio, a.data_inicial, a.data_final, a.data_inclusao"        			
                    + "\n FROM anuncio a"
                    + "\n WHERE a.id_anuncio = ?";

        try {
            stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(++indice, id);
            resultSet = stmt.executeQuery();

            EmpresaDAO empresaDAO = new EmpresaDAO();
            Anuncio anuncio = null;
            if (resultSet.next()) {
            	anuncio = new Anuncio(resultSet.getInt("id_anuncio")
            			, empresaDAO.consultar(resultSet.getInt("id_usuario"))
            			, resultSet.getString("anuncio")
                        , resultSet.getDate("data_inicial")
                        , resultSet.getDate("data_final")
                        , resultSet.getTimestamp("data_inclusao"));
            }
            return anuncio;
        } catch (Exception excecao) {
            throw new DAOException(excecao);
        }
    }    
    
    /**
     * Consulta os anúncios pela empresa
     * @access public
     * @param Empresa empresa
     * @return List<Anuncio>
     * @throws Exception
     */
    public List<Anuncio> consultar(Empresa empresa) throws DAOException {
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        int indice = 0;
        String sql = "SELECT a.id_anuncio, a.id_usuario, a.anuncio, a.data_inicial, a.data_final, a.data_inclusao"
                    + "\n FROM anuncio a"
                    + "\n WHERE a.id_usuario = ?";

        try {
            stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(++indice, empresa.getIdUsuario());
            resultSet = stmt.executeQuery();

            List<Anuncio> listaAnuncio = new ArrayList<Anuncio>();            
            EmpresaDAO empresaDAO = new EmpresaDAO();

            while (resultSet.next()) {
            	listaAnuncio.add( new Anuncio(resultSet.getInt("id_anuncio")
                , empresaDAO.consultar(resultSet.getInt("id_usuario"))
                , resultSet.getString("anuncio")
                , resultSet.getDate("data_inicial")
                , resultSet.getDate("data_final")
                , resultSet.getTimestamp("data_inclusao")));
            }

            return listaAnuncio;
        } catch (Exception excecao) {
            throw new DAOException(excecao);
        }
    } 

    /**
     * Consulta anúncios ativos
     * @access public
     * @param int id
     * @return List<Anuncio>
     * @throws DAOException
     */
    public List<Anuncio> consultarAtivos() throws DAOException {
        PreparedStatement stmt = null;
        ResultSet resultSet = null;

        String sql = "SELECT a.id_anuncio, a.id_usuario, a.anuncio, a.data_inicial, a.data_final, a.data_inclusao"        			
                    + "\n FROM anuncio a"
                    + "\n WHERE a.data_inicial <= NOW()"
                    + "\n AND a.data_final + INTERVAL 1 DAY >= NOW()";

        try {
            stmt = this.conexao.prepareStatement(sql);
            resultSet = stmt.executeQuery();
            EmpresaDAO empresaDAO = new EmpresaDAO();
            List<Anuncio> listaAnuncio = new ArrayList<Anuncio>();
            Empresa empresa;
            while (resultSet.next()) {
            	empresa = empresaDAO.consultar(resultSet.getInt("id_usuario"));
            	if (empresa != null) {
	            	listaAnuncio.add(new Anuncio(resultSet.getInt("id_anuncio")
	            			, empresa
	            			, resultSet.getString("anuncio")
	                        , resultSet.getDate("data_inicial")
	                        , resultSet.getDate("data_final")
	            			, resultSet.getTimestamp("data_inclusao")));
            	}
            }
            return listaAnuncio;
        } catch (Exception excecao) {
            throw new DAOException(excecao);
        }
    }    
    
    /**
     * Incluir um anúncio no banco de dados
     * @access public
     * @param Anuncio obj
     * @return void
     * @throws Exception
     */    
    public void incluir(Anuncio obj) throws DAOException {
        if (obj == null) {
            throw new DAOException("Anúncio inválido para incluir.");
        }
        int indice = 0;
        PreparedStatement stmt = null;

        String sql = "INSERT INTO anuncio"
                    + "\n(id_usuario, anuncio, data_inicial, data_final, data_inclusao)"
                    + "\n VALUES (?, ?, ?, ?, ?)";

        try {
            stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(++indice, obj.getEmpresa().getIdUsuario());            
            stmt.setString(++indice, obj.getAnuncio());
            stmt.setDate(++indice, new java.sql.Date(obj.getDataInicial().getTime()));
            stmt.setDate(++indice, new java.sql.Date(obj.getDataFinal().getTime())); 
            stmt.setDate(++indice, new java.sql.Date(obj.getDataInclusao().getTime()));
            stmt.executeUpdate();
        } catch (Exception excecao) {
            throw new DAOException(excecao);
        }
    }

    /**
     * Alterar um anúncio no banco de dados
     * @access public
     * @param Anuncio obj
     * @return void
     * @throws Exception
     */ 
    public void alterar(Anuncio obj) throws DAOException {
    	if (obj == null) {
            throw new DAOException("anúncio inválido para alterar.");
        }
        int indice = 0;
    	PreparedStatement stmt = null;

        String sql = "UPDATE anuncio SET"
                + "\n id_usuario = ?"
                + "\n, anuncio = ?"
        		+ "\n, data_inicial = ?"
                + "\n, data_final = ?"
                + "\n WHERE id_anuncio = ?";

        try {
	        stmt = this.conexao.prepareStatement(sql);
	        stmt.setInt(++indice, obj.getEmpresa().getIdUsuario());
	        stmt.setString(++indice, obj.getAnuncio());
	        stmt.setDate(++indice, new java.sql.Date(obj.getDataInicial().getTime()));
	        stmt.setDate(++indice, new java.sql.Date(obj.getDataFinal().getTime()));
	        stmt.setInt(++indice, obj.getIdAnuncio());
            stmt.executeUpdate();
        } catch (Exception excecao) {
            throw new DAOException(excecao);
        }
    }

    /**
     * Exclui um anúncio no banco de dados
     * @access public
     * @param Anuncio anuncio
     * @return void
     * @throws Exception
     */	
    public void excluir(Anuncio obj) throws DAOException {
    	if (obj == null) {
            throw new DAOException("Anúncio inválido para excluir.");
        }
        int indice = 0;
        PreparedStatement stmt = null;

        String sql = "DELETE FROM anuncio"
                    + "\n WHERE id_anuncio = ?";

        try {
            stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(++indice, obj.getIdAnuncio());
            stmt.executeUpdate();
        } catch (Exception excecao) {
            throw new DAOException(excecao);
        }
    }
}