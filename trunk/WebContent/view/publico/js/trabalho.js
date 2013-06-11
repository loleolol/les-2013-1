$(function() {
	if ($(".aba").length > 0) {
		jQuery.each($(".aba"), function(i, aba) {
		    $(aba).tabs();
		});			
	}
});

function pesquisar(campo, retorno, botao) {
	$.ajax({
	    url: '/les20131/Pesquisa',
	    data: {acao: "pesquisarPrevia", criterio: $(campo).val()},
	    type: 'POST',
	    success: function(data) {
    		$(retorno).empty();
	    	if (data != "" && data.lista.length > 0) {
	    		$(retorno).attr("class", "retorno_pesquisa");
		    	$(retorno).width($(campo).width());
		    	var i = 0;
		    	var quantidade = (data.lista.length < 3 ? data.lista.length : 3);
		    	var aux = 15;
		    	for (i = 0; i < quantidade; i++) {
			    	var string = "<form id=\"resultado"+i+"\" method=\"post\" action=\"/les20131/"+data.lista[i].tipo+"\">"
			    		+"<div id=\"itemRetornoPesquisaPrevia"+i+"\" class=\"item_retorno_pesquisa\">"
			    		+"<div class=\"parte_bloco\" onclick=\"$('#resultado"+i+"').submit()\">"
			    		+"<img id=\"imagemPreviaPesquisa"+i+"\" class=\"imagem_barra\"/>"
			    		+"</div><div class=\"parte_bloco informacao\" onclick=\"$('#resultado"+i+"').submit()\">"
			    		+"<span class=\"titulo\">"+data.lista[i].identificacao+"</span><br/>"
			    		+"<span>"+data.lista[i].previa+"</span><br/>"
			    		+"<input type=\"hidden\" name=\"acao\" value=\"selecionar\"/>"
						+"<input type=\"hidden\" name=\"id\" value=\""+data.lista[i].id+"\"/>"
			    		+"</div>"
		    			+"<div class=\"parte_bloco\">";
			    	if (data.idUsuario != data.lista[i].id && data.lista[i].tipo == 'Viajante' && data.empresa == 'false') {
				    	if (data.lista[i].flag) {
				    		string += "<button type=\"button\" onclick=\"removerContato($(this), "+data.lista[i].id+")\">"
			    				+"<span class=\"excluir\"></span>";
				    	} else {
				    		string += "<button type=\"button\" onclick=\"adicionarContato($(this), "+data.lista[i].id+")\">"
				    			+"<span class=\"incluir\"></span>";
				    	}
			    		string += "<span>Contato</span>"
		    				+"</button>";
			    	}
			    	string += "</div></div>";
			    	if (i == 2 && data.lista.length > 3) {
			    		string += "<div class=\"item_retorno_pesquisa titulo\" onclick=\""
			    			+"$('#"+$(botao).attr("id")+"').click()"
			    			+"\">Para mais resultados, clique aqui</div></form>";
			    		aux = 19;
			    	}
			    	$(retorno).append(string);
			    	urlCampo = "/les20131/"+data.lista[i].tipo+"?acao=carregarImagem&id="+data.lista[i].id;
			    	(function(index, link) {
				        setTimeout(function() { carregarImagem($('#imagemPreviaPesquisa'+index), link); }, (index*200));
				    })(i, urlCampo);			    	
		    	}
		    	if (quantidade > 0) {
		    		$(retorno).height(($('#itemRetornoPesquisaPrevia0').height()*i)+(aux*i));
		    	}
		    } else {
	    		$(retorno).attr("class", "retorno_pesquisa invisivel");
	    	}
	    }
	});
}

function avaliarEmpresa(estrela, campo) {
	jQuery.each($('.estrela'), function(i, element) {
		var valor = $(estrela).attr("title");
		if (i < valor) {
			$(element).attr("class", "estrela estrela_marcada");
		} else {
			$(element).attr("class", "estrela estrela_nao_marcada");
		}
	});
	$(campo).val($(estrela).attr("title"));
}

function carregarAvaliacaoEmpresa(campo) {
	jQuery.each($('.estrela'), function(i, element) {
		var valor =	$(campo).val();
		if (i < valor) {
			$(element).attr("class", "estrela estrela_marcada");
		} else {
			$(element).attr("class", "estrela estrela_nao_marcada");
		}
	});
}

