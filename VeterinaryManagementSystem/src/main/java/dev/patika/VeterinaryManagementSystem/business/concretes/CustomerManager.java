package dev.patika.VeterinaryManagementSystem.business.concretes;

import dev.patika.VeterinaryManagementSystem.business.abstracts.ICustomerService;
import dev.patika.VeterinaryManagementSystem.core.config.ModelMapper.IModelMapperService;
import dev.patika.VeterinaryManagementSystem.core.exception.AlreadyExistsException;
import dev.patika.VeterinaryManagementSystem.core.exception.NotFoundException;
import dev.patika.VeterinaryManagementSystem.core.utilities.Msg;
import dev.patika.VeterinaryManagementSystem.dao.AnimalRepo;
import dev.patika.VeterinaryManagementSystem.dao.CustomerRepo;
import dev.patika.VeterinaryManagementSystem.dto.response.animal.AnimalResponse;
import dev.patika.VeterinaryManagementSystem.entities.Animal;
import dev.patika.VeterinaryManagementSystem.entities.Customer;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.stream;

@Service
public class CustomerManager implements ICustomerService {
    private final CustomerRepo customerRepo;


    public CustomerManager(CustomerRepo customerRepo) {//DI
        this.customerRepo = customerRepo;


    }

    public Customer save(Customer customer) {
        if(this.customerRepo.existsByMail(customer.getMail())){
            throw new AlreadyExistsException(Msg.MAIL_ALREADY_EXIST);
        }
        return this.customerRepo.save(customer);
    }

    @Override
    public Customer get(long id) {
        return this.customerRepo.findById(id).orElseThrow(()->new NotFoundException(Msg.NOT_FOUND));
    }


    @Override
    public List<Customer> getAll() {
        return this.customerRepo.findAll();
    }



    @Override
    public Customer update(Customer customer) {
        this.get(customer.getId());
        return this.customerRepo.save(customer);
    }

    @Override
    public boolean delete(long id) {
        Customer customer = this.get(id);
        this.customerRepo.delete(customer);
        return true;
    }



    @Override
    public List<Customer> filterByCustomerName(String customerName) {
        List<Customer> filteredCustomers = this.customerRepo.findByNameContainingIgnoreCase(customerName);
        if (filteredCustomers.isEmpty()) {
            throw new NotFoundException(Msg.NOT_FOUND);
        }
        return filteredCustomers;
    }


}
