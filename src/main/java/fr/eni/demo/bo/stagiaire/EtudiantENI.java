package fr.eni.demo.bo.stagiaire;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Entity
@Table(name = "ETUDIANT_ENI")
public class EtudiantENI {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "IMMATRICULATION", length = 50, nullable = false)
    private String immatriculation;

    @Column(name = "EMAIL", length = 50, nullable = false)
    private String email;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "donnee_perso_id")
    private DonneePerso donneePerso;

}
