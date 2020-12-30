//====== efeito infint scroll ========
$(window).scroll(function(){

    //pegando a posição da barra de rolagem
    var scrollTop = $(this).scrollTop();

    var conteudo = $(document).height() -  $(window).height();

    console.log("ScrollTop ->",scrollTop, '|', 'Conteudo ->', conteudo);

    if(scrollTop >= conteudo){
        console.log(" * * * * * ")
    }

});