<fieldset>
<legend>Registro de viagem</legend>
	<form id="cadastro_viagem" class="formulario_padrao" action="<c:url value="/ViagemController"></c:url>" method="post" onsubmit="return validaFormulario(new Array('descricao;String;1', 'dataInicial;Date;0', 'dataFinal;Date;0'))">
        <div class="block">
	        <label for="descricao">Descricao<span class="atencao">*</span>:</label>
	        <textarea id="descricao" name="descricao" rows="10" cols="80"></textarea>
	        <span id="descricaoErro"></span>
		</div>
        <div class="block">
        	<label for="dataInicial">Período da viagem:</label>
        	<br/>
	        <label for="dataInicial">De</label>
	        <input id="dataInicial" type="text" name="dataInicial" size="10" value="" maxlength="10"/>
	        <span>(YYYY-MM-DD)</span>
	        <span id="dataInicialErro"></span>
	        <label for="dataFinal">à</label>
	        <input id="dataFinal" type="text" name="dataFinal" size="10" value="" maxlength="10"/>
	        <span>(YYYY-MM-DD)</span>
	        <span id="dataFinalErro"></span>
		</div>
        <div class="block">
			<input type="submit" name="acao" value="Registrar"/>
        </div>
	</form>
</fieldset>
