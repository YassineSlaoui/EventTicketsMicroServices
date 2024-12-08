package tn.rnu.fst.eteventmicroservice.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Builder
public record EvenementDTO(
        Long idEvenement,
        String nomEvenement,
        Long nbPlacesRestants,
        LocalDate dateEvenement,
        List<Map<String, ?>> tickets,
        List<CategorieDTO> categories) {
}