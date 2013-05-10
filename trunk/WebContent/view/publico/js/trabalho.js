/**
 * "Troca" a imagem
 * @param imagem
 * @param sobrepoe
 * @param campo
 */
function trocaImagem(imagem, sobrepoe, campo, url, acao) {
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
function populaDropDownAno(dropDown) { 
	var data = new Date();
	var i = 0;
	var opcao;
	for (i = (data.getFullYear()-100); i <= (data.getFullYear()+20); i++) {
		opcao = new Option(i, i);
		$(opcao).html(i);
		$(dropDown).append(opcao);	
	}
	$(dropDown).val(data.getFullYear());
}

/**
 * Popula o dia atual
 * @param dropDown
 * @param mes
 */
function populaDropDownDia(dropDown, mes) {
	var data = new Date();
	var diaAtual = data.getDay();
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
	$(dropDown).val(pad(diaAtual,2));
}

/**
 * Popula o mes de uma dropdown de mes
 * @param dropDown
 */
function populaDropDownMes(dropDown) { 
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
	$(dropDown).val(pad(data.getMonth(),2));
}


/**
 * Confirma a exclusào de um registro
 */
function confirmaExclusao(form) {
    if (confirm("Deseja realmente excluir este registro?")) {
        $(form).submit();
    }
}

function adicionaCampoImagem(campo, quantidade, acao) {
    
	$(quantidade).val(parseInt($(quantidade).val())+1);
	indice = $(quantidade).val();
	str = "<div id=\"selecionaImagem"+indice+"\" class=\"imagem_bloco\" onclick=\"$('#imagem"+indice+"').click()\">"
	 + "<img id=\"imagemPrevia"+indice+"\" class=\"imagem_edicao\" src=\"/les20131/view/publico/imagens/semimagem.jpg\"/>"
	 + "<span id=\"removerImagem"+indice+"\" class=\"remover sobrepoe\"></span>"
	 + "</div>"
	 + "<input id=\"imagem"+indice+"\" type=\"file\" name=\"imagem"+indice+"\" accept=\"image/x-png, image/jpeg\""
	 + " onchange=\"trocaImagem($('#imagemPrevia"+indice+"'), $('#novaImagem"+indice+"'), $('#imagem"+indice+"'), $(this.form).attr('action'), '"+acao+"')\"/>";

	$(campo).before(str);
	$("#imagem"+indice).click();
}

/**
 * Mostra mensagem na tela
 */
function mostraMensagem(mensagem) {
    if (mensagem != "") {
    	$(document.body).append("<div id=\"dialog\"title=\"Dialog Title\">I'm a dialog</div>");
    	$("#dialog").dialog({ autoOpen: false });
   		$("#dialog").dialog("open");
        //alert(mensagem);
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