package br.com.les20131.model;

/**
 * Classe de usuário
 * @author 200920183
 */
public class Usuario {

    /**
     * Armazena o código do usuário
     * @access private
     * @var int
     */
    private int idUsuario;

    /**
     * Armazena o email
     * @access private
     * @var String
     */
    private String email;

    /**
     * Armazena o nome
     * @access private
     * @var String
     */
    private String nome;
    
    /**
     * Armazena a senha
     * @access private
     * @var String
     */
    private String senha;

    /**
     * Construtor da classe
     * @access public
     * @param String email
     * @param String nome
     * @param String senha
     */
    public Usuario(String email, String nome, String senha) {
        this.email = email;
        this.nome = nome;
        this.senha = senha;
    }

    /**
     * Construtor da classe
     * @access public
     * @param int idUsuario
     * @param String usuario
     * @param String nome
     * @param String senha
     */
    public Usuario(int idUsuario, String email, String nome, String senha) {
        this.idUsuario = idUsuario;
        this.email = email;
        this.nome = nome;
        this.senha = senha;
    }

    /**
     * Retorna o código do usuário
     * @access public
     * @return integer
     */
    public int getIdUsuario() {
        return this.idUsuario;
    }
    
    /**
     * Define o código do usuário
     * @access public
     * @param int idUsuario
     * @return void
     */
    public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	/**
     * Retorna o email
     * @access public
     * @return String
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Define o email
     * @access public
     * @param String email
     * @return void
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * Retorna o nome
     * @access public
     * @return String
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Define o nome
     * @access public
     * @param String nome
     * @return void
     */
    public void setNome(String nome) {
        this.email = nome;
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

