package fr.eni.demo.dal;

import fr.eni.demo.bo.stagiaire.DonneePerso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonneePersoRepository extends JpaRepository<DonneePerso, Integer> {
}
