package fr.eni.demo.bo.heritage;

import fr.eni.demo.bo.ChargeRee;
import fr.eni.demo.bo.Employe;
import fr.eni.demo.bo.formation.Formateur;
import fr.eni.demo.dal.ChargeReeRepository;
import fr.eni.demo.dal.EmployeRepository;
import fr.eni.demo.dal.FormateurRepository;
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
public class HeritageJoinedTest {

    @Autowired
    private EmployeRepository employeRepository;

    @Autowired
    private FormateurRepository formateurRepository;

    @Autowired
    private ChargeReeRepository chargeReeRepository;

    @BeforeEach
    void init(){
        List<Employe> listeEmployes = new ArrayList<>();

        listeEmployes.add(
                Employe.builder()
                        .nom("NomTest1")
                        .prenom("PrénomTest1")
                        .immatriculation("IMMAT1")
                        .email("email1@test.fr")
                        .build()
        );

        listeEmployes.add(
                Formateur.builder()
                        .nom("NomTest2")
                        .prenom("PrénomTest2")
                        .immatriculation("IMMAT2")
                        .email("email2@test.fr")
                        .filiere("DEV")
                        .build()
        );

        listeEmployes.add(
                ChargeRee.builder()
                        .nom("NomTest3")
                        .prenom("PrénomTest3")
                        .immatriculation("IMMAT3")
                        .email("email3@test.fr")
                        .numeroBureau("BUR1")
                        .build()
        );

        employeRepository.saveAll(listeEmployes);

    }

    @Test
    void test_findAll(){

        List<Employe> listeEmployes = employeRepository.findAll();

        log.info(listeEmployes.toString());

        Assertions.assertThat(listeEmployes).hasSize(3);

    }

    @Test
    void test_findFormateur(){

        List<Formateur> listeFormateur = formateurRepository.findAll();

        log.info(listeFormateur.toString());

        Assertions.assertThat(listeFormateur).hasSize(1);

    }


}
