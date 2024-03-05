package dev.patika.VeterinaryManagementSystem.entities;

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
    private long id;
    private String name;
    private String phone;

    @Column(unique = true)
    private String mail;
    private String address;
    private String city;

    @OneToMany(mappedBy = "doctor")
    private List<Appointment> appointmentList;

    @OneToMany(mappedBy = "doctor")
    private List<AvaibleDate> avaibleDateList;

}
