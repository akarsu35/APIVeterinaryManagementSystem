package dev.patika.VeterinaryManagementSystem.dto.request.animal;

import dev.patika.VeterinaryManagementSystem.entities.Customer;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalUpdateRequest {
    @NotNull(message = "Animal id'si boş olamaz")
    private long id;

    @NotNull(message = "Animal adı boş olamaz")
    private String name;

    private String species;

    private String breed;
    @NotNull(message = "Cinsiyet boş olamaz")
    private String gender;

    private String color;

    @NotNull(message = "Doğum tarihi boş olamaz")
    private LocalDate dateOfBirth;
    private Customer customer;
}
