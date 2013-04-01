<h1>Rede social</h1>
<div class="barra">
	<form id="login" class="formulario_login" action="<c:url value="/UsuarioController"></c:url>" method="post" onsubmit="return validaFormulario(new Array('loginEmail;String;1', 'loginSenha;String;1'))">
		<jsp:useBean id="mensagemBean" class="br.com.les20131.model.bean.MensagemBean" scope="request"/>
		<div>
			<label for="loginEmail">E-mail:</label>
			<input id="loginEmail" type="text" name="loginEmail" value="" maxlength="50"/>
			<span id="loginEmailErro"></span>
			<label for="loginSenha">Senha:</label>
			<input id="loginSenha" type="password" name="loginSenha" value="" maxlength="50"/>
			<span id="loginSenhaErro"></span>
			<input type="submit" name="acao" value="Login"/>
		</div>
	</form>
	<form id="login_cadastro" class="formulario_login" action="<c:url value="/UsuarioController"></c:url>" method="post">
		<div>
			<input type="submit" name="acao" value="Cadastre-se"/>
		</div>
	</form>
</div>