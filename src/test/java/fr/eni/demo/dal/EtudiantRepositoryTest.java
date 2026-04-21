package fr.eni.demo.dal;

import fr.eni.demo.bo.Etudiant;
import fr.eni.demo.bo.pk.EtudiantPK;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@Slf4j
class EtudiantRepositoryTest {

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void test_saveEtudiant(){

        Etudiant etudiant = Etudiant.builder()
                .immatriculation("TEST123")
                .email("test1@email.com")
                .nom("TEST_NOM_01")
                .prenom("TEST_PRENOM_01")
                .build();

        Etudiant etudiantDB = etudiantRepository.save(etudiant);

        log.info(etudiantDB.toString());

        org.junit.jupiter.api.Assertions.assertNotNull(etudiantDB);

    }

    @Test
    void test_findEtudiant(){

        Etudiant etudiant = Etudiant.builder()
                .immatriculation("TEST1234")
                .email("test2@email.com")
                .nom("TEST_NOM_02")
                .prenom("TEST_PRENOM_02")
                .build();

        entityManager.persist(etudiant);
        entityManager.flush();

        EtudiantPK etudiantPK = EtudiantPK.builder()
                .immatriculation("TEST1234")
                .email("test2@email.com")
                .build();

        Optional<Etudiant> optionalEtudiant = etudiantRepository.findById(etudiantPK);

        log.info(etudiant.toString());

        Assertions.assertThat(optionalEtudiant).isPresent();

        log.info(optionalEtudiant.get().toString());

    }

}