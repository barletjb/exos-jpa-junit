package fr.eni.demo.bo;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SuperBuilder
@Entity
@Table(name = "Employee")
@Inheritance(strategy = InheritanceType.JOINED)
public class Employe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_ID")
    private Integer id;

    @Column(name = "EMPLOYEE_LASTNAME", length = 50, nullable = false)
    private String nom;

    @Column(name = "EMPLOYEE_FIRSTNAME", length = 50, nullable = false)
    private String prenom;

    @Column(name = "EMPLOYEE_EMAIL", length = 255, nullable = false, unique = true)
    private String email;

    @EqualsAndHashCode.Include
    @Column(name = "EMPLOYEE_IMMATRICULATION", length = 50, nullable = false, unique = true)
    private String immatriculation;

    @Column(name = "EMPLOYEE_HOME_PHONE_NUMBER", length = 12, nullable = true)
    private String numDom;

    @Column(name = "EMPLOYEE_CELL_PHONE_NUMBER", length = 12, nullable = true)
    private String numPortable;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ADRESSE_ID")
    private Adresse adresse;

    @ManyToOne
    @JoinColumn(name = "CIVILITE_ID")
    private Civilite civilite;

}
