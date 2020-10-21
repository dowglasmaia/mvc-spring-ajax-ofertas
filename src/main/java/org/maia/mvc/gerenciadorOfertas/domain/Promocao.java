package org.maia.mvc.gerenciadorOfertas.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;


import lombok.*;

@SuppressWarnings("serial")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "promocoes")
public class Promocao implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Informe o Nome do site da Promoção")
	@Column(name = "site_promocao", nullable = false)
	private String site;

	@NotBlank(message = "Informe o Titulo da Promoção")
	@Column(name = "title_promocao", nullable = false)
	private String title;

	@NotBlank(message = "Informe o Link da Promoção")
	@Column(name = "link_promocao", nullable = false)
	private String linkPromocao;

	@Column(name = "descricao", nullable = false)
	private String descricao;

	@NotNull(message = "Informe o Preço da Promoção")
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	@Column(name = "preco_promocao", nullable = false)
	private BigDecimal preco;

	@NotBlank(message = "Informe o endereço(url) da Imagem da Promoção")
	@Column(name = "link_imagem", nullable = false)
	private String linkImage;

	@Column(name = "total_likes")
	private int likes;

	@Column(name = "data_cadastro")
	private LocalDateTime dtaCadastroDateTime;
	
	@ManyToOne
	@JoinColumn(name = "categoria_fk")
	private Categoria categoria;

	
}
