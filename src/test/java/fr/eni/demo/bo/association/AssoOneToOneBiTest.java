package fr.eni.demo.bo.association;

import fr.eni.demo.bo.stagiaire.DonneePerso;
import fr.eni.demo.bo.stagiaire.EtudiantENI;
import fr.eni.demo.dal.DonneePersoRepository;
import fr.eni.demo.dal.EtudiantENIRepository;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

@DataJpaTest
@Slf4j
class AssoOneToOneBiTest {

    @Autowired
    private EtudiantENIRepository etudiantEniRepository;

    @Autowired
    private DonneePersoRepository donneePersoRepository;

    @Autowired
    private EntityManager em;

    @Test
    void test_save() {
        EtudiantENI etudiant = EtudiantENI.builder()
                .email("etudiant@ecole.fr")
                .immatriculation("IMMAT1")
                .build();

        DonneePerso donneePerso = DonneePerso.builder()
                .nom("TEST")
                .prenom("Jean-Baptiste")
                .build();

        etudiant.setDonneePerso(donneePerso);
        donneePerso.setEtudiantENI(etudiant);

        EtudiantENI etudiantDB = etudiantEniRepository.save(etudiant);

        log.info(etudiantDB.toString());

        Assertions.assertThat(etudiantDB.getDonneePerso().getId()).isGreaterThan(0);
    }

    @Test
    void test_delete() {

    }

}


