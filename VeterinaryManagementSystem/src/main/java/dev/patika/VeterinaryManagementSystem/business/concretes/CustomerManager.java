package dev.patika.VeterinaryManagementSystem.business.concretes;

import dev.patika.VeterinaryManagementSystem.business.abstracts.ICustomerService;
import dev.patika.VeterinaryManagementSystem.core.config.ModelMapper.IModelMapperService;
import dev.patika.VeterinaryManagementSystem.core.exception.AlreadyExistsException;
import dev.patika.VeterinaryManagementSystem.core.exception.NotFoundException;
import dev.patika.VeterinaryManagementSystem.core.utilities.Msg;
import dev.patika.VeterinaryManagementSystem.core.utilities.ResultHelper;
import dev.patika.VeterinaryManagementSystem.dao.CustomerRepo;
import dev.patika.VeterinaryManagementSystem.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Arrays.stream;

@Service
public class CustomerManager implements ICustomerService {
    private final CustomerRepo customerRepo;
    private final IModelMapperService modelMapper;

    public CustomerManager(CustomerRepo customerRepo, IModelMapperService modelMapper) {//DI
        this.customerRepo = customerRepo;
        this.modelMapper = modelMapper;
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
    public Page<Customer> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.customerRepo.findAll(pageable);
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
    public List<Customer> filterByName(String name) {
        return customerRepo.findByNameContaining(name);
    }

    @Override
    public List<Customer> filterByNameIgnoreCase(String name) {
        return customerRepo.findByNameContainingIgnoreCase(name);
    }


}
