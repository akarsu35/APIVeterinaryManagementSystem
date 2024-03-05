package dev.patika.VeterinaryManagementSystem.dto.request.customer;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerUpdateRequest {

    @Positive(message = "ID değeri pozitif olmak zorunda")
    private Long id;

    @NotNull(message = "Customer adı boş olamaz")
    private String name;

    @NotNull(message = "Customer telefonu boş olamaz")
    private String phone;


    private String mail;
    private String address;
    private String city;
}
