package dev.patika.VeterinaryManagementSystem.api;

import dev.patika.VeterinaryManagementSystem.business.abstracts.IAnimalService;
import dev.patika.VeterinaryManagementSystem.business.abstracts.IAppointmentService;
import dev.patika.VeterinaryManagementSystem.business.abstracts.IDoctorService;
import dev.patika.VeterinaryManagementSystem.core.config.ModelMapper.IModelMapperService;
import dev.patika.VeterinaryManagementSystem.core.result.Result;
import dev.patika.VeterinaryManagementSystem.core.result.ResultData;
import dev.patika.VeterinaryManagementSystem.core.utilities.Msg;
import dev.patika.VeterinaryManagementSystem.core.utilities.ResultHelper;
import dev.patika.VeterinaryManagementSystem.dto.request.animal.AnimalSaveRequest;
import dev.patika.VeterinaryManagementSystem.dto.request.animal.AnimalUpdateRequest;
import dev.patika.VeterinaryManagementSystem.dto.request.appointment.AppointmentSaveRequest;
import dev.patika.VeterinaryManagementSystem.dto.request.appointment.AppointmentUpdateRequest;
import dev.patika.VeterinaryManagementSystem.dto.response.CursorResponse;
import dev.patika.VeterinaryManagementSystem.dto.response.animal.AnimalResponse;
import dev.patika.VeterinaryManagementSystem.dto.response.appointment.AppointmentResponse;
import dev.patika.VeterinaryManagementSystem.entities.Animal;
import dev.patika.VeterinaryManagementSystem.entities.Appointment;
import dev.patika.VeterinaryManagementSystem.entities.Customer;
import dev.patika.VeterinaryManagementSystem.entities.Doctor;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/appointments")
public class AppointmentController {
    private final IAppointmentService appointmentService;
    private final IModelMapperService modelMapper;
    private final IDoctorService doctorService;
    private final IAnimalService animalService;

    public AppointmentController(IAppointmentService appointmentService,
                                 IModelMapperService modelMapper,
                                 IDoctorService doctorService,
                                 IAnimalService animalService) {
        this.appointmentService = appointmentService;
        this.modelMapper = modelMapper;
        this.doctorService = doctorService;
        this.animalService = animalService;
    }

    //Değerlendrime formu 14
    //Değerlendrime formu 22-->AppointmentManager save methodunda
    @PostMapping()//randevu kaydetme
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AppointmentResponse> save(@Valid @RequestBody AppointmentSaveRequest appointmentSaveRequest) {
        Appointment saveAppointment = this.modelMapper.forRequest().map(appointmentSaveRequest, Appointment.class);
        //animal entiti içinde customer_id var dolayısıyla bu animala ait customer_id ve customer bilgilerini ekliyoruz,
        //eğer başka entitiler ile benzer ilişki varsa onları da benzer şekilde eklemeliyiz.
        Doctor doctor = this.doctorService.get(appointmentSaveRequest.getDoctor().getId());
        saveAppointment.setDoctor(doctor);

        Animal animal = this.animalService.get(appointmentSaveRequest.getAnimal().getId());
        saveAppointment.setAnimal(animal);

        this.appointmentService.save(saveAppointment);

        return ResultHelper.created(this.modelMapper.forResponse().map(saveAppointment, AppointmentResponse.class));
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AppointmentResponse> get(@PathVariable("id") long id) {
        Appointment appointment = this.appointmentService.get(id);
        AppointmentResponse appointmentResponse = this.modelMapper.forResponse().map(appointment, AppointmentResponse.class);
        return ResultHelper.success(appointmentResponse);
    }
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AppointmentResponse>> cursor(
            @RequestParam(value = "page", required = false,defaultValue = "0") int page,
            @RequestParam(value = "pageSize", required = false,defaultValue = "4") int pageSize
    ){
        Page<Appointment> appointmentPage = this.appointmentService.cursor(page, pageSize);
        Page<AppointmentResponse> appointmentResponsePage = appointmentPage
                .map(appointment -> this.modelMapper.forResponse().map(appointment, AppointmentResponse.class));

        return ResultHelper.cursor(appointmentResponsePage );
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AppointmentResponse> update(@Valid @RequestBody AppointmentUpdateRequest appointmentUpdateRequest) {

        Appointment updateAppointment = this.modelMapper.forRequest().map(appointmentUpdateRequest, Appointment.class);

        Doctor doctor = this.doctorService.get(appointmentUpdateRequest.getDoctor().getId());
        Animal animal = this.animalService.get(appointmentUpdateRequest.getAnimal().getId());
        updateAppointment.setDoctor(doctor);
        updateAppointment.setAnimal(animal);

        this.appointmentService.update(updateAppointment);

        return ResultHelper.success(this.modelMapper.forResponse().map(updateAppointment, AppointmentResponse.class));
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") long id) {
        this.appointmentService.delete(id);
        return ResultHelper.ok();

    }

    //Değerlendrime formu 23
    @GetMapping("/getByAnimalIdAndAppointmentDate")//girilen tarih aralığına ve hayvan id sine göre randevuları getirir.
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AppointmentResponse>> getByAnimalIdAndAppointmentDate(
            @RequestParam(value = "animalId") long animalId,
            @RequestParam(value = "starDate")LocalDateTime startDate,
            @RequestParam(value = "endDate")LocalDateTime endDate
    ){
        List<Appointment> appointmentList = this.appointmentService.getByAnimalIdAndAppointmentDate(animalId, startDate,endDate);
        List<AppointmentResponse> appointmentResponseList = appointmentList.stream()
                .map(appointment -> this.modelMapper.forResponse().map(appointment, AppointmentResponse.class))
                .collect(Collectors.toList());
        return ResultHelper.success(appointmentResponseList);
    }

    //Değerlendrime formu 24

    @GetMapping("/getByDoctorIdAndAppointmentDate")//girilen tarih aralığına ve doktor id sine göre randevuları getirir.
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AppointmentResponse>> getByDoctorIdAndAppointmentDate(
            @RequestParam(value = "doctorId") long doctorId,
            @RequestParam(value = "starDate")LocalDateTime startDate,
            @RequestParam(value = "endDate")LocalDateTime endDate
    ){
        List<Appointment> appointmentList = this.appointmentService.getByDoctorIdAndAppointmentDate(doctorId, startDate,endDate);
        List<AppointmentResponse> appointmentResponseList = appointmentList.stream()
                .map(appointment -> this.modelMapper.forResponse().map(appointment, AppointmentResponse.class))
                .collect(Collectors.toList());
        return ResultHelper.success(appointmentResponseList);
    }


}
