package dev.patika.VeterinaryManagementSystem.dto.request.avaibledate;

import dev.patika.VeterinaryManagementSystem.entities.Doctor;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvaibleDateSaveRequest {
    @NotNull(message = "Tarih boş olamaz")
    private LocalDate availableDate;
    private Doctor doctor = new Doctor();
}
