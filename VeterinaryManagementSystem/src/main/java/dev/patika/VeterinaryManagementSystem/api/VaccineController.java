package dev.patika.VeterinaryManagementSystem.api;

import dev.patika.VeterinaryManagementSystem.business.abstracts.IAnimalService;
import dev.patika.VeterinaryManagementSystem.business.abstracts.IVaccineService;
import dev.patika.VeterinaryManagementSystem.business.concretes.VaccineManager;
import dev.patika.VeterinaryManagementSystem.core.config.ModelMapper.IModelMapperService;
import dev.patika.VeterinaryManagementSystem.core.result.Result;
import dev.patika.VeterinaryManagementSystem.core.result.ResultData;
import dev.patika.VeterinaryManagementSystem.core.utilities.ResultHelper;
import dev.patika.VeterinaryManagementSystem.dto.request.vaccine.VaccineSaveRequest;
import dev.patika.VeterinaryManagementSystem.dto.request.vaccine.VaccineUpdateRequest;
import dev.patika.VeterinaryManagementSystem.dto.response.CursorResponse;
import dev.patika.VeterinaryManagementSystem.dto.response.vaccine.VaccineResponse;
import dev.patika.VeterinaryManagementSystem.entities.Animal;
import dev.patika.VeterinaryManagementSystem.entities.Vaccine;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/vaccines")
public class VaccineController {
    private final IVaccineService vaccineService;





    public VaccineController(IVaccineService vaccineService) {//DI
        this.vaccineService = vaccineService;

    }

    //Değerlendrime formu 15
    //Değerlendrime formu 19-->VaccineManager save methodunda
    @PostMapping()//hayvana ait aşı kaydetme
    @ResponseStatus(HttpStatus.CREATED)
    public VaccineResponse save(@Valid @RequestBody VaccineSaveRequest vaccineSaveRequest) {
        return this.vaccineService.save(vaccineSaveRequest);
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VaccineResponse get(@PathVariable("id") long id) {
        return this.vaccineService.get(id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<VaccineResponse> cursor(){
        return this.vaccineService.getAll();
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public VaccineResponse update(@Valid @RequestBody VaccineUpdateRequest vaccineUpdateRequest) {
        return this.vaccineService.update(vaccineUpdateRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") long id) {
        this.vaccineService.delete(id);
        return ResultHelper.ok();

    }

    // Kullanıcı tarafından girilen tarih aralığına göre aşı koruyuculuk bitiş tarihi bu aralıkta olan hayvanları listeleme
    //Değerleştirme formu 21
    @GetMapping("/date/{startDate}/{endDate}")
    public List<Vaccine> getVaccinesByDate(
        @PathVariable("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
        @PathVariable("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        return this.vaccineService.getVaccinesByDate(startDate, endDate);
    }





}
