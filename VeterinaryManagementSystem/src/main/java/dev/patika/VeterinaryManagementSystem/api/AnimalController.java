package dev.patika.VeterinaryManagementSystem.api;

import dev.patika.VeterinaryManagementSystem.business.abstracts.IAnimalService;
import dev.patika.VeterinaryManagementSystem.business.abstracts.ICustomerService;
import dev.patika.VeterinaryManagementSystem.business.abstracts.IVaccineService;
import dev.patika.VeterinaryManagementSystem.core.config.ModelMapper.IModelMapperService;
import dev.patika.VeterinaryManagementSystem.core.result.Result;
import dev.patika.VeterinaryManagementSystem.core.result.ResultData;
import dev.patika.VeterinaryManagementSystem.core.utilities.ResultHelper;
import dev.patika.VeterinaryManagementSystem.dto.request.animal.AnimalUpdateRequest;
import dev.patika.VeterinaryManagementSystem.dto.response.CursorResponse;
import dev.patika.VeterinaryManagementSystem.dto.response.animal.AnimalResponse;
import dev.patika.VeterinaryManagementSystem.dto.response.vaccine.VaccineResponse;
import dev.patika.VeterinaryManagementSystem.entities.Animal;
import dev.patika.VeterinaryManagementSystem.entities.Customer;
import dev.patika.VeterinaryManagementSystem.entities.Vaccine;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/animals")
public class AnimalController {
    private final IAnimalService animalService;

    private final IVaccineService vaccineService;

    public AnimalController(IAnimalService animalService,

                           IVaccineService vaccineService) {
        this.animalService = animalService;

        this.vaccineService = vaccineService;
    }

    //Değerlendrime formu 11
    @PostMapping()//hayvanları kaydetme islemi
    @ResponseStatus(HttpStatus.CREATED)
    public Optional<Animal> save(@Valid @RequestBody Animal animal) {
        this.animalService.save(animal);
        return Optional.of(animal);

    }

    @GetMapping("/{id}")//id'si girilen hayvan bilgilerini getirme
    @ResponseStatus(HttpStatus.OK)
    public ResultData<Animal> get(@PathVariable("id") long id) {
        return ResultHelper.success(this.animalService.get(id));

    }


    @GetMapping()//kayıtlı tüm hayvan bilgilerini getirme
    @ResponseStatus(HttpStatus.OK)
    public Optional<List<Animal>> cursor() {
        return Optional.of(this.animalService.getAll());
    }


    @PutMapping()//hayvan bilgilerini guncelleme
    @ResponseStatus(HttpStatus.OK)
    public Animal update(@Valid @RequestBody Animal animal) {
        return this.animalService.update(animal);
    }

    @DeleteMapping("/{id}")//id'si girilen hayvanı silme
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        this.animalService.delete(id);
        return ResultHelper.ok();

    }

    //Değerlendrime formu 16
    //Hayvanları isme göre filtreleme
    @GetMapping("/animalName/{animalName}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ResultData<List<Animal>>> filterByNameInPath(@PathVariable("animalName") String name) {
        return ResponseEntity.ok(ResultHelper.success(this.animalService.filterByAnimalName(name)));
    }


    //Değerlendrime formu 20
    @GetMapping("/vaccineName/{animalName}")//hayvan ismine göre aşı bilgilerini getirme
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ResultData<List<Vaccine>>> getAnimalVaccines(@PathVariable("animalName") String name) {
        return ResponseEntity.ok(ResultHelper.success(this.vaccineService.getVaccinesByAnimalName(name)));
    }


}
