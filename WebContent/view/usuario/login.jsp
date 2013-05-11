<div class="barra">
	<span class="logo">
		#Partiu
	</span>
	<span class="barra_intervalo">
	</span>
	<span id="barra_login" class="barra_login">
		<form id="login" class="formulario_padrao" action="<c:url value="/Login"></c:url>" method="post" onsubmit="return validaFormulario(new Array('loginEmail;String;1', 'loginSenha;String;1'))">
			<jsp:useBean id="mensagemBean" class="br.com.les20131.model.bean.MensagemBean" scope="request"/>
			<jsp:useBean id="usuarioBean" class="br.com.les20131.model.bean.UsuarioBean" scope="request"/>
			<c:choose>
				<c:when test="${usuarioBean.usuario == null}">
					<label for="loginEmail">E-mail:</label>
					<input id="loginEmail" type="text" name="loginEmail" value="" maxlength="50"/>
					<span id="loginEmailErro"></span>
					<label for="loginSenha">Senha:</label>
					<input id="loginSenha" type="password" name="loginSenha" value="" maxlength="50"/>
					<span id="loginSenhaErro"></span>
					<button type="submit" name="acao" value="login">Login</button>
					<button type="button" onclick="enviarSemValidacao($('#login'))">Cadastre-se</button>
					<input type="hidden" name="acao" value="novo"/>
			    </c:when>
			    <c:otherwise>
			    	<img id="imagemBarra" class="imagem_barra" src="<c:url value="/Viajante?acao=carregarImagem&id=${usuarioBean.usuario.idUsuario}"></c:url>"/>
			    	<label>${usuarioBean.usuario.nome}</label>
					<button type="button" onclick="enviarSemValidacao($('#login'))">Logoff</button>
					<input type="hidden" name="acao" value="logoff"/>
			    </c:otherwise>
			</c:choose>
		</form>
	</span>
</div>
<br/>
<br/>