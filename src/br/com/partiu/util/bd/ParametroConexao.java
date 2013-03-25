package br.com.partiu.util.bd;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * Define os par�metros da conex�o
 * @author 200920183
 */
public class ParametroConexao {

        /**
         * Armazena o usu�rio
         * @access private
         * @var String
         */
	private String usuario;

        /**
         * Armazena a senha do usu�rio
         * @access private
         * @var String
         */
        private String senha;
	
        /**
         * Armazena o banco de dados
         * @access private
         * @var String
         */
        private String bancoDados;

        /**
         * Armazena o servidor
         * @access private
         * @var String
         */
        private String servidor;

        /**
         * Armazena a porta de conex�o
         * @access private
         * @var int
         */
        private int porta;

        /**
         * Armazena o par�metro de conex�o
         * @access private
         * @var int
         */
        private int paginacao;

        /**
         * Construtor da classe
         * @access public
         */
	public ParametroConexao() throws Exception {
            DocumentBuilder docBuilder;
            Document documentoXML;

            try {
                    docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                    documentoXML = docBuilder.parse(this.getClass().getResourceAsStream("database.xml"));
                    documentoXML.getDocumentElement().normalize();

                    /**
                     * Carrega as configura��es para conex�o com o banco de dados
                     */
                    this.bancoDados = documentoXML.getElementsByTagName("database").item(0).getTextContent();
                    this.usuario = documentoXML.getElementsByTagName("user").item(0).getTextContent();
                    this.senha = documentoXML.getElementsByTagName("password").item(0).getTextContent();
                    this.servidor = documentoXML.getElementsByTagName("server").item(0).getTextContent();
                    this.porta = Integer.parseInt(documentoXML.getElementsByTagName("port").item(0).getTextContent());
                    this.paginacao = Integer.parseInt(documentoXML.getElementsByTagName("pagination").item(0).getTextContent());

            } catch (ParserConfigurationException excecao) {
                throw new ParserConfigurationException("Problema no conversor do arquivo de configura��o com o banco de dados!");
            } catch (SAXException excecao) {
                throw new SAXException("Problema na convers�o do arquivo de configura��o com o banco de dados!");
            } catch (IOException excecao) {
                throw new IOException("Problema na leitura do arquivo de configura��o com o banco de dados!");
            }
	}

        /**
         * Retorna o usu�rio
         * @access public
         * @return String
         */
	public String getUsuario() {
            return this.usuario;
	}//public String getUsuario()

        /**
         * Retorna a senha
         * @access public
         * @return String
         */
	public String getSenha() {
            return this.senha;
	}//public String getSenha()

        /**
         * Retorna o banco de dados
         * @access public
         * @return String
         */
	public String getBancoDados() {
            return this.bancoDados;
	}//public String getBancoDados()

        /**
         * Retorna o servidor
         * @access public
         * @return String
         */
	public String getServidor() {
            return this.servidor;
	}//public String getServidor()

        /**
         * Retorna a porta
         * @access public
         * @return integer
         */
	public int getPorta() {
            return this.porta;
	}//public int getPorta()

        /**
         * Retorna a pagina��o
         * @access public
         * @return integer
         */
	public int getPaginacao() {
            return this.paginacao;
	}//public int getPaginacao()

        /**
         * Retorna a string de conex�o com banco de dados
         * @access public
         * @return String
         */
        public String retornaUrlConexao() {
            return "jdbc:" + this.getServidor()
                    + ":" + this.getPorta()
                    + "/" + this.getBancoDados();
        }//public String retornaUrlConexao()

}//public class ParametroConexao

