<div class="bloco barra_central">
	<c:choose>
		<c:when test="${empresaBean != null}">
			<form id="iniciar_anuncio" class="formulario_padrao" action="<c:url value="/Anuncio"></c:url>" method="post">
				<label for="compartilhar">Quer ser visto pelos usuários?</label>
				<button id="compartilhar" class="link titulo" type="submit" name="acao" value="">Anuncie!</button>
			</form>
		</c:when>
		<c:when test="${administradorBean == null}">
			<form id="iniciar_viagem" class="formulario_padrao" action="<c:url value="/Viagem"></c:url>" method="post">
				<label for="compartilhar">Viajou recentemente?</label>
				<button id="compartilhar" class="link titulo" type="submit" name="acao" value="">Compartilhe sua experiência!</button>
			</form>
		</c:when>		
		<c:otherwise>
			<p>
				<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
				<span>Modo administrador: Utilize o campo de busca para acessar perfis e efetuar bloqueios/exclusões</span>
			</p>		
		</c:otherwise>
	</c:choose>
</div>