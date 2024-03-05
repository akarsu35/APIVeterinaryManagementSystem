package dev.patika.VeterinaryManagementSystem.business.concretes;

import dev.patika.VeterinaryManagementSystem.business.abstracts.IVaccineService;
import dev.patika.VeterinaryManagementSystem.core.exception.AlreadyExistsException;
import dev.patika.VeterinaryManagementSystem.core.exception.NotFoundException;
import dev.patika.VeterinaryManagementSystem.core.utilities.Msg;
import dev.patika.VeterinaryManagementSystem.dao.VaccineRepo;
import dev.patika.VeterinaryManagementSystem.entities.Animal;
import dev.patika.VeterinaryManagementSystem.entities.Customer;
import dev.patika.VeterinaryManagementSystem.entities.Vaccine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VaccineManager implements IVaccineService {
    private final VaccineRepo vaccineRepo;

    public VaccineManager(VaccineRepo vaccineRepo) {//DI
        this.vaccineRepo = vaccineRepo;
    }

    //Değerlendrime formu 19
    //Yeni aşı kaydetme işleminde koruyuculuk bitiş tarihi kontrolü

    @Override
    public Vaccine save(Vaccine vaccine){
        List<Vaccine> vaccine1 = this.vaccineRepo.getVaccinesByAnimalIdAndCodeAndByDate(vaccine.getAnimal().getId()
        ,vaccine.getCode(),vaccine.getProtectionStartDate(),vaccine.getProtectionFinishDate());
        if(!vaccine1.isEmpty()){
            throw new AlreadyExistsException("Aşı koruyuculuk tarihi bitmemiştir.");
        }
        return this.vaccineRepo.save(vaccine);
    }

    @Override
    public Vaccine get(long id) {
        return this.vaccineRepo.findById(id).orElseThrow(()->new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Page<Vaccine> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.vaccineRepo.findAll(pageable);
    }

    @Override
    public Vaccine update(Vaccine vaccine) {
        this.get(vaccine.getId());
        return this.vaccineRepo.save(vaccine);
    }

    @Override
    public boolean delete(long id) {
        Vaccine vaccine = this.get(id);
        this.vaccineRepo.delete(vaccine);
        return true;
    }

    @Override
    public List<Vaccine> getVaccinesByAnimal(Animal animal) {
        return this.vaccineRepo.getVaccinesByAnimal(animal);
    }
    //Kullanıcı tarafından girilen tarih aralığına göre aşı koruyuculuk bitiş tarihi bu aralıkta olan hayvanları listeleme
    @Override
    public List<Vaccine> getVaccinesByDate(LocalDate startDate, LocalDate endDate) {
        if (this.vaccineRepo.getVaccinesByDate(startDate,endDate).isEmpty()) {
            throw new NotFoundException("Bu tarihte aşı bulunamadı");
        }
        return this.vaccineRepo.getVaccinesByDate(startDate,endDate);
    }





}
