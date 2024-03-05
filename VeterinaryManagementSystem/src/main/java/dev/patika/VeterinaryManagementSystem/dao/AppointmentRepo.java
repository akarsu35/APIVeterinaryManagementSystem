package dev.patika.VeterinaryManagementSystem.dao;

import dev.patika.VeterinaryManagementSystem.entities.Animal;
import dev.patika.VeterinaryManagementSystem.entities.Appointment;
import dev.patika.VeterinaryManagementSystem.entities.AvaibleDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import javax.crypto.spec.PSource;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Long> {

   boolean existsByAnimalIdAndDoctorId(Long animalId, Long doctorId);
   @Query("SELECT a FROM Appointment a WHERE a.appointmentDate BETWEEN :startDate AND :endDate AND a.animal.id = :animalId")
   List<Appointment> getByAnimalIdAndAppointmentDate(@Param("animalId") long animalId,
                                                     @Param("startDate") LocalDateTime startDate,
                                                     @Param("endDate") LocalDateTime endDate);

   @Query("SELECT a FROM Appointment a WHERE a.appointmentDate BETWEEN :startDate AND :endDate AND a.doctor.id = :doctorId")
   List<Appointment> getByDoctorIdAndAppointmentDate(@Param("doctorId") long doctorId,
                                                     @Param("startDate") LocalDateTime startDate,
                                                     @Param("endDate") LocalDateTime endDate);

   @Query("SELECT a FROM AvaibleDate a WHERE a.availableDate = :date AND a.doctor.id = :doctorId")
   List<AvaibleDate> checkAvailableDatesByDoctor(@Param("doctorId") long doctorId,
                                                 @Param("date") LocalDate date);

   @Query("SELECT a FROM Appointment a WHERE a.appointmentDate = :date AND a.doctor.id = :doctorId")
   List<Appointment> checkAppointmentsDatesByDoctor(@Param("doctorId") long animalId,
                                                 @Param("date") LocalDateTime date);



}