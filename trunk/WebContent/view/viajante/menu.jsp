<div id="barra_esquerda">
	<c:choose>
		<c:when test="${usuarioBean.usuario != null}">
			<form id="menu_usuario_inicio" class="formulario_menu" action="<c:url value="/ViajanteController"></c:url>" method="post">
		        <div>
		        	<button type="submit" name="acao" value="">Início</button>
		        </div>
		    </form>
			<form id="menu_usuario_alterar_conta" class="formulario_menu" action="<c:url value="/UsuarioController"></c:url>" method="post">
		        <div>
		        	<button type="submit" name="acao" value="selecionar">Alterar conta</button>
				</div>
		    </form>
			<form id="menu_usuario_alterar_perfil" class="formulario_menu" action="<c:url value="/ViajanteController"></c:url>" method="post">
				<div>
		        	<button type="submit" name="acao" value="selecionar">Alterar perfil</button>
				</div>
		    </form>
			<form id="menu_usuario_minhas_viagens" class="formulario_menu" action="<c:url value="/ViagemController"></c:url>" method="post">
				<div>
		        	<button type="submit" name="acao" value="listar">Minhas viagens</button>
				</div>
			</form>
		</c:when>
		<c:otherwise>
			<br/>
		</c:otherwise>
	</c:choose>
</div>
