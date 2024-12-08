package tn.rnu.fst.eteventmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.rnu.fst.eteventmicroservice.entity.Evenement;

import java.util.Optional;

@Repository
public interface EvenementRepository extends JpaRepository<Evenement, Long> {
    Optional<Evenement> findByNomEvenement(String nomEvt);
}
