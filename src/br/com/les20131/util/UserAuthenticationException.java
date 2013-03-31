package br.com.les20131.util;

/**
 *
 * @author 200920183
 */
public class UserAuthenticationException extends Exception {

    /**
     * Construtor da classe
     * @access public
     */
    public UserAuthenticationException() {
        super("Usuário ou senha inválidos!");
    }

}

