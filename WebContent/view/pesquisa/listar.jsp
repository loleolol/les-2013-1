<%--
    Document   : listar
    Created on : 21/05/2013, 20:02:15
    Author     : 200920183
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>#Partiu</title>
        <link type="text/css" rel="stylesheet" href="/les20131/view/publico/css/estilo.css"/>
        <link type="text/css" rel="stylesheet" href="/les20131/view/publico/css/jquery-ui.css"/>
        <script type="text/javascript" src="/les20131/view/publico/js/jquery-1.9.1.min.js" charset="ISO-8859-1"></script>
        <script type="text/javascript" src="/les20131/view/publico/js/jquery-ui.js"></script>
        <script type="text/javascript" src="/les20131/view/publico/js/trabalho.js" charset="ISO-8859-1"></script>
    </head>
    <body class="perfil" onload="mostraMensagem('${mensagemBean.mensagem}')">
		<%@include file="../usuario/login.jsp"%>
		<%@include file="../menu.jsp" %>
		<%@include file="../adicional.jsp"%>
        <jsp:useBean id="pesquisaBean" class="br.com.les20131.model.bean.PesquisaBean" scope="request"/>
        <div id="corpo">
        	<div class="block">
        		<span>Pesquisa por "<c:out value="${pesquisaBean.criterio}"></c:out>"</span>
        	</div>
            <c:forEach items="${pesquisaBean.listaResultado}" var="itemResultado" varStatus="chave">
            	<div>
	               	<div class="container">
	                	<form id="resultado${chave.count}" 
							action="<c:url value="/${itemResultado.tipo}"></c:url>" method="post">
			    			<div id="itemRetornoPesquisaPrevia${chave.count}" class="item_retorno_pesquisa">
			    				<img id="imagemPreviaPesquisa${chave.count}" class="imagem_barra" 
			    					alt="${itemResultado.id}"/>
			    				<span class="texto_centro titulo">${itemResultado.identificacao}</span>
			    				<span class="texto_baixo">${itemResultado.previa}</span>
							</div>
							<input id="acao${chave.count}" type="hidden" name="acao" value=""/>
							<input type="hidden" name="id" value="${itemResultado.id}"/>
					    </form>
				 	</div>
				 </div>
            </c:forEach>
   			<script type="text/javascript">
				$(document).ready(function() {
					carregarImagemPerfis('imagemPreviaPesquisa', $('.item_retorno_pesquisa').length, 'resultado', 'carregarImagem');
				})
			</script>
		</div>			
    </body>
</html>
