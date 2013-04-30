<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
   

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Usuários</title>
        <link type="text/css" rel="stylesheet" href="/les20131/view/publico/css/estilo.css"/>
        <script type="text/javascript" src="/les20131/view/publico/js/trabalho.js" charset="ISO-8859-1"></script>
        <script type="text/javascript" src="/les20131/view/publico/js/jquery-1.9.1.min.js" charset="ISO-8859-1"></script>
	</head>
	<body class="perfil">
		<%@include file="../usuario/login.jsp"%>
		<%@include file="../admin/menu.jsp" %>
		<jsp:useBean id="UsuarioBean" class="br.com.les20131.model.bean.UsuarioBean" scope="request"/>
		<div class="corpo">
			<fieldset>
				<legend>Usuários</legend>
				<c:forEach items="${usuarioBean.listaUsuario}" var="usuario">
					<fieldset>
						<form id="usuario_${usuario.idUsuario}" class="formulario_invisivel" 
							action="<c:url value="/UsuarioController"></c:url>" method="post">
							<label>${usuario.nome}</label>
						</form>
					</fieldset>
				</c:forEach>				
			</fieldset>
		</div>
	</body>
</html>