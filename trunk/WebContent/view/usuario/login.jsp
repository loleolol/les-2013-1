<div class="barra">
	<div class="logo">
		<h1>Partiu</h1>
	</div>
	<div id="barra_login">
		<form id="login" class="formulario_login" action="<c:url value="/UsuarioController"></c:url>" method="post">
			<jsp:useBean id="mensagemBean" class="br.com.les20131.model.bean.MensagemBean" scope="request"/>
			<jsp:useBean id="usuarioBean" class="br.com.les20131.model.bean.UsuarioBean" scope="request"/>
			<div>
				<c:choose>
					<c:when test="${usuarioBean.usuario == null}">
						<label for="loginEmail">E-mail:</label>
						<input id="loginEmail" type="text" name="loginEmail" value="" maxlength="50"/>
						<span id="loginEmailErro"></span>
						<label for="loginSenha">Senha:</label>
						<input id="loginSenha" type="password" name="loginSenha" value="" maxlength="50"/>
						<span id="loginSenhaErro"></span>
						<input type="submit" name="acao" value="Login"/>
						<input type="button" name="cadastro" value="Cadastre-se" onclick="$('#login_cadastro').submit()"/>
						<script type="text/javascript">
							$('#login').submit(function () { return validaFormulario(new Array('loginEmail;String;1', 'loginSenha;String;1'))});
						</script>
				    </c:when>
				    <c:otherwise>
				    	<label>Logado como: ${usuarioBean.usuario.email}</label>
						<input type="submit" name="acao" value="Logoff"/>
						<script type="text/javascript">
							$('#login').submit(function () { });
						</script>
				    </c:otherwise>
				</c:choose>
			</div>
		</form>
		<form id="login_cadastro" class="formulario_login" action="<c:url value="/UsuarioController"></c:url>" method="post">
			<input type="hidden" name="acao" value="Cadastre-se"/>
		</form>	
	</div>
</div>