function carregarAvaliacoes(campos) {
	jQuery.each($('[name='+campos+']'), function(i, element) {
		var valor = $(element).val();
		var indice;
		for (indice = 1; indice <= valor; indice++) {
			$(element).before("<div class=\"estrela_menor estrela_menor_marcada\"></div>");
		}
		for (indice = valor; indice < 5; indice++) {
			$(element).before("<div class=\"estrela_menor estrela_menor_nao_marcada\"></div>");
		}
	});	
}

function adicionarContato(campo, id) {
	$(campo).attr("class", "carregando");
	$.ajax({
	    url: '/les20131/Contato',
	    data: {acao: "incluir", idUsuario: id},
	    type: 'POST',
	    success: function(data) {
	    	$(campo).attr("class", "");
	    	$(campo).html("<span class=\"excluir\"></span><span>Contato</span>");
    		$(campo).attr("onclick", "removerContato($(this), "+id+")");
	    }
	});
}

function removerContato(campo, id) {
	$(campo).attr("class", "carregando");
	
	$(campo).before("<div id=\""+$(campo).attr("id")+"dialog-confirm\" title=\"Confirma remoção?\">"
			+"<p><span class=\"ui-icon ui-icon-alert\" style=\"float: left; margin: 0 7px 20px 0;\"></span>"
			+"Deseja realmente remover este contato?</p>"
			+"</div>");
	$("#"+$(campo).attr("id")+"dialog-confirm").dialog({
		resizable: false,
		height: 200,
		width: 300,
		modal: true,
		buttons: {
	  "Sim": function() {
	    $(this).dialog("close");
		$.ajax({
		    url: '/les20131/Contato',
		    data: {acao: "excluir", idUsuario: id},
		    type: 'POST',
		    success: function(data) {
		    	$(campo).attr("class", "");
	    		$(campo).html("<span class=\"incluir\"></span><span>Contato</span>");
	    		$(campo).attr("onclick", "adicionarContato($(this), "+id+")");
		    }
		});
	  },
	  "Não": function() {
	    $(this).dialog( "close" );
	      }
	    }
	  });	
}

function bloquearUsuario(campo, id) {
	$(campo).attr("class", "carregando");
	
	$(campo).before("<div id=\""+$(campo).attr("id")+"dialog-confirm\" title=\"Confirma bloqueio?\">"
			+"<p><span class=\"ui-icon ui-icon-alert\" style=\"float: left; margin: 0 7px 20px 0;\"></span>"
			+"Deseja realmente bloquear este usuário?</p>"
			+"</div>");
	$("#"+$(campo).attr("id")+"dialog-confirm").dialog({
		resizable: false,
		height: 200,
		width: 300,
		modal: true,
		buttons: {
	  "Sim": function() {
	    $(this).dialog("close");
		$.ajax({
		    url: '/les20131/Usuario',
		    data: {acao: "bloquear", idUsuario: id},
		    type: 'POST',
		    success: function(data) {
		    	$(campo).attr("class", "");
	    		$(campo).html("<span>Desbloquear</span>");
	    		$(campo).attr("onclick", "desbloquearUsuario($(this), "+id+")");
		    }
		});
	  },
	  "Não": function() {
	    $(this).dialog( "close" );
	      }
	    }
	  });	
}

function desbloquearUsuario(campo, id) {
	$(campo).attr("class", "carregando");
	
	$(campo).before("<div id=\""+$(campo).attr("id")+"dialog-confirm\" title=\"Confirma desbloqueio?\">"
			+"<p><span class=\"ui-icon ui-icon-alert\" style=\"float: left; margin: 0 7px 20px 0;\"></span>"
			+"Deseja realmente desbloquear este usuário?</p>"
			+"</div>");
	$("#"+$(campo).attr("id")+"dialog-confirm").dialog({
		resizable: false,
		height: 200,
		width: 300,
		modal: true,
		buttons: {
	  "Sim": function() {
	    $(this).dialog("close");
		$.ajax({
		    url: '/les20131/Usuario',
		    data: {acao: "desbloquear", idUsuario: id},
		    type: 'POST',
		    success: function(data) {
		    	$(campo).attr("class", "");
	    		$(campo).html("<span>Bloquear</span>");
	    		$(campo).attr("onclick", "bloquearUsuario($(this), "+id+")");
		    }
		});
	  },
	  "Não": function() {
	    $(this).dialog( "close" );
	      }
	    }
	  });	
}

