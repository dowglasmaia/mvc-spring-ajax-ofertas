package org.maia.mvc.gerenciadorOfertas.services;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.maia.mvc.gerenciadorOfertas.domain.SocialMetaTag;
import org.springframework.stereotype.Service;

@Service
public class SocialMetaTagService {
	
	//recuperando os dados ta meta tag da pagina html
	public SocialMetaTag getOpenGraphByUrl(String url) {
		SocialMetaTag tag = new SocialMetaTag();
		
		try {
			Document document = Jsoup.connect(url).get(); 		//recuperando os dados ta meta tag da pagina html  ex: https://www.udemy.com/course/dax-e-pbi/
			
			tag.setTitleString(document.head().select("meta[property=og:title]").attr("content") ); //<meta property="og:title" content="Power BI &amp; DAX Avançado - Guia Completo para Análises Reais">
			tag.setSiteString(document.head().select("meta[property=og:site]").attr("content") ); //<meta property="og:site_name" content="Udemy">
			tag.setImage(document.head().select("meta[property=og:image]").attr("content") ); //<meta property="og:image" content="https://img-b.udemycdn.com/course/480x270/1491672_846d_10.jpg?secure=0oQrfvUUfzwvuyOR16s5tA%3D%3D%2C1602954423">
			tag.setUrl(document.head().select("meta[property=og:url]").attr("content") ); //<meta property="og:url" content="https://www.udemy.com/course/dax-e-pbi/">
					
			
		} catch (IOException e) {			
			e.printStackTrace();
		} 		
		return tag;
	}

}
