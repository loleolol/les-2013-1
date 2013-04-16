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
        <script type="text/javascript" src="/les20131/view/publico/js/jquery-1.9.1.min.js" charset="ISO-8859-1"></script>
    </head>
	<body class="perfil" onload="mostraMensagem('${mensagemBean.mensagem}')"> 
		<%@include file="../usuario/login.jsp"%>
		<%@include file="menu.jsp" %>
		<%@include file="adicional.jsp"%>
		<fieldset>
		<legend>Cadastre-se</legend>
			<form id="cadastro_viajante" class="formulario_padrao" action="<c:url value="/ViajanteController"></c:url>"
			 method="post" onsubmit="return (verificarValorIgualCampos('email') && verificarValorIgualCampos('senha') && validaFormulario(new Array('nome;String;1', 'email;String;1;validaEmail', 'emailConfirma;String;1;validaEmail', 'senha;String;1', 'senhaConfirma;String;1', 'dataNascimentoDia;int;1', 'dataNascimentoMes;int;1', 'dataNascimentoAno;int;1', 'sexo;String;1')))">
			        <div class="block">
				        <label for="nome">Nome<span class="atencao">*</span>:</label>
				        <input id="nome" type="text" name="nome" value="" maxlength="100"/>
				        <span id="nomeErro" class="atencao"></span>
					</div>
			        <div class="block">
				        <label for="email">E-mail<span class="atencao">*</span>:</label>
				        <input id="email" type="text" name="email" value="" maxlength="100"/>
				        <span id="emailErro" class="atencao"></span>
					</div>
			        <div class="block">
				        <label for="emailConfirma">Confirme E-mail<span class="atencao">*</span>:</label>
				        <input id="emailConfirma" type="text" name="emailConfirma" value="" maxlength="100"/>
				        <span id="emailConfirmaErro" class="atencao"></span>
					</div>
			        <div class="block">
				        <label for="senha">Senha<span class="atencao">*</span>:</label>
				        <input id="senha" type="password" name="senha" value="" maxlength="50"/>
				        <span id="senhaErro" class="atencao"></span>
					</div>
			        <div class="block">
				        <label for="senhaConfirma">Confirme Senha<span class="atencao">*</span>:</label>
				        <input id="senhaConfirma" type="password" name="senhaConfirma" value="" maxlength="50"/>
				        <span id="senhaConfirmaErro" class="atencao"></span>
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
					</div>
			        <div class="block">
	                    <label>Sexo<span class="atencao">*</span>:</label>
	                    <input id="sexoM" type="radio" name="sexo" value="M"/><label for="sexoM">Masculino</label>
	                    <input id="sexoF" type="radio" name="sexo" value="F"/><label for="sexoF">Feminino</label>
	                    <span id="sexoErro" class="atencao"></span>
					</div>
			        <div class="block">
						<input type="submit" name="acao" value="Cadastrar"/>
			        </div>
			</form>
		</fieldset>
		<script type="text/javascript">
			$(document).ready(function() { 
				populaDropDownAno($('#dataNascimentoAno'));
				populaDropDownMes($('#dataNascimentoMes')); 
				populaDropDownDia($('#dataNascimentoDia'), $('#dataNascimentoMes').val()); 
			});
		</script>
	</body>
</html>