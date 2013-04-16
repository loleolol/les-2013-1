<fieldset>
<legend>Registro de viagem</legend>
	<form id="cadastro_viagem" class="formulario_padrao" action="<c:url value="/ViagemController"></c:url>" method="post" onsubmit="return validaFormulario(new Array('titulo;String;1', 'descricao;String;1', 'dataInicialDia;int;0', 'dataInicialMes;int;0', 'dataInicialAno;int;0', 'dataFinalDia;int;0', 'dataFinalMes;int;0', 'dataFinalAno;int;0'))">
        <div class="block">
	        <label for="titulo">T�tulo<span class="atencao">*</span>:</label>
	        <input id="titulo" type="text" name="titulo" value="" maxlength="100"/>
	        <span id="tituloErro"></span>
		</div>
        <div class="block">
	        <label for="descricao">Descricao<span class="atencao">*</span>:</label>
	        <textarea id="descricao" name="descricao" rows="8" cols="60"></textarea>
	        <span id="descricaoErro"></span>
		</div>
        <div class="block">
        	<label for="dataInicialDia">Per�odo da viagem:</label>
        	<br/>
			<label for="dataInicialDia">De<span class="atencao">*</span>:</label>
			<select id="dataInicialDia" name="dataInicialDia">
			</select>
			<span id="dataInicialDiaErro"></span>
			<select id="dataInicialMes" name="dataInicialMes" onchange="populaDropDownDia($('#dataInicialDia'), $(this).val());">
			</select>
			<span id="dataInicialMesErro"></span>
			<select id="dataInicialAno" name="dataInicialAno">
			</select>
			<span id="dataInicialAnoErro"></span>
			
	        <label for="dataFinalDia">�</label>
			<select id="dataFinalDia" name="dataFinalDia">
			</select>
			<span id="dataFinalDiaErro"></span>
			<select id="dataFinalMes" name="dataFinalMes" onchange="populaDropDownDia($('#dataFinalDia'), $(this).val());">
			</select>
			<span id="dataFinalMesErro"></span>
			<select id="dataFinalAno" name="dataFinalAno">
			</select>
			<span id="dataFinalAnoErro"></span>
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

