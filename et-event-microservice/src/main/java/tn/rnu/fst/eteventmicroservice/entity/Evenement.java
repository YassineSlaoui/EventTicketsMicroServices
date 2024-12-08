package tn.rnu.fst.eteventmicroservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Evenement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idEvenement;

    @Column(unique = true)
    private String nomEvenement;

    private Long nbPlacesRestants;

    private LocalDate dateEvenement;

    @ElementCollection
    private List<Long> ticketIds;

    @ManyToMany
    @JoinTable(
            name = "evenement_categorie",
            joinColumns = @JoinColumn(name = "evenement_id"),
            inverseJoinColumns = @JoinColumn(name = "categorie_id")
    )
    private List<Categorie> categories;
}