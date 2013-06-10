package br.com.les20131.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.les20131.model.Avaliacao;
import br.com.les20131.model.Empresa;
import br.com.les20131.model.Usuario;
import br.com.les20131.model.Viagem;
import br.com.les20131.model.Viajante;

/**
 * Classe DAO de avaliação
 * @author 200920183
 */
public class AvaliacaoDAO extends DAOBase<Avaliacao> {

    /**
     * Construtor da classe
     * @access public
     * @throws DAOException
     */
    public AvaliacaoDAO() throws Exception {
    	super();
    }

    /**
     * Consulta uma avaliacao pelo id
     * @access public
     * @param int idAvaliacao
     * @return Avaliacao
     */
    public Avaliacao consultar(int idAvaliacao) throws DAOException {
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        int indice = 0;
        String sql = "SELECT a.id_avaliacao, a.id_empresa, a.id_viajante, a.avaliacao, a.descricao, a.data_inclusao"
                    + "\n FROM avaliacao a"
                    + "\n WHERE a.id_avaliacao = ?";

        try {
            stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(++indice, idAvaliacao);
            resultSet = stmt.executeQuery();
            
            EmpresaDAO empresaDAO = new EmpresaDAO();
            ViajanteDAO viajanteDAO = new ViajanteDAO();
            Avaliacao avaliacao = null;
            if (resultSet.next()) {
            	avaliacao = new Avaliacao(resultSet.getInt("id_avaliacao")
            	, empresaDAO.consultar(resultSet.getInt("id_empresa"))
                , viajanteDAO.consultar(resultSet.getInt("id_viajante"))
                , resultSet.getInt("avaliacao")
                , resultSet.getString("descricao")
                , resultSet.getDate("data_inclusao"));
            }

            return avaliacao;
        } catch (Exception excecao) {
            throw new DAOException(excecao);
        }
    }
   
    /**
     * Incluir uma avaliação no banco de dados
     * @access public
     * @param Avaliacao obj
     * @return void
     * @throws Exception
     */    
    public void incluir(Avaliacao obj) throws DAOException {
        if (obj == null) {
            throw new DAOException("Avaliação inválida para incluir.");
        }
        int indice = 0;
        PreparedStatement stmt = null;

        String sql = "INSERT INTO avaliacao"
                    + "\n(id_empresa, id_viajante, avaliacao, descricao, data_inclusao)"
                    + "\n VALUES (?, ?, ?, ?, ?)";

        try {
            stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(++indice, obj.getEmpresa().getIdUsuario());
            stmt.setInt(++indice, obj.getViajante().getIdUsuario());
            stmt.setInt(++indice, obj.getAvaliacao());
            stmt.setString(++indice, obj.getDescricao());
            stmt.setTimestamp(++indice, new java.sql.Timestamp(obj.getDataInclusao().getTime()));
            stmt.executeUpdate();
        } catch (Exception excecao) {
            throw new DAOException(excecao);
        }
    }

    /**
     * Altera uma avaliação
     * @access public
     * @param Avaliacao obj
     * @return void
     * @throws DAOException
     */
	@Override
	public void alterar(Avaliacao obj) throws DAOException {
        if (obj == null) {
            throw new DAOException("Avaliação inválida para alterar.");
        }
        int indice = 0;
    	PreparedStatement stmt = null;

        String sql = "UPDATE avaliacao SET"
                + "\n avaliacao = ?"
        		+ "\n, descricao = ?"
                + "\n WHERE id_avaliacao = ?";

        try {
	        stmt = this.conexao.prepareStatement(sql);
	        stmt.setInt(++indice, obj.getAvaliacao());
	        stmt.setString(++indice, obj.getDescricao());
	        stmt.setInt(++indice, obj.getIdAvaliacao());
            stmt.executeUpdate();
        } catch (Exception excecao) {
            throw new DAOException(excecao);
        }
	}

    /**
     * Exclui uma avaliação no banco de dados
     * @access public
     * @param Viagem Avaliacao
     * @return void
     * @throws Exception
     */	
	@Override
	public void excluir(Avaliacao obj) throws DAOException {
        if (obj == null) {
            throw new DAOException("Avaliação inválida para excluir.");
        }
        int indice = 0;
        PreparedStatement stmt = null;

        String sql = "DELETE FROM avaliacao"
                    + "\n WHERE id_avaliacao = ?";

        try {
            stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(++indice, obj.getIdAvaliacao());
            stmt.executeUpdate();
        } catch (Exception excecao) {
            throw new DAOException(excecao);
        }
    }
	
