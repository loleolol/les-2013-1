<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Login</title>
        <link type="text/css" rel="stylesheet" href="/les20131/view/publico/css/estilo.css"/>
        <script type="text/javascript" src="/les20131/view/publico/js/trabalho.js" charset="ISO-8859-1"></script>
    </head>
	<body onload="mostraMensagem('${mensagemBean.mensagem}')"> 
		<%@include file="../usuario/login.jsp"%>
		<%@include file="menu.jsp" %>
		<div id="corpo">
			<fieldset>
			<legend>Cadastre-se</legend>
				<form id="cadastro_usuario" class="formulario_padrao" action="<c:url value="/ViajanteController"></c:url>" method="post" onsubmit="return validaFormulario(new Array('nome;String;1', 'dataNascimento;Date;1', 'sexo;String;1'))">
			        <div class="block">
				        <label for="nome">Nome<span class="atencao">*</span>:</label>
				        <input id="nome" type="text" name="nome" value="{viajanteBean.viajante.nome}" maxlength="100"/>
				        <span id="nomeErro"></span>
					</div>
			        <div class="block">
	                    <label for="dataNascimento">Data de nascimento<span class="atencao">*</span>:</label>
	                    <input id="dataNascimento" type="text" name="dataNascimento" size="10" value="{viajanteBean.viajante.dataNascimento}" maxlength="10"/>
	                    <span>(YYYY-MM-DD)</span>
	                    <span id="dataNascimentoErro"></span>
					</div>
			        <div class="block">
	                    <label>Sexo<span class="atencao">*</span>:</label>
	                    <c:if test="${locacaoBean.locacao != null}">
	                    	<input id="sexoM" type="radio" name="sexo" value="M" ${viajanteBean.viajante.sexo == 'M' ? 'checked' : ''}/><label for="sexoM">Masculino</label>
	                    </c:if>
	                    <c:if test="${locacaoBean.locacao != null}">
	                    	<input id="sexoF" type="radio" name="sexo" value="F" ${viajanteBean.viajante.sexo == 'F' ? 'checked' : ''}/><label for="sexoF">Feminino</label>
	                    </c:if>
	                    <span id="sexoErro"></span>
					</div>
			        <div class="block">
						<input type="submit" name="acao" value="Alterar"/>
			        </div>
				</form>
			</fieldset>
		</div>
	</body>
</html>