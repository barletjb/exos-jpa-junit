package fr.eni.demo.bll;

import fr.eni.demo.bo.Employe;
import fr.eni.demo.dal.EmployeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeServiceImpl implements EmployeService {

    private EmployeRepository employeRepository;

    @Override
    public void ajouter(Employe e) {

        //Règles de gestion
        if (e == null) {
            throw new RuntimeException("L'employe ne peut pas être null");
        }

        if (e.getNom().isBlank()) {
            throw new RuntimeException("Le nom de l'employe est obligatoire");
        }

        if (e.getPrenom().isBlank()) {
            throw new RuntimeException("Le prenom de l'employe est obligatoire");
        }

        Optional<Employe> optionalEmploye = employeRepository.findByImmatriculation(e.getImmatriculation());

        if (optionalEmploye.isPresent()) {
            throw new RuntimeException("L'immatriculation doit être unique");
        }

        employeRepository.save(e);

    }

    @Override
    public List<Employe> chargerLesEmployes() {
        return employeRepository.findAll();
    }
}
