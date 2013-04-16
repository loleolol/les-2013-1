<fieldset>
<legend>Registro de viagem</legend>
	<form id="cadastro_viagem" class="formulario_padrao" action="<c:url value="/ViagemController"></c:url>" method="post" onsubmit="return validaFormulario(new Array('titulo;String;1', 'descricao;String;1', 'dataInicialDia;int;0', 'dataInicialMes;int;0', 'dataInicialAno;int;0', 'dataFinalDia;int;0', 'dataFinalMes;int;0', 'dataFinalAno;int;0'))">
        <div class="block">
	        <label for="titulo">Título<span class="atencao">*</span>:</label>
	        <input id="titulo" type="text" name="titulo" value="" maxlength="100"/>
	        <span id="tituloErro" class="atencao"></span>
		</div>
        <div class="block">
	        <label for="descricao">Descricao<span class="atencao">*</span>:</label>
	        <textarea id="descricao" name="descricao" rows="8" cols="60"></textarea>
	        <span id="descricaoErro" class="atencao"></span>
		</div>
        <div class="block">
        	<label for="dataInicialDia">Período da viagem:</label>
        	<br/>
			<label for="dataInicialDia">De<span class="atencao">*</span>:</label>
			<select id="dataInicialDia" name="dataInicialDia">
			</select>
			<span id="dataInicialDiaErro" class="atencao"></span>
			<select id="dataInicialMes" name="dataInicialMes" onchange="populaDropDownDia($('#dataInicialDia'), $(this).val());">
			</select>
			<span id="dataInicialMesErro" class="atencao"></span>
			<select id="dataInicialAno" name="dataInicialAno">
			</select>
			<span id="dataInicialAnoErro" class="atencao"></span>
			
	        <label for="dataFinalDia">à</label>
			<select id="dataFinalDia" name="dataFinalDia">
			</select>
			<span id="dataFinalDiaErro" class="atencao"></span>
			<select id="dataFinalMes" name="dataFinalMes" onchange="populaDropDownDia($('#dataFinalDia'), $(this).val());">
			</select>
			<span id="dataFinalMesErro" class="atencao"></span>
			<select id="dataFinalAno" name="dataFinalAno">
			</select>
			<span id="dataFinalAnoErro" class="atencao"></span>
		</div>
        <div class="block">
			<input type="submit" name="acao" value="Registrar"/>
        </div>
	</form>
</fieldset>
<script type="text/javascript">
	$(document).ready(function() { 
		populaDropDownAno($('#dataInicialAno'));
		populaDropDownMes($('#dataInicialMes')); 
		populaDropDownDia($('#dataInicialDia'), $('#dataInicialMes').val());
		populaDropDownAno($('#dataFinalAno'));
		populaDropDownMes($('#dataFinalMes'));
		populaDropDownDia($('#dataFinalDia'), $('#dataFinalMes').val());
	});
</script>			

