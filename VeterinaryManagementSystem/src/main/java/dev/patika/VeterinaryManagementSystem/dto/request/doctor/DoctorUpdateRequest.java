package dev.patika.VeterinaryManagementSystem.dto.request.doctor;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorUpdateRequest {
    @Positive(message = "ID değeri pozitif olmak zorunda")
    private long id;

    @NotNull(message = "Doctor adı boş olamaz")
    private String name;
    @NotNull(message = "Doctor telefonu boş olamaz")
    private String phone;

    private String mail;
    private String address;
    private String city;
}
