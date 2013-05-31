package br.com.les20131.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.les20131.model.Empresa;
import br.com.les20131.model.Viajante;

/**
 * Classe DAO de avaliação
 * @author 200920183
 */
public class EmpresaDAO extends DAOBase<Empresa> {

    /**
     * Construtor da classe
     * @access public
     * @throws DAOException
     */
    public EmpresaDAO() throws Exception {
    	super();
    }

    /**
     * Consulta uma empresa pelo id
     * @access public
     * @param int idEmpresa
     * @return Empresa
     */
    public Empresa consultar(int idEmpresa) throws DAOException {
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        int indice = 0;
        String sql = "SELECT u.id_usuario, u.email, u.nome, u.senha, u.excluido, u.bloqueado"
    			+ "\n, e.url, e.definicao, e.imagem"
                + "\n FROM usuario u, empresa e"
                + "\n WHERE u.id_usuario = ?"
                + "\n AND u.id_usuario = e.id_usuario"
                + "\n AND u.excluido = 0";

        try {
            stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(++indice, idEmpresa);
            resultSet = stmt.executeQuery();
            
            Empresa Empresa = null;
            if (resultSet.next()) {
            	Empresa = new Empresa(resultSet.getInt("id_usuario")
            		, resultSet.getString("email")
            		, resultSet.getString("nome")
            		, resultSet.getString("senha")
            		, resultSet.getString("url")
            		, resultSet.getString("definicao")
            		, resultSet.getBinaryStream("imagem")
            		, resultSet.getInt("excluido")
            		, resultSet.getInt("bloqueado"));
            }

            return Empresa;
        } catch (Exception excecao) {
            throw new DAOException(excecao);
        }
    }
    
    /**
     * Consulta empresas pelo nome
     * @access public
     * @param String nome
     * @return List<Empresa>
     * @throws DAOException
     */
    public List<Empresa> consultar(String nome) throws DAOException {
        int indice = 0;
        nome = "%" + nome + "%";
        PreparedStatement stmt = null;
        ResultSet resultSet = null;

        String sql = "SELECT u.id_usuario, u.email, u.nome, u.senha, u.excluido, u.bloqueado"
        			+ "\n, e.url, e.definicao, e.imagem"
                    + "\n FROM usuario u, empresa e"
                    + "\n WHERE UPPER(u.nome) LIKE UPPER(?)"
                    + "\n AND u.id_usuario = e.id_usuario"
                    + "\n AND u.excluido = 0";

        try {
            stmt = this.conexao.prepareStatement(sql);
            stmt.setString(++indice, nome);
            resultSet = stmt.executeQuery();
            
            List<Empresa> lista = new ArrayList<Empresa>();
            while (resultSet.next()) {
            	lista.add(new Empresa(resultSet.getInt("id_usuario")
	                , resultSet.getString("email")
	                , resultSet.getString("nome")
	                , resultSet.getString("senha")
	                , resultSet.getString("url")
	                , resultSet.getString("definicao")
	                , resultSet.getBinaryStream("imagem")	                
	                , resultSet.getInt("excluido")
	                , resultSet.getInt("bloqueado")));
            }
            return lista;
        } catch (Exception excecao) {
            throw new DAOException(excecao);
        } 
    }
   
    /**
     * Incluir uma empresa no banco de dados
     * @access public
     * @param Empresa obj
     * @return void
     * @throws Exception
     */    
    public void incluir(Empresa obj) throws DAOException {
        if (obj == null) {
            throw new DAOException("Empresa inválida para incluir.");
        }
        int indice = 0;
        PreparedStatement stmt = null;

        String sql = "INSERT INTO empresa"
                    + "\n(id_usuario, url, definicao)"
                    + "\n VALUES (?, ?, ?)";

        try {
            stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(++indice, obj.getIdUsuario());
            stmt.setString(++indice, obj.getUrl());
            stmt.setString(++indice, obj.getDefinicao());
            stmt.executeUpdate();
        } catch (Exception excecao) {
            throw new DAOException(excecao);
        }
    }

    /**
     * Altera uma empresa
     * @access public
     * @param Empresa obj
     * @return void
     * @throws DAOException
     */
	@Override
	public void alterar(Empresa obj) throws DAOException {
        if (obj == null) {
            throw new DAOException("Empresa inválida para alterar.");
        }
        int indice = 0;
    	PreparedStatement stmt = null;

        String sql = "UPDATE empresa SET"
                + "\n, url = ?"
        		+ "\n, definicao = ?"
                + "\n, imagem = ?"
                + "\n WHERE id_empresa = ?";

        try {
	        stmt = this.conexao.prepareStatement(sql);
	        stmt.setString(++indice, obj.getUrl());
	        stmt.setString(++indice, obj.getDefinicao());
	        stmt.setBlob(++indice, obj.getImagem());
            stmt.executeUpdate();
        } catch (Exception excecao) {
            throw new DAOException(excecao);
        }
	}

    /**
     * Exclui uma empresa no banco de dados
     * @access public
     * @param Viagem Empresa
     * @return void
     * @throws Exception
     */	
	@Override
	public void excluir(Empresa obj) throws DAOException {
        throw new DAOException("Não implementado ainda!");
    }
	
}