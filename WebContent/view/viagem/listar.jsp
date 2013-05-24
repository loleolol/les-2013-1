<%--
    Document   : listaLocacao
    Created on : 03/04/2013, 20:02:15
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
        <jsp:useBean id="viagemBean" class="br.com.les20131.model.bean.ViagemBean" scope="request"/>
        <div id="corpo">
        	<%@include file="viagem.jsp"%>
            <c:forEach items="${viagemBean.listaViagem}" var="viagem">
            	<div class="formulario_postagem">
	            	<div class="previa_perfil postador">
			    		<img id="imagemBarra" class="imagem_barra" src="<c:url value="/Viajante?acao=carregarImagem&id=${usuarioBean.usuario.idUsuario}"></c:url>"/>
			    		<span class="texto_centro">${usuarioBean.usuario.nome} compartilhou ${viagem.titulo}</span>               	
			    	</div>
	               	<div class="container">
	                	<a class="editar" href="javascript:void(0)" title="Editar"
							onclick="$('#acao${viagem.idViagem}').val('Selecionar'); $('#viagem${viagem.idViagem}').submit()">
						</a>
						<a class="remover" href="javascript:void(0)" title="Remover"
							onclick="$('#acao${viagem.idViagem}').val('Excluir'); confirmaExclusao($('#viagem${viagem.idViagem}'))">
						</a>
	                	<form id="viagem${viagem.idViagem}" 
							action="<c:url value="/Viagem"></c:url>" method="post">
							<c:if test="${fn:length(viagem.imagemViagem) > 0}">
								<div class="block">
									<div id="galeria${viagem.idViagem}" class="galeria">
										<img id="imagem${viagem.idViagem}" class="imagem"/>
									</div>
								</div>
							</c:if>
		 					<c:forEach items="${viagem.imagemViagem}" var="imagemViagem" varStatus="chave">
		 						<input id="imagemUrl${viagem.idViagem}${chave.count}" name="imagemUrl${viagem.idViagem}" type="hidden" value="<c:url value="/Viagem?acao=carregarImagem&id=${imagemViagem.idImagemViagem}"></c:url>"/>
							</c:forEach>
							<div class="block texto">${viagem.descricao}</div>
							<input id="acao${viagem.idViagem}" type="hidden" name="acao" value=""/>
							<input type="hidden" name="idViagem" value="${viagem.idViagem}"/>
					    </form>
				 	</div>
				 </div>
            </c:forEach>
			<script type="text/javascript">
				$(document).ready(function() { 
					carregarGaleria('idViagem', 'imagem', 'imagemUrl', 'viagem');
				});
			</script>			
		</div>
		<%@include file="../adicional.jsp"%>		
    </body>
</html>
