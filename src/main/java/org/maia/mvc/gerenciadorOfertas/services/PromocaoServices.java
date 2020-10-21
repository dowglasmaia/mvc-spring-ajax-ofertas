package org.maia.mvc.gerenciadorOfertas.services;

import java.io.Serializable;
import java.util.List;

import org.maia.mvc.gerenciadorOfertas.domain.Promocao;
import org.maia.mvc.gerenciadorOfertas.domain.dto.NewPromocaoDTO;
import org.springframework.stereotype.Service;

@Service
public interface PromocaoServices extends Serializable {

	List<Promocao> findAll();

	Promocao findById(Long id);

	Promocao save(Promocao obj);

	void fromDTO(NewPromocaoDTO promocaoDTO);

}
