package br.com.les20131.model;

/**
 * Classe de viagem
 * @author 200920183
 */
public class Viagem {

	private int idViagem;
	
	private String descricao;

	public Viagem(String descricao) {
		this.descricao = descricao;
	}
	
	public Viagem(int idViagem, String descricao) {
		this.idViagem = idViagem;
		this.descricao = descricao;
	}

	public int getIdViagem() {
		return idViagem;
	}

	public void setIdViagem(int idViagem) {
		this.idViagem = idViagem;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	
	
	
}
