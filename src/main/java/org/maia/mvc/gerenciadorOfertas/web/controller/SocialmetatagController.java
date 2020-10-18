package org.maia.mvc.gerenciadorOfertas.web.controller;

import org.maia.mvc.gerenciadorOfertas.domain.SocialMetaTag;
import org.maia.mvc.gerenciadorOfertas.services.SocialMetaTagService;
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
	private SocialMetaTagService tagService;
	
	@PostMapping("/info")
	public ResponseEntity<SocialMetaTag> obterDadosViaUrl(@RequestParam ("url") String url){
		
		SocialMetaTag obj = tagService.getSocialMetaTag(url);
		return obj != null
				? ResponseEntity.ok(obj)
				: ResponseEntity.notFound().build();
	}
	
}
