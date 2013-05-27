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
        <script type="text/javascript" src="/les20131/view/publico/js/jquery-1.9.1.min.js" charset="ISO-8859-1"></script>
        <script type="text/javascript" src="/les20131/view/publico/js/trabalho.js" charset="ISO-8859-1"></script>
    </head>
    <body class="perfil" onload="mostraMensagem('${mensagemBean.mensagem}')">
		<%@include file="../usuario/login.jsp"%>
		<%@include file="../menu.jsp" %>
        <jsp:useBean id="pesquisaBean" class="br.com.les20131.model.bean.PesquisaBean" scope="request"/>
        <div id="corpo">
        	<c:choose>
	        	<c:when test="${pesquisaBean.criterio != null}">
		        	<div class="bloco barra_central">
			        	<span>Pesquisa por "<c:out value="${pesquisaBean.criterio}"></c:out>"</span>
		        	</div>
	        	</c:when>
	        	<c:otherwise>
	        		<%@include file="../viagem/viagem.jsp" %>
	        	</c:otherwise>
	        </c:choose>
            <c:forEach items="${pesquisaBean.listaResultado}" var="itemResultado" varStatus="chave">
            	<div>
	               	<div class="container">
	                	<form id="resultado${chave.count}" 
							action="<c:url value="/${itemResultado.tipo}"></c:url>" method="post">
			    			<div id="itemRetornoPesquisaPrevia${chave.count}" class="item_retorno_pesquisa">
			    				<div class="parte_bloco" onclick="$('#resultado${chave.count}').submit()">
				    				<img id="imagemPreviaPesquisa${chave.count}" class="imagem_barra" 
				    					alt="${itemResultado.id}"/>
				    			</div>
				    			<div class="parte_bloco informacao" onclick="$('#resultado${chave.count}').submit()">
				    				<span class="titulo">${itemResultado.identificacao}</span>
				    				<br/>
				    				<span>${itemResultado.previa}</span>
				    				<br/>
				    			</div>
				    			<div class="parte_bloco">
				    				<c:set var="idUsuario">${usuarioBean.usuario.idUsuario}</c:set>
				    				<c:choose>
				    					<c:when test="${itemResultado.id == idUsuario}">
				    					</c:when>
				    					<c:when test="${itemResultado.flag}">
						    				<button type="button" onclick="removerContato($(this), ${itemResultado.id})">
						    					<span class="excluir"></span>
						    					<span>Contato</span>
						    				</button>
						    			</c:when>
						    			<c:otherwise>
						    				<button type="button" onclick="adicionarContato($(this), ${itemResultado.id})">
						    					<span class="incluir"></span>
						    					<span>Contato</span>
						    				</button>
						    			</c:otherwise>
						    		</c:choose>
				    			</div>
							</div>
							<input id="acao${chave.count}" type="hidden" name="acao" value="selecionar"/>
							<input type="hidden" name="id" value="${itemResultado.id}"/>
					    </form>
				 	</div>
				 </div>
            </c:forEach>
   			<script type="text/javascript">
				$(document).ready(function() {
					carregarImagemPerfis('imagemPreviaPesquisa', $('.item_retorno_pesquisa').length, 'resultado', 'carregarImagem');
				});
			</script>
		</div>
		<%@include file="../adicional.jsp"%>
    </body>
</html>
