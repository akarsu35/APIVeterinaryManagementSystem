package dev.patika.VeterinaryManagementSystem.dto.response.avaibledate;

import dev.patika.VeterinaryManagementSystem.entities.Doctor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvaibleDateResponse {

    private long id;
    private LocalDate availableDate;
    private Doctor doctor;
}
