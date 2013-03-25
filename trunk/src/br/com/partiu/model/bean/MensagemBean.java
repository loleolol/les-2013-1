package br.com.partiu.model.bean;

/**
 *
 * @author 200920183
 */
public class MensagemBean {

    /**
     * Armazena a mensagem de erro
     * @access private
     * @var String
     */
    private String mensagem;

    /**
     * Construtor da classe
     * @access public
     */
    public MensagemBean() {

    }

    public MensagemBean(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return this.mensagem;
    }

}
