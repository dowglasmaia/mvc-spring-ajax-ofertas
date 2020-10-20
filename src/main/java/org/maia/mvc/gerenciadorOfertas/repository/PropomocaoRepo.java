package org.maia.mvc.gerenciadorOfertas.repository;

import org.maia.mvc.gerenciadorOfertas.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropomocaoRepo extends JpaRepository<Categoria, Long> {

}
