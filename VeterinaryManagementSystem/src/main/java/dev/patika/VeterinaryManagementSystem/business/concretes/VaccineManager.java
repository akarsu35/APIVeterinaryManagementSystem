package dev.patika.VeterinaryManagementSystem.business.concretes;

import dev.patika.VeterinaryManagementSystem.business.abstracts.IAnimalService;
import dev.patika.VeterinaryManagementSystem.business.abstracts.IVaccineService;
import dev.patika.VeterinaryManagementSystem.core.config.ModelMapper.IModelMapperService;
import dev.patika.VeterinaryManagementSystem.core.exception.AlreadyExistsException;
import dev.patika.VeterinaryManagementSystem.core.exception.NotFoundException;
import dev.patika.VeterinaryManagementSystem.core.exception.TimeException;
import dev.patika.VeterinaryManagementSystem.core.result.Result;
import dev.patika.VeterinaryManagementSystem.core.result.ResultData;
import dev.patika.VeterinaryManagementSystem.core.utilities.Msg;
import dev.patika.VeterinaryManagementSystem.core.utilities.ResultHelper;
import dev.patika.VeterinaryManagementSystem.dao.VaccineRepo;
import dev.patika.VeterinaryManagementSystem.dto.request.vaccine.VaccineSaveRequest;
import dev.patika.VeterinaryManagementSystem.dto.request.vaccine.VaccineUpdateRequest;
import dev.patika.VeterinaryManagementSystem.dto.response.vaccine.VaccineResponse;
import dev.patika.VeterinaryManagementSystem.entities.Animal;
import dev.patika.VeterinaryManagementSystem.entities.Customer;
import dev.patika.VeterinaryManagementSystem.entities.Vaccine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VaccineManager implements IVaccineService {
    private final VaccineRepo vaccineRepo;
    private final IModelMapperService modelMapper;
    private final IAnimalService animalManager;

    public VaccineManager(VaccineRepo vaccineRepo, IModelMapperService modelMapper, AnimalManager animalManager) {//DI
        this.vaccineRepo = vaccineRepo;
        this.modelMapper = modelMapper;
        this.animalManager = animalManager;
    }

    //Değerlendrime formu 19
    //Yeni aşı kaydetme işleminde koruyuculuk bitiş tarihi kontrolü


    @Override
    public VaccineResponse save(VaccineSaveRequest newVaccine) {
        // protectionStartDate'nin protectionFinishDate'den önce olup olmadığını kontrol et
        if (newVaccine.getProtectionStartDate().isAfter(newVaccine.getProtectionFinishDate())) {
            throw new TimeException("Koruyuculuk başlangıç tarihi, bitiş tarihinden sonra olamaz.");
        }

        List<Vaccine> existingVaccine = this.vaccineRepo.getVaccineByAnimalIdAndCodeAndDate(
                newVaccine.getAnimal().getId(),
                newVaccine.getName(),
                newVaccine.getCode(),
                newVaccine.getProtectionStartDate()
        );

        if (!existingVaccine.isEmpty()) {
            throw new AlreadyExistsException("Bu hayvanın bu aşı için aşı koruyuculuk tarihi geçmemiş.");
        }
        //newVaccine.setAnimal(this.modelMapper.forResponse().map(newVaccine.getAnimal(), Animal.class));
        Vaccine vaccine = this.modelMapper.forRequest().map(newVaccine, Vaccine.class);
        this.vaccineRepo.save(vaccine);
        return this.modelMapper.forResponse().map(vaccine, VaccineResponse.class);
    }
    @Override
    public VaccineResponse get(long id) {
        Vaccine vaccine = this.vaccineRepo.findById(id).orElseThrow(()->new NotFoundException(Msg.NOT_FOUND));
        return this.modelMapper.forResponse().map(vaccine, VaccineResponse.class);

    }


    @Override
    public List<VaccineResponse> getAll(){
        return this.vaccineRepo.findAll().stream()
                .map(vaccine -> this.modelMapper.forResponse()
                        .map(vaccine, VaccineResponse.class))
                .collect(Collectors.toList());
    }


    @Override
    public VaccineResponse update(VaccineUpdateRequest vaccine) {
        this.get(vaccine.getId());
        Animal existingAnimal = this.animalManager.get(vaccine.getAnimal().getId());

        if (existingAnimal == null) {
            // Hayvan bulunamadı, hata ver
            throw new NotFoundException("Hayvan bulunamadı.");
        }

        return this.modelMapper.forResponse().map(this.vaccineRepo.save(this.modelMapper.forRequest().map(vaccine, Vaccine.class)), VaccineResponse.class);
    }

    @Override
    public boolean delete(long id) {
        VaccineResponse vaccine = this.get(id);
        if (vaccine == null) {
            throw new NotFoundException("Aşı bulunamadı.");
        }
        this.vaccineRepo.delete(this.vaccineRepo.getOne(id));
        return true;
    }


    //Kullanıcı tarafından girilen tarih aralığına göre aşı koruyuculuk bitiş tarihi bu aralıkta olan hayvanları listeleme işlemi
    @Override
    public List<Vaccine> getVaccinesByDate(LocalDate startDate, LocalDate endDate) {
        if(startDate.isAfter(endDate)){
            throw new TimeException("başlangıç tarihi, bitiş tarihinden sonra olamaz !");
        }
        if (this.vaccineRepo.getVaccinesByDate(startDate,endDate).isEmpty()) {
            throw new NotFoundException("Bu tarihte aşı bulunamadı.");
        }
        return this.vaccineRepo.getVaccinesByDate(startDate,endDate);
    }

    @Override
    public List<Vaccine> getVaccinesByAnimalName(String name) {
        List<Vaccine> vaccines = this.vaccineRepo.getVaccinesByAnimalName(name);

        if (vaccines.isEmpty()) {
            throw new NotFoundException("Bu hayvanın aşı kaydı bulunmamaktadır.");
        }
        return vaccines;

    }


}
