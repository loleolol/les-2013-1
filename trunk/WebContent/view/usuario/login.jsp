<div class="barra">
	<div class="logo">
		<h1>#Partiu</h1>
	</div>
	<div id="barra_login">
		<form id="login" class="formulario_login" action="<c:url value="/Login"></c:url>" method="post" onsubmit="return validaFormulario(new Array('loginEmail;String;1', 'loginSenha;String;1'))">
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
						<button type="submit" name="acao" value="login">Login</button>
						<button type="button" onclick="$('#cadastro').submit()">Cadastre-se</button>
				    </c:when>
				    <c:otherwise>
				    	<label>Logado como: ${usuarioBean.usuario.email}</label>
						<button type="submit" name="acao" value="logoff" onclick="enviarSemValidacao($('#login'))">Logoff</button>
				    </c:otherwise>
				</c:choose>
			</div>
		</form>
		<form id="cadastro" action="<c:url value="/Usuario"></c:url>" method="post">
			<input type="hidden" name="acao" value="novo"/>
		</form>
	</div>
</div>