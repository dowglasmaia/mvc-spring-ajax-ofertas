package org.maia.mvc.gerenciadorOfertas.web.controller;

import java.util.List;

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
	public ResponseEntity<Promocao>salvarPromocao(NewPromocaoDTO promocaoDTO){
		log.info("Salvando a promocao: Promocao {}", promocaoDTO.toString() );
		
		 promoServices.fromDTO(promocaoDTO);	
		
		return ResponseEntity.ok().build();
	}
	

}
