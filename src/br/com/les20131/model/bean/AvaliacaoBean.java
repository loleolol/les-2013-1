package br.com.les20131.model.bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.les20131.model.Avaliacao;
import br.com.les20131.model.Empresa;
import br.com.les20131.model.Usuario;
import br.com.les20131.model.Viagem;
import br.com.les20131.model.Viajante;
import br.com.les20131.model.dao.AvaliacaoDAO;
import br.com.les20131.model.dao.EmpresaDAO;
import br.com.les20131.model.dao.UsuarioDAO;
import br.com.les20131.model.dao.ViajanteDAO;

/**
 * Classe bean avaliação 
 * @author 200920183
 *
 */
public class AvaliacaoBean {

	/**
	 * Armazena a DAO de avaliacao
	 * @access private
	 * @var AvaliacaoDAO
	 */
	private AvaliacaoDAO avaliacaoDAO;
	
	/**
	 * Armazena a avaliação
	 * @access private
	 * @var Avaliacao
	 */
	private Avaliacao avaliacao;
	
	/**
	 * Armazena a lista de avaliações
	 * @access private
	 * @var List<Avaliacao>
	 */
	private List<Avaliacao> listaAvaliacao;
	
	/**
	 * Construtor da classe
	 * @throws Exception 
	 */
	public AvaliacaoBean() throws Exception {
		this.avaliacaoDAO = new AvaliacaoDAO();
		this.listaAvaliacao = new ArrayList<Avaliacao>();
	}
  
	
	/**
	 * Armazena a avaliação
	 * @access public
	 * @return Avaliacao
	 */
	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	/**
	 * Define a avaliação
	 * @access public
	 * @param Avalicao avaliacao
	 * @return void
	 */
	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

	/**
	 * Retorna a lista de avaliação
	 * @access public
	 * @return List<Avaliacao>
	 */
	public List<Avaliacao> getListaAvaliacao() {
		return listaAvaliacao;
	}

	/**
	 * Define a lista de avaliação
	 * @access public
	 * @param List<Avaliacao> listaAvaliacao
	 * @return void
	 */
	public void setListaAvaliacao(List<Avaliacao> listaAvaliacao) {
		this.listaAvaliacao = listaAvaliacao;
	}

	/**
     * Consulta uma avaliação
	 * @access public
	 * @param int idAvaliacao
	 * @return void
	 * @throws Exception
	 */
	public void consultar(int idAvaliacao) throws Exception {
       AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();
       this.avaliacao = avaliacaoDAO.consultar(idAvaliacao);
	}
     
	/**
	 * Consultar avaliações feitas pelo viajante
	 * @access public
	 * @param Viajante viajante
	 * @return void
	 * @throws Exception
	 */
	public void consultar(Viajante viajante) throws Exception {
		AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();
		this.listaAvaliacao = avaliacaoDAO.consultar(viajante);
	}
	
	/**
	 * Consultar avaliações de uma empresa
	 * @access public
	 * @param Empresa empresa
	 * @return void
	 * @throws Exception
	 */
	public void consultar(Empresa empresa) throws Exception {
		AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();
		this.listaAvaliacao = avaliacaoDAO.consultar(empresa);
	}	
	
   /**
    * Insere um viagem
    * @access public
    * @param int idEmpresa
    * @param int idViajante
    * @param int avaliacao
    * @param String descricao
    * @return void
    * @throws Exception
    */
   public void incluir(int idEmpresa, int idViajante, int avaliacao, String descricao) throws Exception {
	   EmpresaDAO empresaDAO = new EmpresaDAO();
	   ViajanteDAO viajanteDAO = new ViajanteDAO();
	   this.avaliacao.setEmpresa(empresaDAO.consultar(idEmpresa));
	   this.avaliacao.setViajante(viajanteDAO.consultar(idViajante));
	   this.avaliacao.setAvaliacao(avaliacao);
	   this.avaliacao.setDescricao(descricao);
	   this.avaliacaoDAO.incluir(this.avaliacao);
   }

   /**
    * Altera uma avaliação
    * @access public
    * @param int idAvaliacao
    * @param int avaliacao
    * @param String descricao
    * @return void
    * @throws Exception
    */
   public void alterar(int idAvaliacao, int avaliacao, String descricao) throws Exception {
	   this.avaliacao = this.avaliacaoDAO.consultar(idAvaliacao);
	   this.avaliacao.setAvaliacao(avaliacao);
	   this.avaliacao.setDescricao(descricao);
	   this.avaliacaoDAO.alterar(this.avaliacao);
   }

   /**
    * Exclui um viagem
    * @access public
    * @param int idAvaliacao
    * @return void
    * @throws Exception
    */
   public void excluir(int idAvaliacao) throws Exception {
	   this.avaliacao = this.avaliacaoDAO.consultar(idAvaliacao);
	   this.avaliacaoDAO.excluir(this.avaliacao);
   }	
	
}
