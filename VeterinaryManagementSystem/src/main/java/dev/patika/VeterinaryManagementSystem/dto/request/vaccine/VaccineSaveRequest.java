package dev.patika.VeterinaryManagementSystem.dto.request.vaccine;

import dev.patika.VeterinaryManagementSystem.entities.Animal;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VaccineSaveRequest {
    @NotNull(message = "Aşı adı boş olamaz")
    private String name;


    private String code;

    @NotNull(message = "Aşı başlangıç tarihi boş olamaz")
    private LocalDate protectionStartDate;

    @NotNull(message = "Aşı etkisi bitiş tarihi boş olamaz")
    private LocalDate protectionFinishDate;
    private Animal animal = new Animal();
}
