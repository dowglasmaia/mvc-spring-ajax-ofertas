var pageNumber = 0;

//====== efeito infint scroll ========
$(window).scroll(function(){

    //pegando a posição da barra de rolagem
    var scrollTop = $(this).scrollTop();

    var conteudo = $(document).height() -  $(window).height();

    console.log("ScrollTop ->",scrollTop, '|', 'Conteudo ->', conteudo);

    if(scrollTop >= conteudo){
       pageNumber ++;

      setTimeout(function(){
        loadByScrollBar(pageNumber);
      }, 200)
    }

});

/* realizando consultar via ajax de acordo com o numero da pagina. */
function loadByScrollBar(pageNumber) {

    $.ajax({
        method:"GET",
        url: "/promocao/list/ajax",
        data: {
            page: pageNumber
        },        
        success: function (response) {
            console.log("Resposta -> ",response)
            
        }
    });
    
}