    /**
     * Consulta as avaliações pelo viajante
     * @access public
     * @param Viajante viajante
     * @return List<Viajante>
     * @throws Exception
     */
    public List<Avaliacao> consultar(Viajante viajante) throws DAOException {
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        int indice = 0;
        String sql = "SELECT a.id_avaliacao, a.id_empresa, a.id_viajante, a.avaliacao, a.descricao, a.data_inclusao"
                    + "\n FROM avaliacao a"
                    + "\n WHERE a.id_viajante = ?";

        try {
            stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(++indice, viajante.getIdUsuario());
            resultSet = stmt.executeQuery();

            List<Avaliacao> listaAvaliacao = new ArrayList<Avaliacao>();
            ViajanteDAO viajanteDAO = new ViajanteDAO();
            EmpresaDAO empresaDAO = new EmpresaDAO();

            while (resultSet.next()) {
                listaAvaliacao.add( new Avaliacao(resultSet.getInt("id_avaliacao")
                , empresaDAO.consultar(resultSet.getInt("id_empresa"))
                , viajanteDAO.consultar(resultSet.getInt("id_viajante"))
                , resultSet.getInt("avaliacao")
                , resultSet.getString("descricao")
                , resultSet.getTimestamp("data_inclusao")));
            }

            return listaAvaliacao;
        } catch (Exception excecao) {
            throw new DAOException(excecao);
        }
    }
    
    /**
     * Consultar avaliação por viajante e empresa
     * @access public
     * @param Viajante viajante
     * @param Empresa empresa
     * @return Avaliacao
     * @throws DAOException
     */
    public Avaliacao consultar(Viajante viajante, Empresa empresa) throws DAOException {
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        int indice = 0;
        String sql = "SELECT a.id_avaliacao, a.id_empresa, a.id_viajante, a.avaliacao, a.descricao, a.data_inclusao"
                    + "\n FROM avaliacao a"
                    + "\n WHERE a.id_viajante = ?"
                    + "\n AND a.id_empresa = ?";

        try {
            stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(++indice, viajante.getIdUsuario());
            stmt.setInt(++indice, empresa.getIdUsuario());
            resultSet = stmt.executeQuery();
            
            EmpresaDAO empresaDAO = new EmpresaDAO();
            ViajanteDAO viajanteDAO = new ViajanteDAO();
            Avaliacao avaliacao = null;
            if (resultSet.next()) {
            	avaliacao = new Avaliacao(resultSet.getInt("id_avaliacao")
            	, empresaDAO.consultar(resultSet.getInt("id_empresa"))
                , viajanteDAO.consultar(resultSet.getInt("id_viajante"))
                , resultSet.getInt("avaliacao")
                , resultSet.getString("descricao")
                , resultSet.getDate("data_inclusao"));
            }

            return avaliacao;
        } catch (Exception excecao) {
            throw new DAOException(excecao);
        }    	
    }
    
    /**
     * Consulta as avaliações pela empresa
     * @access public
     * @param Empresa empresa
     * @return List<Viajante>
     * @throws Exception
     */
    public List<Avaliacao> consultar(Empresa empresa) throws DAOException {
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        int indice = 0;
        String sql = "SELECT a.id_avaliacao, a.id_empresa, a.id_viajante, a.avaliacao, a.descricao, a.data_inclusao"
                    + "\n FROM avaliacao a"
                    + "\n WHERE a.id_empresa = ?";

        try {
            stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(++indice, empresa.getIdUsuario());
            resultSet = stmt.executeQuery();

            List<Avaliacao> listaAvaliacao = new ArrayList<Avaliacao>();
            ViajanteDAO viajanteDAO = new ViajanteDAO();
            EmpresaDAO empresaDAO = new EmpresaDAO();

            while (resultSet.next()) {
                listaAvaliacao.add( new Avaliacao(resultSet.getInt("id_avaliacao")
                , empresaDAO.consultar(resultSet.getInt("id_empresa"))
                , viajanteDAO.consultar(resultSet.getInt("id_viajante"))
                , resultSet.getInt("avaliacao")
                , resultSet.getString("descricao")
                , resultSet.getTimestamp("data_inclusao")));
            }

            return listaAvaliacao;
        } catch (Exception excecao) {
            throw new DAOException(excecao);
        }
    }    
	
}