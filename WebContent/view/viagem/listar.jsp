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
						<form id="viagem_${viagem.idViagem}" class="formulario_invisivel" 
							action="<c:url value="/Viagem"></c:url>" method="post">
							<textarea readonly rows=8 cols=55>${viagem.descricao}</textarea>
							<br/>
							<span>
								Realizada no período de ${fn:substring(viagem.dataInicial, 0, 10)} 
								à ${fn:substring(viagem.dataFinal, 0, 10)}
							</span>
							<span class="actions">
								<a class="edit" href="javascript:void(0)" 
									onclick="$('#acao_${viagem.idViagem}').val('Selecionar'); $('#viagem_${viagem.idViagem}').submit()">
								</a>
								<a class="delete" href="javascript:void(0)" 
									onclick="$('#acao_${viagem.idViagem}').val('Excluir'); confirmaExclusao($('#viagem_${viagem.idViagem}'))">
								</a>
							</span>
							<input id="acao_${viagem.idViagem}" type="hidden" name="acao" value=""/>
							<input type="hidden" name="idViagem" value="${viagem.idViagem}"/>
					    </form>
				    </fieldset>
	            </c:forEach>
            </fieldset>
		</div>			
    </body>
</html>
