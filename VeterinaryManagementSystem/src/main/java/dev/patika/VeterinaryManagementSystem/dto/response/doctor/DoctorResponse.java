package dev.patika.VeterinaryManagementSystem.dto.response.doctor;

import dev.patika.VeterinaryManagementSystem.entities.AvaibleDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorResponse {

    private long id;
    private String name;
    private String phone;
    private String mail;
    private String address;
    private String city;

}
