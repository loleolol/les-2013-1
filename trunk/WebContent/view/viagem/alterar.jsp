<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Login</title>
        <link type="text/css" rel="stylesheet" href="/les20131/view/publico/css/estilo.css"/>
        <script type="text/javascript" src="/les20131/view/publico/js/trabalho.js" charset="ISO-8859-1"></script>
        <script type="text/javascript" src="/les20131/view/publico/js/jquery-1.9.1.min.js" charset="ISO-8859-1"></script>
        
    </head>
	<body class="perfil" onload="mostraMensagem('${mensagemBean.mensagem}')"> 
		<%@include file="../usuario/login.jsp"%>
		<%@include file="../viajante/menu.jsp"%>
		<%@include file="../viajante/adicional.jsp"%>
        <jsp:useBean id="viagemBean" class="br.com.les20131.model.bean.ViagemBean" scope="request"/>
		<div id="corpo">
			<fieldset>
			<legend>Registro de viagem</legend>
				<form id="cadastro_viagem" class="formulario_padrao" action="<c:url value="/ModuloViagemController"></c:url>" method="post" onsubmit="return validaFormulario(new Array('titulo;String;1', 'descricao;String;1', 'dataInicialDia;int;0', 'dataInicialMes;int;0', 'dataInicialAno;int;0', 'dataFinalDia;int;0', 'dataFinalMes;int;0', 'dataFinalAno;int;0'))">
			        <div class=block>
				        <label for="imagemPrevia">Imagem:</label>
				        <div id="selecionaImagem" class="imagemPerfil" onclick="$('#imagem').click()">
				        <span id="novaImagem" class="sobrepoe"></span>
				        <img id="imagemPrevia" src="<c:url value="/ImagemController?id=${viajanteBean.viajante.idUsuario}"></c:url>"/>
						</div>
				        <br/>
				        <input id="imagem" type="file" name="imagem" onchange="trocaImagem($('#imagemPrevia'), $('#novaImagem'), $('#imagem'))"/>
				        <span id="imagemErro" class="atencao"></span>
					</div>
			        <div class="block">
				        <label for="titulo">Título<span class="atencao">*</span>:</label>
				        <input id="titulo" type="text" name="titulo" value="${viagemBean.viagem.titulo}" maxlength="100"/>
				        <span id="tituloErro" class="atencao"></span>
					</div>
			        <div class="block">
				        <label for="descricao">Descricao<span class="atencao">*</span>:</label>
				        <textarea id="descricao" name="descricao" rows="8" cols="60"><c:out value="${viagemBean.viagem.descricao}"></c:out></textarea>
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
						<fmt:formatDate type="date" pattern="yyyy-MM-dd" value="${viagemBean.viagem.dataInicial}" var="dataInicial"/>	                    
	                    <input id="dataInicial" type="hidden" name="dataInicial" value="${dataInicial}"/>
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
						<fmt:formatDate type="date" pattern="yyyy-MM-dd" value="${viagemBean.viagem.dataFinal}" var="dataFinal"/>	                    
	                    <input id="dataFinal" type="hidden" name="dataFinal" value="${dataFinal}"/>
						
						<input id="idViagem" type="hidden" name="idViagem" value="${viagemBean.viagem.idViagem}"/>
					</div>
			        <div class="block">
			        	<button type="submit" name="acao" value="alterar">Alterar</button>
			        </div>
				</form>
			</fieldset>
			<script type="text/javascript">
				$(document).ready(function() { 
					populaDropDownAno($('#dataInicialAno'));
					populaDropDownMes($('#dataInicialMes')); 
					populaDropDownDia($('#dataInicialDia'), $('#dataInicialMes').val());
					$('#dataInicialAno').val($('#dataInicial').val().split("-")[0]);
					$('#dataInicialMes').val($('#dataInicial').val().split("-")[1]);
					$('#dataInicialDia').val($('#dataInicial').val().split("-")[2]);
					populaDropDownAno($('#dataFinalAno'));
					populaDropDownMes($('#dataFinalMes'));
					populaDropDownDia($('#dataFinalDia'), $('#dataFinalMes').val());
					$('#dataFinalAno').val($('#dataFinal').val().split("-")[0]);
					$('#dataFinalMes').val($('#dataFinal').val().split("-")[1]);
					$('#dataFinalDia').val($('#dataFinal').val().split("-")[2]);
				});
			</script>			
		</div>
	</body>
</html>
