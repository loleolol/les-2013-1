**O Ambiente é composto por**
  * Visual Paradigm CE NoInstall 10.1.20130306
  * Eclipse Juno Service Release 2
    * Subclipse 1.8.1 (Plugin eclipse SVN)
  * XAMPP 1.8.1
    * MySQL 5.5.27 (Community Server)
    * Tomcat 7.0.30
  * jQuery 1.9.1
  * jstl 1.2
  * mysql-connector-java 5.1.24
  * jquery-ui-1.10.3.custom

Sistema desenvolvido em ambiente J2EE utilizando MVC2 Model 2.

<br></br>
### Visual Paradigm (para visualização dos diagramas) ###

  * O Visual Paradigm Community Edition (no install) pode ser baixado [neste link](http://www.visual-paradigm.com/download/vpuml.jsp?edition=ce)


<br></br>
### Pré-requisitos ###

  1. É necessário ter instalado a última versão do Java JDK.
  1. Para isso, acesse este [link](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html).
  1. Navegue até a seção **"Java SE Development Kit 7u17"**.
  1. Clique em **"Accept License Agreement"**.
  1. Faça o download da versão **"Windows x86"** e instale.

**O ambiente utilizado é 32 bits (sendo o Eclipse 32 bits, o JDK também deve ser 32 bits**

  * No caso do uso de um sistema de 64 bits, pode ser necessário fazer uma configuração adicional no Eclipse:
  1. Caso apareça uma mensagem de erro sobre o JDK na inicialização do Eclipse, após a instalação do JDK ter sido concluída, navegue até **"../ambiente/eclipse"**.
  1. Abra o arquivo **eclipse.ini**.
  1. Adicione as seguintes linhas **ANTES** da linha que contém **-vmargs**:

**-vm**<br></br> {caminho do jdk}/bin/javaw.exe

<br></br>
### Configuração do Eclipse ###

  1. Para ter o ambiente configurado, é necessário fazer o download das partes e extraí-las para uma pasta qualquer. Você pode baixar as pastas **"ambiente.zip.001-007"** na seção de downloads acessando a [página de downloads](https://code.google.com/p/les-2013-1/downloads/list).
  1. Após fazer o download, faça a extração da pasta **"ambiente"** para um diretório qualquer.
  1. Acesse o caminho **"../ambiente/eclipse"** e abra o programa através do executável **eclipse**.
  1. Na abertura do programa, aponte para o Workspace previamente definido em **"../ambiente/eclipse/workspace"**.

<br></br>
### Configuração do Xampp ###

  1. Acesse o diretório **"../ambiente/xampp"**.
  1. Execute o arquivo **setup\_xampp** e aguarde a conclusão da execução do mesmo.
  1. O controle da execução do servidor e do bando de dados pode ser feito através da execução do **xampp\_control**.

<br></br>
### Para fazer checkout do código ###

  1. No Eclipse, clique com o botão direito sobre o projeto.
  1. Clique em **Team > Update do HEAD**.
<br></br>
### Para fazer commit do código ###

  1. No Eclipse, clique com o botão direito sobre o projeto.
  1. Clique em **Team > Commit...**.
  1. Insira comentários sobre o commit e clique em **OK**.
  1. Insira seu nome de usuário Google e a senha gerada através [deste link](https://code.google.com/hosting/settings) e clique em **OK** para commitar.