package org.maia.mvc.gerenciadorOfertas.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.maia.mvc.gerenciadorOfertas.domain.Categoria;
import org.maia.mvc.gerenciadorOfertas.domain.Promocao;
import org.maia.mvc.gerenciadorOfertas.domain.dto.NewPromocaoDTO;
import org.maia.mvc.gerenciadorOfertas.repository.PromocaoRepo;
import org.maia.mvc.gerenciadorOfertas.services.CategoriaServices;
import org.maia.mvc.gerenciadorOfertas.services.PromocaoServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/promocao")
public class PromocaoController {

	private static Logger log = LoggerFactory.getLogger(PromocaoController.class);

	@Autowired
	private CategoriaServices services;

	@Autowired
	private PromocaoServices promoServices;

	@Autowired
	private PromocaoRepo repo;

	@ModelAttribute("categorias") // refenciado no template html e atualiza o combobox da pagina
	public List<Categoria> obterCategorias() {
		return services.findAll();

	}

	@GetMapping("/add")
	public String abrirCadastro() {
		return "promo-add";
	}

	// atualiza os links e retona o total atualizado
	@PostMapping("/like/{id}")
	public ResponseEntity<?> adicionarLikes(@PathVariable("id") Long id) {
		repo.updateSomaLike(id);
		int likes = repo.findLinkesById(id);
		return ResponseEntity.ok(likes);
	}

	@PostMapping("/save")
	public ResponseEntity<?> salvarPromocao(@Valid NewPromocaoDTO promocaoDTO, BindingResult result) {

		log.info("Salvando a promocao: Promocao {}", promocaoDTO.toString());

		/* pegando os erros dos campos do objeto */
		if (result.hasErrors()) {
			Map<String, String> errors = new HashMap<>();

			for (FieldError error : result.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage()); // pega o campo e a msg de Error do mesmo.
				log.error("Error de Validação de campo: " + error.getDefaultMessage());
			}

			return ResponseEntity.unprocessableEntity().body(errors);
		}
		promoServices.fromDTO(promocaoDTO);

		return ResponseEntity.ok().build();
	}

	// ======== AUTOCOMPLETE ==========//
	@GetMapping("/site")
	public ResponseEntity<?> autoCompleteByTermo(@RequestParam("termo") String termo) {
		List<String> sites = repo.findSitesByTermo(termo);

		return ResponseEntity.ok(sites);
	}
	

	// ======== LISTA DE OFERTAS ==========//
	
	/* Retoena as Provoções por nome do Site, e Monta os Novos Cards para a Tela*/
	@GetMapping("/site/list")
	public String lstarPorSite(@RequestParam("site") String site, ModelMap model) {
		System.out.println("Nome do Site "+ site);
		
		Sort sort = new Sort(Sort.Direction.DESC, "dtaCadastroDateTime");

		PageRequest pageRequest = PageRequest.of(0, 8, sort);

		model.addAttribute("promocoes", repo.findBySite(site,pageRequest));

		return "promo-card";
	}

	@GetMapping("/list")
	public String listarOfertas(ModelMap model) {

		Sort sort = new Sort(Sort.Direction.DESC, "dtaCadastroDateTime");

		PageRequest pg = PageRequest.of(0, 8, sort);

		model.addAttribute("promocoes", repo.findAll(pg));

		return "promo-list";
	}

	// ======== LISTA DE OFERTAS ==========//
	@GetMapping("/list/ajax")
	public String listarCards(@RequestParam(name = "page", defaultValue = "1") int page, ModelMap model) {

		Sort sort = new Sort(Sort.Direction.DESC, "dtaCadastroDateTime");

		PageRequest pg = PageRequest.of(page, 8, sort);

		model.addAttribute("promocoes", repo.findAll(pg));

		return "promo-card";
	}

	protected List<Promocao> getPromocoesOrderByDateTime() {
		List<Promocao> lista = repo.findAll().stream()
				.sorted((l1, l2) -> l1.getDtaCadastroDateTime().compareTo(l2.getDtaCadastroDateTime()))
				.collect(Collectors.toList());
		return lista;
	}

}
