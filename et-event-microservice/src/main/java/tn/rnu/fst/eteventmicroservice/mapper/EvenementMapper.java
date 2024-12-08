package tn.rnu.fst.eteventmicroservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestTemplate;
import tn.rnu.fst.eteventmicroservice.dto.EvenementDTO;
import tn.rnu.fst.eteventmicroservice.entity.Evenement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring", uses = CategorieMapper.class)
public interface EvenementMapper {

    @Mapping(target = "tickets", expression = "java(mapTickets(entity.getTicketIds(), fetchTickets))")
    EvenementDTO toDto(Evenement entity, boolean fetchTickets);

    @Mapping(target = "ticketIds", expression = "java(mapTicketIds(dto.tickets()))")
    Evenement toEntity(EvenementDTO dto);

    @Mapping(target = "idEvenement", ignore = true) // ID should not be updated
    @Mapping(target = "ticketIds", expression = "java(mapTicketIds(dto.tickets()))")
    void updateEntityFromDto(EvenementDTO dto, @MappingTarget Evenement entity);

    // Custom mapping logic for tickets (entity to DTO)
    default List<Map<String, ?>> mapTickets(List<Long> ticketIds, boolean fetchTickets) {
        if (fetchTickets) {
            RestTemplate restTemplate = new RestTemplate();
            try {
                return restTemplate.exchange(
                        "http://localhost:8080/tickets?ids=" + String.join(",",
                                ticketIds.stream().map(String::valueOf).toArray(String[]::new)),
                        org.springframework.http.HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<Map<String, ?>>>() {
                        }
                ).getBody();
            } catch (Exception e) {
                return new ArrayList<>();
            }
        }
        return ticketIds.stream()
                .<Map<String, ?>>map(id -> Map.of("ticketId", id))
                .toList();
    }

    // Custom mapping logic for tickets (DTO to entity)
    default List<Long> mapTicketIds(List<Map<String, ?>> tickets) {
        return tickets == null ? new ArrayList<>() : tickets.stream()
                .map(ticket -> (Long) ticket.get("ticketId"))
                .toList();
    }

}
