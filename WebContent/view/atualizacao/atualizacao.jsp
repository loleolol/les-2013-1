<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<jsp:useBean id="atualizacaoBean" class="br.com.les20131.model.bean.AtualizacaoBean" scope="request"/>
<fieldset>
	<legend>Atividade recente</legend>
	<c:forEach items="${atualizacaoBean.listaAtualizacao}" var="atualizacao" varStatus="chave">
		<div class="formulario_postagem">
			<c:if test="${atualizacao.idAutor == usuarioBean.usuario.idUsuario}">
		        <a class="editar" href="javascript:void(0)" title="Editar"
					onclick="$('#acao${chave.count}').val('Selecionar'); $('#atualizacao${chave.count}').submit()">
				</a>
				<a class="remover" href="javascript:void(0)" title="Remover"
					onclick="$('#acao${chave.count}').val('Excluir'); confirmaExclusao($('#atualizacao${chave.count}'))">
				</a>
			</c:if>
			<div class="parte_bloco postador">
				<img id="imagemBarra" class="imagem_barra" alt="<c:url value="/Viajante?acao=carregarImagem&id=${atualizacao.idAutor}"></c:url>"/>
			</div>
			<div class="parte_bloco">
				<span class="titulo">${atualizacao.autor}</span>
				<c:choose>
	   				<c:when test="${atualizacao.acao == 'Viagem'}">
						<span> compartilhou </span>
						<span class="titulo">${atualizacao.nome}</span>
					</c:when>
					<c:otherwise>
						<span> adicionou </span>
						<span class="titulo">${atualizacao.nome}</span>
						<span> como contato</span>
					</c:otherwise>
				</c:choose>
				<br/>
				<br/>
			</div>
			<br/>          	
			<div class="parte_bloco postador">
			</div>
			<div class="parte_bloco container">
				<form id="atualizacao${chave.count}" action="<c:url value="/${atualizacao.acao}"></c:url>" method="post">
					<c:if test="${fn:length(atualizacao.listaId) > 0}">
						<div>
							<div id="galeria${chave.count}" class="galeria">
								<img id="imagem${chave.count}" class="imagem"/>
							</div>
						</div>
						<c:forEach items="${atualizacao.listaId}" var="id" varStatus="chave2">
							<input id="imagemUrl${chave.count}${chave2.count}" name="imagemUrl${chave.count}" type="hidden" value="<c:url value="/Viagem?acao=carregarImagem&id=${id}"></c:url>"/>
						</c:forEach>
					</c:if>
					<c:if test="${atualizacao.acao == 'Viagem'}">
						<div class="texto">${atualizacao.texto}</div>
					</c:if>
					<input type="hidden" name="id" value="${atualizacao.id}"/>
					<c:if test="${atualizacao.idAutor == usuarioBean.usuario.idUsuario}">
						<input id="acao${chave.count}" type="hidden" name="acao" value=""/>
					</c:if>
				</form>
		 	</div>
		</div>
	</c:forEach>
</fieldset>
<script type="text/javascript">
	$(document).ready(function() { 
		carregarGaleria('id', 'imagem', 'imagemUrl', 'atualizacao');
	});
</script>