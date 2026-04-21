package fr.eni.demo.bo;

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
@Table(name = "ADRESSE")
public class Adresse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADRESSE_ID", unique = true)
    private Integer id;

    @Column(name = "CODE_POSTAL", length = 10 ,nullable = false)
    private String codePostal;

    @Column(name = "VILLE", length = 100 ,nullable = false)
    private String ville;

    @Column(name = "RUE", length = 100, nullable = false)
    private String rue;
}
