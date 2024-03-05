package dev.patika.VeterinaryManagementSystem.business.abstracts;

import dev.patika.VeterinaryManagementSystem.entities.Customer;
import jakarta.validation.constraints.Email;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICustomerService {

    Customer save(Customer customer);

    Customer get(long id);
    Page<Customer> cursor(int page, int pageSize);
    Customer update(Customer customer);

    boolean delete(long id);
    List<Customer> filterByName(String name);
    List<Customer> filterByNameIgnoreCase(String name);




}
