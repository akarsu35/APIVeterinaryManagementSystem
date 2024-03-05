package dev.patika.VeterinaryManagementSystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "customers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;


    private String name;

    private String phone;

    @Column(unique = true)
    private String mail;

    private String address;

    private String city;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY ,mappedBy = "customer", cascade = CascadeType.REMOVE)
    private List<Animal> animalList;

}
