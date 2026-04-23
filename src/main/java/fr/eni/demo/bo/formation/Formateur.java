package fr.eni.demo.bo.formation;


import fr.eni.demo.bo.Employe;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@SuperBuilder
@Entity
@Table(name = "TRAINER")
public class Formateur extends Employe {

    @Column(name = "COMPUTER_SCIENCE_COURSE", nullable = false, length = 50)
    private String filiere;

    @ManyToMany
    @JoinTable(name = "COMPUTER_COURS_PROVIDED", joinColumns = { @JoinColumn(name = "TRAINER_ID")}, inverseJoinColumns = {@JoinColumn(name = "COMPUTER_COURSE_ID")})
    @Builder.Default
    private List<Cours> listeCours = new ArrayList<>();

}
