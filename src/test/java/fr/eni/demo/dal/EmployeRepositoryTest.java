package fr.eni.demo.dal;

import fr.eni.demo.bo.Employe;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@Slf4j
class EmployeRepositoryTest {

    @Autowired
    private EmployeRepository employeRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void test_saveEmploye(){

        Employe employe = Employe.builder()
                .nom("TEST")
                .prenom("test")
                .email("test@gmail.com")
                .immatriculation("TEST01")
                .numDom("0123456789")
                .numPortable("0623456789")
                .build();

        Employe employeDB = employeRepository.save(employe);

        log.info(employeDB.toString());

        Assertions.assertThat(employeDB.getId()).isGreaterThan(0);

    }

    @Test
    void test_findEmploye(){

        Employe employe = Employe.builder()
                .nom("TEST2")
                .prenom("test2")
                .email("test2@gmail.com")
                .immatriculation("TEST02")
                .numDom("0123456788")
                .numPortable("0623456788")
                .build();

        entityManager.persist(employe);
        entityManager.flush();

        log.info(employe.toString());

        Integer id = employe.getId();

        Optional<Employe> employeOptional = employeRepository.findById(id);

        Assertions.assertThat(employeOptional).isPresent();

        log.info(employeOptional.toString());

    }


    @Test
    void test_deleteEmploye(){

        Employe employe = Employe.builder()
                .nom("TEST3")
                .prenom("test3")
                .email("test3@gmail.com")
                .immatriculation("TEST03")
                .build();

        entityManager.persist(employe);
        entityManager.flush();

        log.info(employe.toString());

        Integer id = employe.getId();

        employeRepository.delete(employe);

        Employe e = entityManager.find(Employe.class, id);

        Assertions.assertThat(e).isNull();

    }

}