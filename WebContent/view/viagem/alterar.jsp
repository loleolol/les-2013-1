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
		<jsp:useBean id="imagemViagemBean" class="br.com.les20131.model.bean.ImagemViagemBean" scope="request"/>
        <jsp:useBean id="viagemBean" class="br.com.les20131.model.bean.ViagemBean" scope="request"/>
		<div id="corpo">
			<%@include file="../central.jsp" %>
			<div class="aba">
				<ul>
					<li><a href="#abaViagem">Registro de viagem</a></li>
				</ul>
				<div id="abaViagem">
					<form id="cadastro_viagem" class="formulario_padrao" enctype="multipart/form-data" action="<c:url value="/Viagem"></c:url>" method="post" onsubmit="return validaFormulario(new Array('titulo;String;1', 'descricao;String;1', 'dataInicialDia;int;1', 'dataInicialMes;int;1', 'dataInicialAno;int;1', 'dataFinalDia;int;1', 'dataFinalMes;int;1', 'dataFinalAno;int;1'))">
				        <div class="bloco">
					        <label for="titulo">T�tulo<span class="atencao">*</span>:</label>
					        <input id="titulo" type="text" name="titulo" value="${viagemBean.viagem.titulo}" maxlength="100"/>
					        <span id="tituloErro" class="atencao"></span>
						</div>
				        <div class="bloco galeria">
					        <label for="previrImagem">Imagens:</label>
					        <br/>
					        <input id="quantidadeImagem" type="hidden" name="quantidadeImagem" value="${fn:length(imagemViagemBean.listaImagemViagem)}"/>
					        <div id="adicionaImagem" class="imagem adiciona_imagem" onclick="adicionaCampoImagem($('#adicionaImagem'), $('#quantidadeImagem'), 'previrImagem', 'selecionaImagem', 'imagemPrevia', 'removerImagem', 'imagem')">
							</div>
						</div>
				        <div class="bloco">
					        <label for="descricao">Descri��o<span class="atencao">*</span>:</label>
					        <br/>
					        <textarea id="descricao" name="descricao" rows="8" cols="70"><c:out value="${viagemBean.viagem.descricao}"></c:out></textarea>
					        <span id="descricaoErro" class="atencao"></span>
						</div>
				        <div class="bloco">
				        	<label for="dataInicialDia">Per�odo da viagem:</label>
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
					        <label for="dataFinalDia">�</label>
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
		 					<c:forEach items="${imagemViagemBean.listaImagemViagem}" var="imagemViagem" varStatus="chave">
		 						<input id="idImagemViagem${chave.count}" type="hidden" name="idImagemViagem${chave.count}" value="${imagemViagem.idImagemViagem}"/>
							</c:forEach>
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
							carregarGaleriaEdicao($('#adicionaImagem'), $('#quantidadeImagem'), 'previrImagem', 'selecionaImagem', 'imagemPrevia', 'removerImagem', 'imagem', $('#cadastro_viagem').attr('action'), 'idImagemViagem', 'carregarImagem');
						});
					</script>
				</div>		
			</div>
		</div>
		<%@include file="../adicional.jsp"%>
	</body>
</html>
