package fr.eni.demo.bo.stagiaire;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Entity
@Table(name = "STUDENT_CLASS")
public class Promo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(name = "NAME", length = 20, nullable = false)
    private String nom;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true ,fetch = FetchType.LAZY)
    @JoinColumn(name = "CLASS_ID")
    @Builder.Default
    private List<EtudiantENI> etudiantENIS = new ArrayList<>();
}
