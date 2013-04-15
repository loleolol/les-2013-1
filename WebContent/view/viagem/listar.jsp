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
        <title>Locações</title>
        <link type="text/css" rel="stylesheet" href="/les20131/view/publico/css/estilo.css"/>
        <script type="text/javascript" src="/les20131/view/publico/js/trabalho.js" charset="ISO-8859-1"></script>
        <script type="text/javascript" src="/les20131/view/publico/js/jquery-1.9.1.min.js" charset="ISO-8859-1"></script>
    </head>
    <body class="perfil" onload="mostraMensagem('${mensagemBean.mensagem}')">
		<%@include file="../usuario/login.jsp"%>
		<%@include file="../viajante/menu.jsp" %>
		<%@include file="../viajante/adicional.jsp"%>
        <jsp:useBean id="ViagemBean" class="br.com.les20131.model.bean.ViagemBean" scope="request"/>
        <div class="corpo">
	        <form id="lista_viagem" class="formulario_padrao" action="<c:url value="/ViagemController"></c:url>" method="post">
	            <fieldset>
	                <legend>Minhas viagens</legend>
	                <button type="submit" name="acao" value="">Registrar viagem</button>
			        <table>
			            <tr>
			                <th>Descrição</th>
			                <th>Início</th>
			                <th>Fim</th>
			                <th>Ação</th>
			            </tr>
			            <c:forEach items="${viagemBean.listaViagem}" var="viagem">
			                <tr>
			                    <td><c:out value="${viagem.descricao}"/></td>
			                    <td><c:out value="${fn:substring(viagem.dataInicial, 0, 10)}"/></td>
			                    <td><c:out value="${fn:substring(viagem.dataFinal, 0, 10)}"/></td>
			                    <td class="actions">
									<a class="edit" href="javascript:void(0)" onclick="$('#editar_viagem_${viagem.idViagem}').submit()">
									</a>
									<a class="delete" href="javascript:void(0)" onclick="$('#excluir_viagem_${viagem.idViagem}').submit()">
									</a>
			                    </td>
			                </tr>
			            </c:forEach>
			        </table>
	            </fieldset>
	        </form>
			<c:forEach items="${viagemBean.listaViagem}" var="viagem">
				<form id="editar_viagem_${viagem.idViagem}" class="formulario_invisivel" 
				action="<c:url value="/ViagemController"></c:url>" method="post">
					<input type="hidden" name="acao" value="Selecionar"/>
					<input type="hidden" name="idViagem" value="${viagem.idViagem}"/>
			    </form>
				<form id="excluir_viagem_${viagem.idViagem}" class="formulario_invisivel" 
				action="<c:url value="/ViagemController"></c:url>" method="post" onsubmit="return confirmaExclusao();">
					<input type="hidden" name="acao" value="Excluir"/>
					<input type="hidden" name="idViagem" value="${viagem.idViagem}"/>
			    </form>
			</c:forEach>
		</div>			
    </body>
</html>
