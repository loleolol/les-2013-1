<form id="cadastro_viagem" class="formulario_padrao" enctype="multipart/form-data" action="<c:url value="/Viagem"></c:url>" method="post" onsubmit="return validaFormulario(new Array('titulo;String;1', 'descricao;String;1', 'dataInicialDia;int;0', 'dataInicialMes;int;0', 'dataInicialAno;int;0', 'dataFinalDia;int;0', 'dataFinalMes;int;0', 'dataFinalAno;int;0'))">
       <div class="bloco">
        <label for="titulo">Título<span class="atencao">*</span>:</label>
        <input id="titulo" type="text" name="titulo" value="" maxlength="100"/>
        <span id="tituloErro" class="atencao"></span>
	</div>
       <div class="bloco galeria">
        <label for="previrImagem">Imagens:</label>
        <br/>
        <input id="quantidadeImagem" type="hidden" name="quantidadeImagem" value=""/>
        <div id="adicionaImagem" class="imagem adiciona_imagem" title="Adicionar imagem" onclick="adicionaCampoImagem($('#adicionaImagem'), $('#quantidadeImagem'), 'previrImagem', 'selecionaImagem', 'imagemPrevia', 'removerImagem', 'imagem')">
		</div>
	</div>
       <div class="bloco">
        <label for="descricao">Descricao<span class="atencao">*</span>:</label>
        <br/>
        <textarea id="descricao" name="descricao" rows="8" cols="56"></textarea>
        <span id="descricaoErro" class="atencao"></span>
	</div>
       <div class="bloco">
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
		$('#quantidadeImagem').val($('.adiciona_imagem').length-1);
	});
</script>
