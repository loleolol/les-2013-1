package br.com.les20131.model;

/**
 * Classe de administrador
 * @author 200910001
 */
public class Administrador extends Usuario {
	
	/**
	 * Construtor da classe
	 * @access public
	 * @param String email
	 * @param String nome
	 * @param String senha
	 */
	public Administrador(String email, String nome, String senha) {
		super(email, nome, senha);
	}
	
	/**
	 * Construtor da classe
	 * @access public
	 * @param int idUsuario
	 * @param String email
	 * @param String nome
	 * @param String senha
	 */
	public Administrador(int idUsuario, String email, String nome, String senha, int excluido, int bloqueado) {
		super(idUsuario, email, nome, senha, excluido, bloqueado);
	}	
	
}
