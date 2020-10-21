package org.maia.mvc.gerenciadorOfertas.domain.dto;

import java.math.BigDecimal;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;


import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewPromocaoDTO  {

	

	private String site;
	private String title;	
	private String linkPromocao;
	private String descricao;	
	private String linkImage;		
	private Long categoria;
	
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	private BigDecimal preco;

	
}
