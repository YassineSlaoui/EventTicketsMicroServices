package tn.rnu.fst.eteventmicroservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.rnu.fst.eteventmicroservice.dto.EvenementDTO;
import tn.rnu.fst.eteventmicroservice.service.EvenementService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class EvenementController {

    private final EvenementService evenementService;

    @PostMapping
    public ResponseEntity<EvenementDTO> createEvenement(@RequestBody EvenementDTO evenementDTO) {
        EvenementDTO savedEvenement = evenementService.saveEvenement(evenementDTO);
        return ResponseEntity.ok(savedEvenement);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EvenementDTO> updateEvenement(@PathVariable Long id, @RequestBody EvenementDTO evenementDTO) {
        EvenementDTO updatedEvenement = evenementService.updateEvenement(id, evenementDTO);
        return updatedEvenement != null ? ResponseEntity.ok(updatedEvenement) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<EvenementDTO>> getAllEvenements() {
        List<EvenementDTO> evenements = evenementService.getAllEvenements();
        return ResponseEntity.ok(evenements);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EvenementDTO> getEvenementById(@PathVariable Long id) {
        Optional<EvenementDTO> evenementDTO = evenementService.getEvenementById(id);
        return evenementDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvenement(@PathVariable Long id) {
        evenementService.deleteEvenement(id);
        return ResponseEntity.noContent().build();
    }
}
