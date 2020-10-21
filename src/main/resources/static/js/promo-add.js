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
    
});

//submmit do formulario para o cotroller
$("#form-add-promo").submit(function(evt){
	evt.preventDefault();
	
	var promo = {};	
	promo.linkPromocao = $("#linkPromocao").val();
	promo.descricao = $("#descricao").val();
	promo.preco = $("#preco").val();
	promo.site = $("#site").text();
	promo.title = $("#titulo").val(); 
	promo.linkImage = $("#linkImagem").attr("src");  
	promo.categoria = $("#categoria").val(); 
	
	console.log('promo > ', promo);
	
	/*requisição para salvar a promoção via ajax*/
	$.ajax({
		method: "POST",
		url: "/promocao/save",
		data:promo,
			beforeSend: function(){
			  $("#alert").removeClass("alert alert-danger").text(" ");//removendo msg de error
			  $("#alert").removeClass("alert alert-success").text(" ");//removendo msg de error
			},
			success: function(){
				$("#alert").addClass("alert alert-success").text("OK! Promoção cadastrada com sucesso.");
			},
			error: function(xhr){
				console.log("> error: ", xhr.responseText);
				$("#alert").addClass("alert alert-danger").text("Não foi possível salvar esta promoção.");
			}
	});
	
});







