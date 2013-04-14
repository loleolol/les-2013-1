package br.com.les20131.model.bean;

import java.util.Date;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import br.com.les20131.model.Viajante;
import br.com.les20131.model.Usuario;
import br.com.les20131.model.bean.UsuarioBean;
import br.com.les20131.model.dao.UsuarioDAO;
import br.com.les20131.model.dao.ViajanteDAO;
import br.com.les20131.util.UserAuthenticationException;

/**
 *
 * @author 200920183
 */
public class ViajanteBean extends UsuarioBean {

    /**
     * Armazena o objeto de persistência de viajante
     * @access private
     * @var viajanteDAO
     */
    private ViajanteDAO viajanteDAO;
    
    /**
     * Armazena um viajante
     * @access private
     * @var Viajante
     */
    private Viajante viajante;

    /**
     * Construtor da classe
     * @access public
     */
    public ViajanteBean() {

    }

    /**
     * Retorna o viajante
     * @access public
     * @return Viajante
     */
    public Viajante getViajante() {
        return this.viajante;
    }
   
    
    /**
     * Consulta as viajantes cadastrados
     * @access public
     * @return void
     * @throws Exception
     */
    public void consultarViajantes() throws Exception {
        throw new NotImplementedException();
    }

    /**
     * Consulta um viajante com o código passado por parâmetro
     * @access public
     * @param int idUsuario
     * @return void
     * @throws Exception
     */
    public void consultar(int idUsuario) throws Exception {
    	this.viajanteDAO = new ViajanteDAO();
        this.viajante = this.viajanteDAO.consultar(idUsuario);
    }

    /**
     * Insere um viajante
     * @access public
     * @param String descrMarca
     * @return void
     * @throws Exception
     */
    public void incluir(String email, String nome, String senha, String sexo, String dataNascimento) throws Exception {
    	this.usuarioDAO = new UsuarioDAO();
    	this.viajante = new Viajante(email, nome, senha, sexo, new Date());
    	this.usuarioDAO.incluir((Usuario)this.viajante);
    	this.viajante.setIdUsuario(((Usuario)this.usuarioDAO.consultar(email, senha)).getIdUsuario());
      	this.viajanteDAO = new ViajanteDAO();
      	this.viajanteDAO.incluir(this.viajante);
    }

    /**
     * Altera um viajante
     * @access public
     * @return void
     * @throws Exception
     */
    public void alterar(int idUsuario, String nome, String sexo, String dataNascimento) throws Exception {
    	this.usuarioDAO = new UsuarioDAO();
    	this.viajanteDAO = new ViajanteDAO();
      	this.viajante = this.viajanteDAO.consultar(idUsuario);
      	this.viajante.setNome(nome);
      	this.viajante.setSexo(sexo);
      	//this.viajante.setDataNascimento(new Date(dataNascimento));
    	this.usuarioDAO.alterar((Usuario)this.viajante);
    	this.viajanteDAO.alterar(this.viajante);
    }

    /**
     * Exclui um viajante
     * @access public
     * @return void
     * @throws Exception
     */
    public void excluir() throws Exception {
        throw new NotImplementedException();
    }
       
}
