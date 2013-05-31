package br.com.les20131.model.bean;

import java.util.ArrayList;
import java.util.List;

import br.com.les20131.model.Contato;
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
     * Armazena o objeto de persistência de empresa
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
     * Armazena a lista de empresas
     * @access protected
     * @var List<Empresa>
     */
    protected List<Empresa> listaEmpresa;    
       
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
     * Define a empresa
     * @access public
     * @param Empresaempresa
     * @return void
     */ 
    public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
    
    /**
     * Retorna a lista de empresas
     * @access public
     * @return List<Empresa>
     */
    public List<Empresa> getListaEmpresa() {
		return listaEmpresa;
	}

    /**
     * Define a lista de empresas
     * @access public
     * @param List<Empresa> listaEmpresa
     * @return void
     */
	public void setListaEmpresa(List<Empresa> listaEmpresa) {
		this.listaEmpresa = listaEmpresa;
	}
    
	/**
     * Consulta uma empresa com o código passado por parâmetro
     * @access public
     * @param int idUsuario
     * @return void
     * @throws Exception
     */
    public void consultar(int idUsuario) throws Exception {
        this.empresa = this.empresaDAO.consultar(idUsuario);
    } 
    
    /**
     * Consulta empresas pelo nome
     * @access public
     * @param String nome
     * @return void
     * @throws Exception
     */
    public void consultar(String nome) throws Exception {
    	this.listaEmpresa = this.empresaDAO.consultar(nome);
    }    
}
