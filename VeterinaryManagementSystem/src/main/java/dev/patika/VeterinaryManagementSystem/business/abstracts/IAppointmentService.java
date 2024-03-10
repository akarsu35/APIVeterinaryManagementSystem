package dev.patika.VeterinaryManagementSystem.business.abstracts;

import dev.patika.VeterinaryManagementSystem.dto.request.appointment.AppointmentUpdateRequest;
import dev.patika.VeterinaryManagementSystem.dto.response.appointment.AppointmentResponse;
import dev.patika.VeterinaryManagementSystem.entities.Appointment;


import java.time.LocalDateTime;
import java.util.List;

public interface IAppointmentService {
    Appointment save(Appointment appointment);

    //Appointment get(long id);
    AppointmentResponse get(long id);
    List<AppointmentResponse> getAll();
    //Page<Appointment> cursor(int page, int pageSize);
    //Appointment update(Appointment appointment);
    AppointmentResponse update(AppointmentUpdateRequest appointmentUpdateRequest);

    boolean delete(long id);
    List<Appointment> getByAnimalIdAndAppointmentDate(Long animalId, LocalDateTime startDate, LocalDateTime endDate);
    List<Appointment> getByDoctorIdAndAppointmentDate(long doctorId, LocalDateTime startDate, LocalDateTime endDate);


}
