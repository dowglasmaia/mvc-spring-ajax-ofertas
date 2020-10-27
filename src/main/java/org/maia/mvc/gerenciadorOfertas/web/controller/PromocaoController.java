package org.maia.mvc.gerenciadorOfertas.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.maia.mvc.gerenciadorOfertas.domain.Categoria;
import org.maia.mvc.gerenciadorOfertas.domain.Promocao;
import org.maia.mvc.gerenciadorOfertas.domain.dto.NewPromocaoDTO;
import org.maia.mvc.gerenciadorOfertas.services.CategoriaServices;
import org.maia.mvc.gerenciadorOfertas.services.PromocaoServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/promocao")
public class PromocaoController {

	private static Logger log = LoggerFactory.getLogger(PromocaoController.class);

	@Autowired
	private CategoriaServices services;

	@Autowired
	private PromocaoServices promoServices;

	@ModelAttribute("categorias") // refenciado no template html e atualiza o combobox da pagina
	public List<Categoria> obterCategorias() {
		return services.findAll();

	}

	@GetMapping("/add")
	public String abrirCadastro() {
		return "promo-add";
	}

	@PostMapping("/save")
	public ResponseEntity<?> salvarPromocao(@Valid NewPromocaoDTO promocaoDTO, BindingResult result) {
		
		log.info("Salvando a promocao: Promocao {}", promocaoDTO.toString());

		/* pegando os erros dos campos do objeto */
		if (result.hasErrors()) {
			Map<String, String> errors = new HashMap<>();

			for (FieldError error : result.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage()); // pega o campo e a msg de Error do mesmo.
				log.error("Error de Validação de campo: " + error.getDefaultMessage());
			}

			return ResponseEntity.unprocessableEntity().body(errors);
		}
		promoServices.fromDTO(promocaoDTO);

		return ResponseEntity.ok().build();
	}

}
