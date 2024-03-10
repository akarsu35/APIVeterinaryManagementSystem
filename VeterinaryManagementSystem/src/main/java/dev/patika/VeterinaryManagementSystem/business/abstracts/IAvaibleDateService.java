package dev.patika.VeterinaryManagementSystem.business.abstracts;

import dev.patika.VeterinaryManagementSystem.core.result.ResultData;
import dev.patika.VeterinaryManagementSystem.dao.AvaibleDateRepo;
import dev.patika.VeterinaryManagementSystem.dto.request.avaibledate.AvaibleDateSaveRequest;
import dev.patika.VeterinaryManagementSystem.dto.request.avaibledate.AvaibleDateUpdateRequest;
import dev.patika.VeterinaryManagementSystem.dto.response.CursorResponse;
import dev.patika.VeterinaryManagementSystem.dto.response.avaibledate.AvaibleDateResponse;
import dev.patika.VeterinaryManagementSystem.entities.AvaibleDate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface IAvaibleDateService {
    //AvaibleDate save(AvaibleDate date);
    //ResultData<LocalDate> save(AvaibleDateSaveRequest date);
    AvaibleDateResponse save(AvaibleDateSaveRequest date);
    AvaibleDateResponse get(long id);
    //ResultData<CursorResponse<LocalDate>> cursor(int page, int pageSize);
    List<AvaibleDateResponse> getAll();
    AvaibleDateResponse update(AvaibleDateUpdateRequest date);
    boolean delete(long id);

}
