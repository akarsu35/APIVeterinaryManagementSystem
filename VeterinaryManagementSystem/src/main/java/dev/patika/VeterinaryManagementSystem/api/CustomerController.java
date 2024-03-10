package dev.patika.VeterinaryManagementSystem.api;

import dev.patika.VeterinaryManagementSystem.business.abstracts.IAnimalService;
import dev.patika.VeterinaryManagementSystem.business.abstracts.ICustomerService;
import dev.patika.VeterinaryManagementSystem.core.config.ModelMapper.IModelMapperService;
import dev.patika.VeterinaryManagementSystem.core.result.Result;
import dev.patika.VeterinaryManagementSystem.core.result.ResultData;
import dev.patika.VeterinaryManagementSystem.core.utilities.ResultHelper;
import dev.patika.VeterinaryManagementSystem.dto.response.animal.AnimalResponse;
import dev.patika.VeterinaryManagementSystem.entities.Animal;
import dev.patika.VeterinaryManagementSystem.entities.Customer;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/customers")
public class CustomerController {
    private final ICustomerService customerService;
    private final IAnimalService animalService;


    public CustomerController(ICustomerService customerService, IAnimalService animalService, IModelMapperService modelMapper) {//DI
        this.customerService = customerService;
        this.animalService = animalService;
    }

    //Değerlendrime formu 10
    @PostMapping()//hayvan sahibi kaydetme
    @ResponseStatus(HttpStatus.CREATED)
    public Optional<Customer> save(@Valid @RequestBody Customer customer) {
        this.customerService.save(customer);
        return Optional.of(customer);
    }

    @GetMapping("/{id}")//id'si girilen hayvan sahibi bilgilerini getirme
    @ResponseStatus(HttpStatus.OK)
    public Optional<Customer> get(@PathVariable("id") Long id) {
        return Optional.of(this.customerService.get(id));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public Optional<List<Customer>> cursor() {
        return Optional.of(this.customerService.getAll());
    }


    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public Customer update(@Valid @RequestBody Customer customer) {
        return this.customerService.update(customer);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        this.customerService.delete(id);
        return ResultHelper.ok();

    }

    //Değerlendrime formu 17
    //Hayvan sahiplerini isme gore filtreleme
    @GetMapping("/customerName/{customerName}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ResultData<List<Customer>>> filterByName(@PathVariable("customerName") String name) {
        return ResponseEntity.ok(ResultHelper.success(this.customerService.filterByCustomerName(name)));

    }

    //Değerlendrime formu 18
    //Girilen hayvan sahibinin sistemde kayıtlı tüm hayvan bilgilerini getirme

    @GetMapping("/animals/{customerName}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ResultData<List<Animal>>> getCustomerAnimals(@PathVariable("customerName") String name) {
        return ResponseEntity.ok(ResultHelper.success(this.animalService.getAnimalByCustomerName(name)));
    }



}
