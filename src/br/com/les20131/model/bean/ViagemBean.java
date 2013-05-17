package br.com.les20131.model.bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import br.com.les20131.model.Usuario;
import br.com.les20131.model.Viagem;
import br.com.les20131.model.Viajante;
import br.com.les20131.model.dao.ImagemViagemDAO;
import br.com.les20131.model.dao.UsuarioDAO;
import br.com.les20131.model.dao.ViagemDAO;
import br.com.les20131.model.dao.ViajanteDAO;

/**
 * Classe bean de viagem
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
     * @throws Exception 
     * @access public
     */
    public ViagemBean() throws Exception {
    	this.listaViagem = new ArrayList<Viagem>();
    	this.viagemDAO = new ViagemDAO();
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
     * @param Viajante viajante
     * @return void
     * @throws Exception
     */
    public void consultar(Viajante viajante) throws Exception {
    	this.consultar((Usuario)viajante, false);
    }    
    
    /**
     * Consulta as viagens cadastradas por usuário
     * @access public
     * @param Usuario viajante
     * @return void
     * @throws Exception
     */
    public void consultar(Usuario viajante) throws Exception {
    	this.consultar(viajante, false);
    }
 
    /**
     * Consulta as viagens cadastradas por usuário
     * @access public
     * @param Viajante viajante
     * @param boolean carregarImagemViagem
     * @return void
     * @throws Exception
     */
    public void consultar(Viajante viajante, boolean carregarImagemViagem) throws Exception {
    	this.consultar((Usuario)viajante, carregarImagemViagem);
    }
    
    /**
     * Consulta as viagens cadastras por viajante
     * @access public
     * @param Usuario viajante
     * @param boolean carregarImagemViagem
	 * @return void
     * @throws Exception
     */
    public void consultar(Usuario viajante, boolean carregarImagemViagem) throws Exception {
    	ImagemViagemDAO imagemViagemDAO = new ImagemViagemDAO();
        this.listaViagem = this.viagemDAO.consultar(viajante);
        if (carregarImagemViagem == true) {
	        int indice = 0;
	        for (indice = 0; indice < this.listaViagem.size(); indice++) {
	        	this.listaViagem.get(indice).setImagemViagem(imagemViagemDAO.consultar(this.listaViagem.get(indice)));
	        }
        }
    }
    
    /**
     * Consulta um viagem com o código passado por parâmetro
     * @access public
     * @param int idViagem
     * @return void
     * @throws Exception
     */
    public void consultar(int idViagem) throws Exception {
        this.viagem = this.viagemDAO.consultar(idViagem);
    }
    
    /**
     * Insere um viagem
     * @access public
     * @param int idUsuario
     * @param String titulo
     * @param String descricao
     * @param String dataInicial
     * @param String dataFinal
     * @return void
     * @throws Exception
     */
    public void incluir(int idUsuario, String titulo, String descricao, String dataInicial, String dataFinal) throws Exception {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	ViajanteDAO viajanteDAO = new ViajanteDAO();
    	Viajante viajante = viajanteDAO.consultar(idUsuario);
    	this.viagem = new Viagem(viajante, titulo, descricao, dateFormat.parse(dataInicial), dateFormat.parse(dataFinal));
    	this.viagemDAO.incluir(this.viagem);
    	this.viagem.setIdViagem(this.viagemDAO.retornarUltimoId());
    }

    /**
     * Altera um viagem
     * @access public
     * @param int idViagem
     * @param String titulo
     * @param String descricao
     * @param String dataInicial
     * @param String dataFinal
     * @return void
     * @throws Exception
     */
    public void alterar(int idViagem, String titulo, String descricao, String dataInicial, String dataFinal) throws Exception {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
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
     * @param Viagem viagem
     * @return void
     * @throws Exception
     */
    public void excluir(Viagem viagem) throws Exception {
    	this.viagemDAO.excluir(viagem);
    }
       
}
