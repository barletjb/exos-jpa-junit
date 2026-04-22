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
@Table(name = "CIVILITE")
public class Civilite {

   @Id
   @EqualsAndHashCode.Include
   @Column(name = "CIVILITE_ID", length = 5)
   private String clef;

   @Column(name = "CIVILITE_LABEL", nullable = false, length = 20)
   private String libelle;


}
