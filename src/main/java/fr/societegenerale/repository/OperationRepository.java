package fr.societegenerale.repository;


import fr.societegenerale.model.OperationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface OperationRepository extends JpaRepository<OperationEntity, Long> {
    Set<OperationEntity> findByAccountIdOrderByDateDesc(Long id);
}
