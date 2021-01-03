var pageNumber = 0;

//ocultando  elementos quando a pagina e carregada.
$(document).ready(function () {
    $("#loader-img").hide();
    $("#fim-btn").hide();
});

//====== efeito infint scroll ========
$(window).scroll(function () {

    //pegando a posição da barra de rolagem
    var scrollTop = $(this).scrollTop();

    var conteudo = $(document).height() - $(window).height();

    // console.log("ScrollTop ->",scrollTop, '|', 'Conteudo ->', conteudo);

    if (scrollTop >= conteudo) {
        pageNumber++;
        console.log(pageNumber);

        setTimeout(function () {
            loadByScrollBar(pageNumber);
        }, 200)
    }

});

/* realizando consultar via ajax de acordo com o numero da pagina. */
function loadByScrollBar(pageNumber) {
    $.ajax({
        method: "GET",
        url: "/promocao/list/ajax",
        data: {
            page: pageNumber
        },

        beforeSend: function () {
            $("#loader-img").show();
        },

        success: function (response) {
            // console.log("Resposta -> ",response)
            if (response.length > 150) {

                $(".row").fadeIn(250, function () {
                    $(this).append(response);
                });
            } else {
                $("#fim-btn").show();
                $("#loader-img").removeClass("loader");
            }

        },
        error: function (xhr) {
            alert("Ops!, ocorreu um error: " + xhr.status + " - " + xhr.statusText);
        },

        complete: function () {
            $("#loader-img").hide();
        },

    });
}

//adicioando Likes 
//$("button[id*='likes-btn-']").on("click", function () {
$(document).on("click", "button[id*='likes-btn-']", function () {
    var id = $(this).attr("id").split("-")[2];
    console.log("o ID é > " + id)

    //enviando requisição da curtida via ajax, com o valor do id do butão:
    $.ajax({
        method: "POST",
        url: "/promocao/like/" + id,
        success: function (response) {
            $("#likes-count-" + id).text(response);
        },
        error: function (xhr) {
            alert("Ops, ocerreu um error: " + xhr.status + ", " + xhr.statusText);
        }
    }); //  fim Ajax
});

//Autocomplete do Jquery,  retornando os valoes da consulta na base de dados,  com base no que for digitado no campo input.
$("#autocomplete-input").autocomplete({
    source: function (request, response) {
        $.ajax({
            method: "GET",
            url: "/promocao/site",
            data: {
                termo: request.term
            },
            success: function (result) {
                response(result);
            }
        });
    }
});

//listar promoções pelo nome do site, removndo os cards da pagina antes de preencher com os novos, con efitos de entrada e saida
$("#autocomplete-submit").click(function () {
    var site = $("#autocomplete-input").val();
    $.ajax({
        method: "GET",
        url: "/promocao/site/list",
        data: {
            site: site, // parametro no corpo da requisição
        },

        beforeSend: function () {
            pageNumber = 0,
            $("#fim-btn").hide();
            $(".row").fadeOut(400, function () {
                $(this).empty(); // remove os cardes da tela
            });
        },

        success: function (response) {
            $(".row").fadeIn(250, function () {
                $(this).append(response); // preenche a tela com o novos cards com valores da resposta do servidor.
            });
        },
        error: function(xhr){
            alert("Ops, Algo deu errado: "+ xhr.status + ", "+ xhr.statusText);
        }
    });

});

/*
$("#autocomplete-submit").click(function () {
    var site = $("#autocomplete-input").val();
    console.log("o Site é:> " + site);

    $.ajax({
        method: "POST",
        url: "/promocao/site/" + site,

        success: function () {

        }
    });
});
*/

