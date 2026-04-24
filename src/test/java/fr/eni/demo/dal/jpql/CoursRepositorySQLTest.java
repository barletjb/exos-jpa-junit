package fr.eni.demo.dal.jpql;

import fr.eni.demo.bo.formation.Cours;
import fr.eni.demo.dal.CoursRepository;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
@Slf4j
class CoursRepositorySQLTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private CoursRepository coursRepository;

    private Cours coursJava;
    private Cours coursAngular;
    private Cours baseReseaux;
    private List<Cours> listeCours = new ArrayList<>();

    @BeforeEach
    void init_cours(){

        coursJava = Cours.builder()
                .titre("Java")
                .filiere("DEV")
                .reference("DEV2026_01")
                .build();

        coursAngular = Cours.builder()
                .titre("Angular")
                .filiere("DEV")
                .reference("DEV2026_02")
                .build();


        baseReseaux = Cours.builder()
                .titre("Base réseaux")
                .filiere("RESEAU")
                .reference("RES2026_01")
                .build();

        listeCours.add(coursJava);
        listeCours.add(coursAngular);
        listeCours.add(baseReseaux);
        coursRepository.saveAll(listeCours);

    }

    @Test
    void test_findByFiliereSQL(){

        List<Cours> listeCoursDB = coursRepository.findByFiliereSQL("DEV");

        log.info(listeCoursDB.toString());

        Assertions.assertThat(listeCoursDB).hasSize(2);
    }



}
