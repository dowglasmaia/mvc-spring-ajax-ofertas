package org.maia.mvc.gerenciadorOfertas.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String init() {
		return "promo-add";
	}
	
}
