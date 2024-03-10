package dev.patika.VeterinaryManagementSystem.dao;

import dev.patika.VeterinaryManagementSystem.entities.AvaibleDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AvaibleDateRepo extends JpaRepository<AvaibleDate, Long> {

    boolean existsByAvailableDateAndDoctorId(LocalDate availableDate, long id);

    Optional<AvaibleDate> findByAvailableDateAndDoctorId(LocalDate availableDate, long id);

}
