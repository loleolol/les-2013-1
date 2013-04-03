package br.com.les20131.model.bean;

import java.util.Date;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import br.com.les20131.model.Viagem;
import br.com.les20131.model.dao.ViagemDAO;

/**
 *
 * @author 200920183
 */
public class ViagemBean {

    /**
     * Armazena o objeto de persistência de viagem
     * @access private
     * @var viagemDAO
     */
    private ViagemDAO viagemDAO;
    
    /**
     * Armazena uma viagem
     * @access private
     * @var Viagem
     */
    private Viagem viagem;

    /**
     * Construtor da classe
     * @access public
     */
    public ViagemBean() {

    }

    /**
     * Retorna o viagem
     * @access public
     * @return Viagem
     */
    public Viagem getViagem() {
        return this.viagem;
    }

    /**
     * Consulta as viagens cadastradas
     * @access public
     * @return void
     * @throws Exception
     */
    public void consultarViagens() throws Exception {
        throw new NotImplementedException();
    }

    /**
     * Consulta um viagem com o código passado por parâmetro
     * @access public
     * @param int idViagem
     * @return void
     * @throws Exception
     */
    public void consultar(int idViagem) throws Exception {
        throw new NotImplementedException();
    }

    /**
     * Insere um viagem
     * @access public
     * @param String descricao
     * @return void
     * @throws Exception
     */
    public void incluir(String descricao) throws Exception {
    	this.viagemDAO = new ViagemDAO();
    	this.viagem = new Viagem(descricao);
    	this.viagemDAO.incluir(this.viagem);
    }

    /**
     * Altera um viagem
     * @access public
     * @return void
     * @throws Exception
     */
    public void alterar() throws Exception {
        throw new NotImplementedException();
    }

    /**
     * Exclui um viagem
     * @access public
     * @return void
     * @throws Exception
     */
    public void excluir() throws Exception {
        throw new NotImplementedException();
    }
       
}
