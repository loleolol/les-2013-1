<%-- 
    Document   : menu
    Created on : Jun 21, 2011, 10:35:19 AM
    Author     : 200920183
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Menu</title>
        <link type="text/css" rel="stylesheet" href="/TrabalhoG1/view/publico/css/estilo.css"/>
    </head>
    <body>
        <h1>Locadora de Veículos</h1>
        <ul class="nav">
            <li class="first"><a href="<c:url value='/MarcaController'></c:url>" title="Marcas">Marcas</a></li>
            <li><a href="<c:url value='/ModeloController'></c:url>" title="Modelos">Modelos</a></li>
            <li><a href="<c:url value='/VeiculoController'></c:url>" title="Veículos">Veículos</a></li>
            <li><a href="<c:url value='/LocadorController'></c:url>" title="Locadores">Locadores</a></li>
            <li class="last"><a href="<c:url value='/LocacaoController'></c:url>" title="Locações">Locações</a></li>
            <li class="logoff"><a href="<c:url value='/LoginController'><c:param name='acao' value='logoff'/></c:url>" title="Sair">Sair</a></li>
        </ul>
    </body>
</html>
