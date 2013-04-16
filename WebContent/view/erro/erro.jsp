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
        <link type="text/css" rel="stylesheet" href="/les20131/view/publico/css/estilo.css"/>
        <script type="text/javascript" src="/les20131/view/publico/js/trabalho.js" charset="ISO-8859-1"></script>
        <script type="text/javascript" src="/les20131/view/publico/js/jquery-1.9.1.min.js" charset="ISO-8859-1"></script>
    </head>
    <body class="perfil">
		<%@include file="../usuario/login.jsp"%>
		<%@include file="../viajante/menu.jsp"%>
		<%@include file="../viajante/adicional.jsp"%>
        <div class="corpo">
            <fieldset>
                <legend>Erro</legend>
                <div class="block">
                    <label>Erro ocorrido: </label>
                    <div>${mensagemBean.mensagem}</div>
                </div>
            </fieldset>
        </div>
    </body>
</html>
