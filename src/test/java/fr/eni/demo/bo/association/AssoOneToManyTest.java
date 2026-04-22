package fr.eni.demo.bo.association;


import fr.eni.demo.bo.stagiaire.DonneePerso;
import fr.eni.demo.bo.stagiaire.EtudiantENI;
import fr.eni.demo.bo.stagiaire.Promo;
import fr.eni.demo.dal.PromoRepository;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

@DataJpaTest
@Slf4j
class AssoOneToManyTest {

    @Autowired
    private PromoRepository promoRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void test_save() {

        Promo promo = Promo.builder()
                .nom("TEST_PROMO")
                .build();

        for (int i = 0; i < 5; i++) {

            DonneePerso donneePerso = DonneePerso.builder()
                    .nom("TEST" + i)
                    .prenom("Jean-Baptiste" + i)
                    .build();

            EtudiantENI etudiant = EtudiantENI.builder()
                    .email("etudiant" + i + "@ecole.fr")
                    .immatriculation("IMMAT" + i)
                    .donneePerso(donneePerso)
                    .build();

            promo.getEtudiantENIS().add(etudiant);

        }
        Promo promoDB = promoRepository.save(promo);

        log.info(promoDB.toString());

        Assertions.assertThat(promoDB.getId()).isGreaterThan(0);
        Assertions.assertThat(promoDB.getEtudiantENIS()).hasSize(5);

    }

    @Test
    void test_delete() {

        Promo promo = Promo.builder()
                .nom("TEST_PROMO")
                .build();

        for (int i = 0; i < 5; i++) {

            DonneePerso donneePerso = DonneePerso.builder()
                    .nom("TEST" + i)
                    .prenom("Jean-Baptiste" + i)
                    .build();

            EtudiantENI etudiant = EtudiantENI.builder()
                    .email("etudiant" + i + "@ecole.fr")
                    .immatriculation("IMMAT" + i)
                    .donneePerso(donneePerso)
                    .build();

            promo.getEtudiantENIS().add(etudiant);

        }

        entityManager.persist(promo);
        entityManager.flush();

        log.info(promo.toString());

        Integer idPromo = promo.getId();

        promoRepository.delete(promo);

        Promo promoDB = entityManager.find(Promo.class, idPromo);

        Assertions.assertThat(promoDB).isNull();

        for (int i = 0 ; i < 5 ; i ++) {
            EtudiantENI etudiantENIDB = entityManager.find(EtudiantENI.class,"IMMAT" + i );

            Assertions.assertThat(etudiantENIDB).isNull();
        }

    }


    @Test
    void test_orphanRemoval() {

        Promo promo = Promo.builder()
                .nom("TEST_PROMO")
                .build();

        for (int i = 0; i < 5; i++) {

            DonneePerso donneePerso = DonneePerso.builder()
                    .nom("TEST" + i)
                    .prenom("Jean-Baptiste" + i)
                    .build();

            EtudiantENI etudiant = EtudiantENI.builder()
                    .email("etudiant" + i + "@ecole.fr")
                    .immatriculation("IMMAT" + i)
                    .donneePerso(donneePerso)
                    .build();

            promo.getEtudiantENIS().add(etudiant);

        }

        entityManager.persist(promo);
        entityManager.flush();

        log.info(promo.toString());

        Integer idPromo = promo.getId();

        promo.getEtudiantENIS().clear();
        promoRepository.delete(promo);

        Promo promoDB = entityManager.find(Promo.class, idPromo);

        Assertions.assertThat(promoDB).isNull();

        for (int i = 0 ; i < 5 ; i ++) {
            EtudiantENI etudiantENIDB = entityManager.find(EtudiantENI.class,"IMMAT" + i );

            Assertions.assertThat(etudiantENIDB).isNull();
        }

    }

}
