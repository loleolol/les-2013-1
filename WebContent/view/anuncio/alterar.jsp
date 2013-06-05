<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
        <jsp:useBean id="anuncioBean" class="br.com.les20131.model.bean.AnuncioBean" scope="request"/>
		<div id="corpo">
			<%@include file="../central.jsp" %>
			<div class="aba">
				<ul>
					<li><a href="#abaAnuncio">Registro de anúncio</a></li>
				</ul>
				<div id="abaAnuncio">
					<form id="cadastro_anuncio" class="formulario_padrao" action="<c:url value="/Anuncio"></c:url>" method="post" onsubmit="return validaFormulario(new Array('anuncio;String;1', 'dataInicialDia;int;1', 'dataInicialMes;int;1', 'dataInicialAno;int;1', 'dataFinalDia;int;1', 'dataFinalMes;int;1', 'dataFinalAno;int;1'))">
				        <div class="bloco">
					        <label for="anuncio">Anúncio<span class="atencao">*</span>:</label>
					        <br/>
					        <textarea id="anuncio" name="anuncio" rows="8" cols="70"><c:out value="${anuncioBean.anuncio.anuncio}"></c:out></textarea>
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
							<fmt:formatDate type="date" pattern="yyyy-MM-dd" value="${anuncioBean.anuncio.dataInicial}" var="dataInicial"/>	                    
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
							<fmt:formatDate type="date" pattern="yyyy-MM-dd" value="${anuncioBean.anuncio.dataFinal}" var="dataFinal"/>	                    
		                    <input id="dataFinal" type="hidden" name="dataFinal" value="${dataFinal}"/>
							
							<input id="idAnuncio" type="hidden" name="idAnuncio" value="${anuncioBean.anuncio.idAnuncio}"/>		 					
						</div>
				        <div class="bloco">
				        	<button type="submit" name="acao" value="alterar">Alterar</button>
				        </div>
					</form>
					<script type="text/javascript">
						$(document).ready(function() { 
							populaDropDownAno($('#dataInicialAno'), $('#dataInicial'));
							populaDropDownMes($('#dataInicialMes'), $('#dataInicial')); 
							populaDropDownDia($('#dataInicialDia'), $('#dataInicialMes').val(), $('#dataInicial'));
							populaDropDownAno($('#dataFinalAno'), $('#dataFinal'));
							populaDropDownMes($('#dataFinalMes'), $('#dataFinal'));
							populaDropDownDia($('#dataFinalDia'), $('#dataFinalMes').val(), $('#dataFinal'));							
						});
					</script>
				</div>		
			</div>
		</div>
		<%@include file="../adicional.jsp"%>
	</body>
</html>
