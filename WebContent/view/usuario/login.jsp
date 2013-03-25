<%-- 
    Document   : login
    Created on : Jun 18, 2011, 8:18:25 PM
    Author     : 200920183
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%--
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Login</title>
        <link type="text/css" rel="stylesheet" href="/TrabalhoG1/view/publico/css/estilo.css"/>
        <script type="text/javascript" src="/TrabalhoG1/view/publico/js/trabalho.js" charset="UTF-8"></script>
    </head>
    <body onload="mostraMensagem('${mensagemBean.mensagem}')">
        <h1>Locadora de Veículos</h1>
        <form id="login" class="formulario_padrao" action="<c:url value="/LoginController"></c:url>" method="post" onsubmit="return validaFormulario(new Array('usuario;String;1', 'senha;String;1'))">
            <fieldset>
                <legend>Login</legend>
                <jsp:useBean id="mensagemBean" class="br.com.partiu.model.bean.MensagemBean" scope="request"/>
                <div class="block">
                    <label for="usuario">Usuário<span class="atencao">*</span>:</label>
                    <input id="usuario" type="text" name="usuario" value="" maxlength="50"/>
                    <span id="usuarioErro"></span>
                </div>
                <div class="block">
                    <label for="senha">Senha<span class="atencao">*</span>:</label>
                    <input id="senha" type="password" name="senha" value="" maxlength="50"/>
                    <span id="senhaErro"></span>
                </div>
                <div class="buttons">
                    <input type="submit" name="acao" value="Login"/>
                </div>
            </fieldset>
        </form>
    </body>
</html>
