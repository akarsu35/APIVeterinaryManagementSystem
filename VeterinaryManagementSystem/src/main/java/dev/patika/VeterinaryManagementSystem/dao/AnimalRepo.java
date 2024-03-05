package dev.patika.VeterinaryManagementSystem.dao;

import dev.patika.VeterinaryManagementSystem.entities.Animal;
import dev.patika.VeterinaryManagementSystem.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepo extends JpaRepository<Animal, Long> {


    List<Animal> findByNameContaining(String name);

    List<Animal> findByNameContainingIgnoreCase(String name);

    List<Animal> getAnimalByCustomer(Customer customer);


    boolean existsByCustomerAndName(Customer customer, String name);
}
