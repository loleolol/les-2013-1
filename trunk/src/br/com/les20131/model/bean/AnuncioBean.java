package br.com.les20131.model.bean;

import java.util.ArrayList;
import java.util.List;

import br.com.les20131.model.Anuncio;
import br.com.les20131.model.dao.AnuncioDAO;
import br.com.les20131.util.UserAuthenticationException;

/**
 * Classe bean de anúncio
 * @author 200910001
 */
public class AnuncioBean {

    /**
     * Armazena o objeto de persistência de anúncio
     * @access private
     * @var anuncioDAO
     */
    private AnuncioDAO anuncioDAO;
    
    /**
     * Armazena um anúncio
     * @access private
     * @var Anuncio
     */
    private Anuncio anuncio;    
    
    /**
     * Armazena a lista de anúncios
     * @access private
     * @var List<Anuncio>
     */
    private List<Anuncio> listaAnuncio;
    
    /**
     * Construtor da classe
     * @throws Exception 
     * @access public
     */
    public AnuncioBean() throws Exception {
    	this.anuncioDAO = new AnuncioDAO();
    }

    /**
     * Retorna o anuncio
     * @access public
     * @return Anuncio
     */
    public Anuncio getAnuncio() {
        return this.anuncio;
    } 
    
    /**
     * Retorna a lista de anúncio
     * @access public
     * @return List<Anuncio>
     */
    public List<Anuncio> getListaAnuncio() {
    	return this.listaAnuncio;
    }
    
    /**
     * Consulta um anúncio com o código passado por parâmetro
     * @access public
     * @param int idAnuncio
     * @return void
     * @throws Exception
     */
    public void consultar(int idAnuncio) throws Exception {
        this.anuncio = this.anuncioDAO.consultar(idAnuncio);
    }
    
    /**
     * Consulta de anúncios ativos
     * @access public
     * @return void
     * @throws Exception
     */
    public void consultarAtivos() throws Exception {
    	this.listaAnuncio = this.anuncioDAO.consultarAtivos();
    }
}
