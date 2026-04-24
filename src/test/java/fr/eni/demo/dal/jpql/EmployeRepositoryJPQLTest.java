package fr.eni.demo.dal.jpql;

import fr.eni.demo.bo.Employe;
import fr.eni.demo.dal.EmployeRepository;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@Slf4j
class EmployeRepositoryJPQLTest {

    @Autowired
    private EmployeRepository employeRepository;

    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    void createEmploye(){

        Employe employe = Employe.builder()
                .nom("TEST")
                .prenom("test")
                .email("test@gmail.com")
                .immatriculation("TEST01")
                .numDom("0123456789")
                .numPortable("0623456789")
                .build();

      entityManager.persist(employe);
      entityManager.flush();

    }

    @Test
    void test_findByEmailRecherche(){

        Optional<Employe> employeDB = employeRepository.findByEmailRecherche("test@gmail.com");

        Assertions.assertThat(employeDB).isPresent();

    }


}