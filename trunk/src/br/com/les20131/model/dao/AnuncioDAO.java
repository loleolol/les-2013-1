package br.com.les20131.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

        String sql = "SELECT a.id_anuncio, a.id_usuario, a.anuncio, a.data_inicial, a.data_final"        			
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
                        , resultSet.getDate("data_final"));
            }
            return anuncio;
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

        String sql = "SELECT a.id_anuncio, a.id_usuario, a.anuncio, a.data_inicial, a.data_final"        			
                    + "\n FROM anuncio a"
                    + "\n WHERE a.data_inicial <= NOW()"
                    + "\n AND a.data_final + INTERVAL 1 DAY >= NOW()";

        try {
            stmt = this.conexao.prepareStatement(sql);
            resultSet = stmt.executeQuery();
            EmpresaDAO empresaDAO = new EmpresaDAO();
            List<Anuncio> listaAnuncio = new ArrayList<Anuncio>();
            if (resultSet.next()) {
            	listaAnuncio.add(new Anuncio(resultSet.getInt("id_anuncio")
            			, empresaDAO.consultar(resultSet.getInt("id_usuario"))
            			, resultSet.getString("anuncio")
                        , resultSet.getDate("data_inicial")
                        , resultSet.getDate("data_final")));
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
                    + "\n(id_usuario, anuncio, data_inicial, data_final)"
                    + "\n VALUES (?, ?, ?, ?)";

        try {
            stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(++indice, obj.getEmpresa().getIdUsuario());            
            stmt.setString(++indice, obj.getAnuncio());
            stmt.setDate(++indice, new java.sql.Date(obj.getDataInicial().getTime()));
            stmt.setDate(++indice, new java.sql.Date(obj.getDataFinal().getTime()));            
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
        throw new DAOException("Não implementado ainda");
    }

    public void excluir(Anuncio obj) throws DAOException {
    	throw new DAOException("Não implementado ainda");
    }
}