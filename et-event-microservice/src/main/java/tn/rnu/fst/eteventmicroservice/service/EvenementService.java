package tn.rnu.fst.eteventmicroservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.rnu.fst.eteventmicroservice.dto.EvenementDTO;
import tn.rnu.fst.eteventmicroservice.entity.Evenement;
import tn.rnu.fst.eteventmicroservice.entity.Categorie;
import tn.rnu.fst.eteventmicroservice.mapper.EvenementMapper;
import tn.rnu.fst.eteventmicroservice.mapper.CategorieMapper;
import tn.rnu.fst.eteventmicroservice.repository.EvenementRepository;
import tn.rnu.fst.eteventmicroservice.repository.CategorieRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EvenementService {

    private final EvenementRepository evenementRepository;
    private final CategorieRepository categorieRepository;
    private final EvenementMapper evenementMapper;
    private final CategorieMapper categorieMapper;

    @Transactional
    public EvenementDTO saveEvenement(EvenementDTO evenementDTO) {
        List<Categorie> categories = evenementDTO.categories().stream().map(categorieMapper::toEntity).map(this::saveCategorie).toList();

        Evenement evenement = evenementMapper.toEntity(evenementDTO);
        evenement.setCategories(categories);

        Evenement savedEvenement = evenementRepository.save(evenement);

        return evenementMapper.toDto(savedEvenement, false);
    }

    public EvenementDTO updateEvenement(Long id, EvenementDTO evenementDTO) {
        if (evenementRepository.existsById(id)) {
            List<Categorie> categories = evenementDTO.categories().stream().map(categorieMapper::toEntity).map(this::saveCategorie).toList();

            Evenement evenement = evenementMapper.toEntity(evenementDTO);
            evenement.setCategories(categories);
            evenement.setIdEvenement(id);

            Evenement updatedEvenement = evenementRepository.save(evenement);
            return evenementMapper.toDto(updatedEvenement, true);
        }

        return null;
    }

    public List<EvenementDTO> getAllEvenements() {
        return evenementRepository.findAll().stream().map(evenement -> evenementMapper.toDto(evenement,true)).toList();
    }

    public Optional<EvenementDTO> getEvenementById(Long id) {
        return evenementRepository.findById(id).map(evenement -> evenementMapper.toDto(evenement,true));
    }

    public void deleteEvenement(Long id) {
        evenementRepository.deleteById(id);
    }

    private Categorie saveCategorie(Categorie categorie) {
        Optional<Categorie> existingCategorie = categorieRepository.findByCodeCategorie(categorie.getCodeCategorie());
        return existingCategorie.orElseGet(() -> categorieRepository.save(categorie));
    }
}
