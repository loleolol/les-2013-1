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
		<fieldset>
		<legend>Cadastre a viagem</legend>
			<form id="cadastro_viagem" class="formulario_padrao" action="<c:url value="/ViagemController"></c:url>" method="post" onsubmit="return validaFormulario(new Array('descricao;String;1'))">
			        <div class="block">
				        <label for="descricao">Descricao<span class="atencao">*</span>:</label>
				        <input id="descricao" type="text" name="descricao" value="" maxlength="100"/>
				        <span id="descricaoErro"></span>
					</div>
			        <div class="block">
						<input type="submit" name="acao" value="Cadastrar"/>
			        </div>
			</form>
		</fieldset>
	</body>
</html>