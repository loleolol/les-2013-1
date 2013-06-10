<div class="barra">
	<span class="logo" onclick="$('#logo').submit()">
	<c:choose>
		<c:when test="${empresaBean == null && usuarioBean.usuario != null && administradorBean == null}">
			<form id="logo" class="formulario_invisivel" action="<c:url value="/Viajante"></c:url>" method="post">
		    </form>
		</c:when>
		<c:when test="${empresaBean != null && usuarioBean.usuario != null && administradorBean == null}">
			<form id="logo" class="formulario_invisivel" action="<c:url value="/Empresa"></c:url>" method="post">
		    </form>
		</c:when>
		<c:otherwise>
			<form id="logo" class="formulario_invisivel" action="" onsubmit="return false" method="post">
		    </form>
		</c:otherwise>
	</c:choose>
	</span>
	<span class="barra_intervalo">
		<c:choose>
			<c:when test="${usuarioBean.usuario != null}">
				<form id="formularioPesquisa" class="formulario_padrao" action="<c:url value="/Pesquisa"></c:url>" method="post" onsubmit="return validaFormulario(new Array('criterio;String;1;'))">
					<c:choose>
						<c:when test="${administradorBean == null}">
							<input id="criterio" type="text" name="criterio" size="50"
								onkeyup="pesquisar(this, $('#retornoPesquisa'), $('#pesquisa'))" />
						</c:when>
						<c:otherwise>
							<input id="criterio" type="text" name="criterio" size="50" />
						</c:otherwise>
					</c:choose>
					<span id="criterioErro"></span>
					<button id="pesquisa" type="submit" name="acao" value="pesquisar">
						<span class="pesquisar"></span>
					</button>
	    			<div id="retornoPesquisa" class="retorno_pesquisa invisivel">
	    			</div>
	    		</form>
    		</c:when>
    	</c:choose>
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
					<input type="hidden" name="acao" value="novo"/>
			    </c:when>
			    <c:otherwise>
					<button type="button" onclick="enviarSemValidacao($('#login'))">Logoff</button>
					<input type="hidden" name="acao" value="logoff"/>
			    </c:otherwise>
			</c:choose>
		</form>
	</span>
</div>
<br/>
<br/>