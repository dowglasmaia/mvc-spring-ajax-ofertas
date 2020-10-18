//funcção para capturar as meta tags
$("#linkPromocao").on('change', function () {

  // pegando os dados do input do formulario da pagina html
  var url = $(this).val();

  if (url.length > 7) {	  
	  $.ajax({
		  method:"POST",
		  url:"/meta/info?url=" + url,
		  cache: false,
		  success: function( data ){
			  console.log(data);
			  $("#titulo").val(data.title);
			  $("#site").text(data.site.replace("@",""));
			  $("#linkImagem").attr("src",data.image);			  
		  },
		  statusCode: {
			  /*criando messagem de resposta de Error*/
			  404: function(){
				  $("#alert").addClass("alert alert-danger").text(
						  "Nenhuma informação pode ser recuperada dessa url!");
			  }
		  }
	  })
  }
  
  
  
})