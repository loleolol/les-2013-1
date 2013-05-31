package br.com.les20131.model.bean;

import java.util.ArrayList;
import java.util.List;

import br.com.les20131.model.Empresa;
import br.com.les20131.model.Usuario;
import br.com.les20131.model.bean.UsuarioBean;
import br.com.les20131.model.dao.EmpresaDAO;
import br.com.les20131.model.dao.UsuarioDAO;
import br.com.les20131.model.dao.AdministradorDAO;
import br.com.les20131.util.UserAuthenticationException;

/**
 * Classe bean de empresa
 * @author 200910001
 */
public class EmpresaBean extends UsuarioBean {

    /**
     * Armazena o objeto de persist�ncia de empresa
     * @access private
     * @var EmpresaDAO
     */
    private EmpresaDAO empresaDAO;
    
    /**
     * Armazena um empresa
     * @access private
     * @var Empresa
     */
    private Empresa empresa;
    
       
    /**
     * Construtor da classe
     * @throws Exception 
     * @access public
     */
    public EmpresaBean() throws Exception {
    	this.empresaDAO = new EmpresaDAO();
    	this.usuarioDAO = new UsuarioDAO();
    }

    /**
     * Retorna a empresa
     * @access public
     * @return Empresa
     */
    public Empresa getEmpresa() {
        return this.empresa;
    } 
    
    /**
     * Consulta uma empresa com o c�digo passado por par�metro
     * @access public
     * @param int idUsuario
     * @return void
     * @throws Exception
     */
    public void consultar(int idUsuario) throws Exception {
        this.empresa = this.empresaDAO.consultar(idUsuario);
    }             
}
