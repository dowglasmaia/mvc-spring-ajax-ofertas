package org.maia.mvc.gerenciadorOfertas.repository;

import org.maia.mvc.gerenciadorOfertas.domain.Promocao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromocaoRepo extends JpaRepository<Promocao, Long> {

}
