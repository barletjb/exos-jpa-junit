package fr.eni.demo.bo.association;


import fr.eni.demo.bo.Adresse;
import fr.eni.demo.bo.Employe;
import fr.eni.demo.dal.EmployeRepository;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

@DataJpaTest
@Slf4j
public class AssoOneToOneTest {

    @Autowired
    private EmployeRepository employeRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void test_save(){

        Adresse adresse = Adresse.builder()
                .rue("RueTest")
                .codePostal("44000")
                .ville("NANTES")
                .build();

        Employe employe = Employe.builder()
                .nom("TEST")
                .prenom("test")
                .email("test@gmail.com")
                .immatriculation("TEST01")
                .numDom("0123456789")
                .numPortable("0623456789")
                .adresse(adresse)
                .build();

        Employe employeDB = employeRepository.save(employe);

        log.info(employeDB.toString());

        Assertions.assertThat(employeDB.getId()).isGreaterThan(0);
        Assertions.assertThat(employeDB.getAdresse().getId()).isGreaterThan(0);

    }

    @Test
    void test_delete(){

        Adresse adresse = Adresse.builder()
                .rue("RueTest")
                .codePostal("44000")
                .ville("NANTES")
                .build();

        Employe employe = Employe.builder()
                .nom("TEST")
                .prenom("test")
                .email("test@gmail.com")
                .immatriculation("TEST01")
                .numDom("0123456789")
                .numPortable("0623456789")
                .adresse(adresse)
                .build();

        entityManager.persist(employe);
        entityManager.flush();

        log.info(employe.toString());

        Integer idEmploye = employe.getId();
        Integer idAdresse = employe.getAdresse().getId();

        employeRepository.delete(employe);

        Employe employeDB = entityManager.find(Employe.class, idEmploye);
        Adresse adresseDB = entityManager.find(Adresse.class, idAdresse);

        Assertions.assertThat(employeDB).isNull();
        Assertions.assertThat(adresseDB).isNull();
    }


}
