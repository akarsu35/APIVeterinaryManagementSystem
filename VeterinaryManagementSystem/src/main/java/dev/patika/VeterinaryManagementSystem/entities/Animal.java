package dev.patika.VeterinaryManagementSystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "animals")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private String name;

    private String species;
    private String breed;
    private String gender;
    private String color;

    @Temporal(TemporalType.DATE)
    private LocalDate dateOfBirth;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;


    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY ,mappedBy = "animal", cascade = CascadeType.REMOVE)
    private List<Vaccine> vaccineList;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY ,mappedBy = "animal", cascade = CascadeType.REMOVE)
    private List<Appointment> appointmentList;


}
