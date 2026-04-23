package fr.eni.demo.bo.formation;

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
@Table(name = "COMPUTER_COURSE")
public class Cours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "REFERENCE", nullable = false, length = 20, unique = true)
    private String reference;

    @Column(name = "TITLE", nullable = false, length = 50, unique = true)
    private String titre;

    @Column(name = "COMPUTER_SCIENCE_COURSE", nullable = false, length = 50)
    private String filiere;


}
