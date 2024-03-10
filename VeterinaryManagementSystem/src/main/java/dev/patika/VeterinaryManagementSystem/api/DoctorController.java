package dev.patika.VeterinaryManagementSystem.api;

import dev.patika.VeterinaryManagementSystem.business.abstracts.IDoctorService;
import dev.patika.VeterinaryManagementSystem.business.concretes.DoctorManager;
import dev.patika.VeterinaryManagementSystem.core.config.ModelMapper.IModelMapperService;
import dev.patika.VeterinaryManagementSystem.core.result.Result;
import dev.patika.VeterinaryManagementSystem.core.result.ResultData;
import dev.patika.VeterinaryManagementSystem.core.utilities.ResultHelper;
import dev.patika.VeterinaryManagementSystem.dto.response.CursorResponse;
import dev.patika.VeterinaryManagementSystem.dto.response.doctor.DoctorResponse;
import dev.patika.VeterinaryManagementSystem.entities.Doctor;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/doctors")
public class DoctorController {
    private final IDoctorService doctorService;


    public DoctorController(IDoctorService doctorService) {
        this.doctorService = doctorService;

    }

    //Değerlendrime formu 12
    @PostMapping()//doktor kaydetme
    @ResponseStatus(HttpStatus.CREATED)
    public Optional<Doctor> save(@Valid @RequestBody Doctor doctor) {
        this.doctorService.save(doctor);
        return Optional.of(doctor);
    }
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<DoctorResponse> cursor() {
        return this.doctorService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DoctorResponse get(@PathVariable("id") Long id) {
        return this.doctorService.get(id);
    }


    @PutMapping()
    //@ResponseStatus(HttpStatus.OK)
    public Doctor update(@Valid @RequestBody Doctor doctor) {
        return this.doctorService.update(doctor);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        this.doctorService.delete(id);
        return ResultHelper.ok();

    }
}