function excluirUsuario(container, campo, id) {
	$(campo).attr("class", "carregando");
	
	$(campo).before("<div id=\""+$(campo).attr("id")+"dialog-confirm\" title=\"Confirma exclusão?\">"
			+"<p><span class=\"ui-icon ui-icon-alert\" style=\"float: left; margin: 0 7px 20px 0;\"></span>"
			+"Deseja realmente excluir este usuário?</p>"
			+"</div>");
	$("#"+$(campo).attr("id")+"dialog-confirm").dialog({
		resizable: false,
		height: 200,
		width: 300,
		modal: true,
		buttons: {
	  "Sim": function() {
	    $(this).dialog("close");
		$.ajax({
		    url: '/les20131/Usuario',
		    data: {acao: "excluir", idUsuario: id},
		    type: 'POST',
		    success: function(data) {
	    		$(container).remove();
		    }
		});
	  },
	  "Não": function() {
	    $(this).dialog( "close" );
	      }
	    }
	  });	
}

/**
 * "Troca" a imagem
 * @param imagem
 * @param campo
 */
function trocaImagem(imagem, campo, url, acao) {
	$(imagem).attr("src", "/les20131/view/publico/imagens/carregando.gif");
	urlImagem = url+"?acao="+acao+"&nome="+$(campo).attr("id");
	var data = new FormData();
	jQuery.each($(campo)[0].files, function(i, file) {
	    data.append($(campo).attr("id"), file);
	});	
	data.append("nome", $(campo).attr("id"));
	data.append("acao", acao);
	$.ajax({
	    url: url,
	    data: data,
	    cache: false,
	    contentType: false,
	    processData: false,
	    type: 'POST',
	    success: function(data){
	        $(imagem).attr("src", urlImagem);
	    }
	});
	
}

function removerValidacao(form) {
	$(form).attr("onsubmit", "");
}

function enviarSemValidacao(form) {
	removerValidacao(form);
	$(form).submit();
}

function atalhoPerfil(botao, tipo) {
	$('#idAtalho').val($(botao).val());
	$('#atalho').attr("action", $('#atalho').attr("action")+tipo);
	$('#atalho').submit();
}

function enviarFormulario(form) {
	$('#'+form).submit();
}

function pad(str, max) {
	  return str.length < max ? pad("0" + str, max) : str;
}

/**
 * Verifica se o valor dos campos é igual
 * @param campo
 * @param aceitaNulo
 * @returns {Boolean}
 */
function verificarValorIgualCampos(campo) {
	var campo1 = campo;
	var campo2 = campo1+'Confirma';
	if ($('#'+campo1).val() == $('#'+campo2).val()) {
		return true;
	}
	escreveCampoErro(campo, "Valores diferentes");
	escreveCampoErro(campo+'Confirma', "Valores diferentes");
	return false;
}

/**
 * Popula o ano de uma dropdown de ano
 * @param dropDown
 */
function populaDropDownAno(dropDown, campoData) { 
	if ($(dropDown) != undefined) {
		var data = new Date();
		var i = 0;
		var opcao;
		for (i = (data.getFullYear()-100); i <= (data.getFullYear()+20); i++) {
			opcao = new Option(i, i);
			$(opcao).html(i);
			$(dropDown).append(opcao);	
		}
		if ($(campoData) == undefined) {
			if ($(campoData).val() != undefined) {
				$(dropDown).val($(campoData).val().split("-")[0]);
			} else {
				$(dropDown).val(data.getFullYear());
			}
		} else {
			$(dropDown).val(data.getFullYear());
		}
	}
}

/**
 * Popula o dia atual
 * @param dropDown
 * @param mes
 */
function populaDropDownDia(dropDown, mes, campoData) {
	if ($(dropDown) != undefined) {
		var data = new Date();
		var diaAtual = data.getDate();
		data = new Date(data.getFullYear(), mes, 0);
		var opcao;
		$(dropDown).html("");
		var i = 0;
		var s;
		for (i = 1; i <= data.getDate(); i++) {
			s = i.toString();
			opcao = new Option(pad(s,2), pad(s,2));
			$(opcao).html(pad(s,2));
			$(dropDown).append(opcao);
		}
		
		if ($(campoData) != undefined) {
			if ($(campoData).val() != undefined) {
				$(dropDown).val($(campoData).val().split("-")[2]);
			} else {
				$(dropDown).val(pad(diaAtual,2));
			}
		} else {
			$(dropDown).val(pad(diaAtual,2));
		}
	}		
}

/**
 * Popula o mes de uma dropdown de mes
 * @param dropDown
 */
