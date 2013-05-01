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
        <script type="text/javascript" src="/les20131/view/publico/js/jquery-1.9.1.min.js" charset="ISO-8859-1"></script>
        
    </head>
	<body class="perfil" onload="mostraMensagem('${mensagemBean.mensagem}')"> 
		<%@include file="../usuario/login.jsp"%>
		<%@include file="../viajante/menu.jsp"%>
		<%@include file="../viajante/adicional.jsp"%>
		<div id="corpo">
			<fieldset>
			<legend>Conta</legend>
				<form id="cadastro_usuario" class="formulario_padrao" action="<c:url value="/Usuario"></c:url>"
				 method="post" onsubmit="return (verificarValorIgualCampos('email') && verificarValorIgualCampos('senha') && validaFormulario(new Array('email;String;1;validaEmail', 'emailConfirma;String;1;validaEmail', 'senha;String;0', 'senhaConfirma;String;0')))">
			        <div class="block">
				        <label for="email">E-mail<span class="atencao">*</span>:</label>
				        <input id="email" type="text" name="email" value="${usuarioBean.usuario.email}" maxlength="100"/>
				        <span id="emailErro" class="atencao"></span>
					</div>
			        <div class="block">
				        <label for="emailConfirma">Confirme E-mail<span class="atencao">*</span>:</label>
				        <input id="emailConfirma" type="text" name="emailConfirma" value="${usuarioBean.usuario.email}" maxlength="100"/>
				        <span id="emailConfirmaErro" class="atencao"></span>
					</div>
			        <div class="block">
				        <label for="senha">Senha:</label>
				        <input id="senha" type="password" name="senha" value="" maxlength="50"/>
				        <span id="senhaErro" class="atencao"></span>
					</div>
			        <div class="block">
				        <label for="senhaConfirma">Confirme Senha:</label>
				        <input id="senhaConfirma" type="password" name="senhaConfirma" value="" maxlength="50"/>
				        <span id="senhaConfirmaErro" class="atencao"></span>
					</div>			
			        <div class="block">
			           	<button type="submit" name="acao" value="alterar">Alterar</button>
			        </div>
				</form>
			</fieldset>
		</div>
	</body>
</html>