<%--
    Document   : erro
    Created on : 05/05/2011, 20:02:15
    Author     : 200920183
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Erro</title>
        <link type="text/css" rel="stylesheet" href="/TrabalhoG1/view/publico/css/estilo.css"/>
    </head>
    <body>
        <div>
            <fieldset>
                <legend>Erro</legend>
                <jsp:useBean id="mensagemBean" class="br.com.les20131.model.bean.MensagemBean" scope="request"/>
                <div class="block">
                    <label>Erro ocorrido: </label>
                    <div>${mensagemBean.mensagem}</div>
                </div>
            </fieldset>
        </div>
    </body>
</html>
