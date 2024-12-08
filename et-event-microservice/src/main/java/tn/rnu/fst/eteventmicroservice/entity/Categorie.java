package tn.rnu.fst.eteventmicroservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idCategorie;

    @Column(unique = true)
    private String codeCategorie;

    @Column(unique = true)
    private String nomCategorie;

    @ManyToMany(mappedBy = "categories")
    private List<Evenement> evenements;
}
