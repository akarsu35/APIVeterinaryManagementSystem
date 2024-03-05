package dev.patika.VeterinaryManagementSystem.business.abstracts;

import dev.patika.VeterinaryManagementSystem.entities.Customer;
import dev.patika.VeterinaryManagementSystem.entities.Doctor;
import org.springframework.data.domain.Page;

public interface IDoctorService{
    Doctor save(Doctor doctor);

    Doctor get(long id);
    Page<Doctor> cursor(int page, int pageSize);
    Doctor update(Doctor doctor);

    boolean delete(long id);
}
