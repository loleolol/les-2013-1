<div id="barra_esquerda">
	<c:choose>
		<c:when test="${usuarioBean.usuario != null}">
			<form id="menu1" class="formulario_menu" action="<c:url value="/Viajante"></c:url>" method="post">
				<div class="previa_perfil menu_perfil">
					<button type="submit" name="acao" value="selecionar">
			    		<div class="parte_bloco">
				    		<img id="imagemBarra" class="imagem_barra" alt="<c:url value="/Viajante?acao=carregarImagem&id=${usuarioBean.usuario.idUsuario}"></c:url>"/>
			    		</div>
			    		<div class="parte_bloco">
			    			<span class="titulo">${usuarioBean.usuario.nome}</span>
			    			<br/>
			    			<span>Editar perfil</span>
			    			<br/>
			    			<br/>
						</div>
					</button>
				</div>
			</form>
			<form id="menu2" class="formulario_menu" action="<c:url value="/Viajante"></c:url>" method="post">
		        <div>
		        	<button type="submit" name="acao" value="">Início</button>
		        </div>
		    </form>
			<form id="menu3" class="formulario_menu" action="<c:url value="/Pesquisa"></c:url>" method="post">
		        <div>
		        	<button type="submit" name="acao" value="contatos">Contatos</button>
				</div>
		    </form>
			<form id="menu4" class="formulario_menu" action="<c:url value="/Atualizacao"></c:url>" method="post">
				<div>
		        	<button type="submit" name="acao" value="viagens">Viagens</button>
				</div>
			</form>
			<form id="menu5" class="formulario_menu" action="<c:url value="/Usuario"></c:url>" method="post">
		        <div>
		        	<button type="submit" name="acao" value="selecionar">Conta</button>
				</div>
		    </form>
		</c:when>
		<c:otherwise>
			<br/>
		</c:otherwise>
	</c:choose>
</div>
