package dev.patika.VeterinaryManagementSystem.dto.response.vaccine;

import dev.patika.VeterinaryManagementSystem.entities.Animal;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VaccineResponse {
    private long id;

    private String name;

    private String code;

    private LocalDate protectionStartDate;

    private LocalDate protectionFinishDate;
    private Animal animal;
}
