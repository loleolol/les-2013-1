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
			<form id="cadastro_viajante" class="formulario_padrao" action="<c:url value="/ViajanteController"></c:url>" method="post" onsubmit="return validaFormulario(new Array('nome;String;1', 'email;String;1', 'confirmaEmail;String;1', 'senha;String;1', 'confirmaSenha;String;1', 'dataNascimentoDia;int;1', 'dataNascimentoMes;int;1', 'dataNascimentoAno;int;1', 'sexo;String;1'))">
			        <div class="block">
				        <label for="nome">Nome<span class="atencao">*</span>:</label>
				        <input id="nome" type="text" name="nome" value="" maxlength="100"/>
				        <span id="nomeErro"></span>
					</div>
			        <div class="block">
				        <label for="email">E-mail<span class="atencao">*</span>:</label>
				        <input id="email" type="text" name="email" value="" maxlength="100"/>
				        <span id="emailErro"></span>
					</div>
			        <div class="block">
				        <label for="confirmaEmail">Confirme E-mail<span class="atencao">*</span>:</label>
				        <input id="confirmaEmail" type="text" name="confirmaEmail" value="" maxlength="100"/>
				        <span id="confirmaEmailErro"></span>
					</div>
			        <div class="block">
				        <label for="senha">Senha<span class="atencao">*</span>:</label>
				        <input id="senha" type="password" name="senha" value="" maxlength="50"/>
				        <span id="senhaErro"></span>
					</div>
			        <div class="block">
				        <label for="confirmaSenha">Confirme Senha<span class="atencao">*</span>:</label>
				        <input id="confirmaSenha" type="password" name="confirmaSenha" value="" maxlength="50"/>
				        <span id="confirmaSenhaErro"></span>
					</div>			
			        <div class="block">
	                    <label for="dataNascimentoDia">Data de nascimento<span class="atencao">*</span>:</label>
	                    <select id="dataNascimentoDia" name="dataNascimentoDia">
	                    </select>
	                    <span id="dataNascimentoDiaErro"></span>
	                    <select id="dataNascimentoMes" name="dataNascimentoMes" onchange="populaDropDownDia($('#dataNascimentoDia'), $(this).val());">
	                    </select>
	                    <span id="dataNascimentoMesErro"></span>
	                    <select id="dataNascimentoAno" name="dataNascimentoAno">
	                    </select>
	                    <span id="dataNascimentoAnoErro"></span>
					</div>
			        <div class="block">
	                    <label>Sexo<span class="atencao">*</span>:</label>
	                    <input id="sexoM" type="radio" name="sexo" value="M"/><label for="sexoM">Masculino</label>
	                    <input id="sexoF" type="radio" name="sexo" value="F"/><label for="sexoF">Feminino</label>
	                    <span id="sexoErro"></span>
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