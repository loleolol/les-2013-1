<h1>Sistema</h1>
<form id="login" class="formulario_padrao" action="<c:url value="/UsuarioController"></c:url>" method="post" onsubmit="return validaFormulario(new Array('usuario;String;1', 'senha;String;1'))">
	<fieldset>
    	<legend>Acesso</legend>
    	<%-- <jsp:useBean id="mensagemBean" class="br.com.les20131.model.bean.MensagemBean" scope="request"/>--%>
        <div class="block">
			<input type="submit" name="acao" value="Cadastrar-se"/>
            <label for="usuario">Usuário:</label>
            <input id="usuario" type="text" name="usuario" value="" maxlength="50"/>
            <span id="usuarioErro"></span>
            <label for="senha">Senha:</label>
            <input id="senha" type="password" name="senha" value="" maxlength="50"/>
            <span id="senhaErro"></span>
            <input type="submit" name="acao" value="Login"/>
        </div>
    </fieldset>
</form>
