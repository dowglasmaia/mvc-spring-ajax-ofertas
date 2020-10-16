package org.maia.mvc.gerenciadorOfertas;

import org.maia.mvc.gerenciadorOfertas.domain.SocialMetaTag;
import org.maia.mvc.gerenciadorOfertas.services.SocialMetaTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MvcSpringAjaxApplication implements CommandLineRunner	{

	public static void main(String[] args) {
		SpringApplication.run(MvcSpringAjaxApplication.class, args);
	}

	@Autowired
	SocialMetaTagService services;
	
	@Override
	public void run(String... args) throws Exception {
		
		SocialMetaTag metaTag = services.getOpenGraphByUrl("https://www.udemy.com/course/dax-e-pbi/");
		
		System.out.println(metaTag);
	}

}
