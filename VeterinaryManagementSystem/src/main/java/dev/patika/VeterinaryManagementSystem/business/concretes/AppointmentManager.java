package dev.patika.VeterinaryManagementSystem.business.concretes;

import dev.patika.VeterinaryManagementSystem.business.abstracts.IAppointmentService;
import dev.patika.VeterinaryManagementSystem.core.exception.AlreadyExistsException;
import dev.patika.VeterinaryManagementSystem.core.exception.NotFoundException;
import dev.patika.VeterinaryManagementSystem.core.exception.TimeException;
import dev.patika.VeterinaryManagementSystem.core.utilities.Msg;
import dev.patika.VeterinaryManagementSystem.dao.AppointmentRepo;
import dev.patika.VeterinaryManagementSystem.dao.AvaibleDateRepo;
import dev.patika.VeterinaryManagementSystem.entities.Appointment;
import dev.patika.VeterinaryManagementSystem.entities.AvaibleDate;
import dev.patika.VeterinaryManagementSystem.entities.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class AppointmentManager implements IAppointmentService {

    private final AppointmentRepo appointmentRepo;
    private final AvaibleDateRepo avaibleDateRepo;

    public AppointmentManager(AppointmentRepo appointmentRepo, AvaibleDateRepo avaibleDateRepo) {
        this.appointmentRepo = appointmentRepo;
        this.avaibleDateRepo = avaibleDateRepo;
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
            // Assuming appointments are stored as LocalDateTime in the Appointment object
            LocalDateTime existingAppointmentTime = existingAppointment.getAppointmentDate();

            if (existingAppointmentTime != null &&
                    existingAppointmentTime.toLocalDate().equals(appointmentDateTime.toLocalDate()) &&
                    existingAppointmentTime.toLocalTime().equals(appointmentDateTime.toLocalTime())) {
                return false; // Existing appointment at the same time
            }
        }
        return true; // No existing appointment at the same time
    }

    @Override
    public Appointment get(long id) {
        return this.appointmentRepo.findById(id).orElseThrow(()->new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Page<Appointment> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.appointmentRepo.findAll(pageable);
    }

    @Override
    public Appointment update(Appointment appointment) {
        this.get(appointment.getId());
        return this.appointmentRepo.save(appointment);
    }

    @Override
    public boolean delete(long id) {
        Appointment appointment = this.get(id);
        this.appointmentRepo.delete(appointment);
        return true;
    }

    //

    @Override
    public List<Appointment> getByAnimalIdAndAppointmentDate(long animalId, LocalDateTime starDate, LocalDateTime endDate) {
        if (this.appointmentRepo.getByAnimalIdAndAppointmentDate(animalId, starDate, endDate).isEmpty()) {
            throw new NotFoundException(Msg.NOT_FOUND);
        }
        return this.appointmentRepo.getByAnimalIdAndAppointmentDate(animalId, starDate, endDate);
    }

    //Değerlendrime formu 24

    @Override
    public List<Appointment> getByDoctorIdAndAppointmentDate(long doctorId, LocalDateTime startDate, LocalDateTime endDate) {
        if(this.appointmentRepo.getByDoctorIdAndAppointmentDate(doctorId, startDate, endDate).isEmpty()){
            throw new NotFoundException(Msg.NOT_FOUND);
        }
        return this.appointmentRepo.getByDoctorIdAndAppointmentDate(doctorId, startDate, endDate);
    }


}