function populaDropDownMes(dropDown, campoData) {
	if ($(dropDown) != undefined) {
		var data = new Date();
		var opcao;
		opcao = new Option("Janeiro", "01");
		$(opcao).html("Janeiro");
		$(dropDown).append(opcao);	
		opcao = new Option("Fevereiro", "02");
		$(opcao).html("Fevereiro");
		$(dropDown).append(opcao);	
		opcao = new Option("Março", "03");
		$(opcao).html("Março");
		$(dropDown).append(opcao);	
		opcao = new Option("Abril", "04");
		$(opcao).html("Abril");
		$(dropDown).append(opcao);	
		opcao = new Option("Maio", "05");
		$(opcao).html("Maio");
		$(dropDown).append(opcao);	
		opcao = new Option("Junho", "06");
		$(opcao).html("Junho");
		$(dropDown).append(opcao);	
		opcao = new Option("Julho", "07");
		$(opcao).html("Julho");
		$(dropDown).append(opcao);	
		opcao = new Option("Agosto", "08");
		$(opcao).html("Agosto");
		$(dropDown).append(opcao);	
		opcao = new Option("Setembro", "09");
		$(opcao).html("Setembro");
		$(dropDown).append(opcao);	
		opcao = new Option("Outubro", "10");
		$(opcao).html("Outubro");
		$(dropDown).append(opcao);	
		opcao = new Option("Novembro", "11");
		$(opcao).html("Novembro");
		$(dropDown).append(opcao);	
		opcao = new Option("Dezembro", "12");
		$(opcao).html("Dezembro");
		$(dropDown).append(opcao);	
		var mes = (data.getMonth()+1 < 10 ? "0"+(data.getMonth()+1) : (data.getMonth()+1));
		if ($(campoData) != undefined) {
			if ($(campoData).val() != undefined) {
				$(dropDown).val($(campoData).val().split("-")[1]);
			} else {
				$(dropDown).val(mes);
			}
		} else {
			$(dropDown).val(mes);
		}
	}			
}


/**
 * Confirma a exclusão de um registro
 */
function confirmaExclusao(form, msg, campo, acao) {
	$(form).before("<div id=\""+$(form).attr("id")+"dialog-confirm\" title=\"Confirma exclusão?\">"
			+"<p><span class=\"ui-icon ui-icon-alert\" style=\"float: left; margin: 0 7px 20px 0;\"></span>"
			+msg+"</p>"
			+"</div>");
	$("#"+$(form).attr("id")+"dialog-confirm").dialog({
		resizable: false,
		height: 200,
		width: 300,
		modal: true,
		buttons: {
	  "Sim": function() {
	    $(this).dialog("close");
	    $(campo).val(acao);
	    $(form).submit();
	  },
	  "Não": function() {
	    $(this).dialog("close");
	      }
	    }
	  });
}

function removerImagem(campo1, campo2, quantidade, id, imagem, indice) {
	if (id != undefined) {
		$(quantidade).before("<input id=\""+id+indice+"\" type=\"hidden\" name=\""+id+indice+"\" value=\""+$('#'+imagem+indice).attr("identif")+"\"/>");
	}
	$(campo1).remove();
	$(campo2).remove();
}

function carregarImagemPerfis(img, quantidade, form, acao) {
	var i = 1;
	for (i = 1; i <= quantidade; i++) {
		var imagem = '#'+img+i;
		var formulario = '#'+form+i;
		$(imagem).attr("alt", $(formulario).attr("action")+"?acao="+acao+"&id="+$(imagem).attr("alt"));
	}
}

function carregarGaleriaEdicao(campo, quantidade, acao, seleciona, imagem, remove, input, url, id, acaoCarregar) {
	var quant = $(quantidade).val();
	$(quantidade).val(0);
	var indice;
	var urlCampo;
	var t;
	for (indice = 1; indice <= quant; indice++) {
		t = '#'+id+indice;
		urlCampo = url+"?acao="+acaoCarregar+"&id="+$(t).val();
		adicionaCampoImagem(campo, quantidade, acao, seleciona, imagem, remove, input, id);
		$('#imagemPrevia'+indice).attr("onclick", "");
		$('#imagemPrevia'+indice).attr("alt", urlCampo);
		$('#imagemPrevia'+indice).attr("identif", $(t).val());
		$(t).remove();
	}
}

function carregarImagem(imagem, url) {
	if ($(imagem).length > 0) {
		if (url.length > 0) {
			$(imagem).attr("src", url+"&nan="+Math.round(new Date().getTime() / 1000));
		} else {
			$(imagem).attr("src", "/les20131/view/publico/imagens/semimagem.png");
		}
		$(imagem).attr("alt", "");
	}
}

