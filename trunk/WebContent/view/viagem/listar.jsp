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
        <script type="text/javascript" src="/les20131/view/publico/js/trabalho.js" charset="ISO-8859-1"></script>
        <script type="text/javascript" src="/les20131/view/publico/js/jquery-1.9.1.min.js" charset="ISO-8859-1"></script>
        <script type="text/javascript" src="/les20131/view/publico/js/jquery-ui.js"></script>
    </head>
    <body class="perfil" onload="mostraMensagem('${mensagemBean.mensagem}')">
		<%@include file="../usuario/login.jsp"%>
		<%@include file="../viajante/menu.jsp" %>
		<%@include file="../viajante/adicional.jsp"%>
        <jsp:useBean id="ViagemBean" class="br.com.les20131.model.bean.ViagemBean" scope="request"/>
        <div class="corpo">
            <fieldset>
                <legend>Minhas viagens</legend>
                <form id="lista_viagem" class="formulario_padrao" action="<c:url value="/Viagem"></c:url>" method="post">
	                <button type="submit" name="acao" value="">Registrar viagem</button>
	            </form>
	            <c:forEach items="${viagemBean.listaViagem}" var="viagem">
		            <fieldset>
	                <legend>${viagem.titulo}</legend>
	                	<div class="container">
		                	<a class="editar" href="javascript:void(0)" 
								onclick="$('#acao${viagem.idViagem}').val('Selecionar'); $('#viagem${viagem.idViagem}').submit()">
							</a>
							<a class="remover" href="javascript:void(0)" 
								onclick="$('#acao${viagem.idViagem}').val('Excluir'); confirmaExclusao($('#viagem${viagem.idViagem}'))">
							</a>
							<div class="galeria">
								<img id="imagem" src/>
							</div>
		                	<form id="viagem${viagem.idViagem}" class="formulario_padrao" 
								action="<c:url value="/Viagem"></c:url>" method="post">
			 					<c:forEach items="${imagemViagemBean.listaImagemViagem}" var="imagemViagem" varStatus="chave">
			 						<input id="imagemUrl${chave.count}" type="hidden" value="+"<c:url value="/Viagem?acao=carregarImagem&id=${imagemViagem.idImagemViagem}"></c:url>/>
								</c:forEach>
								<textarea readonly rows=8 cols=55>${viagem.descricao}</textarea>
								<br/>
								<span>
									Realizada no período de ${fn:substring(viagem.dataInicial, 0, 10)} 
									à ${fn:substring(viagem.dataFinal, 0, 10)}
								</span>
								<input id="acao${viagem.idViagem}" type="hidden" name="acao" value=""/>
								<input type="hidden" name="idViagem" value="${viagem.idViagem}"/>
						        <input id="quantidadeImagem${viagem.idViagem}" type="hidden" name="quantidadeImagem" value="${fn:length(imagemViagemBean.listaImagemViagem)}"/>
						    </form>
						 </div>
				    </fieldset>
	            </c:forEach>
            </fieldset>
			<script type="text/javascript">
				$(document).ready(function() { 
					$('#imagem').attr("src", $('imagemUrl1').val());
				});
			</script>			
		</div>			
    </body>
</html>
