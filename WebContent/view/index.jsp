<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>#Partiu</title>
        <link type="text/css" rel="stylesheet" href="/les20131/view/publico/css/jquery-ui-1.10.3.custom.min.css"/>
        <link type="text/css" rel="stylesheet" href="/les20131/view/publico/css/estilo.css"/>
        <script type="text/javascript" src="/les20131/view/publico/js/jquery-1.9.1.min.js" charset="ISO-8859-1"></script>
        <script type="text/javascript" src="/les20131/view/publico/js/jquery-ui-1.10.3.custom.js" charset="ISO-8859-1"></script>
        <script type="text/javascript" src="/les20131/view/publico/js/trabalho.js" charset="ISO-8859-1"></script>
    </head>
	<body onload="mostraMensagem('inicial_esquerda', '${mensagemBean.mensagem}')"> 
		<%@include file="usuario/login.jsp"%>
		<div id="inicial_esquerda">
			<br/>
		</div>
		<div id="inicial_centro">
			<div class="bloco_imagem_inicio">
				<img id="imagem_inicio" src="<c:url value="/view/publico/imagens/park.jpg"></c:url>"/>
				<h2>
					<span>Compartilhe suas experiências</span><br/><span>Conheça novas pessoas e lugares</span>
				</h2>
			</div>
			<script type="text/javascript">
				$(document).ready(function() { 
					slideImagensInicio();
				});
			</script>
		</div>
		<div id="barra_direita">
			<%@include file="viajante/incluir.jsp" %>
		</div>
	</body>
</html>