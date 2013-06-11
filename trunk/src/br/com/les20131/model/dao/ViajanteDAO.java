package br.com.les20131.model.dao;

import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.les20131.model.Usuario;
import br.com.les20131.model.Viajante;

/**
 * Classe DAO de viajante
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

    /**
     * Consulta um viajante pelo id
     * @access public
     * @param int id
     * @return viajante
     * @throws DAOException
     */
    public Viajante consultar(int id) throws DAOException {
    	if (id <= 0) {
            throw new DAOException("Viajante inválido.");
        }
        int indice = 0;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;

        String sql = "SELECT u.id_usuario, u.email, u.nome, u.senha, u.excluido, u.bloqueado"
        			+ "\n, v.sexo, v.data_nascimento, v.latitude, v.longitude, v.imagem"
                    + "\n FROM usuario u, viajante v"
                    + "\n WHERE u.id_usuario = ?"
                    + "\n AND u.id_usuario = v.id_usuario"
                    + "\n AND u.excluido = 0";

        try {
            stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(++indice, id);
            resultSet = stmt.executeQuery();

            Viajante viajante = null;
            if (resultSet.next()) {
            	viajante = new Viajante(resultSet.getInt("id_usuario")
                                , resultSet.getString("email")
                                , resultSet.getString("nome")
                                , resultSet.getString("senha")
                                , resultSet.getInt("excluido")
                                , resultSet.getInt("bloqueado")
                                , resultSet.getString("sexo")
                                , resultSet.getDate("data_nascimento")
                                , resultSet.getDouble("latitude")
                                , resultSet.getDouble("longitude")
                                , resultSet.getBinaryStream("imagem"));
            }
            return viajante;
        } catch (Exception excecao) {
            throw new DAOException(excecao);
        }
    }
    
    /**
     * Consulta um viajante por e-mail
     * @access public
     * @param String email
     * @return Viajante
     * @throws DAOException
     */
    public Viajante consultarEmail(String email) throws DAOException {
        int indice = 0;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;

        String sql = "SELECT u.id_usuario, u.email, u.nome, u.senha, u.excluido, u.bloqueado"
        			+ "\n, v.sexo, v.data_nascimento, v.latitude, v.longitude, v.imagem"
                    + "\n FROM usuario u, viajante v"
                    + "\n WHERE u.email = ?"
                    + "\n AND u.id_usuario = v.id_usuario"
                    + "\n AND u.excluido = 0";

        try {
            stmt = this.conexao.prepareStatement(sql);
            stmt.setString(++indice, email);
            resultSet = stmt.executeQuery();

            Viajante viajante = null;
            if (resultSet.next()) {
            	viajante = new Viajante(resultSet.getInt("id_usuario")
                                , resultSet.getString("email")
                                , resultSet.getString("nome")
                                , resultSet.getString("senha")
                                , resultSet.getInt("excluido")
                                , resultSet.getInt("bloqueado")
                                , resultSet.getString("sexo")
                                , resultSet.getDate("data_nascimento")
                                , resultSet.getDouble("latitude")
                                , resultSet.getDouble("longitude")
                                , resultSet.getBinaryStream("imagem"));
            }
            return viajante;
        } catch (Exception excecao) {
            throw new DAOException(excecao);
        }  	
    }
    
    /**
     * Consulta contatos de viajante
     * @access public
     * @param int idUsuario
     * @return List<Viajante>
     * @throws DAOException
     */
    public List<Viajante> consultarContatos(int idUsuario) throws DAOException {
        int indice = 0;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;

        String sql = "SELECT u.id_usuario, u.email, u.nome, u.senha, u.excluido, u.bloqueado"
        			+ "\n, v2.sexo, v2.data_nascimento, v2.latitude, v2.longitude, v2.imagem"
                    + "\n FROM usuario u, viajante v2, contato c, viajante v1"
                    + "\n WHERE v1.id_usuario = ?"
                    + "\n AND u.id_usuario = v2.id_usuario"
                    + "\n AND ((v2.id_usuario = c.id_usuario2"
                    + "\n AND c.id_usuario1 = v1.id_usuario)"
                    + "\n OR (v2.id_usuario = c.id_usuario1"
                    + "\n AND c.id_usuario2 = v1.id_usuario))"
                    + "\n AND u.excluido = 0";

        try {
            stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(++indice, idUsuario);
            resultSet = stmt.executeQuery();
            
            List<Viajante> lista = new ArrayList<Viajante>();
            while (resultSet.next()) {
            	lista.add(new Viajante(resultSet.getInt("id_usuario")
	                , resultSet.getString("email")
	                , resultSet.getString("nome")
	                , resultSet.getString("senha")
	                , resultSet.getInt("excluido")
	                , resultSet.getInt("bloqueado")
	                , resultSet.getString("sexo")
	                , resultSet.getDate("data_nascimento")
	                , resultSet.getDouble("latitude")
	                , resultSet.getDouble("longitude")
	                , resultSet.getBinaryStream("imagem")));
            }
            return lista;
        } catch (Exception excecao) {
            throw new DAOException(excecao);
        }    	
    }    
    
    /**
     * Consulta viajantes pelo nome
     * @access public
     * @param String nome
     * @return List<Viajante>
     * @throws DAOException
     */
    public List<Viajante> consultar(String nome) throws DAOException {
        int indice = 0;
        nome = "%" + nome + "%";
        PreparedStatement stmt = null;
        ResultSet resultSet = null;

        String sql = "SELECT u.id_usuario, u.email, u.nome, u.senha, u.excluido, u.bloqueado"
        			+ "\n, v.sexo, v.data_nascimento, v.latitude, v.longitude, v.imagem"
                    + "\n FROM usuario u, viajante v"
                    + "\n WHERE UPPER(u.nome) LIKE UPPER(?)"
                    + "\n AND u.id_usuario = v.id_usuario"
                    + "\n AND u.excluido = 0";

        try {
            stmt = this.conexao.prepareStatement(sql);
            stmt.setString(++indice, nome);
            resultSet = stmt.executeQuery();
            
            List<Viajante> lista = new ArrayList<Viajante>();
            while (resultSet.next()) {
            	lista.add(new Viajante(resultSet.getInt("id_usuario")
	                , resultSet.getString("email")
	                , resultSet.getString("nome")
	                , resultSet.getString("senha")
	                , resultSet.getInt("excluido")
	                , resultSet.getInt("bloqueado")
	                , resultSet.getString("sexo")
	                , resultSet.getDate("data_nascimento")
	                , resultSet.getDouble("latitude")
	                , resultSet.getDouble("longitude")
	                , resultSet.getBinaryStream("imagem")));
            }
            return lista;
        } catch (Exception excecao) {
            throw new DAOException(excecao);
        }    	
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
            throw new DAOException("Viajante inválido para incluir.");
        }
        int indice = 0;
        PreparedStatement stmt = null;

        String sql = "INSERT INTO viajante"
                    + "\n(id_usuario, sexo, data_nascimento, latitude, longitude)"
                    + "\n VALUES (?, ?, ?, -9999, -9999)";

        try {
            stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(++indice, obj.getIdUsuario());
            stmt.setString(++indice, obj.getSexo());
            stmt.setDate(++indice, new java.sql.Date(obj.getDataNascimento().getTime()));
            stmt.executeUpdate();
        } catch (Exception excecao) {
            throw new DAOException(excecao);
        }
    }

    /**
     * alterar um viajante no banco de dados
     * @access public
     * @param Viajante obj
     * @return void
     * @throws Exception
     */ 
    public void alterar(Viajante obj) throws DAOException {
        if (obj == null) {
            throw new DAOException("Viajante inválido para alterar.");
        }
        int indice = 0;
    	PreparedStatement stmt = null;

        try {
            String sql = "UPDATE viajante SET"
                        + "\n sexo = ?"
                        + "\n, data_nascimento = ?"
            			+ "\n, latitude = ?"
            			+ "\n, longitude = ?"
                        + "\n, imagem = ?"
                        + "\n WHERE id_usuario = ?";

            stmt = this.conexao.prepareStatement(sql);
            stmt.setString(++indice, obj.getSexo());
            stmt.setDate(++indice, new java.sql.Date(obj.getDataNascimento().getTime()));
        	stmt.setDouble(++indice, obj.getLatitude());
        	stmt.setDouble(++indice, obj.getLongitude());
            stmt.setBlob(++indice, obj.getImagem());
            stmt.setInt(++indice, obj.getIdUsuario());
            stmt.executeUpdate();
        } catch (Exception excecao) {
            throw new DAOException(excecao);
        }
    }

    /**
     * Exclui o viajante
     * @access public
     * @return void
     */
    public void excluir(Viajante obj) throws DAOException {
        throw new DAOException("Não implementado ainda!");
    }
}