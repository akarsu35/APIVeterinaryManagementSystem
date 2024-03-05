package dev.patika.VeterinaryManagementSystem.dao;

import dev.patika.VeterinaryManagementSystem.entities.AvaibleDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;

@Repository
public interface AvaibleDateRepo extends JpaRepository<AvaibleDate, Long> {

    boolean existsByAvailableDateAndDoctorId(LocalDate availableDate, long id);
}
