package dev.patika.VeterinaryManagementSystem.dto.request.avaibledate;

import dev.patika.VeterinaryManagementSystem.entities.Doctor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvaibleDateUpdateRequest {
    @Positive(message = "ID değeri pozitif olmak zorunda")
    private long id;
    @NotNull(message = "Tarih boş olamaz")
    private LocalDate availableDate;
    private Doctor doctor = new Doctor();
}
