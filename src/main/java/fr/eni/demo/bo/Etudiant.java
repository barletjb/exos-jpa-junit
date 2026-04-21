package fr.eni.demo.bo;

import fr.eni.demo.bo.pk.EtudiantPK;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
@Entity
@IdClass(EtudiantPK.class)
@Table(name = "ETUDIANTS")
public class Etudiant {

    @Id
    @Column(name ="EMAIL", length = 100, nullable = false, unique = true)
    private String email;
    @Id
    @Column(name ="IMMATRICULATION", length = 100, nullable = false, unique = true)
    private String immatriculation;

    @Column(name ="NOM", length = 50, nullable = false)
    private String nom;

    @Column(name ="PRENOM", length = 50, nullable = false)
    private String prenom;

}
