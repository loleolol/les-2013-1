package br.com.les20131.model.bean;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
     * @throws Exception 
     * @access public
     */
    public ImagemViagemBean() throws Exception {
    	this.listaImagemViagem = new ArrayList<ImagemViagem>();
    	this.imagemViagemDAO = new ImagemViagemDAO();
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
     * @param Viagem viagem
     * @return void
     * @throws Exception
     */
    public void consultar(Viagem viagem) throws Exception {
        this.listaImagemViagem = this.imagemViagemDAO.consultar(viagem);
    }    
    
    /**
     * Consulta uma imagem de viagem com o código passado por parâmetro
     * @access public
     * @param int idImagemViagem
     * @return void
     * @throws Exception
     */
    public void consultar(int idImagemViagem) throws Exception {
        this.imagemViagem = this.imagemViagemDAO.consultar(idImagemViagem);
    }

    /**
     * Consulta uma imagem de viagem e a adiciona à lista
     * @access public
     * @param int idImagemViagem
     * @return void
     * @throws Exception
     */
    public void adicionarLista(int idImagemViagem) throws Exception {
    	this.imagemViagem = this.imagemViagemDAO.consultar(idImagemViagem);
    	this.listaImagemViagem.add(this.imagemViagem);
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
    	ViagemDAO viagemDAO = new ViagemDAO();
    	this.imagemViagem = imagemViagemDAO.consultar(idImagemViagem);
      	this.imagemViagem.setViagem(viagemDAO.consultar(idViagem));
      	this.imagemViagem.setImagem(imagem);
      	this.imagemViagemDAO.alterar(this.imagemViagem);
    }

    /**
     * Exclui um viagem
     * @access public
     * @param ImagemViagem imagemViagem
     * @return void
     * @throws Exception
     */
    public void excluir(int idImagemViagem) throws Exception {
    	this.imagemViagem = this.imagemViagemDAO.consultar(idImagemViagem);
    	this.imagemViagemDAO.excluir(this.imagemViagem);
    }
    
    /**
     * Exclui uma lista de imagem de viagem
     * @access public
     * @param List<ImagemViagem> lista
     * @return void
     * @throws Exception
     */
    public void excluirLista(List<ImagemViagem> lista) throws Exception {
    	int indice = 0;
    	for (indice = 0; indice < lista.size(); indice++) {
    		this.imagemViagemDAO.excluir(lista.get(indice));
    	}
    }
	
}
