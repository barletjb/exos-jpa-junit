package fr.eni.demo.bll;

import fr.eni.demo.bo.Employe;

import java.util.List;

public interface EmployeService {

    void ajouter(Employe e);

    List<Employe> chargerLesEmployes();

}
