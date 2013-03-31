package br.com.les20131.model.dao;

import java.util.List;


/**
 * InterfaceDAO
 * @author 200920183
 */
public interface DAOInterface<classe> {

    public void inserir(classe obj)throws DAOException;

    public void alterar(classe obj)throws DAOException;

    public void excluir(classe obj)throws DAOException;

    public classe consultar(int intId) throws DAOException;

    public List<classe> consultarTodos() throws DAOException;

}
