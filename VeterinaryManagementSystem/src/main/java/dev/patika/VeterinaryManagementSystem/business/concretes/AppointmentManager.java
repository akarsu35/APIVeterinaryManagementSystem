package dev.patika.VeterinaryManagementSystem.business.concretes;

import dev.patika.VeterinaryManagementSystem.business.abstracts.IAppointmentService;
import dev.patika.VeterinaryManagementSystem.core.config.ModelMapper.IModelMapperService;
import dev.patika.VeterinaryManagementSystem.core.exception.AlreadyExistsException;
import dev.patika.VeterinaryManagementSystem.core.exception.NotFoundException;
import dev.patika.VeterinaryManagementSystem.core.exception.TimeException;
import dev.patika.VeterinaryManagementSystem.core.utilities.Msg;
import dev.patika.VeterinaryManagementSystem.dao.AnimalRepo;
import dev.patika.VeterinaryManagementSystem.dao.AppointmentRepo;
import dev.patika.VeterinaryManagementSystem.dao.AvaibleDateRepo;
import dev.patika.VeterinaryManagementSystem.dao.DoctorRepo;
import dev.patika.VeterinaryManagementSystem.dto.request.appointment.AppointmentUpdateRequest;
import dev.patika.VeterinaryManagementSystem.dto.response.appointment.AppointmentResponse;
import dev.patika.VeterinaryManagementSystem.entities.Animal;
import dev.patika.VeterinaryManagementSystem.entities.Appointment;
import dev.patika.VeterinaryManagementSystem.entities.AvaibleDate;
import dev.patika.VeterinaryManagementSystem.entities.Doctor;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentManager implements IAppointmentService {

    private final AppointmentRepo appointmentRepo;

    private final IModelMapperService modelMapper;
    private final AnimalRepo animalRepo;
    private final DoctorRepo doctorRepo;


    public AppointmentManager(AppointmentRepo appointmentRepo, IModelMapperService modelMapper, AnimalRepo animalRepo, DoctorRepo doctorRepo) {
        this.appointmentRepo = appointmentRepo;

        this.modelMapper = modelMapper;

        this.animalRepo = animalRepo;
        this.doctorRepo = doctorRepo;
    }

    //Değerlendrime formu 22
    //Randevu oluştururken doktorun gün ve saat uygunluğu kontrolü

    @Override
    public Appointment save(Appointment appointment) {

        LocalTime appointmentTime = appointment.getAppointmentDate().toLocalTime();
        if (appointmentTime.getMinute() != 0 || appointmentTime.getSecond() != 0) {
            throw new TimeException(Msg.TIME_ERROR); //Hayvanların her türlü muayenesi için doktorlardan uygun tarihlerde ve saatlerde randevu oluşturulmalıdır. Her doktor için sadece saat başı randevu oluşturulabilir. Bir muayenenin sabit olarak bir saat süreceğini kabul edin.
        }

        List<AvaibleDate> availableDates = this.appointmentRepo.checkAvailableDatesByDoctor(appointment.getDoctor().getId(), appointment.getAppointmentDate().toLocalDate());
        if (!availableDates.isEmpty()) {
            List<Appointment> appointments = this.appointmentRepo.checkAppointmentsDatesByDoctor(appointment.getDoctor().getId(),appointment.getAppointmentDate());
            if (appointments.isEmpty()) {
                return this.appointmentRepo.save(appointment);
            } else {
                throw new AlreadyExistsException("Doktorun bu satte başka bir randevusu mevcuttur");
            }
        } else {
            throw new NotFoundException("Doktorun girilen gün için müsaitliği bulunmamaktadır.");
        }

    }

    private boolean isAppointmentTimeAvailable(LocalDateTime appointmentDateTime, Doctor doctor) {
        for (Appointment existingAppointment : doctor.getAppointmentList()) {

            LocalDateTime existingAppointmentTime = existingAppointment.getAppointmentDate();

            if (existingAppointmentTime != null &&
                    existingAppointmentTime.toLocalDate().equals(appointmentDateTime.toLocalDate()) &&
                    existingAppointmentTime.toLocalTime().equals(appointmentDateTime.toLocalTime())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public AppointmentResponse get(long id) {
        AppointmentResponse appointmentResponse = this.modelMapper.forResponse().map(this.appointmentRepo.findById(id).orElseThrow(()->new NotFoundException(Msg.NOT_FOUND)), AppointmentResponse.class);
        return appointmentResponse;


    }

    @Override
    public List<AppointmentResponse> getAll() {

        return this.appointmentRepo.findAll().stream()
                .map(appointment -> this.modelMapper.forResponse()
                        .map(appointment, AppointmentResponse.class))
                .collect(Collectors.toList());
    }


    @Override
    public AppointmentResponse update(AppointmentUpdateRequest appointmentUpdateRequest) {
        this.get(appointmentUpdateRequest.getId());
        // Hayvanı kontrol et
        Animal animal = appointmentUpdateRequest.getAnimal();
        if (animal != null && animal.getId() != null && !animalRepo.existsById(animal.getId())) {
            throw new NotFoundException("Hayvan bulunamadı");
        }

        // Doktoru kontrol et
        Doctor doctor = appointmentUpdateRequest.getDoctor();
        if (doctor != null && doctor.getId() != null && !doctorRepo.existsById(doctor.getId())) {
            throw new NotFoundException("Doktor bulunamadı");
        }
        if (this.get(appointmentUpdateRequest.getId()) == null) {
            throw new NotFoundException(Msg.NOT_FOUND);
        }
        //this.modelMapper.forResponse().map(appointmentUpdateRequest, AppointmentResponse.class);
        return this.modelMapper.forResponse().map(this.appointmentRepo.save(this.modelMapper.forRequest().map(appointmentUpdateRequest, Appointment.class)), AppointmentResponse.class);

    }

    @Override
    public boolean delete(long id) {
        AppointmentResponse appointment = this.get(id);
        this.appointmentRepo.deleteById(id);
        return true;
    }

    //

    @Override
    public List<Appointment> getByAnimalIdAndAppointmentDate(Long animalId, LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate.isAfter(endDate)) {
            throw new TimeException("Başlangıç tarihi, bitiş tarihinden sonra olamaz.");
        }

        List<Appointment> appointmentList = appointmentRepo.getByAnimalIdAndAppointmentDate(animalId, startDate, endDate);

        if (appointmentList.isEmpty()) {
            throw new NotFoundException(Msg.NOT_FOUND);
        }

        return appointmentList;
    }


    //Değerlendrime formu 24

    @Override
    public List<Appointment> getByDoctorIdAndAppointmentDate(long doctorId, LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate.isAfter(endDate)) {//tarih kontrolu
            throw new TimeException("başlangıç tarihi, bitiş tarihinden sonra olamaz.");
        }
        if(this.appointmentRepo.getByDoctorIdAndAppointmentDate(doctorId, startDate, endDate).isEmpty()){
            throw new NotFoundException(Msg.NOT_FOUND);
        }
        return this.appointmentRepo.getByDoctorIdAndAppointmentDate(doctorId, startDate, endDate);
    }


}
