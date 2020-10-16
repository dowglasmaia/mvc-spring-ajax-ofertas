package org.maia.mvc.gerenciadorOfertas.domain;

import java.io.Serializable;

import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SocialMetaTag implements Serializable {

	private String site;
	private String title;
	private String descricao;
	private String url;
	private String image;

}
