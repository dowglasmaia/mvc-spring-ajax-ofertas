package org.maia.mvc.gerenciadorOfertas.services.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.maia.mvc.gerenciadorOfertas.domain.Categoria;
import org.maia.mvc.gerenciadorOfertas.domain.Promocao;
import org.maia.mvc.gerenciadorOfertas.domain.dto.NewPromocaoDTO;
import org.maia.mvc.gerenciadorOfertas.repository.PromocaoRepo;
import org.maia.mvc.gerenciadorOfertas.services.CategoriaServices;
import org.maia.mvc.gerenciadorOfertas.services.PromocaoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromocaoServicesImpl implements PromocaoServices {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private PromocaoRepo repo;
	
	@Autowired
	private CategoriaServices catServices;

	@Override
	public List<Promocao> findAll() {		
		return repo.findAll();
	}

	@Override
	public Promocao findById(Long id) {		
		return repo.findById(id).orElseThrow(
				()-> new RuntimeException("Promoão não encontrada para o ID: "+id ));
	}

	@Override
	public Promocao save(Promocao obj) {	
		obj.setDtaCadastroDateTime(LocalDateTime.now());
		return repo.save(obj);
	}

	@Override
	public void fromDTO(NewPromocaoDTO promocaoDTO) {
		Categoria categoria = catServices.findById(promocaoDTO.getCategoria()); 			
		
		Promocao entity = Promocao.builder()
				.categoria(categoria)
				.site(promocaoDTO.getSite())
				.linkImage(promocaoDTO.getLinkImage())
				.titulo(promocaoDTO.getTitulo())
				.linkPromocao(promocaoDTO.getLinkPromocao())
				.descricao(promocaoDTO.getDescricao())
				.preco(promocaoDTO.getPreco())
				.build();										
		
		this.save(entity);		
	}

}
