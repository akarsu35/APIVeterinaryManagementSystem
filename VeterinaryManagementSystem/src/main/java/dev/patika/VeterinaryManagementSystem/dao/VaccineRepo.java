package dev.patika.VeterinaryManagementSystem.dao;

import dev.patika.VeterinaryManagementSystem.entities.Animal;
import dev.patika.VeterinaryManagementSystem.entities.Vaccine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface VaccineRepo extends JpaRepository<Vaccine, Long> {

    List<Vaccine> getVaccinesByAnimal(Animal animal);
    @Query("SELECT v FROM Vaccine v WHERE v.animal.id = :animalId AND v.code = :code AND v.protectionFinishDate BETWEEN :startDate AND :endDate")
    List<Vaccine> getVaccinesByAnimalIdAndCodeAndByDate(@Param("animalId") Long animalId,
                                                         @Param("code") String code,
                                                         @Param("startDate") LocalDate startDate,
                                                         @Param("endDate") LocalDate endDate);

    //Vaccine findByNameAndCodeAndProtectionStartDateAndProtectionFinishDate(String name, String code, LocalDate startDate, LocalDate endDate);
    
    Vaccine findByCodeAndName(String code, String name);

    // Kullanıcı tarafından girilen tarih aralığına göre aşı koruyuculuk bitiş tarihi bu aralıkta olan hayvanları listeleme
    @Query("SELECT v FROM Vaccine v WHERE v.protectionFinishDate BETWEEN :startDate AND :endDate")
    List<Vaccine> getVaccinesByDate(@Param("startDate") LocalDate startDate,
                                    @Param("endDate") LocalDate endDate);



}
