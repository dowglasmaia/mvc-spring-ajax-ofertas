package org.maia.mvc.gerenciadorOfertas.domain;

import java.io.Serializable;


import lombok.*;

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
