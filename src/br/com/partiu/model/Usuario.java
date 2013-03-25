package br.com.partiu.model;

/**
 * Classe de usu�rio
 * @author 200920183
 */
public class Usuario {

    /**
     * Armazena o c�digo do usu�rio
     * @access private
     * @var int
     */
    private int codUsuario;

    /**
     * Armazena o usu�rio
     * @access private
     * @var String
     */
    private String usuario;

    /**
     * Armazena a senha
     * @access private
     * @var String
     */
    private String senha;

    /**
     * Construtor da classe
     * @access public
     * @param String usuario
     * @param String senha
     */
    public Usuario(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }

    /**
     * Construtor da classe
     * @access public
     * @param int codUsuario
     * @param String usuario
     * @param String senha
     */
    public Usuario(int codUsuario, String usuario, String senha) {
        this.codUsuario = codUsuario;
        this.usuario = usuario;
        this.senha = senha;
    }

    /**
     * Retorna o c�digo do usu�rio
     * @access public
     * @return integer
     */
    public int getCodUsuario() {
        return this.codUsuario;
    }

    /**
     * Retorna o usu�rio
     * @access public
     * @return String
     */
    public String getUsuario() {
        return this.usuario;
    }

    /**
     * Define o usu�rio
     * @access public
     * @param String usuario
     * @return void
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * Retorna a senha
     * @access public
     * @return String
     */
    public String getSenha() {
        return this.senha;
    }

    /**
     * Define a senha
     * @access public
     * @param String senha
     * @return void
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }


}

