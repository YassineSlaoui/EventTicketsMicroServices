package tn.rnu.fst.eteventmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.rnu.fst.eteventmicroservice.entity.Categorie;

import java.util.Optional;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Long> {
    Optional<Categorie> findByCodeCategorie(String codeCategorie);
}
