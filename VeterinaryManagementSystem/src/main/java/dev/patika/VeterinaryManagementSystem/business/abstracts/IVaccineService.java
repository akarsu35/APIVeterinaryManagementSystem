package dev.patika.VeterinaryManagementSystem.business.abstracts;

import dev.patika.VeterinaryManagementSystem.entities.Animal;
import dev.patika.VeterinaryManagementSystem.entities.Customer;
import dev.patika.VeterinaryManagementSystem.entities.Vaccine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface IVaccineService {
    Vaccine save(Vaccine vaccine);

    Vaccine get(long id);
    Page<Vaccine> cursor(int page, int pageSize);
    Vaccine update(Vaccine vaccine);

    boolean delete(long id);


    List<Vaccine> getVaccinesByAnimal(Animal animal);

    List<Vaccine> getVaccinesByDate(LocalDate startDate, LocalDate endDate);


}
