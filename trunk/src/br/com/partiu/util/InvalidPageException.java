package br.com.partiu.util;

/**
*
* @author 200920183
*/
public class InvalidPageException extends Exception {

   /**
    * Construtor da classe
    * @access public
    */
   public InvalidPageException() {
       super("Página inválida");
   }

}