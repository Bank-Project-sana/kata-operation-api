package fr.societegenerale.repository;

import fr.societegenerale.model.ClientAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientAccountRepository extends JpaRepository<ClientAccountEntity, Long> {

}
