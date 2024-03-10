package dev.patika.VeterinaryManagementSystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phone;

    @Column(unique = true)
    private String mail;
    private String address;
    private String city;

    @JsonIgnore
    @OneToMany(mappedBy = "doctor",cascade = CascadeType.REMOVE)
    private List<Appointment> appointmentList;

    @JsonIgnore
    @OneToMany(mappedBy = "doctor",cascade = CascadeType.REMOVE)
    private List<AvaibleDate> avaibleDateList;


}
