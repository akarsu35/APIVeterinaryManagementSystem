package dev.patika.VeterinaryManagementSystem.api;

import dev.patika.VeterinaryManagementSystem.business.abstracts.IAnimalService;
import dev.patika.VeterinaryManagementSystem.business.abstracts.IAppointmentService;
import dev.patika.VeterinaryManagementSystem.business.abstracts.IDoctorService;
import dev.patika.VeterinaryManagementSystem.core.config.ModelMapper.IModelMapperService;
import dev.patika.VeterinaryManagementSystem.core.result.Result;
import dev.patika.VeterinaryManagementSystem.core.result.ResultData;
import dev.patika.VeterinaryManagementSystem.core.utilities.ResultHelper;
import dev.patika.VeterinaryManagementSystem.dto.request.appointment.AppointmentSaveRequest;
import dev.patika.VeterinaryManagementSystem.dto.request.appointment.AppointmentUpdateRequest;
import dev.patika.VeterinaryManagementSystem.dto.response.CursorResponse;
import dev.patika.VeterinaryManagementSystem.dto.response.appointment.AppointmentResponse;
import dev.patika.VeterinaryManagementSystem.entities.Animal;
import dev.patika.VeterinaryManagementSystem.entities.Appointment;
import dev.patika.VeterinaryManagementSystem.entities.Doctor;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/appointments")
public class AppointmentController {
    private final IAppointmentService appointmentService;
    private final IModelMapperService modelMapper;

    public AppointmentController(IAppointmentService appointmentService,
                                 IModelMapperService modelMapper
                                 ) {
        this.appointmentService = appointmentService;
        this.modelMapper = modelMapper;

    }

    //Değerlendrime formu 14
    //Değerlendrime formu 22-->AppointmentManager save methodunda
    @PostMapping()//randevu kaydetme
    @ResponseStatus(HttpStatus.CREATED)
    public AppointmentResponse save(@Valid @RequestBody AppointmentSaveRequest appointmentSaveRequest) {
        return this.modelMapper.forResponse().map(this.appointmentService.save(this.modelMapper.forRequest().map(appointmentSaveRequest, Appointment.class)), AppointmentResponse.class);

    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AppointmentResponse get(@PathVariable("id") long id) {
        return this.appointmentService.get(id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<AppointmentResponse> cursor() {
        return this.appointmentService.getAll();
    }


    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public AppointmentResponse update(@Valid @RequestBody AppointmentUpdateRequest appointmentUpdateRequest) {
        return this.appointmentService.update(appointmentUpdateRequest);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") long id) {
        this.appointmentService.delete(id);
        return ResultHelper.ok();

    }

    //Değerlendrime formu 23
    @GetMapping("/getByAnimalIdAndAppointmentDate/{animalId}/{startDate}/{endDate}")//girilen tarih aralığına ve hayvan id sine göre randevuları getirir.
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<Appointment>>getByAnimalIdAndAppointmentDate(
            @PathVariable("animalId") Long animalId,
            @PathVariable("startDate") LocalDateTime startDate,
            @PathVariable("endDate") LocalDateTime endDate
    ){
        return ResultHelper.success(appointmentService.getByAnimalIdAndAppointmentDate(animalId, startDate, endDate));
    }


    //Değerlendrime formu 24

    @GetMapping("/getByDoctorIdAndAppointmentDate/{doctorId}/{startDate}/{endDate}")//girilen tarih aralığına ve doktor id sine göre randevuları getirir.
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<Appointment>> getByDoctorIdAndAppointmentDate(
           @PathVariable("doctorId") Long doctorId,
           @PathVariable("startDate") LocalDateTime startDate,
           @PathVariable("endDate") LocalDateTime endDate
    ){

        return ResultHelper.success(appointmentService.getByDoctorIdAndAppointmentDate(doctorId, startDate,endDate));
    }


}
