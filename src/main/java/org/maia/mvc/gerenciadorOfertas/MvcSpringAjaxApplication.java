package org.maia.mvc.gerenciadorOfertas;

import org.maia.mvc.gerenciadorOfertas.services.SocialMetaTagServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MvcSpringAjaxApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MvcSpringAjaxApplication.class, args);
	}

	@Autowired
	SocialMetaTagServices services;

	@Override
	public void run(String... args) throws Exception {

		//SocialMetaTag metaTag = services.getSocialMetaTag("https://www.udemy.com/course/dax-e-pbi/");

		//System.out.println(metaTag);
		
		/*
		SocialMetaTag metaTagByTwitter = services.getSocialMetaTag("https://www.udemy.com/course/dax-e-pbi/");

		System.out.println(metaTagByTwitter);

		SocialMetaTag metaTagItemprop = services.getSocialMetaTag("https://www.udemy.com/course/dax-e-pbi/");

		System.out.println(metaTagItemprop);*/ 
		
	}

}
