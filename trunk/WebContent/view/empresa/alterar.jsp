<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
	<body class="perfil" onload=""> 
		<%@include file="../usuario/login.jsp"%>
		<%@include file="../menu.jsp" %>
        <jsp:useBean id="empresaBean" class="br.com.les20131.model.bean.EmpresaBean" scope="request"/>
		<div id="corpo">
			<%@include file="../central.jsp" %>
			<div class="aba">
				<ul>
					<li><a href="#abaEmpresa">Perfil</a></li>
				</ul>
				<div id="abaEmpresa">
					<form id="cadastro_empresa" class="formulario_padrao" enctype="multipart/form-data" action="<c:url value="/Empresa"></c:url>" method="post" onsubmit="return validaFormulario(new Array('nomeEmpresa;String;1', 'url;String;0','definicao;String;0'))">
				        <div class="bloco container">
					        <div class="parte_bloco">
						        <label for="imagem">Imagem:</label>
						        <br/>
						        <c:choose>
							        <c:when test="${empresaBean.empresa.idUsuario == usuarioBean.usuario.idUsuario}">
								        <div id="selecionaImagem" class="imagem_bloco">
									        <img id="imagemPrevia" class="imagem" alt="<c:url value="/Empresa?acao=carregarImagem&id=${empresaBean.empresa.idUsuario}"></c:url>" onclick="$('#imagem').click()"/>
										</div>
								        <input id="imagem" type="file" name="imagem" accept="image/x-png, image/jpeg" onchange="trocaImagem($('#imagemPrevia'), $('#imagem'), $(this.form).attr('action'), 'previrImagem')"/>
								        <span id="imagemErro" class="atencao"></span>
								    </c:when>
								    <c:otherwise>
								        <div id="selecionaImagem" class="imagem_bloco">
									        <img id="imagemPrevia" class="imagem" alt="<c:url value="/Empresa?acao=carregarImagem&id=${empresaBean.empresa.idUsuario}"></c:url>"/>
										</div>
								    </c:otherwise>
								</c:choose>
							</div>
							<div class="parte_bloco">
						        <div class="bloco">
							        <c:choose>
								        <c:when test="${empresaBean.empresa.idUsuario == usuarioBean.usuario.idUsuario}">
									        <label for="nomeEmpresa">Nome<span class="atencao">*</span>:</label>
									        <input id="nomeEmpresa" type="text" name="nomeEmpresa" value="${empresaBean.empresa.nome}" maxlength="100"/>
									        <span id="nomeEmpresaErro" class="atencao"></span>
									    </c:when>
									    <c:otherwise>
									    	<span class="titulo">${empresaBean.empresa.nome}</span>
									    </c:otherwise>
									</c:choose>
								</div>
						        <div class="bloco">
						        	<c:choose>
								        <c:when test="${empresaBean.empresa.idUsuario == usuarioBean.usuario.idUsuario}">
									        <label for="url">URL:</label>
									        <input id="url" type="text" name="url" value="${empresaBean.empresa.url}" maxlength="100"/>
									        <span id="urlErro" class="atencao"></span>
									    </c:when>
									    <c:otherwise>
									    	<a href="${empresaBean.empresa.url}"><button type="button" class="link titulo">${empresaBean.empresa.url}</button></a>
									    </c:otherwise>
									</c:choose>													                    
								</div>
						        <div class="bloco">
						        	<c:choose>
								        <c:when test="${empresaBean.empresa.idUsuario == usuarioBean.usuario.idUsuario}">
									        <label for="definicao">Definição:</label>
									        <input id="definicao" type="text" name="definicao" value="${empresaBean.empresa.definicao}" maxlength="100"/>
									        <span id="definicaoErro" class="atencao"></span>
									    </c:when>
									    <c:otherwise>
									    	<span>${empresaBean.empresa.definicao}</span>
									    </c:otherwise>
									</c:choose>	   						                          
								</div>
								<br/>
							</div>
							<c:if test="${usuarioEmpresaBean == null && administradorBean == null}">
								<div class="inferior_direito">
									<c:choose>
										<c:when test="${idAvaliacao > 0}">
											<button type="button"
												onclick="$('#avaliacao').submit()">
												<span>Editar avaliação</span>
											</button>
										</c:when>
										<c:otherwise>
											<button type="button"
												onclick="$('#avaliacao').submit()">
												<span>Avaliar</span>
											</button>
										</c:otherwise>
									</c:choose>
								</div>
							</c:if>	
						</div>						
	    				<c:if test="${empresaBean.empresa.idUsuario == usuarioBean.usuario.idUsuario}">	
					        <div class="bloco">
					        	<button type="submit" name="acao" value="alterar" >Alterar</button>
					        </div>
					    </c:if>
					</form>
					<form id="avaliacao" class="formulario_invisivel" action="<c:url value="/Avaliacao"></c:url>" method="post">
						<c:choose>
							<c:when test="${idAvaliacao > 0}">
								<input type="hidden" name="acao" value="selecionar"/>
								<input type="hidden" name="id" value="${idAvaliacao}"/>
							</c:when>
							<c:otherwise>
								<input type="hidden" name="acao" value=""/>
							</c:otherwise>
						</c:choose>
						<input type="hidden" name="idEmpresa" value="${empresaBean.empresa.idUsuario}"/>
					</form>
				</div>						
			</div>
			<c:if test="${empresaBean.empresa.idUsuario != usuarioBean.usuario.idUsuario}">
				<br/>
				<c:set var="perfilOutro" value="true" scope="request"/>
				<%@include file="../atualizacao/atualizacao.jsp"%>
			</c:if>
		</div>
		<%@include file="../adicional.jsp"%>
	</body>
</html>