package br.com.les20131.model.bean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import br.com.les20131.model.Usuario;
import br.com.les20131.model.Viagem;
import br.com.les20131.model.Viajante;
import br.com.les20131.model.dao.UsuarioDAO;
import br.com.les20131.model.dao.ViagemDAO;
import br.com.les20131.model.dao.ViajanteDAO;

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
     * Armazena uma lista de viagens
     * @access private
     * @var List<Viagem>
     */
    private List<Viagem> listaViagem;    
    
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
     * Retorna a lista de viagens
     * @access public
     * @return List<Viagem>
     */
    public List<Viagem> getListaViagem() {
        return this.listaViagem;
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
     * Consulta as viagens cadastradas por viajante
     * @access public
     * @return void
     * @throws Exception
     */
    public void consultarPorViajante(int idUsuario) throws Exception {
        this.viagemDAO = new ViagemDAO();
        this.listaViagem = this.viagemDAO.consultarPorViajante(idUsuario);
    }    
    
    /**
     * Consulta um viagem com o código passado por parâmetro
     * @access public
     * @param int idViagem
     * @return void
     * @throws Exception
     */
    public void consultar(int idViagem) throws Exception {
    	this.viagemDAO = new ViagemDAO();
        this.viagem = this.viagemDAO.consultar(idViagem);
    }

    /**
     * Insere um viagem
     * @access public
     * @param String descricao
     * @return void
     * @throws Exception
     */
    public void incluir(int idUsuario, String titulo, String descricao, String dataInicial, String dataFinal) throws Exception {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	this.viagemDAO = new ViagemDAO();
    	ViajanteDAO viajanteDAO = new ViajanteDAO();
    	Viajante viajante = viajanteDAO.consultar(idUsuario);
    	this.viagem = new Viagem(viajante, titulo, descricao, dateFormat.parse(dataInicial), dateFormat.parse(dataFinal));
    	this.viagemDAO.incluir(this.viagem);
    }

    /**
     * Altera um viagem
     * @access public
     * @return void
     * @throws Exception
     */
    public void alterar(int idViagem, String titulo, String descricao, String dataInicial, String dataFinal) throws Exception {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	this.viagemDAO = new ViagemDAO();
      	this.viagem = this.viagemDAO.consultar(idViagem);
      	this.viagem.setTitulo(titulo);
      	this.viagem.setDescricao(descricao);
      	this.viagem.setDataInicial(dateFormat.parse(dataInicial));
      	this.viagem.setDataFinal(dateFormat.parse(dataFinal));
      	this.viagemDAO.alterar(this.viagem);
    }

    /**
     * Exclui um viagem
     * @access public
     * @return void
     * @throws Exception
     */
    public void excluir(int idViagem) throws Exception {
    	this.viagemDAO = new ViagemDAO();
      	this.viagem = this.viagemDAO.consultar(idViagem);
    	this.viagemDAO.excluir(this.viagem);
    }
       
}
