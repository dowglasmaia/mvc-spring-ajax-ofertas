package org.maia.mvc.gerenciadorOfertas.services.impl;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.maia.mvc.gerenciadorOfertas.domain.SocialMetaTag;
import org.maia.mvc.gerenciadorOfertas.services.SocialMetaTagServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SocialMetaTagServiceImpl implements SocialMetaTagServices{
	
	private static Logger log = LoggerFactory.getLogger(SocialMetaTagServiceImpl.class);
	
	@Override
	public SocialMetaTag getSocialMetaTag(String url) {					
		SocialMetaTag tagsTwitter  = getTwitterCardByUrl(url);			
		if(!isEmpty(tagsTwitter)) {
			return tagsTwitter;
		}
		
		SocialMetaTag tagsOpenGraph  = getOpenGraphByUrl(url);			
		if(!isEmpty(tagsOpenGraph)) {
			return tagsOpenGraph;
		}	
		
		SocialMetaTag tagsItemprop  = getItempropByUrl(url);			
		if(!isEmpty(tagsItemprop)) {
			return tagsItemprop;
		}
	 return null;
	}
	
		
	//recuperando os dados ta meta tag da pagina html com openGraph	
	private SocialMetaTag getOpenGraphByUrl(String url) {		
		SocialMetaTag tag = new SocialMetaTag();		
		try {
			Document document = Jsoup.connect(url).get(); 		//recuperando os dados ta meta tag da pagina html  ex: https://www.udemy.com/course/dax-e-pbi/
			
			tag.setTitle(document.head().select("meta[property=og:title]").attr("content") ); //<meta property="og:title" content="Power BI &amp; DAX Avançado - Guia Completo para Análises Reais">
			tag.setSite(document.head().select("meta[property=og:]").attr("content") ); //<meta property="og:site_name" content="Udemy">
			tag.setImage(document.head().select("meta[property=og:image]").attr("content") ); //<meta property="og:image" content="https://img-b.udemycdn.com/course/480x270/1491672_846d_10.jpg?secure=0oQrfvUUfzwvuyOR16s5tA%3D%3D%2C1602954423">
			tag.setUrl(document.head().select("meta[property=og:url]").attr("content") ); //<meta property="og:url" content="https://www.udemy.com/course/dax-e-pbi/">
		} catch (IOException e) {			
			//e.printStackTrace();
			log.error(e.getMessage(), e.getCause());
		} 		
		return tag;
	}
	
	
	//recuperando os dados ta meta tag da pagina html com TwitterCard
	private SocialMetaTag getTwitterCardByUrl(String url) {		
		SocialMetaTag tag = new SocialMetaTag();		
		try {
			Document document = Jsoup.connect(url).get(); 		//recuperando os dados ta meta tag da pagina html  ex: https://www.udemy.com/course/dax-e-pbi/
			
			tag.setTitle(document.head().select("meta[name=twitter:title]").attr("content") ); //<meta property="twitter:title" content="Power BI &amp; DAX Avançado - Guia Completo para Análises Reais">
			tag.setSite(document.head().select("meta[name=twitter:site]").attr("content") ); //<meta property="twitter:site_name" content="Udemy">
			tag.setImage(document.head().select("meta[name=twitter:image]").attr("content") ); //<meta property="twitter:image" content="https://img-b.udemycdn.com/course/480x270/1491672_846d_10.jpg?secure=0oQrfvUUfzwvuyOR16s5tA%3D%3D%2C1602954423">
			tag.setUrl(document.head().select("meta[name=twitter:url]").attr("content") ); //<meta property="twitter:url" content="https://www.udemy.com/course/dax-e-pbi/">
		} catch (IOException e) {			
			//e.printStackTrace();
			log.error(e.getMessage(), e.getCause());
		} 		
		return tag;
	}
	
	//recuperando os dados ta meta tag da pagina html com itemprop
	private SocialMetaTag getItempropByUrl(String url) {			
			SocialMetaTag tag = new SocialMetaTag();			
			try {
				Document document = Jsoup.connect(url).get(); 		
				
				tag.setTitle(document.select("meta[itemprop=name]").attr("content") ); //<meta itemprop="name" content="Power BI &amp; DAX Avançado - Guia Completo para Análises Reais">
				tag.setDescricao(document.select("meta[itemprop=description]").attr("content") ); 
				tag.setImage(document.select("meta[itemprop=image]").attr("content") ); 
				tag.setUrl(document.select("meta[itemprop=url]").attr("content") ); 				
			} catch (IOException e) {			
				//e.printStackTrace();
				log.error(e.getMessage(), e.getCause());
			} 		
			return tag;
		}
		
		private boolean isEmpty(SocialMetaTag tag) {			
			 if (tag.getImage().isEmpty() ) return true;
			 if (tag.getTitle().isEmpty() ) return true;
			 if (tag.getUrl().isEmpty() ) return true;
			 if (tag.getSite().isEmpty() ) return true;			
			return false;
		}
		

}
