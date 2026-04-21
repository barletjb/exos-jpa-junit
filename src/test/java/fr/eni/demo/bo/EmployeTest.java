package fr.eni.demo.bo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
class EmployeTest {

    @Test
    public void test_employer(){

        Employe e = Employe.builder()
                .nom("BT")
                .prenom("Jb")
                .build();

        log.info(e.toString());

        Assertions.assertNotNull(e);
        assertThat(e.getNom()).isEqualTo("BT");

    }

}