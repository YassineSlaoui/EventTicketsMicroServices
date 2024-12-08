package tn.rnu.fst.eteventmicroservice.dto;

import lombok.Builder;

@Builder
public record CategorieDTO(
        Long idCategorie,
        String codeCategorie,
        String nomCategorie) {
}