package dev.patika.VeterinaryManagementSystem.business.abstracts;

import dev.patika.VeterinaryManagementSystem.core.result.ResultData;
import dev.patika.VeterinaryManagementSystem.dto.request.vaccine.VaccineSaveRequest;
import dev.patika.VeterinaryManagementSystem.dto.request.vaccine.VaccineUpdateRequest;
import dev.patika.VeterinaryManagementSystem.dto.response.vaccine.VaccineResponse;
import dev.patika.VeterinaryManagementSystem.entities.Animal;
import dev.patika.VeterinaryManagementSystem.entities.Customer;
import dev.patika.VeterinaryManagementSystem.entities.Vaccine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface IVaccineService {
    //Vaccine save(Vaccine vaccine);
    VaccineResponse save(VaccineSaveRequest vaccineSaveRequest);

    //Vaccine get(long id);
    VaccineResponse get(long id);
    //Page<Vaccine> cursor(int page, int pageSize);
    List<VaccineResponse> getAll();
    //Vaccine update(Vaccine vaccine);
    VaccineResponse update(VaccineUpdateRequest vaccineUpdateRequest);

    boolean delete(long id);


    List<Vaccine> getVaccinesByDate(LocalDate startDate, LocalDate endDate);

    List<Vaccine> getVaccinesByAnimalName(String name);
}
