package br.com.les20131.model.bean;

import java.io.InputStream;
import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.Part;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import br.com.les20131.model.Viajante;
import br.com.les20131.model.Usuario;
import br.com.les20131.model.bean.UsuarioBean;
import br.com.les20131.model.dao.UsuarioDAO;
import br.com.les20131.model.dao.ViajanteDAO;
import br.com.les20131.util.UserAuthenticationException;

/**
 * Classe bean de viajante
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
     * @param String email
     * @param String nome
     * @param String senha
     * @param String sexo
     * @param String dataNascimento
     * @return void
     * @throws Exception
     */
    public void incluir(String email, String nome, String senha, String sexo, String dataNascimento) throws Exception {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	this.usuarioDAO = new UsuarioDAO();
    	this.viajante = new Viajante(email, nome, senha, sexo, dateFormat.parse(dataNascimento));
    	this.usuarioDAO.incluir((Usuario)this.viajante);
    	this.viajante.setIdUsuario(((Usuario)this.usuarioDAO.consultar(email, senha)).getIdUsuario());
      	this.viajanteDAO = new ViajanteDAO();
      	this.viajanteDAO.incluir(this.viajante);
    }

    /**
     * Altera um viajante
     * @access public
     * @param int idUsuario
     * @param String nome
     * @param String sexo
     * @param String dataNascimento
     * @param String latitude
     * @param String longitude
     * @param InputStream imagem
     * @return void
     * @throws Exception
     */
    public void alterar(int idUsuario, String nome, String sexo, String dataNascimento, String latitude, String longitude, InputStream imagem) throws Exception {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	this.usuarioDAO = new UsuarioDAO();
    	this.viajanteDAO = new ViajanteDAO();
      	this.viajante = this.viajanteDAO.consultar(idUsuario);
      	this.viajante.setNome(nome);
      	this.viajante.setSexo(sexo);
      	this.viajante.setDataNascimento(dateFormat.parse(dataNascimento));
      	if (latitude.isEmpty() == false) {
      		this.viajante.setLatitude(Double.parseDouble(latitude));
      	} else {
      		this.viajante.setLatitude(-9999);
      	}
      	if (longitude.isEmpty() == false) {
      		this.viajante.setLongitude(Double.parseDouble(longitude));
      	} else {
      		this.viajante.setLongitude(-9999);
      	}
      	if (imagem != null) {
      		this.viajante.setImagem(imagem);
      	}
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