function carregarImagens() {
	jQuery.each($('img'), function(i, element) {
		$(element).attr("src", "/les20131/view/publico/imagens/carregando.gif");
		setTimeout(function() { carregarImagem($(element), $(element).attr("alt")); }, (i*800));
	});
}

function slideImagensInicio() {
	if ($("#imagem_inicio").length > 0) {
		setTimeout(function() {
			$("#imagem_inicio").effect("fade", {}, 500, 
					function() {
						$("#imagem_inicio").removeAttr( "style" ).hide().fadeIn();
						$('#imagem_inicio').attr("src", "/les20131/view/publico/imagens/paris.jpg");
					}
				);
			setTimeout(function() {
				$("#imagem_inicio").effect("fade", {}, 500,
					function() {
						$("#imagem_inicio").removeAttr( "style" ).hide().fadeIn();
						$('#imagem_inicio').attr("src", "/les20131/view/publico/imagens/dawn.jpg");
					}
				);
				setTimeout(function() {
					$("#imagem_inicio").effect("fade", {}, 500,
						function() {
							$("#imagem_inicio").removeAttr( "style" ).hide().fadeIn();
							$('#imagem_inicio').attr("src", "/les20131/view/publico/imagens/park.jpg");
							slideImagensInicio();
						}
					);
				}, (15000));
			}, (10000));
		}, (5000));
	}
}

function carregarGaleria(id, imagem, url, form) {
	jQuery.each($('[name='+id+']'), function(i, element) {
		var indice = (i+1);
		var img = '#'+imagem+indice;
		if ($(img) != undefined && $(img).length > 0) {
			if ($('#'+url+indice+'2').length > 0) {
				$('#barraFerramentas'+indice).append("<a class=\"proximo\" href=\"javascript:void(0)\" title=\"Próximo\""
						+ " onclick=\"navegarGaleria($('#"+imagem+indice+"'), '"+url+indice+"', false)\"></a>");
				$('#barraFerramentas'+indice).append("<a class=\"anterior\" href=\"javascript:void(0)\" title=\"Anterior\""
						+ " onclick=\"navegarGaleria($('#"+imagem+indice+"'), '"+url+indice+"', true)\"></a>");
			}
			$(img).attr("alt", $('#'+url+indice+'1').val());
		}
	});
}

function navegarGaleria(imagem, url, reverso) {
	var achou = false;
	var i = 0, j = 0;
	var elemento;
	var quantidade = $('[name='+url+']').length;
	$(imagem).attr("src", $(imagem).attr("src").split("&nan")[0]);
	if (reverso == false) {
		for(i = 0; i < quantidade; i++) {
			if (achou == false) {
				elemento = $('[name='+url+']')[i];
				if ($(elemento).val() == $(imagem).attr("src")) {
					achou = true;
					j = i;
				}
			}
		}
		if (achou == true && j < (quantidade-1)) {
			elemento = $('[name='+url+']')[j+1];
			$(imagem).effect("fade", {}, 500,
				function() {
					$(imagem).removeAttr( "style" ).hide().fadeIn();
					carregarImagem($(imagem), $(elemento).val());
				}
			);
		} else {
			if (quantidade > 0) {
				elemento = $('[name='+url+']')[0];
				$(imagem).effect("fade", {}, 500,
					function() {
						$(imagem).removeAttr( "style" ).hide().fadeIn();
						carregarImagem($(imagem), $(elemento).val());
					}
				);
			}
		}
	} else {
		for(i = (quantidade-1); i >= 0; i--) {
			if (achou == false) {
				elemento = $('[name='+url+']')[i];
				if ($(elemento).val() == $(imagem).attr("src")) {
					achou = true;
					j = i;
				}
			}
		}	
		if (achou == true && j >= 1) {
			elemento = $('[name='+url+']')[j-1];
			$(imagem).effect("fade", {}, 500,
				function() {
					$(imagem).removeAttr( "style" ).hide().fadeIn();
					carregarImagem($(imagem), $(elemento).val());
				}
			);

		} else {
			if (quantidade > 0) {
				elemento = $('[name='+url+']')[(quantidade-1)];
				$(imagem).effect("fade", {}, 500,
					function() {
						$(imagem).removeAttr( "style" ).hide().fadeIn();
						carregarImagem($(imagem), $(elemento).val());
					}
				);
			}
		}
	}
	
}

