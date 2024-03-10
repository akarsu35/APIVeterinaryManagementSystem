package dev.patika.VeterinaryManagementSystem.dao;

import dev.patika.VeterinaryManagementSystem.dto.response.animal.AnimalResponse;
import dev.patika.VeterinaryManagementSystem.entities.Animal;
import dev.patika.VeterinaryManagementSystem.entities.Customer;
import dev.patika.VeterinaryManagementSystem.entities.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepo extends JpaRepository<Animal, Long> {


    List<Animal> findByNameContaining(String name);

    List<Animal> findByNameContainingIgnoreCase(String name);

    boolean existsByCustomerAndName(Customer customer, String name);

    List<Animal> findByCustomerName(String name);

}
