package org.maia.mvc.gerenciadorOfertas.web.controller;

import java.util.List;

import org.maia.mvc.gerenciadorOfertas.domain.Categoria;
import org.maia.mvc.gerenciadorOfertas.services.CategoriaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/promocao")
public class PromocaoController {

	@Autowired
	private CategoriaServices services;

	@ModelAttribute("categorias") // refenciado no template html e atualiza o combobox da pagina
	public List<Categoria> obterCategorias() {
		return services.findAll();

	}

	@GetMapping("/add")
	public String abrirCadastro() {

		return "promo-add";
	}
	


}
