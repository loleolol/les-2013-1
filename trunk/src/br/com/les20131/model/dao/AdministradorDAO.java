package br.com.les20131.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.les20131.model.Usuario;
import br.com.les20131.model.Administrador;

/**
 * Classe DAO de administrador
 * @author 200910001
 */
public class AdministradorDAO extends DAOBase<Administrador> {

    /**
     * Construtor da classe
     * @access public
     * @throws DAOException
     */
    public AdministradorDAO() throws Exception {
        super();
    }

    /**
     * Consulta um administrador pelo id
     * @access public
     * @param int id
     * @return administrador
     * @throws DAOException
     */
    public Administrador consultar(int id) throws DAOException {
    	if (id <= 0) {
            throw new DAOException("Administrador inválido.");
        }
        int indice = 0;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;

        String sql = "SELECT u.id_usuario, u.email, u.nome, u.senha, u.excluido, u.bloqueado"        			
                    + "\n FROM usuario u, administrador a"
                    + "\n WHERE u.id_usuario = ?"
                    + "\n AND u.id_usuario = a.id_usuario";

        try {
            stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(++indice, id);
            resultSet = stmt.executeQuery();

            Administrador administrador = null;
            if (resultSet.next()) {
            	administrador = new Administrador(resultSet.getInt("id_usuario")
                                , resultSet.getString("email")
                                , resultSet.getString("nome")
                                , resultSet.getString("senha")
                                , resultSet.getInt("excluido")
                                , resultSet.getInt("bloqueado"));
            }
            return administrador;
        } catch (Exception excecao) {
            throw new DAOException(excecao);
        }
    }    
        
    /**
     * Incluir um administrador no banco de dados
     * @access public
     * @param Administrador obj
     * @return void
     * @throws Exception
     */    
    public void incluir(Administrador obj) throws DAOException {
        if (obj == null) {
            throw new DAOException("Administrador inválido para incluir.");
        }
        int indice = 0;
        PreparedStatement stmt = null;

        String sql = "INSERT INTO administrador"
                    + "\n(id_usuario)"
                    + "\n VALUES (?)";

        try {
            stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(++indice, obj.getIdUsuario());            
            stmt.executeUpdate();
        } catch (Exception excecao) {
            throw new DAOException(excecao);
        }
    }

    /**
     * alterar um administrador no banco de dados
     * @access public
     * @param Administrador obj
     * @return void
     * @throws Exception
     */ 
    public void alterar(Administrador obj) throws DAOException {
        throw new DAOException("Administrador não pode ser alterado");
    }

    public void excluir(Administrador obj) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}