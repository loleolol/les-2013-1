package br.com.les20131.model.bean;

import java.util.Date;

import br.com.les20131.model.Contato;
import br.com.les20131.model.Viajante;
import br.com.les20131.model.dao.ContatoDAO;
import br.com.les20131.model.dao.ViajanteDAO;

/**
 * Classe bean de Contato
 * @author 200920183
 *
 */
public class ContatoBean {

    /**
     * Armazena o objeto de persistência de contato
     * @access protected
     * @var ContatoDAO
     */
    protected ContatoDAO contatoDAO;

    /**
     * Armazena um contato
     * @access protected
     * @var Contato
     */
    protected Contato contato;
    
    /**
     * Construtor da classe
     * @access public
     * @throws Exception 
     */
    public ContatoBean() throws Exception {
    	this.contatoDAO = new ContatoDAO();
    }

    /**
     * Retorna contato
     * @access public
     * @return Contato
     */
	public Contato getContato() {
		return contato;
	}

	/**
	 * Define contato
	 * @access public
	 * @param Contato contato
	 * @return void
	 */
	public void setContato(Contato contato) {
		this.contato = contato;
	}
	
    /**
     * Insere um contato
     * @access public
     * @param int idUsuario1
     * @param int idUsuario2
     * @return void
     * @throws Exception
     */
    public void incluir(int idUsuario1, int idUsuario2) throws Exception {
    	ViajanteDAO viajanteDAO = new ViajanteDAO();
    	Viajante viajante1 = viajanteDAO.consultar(idUsuario1);
    	Viajante viajante2 = viajanteDAO.consultar(idUsuario2);
    	this.contato = new Contato(viajante1, viajante2, new Date());
    	this.contatoDAO.incluir(this.contato);
    	this.contato.setIdContato(this.contatoDAO.retornarUltimoId());
    }
    
    /**
     * Exclui um contato de um usuário
     * @access public
     * @param int idUsuario1
     * @param int idUsuario2
     * @return void
     * @throws Exception
     */
    public void excluir(int idUsuario1, int idUsuario2) throws Exception {
    	this.contato = this.contatoDAO.consultar(idUsuario1, idUsuario2);
    	this.contatoDAO.excluir(this.contato);
    }    
    
    /**
     * Exclui um contato
     * @access public
     * @param int idContato
     * @return void
     * @throws Exception
     */
    public void excluir(int idContato) throws Exception {
    	this.contato = this.contatoDAO.consultar(idContato);
    	this.contatoDAO.excluir(this.contato);
    }
	
    /**
     * Consulta um contato
     * @access public
     * @param int idUsuario1
     * @param int idUsuario2
     * @return void
     * @throws Exception
     */
    public void consultar(int idUsuario1, int idUsuario2) throws Exception {
        this.contato = this.contatoDAO.consultar(idUsuario1, idUsuario2);
    }    
    
}
