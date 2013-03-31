package br.com.les20131.util.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Fabrica de conex�es com banco de dados
 * @author 200920183
 */
public class FabricaConexao {

    /**
     * Armazena a conex�o est�tica com o banco de dados
     * @access private static
     * @var Connection
     */
    private static Connection conexao = null;

    /**
     * Armazena os par�metros da conex�o com o banco de dados
     * @access private static
     * @var ParametroConexao
     */
    private static ParametroConexao parametroConexao;

    /**
     * Abre uma conex�o est�tica com o banco de dados
     * @access private static
     * @return void
     */
    private static void abrirConexao() throws Exception {
        try {
            FabricaConexao.parametroConexao = new ParametroConexao();
            Class.forName("com.mysql.jdbc.Driver");
            FabricaConexao.conexao = DriverManager.getConnection(
                        FabricaConexao.parametroConexao.retornaUrlConexao()
                        , parametroConexao.getUsuario()
                        , parametroConexao.getSenha());
        } catch (ClassNotFoundException excecao) {
            throw new ClassNotFoundException("Problema na carga de driver com o banco de dados!");
        } catch (SQLException excecao) {
            throw new SQLException("Problema nos par�metros para acesso ao banco de dados!");
        }
    }

    /**
     * Retorna uma conex�o com o banco de dados
     * @access public static
     * @return Connection
     */
    public static Connection getObjConexao() throws Exception {
        if (!(FabricaConexao.conexao == null)) {
            if (!FabricaConexao.conexao.isClosed()) {
                return FabricaConexao.conexao;
            }
        }
        FabricaConexao.abrirConexao();
        return FabricaConexao.conexao;
    }

    /**
     * Fecha a conex�o est�tica com o banco de dados
     * @access public static
     * @return void
     */
    public static void fecharConexao() throws Exception {
        FabricaConexao.conexao.close();
    }

    /**
     * Retorna os par�metros de conex�o
     * @access public static
     * @return ParametroConexao
     */
    public static ParametroConexao getParametroConexao() {
        return FabricaConexao.parametroConexao;
    }//public static ParametroConexao getParametroConexao()
}
