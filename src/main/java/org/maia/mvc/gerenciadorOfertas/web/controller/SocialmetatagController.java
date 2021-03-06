package org.maia.mvc.gerenciadorOfertas.web.controller;

import org.maia.mvc.gerenciadorOfertas.domain.SocialMetaTag;
import org.maia.mvc.gerenciadorOfertas.services.SocialMetaTagServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/meta")
public class SocialmetatagController {

	
	@Autowired
	private SocialMetaTagServices tagService;
	
	@PostMapping("/info")
	public ResponseEntity<SocialMetaTag> obterDadosViaUrl(@RequestParam ("url") String url){
		
		SocialMetaTag obj = tagService.getSocialMetaTag(url);
		
		System.out.println(obj);
		
		return obj != null
				? ResponseEntity.ok(obj)
				: ResponseEntity.notFound().build();
	}
	
}
