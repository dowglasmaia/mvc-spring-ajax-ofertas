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