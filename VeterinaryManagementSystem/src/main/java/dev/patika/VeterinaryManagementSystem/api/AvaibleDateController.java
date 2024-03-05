package dev.patika.VeterinaryManagementSystem.api;

import dev.patika.VeterinaryManagementSystem.business.abstracts.IAvaibleDateService;
import dev.patika.VeterinaryManagementSystem.business.abstracts.IDoctorService;
import dev.patika.VeterinaryManagementSystem.core.config.ModelMapper.IModelMapperService;
import dev.patika.VeterinaryManagementSystem.core.result.Result;
import dev.patika.VeterinaryManagementSystem.core.result.ResultData;
import dev.patika.VeterinaryManagementSystem.core.utilities.ResultHelper;
import dev.patika.VeterinaryManagementSystem.dto.request.animal.AnimalSaveRequest;
import dev.patika.VeterinaryManagementSystem.dto.request.avaibledate.AvaibleDateSaveRequest;
import dev.patika.VeterinaryManagementSystem.dto.request.avaibledate.AvaibleDateUpdateRequest;
import dev.patika.VeterinaryManagementSystem.dto.response.CursorResponse;
import dev.patika.VeterinaryManagementSystem.dto.response.animal.AnimalResponse;
import dev.patika.VeterinaryManagementSystem.dto.response.avaibledate.AvaibleDateResponse;
import dev.patika.VeterinaryManagementSystem.dto.response.customer.CustomerResponse;
import dev.patika.VeterinaryManagementSystem.entities.Animal;
import dev.patika.VeterinaryManagementSystem.entities.AvaibleDate;
import dev.patika.VeterinaryManagementSystem.entities.Customer;
import dev.patika.VeterinaryManagementSystem.entities.Doctor;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/v1/avaibledates")
public class AvaibleDateController {
    private final IAvaibleDateService avaibleDateService;
    private final IModelMapperService modelMapper;
    private final IDoctorService doctorService;

    public AvaibleDateController(IAvaibleDateService avaibleDateService,
                                 IModelMapperService modelMapper,
                                 IDoctorService doctorService) {
        this.avaibleDateService = avaibleDateService;
        this.modelMapper = modelMapper;
        this.doctorService = doctorService;
    }


    //Değerlendrime formu 13
    @PostMapping()//doktor müsait günü kaydetme
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<LocalDate> save(@Valid @RequestBody AvaibleDateSaveRequest avaibleDateSaveRequest) {
        AvaibleDate saveAvaibleDate = this.modelMapper.forRequest().map(avaibleDateSaveRequest, AvaibleDate.class);

        Doctor doctor = this.doctorService.get(avaibleDateSaveRequest.getDoctor().getId());
        saveAvaibleDate.setDoctor(doctor);

        this.avaibleDateService.save(saveAvaibleDate);

        return ResultHelper.created(this.modelMapper.forResponse().map(saveAvaibleDate, AvaibleDateResponse.class).getAvailableDate());
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<LocalDate> get(@PathVariable("id") long id) {
        AvaibleDate avaibleDate = this.avaibleDateService.get(id);
        AvaibleDateResponse avaibleDateResponse = this.modelMapper.forResponse().map(avaibleDate, AvaibleDateResponse.class);
        return ResultHelper.success(avaibleDateResponse.getAvailableDate());
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<LocalDate>> cursor(
            @RequestParam(value = "page", required = false,defaultValue = "0") int page,
            @RequestParam(value = "pageSize", required = false,defaultValue = "10") int pageSize
    ){
        Page<AvaibleDate> avaibledatePage = this.avaibleDateService.cursor(page, pageSize);
        Page<LocalDate> avaibledateResponsePage = avaibledatePage
                .map(avaibleDate -> this.modelMapper.forResponse().map(avaibleDate, AvaibleDateResponse.class).getAvailableDate());

        return ResultHelper.cursor(avaibledateResponsePage);
    }


    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AvaibleDateResponse> update(@Valid @RequestBody AvaibleDateUpdateRequest avaibledateUpdateRequest) {

        AvaibleDate updateAvaibleDate = this.modelMapper.forRequest().map(avaibledateUpdateRequest, AvaibleDate.class);
        this.avaibleDateService.update(updateAvaibleDate);

        return ResultHelper.success(this.modelMapper.forResponse().map(updateAvaibleDate, AvaibleDateResponse.class));
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") long id) {

        this.avaibleDateService.delete(id);
        return ResultHelper.ok();

    }

}
