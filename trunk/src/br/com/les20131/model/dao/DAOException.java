package br.com.les20131.model.dao;

/**
 *
 * @author 200920183
 */
public class DAOException extends Exception {

	/**
     * Construtor da classe
     * @access public
     */
    public DAOException() {

    }
    
    /**
     * Sobrecarga do construtor
     * @access public
     * @param String strArg
     */
    public DAOException(String arg) {
        super(arg);
    }

    /**
     * Sobrecarga do construtor
     * @access public
     * @param Throwable objArg
     */
    public DAOException(Throwable arg) {
        super(arg);
    }

}
