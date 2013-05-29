package br.com.les20131.model.bean;

import java.util.ArrayList;
import java.util.List;

import br.com.les20131.model.Administrador;
import br.com.les20131.model.Usuario;
import br.com.les20131.model.bean.UsuarioBean;
import br.com.les20131.model.dao.UsuarioDAO;
import br.com.les20131.model.dao.AdministradorDAO;
import br.com.les20131.util.UserAuthenticationException;

/**
 * Classe bean de administrador
 * @author 200910001
 */
public class AdministradorBean extends UsuarioBean {

    /**
     * Armazena o objeto de persistência de administrador
     * @access private
     * @var administradorDAO
     */
    private AdministradorDAO administradorDAO;
    
    /**
     * Armazena um administrador
     * @access private
     * @var Administrador
     */
    private Administrador administrador;
    
       
    /**
     * Construtor da classe
     * @throws Exception 
     * @access public
     */
    public AdministradorBean() throws Exception {
    	this.administradorDAO = new AdministradorDAO();
    	this.usuarioDAO = new UsuarioDAO();
    }

    /**
     * Retorna o administrador
     * @access public
     * @return Administrador
     */
    public Administrador getAdministrador() {
        return this.administrador;
    } 
    
    /**
     * Consulta um administrador com o código passado por parâmetro
     * @access public
     * @param int idUsuario
     * @return void
     * @throws Exception
     */
    public void consultar(int idUsuario) throws Exception {
        this.administrador = this.administradorDAO.consultar(idUsuario);
    }             
}
