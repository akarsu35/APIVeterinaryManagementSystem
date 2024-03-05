package dev.patika.VeterinaryManagementSystem.api;

import dev.patika.VeterinaryManagementSystem.business.abstracts.IAnimalService;
import dev.patika.VeterinaryManagementSystem.business.abstracts.ICustomerService;
import dev.patika.VeterinaryManagementSystem.business.abstracts.IVaccineService;
import dev.patika.VeterinaryManagementSystem.core.config.ModelMapper.IModelMapperService;
import dev.patika.VeterinaryManagementSystem.core.result.Result;
import dev.patika.VeterinaryManagementSystem.core.result.ResultData;
import dev.patika.VeterinaryManagementSystem.core.utilities.ResultHelper;
import dev.patika.VeterinaryManagementSystem.dto.request.animal.AnimalSaveRequest;
import dev.patika.VeterinaryManagementSystem.dto.request.animal.AnimalUpdateRequest;
import dev.patika.VeterinaryManagementSystem.dto.response.CursorResponse;
import dev.patika.VeterinaryManagementSystem.dto.response.animal.AnimalResponse;
import dev.patika.VeterinaryManagementSystem.dto.response.customer.CustomerResponse;
import dev.patika.VeterinaryManagementSystem.dto.response.vaccine.VaccineResponse;
import dev.patika.VeterinaryManagementSystem.entities.Animal;
import dev.patika.VeterinaryManagementSystem.entities.Customer;
import dev.patika.VeterinaryManagementSystem.entities.Vaccine;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/animals")
public class AnimalController {
    private final IAnimalService animalService;
    private final ICustomerService customerService;
    private final IModelMapperService modelMapper;
    private final IVaccineService vaccineService;

    public AnimalController(IAnimalService animalService,
                            ICustomerService customerService,
                            IModelMapperService modelMapper, IVaccineService vaccineService) {
        this.animalService = animalService;
        this.customerService = customerService;
        this.modelMapper = modelMapper;
        this.vaccineService = vaccineService;
    }

    //Değerlendrime formu 11
    @PostMapping()//hayvanları kaydetme islemi
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AnimalResponse> save(@Valid @RequestBody AnimalSaveRequest animalSaveRequest) {
        Animal saveAnimal = this.modelMapper.forRequest().map(animalSaveRequest, Animal.class);

        //animal entiti içinde customer_id var dolayısıyla bu animala ait customer_id ve customer bilgilerini ekliyoruz,
        //eğer başka entitiler ile benzer ilişki varsa onları da benzer şekilde eklemeliyiz.
        Customer customer = this.customerService.get(animalSaveRequest.getCustomer().getId());

        saveAnimal.setCustomer(customer);
        /*Customer customer=this.customerService.get(animalSaveRequest.getCustomerId());//customer id ile customer bilgilerini getir
        animalSaveRequest.setCustomerId(null);
        Animal saveAnimal2=this.modelMapper.forRequest().map(animalSaveRequest,Animal.class);
        saveAnimal2.setCustomer(customer);*/

        this.animalService.save(saveAnimal);

        return ResultHelper.created(this.modelMapper.forResponse().map(saveAnimal, AnimalResponse.class));
    }
    @GetMapping("/{id}")//id'si girilen hayvan bilgilerini getirme
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> get(@PathVariable("id") long id) {
        Animal animal = this.animalService.get(id);
        AnimalResponse animalResponse = this.modelMapper.forResponse().map(animal, AnimalResponse.class);
        return ResultHelper.success(animalResponse);
    }
    @GetMapping()//kayıtlı tüm hayvan bilgilerini getirme
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AnimalResponse>> cursor(
            @RequestParam(value = "page", required = false,defaultValue = "0") int page,
            @RequestParam(value = "pageSize", required = false,defaultValue = "15   ") int pageSize
    ){
        Page<Animal> animalPage = this.animalService.cursor(page, pageSize);
        Page<AnimalResponse> animalResponsePage = animalPage
                .map(animal -> this.modelMapper.forResponse().map(animal, AnimalResponse.class));

        return ResultHelper.cursor(animalResponsePage );
    }

    @PutMapping()//hayvan bilgilerini guncelleme
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> update(@Valid @RequestBody AnimalUpdateRequest animalUpdateRequest) {

        Animal updateAnimal = this.modelMapper.forRequest().map(animalUpdateRequest, Animal.class);
        Customer customer = this.customerService.get(animalUpdateRequest.getCustomer().getId());
        this.animalService.update(updateAnimal);

        return ResultHelper.success(this.modelMapper.forResponse().map(updateAnimal, AnimalResponse.class));
    }
    @DeleteMapping("/{id}")//id'si girilen hayvanı silme
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") long id) {
        this.animalService.delete(id);
        return ResultHelper.ok();

    }

    //Değerlendrime formu 16
    //Hayvanları isme göre filtreleme
    @GetMapping("/animalName/{name}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ResultData<List<AnimalResponse>>> filterByNameInPath(@PathVariable("name") String name) {
        List<Animal> filteredAnimals = this.animalService.filterByNameIgnoreCase(name);
        List<AnimalResponse> animalResponses = filteredAnimals
                .stream()
                .map(animal -> this.modelMapper.forResponse().map(animal,AnimalResponse.class))
                .collect(Collectors.toList());
        if (animalResponses.isEmpty()) {
            return ResponseEntity.ok(ResultHelper.notFoundError(animalResponses));
        }
        return ResponseEntity.ok(ResultHelper.success(animalResponses));
    }

    //Değerlendrime formu 20
    @GetMapping("/vaccineName/{animalName}")//hayvan isme göre aşı bilgilerini getirme
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ResultData<List<VaccineResponse>>> getAnimalVaccines(@PathVariable("animalName") String name) {
        List<Animal> animal = this.animalService.filterByName(name);

        //List<Vaccine> filteredVaccines = this.vaccineService.getVaccinesByAnimal(animal);
        List<VaccineResponse> vaccineResponses = animal
                .stream()
                .map(vaccine -> this.modelMapper.forResponse().map(vaccine, VaccineResponse.class))
                .collect(Collectors.toList());
        if (vaccineResponses.isEmpty()) {
            return ResponseEntity.ok(ResultHelper.notFoundError(vaccineResponses));
        }
        return ResponseEntity.ok(ResultHelper.success(vaccineResponses));
    }




}
