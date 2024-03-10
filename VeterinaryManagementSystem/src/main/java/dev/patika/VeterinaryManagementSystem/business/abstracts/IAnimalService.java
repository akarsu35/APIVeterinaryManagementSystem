package dev.patika.VeterinaryManagementSystem.business.abstracts;

import dev.patika.VeterinaryManagementSystem.dto.request.animal.AnimalSaveRequest;
import dev.patika.VeterinaryManagementSystem.dto.response.animal.AnimalResponse;
import dev.patika.VeterinaryManagementSystem.dto.response.vaccine.VaccineResponse;
import dev.patika.VeterinaryManagementSystem.entities.Animal;
import dev.patika.VeterinaryManagementSystem.entities.Customer;
import dev.patika.VeterinaryManagementSystem.entities.Vaccine;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IAnimalService {
    Animal save(Animal animal);


    Animal get(Long id);
    //Page<Animal> cursor(int page, int pageSize);
    Animal update(Animal animal);

    boolean delete(long id);
    List<Animal> filterByName(String name);

    List<Animal> filterByAnimalName(String animalName);

    List<Animal> getAll();

    List<Animal> getAnimalByCustomerName(String name);

}
