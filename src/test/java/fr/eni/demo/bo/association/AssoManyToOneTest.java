package fr.eni.demo.bo.association;


import fr.eni.demo.bo.Civilite;
import fr.eni.demo.bo.Employe;
import fr.eni.demo.dal.CiviliteRepository;
import fr.eni.demo.dal.EmployeRepository;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

@DataJpaTest
@Slf4j
class AssoManyToOneTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private CiviliteRepository civiliteRepository;

    @Autowired
    private EmployeRepository employeRepository;

    private Civilite monsieur;
    private Civilite madame;
    private Civilite mademoiselle;

    @BeforeEach
    void initTest(){

        monsieur = Civilite.builder()
                .clef("M")
                .libelle("Monsieur")
                .build();

        madame = Civilite.builder()
                .clef("Mme")
                .libelle("Madame")
                .build();

        mademoiselle = Civilite.builder()
                .clef("Mlle")
                .libelle("Mademoiselle")
                .build();

        entityManager.persist(monsieur);
        entityManager.persist(madame);
        entityManager.persist(mademoiselle);
        entityManager.flush();

    }

    @Test
    void test_save(){

        Employe employe = Employe.builder()
                .nom("TEST")
                .prenom("test")
                .email("test@gmail.com")
                .immatriculation("TEST01")
                .civilite(monsieur)
                .build();

        Employe employeDB = employeRepository.save(employe);

        Assertions.assertThat(employeDB.getId()).isGreaterThan(0);
        Assertions.assertThat(employeDB.getCivilite()).isEqualTo(monsieur);

    }



}
