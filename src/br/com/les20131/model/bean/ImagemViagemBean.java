package br.com.les20131.model.bean;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import br.com.les20131.model.ImagemViagem;
import br.com.les20131.model.Viagem;
import br.com.les20131.model.Viajante;
import br.com.les20131.model.dao.ImagemViagemDAO;
import br.com.les20131.model.dao.ViagemDAO;

/**
 * Classe bean de imagem de viagem
 * @author 200920183
 */
public class ImagemViagemBean {

    /**
     * Armazena o objeto de persistência de imagem de viagem
     * @access private
     * @var imagemViagemDAO
     */
    private ImagemViagemDAO imagemViagemDAO;
    
    /**
     * Armazena uma lista de imagem de viagens
     * @access private
     * @var List<ImagemViagem>
     */
    private List<ImagemViagem> listaImagemViagem;    
    
    /**
     * Armazena uma imagem de viagem
     * @access private
     * @var ImagemViagem
     */
    private ImagemViagem imagemViagem;

    /**
     * Construtor da classe
     * @access public
     */
    public ImagemViagemBean() {

    }

    /**
     * Retorna a lista de imagem de viagem
     * @access public
     * @return List<ImagemViagem>
     */
    public List<ImagemViagem> getListaImagemViagem() {
        return this.listaImagemViagem;
    }    
    
    /**
     * Retorna a imagem de viagem
     * @access public
     * @return ImagemViagem
     */
    public ImagemViagem getImagemViagem() {
        return this.imagemViagem;
    }

    /**
     * Consulta as imagens de viagens cadastradas
     * @access public
     * @return void
     * @throws Exception
     */
    public void consultarImagensViagens() throws Exception {
        throw new NotImplementedException();
    }

    /**
     * Consulta as imagens de viagens cadastradas por viagem
     * @access public
     * @param int idViagem
     * @return void
     * @throws Exception
     */
    public void consultarPorViagem(int idViagem) throws Exception {
        this.imagemViagemDAO = new ImagemViagemDAO();
        this.listaImagemViagem = this.imagemViagemDAO.consultarPorViagem(idViagem);
    }    
    
    /**
     * Consulta uma imagem de viagem com o código passado por parâmetro
     * @access public
     * @param int idImagemViagem
     * @return void
     * @throws Exception
     */
    public void consultar(int idImagemViagem) throws Exception {
    	this.imagemViagemDAO = new ImagemViagemDAO();
        this.imagemViagem = this.imagemViagemDAO.consultar(idImagemViagem);
    }

    /**
     * Insere um viagem
     * @access public
     * @param int idViagem
     * @param InputStream imagem
     * @return void
     * @throws Exception
     */
    public void incluir(int idViagem, InputStream imagem) throws Exception {
    	this.imagemViagemDAO = new ImagemViagemDAO();
    	ViagemDAO viagemDAO = new ViagemDAO();
    	Viagem viagem = viagemDAO.consultar(idViagem);
    	this.imagemViagem = new ImagemViagem(viagem, imagem);
    	this.imagemViagemDAO.incluir(this.imagemViagem);
    }
    
    /**
     * Altera um viagem
     * @access public
     * @param int idImagemViagem
     * @param int idViagem
     * @param InputStream imagem
     * @return void
     * @throws Exception
     */
    public void alterar(int idImagemViagem, int idViagem, InputStream imagem) throws Exception {
    	this.imagemViagemDAO = new ImagemViagemDAO();
    	ViagemDAO viagemDAO = new ViagemDAO();
    	this.imagemViagem = imagemViagemDAO.consultar(idImagemViagem);
      	this.imagemViagem.setViagem(viagemDAO.consultar(idViagem));
      	this.imagemViagem.setImagem(imagem);
      	this.imagemViagemDAO.alterar(this.imagemViagem);
    }

    /**
     * Exclui um viagem
     * @access public
     * @param int idImagemViagem
     * @return void
     * @throws Exception
     */
    public void excluir(int idImagemViagem) throws Exception {
    	this.imagemViagemDAO = new ImagemViagemDAO();
    	this.imagemViagem = imagemViagemDAO.consultar(idImagemViagem);
    	this.imagemViagemDAO.excluir(this.imagemViagem);
    }	
	
}
