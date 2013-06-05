<form id="cadastro_anuncio" class="formulario_padrao" action="<c:url value="/Anuncio"></c:url>" method="post" onsubmit="return validaFormulario(new Array('anuncio;String;1', 'dataInicialDia;int;1', 'dataInicialMes;int;1', 'dataInicialAno;int;1', 'dataFinalDia;int;1', 'dataFinalMes;int;1', 'dataFinalAno;int;1'))">      
    <div class="bloco">
        <label for="anuncio">Anúncio<span class="atencao">*</span>:</label>
        <br/>
        <textarea id="anuncio" name="anuncio" rows="8" cols="70"></textarea>
        <span id="anuncioErro" class="atencao"></span>
	</div>
    <div class="bloco">
       	<label for="dataInicialDia">Período do anúncio:</label>
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
		<br/>
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
    <div class="bloco">
       	<button type="submit" name="acao" value="incluir">Registrar</button>
    </div>
</form>
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
