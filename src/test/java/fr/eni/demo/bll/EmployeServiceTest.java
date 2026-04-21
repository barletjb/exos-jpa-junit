package fr.eni.demo.bll;

import fr.eni.demo.bo.Employe;
import fr.eni.demo.dal.EmployeRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Slf4j
class EmployeServiceTest {

    @Autowired
    private EmployeService employeService;

    @MockitoBean
    private EmployeRepository employeRepository;

    @Test
    void test_employeNull() {

        Assertions.assertThrows(RuntimeException.class,
                ()-> employeService.ajouter(null));
    }

    @Test
    void test_ajout_employe_immat_existe(){

        String immatriculation = "TEST01";

        Employe employe = Employe.builder()
                .nom("TEST")
                .prenom("test")
                .email("test@gmail.com")
                .immatriculation(immatriculation)
                .build();

       // Mockito.when(
          //      employeRepository.findByImmatriculation(immatriculation))
         //       .thenReturn(Optional.of(employe));

        RuntimeException e =  Assertions.assertThrows(RuntimeException.class,
                ()-> employeService.ajouter(employe));

        Assertions.assertEquals("L'immatriculation doit être unique", e.getMessage());

    }

    @Test
    void test_chargerTousLesEmployes_nonVide(){
        Employe employe1 = Employe.builder()
                .nom("TEST1")
                .prenom("test1")
                .email("test1@gmail.com")
                .immatriculation("TEST01")
                .build();

        Employe employe2 = Employe.builder()
                .nom("TEST2")
                .prenom("test2")
                .email("test2@gmail.com")
                .immatriculation("TEST02")
                .build();

        List<Employe> listeEmployes = new ArrayList<>();
        listeEmployes.add(employe1);
        listeEmployes.add(employe2);

        Mockito.when(
                employeRepository.findAll())
                .thenReturn(listeEmployes);

        List<Employe> employeList = employeService.chargerLesEmployes();

        org.assertj.core.api.Assertions.assertThat(employeList).hasSize(2);

        log.info(employeList.toString());
    }

}