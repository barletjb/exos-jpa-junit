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
    private EntityManager entityManager;

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
    void test_saveDonnePerso() {

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

        DonneePerso donneePersoDB = donneePersoRepository.save(donneePerso);

        log.info(donneePersoDB.toString());

        Assertions.assertThat(donneePersoDB.getId()).isGreaterThan(0);

        EtudiantENI etudiantENIDB = entityManager.find(EtudiantENI.class, "IMMAT1");

        Assertions.assertThat(etudiantENIDB).isNotNull();

    }

    @Test
    void test_deleteEtudiantENI() {
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

        entityManager.persist(etudiant);
        entityManager.flush();

        Integer idDonneePerso = etudiant.getDonneePerso().getId();

        log.info(etudiant.toString());

        etudiantEniRepository.delete(etudiant);

        EtudiantENI etudiantDB = entityManager.find(EtudiantENI.class,"IMMAT1");

        Assertions.assertThat(etudiantDB).isNull();

        DonneePerso donneePersoDB = entityManager.find(DonneePerso.class, idDonneePerso);

        Assertions.assertThat(donneePersoDB).isNull();

    }

    @Test
    void test_deleteDonneePerso() {
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

        entityManager.persist(etudiant);
        entityManager.flush();

        Integer idDonneePerso = etudiant.getDonneePerso().getId();

        log.info(etudiant.toString());

        donneePersoRepository.delete(donneePerso);

        EtudiantENI etudiantDB = entityManager.find(EtudiantENI.class,"IMMAT1");

        Assertions.assertThat(etudiantDB).isNull();

        DonneePerso donneePersoDB = entityManager.find(DonneePerso.class, idDonneePerso);

        Assertions.assertThat(donneePersoDB).isNull();

    }

    @Test
    void test_orphanRemovalEtudiantENI() {
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

        entityManager.persist(etudiant);
        entityManager.flush();

        Integer idDonneePerso = etudiant.getDonneePerso().getId();

        log.info(etudiant.toString());

        etudiant.setDonneePerso(null);
        etudiantEniRepository.delete(etudiant);

        EtudiantENI etudiantDB = entityManager.find(EtudiantENI.class,"IMMAT1");

        Assertions.assertThat(etudiantDB).isNull();

        DonneePerso donneePersoDB = entityManager.find(DonneePerso.class, idDonneePerso);

        Assertions.assertThat(donneePersoDB).isNull();

    }

    @Test
    void test_orphanRemovalDonneePerso() {
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

        entityManager.persist(etudiant);
        entityManager.flush();

        Integer idDonneePerso = etudiant.getDonneePerso().getId();

        log.info(etudiant.toString());

        donneePerso.setEtudiantENI(null);
        donneePersoRepository.delete(donneePerso);

        EtudiantENI etudiantDB = entityManager.find(EtudiantENI.class,"IMMAT1");

        Assertions.assertThat(etudiantDB).isNull();

        DonneePerso donneePersoDB = entityManager.find(DonneePerso.class, idDonneePerso);

        Assertions.assertThat(donneePersoDB).isNull();

    }


}


