package dev.patika.VeterinaryManagementSystem.business.concretes;

import dev.patika.VeterinaryManagementSystem.business.abstracts.IAvaibleDateService;
import dev.patika.VeterinaryManagementSystem.core.config.ModelMapper.IModelMapperService;
import dev.patika.VeterinaryManagementSystem.core.exception.AlreadyExistsAvaibleDateException;
import dev.patika.VeterinaryManagementSystem.core.exception.NotFoundException;
import dev.patika.VeterinaryManagementSystem.core.result.ResultData;
import dev.patika.VeterinaryManagementSystem.core.utilities.Msg;
import dev.patika.VeterinaryManagementSystem.core.utilities.ResultHelper;
import dev.patika.VeterinaryManagementSystem.dao.AvaibleDateRepo;
import dev.patika.VeterinaryManagementSystem.dao.DoctorRepo;
import dev.patika.VeterinaryManagementSystem.dto.request.avaibledate.AvaibleDateSaveRequest;
import dev.patika.VeterinaryManagementSystem.dto.request.avaibledate.AvaibleDateUpdateRequest;
import dev.patika.VeterinaryManagementSystem.dto.response.CursorResponse;
import dev.patika.VeterinaryManagementSystem.dto.response.avaibledate.AvaibleDateResponse;
import dev.patika.VeterinaryManagementSystem.entities.AvaibleDate;
import dev.patika.VeterinaryManagementSystem.entities.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AvaibleDateManager implements IAvaibleDateService {
    private final AvaibleDateRepo avaibleDateRepo;

    private final IModelMapperService modelMapper;
    private final DoctorRepo doctorRepo;


    public AvaibleDateManager(AvaibleDateRepo avaibleDateRepo,IModelMapperService modelMapper, DoctorRepo doctorRepo) {//DI
        this.avaibleDateRepo = avaibleDateRepo;

        this.modelMapper = modelMapper;
        this.doctorRepo = doctorRepo;
    }


    @Override
    public AvaibleDateResponse save(AvaibleDateSaveRequest avaibleDateSaveRequest) {
        Optional<AvaibleDate> avaibleDate = this.avaibleDateRepo.findByAvailableDateAndDoctorId(avaibleDateSaveRequest.getAvailableDate(), avaibleDateSaveRequest.getDoctor().getId());
        Doctor doctor = avaibleDateSaveRequest.getDoctor();
        if (doctor == null || doctor.getId() == null || !doctorRepo.existsById(doctor.getId())) {
            throw new NotFoundException("Doktor bulunamadı");
        }
        if (avaibleDate.isPresent()) {
            throw new AlreadyExistsAvaibleDateException(Msg.DATE_ALREADY_EXIST);
        }


        return this.modelMapper.forResponse().map(this.avaibleDateRepo.save(modelMapper.forRequest().map(avaibleDateSaveRequest, AvaibleDate.class)),AvaibleDateResponse.class);
    }


    @Override
    public AvaibleDate get(long id) {
        return  this.avaibleDateRepo.findById(id).orElseThrow(()->new NotFoundException(Msg.NOT_FOUND));
    }


    @Override
    public List<AvaibleDateResponse> getAll() {
        return this.avaibleDateRepo.findAll().stream()
                .map(avaibleDate -> modelMapper.forResponse()
                        .map(avaibleDate, AvaibleDateResponse.class))
                .collect(Collectors.toList());
    }


    @Override
    public AvaibleDateResponse update(AvaibleDateUpdateRequest date) {
        AvaibleDate avaibleDate = modelMapper.forRequest().map(date, AvaibleDate.class);
        this.get(date.getId());
        Doctor doctor = date.getDoctor();
        if (doctor == null || doctor.getId() == null || !doctorRepo.existsById(doctor.getId())) {
            throw new NotFoundException("Doktor bulunamadı");
        }
        if(this.get(date.getId())==null){
            throw new NotFoundException(Msg.NOT_FOUND);
        }
        this.avaibleDateRepo.save(avaibleDate);
        return this.modelMapper.forResponse().map(avaibleDate,AvaibleDateResponse.class);

    }


    @Override
    public boolean delete(long id) {
        this.get(id);
        this.avaibleDateRepo.deleteById(id);
        return true;
    }
}
