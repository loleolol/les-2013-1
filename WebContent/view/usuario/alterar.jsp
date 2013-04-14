<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Login</title>
        <link type="text/css" rel="stylesheet" href="/les20131/view/publico/css/estilo.css"/>
        <script type="text/javascript" src="/les20131/view/publico/js/trabalho.js" charset="ISO-8859-1"></script>
    </head>
	<body class="perfil" onload="mostraMensagem('${mensagemBean.mensagem}')"> 
		<%@include file="../usuario/login.jsp"%>
		<%@include file="../viajante/menu.jsp"%>
		<%@include file="../viajante/adicional.jsp"%>
		<div id="corpo">
			<fieldset>
			<legend>Conta</legend>
				<form id="cadastro_usuario" class="formulario_padrao" action="<c:url value="/UsuarioController"></c:url>" method="post" onsubmit="return validaFormulario(new Array('email;String;1', 'confirmaEmail;String;1', 'senha;String;0', 'confirmaSenha;String;0'))">
			        <div class="block">
				        <label for="email">E-mail<span class="atencao">*</span>:</label>
				        <input id="email" type="text" name="email" value="${usuarioBean.usuario.email}" maxlength="100"/>
				        <span id="emailErro"></span>
					</div>
			        <div class="block">
				        <label for="confirmaEmail">Confirme E-mail<span class="atencao">*</span>:</label>
				        <input id="confirmaEmail" type="text" name="confirmaEmail" value="${usuarioBean.usuario.email}" maxlength="100"/>
				        <span id="confirmaEmailErro"></span>
					</div>
			        <div class="block">
				        <label for="senha">Senha:</label>
				        <input id="senha" type="password" name="senha" value="" maxlength="50"/>
				        <span id="senhaErro"></span>
					</div>
			        <div class="block">
				        <label for="confirmaSenha">Confirme Senha:</label>
				        <input id="confirmaSenha" type="password" name="confirmaSenha" value="" maxlength="50"/>
				        <span id="confirmaSenhaErro"></span>
					</div>			
			        <div class="block">
						<input type="submit" name="acao" value="Alterar"/>
			        </div>
				</form>
			</fieldset>
		</div>
	</body>
</html>