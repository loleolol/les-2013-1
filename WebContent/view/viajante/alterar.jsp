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
		<script type="text/javascript"
    		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDdl9ezzvGQd68O6zshDaBgJaHM_RH6-6g&sensor=true">
		</script>
	    <script type="text/javascript">
	    	var map;
	    	var markersArray = [];
			function initialize() {
				var editavel = (document.getElementById("nome") == undefined ? false : true);
				var latitude = document.getElementById("latitude").value;
				var longitude = document.getElementById("longitude").value;
				var semPosicao = false;
				if ((latitude == -9999) && (longitude == -9999)) {
					latitude = -20.767954;
					longitude = -48.071657;
					semPosicao = true;
				}
				posicao = new google.maps.LatLng(latitude,longitude);
				var mapOptions = {
					center: posicao,
					zoom: 3,
					mapTypeId: google.maps.MapTypeId.ROADMAP
				};
				map = new google.maps.Map(document.getElementById("map-canvas"), mapOptions);
				
				if (editavel == true) {
					google.maps.event.addListener(map, 'click', function(event) {
						placeMarker(event.latLng);
					});
				}			  
				
				google.maps.Map.prototype.clearOverlays = function() {
					for (var i = 0; i < markersArray.length; i++ ) {
						markersArray[i].setMap(null);
					}
					document.getElementById("latitude").setAttribute("value", "");
					document.getElementById("longitude").setAttribute("value", "");
				}
				if (semPosicao == false) {
					placeMarker(posicao);
				}
			}

			function placeMarker(location) {
				map.clearOverlays(); 
				var marker = new google.maps.Marker({
					position: location,
					map: map
				});
				document.getElementById("latitude").setAttribute("value", location.lat());
				document.getElementById("longitude").setAttribute("value", location.lng());
				markersArray.push(marker);
				map.setCenter(location);
			}

			google.maps.event.addDomListener(window, 'load', initialize);
		 </script>		
    </head>
	<body class="perfil" onload="initialize(); mostraMensagem('${mensagemBean.mensagem}')"> 
		<%@include file="../usuario/login.jsp"%>
		<%@include file="../menu.jsp" %>
        <jsp:useBean id="viajanteBean" class="br.com.les20131.model.bean.ViajanteBean" scope="request"/>
		<div id="corpo">
			<%@include file="../central.jsp" %>
			<div class="aba">
				<ul>
					<li><a href="#abaViajante">Perfil</a></li>
				</ul>
				<div id="abaViajante">
					<form id="cadastro_viajante" class="formulario_padrao" enctype="multipart/form-data" action="<c:url value="/Viajante"></c:url>" method="post" onsubmit="return validaFormulario(new Array('nome;String;1', 'dataNascimentoDia;int;1', 'dataNascimentoMes;int;1', 'dataNascimentoAno;int;1', 'sexo;String;1'))">
				        <div class="bloco container">
					        <div class="parte_bloco">
						        <label for="imagem">Imagem:</label>
						        <br/>
						        <c:choose>
							        <c:when test="${viajanteBean.viajante.idUsuario == usuarioBean.usuario.idUsuario}">
								        <div id="selecionaImagem" class="imagem_bloco">
									        <img id="imagemPrevia" class="imagem" alt="<c:url value="/Viajante?acao=carregarImagem&id=${viajanteBean.viajante.idUsuario}"></c:url>" onclick="$('#imagem').click()"/>
										</div>
								        <input id="imagem" type="file" name="imagem" accept="image/x-png, image/jpeg" onchange="trocaImagem($('#imagemPrevia'), $('#imagem'), $(this.form).attr('action'), 'previrImagem')"/>
								        <span id="imagemErro" class="atencao"></span>
								    </c:when>
								    <c:otherwise>
								        <div id="selecionaImagem" class="imagem_bloco">
									        <img id="imagemPrevia" class="imagem" alt="<c:url value="/Viajante?acao=carregarImagem&id=${viajanteBean.viajante.idUsuario}"></c:url>"/>
										</div>
								    </c:otherwise>
								</c:choose>
							</div>
							<div class="parte_bloco">
						        <div class="bloco">
							        <c:choose>
								        <c:when test="${viajanteBean.viajante.idUsuario == usuarioBean.usuario.idUsuario}">
									        <label for="nome">Nome<span class="atencao">*</span>:</label>
									        <input id="nome" type="text" name="nome" value="${viajanteBean.viajante.nome}" maxlength="100"/>
									        <span id="nomeErro" class="atencao"></span>
									    </c:when>
									    <c:otherwise>
									    	<span class="titulo">${viajanteBean.viajante.nome}</span>
									    </c:otherwise>
									</c:choose>
								</div>
						        <div class="bloco">
									<fmt:formatDate type="date" pattern="yyyy-MM-dd" value="${viajanteBean.viajante.dataNascimento}" var="dataNascimento"/>	                    
	   						        <c:choose>
								        <c:when test="${viajanteBean.viajante.idUsuario == usuarioBean.usuario.idUsuario}">
						                    <label for="dataNascimentoDia">Data de nascimento<span class="atencao">*</span>:</label>
						                    <select id="dataNascimentoDia" name="dataNascimentoDia">
						                    </select>
						                    <span id="dataNascimentoDiaErro" class="atencao"></span>
						                    <select id="dataNascimentoMes" name="dataNascimentoMes" onchange="populaDropDownDia($('#dataNascimentoDia'), $(this).val());">
						                    </select>
						                    <span id="dataNascimentoMesErro" class="atencao"></span>
						                    <select id="dataNascimentoAno" name="dataNascimentoAno">
						                    </select>
						                    <span id="dataNascimentoAnoErro" class="atencao"></span>
						                    <input id="dataNascimento" type="hidden" name="dataNascimento" value="${dataNascimento}"/>
									    </c:when>
									    <c:otherwise>
									    	<span id="idade">${dataNascimento}</span>
									    </c:otherwise>
									</c:choose>					                    
								</div>
						        <div class="bloco">
	   						        <c:choose>
								        <c:when test="${viajanteBean.viajante.idUsuario == usuarioBean.usuario.idUsuario}">					        
						                    <label>Sexo<span class="atencao">*</span>:</label>
					                    	<input id="sexoM" type="radio" name="sexo" value="M" ${viajanteBean.viajante.sexo == 'M' ? 'checked' : ''}/><label class="label_radio" for="sexoM">Masculino</label>
					                    	<input id="sexoF" type="radio" name="sexo" value="F" ${viajanteBean.viajante.sexo == 'F' ? 'checked' : ''}/><label class="label_radio" for="sexoF">Feminino</label>
						                    <span id="sexoErro" class="atencao"></span>
									    </c:when>
									    <c:when test="${viajanteBean.viajante.sexo == 'M'}">
									    	<span>Masculino</span>
									    </c:when>
									    <c:otherwise>
									    	<span>Feminino</span>
									    </c:otherwise>
									</c:choose>							                    
								</div>
								<br/>
							</div>
							<c:if test="${viajanteBean.viajante.idUsuario != usuarioBean.usuario.idUsuario}">
								<div class="inferior_direito">
									<c:choose>
										<c:when test="${administradorBean != null }">
											<c:choose>
												<c:when test="${usuarioBean.usuario.bloqueado == 0}">
													<button type="button"
														onclick="bloquearUsuario($(this), ${itemResultado.id})">
														<span>Bloquear</span>
													</button>
												</c:when>
												<c:otherwise>
													<button type="button"
														onclick="desbloquearUsuario($(this), ${itemResultado.id})">
														<span>Desbloquear</span>
													</button>
												</c:otherwise>
											</c:choose>
										</c:when>
										<c:otherwise>
											<button type="button" onclick="$('#contato').submit()">
				    							<span>Ver contatos</span>
				    						</button>
											<c:choose>
												<c:when test="${contato}">
													<button type="button"
														onclick="removerContato($(this), ${viajanteBean.viajante.idUsuario})">
														<span class="excluir"></span> <span>Contato</span>
													</button>
												</c:when>
												<c:otherwise>
													<button type="button"
														onclick="adicionarContato($(this), ${viajanteBean.viajante.idUsuario})">
														<span class="incluir"></span> <span>Contato</span>
													</button>
												</c:otherwise>
											</c:choose>
										</c:otherwise>
									</c:choose>				    				
								</div>
							</c:if>	
						</div>
						<c:choose>
					        <c:when test="${viajanteBean.viajante.idUsuario != usuarioBean.usuario.idUsuario && viajanteBean.viajante.latitude == -9999}">		
			    			</c:when>
			    			<c:otherwise>
								<div class="bloco container">
									<label for="map-canvas">Localização:</label>
							        <c:if test="${viajanteBean.viajante.idUsuario == usuarioBean.usuario.idUsuario}">		
											<a class="remover" href="javascript:void(0)" 
												onclick="map.clearOverlays();" title="Remover localização"> 
											</a>
									</c:if>				
									<div id="map-canvas"></div>
									<input id="latitude" type="hidden" name="latitude" value="${viajanteBean.viajante.latitude}"/>
									<input id="longitude" type="hidden" name="longitude" value="${viajanteBean.viajante.longitude}"/>
			    				</div>
			    			</c:otherwise>
			    		</c:choose>
	    				<c:if test="${viajanteBean.viajante.idUsuario == usuarioBean.usuario.idUsuario}">	
					        <div class="bloco">
					        	<button type="submit" name="acao" value="alterar" >Alterar</button>
					        	<input id="hidden_excluir" type="hidden" name="acao" value=""/>
					        	<button type="button" name="acao" value="excluir" 
					        		onclick="confirmaExclusao($('#cadastro_viajante'), 'Deseja realmente realizar esta exclusão?', '#hidden_excluir', 'Excluir')">Excluir Cadastro</button>
					        </div>
					    </c:if>
					</form>
					<form id="contato" class="formulario_invisivel" action="<c:url value="/Pesquisa"></c:url>" method="post">
						<input type="hidden" name="acao" value="contatos"/>
						<input type="hidden" name="criterio" value="${viajanteBean.viajante.idUsuario}"/>
					</form>
				</div>
				<script type="text/javascript">
					$(document).ready(function() { 
						populaDropDownAno($('#dataNascimentoAno'), $('#dataNascimento'));
						populaDropDownMes($('#dataNascimentoMes'), $('#dataNascimento')); 
						populaDropDownDia($('#dataNascimentoDia'), $('#dataNascimentoMes').val(), $('#dataNascimento'));
						calculaIdade($('#idade'));
					});
				</script>			
			</div>
			<c:if test="${viajanteBean.viajante.idUsuario != usuarioBean.usuario.idUsuario}">
				<br/>
				<c:set var="perfilOutro" value="true"/>
				<%@include file="../atualizacao/atualizacao.jsp"%>
			</c:if>
		</div>
		<%@include file="../adicional.jsp"%>
	</body>
</html>