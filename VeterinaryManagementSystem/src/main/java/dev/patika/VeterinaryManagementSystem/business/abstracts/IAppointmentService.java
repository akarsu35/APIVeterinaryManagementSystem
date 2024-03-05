package dev.patika.VeterinaryManagementSystem.business.abstracts;

import dev.patika.VeterinaryManagementSystem.entities.Animal;
import dev.patika.VeterinaryManagementSystem.entities.Appointment;
import dev.patika.VeterinaryManagementSystem.entities.AvaibleDate;
import org.springframework.data.domain.Page;


import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

public interface IAppointmentService {
    Appointment save(Appointment appointment);

    Appointment get(long id);
    Page<Appointment> cursor(int page, int pageSize);
    Appointment update(Appointment appointment);

    boolean delete(long id);
    List<Appointment> getByAnimalIdAndAppointmentDate(long animalId, LocalDateTime startDate, LocalDateTime endDate);
    List<Appointment> getByDoctorIdAndAppointmentDate(long doctorId, LocalDateTime startDate, LocalDateTime endDate);


}
