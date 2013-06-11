<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="atualizacaoBean" class="br.com.les20131.model.bean.AtualizacaoBean" scope="request"/>
<div class="aba">
	<ul>
		<li>
			<a href="#abaAtividadeRecente">
				<c:choose>
					<c:when test="${criterio == 'Avaliacao'}">
						Avaliações
					</c:when>
					<c:when test="${criterio == 'Viagem'}">
						Viagens
					</c:when>
					<c:when test="${criterio == 'Anuncio'}">
						Anúncios
					</c:when>					
					<c:otherwise>
						Atividade recente
					</c:otherwise>
				</c:choose>
			</a>
		</li>
		<c:choose>
			<c:when test="${usuarioEmpresaBean != null && perfilOutro != true}">
				<li><a href="#abaAnuncio">Novo anúncio</a></li>
			</c:when>
			<c:when test="${administradorBean == null && perfilOutro != true}">
				<li><a href="#abaViagem">Nova viagem</a></li>
			</c:when>
		</c:choose>

	</ul>
	<div id="abaAtividadeRecente">  
		<c:choose>
			<c:when test="${fn:length(atualizacaoBean.listaAtualizacao) > 0}">
				<c:forEach items="${atualizacaoBean.listaAtualizacao}" var="atualizacao" varStatus="chave">
					<div class="formulario_postagem">
						<c:if test="${atualizacao.acao == 'Viagem' || atualizacao.acao == 'Avaliacao' || atualizacao.acao == 'Anuncio'}">
							<div id="barraFerramentas${chave.count}" class="parte_bloco barra_ferramentas">
								<c:choose>
									<c:when test="${administradorBean != null}">
										<a class="remover" href="javascript:void(0)" title="Remover"
											onclick="confirmaExclusao($('#atualizacao${chave.count}'), 'Deseja realmente realizar esta exclusão?', '#acao${chave.count}', 'Excluir')">
										</a>
									</c:when>
									<c:when test="${atualizacao.idAutor == usuarioBean.usuario.idUsuario}">
								        <a class="editar" href="javascript:void(0)" title="Editar"
											onclick="$('#acao${chave.count}').val('Selecionar'); $('#atualizacao${chave.count}').submit()">
										</a>
										<a class="remover" href="javascript:void(0)" title="Remover"
											onclick="confirmaExclusao($('#atualizacao${chave.count}'), 'Deseja realmente realizar esta exclusão?', '#acao${chave.count}', 'Excluir')">
										</a>
									</c:when>
									<c:otherwise></c:otherwise>
								</c:choose>						
							</div>
						</c:if>
						<div class="parte_bloco postador">
							<c:choose>
								<c:when test="${usuarioEmpresaBean != null }">
									<img id="imagemBarra" class="imagem_barra" alt="<c:url value="/Empresa?acao=carregarImagem&id=${atualizacao.idAutor}"></c:url>"/>
								</c:when>
								<c:otherwise>
									<img id="imagemBarra" class="imagem_barra" alt="<c:url value="/Viajante?acao=carregarImagem&id=${atualizacao.idAutor}"></c:url>"/>
								</c:otherwise>
							</c:choose>
						</div>
						<div class="parte_bloco">
							<c:choose>
				   				<c:when test="${atualizacao.acao == 'Viagem'}">
									<button class="link titulo"  type="button" value="${atualizacao.idAutor}" onclick="atalhoPerfil(this, 'Viajante')">${atualizacao.autor}</button>
									<span>compartilhou</span>
									<span class="titulo">${atualizacao.nome}</span>
								</c:when>
				   				<c:when test="${atualizacao.acao == 'Avaliacao'}">
				   					<button class="link titulo"  type="button" value="${atualizacao.idAutor}" onclick="atalhoPerfil(this, 'Viajante')">${atualizacao.autor}</button>
									<span>avaliou</span>
									<button class="link titulo" type="button" value="${atualizacao.listaId[0]}" onclick="atalhoPerfil(this, 'Empresa')">${atualizacao.nome}</button>
									<span>em</span>
							        <input id="avaliacao${chave.count}" type="hidden" name="avaliacao" value="${atualizacao.listaId[1]}"/>
								</c:when>
				   				<c:when test="${atualizacao.acao == 'Anuncio'}">
				   					<button class="link titulo"  type="button" value="${atualizacao.idAutor}" onclick="atalhoPerfil(this, 'Empresa')">${atualizacao.autor}</button>
									<span>anunciou:</span>
								</c:when>
								<c:otherwise>
									<button class="link titulo"  type="button" value="${atualizacao.idAutor}" onclick="atalhoPerfil(this, 'Viajante')">${atualizacao.autor}</button>
									<span>adicionou</span>
									<button class="link titulo" type="button" value="${atualizacao.id}" onclick="atalhoPerfil(this, 'Viajante')">${atualizacao.nome}</button>
									<span>como contato</span>
								</c:otherwise>
							</c:choose>
							<br/>
							<fmt:formatDate type="date" pattern="dd/MM/yyyy" value="${atualizacao.dataInclusao}" var="dataInclusao"/>	                    
							<span class="data_atualizacao">${dataInclusao}</span>
							<br/>
						</div>
						<c:choose>
							<c:when test="${atualizacao.acao == 'Viagem' || atualizacao.acao == 'Avaliacao' || atualizacao.acao == 'Anuncio'}">
								<br/>
								<div class="parte_bloco intervalo_postagem">
								</div>
								<div class="parte_bloco container postagem">
									<form id="atualizacao${chave.count}" action="<c:url value="/${atualizacao.acao}"></c:url>" method="post">
										<c:if test="${atualizacao.acao == 'Viagem' && fn:length(atualizacao.listaId) > 0}">
											<div id="galeria${chave.count}">
												<img id="imagem${chave.count}" class="imagem"/>
											</div>
											<c:forEach items="${atualizacao.listaId}" var="id" varStatus="chave2">
												<input id="imagemUrl${chave.count}${chave2.count}" name="imagemUrl${chave.count}" type="hidden" value="<c:url value="/Viagem?acao=carregarImagem&id=${id}"></c:url>"/>
											</c:forEach>
										</c:if>
										<div class="texto">${atualizacao.texto}</div>
										<input type="hidden" name="id" value="${atualizacao.id}"/>
										<c:if test="${atualizacao.idAutor == usuarioBean.usuario.idUsuario || administradorBean != null}">
											<input id="acao${chave.count}" type="hidden" name="acao" value=""/>
										</c:if>
									</form>
							 	</div>
							 </c:when>
							 <c:otherwise>
							 	<input type="hidden" name="id" value="${atualizacao.id}"/>
							 </c:otherwise>
						</c:choose>
					</div>
				</c:forEach>
				<script type="text/javascript">
					$(document).ready(function() { 
						carregarGaleria('id', 'imagem', 'imagemUrl', 'atualizacao');
						carregarAvaliacoes('avaliacao');
					});
				</script>
				<form id="atalho" action="<c:url value="/"></c:url>" method="post">
					<input id="idAtalho" type="hidden" name="id"/>
					<input type="hidden" name="acao" value="selecionar"/>
				</form>								
			</c:when>
			<c:otherwise>
				<div class="bloco barra_central">Nenhuma atualização no momento.</div>
			</c:otherwise>
		</c:choose>
	</div>
	<c:choose>
		<c:when test="${usuarioEmpresaBean != null && perfilOutro != true}">
			<div id="abaAnuncio">
				<%@include file="../anuncio/novo.jsp"%>
			</div>
		</c:when>
		<c:when test="${administradorBean == null && perfilOutro != true}">
			<div id="abaViagem">
				<%@include file="../viagem/novo.jsp"%>
			</div>
		</c:when>
	</c:choose>

</div>