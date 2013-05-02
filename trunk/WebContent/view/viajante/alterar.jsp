<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
		<%@include file="menu.jsp" %>
		<%@include file="adicional.jsp"%>
        <jsp:useBean id="viajanteBean" class="br.com.les20131.model.bean.ViajanteBean" scope="request"/>
		<div id="corpo">
			<fieldset>
			<legend>Perfil</legend>
				<form id="cadastro_viajante" class="formulario_padrao" enctype="multipart/form-data" action="<c:url value="/Viajante"></c:url>" method="post" onsubmit="return validaFormulario(new Array('nome;String;1', 'dataNascimentoDia;int;1', 'dataNascimentoMes;int;1', 'dataNascimentoAno;int;1', 'sexo;String;1'))">
			        <div class="block">
				        <label for="imagemPrevia">Imagem:</label>
				        <div id="selecionaImagem" class="imagemPerfil" onclick="$('#imagem').click()">
					        <span id="novaImagem" class="sobrepoe"></span>
					        <img id="imagemPrevia" class="imagemPerfil" src="<c:url value="/Imagem?id=${viajanteBean.viajante.idUsuario}"></c:url>"/>
						</div>
				        <br/>
				        <input id="imagem" type="file" name="imagem" onchange="trocaImagem($('#imagemPrevia'), $('#novaImagem'), $('#imagem'))"/>
				        <span id="imagemErro" class="atencao"></span>
					</div>
			        <div class="block">
				        <label for="nome">Nome<span class="atencao">*</span>:</label>
				        <input id="nome" type="text" name="nome" value="${viajanteBean.viajante.nome}" maxlength="100"/>
				        <span id="nomeErro" class="atencao"></span>
					</div>
			        <div class="block">
	                    <label for="dataNascimentoDia">Data de nascimento<span class="atencao">*</span>:</label>
	                    <select id="dataNascimentoDia" name="dataNascimentoDia">
	                    </select>
	                    <span id="dataNascimentoDiaErro" class="atencao"></span>
	                    <select id="dataNascimentoMes" name="dataNascimentoMes" onchange="populaDropDownDia($('#dataNascimentoDia'), $(this).val());">
	                    </select>
	                    <span id="dataNascimentoMesErro" class="atencao"></span>
	                    <select id="dataNascimentoAno" name="dataNascimentoAno">
	                    </select>
	                    <span id="dataNascimentoAnoErro" class="atencao"></span>
						<fmt:formatDate type="date" pattern="yyyy-MM-dd" value="${viajanteBean.viajante.dataNascimento}" var="dataNascimento"/>	                    
	                    <input id="dataNascimento" type="hidden" name="dataNascimento" value="${dataNascimento}"/>
					</div>
			        <div class="block">
	                    <label>Sexo<span class="atencao">*</span>:</label>
                    	<input id="sexoM" type="radio" name="sexo" value="M" ${viajanteBean.viajante.sexo == 'M' ? 'checked' : ''}/><label class="label_radio" for="sexoM">Masculino</label>
                    	<input id="sexoF" type="radio" name="sexo" value="F" ${viajanteBean.viajante.sexo == 'F' ? 'checked' : ''}/><label class="label_radio" for="sexoF">Feminino</label>
	                    <span id="sexoErro" class="atencao"></span>
					</div>
			        <div class="block">
			        	<button type="submit" name="acao" value="alterar" >Alterar</button>
			        </div>
				</form>
			</fieldset>
			<script type="text/javascript">
				$(document).ready(function() { 
					populaDropDownAno($('#dataNascimentoAno'));
					populaDropDownMes($('#dataNascimentoMes')); 
					populaDropDownDia($('#dataNascimentoDia'), $('#dataNascimentoMes').val());
					$('#dataNascimentoAno').val($('#dataNascimento').val().split("-")[0]);
					$('#dataNascimentoMes').val($('#dataNascimento').val().split("-")[1]);
					$('#dataNascimentoDia').val($('#dataNascimento').val().split("-")[2]);
				});
			</script>			
		</div>
	</body>
</html>