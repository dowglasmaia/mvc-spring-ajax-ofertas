//funcção para capturar as meta tags
$("#linkPromocao").on('change', function () {

  // pegando os dados do input do formulario da pagina html
  var url = $(this).val();

  if (url.length > 7) {	  
	  $.ajax({
		  method:"POST",
		  url:"/meta/info?url=" + url,
		  cache: false,
		  //antes  de qualquer requisição
		  beforeSend: function(){
			  $("#alert").removeClass("alert alert-danger").text(" ");//removendo msg de error
			  $("#titulo").val(""); // limpa o campo de titulo
			  $("#site").text(""); // limpa o campo  onde estar o nome do dite
			  $("#linkImagem").attr("src"," "); // limpa o campo onde estar o nome da imagem
			  $("#loader-img").addClass("loader");
		  },		  
		  success: function( data ){
			  console.log(data);
			  $("#titulo").val(data.title);
			  $("#site").text(data.site.replace("@",""));			 
			  $("#linkImagem").attr("src",data.image);	
			},		  
		  statusCode: {
			  /*criando messagem de resposta de Error*/
			  404: function(){
				  $("#alert")
				  .addClass("alert alert-danger")
				  .text( "Nenhuma informação pode ser recuperada dessa url!");
				  $("#linkImagem").attr("src","/images/promo-dark.png "); // limpa o campo onde estar o nome da imagem
			},
			  500: function(){
				  $("#alert")
				  .addClass("alert alert-danger")
				  .text( "Url inválida");
				  $("#linkImagem").attr("src","/images/promo-dark.png "); // limpa o campo onde estar o nome da imagem
				 }	
		  },
		  /*Erros genericos*/
		  error: function(){
			  $("#alert")
			  .addClass("alert alert-danger")
			  .text( "Ops... algo deu errado, tente novamente mas tarde");
			  $("#linkImagem").attr("src","/images/promo-dark.png "); // limpa o campo onde estar o nome da imagem
			},
			/*apois concluir a operação*/
		 complete: function(){
			 $("#loader-img").removeClass("loader");
		 },
	  })
  }
  
  
  
})