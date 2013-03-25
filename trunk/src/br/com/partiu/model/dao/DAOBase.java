package br.com.partiu.model.dao;

import java.sql.Connection;

import br.com.partiu.util.bd.FabricaConexao;

/**
 *
 * @author 200920183
 */
public abstract class DAOBase<classe> implements DAOInterface<classe> {

    /**
     * Armazena uma conexão
     * @access private
     * @var Connection
     */
    protected Connection conexao;

    /**
     * Construtor da classe
     * @access public
     */
    public DAOBase() throws Exception {
        this.conexao = FabricaConexao.getObjConexao();
    }

}

