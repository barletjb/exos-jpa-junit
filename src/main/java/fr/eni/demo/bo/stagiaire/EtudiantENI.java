package fr.eni.demo.bo.stagiaire;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
}