function adicionaCampoImagem(campo, quantidade, acao, seleciona, imagem, remove, input, id) {
	$(quantidade).val(parseInt($(quantidade).val())+1);
	var indice = $(quantidade).val();
	var str = "<div id=\""+seleciona+indice+"\" class=\"imagem_bloco\">"
	 	+ "<img id=\""+imagem+indice+"\" class=\"imagem\" src=\"/les20131/view/publico/imagens/semimagem.png\" onclick=\"$('#"+input+indice+"').click()\"/>"
	 	+ "<span id=\""+remove+indice+"\" class=\"remover sobrepoe\" title=\"Remover\" onclick=\"removerImagem($('#"+seleciona+indice+"'), $('#"+input+indice+"'), $('#"+$(quantidade).attr("id")+"')";
	if (id != undefined) {
		str += ", '"+id+"', '"+imagem+"', "+indice;
	}
	str += ")\"></span></div>"
		+ "<input id=\""+input+indice+"\" type=\"file\" name=\""+input+indice+"\" accept=\"image/x-png, image/jpeg\""
		+ " onchange=\"trocaImagem($('#"+imagem+indice+"'), $('#"+input+indice+"'), $(this.form).attr('action'), '"+acao+"')\"/>";

	$(campo).before(str);
	if (id == undefined) {
		$("#"+input+indice).click();
	}
}

function calculaIdade(campo) {
	var vData = ($(campo).text()).split("-");
	var data = new Date();
	data.setFullYear(parseInt(vData[0]), parseInt(vData[1])-1, parseInt(vData[2]))
	var s = ~~((Date.now() - data) / (31557600000));
	$(campo).text(s);	
}

/**
 * Mostra mensagem na tela
 */
function mostraMensagem(elemento, mensagem) {
    if (mensagem != "") {
    	$('#'+elemento).append("<div id=\""+elemento+"dialog\" title=\"Aviso\">"
    			+"<p><span class=\"ui-icon ui-icon-alert\" style=\"float: left; margin: 0 7px 20px 0;\"></span>"
    			+mensagem+"</p>"
    			+"</div>");
    	$("#"+elemento+"dialog").dialog({
    		resizable: false,
    		height: 200,
    		width: 400,
    		modal: true,
    		buttons: {
    			  "Ok": function() {
    			    $(this).dialog("close");
    			  }
    		}
    	  });
    }
}

/**
 * Remove caracteres em branco
 * utilizando uma expressào regular
 * que identifica caracteres à  esquerda
 * e à  direita da string
 */
function trim(strValor) {
    return strValor.replace(/^\s+|\s+$/g,"");
}

/**
 * Validação de campo deixado em branco
 */
function validaCampoBranco(strValor)
{
    if (strValor.length > 0) {
        return true;
    }//if (strValor.length > 0)
    return false;
}//function validaCampoBranco(strValor)

/**
 * Valida o email
 * @param email
 * @returns
 */
function validaEmail(email) { 
    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    if (re.test(email) == true) {
    	return "";
    }
    return "E-mail inválido";
} 

/**
 * Validação de tipo inteiro
 * Verifica se o parâmetro é um número e entào compara se o resultado da conversào para
 * inteiro é identico ao original. Caso seja diferente, o valor nào era um inteiro
 */
function validaInteiro(strValor)
{
    if (!(isNaN(strValor))) {
        return (parseInt(strValor)==strValor);
    }//if (!(isNaN(strValor)))
    return false;
}//function validaInteiro(objElemento)

/**
 * Validação de tipo float
 * Verifica se o parâmetro é um número e entào compara se o resultado da conversào para
 * float é identico ao original. Caso seja diferente, o valor nào era um float
 */
function validaFloat(strValor)
{
    strValor = strValor.replace(".", "");
    strValor = strValor.replace(",", ".");
    if (!(isNaN(strValor))) {
        return (parseFloat(strValor)==strValor);
    }//if (!(isNaN(strValor)))
    return false;
}//function validaFloat(objElemento)

/**
 * Validação de tipo data
 * Verifica se a divisào da string pela barra resulta em três fragmentos
 * para entào instanciar um objeto de data com o padrào americano MM/DD/YYYY
 * Como o javascript é muito flexível na hora de aceitar data inválidas, convertendo
 * para datas válidas, é realizada uma verificação se os dados resultantes sào os mesmos
 * do parâmetro recebido. Caso seja diferente, o valor nào era uma data
 */
function validaData(strValor)
{
    var arrData = new Array();
    arrData     = strValor.split("-");
    if (arrData.length == 3) {
        var objData = new Date(arrData[0], arrData[1]-1, arrData[2]);
        return (arrData[2]==objData.getDate()
             && (arrData[1])==(objData.getMonth()+1)
             && arrData[0]==objData.getFullYear());
    }//if (arrData.length == 3)
    return false;
}//function validaData(strValor)

