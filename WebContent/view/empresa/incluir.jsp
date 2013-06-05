<div id="abaEmpresa">
	<form id="cadastro_empresa" class="formulario_padrao"
		action="<c:url value="/Empresa"></c:url>" method="post"
		onsubmit="return (verificarValorIgualCampos('emailEmpresa') && verificarValorIgualCampos('senhaEmpresa') && validaFormulario(new Array('nomeEmpresa;String;1', 'emailEmpresa;String;1;validaEmail', 'emailEmpresaConfirma;String;1;validaEmail', 'senhaEmpresa;String;1', 'senhaEmpresaConfirma;String;1')))">
		<div class="bloco">
			<label for="nomeEmpresa">Nome<span class="atencao">*</span>:
			</label> <input id="nomeEmpresa" type="text" name="nomeEmpresa" value=""
				maxlength="100" /> <span id="nomeEmpresaErro" class="atencao"></span>
		</div>
		<div class="bloco">
			<label for="emailEmpresa">E-mail<span class="atencao">*</span>:
			</label> <input id="emailEmpresa" type="text" name="emailEmpresa" value=""
				maxlength="100" /> <span id="emailEmpresaErro" class="atencao"></span>
		</div>
		<div class="bloco">
			<label for="emailEmpresaConfirma">Confirme E-mail<span
				class="atencao">*</span>:
			</label> <input id="emailEmpresaConfirma" type="text"
				name="emailEmpresaConfirma" value="" maxlength="100" /> <span
				id="emailEmpresaConfirmaErro" class="atencao"></span>
		</div>
		<div class="bloco">
			<label for="senhaEmpresa">Senha<span class="atencao">*</span>:
			</label> <input id="senhaEmpresa" type="password" name="senhaEmpresa"
				value="" maxlength="50" /> <span id="senhaEmpresaErro"
				class="atencao"></span>
		</div>
		<div class="bloco">
			<label for="senhaEmpresaConfirma">Confirme Senha<span
				class="atencao">*</span>:
			</label> <input id="senhaEmpresaConfirma" type="password"
				name="senhaEmpresaConfirma" value="" maxlength="50" /> <span
				id="senhaEmpresaConfirmaErro" class="atencao"></span>
		</div>
		<div class="bloco">
			<button type="submit" name="acao" value="incluir">Cadastrar</button>
		</div>
	</form>
</div>