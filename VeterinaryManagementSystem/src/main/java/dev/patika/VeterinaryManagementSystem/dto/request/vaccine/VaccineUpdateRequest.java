package dev.patika.VeterinaryManagementSystem.dto.request.vaccine;

import dev.patika.VeterinaryManagementSystem.entities.Animal;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VaccineUpdateRequest {
    @Positive(message = "ID değeri pozitif olmak zorunda")
    private Long id;
    @NotNull(message = "Aşı adı boş olamaz")
    private String name;

    private String code;
    @NotNull(message = "Aşı başlangıç tarihi boş olamaz")
    private LocalDate protectionStartDate;
    @NotNull(message = "Aşı etkisi bitiş tarihi boş olamaz")
    private LocalDate protectionFinishDate;
    private Animal animal = new Animal();
}
