package dev.patika.VeterinaryManagementSystem.business.abstracts;

import dev.patika.VeterinaryManagementSystem.core.result.ResultData;
import dev.patika.VeterinaryManagementSystem.dto.response.CursorResponse;
import dev.patika.VeterinaryManagementSystem.dto.response.doctor.DoctorResponse;
import dev.patika.VeterinaryManagementSystem.entities.Doctor;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IDoctorService{
    Doctor save(Doctor doctor);

    //Doctor get(long id);
    DoctorResponse get(long id);


    //Page<DoctorResponse> cursor(int page, int pageSize);
    List<DoctorResponse> getAll();
    Doctor update(Doctor doctor);

    boolean delete(long id);

}
