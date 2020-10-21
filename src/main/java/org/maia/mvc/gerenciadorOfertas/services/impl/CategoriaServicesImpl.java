package org.maia.mvc.gerenciadorOfertas.services.impl;

import java.util.List;

import org.maia.mvc.gerenciadorOfertas.domain.Categoria;
import org.maia.mvc.gerenciadorOfertas.repository.CategoriaRepo;
import org.maia.mvc.gerenciadorOfertas.services.CategoriaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

@Service
public class CategoriaServicesImpl implements CategoriaServices {
	private static final long serialVersionUID = 1L;

	@Autowired
	private CategoriaRepo repository;

	@Override
	public List<Categoria> findAll() {
	
		return repository.findAll();
	}

	@Override
	public Categoria findById(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Nenhuma Categoria encontrada para o ID: " + id) );
	}

	
}
