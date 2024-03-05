package dev.patika.VeterinaryManagementSystem.api;

import dev.patika.VeterinaryManagementSystem.business.abstracts.IAnimalService;
import dev.patika.VeterinaryManagementSystem.business.abstracts.ICustomerService;
import dev.patika.VeterinaryManagementSystem.core.config.ModelMapper.IModelMapperService;
import dev.patika.VeterinaryManagementSystem.core.exception.NotFoundException;
import dev.patika.VeterinaryManagementSystem.core.result.Result;
import dev.patika.VeterinaryManagementSystem.core.result.ResultData;
import dev.patika.VeterinaryManagementSystem.core.utilities.Msg;
import dev.patika.VeterinaryManagementSystem.core.utilities.ResultHelper;
import dev.patika.VeterinaryManagementSystem.dto.request.customer.CustomerSaveRequest;
import dev.patika.VeterinaryManagementSystem.dto.request.customer.CustomerUpdateRequest;
import dev.patika.VeterinaryManagementSystem.dto.response.CursorResponse;
import dev.patika.VeterinaryManagementSystem.dto.response.animal.AnimalResponse;
import dev.patika.VeterinaryManagementSystem.dto.response.customer.CustomerResponse;
import dev.patika.VeterinaryManagementSystem.entities.Animal;
import dev.patika.VeterinaryManagementSystem.entities.Customer;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/customers")
public class CustomerController {
    private final ICustomerService customerService;
    private final IAnimalService animalService;
    private final IModelMapperService modelMapper;

    public CustomerController(ICustomerService customerService, IAnimalService animalService, IModelMapperService modelMapper) {//DI
        this.customerService = customerService;
        this.animalService = animalService;
        this.modelMapper = modelMapper;
    }

    //Değerlendrime formu 10
    @PostMapping()//hayvan sahibi kaydetme
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<CustomerResponse> save(@Valid @RequestBody CustomerSaveRequest customerSaveRequest) {
        Customer saveCustomer = this.modelMapper.forRequest().map(customerSaveRequest, Customer.class);

        this.customerService.save(saveCustomer);

        return ResultHelper.created(this.modelMapper.forResponse().map(saveCustomer, CustomerResponse.class));
    }

    @GetMapping("/{id}")//id'si girilen hayvan sahibi bilgilerini getirme
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> get(@PathVariable("id") Long id) {
        Customer customer = this.customerService.get(id);
        CustomerResponse customerResponse = this.modelMapper.forResponse().map(customer, CustomerResponse.class);
        return ResultHelper.success(customerResponse);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<CustomerResponse>> cursor(
            @RequestParam(value = "page", required = false,defaultValue = "0") int page,
            @RequestParam(value = "pageSize", required = false,defaultValue = "10") int pageSize
    ){
        Page<Customer> customerPage = this.customerService.cursor(page, pageSize);
        Page<CustomerResponse> customerResponsePage = customerPage
                .map(customer -> this.modelMapper.forResponse().map(customer, CustomerResponse.class));

        return ResultHelper.cursor(customerResponsePage );
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> update(@Valid @RequestBody CustomerUpdateRequest customerUpdateRequest) {

        Customer updateCustomer = this.modelMapper.forRequest().map(customerUpdateRequest, Customer.class);
        this.customerService.update(updateCustomer);

        return ResultHelper.success(this.modelMapper.forResponse().map(updateCustomer, CustomerResponse.class));
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {

        this.customerService.delete(id);
        return ResultHelper.ok();

    }

    //Değerlendrime formu 17
    //Hayvan sahiplerini isme gore filtreleme
    @GetMapping("/customerName/{name}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ResultData<List<CustomerResponse>>> filterByNameInPath(@PathVariable("name") String name) {
        List<Customer> filteredCustomers = this.customerService.filterByNameIgnoreCase(name);
        List<CustomerResponse> customerResponses = filteredCustomers
                .stream()
                .map(customer -> this.modelMapper.forResponse().map(customer, CustomerResponse.class))
                .collect(Collectors.toList());
        if (customerResponses.isEmpty()) {
            return ResponseEntity.ok(ResultHelper.notFoundError(customerResponses));
        }
        return ResponseEntity.ok(ResultHelper.success(customerResponses));
    }

    //Değerlendrime formu 18
    //Girilen hayvan sahibinin sistemde kayıtlı tüm hayvan bilgilerini getirme

    @GetMapping("/animals/{customerName}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ResultData<List<AnimalResponse>>> getCustomerAnimals(@PathVariable("customerName") String name) {
        List<Customer> filteredCustomers = this.customerService.filterByName(name);

        if (filteredCustomers.isEmpty()) {
            return ResponseEntity.ok(ResultHelper.notFoundError(Collections.emptyList()));
        }

        Customer customer = filteredCustomers.get(0);

        List<Animal> filteredAnimals = this.animalService.getAnimalByCustomer(customer);
        List<AnimalResponse> animalResponses = filteredAnimals
            .stream()
            .map(animal -> this.modelMapper.forResponse().map(animal, AnimalResponse.class))
            .collect(Collectors.toList());

        if (animalResponses.isEmpty()) {
            return ResponseEntity.ok(ResultHelper.notFoundError(animalResponses));
        }

        return ResponseEntity.ok(ResultHelper.success(animalResponses));
    }



}
