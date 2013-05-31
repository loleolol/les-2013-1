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
	<body class="perfil" onload="mostraMensagem('${mensagemBean.mensagem}')"> 
		<%@include file="../usuario/login.jsp"%>
		<%@include file="../menu.jsp"%>
		<div id="corpo">
			<%@include file="../central.jsp" %>
			<div class="aba">
				<ul>
					<li><a href="#abaAvaliacao">Avaliação</a></li>
				</ul>
				<div id="abaAvaliacao">
					<form id="cadastroAvaliacao" class="formulario_padrao" action="<c:url value="/Avaliacao"></c:url>"
					 method="post" onsubmit="return validaFormulario(new Array('avaliacao;int;1', 'descricao;String;1'))">
				        <div class="bloco">
					        <label for="avaliacao">Avaliação<span class="atencao">*</span>:</label>
					        <div class="estrela estrela_nao_marcada" title="1" onclick="avaliarEmpresa(this, $('#avaliacao'))"></div>
					        <div class="estrela estrela_nao_marcada" title="2" onclick="avaliarEmpresa(this, $('#avaliacao'))"></div>
					        <div class="estrela estrela_nao_marcada" title="3" onclick="avaliarEmpresa(this, $('#avaliacao'))"></div>
					        <div class="estrela estrela_nao_marcada" title="4" onclick="avaliarEmpresa(this, $('#avaliacao'))"></div>
					        <div class="estrela estrela_nao_marcada" title="5" onclick="avaliarEmpresa(this, $('#avaliacao'))"></div>
					        <input id="avaliacao" type="hidden" name="avaliacao" value="${avaliacaoBean.avaliacao.avaliacao}"/>
					        <span id="avaliacaoErro" class="atencao"></span>
						</div>
				        <div class="bloco">
					        <label for="descricao">Descrição<span class="atencao">*</span>:</label>
					        <br/>
					        <textarea id="descricao" name="descricao" rows="8" cols="70">${avaliacaoBean.avaliacao.descricao}</textarea>
					        <span id="descricaoErro" class="atencao"></span>
							<input id="idAvaliacao" type="hidden" name="idAvaliacao" value="${avaliacaoBean.avaliacao.idAvaliacao}"/>
						</div>
				        <div class="bloco">
				           	<button type="submit" name="acao" value="alterar">Alterar</button>
				        </div>
					</form>
					<script type="text/javascript">
						$(document).ready(function() { 
							carregarAvaliacaoEmpresa($('#avaliacao'));
						});
					</script>					
				</div>
			</div>
		</div>
		<%@include file="../adicional.jsp"%>
	</body>
</html>