/**
 * Valida a hora, verificando se o primeiro e o segundo
 * segmentos sào compostos somente de números
 * e se seu alcance está correto
 */
function validaHora(strValor)
{
    var arrHora = new Array();
    arrHora     = strValor.split(":");
    if (arrHora.length == 2) {
        if (!(isNaN(arrHora[0])) && !(isNaN(arrHora[1]))) {
            return (arrHora[0] >= 0 && arrHora[0] <= 23 && arrHora[1] >= 0 && arrHora[1] <= 59);
        }//if (!(isNaN(arrHora[0])) && !(isNaN(arrHora[1]))
        return false;
    }//if (arrHora.length == 2)
    return false;
}//function validaHora(strValor)

/**
 * Valida uma placa de transito
 * Os três primeiros caracteres devem ser nào numéricos
 * Os quatro últimos caracteres devem ser numéricos
 */
function validaPlaca(strValor)
{
    var intIndice = 0;
    for (intIndice = 0; intIndice < strValor.length; intIndice++)
    {
        if ((intIndice < 3 && !(isNaN(strValor.charAt(intIndice))))
        || (intIndice > 4 && isNaN(strValor.charAt(intIndice)))) {
            return "Placa inválida";
        }//if (intIndice < 3 && !(isNaN(strValor.charAt(intIndice))))
    }//for (intIndice = 0; intIndice < strValor.length; intIndice++)
    return "";
}//function validaPlaca(strValor)

/**
 * Valida um CPF
 * Realiza o cálculo do módulo para verifica
 * os dois dígitos verificadores
 */
function validaCpf(strValor)
{
    var intIndice = 0;
    var intTotal  = 0;
    if (strValor == "00000000000" || strValor == "11111111111"
     || strValor == "22222222222" || strValor == "33333333333"
     || strValor == "44444444444" || strValor == "55555555555"
     || strValor == "66666666666" || strValor == "77777777777"
     || strValor == "88888888888" || strValor == "99999999999"){
        return "CPF inválido";
    }//if (strValor == "00000000000" || strValor == "11111111111"...

    for (intIndice = 0; intIndice < 9; intIndice++) {
        intTotal += strValor.charAt(intIndice)*(10 - intIndice);
    }//for (intIndice = 0; intIndice < 9; intIndice++)
    intTotal = intTotal % 11;
    intTotal = (intTotal < 2 ? 0 : 11 - intTotal);
    if (intTotal != strValor.charAt(9)) {
        return "CPF inválido";
    }//if (intTotal != strValor.charAt(9))

    intTotal = 0;
    for (intIndice = 0; intIndice < 10; intIndice++) {
        intTotal += strValor.charAt(intIndice)*(11 - intIndice);
    }//for (intIndice = 0; intIndice < 10; intIndice++)
    intTotal = intTotal % 11;
    intTotal = (intTotal < 2 ? 0 : 11 - intTotal);
    if (intTotal != strValor.charAt(10)) {
        return "CPF inválido";
    }//if (intTotal != strValor.charAt(10))

    return "";
}//function validaCpf(strValor)

/**
 * Valida um formulário com o vetor de campos recebido
 * por parâmetro, contendo os dados do campo
 * separados em nome;tipo;nàonulo;funcaoAdicional
 */
