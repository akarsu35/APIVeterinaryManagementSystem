package dev.patika.VeterinaryManagementSystem.dto.request.doctor;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorSaveRequest {
    @NotNull(message = "Doctor adı boş olamaz")
    private String name;
    @NotNull(message = "Doctor telefonu boş olamaz")
    private String phone;

    private String mail;
    private String address;
    private String city;
}
