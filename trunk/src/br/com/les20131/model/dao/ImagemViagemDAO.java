package br.com.les20131.model.dao;

import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.les20131.model.ImagemViagem;
import br.com.les20131.model.Viagem;
import br.com.les20131.model.Viajante;

/**
 * Classe DAO de imagem de viagem
 * @author 200920183
 */
public class ImagemViagemDAO extends DAOBase<ImagemViagem> {
    /**
     * Construtor da classe
     * @access public
     * @throws DAOException
     */
    public ImagemViagemDAO() throws Exception {
        super();
    }

    /**
     * Consulta uma imagem de viagem pelo id
     * @access public
     * @param int id
     * @return ImagemViagem
     */
    public ImagemViagem consultar(int id) throws DAOException {
    	if (id <= 0) {
            throw new DAOException("Imagem de viagem inválida!");
        }
        int indice = 0;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;

        String sql = "SELECT iv.id_imagem_viagem, iv.id_viagem, iv.imagem"
        		    + "\n FROM imagem_viagem iv"
                    + "\n WHERE iv.id_imagem_viagem = ?";
        
        try {
            stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(++indice, id);
            resultSet = stmt.executeQuery();

            ImagemViagem imagemViagem = null;
            if (resultSet.next()) {
            	ViagemDAO viagemDAO = new ViagemDAO();
            	imagemViagem = new ImagemViagem(resultSet.getInt("id_imagem_viagem")
                                , viagemDAO.consultar(resultSet.getInt("id_viagem"))
                                , resultSet.getBinaryStream("imagem"));
            }
            return imagemViagem;
        } catch (Exception excecao) {
            throw new DAOException(excecao);
        }
    }
    
    /**
     * Consulta as imagens de viagem pela viagem
     * @access public
     * @param int idViagem
     * @return List<ImagemViagem>
     * @throws Exception
     */
    public List<ImagemViagem> consultarPorViagem(int idViagem) throws DAOException {
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        int indice = 0;
        String sql = "SELECT iv.id_imagem_viagem, iv.id_viagem, iv.imagem"
                    + "\n FROM imagem_viagem iv"
                    + "\n WHERE iv.id_viagem = ?";    

        try {
            stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(++indice, idViagem);
            resultSet = stmt.executeQuery();

            List<ImagemViagem> listaImagemViagem = new ArrayList<ImagemViagem>();
            ViagemDAO viagemDAO = new ViagemDAO();

            while (resultSet.next()) {
            	listaImagemViagem.add(new ImagemViagem(resultSet.getInt("id_imagem_viagem")
                , viagemDAO.consultar(resultSet.getInt("id_viagem"))
                , resultSet.getBinaryStream("imagem")));
            }

            return listaImagemViagem;
        } catch (Exception excecao) {
            throw new DAOException(excecao);
        }
    }    
    
    /**
     * Incluir uma imagem de viagem
     * @access public
     * @param ImagemViagem obj
     * @return void
     * @throws Exception
     */    
    public void incluir(ImagemViagem obj) throws DAOException {
        if (obj == null) {
            throw new DAOException("Imagem de viagem inválida para incluir!");
        }
        int indice = 0;
        PreparedStatement stmt = null;

        String sql = "INSERT INTO imagem_viagem"
                    + "\n(id_viagem, imagem)"
                    + "\n VALUES (?, ?)";

        try {
            stmt = this.conexao.prepareStatement(sql);
           	stmt.setInt(++indice, obj.getViagem().getIdViagem());
            stmt.setBlob(++indice, obj.getImagem());
            stmt.executeUpdate();
        } catch (Exception excecao) {
            throw new DAOException(excecao);
        }
    }

    /**
     * Alterar uma imagem de viagem
     * @access public
     * @param ImagemViagem obj
     * @return void
     * @throws Exception
     */ 
    public void alterar(ImagemViagem obj) throws DAOException {
        if (obj == null) {
            throw new DAOException("Imagem de viagem inválida para alterar!");
        }
        int indice = 0;
    	PreparedStatement stmt = null;

        try {
            String sql = "UPDATE imagem_viagem SET"
                        + "\n id_viagem = ?"
                        + "\n, imagem = ?"
                        + "\n WHERE id_viagem_imagem = ?";

            stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(++indice, obj.getViagem().getIdViagem());
            stmt.setBlob(++indice, obj.getImagem());
            stmt.setInt(++indice, obj.getIdImagemViagem());
            stmt.executeUpdate();
        } catch (Exception excecao) {
            throw new DAOException(excecao);
        }
    }

    public void excluir(ImagemViagem obj) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<ImagemViagem> consultarTodos() throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }	
	

}
