package br.com.partiu.util;

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
        super("Usu�rio ou senha inv�lidos!");
    }

}

