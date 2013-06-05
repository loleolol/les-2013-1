<jsp:useBean id="anuncioEmpresaBean" class="br.com.les20131.model.bean.AnuncioBean" scope="request"/>
<div id="barra_direita">
	<c:choose>
		<c:when test="${usuarioBean.usuario != null}">
			<c:forEach items="${anuncioEmpresaBean.listaAnuncio}" var="anuncio" varStatus="chave">
				<form id="menuUsuario${chave.count}" class="formulario_anuncio" action="<c:url value="/Anuncio"></c:url>" method="post">
					<div class="anuncio">
	        			<span class="titulo"><c:out value="${anuncio.empresa.nome}"/></span>
	        			<br/>
	        			<c:out value="${anuncio.anuncio}"/>
	        			<br/>
	        			<input name="idAnuncio" type="hidden" value="${anuncio.idAnuncio}"/>
	        		</div>
				</form>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<br/>
		</c:otherwise>
	</c:choose>
	<script type="text/javascript">
		$(document).ready(function() { 
			carregarImagens();
		});
	</script>
</div>
