package org.maia.mvc.gerenciadorOfertas.domain.dto;

import java.math.BigDecimal;

import javax.validation.constraints.*;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewPromocaoDTO  {

	
	@NotBlank(message = "Informe o Nome do site da Promoção")
	private String site;
	
	@NotBlank(message = "Informe o Titulo da Promoção")
	private String titulo;	
	
	private String linkPromocao;
	
	private String descricao;	
	
	private String linkImage;	
	
	@NotNull(message = "Uma Categoria é requirida.")
	private Long categoria;
	
	@NotNull(message = "Informe o Preço da Promoção")
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	private BigDecimal preco;

	
}
