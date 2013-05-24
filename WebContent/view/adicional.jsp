<div id="barra_direita">
	<c:choose>
		<c:when test="${usuarioBean.usuario != null}">
			<form id="menu_usuario" class="formulario_menu" action="<c:url value="/Viajante"></c:url>" method="post">
        		<div class="anuncio">
        			Anúncios
        			<br/>
        		</div>
			</form>
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