function validaFormulario(arrFormulario)
{
    const intChaveNome = 0, intChaveTipo = 1, intChaveNaoNulo = 2, intChaveValidacaoAdicional = 3;
    var arrCampo       = new Array(3);
    var strValor       = "";
    var intIndice      = 0;
    var boolRetorno    = true;
    var strMensagem    = "";

    for (intIndice = 0; intIndice < arrFormulario.length; intIndice++)
    {
        arrCampo          = arrFormulario[intIndice].split(";");
        strValor          = retornaValorCampo(arrCampo[intChaveNome]);
        limpaCampoErro(arrCampo[intChaveNome]);
        if (validaCampoBranco(strValor)) {
            if (arrCampo[intChaveTipo] == "integer" || arrCampo[intChaveTipo] == "boolean") {
                if (!validaInteiro(strValor)) {
                    escreveCampoErro(arrCampo[intChaveNome], "Valor inválido");
                    boolRetorno = false;
                }//if (validaInteiro(strValor) == false)
            } else if (arrCampo[intChaveTipo] == "float") {
                if (!validaFloat(strValor)) {
                    escreveCampoErro(arrCampo[intChaveNome], "Valor inválido");
                    boolRetorno = false;
                }//if (validaFloat(strValor) == false)
            } else if (arrCampo[intChaveTipo] == "Time") {
                if (!validaHora(strValor)) {
                    escreveCampoErro(arrCampo[intChaveNome], "Hora inválida");
                    boolRetorno = false;
                }//if (!validaHora(strValor))
            } else if (arrCampo[intChaveTipo] == "Date") {
                if (!validaData(strValor)) {
                    escreveCampoErro(arrCampo[intChaveNome], "Data inválida");
                    boolRetorno = false;
                }//if (!validaHora(strValor))
            } else if (arrCampo[intChaveTipo] == "DateTime") {
                var arrValor = strValor.split(" ");
                if (!validaData(arrValor[0])) {
                    escreveCampoErro(arrCampo[intChaveNome], "Data inválida");
                    boolRetorno = false;
                } else if (!validaHora(arrValor[1])) {
                    escreveCampoErro(arrCampo[intChaveNome], "Hora inválida");
                    boolRetorno = false;
                }//if (!validaHora(strValor))
            }//if (arrCampo[intChaveTipo] == "integer")
            if (arrCampo[intChaveValidacaoAdicional] != undefined) {
                strMensagem = eval(arrCampo[intChaveValidacaoAdicional])(strValor)
                if (strMensagem != "") {
                    escreveCampoErro(arrCampo[intChaveNome], strMensagem);
                    boolRetorno = false;
                }//if (!arrCampo[intChaveValidacaoAdicional](strValor))
            }//if (arrCampo[intChaveValidacaoAdicional] != undefined)
        } else {
            if (arrCampo[intChaveNaoNulo] == 1) {
                escreveCampoErro(arrCampo[intChaveNome], "Campo obrigatório");
                boolRetorno = false;
            }//if (!(arrCampo[intChaveNaoNulo] == 1))
        }//if (validaCampoBranco(strValor))
    }//for (intIndice = 0; intIndice < arrFormulario.length; intIndice++)
    return boolRetorno;
}//function validaFormulario(arrCampo)

/**
 * Retorna o valor de um campo dependendo do seu tipo
 */
function retornaValorCampo(strName)
{
    var arrElemento = document.getElementsByName(strName);
    var intIndice   = 0;

    if (arrElemento.length != undefined) {
        for (intIndice = 0; intIndice < arrElemento.length; intIndice++) {
            if (arrElemento[intIndice].tagName.toLowerCase() == "input") {
                if (arrElemento[intIndice].type.toLowerCase() == "radio") {
                    /**
                     * Caso seja um radio button devendo ser navegado até encontrar
                     * o elemento selecionado para obter o valor
                     */
                    if (arrElemento[intIndice].checked) {
                        return arrElemento[intIndice].value;
                    }//if (objElemento[intIndice].checked)
                } else {
                    arrElemento[intIndice].value = trim(arrElemento[intIndice].value);
                    return arrElemento[intIndice].value;
                }//if (arrElemento[intIndice].type == "radio")
            /**
             * Caso o elemento seja um select, deve-se encontrar o valor
             * da opção selecionada através do índice da mesmas
             */
            } else if (arrElemento[intIndice].tagName.toLowerCase() == "select") {
                return arrElemento[intIndice].options[arrElemento[intIndice].selectedIndex].value;
            } else if (arrElemento[intIndice].tagName.toLowerCase() == "textarea") {
                arrElemento[intIndice].value = trim(arrElemento[intIndice].value);
                return arrElemento[intIndice].value;
            }//if (arrElemento[intIndice].tagName == "input")
        }//for (intIndice = 0; intIndice < objElemento.length; intIndice++)
    }//if (arrElemento.length != undefined)
    return "";
}//function retornaValorCampo(strName)

/**
 * Limpar a mensagem de erro da label do campo
 */
function limpaCampoErro(strName)
{
    escreveMensagem(strName+"Erro", "");
}//function limpaCampoErro(strId, strMensagem)

/**
 * Escreve uma mensagem em um campo especificado
 */
function escreveMensagem(strId, strMensagem)
{
    $('#'+strId).html(strMensagem);
}//function escreveMensagem(strId, strMensagem)

/**
 * Escreve o erro na label de erro do campo
 */
function escreveCampoErro(strName, strMensagem)
{
    escreveMensagem(strName+"Erro", strMensagem);
}//function escreveCampoErro(strId, strMensagem)