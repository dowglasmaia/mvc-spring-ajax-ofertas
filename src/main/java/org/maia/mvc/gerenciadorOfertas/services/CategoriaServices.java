package org.maia.mvc.gerenciadorOfertas.services;

import java.io.Serializable;
import java.util.List;

import org.maia.mvc.gerenciadorOfertas.domain.Categoria;
import org.springframework.stereotype.Service;


public interface CategoriaServices extends Serializable{
	
	List<Categoria> findAll();
	
	Categoria findById(Long id);

}
