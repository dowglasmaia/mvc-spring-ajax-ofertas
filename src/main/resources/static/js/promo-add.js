//funcção para capturar as meta tags
$("#linkPromocao").on('change', function () {

	// pegando os dados do input do formulario da pagina html
	var url = $(this).val();

	if (url.length > 7) {
		$.ajax({
			method: "POST",
			url: "/meta/info?url=" + url,
			cache: false,
			//antes  de qualquer requisição
			beforeSend: function () {
				$("#alert").removeClass("alert alert-danger alert-success").text(" ");//removendo msg de error
				$("#titulo").val(""); // limpa o campo de titulo
				$("#site").text(""); // limpa o campo  onde estar o nome do dite
				$("#linkImagem").attr("src", " "); // limpa o campo onde estar o nome da imagem
				$("#loader-img").addClass("loader");
			},
			success: function (data) {
				console.log(data);
				$("#titulo").val(data.title);
				$("#site").text(data.site.replace("@", ""));
				$("#linkImagem").attr("src", data.image);
			},
			statusCode: {
				/*criando messagem de resposta de Error*/
				404: function () {
					$("#alert")
						.addClass("alert alert-danger")
						.text("Nenhuma informação pode ser recuperada dessa url!");
					$("#linkImagem").attr("src", "/images/promo-dark.png "); // limpa o campo onde estar o nome da imagem
				},
				500: function () {
					$("#alert")
						.addClass("alert alert-danger")
						.text("Url inválida");
					$("#linkImagem").attr("src", "/images/promo-dark.png "); // limpa o campo onde estar o nome da imagem
				}
			},
			/*Erros genericos*/
			error: function () {
				$("#alert")
					.addClass("alert alert-danger")
					.text("Ops... algo deu errado, tente novamente mas tarde");
				$("#linkImagem").attr("src", "/images/promo-dark.png "); // limpa o campo onde estar o nome da imagem
			},
			/*apois concluir a operação*/
			complete: function () {
				$("#loader-img").removeClass("loader");
			},
		})
	}

});

//submmit do formulario para o cotroller
$("#form-add-promo").submit(function (evt) {
	evt.preventDefault();

	var promo = {};
	promo.linkPromocao = $("#linkPromocao").val();
	promo.descricao = $("#descricao").val();
	promo.preco = $("#preco").val();
	promo.site = $("#site").text();
	promo.titulo = $("#titulo").val();
	promo.linkImage = $("#linkImagem").attr("src");
	promo.categoria = $("#categoria").val();

	console.log('promo > ', promo);

	/*requisição para salvar a promoção via ajax*/
	$.ajax({
		method: "POST",
		url: "/promocao/save",
		data: promo,
		beforeSend: function () {
			/*escodendo o formulario e add class de load*/
			$("#form-add-promo").hide();

			//removendo as messagens
			$('span').closest('.error-span').remove();

			//remove as bordas vermelhas dos campos
			$('#categoria').removeClass('is-invalid');
			$('#titulo').removeClass('is-invalid');
			$('#preco').removeClass('is-invalid');
			$('#linkPromocao').removeClass('is-invalid');

			//habilta o load
			$("#loader-form").addClass("loader").show();
			//$("#alert").removeClass("alert alert-danger alert-success ").text(" ");//removendo msg de error
		},
		success: function () {
			/*limpando todos os campos de entrada do formulario497*/
			$("#form-add-promo").each(function () {
				this.reset();
			});

			$("#linkImagem").attr("src", "/images/promo-dark.png ");
			$("#titulo").val("");

			$("#alert")
				.removeClass('alert alert-danger')
				.addClass("alert alert-success")
				.text("OK! Promoção cadastrada com sucesso.");
		},
		/*pegando os erros de validações dos campos e exibindo na Tela*/
		statusCode: {
			422: function(xhr) {
				
				console.log('status error: ' + xhr.status);
				
				var errors = $.parseJSON(xhr.responseText); 
				$.each(errors, function (key, val) {
					$('#' + key).addClass('is-invalid');
					$('#error-' + key)
							.addClass('invalid-feedback')
							.append("<span class='error-span' >" + val + "</span>")
				});
			}
		},
		error: function (xhr) {
			console.log("> error: ", xhr.responseText);
			$("#alert").addClass("alert alert-danger").text("Não foi possível salvar esta promoção.");
		},
		complete: function () {
			/* manipulando efeito na transição*/
			$("#loader-form").fadeOut(800, function () {
				$("#form-add-promo").fadeIn(250); // mostrando formulario suavimente com os DADOS da resposta.
				$("#loader-form").removeClass("loader"); //remove a class com efeito de load CSS
			});
		}
	});

});
