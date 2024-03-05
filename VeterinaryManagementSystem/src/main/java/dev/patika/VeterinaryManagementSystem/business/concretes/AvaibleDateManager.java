package dev.patika.VeterinaryManagementSystem.business.concretes;

import dev.patika.VeterinaryManagementSystem.business.abstracts.IAvaibleDateService;
import dev.patika.VeterinaryManagementSystem.core.exception.AlreadyExistsAvaibleDateException;
import dev.patika.VeterinaryManagementSystem.core.exception.AlreadyExistsException;
import dev.patika.VeterinaryManagementSystem.core.exception.NotFoundException;
import dev.patika.VeterinaryManagementSystem.core.utilities.Msg;
import dev.patika.VeterinaryManagementSystem.dao.AvaibleDateRepo;
import dev.patika.VeterinaryManagementSystem.entities.AvaibleDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
@Service
public class AvaibleDateManager implements IAvaibleDateService {
    private final AvaibleDateRepo avaibleDateRepo;

    public AvaibleDateManager(AvaibleDateRepo avaibleDateRepo) {//DI
        this.avaibleDateRepo = avaibleDateRepo;
    }


    @Override
    public AvaibleDate save(AvaibleDate date) {
        if(date.getAvailableDate()!=null&&this.avaibleDateRepo.existsByAvailableDateAndDoctorId(date.getAvailableDate(), date.getDoctor().getId())){
            throw new AlreadyExistsAvaibleDateException(Msg.DATE_ALREADY_EXIST);

        }
        return this.avaibleDateRepo.save(date);
    }

    @Override
    public AvaibleDate get(long id) {
        return this.avaibleDateRepo.findById(id).orElseThrow(()->new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Page<AvaibleDate> cursor(int page, int pageSize) {

        Pageable pageable = PageRequest.of(page, pageSize);
        return this.avaibleDateRepo.findAll(pageable);
    }

    @Override
    public AvaibleDate update(AvaibleDate date) {
        this.get(date.getId());
        return this.avaibleDateRepo.save(date);

    }

    @Override
    public boolean delete(long id) {
        this.get(id);
        this.avaibleDateRepo.deleteById(id);
        return true;
    }
}
