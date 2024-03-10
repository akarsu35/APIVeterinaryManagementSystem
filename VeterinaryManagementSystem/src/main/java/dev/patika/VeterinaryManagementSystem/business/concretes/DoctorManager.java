package dev.patika.VeterinaryManagementSystem.business.concretes;

import dev.patika.VeterinaryManagementSystem.business.abstracts.IDoctorService;
import dev.patika.VeterinaryManagementSystem.core.config.ModelMapper.IModelMapperService;
import dev.patika.VeterinaryManagementSystem.core.exception.AlreadyExistsException;
import dev.patika.VeterinaryManagementSystem.core.exception.NotFoundException;
import dev.patika.VeterinaryManagementSystem.core.result.ResultData;
import dev.patika.VeterinaryManagementSystem.core.utilities.Msg;
import dev.patika.VeterinaryManagementSystem.dao.DoctorRepo;
import dev.patika.VeterinaryManagementSystem.dto.response.CursorResponse;
import dev.patika.VeterinaryManagementSystem.dto.response.doctor.DoctorResponse;
import dev.patika.VeterinaryManagementSystem.entities.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorManager implements IDoctorService {
    private final DoctorRepo doctorRepo;
    private final IModelMapperService modelMapper;

    public DoctorManager(DoctorRepo doctorRepo, IModelMapperService modelMapper) {
        this.doctorRepo = doctorRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public Doctor save(Doctor doctor) {
        if (this.doctorRepo.existsByMail(doctor.getMail())) {
            throw new AlreadyExistsException(Msg.MAIL_ALREADY_EXIST);
        }
        return this.doctorRepo.save(doctor);
    }


    @Override
    public DoctorResponse get(long id) {
        Doctor doctor = this.doctorRepo.findById(id).orElseThrow(()->new NotFoundException(Msg.NOT_FOUND));
        DoctorResponse doctorResponse = this.modelMapper.forResponse().map(doctor, DoctorResponse.class);
        return doctorResponse;

    }


    @Override
    public List<DoctorResponse> getAll() {
        List<DoctorResponse> doctorResponses = this.doctorRepo
                .findAll()
                .stream()
                .map(doctor -> this.modelMapper.forResponse().map(doctor, DoctorResponse.class))
                .collect(Collectors.toList());
        return doctorResponses;
    }


    @Override
    public Doctor update(Doctor doctor) {
        this.get(doctor.getId());
        if (this.doctorRepo.existsByMail(doctor.getMail())) {
            throw new AlreadyExistsException(Msg.MAIL_ALREADY_EXIST);
        }
        return this.doctorRepo.save(doctor);
    }

    @Override
    public boolean delete(long id) {
        DoctorResponse doctor = this.get(id);
        if(doctor == null){
            throw new NotFoundException(Msg.NOT_FOUND);
        }

        this.doctorRepo.delete(this.doctorRepo.getOne(id));
        return true;
    }



}
