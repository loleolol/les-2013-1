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
		<%@include file="menu.jsp" %>
		<jsp:useBean id="UsuarioBean" class="br.com.les20131.model.bean.UsuarioBean" scope="request"/>
		<div class="corpo">
			<fieldset>
				<legend>Usu�rios</legend>
				<form id="lista_usuarios" class="formulario_invisivel" 
							action="<c:url value="/AdminUsuario"></c:url>" method="post">
				<input id="quantidade" name="quantidade" type="hidden" value="${usuarioBeanLista.tamanhoListaUsuario}" />
				<table>
					<c:forEach items="${usuarioBeanLista.listaUsuario}" var="usuario">
						<tr>
							<td>																	
							<input name="usuario${usuario.idUsuario}" type="checkbox" id="usuario${usuario.idUsuario}" 
								value="${usuario.idUsuario}" />
							</td>	
							<td>${usuario.nome}</td>
							<td>${usuario.email}</td>
						</tr>
					</c:forEach>
				</table>
				<div class="block">
			        	<button type="submit" name="acao" value="bloquear" >Bloquear</button>
			        	<button type="submit" name="acao" value="excluir" >Excluir</button>
			    </div>
				</form>				
			</fieldset>
		</div>
	</body>
</html>