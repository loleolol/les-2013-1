package br.com.les20131.model.bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.les20131.model.Anuncio;
import br.com.les20131.model.Anuncio;
import br.com.les20131.model.Empresa;
import br.com.les20131.model.dao.AnuncioDAO;
import br.com.les20131.model.dao.AvaliacaoDAO;
import br.com.les20131.model.dao.EmpresaDAO;
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
	 * Consultar avaliações de uma empresa
	 * @access public
	 * @param Empresa empresa
	 * @return void
	 * @throws Exception
	 */
	public void consultar(Empresa empresa) throws Exception {
		AnuncioDAO anuncioDAO = new AnuncioDAO();
		this.listaAnuncio = anuncioDAO.consultar(empresa);
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
    
    /**
     * Insere um anuncio
     * @access public
     * @param int idUsuario
     * @param String anuncio
     * @param String dataInicial
     * @param String dataFinal
     * @return void
     * @throws Exception
     */
    public void incluir(int idUsuario, String anuncio, String dataInicial, String dataFinal) throws Exception {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	EmpresaDAO empresaDAO = new EmpresaDAO();
    	Empresa empresa = empresaDAO.consultar(idUsuario);
    	this.anuncio = new Anuncio(empresa, anuncio, dateFormat.parse(dataInicial), dateFormat.parse(dataFinal), new Date());
    	this.anuncioDAO.incluir(this.anuncio);
    	this.anuncio.setIdAnuncio(this.anuncioDAO.retornarUltimoId());
    }

    /**
     * Altera um anuncio
     * @access public
     * @param int idAnuncio
     * @param String anuncio
     * @param String dataInicial
     * @param String dataFinal
     * @return void
     * @throws Exception
     */
    public void alterar(int idAnuncio, String anuncio, String dataInicial, String dataFinal) throws Exception {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      	this.anuncio = this.anuncioDAO.consultar(idAnuncio);
      	this.anuncio.setAnuncio(anuncio);
      	this.anuncio.setDataInicial(dateFormat.parse(dataInicial));
      	this.anuncio.setDataFinal(dateFormat.parse(dataFinal));
      	this.anuncioDAO.alterar(this.anuncio);
    }

    /**
     * Exclui um anuncio
     * @access public
     * @param int idAnuncio
     * @return void
     * @throws Exception
     */
    public void excluir(int idAnuncio) throws Exception {
    	this.anuncio = this.anuncioDAO.consultar(idAnuncio);
    	this.anuncioDAO.excluir(this.anuncio);
    }
}
