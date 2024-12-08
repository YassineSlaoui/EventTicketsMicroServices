package tn.rnu.fst.eteventmicroservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tn.rnu.fst.eteventmicroservice.dto.CategorieDTO;
import tn.rnu.fst.eteventmicroservice.entity.Categorie;

@Mapper(componentModel = "spring")
public interface CategorieMapper {
    CategorieDTO toDto(Categorie entity);

    @Mapping(target = "evenements", ignore = true)
    Categorie toEntity(CategorieDTO dto);
}
