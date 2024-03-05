package dev.patika.VeterinaryManagementSystem.dto.request.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSaveRequest {
    @NotNull(message = "Customer adı boş olamaz")
    private String name;

    @NotNull(message = "Customer telefonu boş olamaz")
    private String phone;

    @Email
    private String mail;

    private String address;

    private String city;
}
