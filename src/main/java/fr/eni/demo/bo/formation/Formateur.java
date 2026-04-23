package fr.eni.demo.bo.formation;


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
@Table(name = "TRAINER")
public class Formateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(name = "LAST_NAME", nullable = false, length = 100)
    private String nom;

    @Column(name = "FIRST_NAME", nullable = false, length = 100)
    private String prenom;

    @Column(name = "COMPUTER_SCIENCE_COURSE", nullable = false, length = 50)
    private String filiere;


    @ManyToMany
    @JoinTable(name = "COMPUTER_COURS_PROVIDED", joinColumns = { @JoinColumn(name = "TRAINER_ID")}, inverseJoinColumns = {@JoinColumn(name = "COMPUTER_COURSE_ID")})
    @Builder.Default
    private List<Cours> listeCours = new ArrayList<>();

}
