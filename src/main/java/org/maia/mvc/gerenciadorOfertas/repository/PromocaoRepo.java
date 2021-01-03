package org.maia.mvc.gerenciadorOfertas.repository;

import java.util.List;

import org.maia.mvc.gerenciadorOfertas.domain.Promocao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PromocaoRepo extends JpaRepository<Promocao, Long> {
	
	
	@Query("select distinct p.site from Promocao p where p.site like %:site%")
	List<String>findSitesByTermo(@Param("site") String site);
	
	@Transactional(readOnly = false) // modificando a transação para poder realizar o update
	@Modifying
	@Query("update Promocao p set p.likes = p.likes + 1 where p.id = :id") //atualizando o valor das curtidas
	void updateSomaLike(@Param("id") Long id);
	
	@Query("select p.likes from Promocao p where p.id = :id")
	int findLinkesById(@Param("id") Long id);

}
