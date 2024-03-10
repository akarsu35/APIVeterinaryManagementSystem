package dev.patika.VeterinaryManagementSystem.api;

import dev.patika.VeterinaryManagementSystem.business.abstracts.IAvaibleDateService;
import dev.patika.VeterinaryManagementSystem.core.result.Result;
import dev.patika.VeterinaryManagementSystem.core.result.ResultData;
import dev.patika.VeterinaryManagementSystem.core.utilities.ResultHelper;
import dev.patika.VeterinaryManagementSystem.dto.request.avaibledate.AvaibleDateSaveRequest;
import dev.patika.VeterinaryManagementSystem.dto.request.avaibledate.AvaibleDateUpdateRequest;
import dev.patika.VeterinaryManagementSystem.dto.response.avaibledate.AvaibleDateResponse;
import dev.patika.VeterinaryManagementSystem.entities.AvaibleDate;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/avaibledates")
public class AvaibleDateController {
    private final IAvaibleDateService avaibleDateService;

    public AvaibleDateController(IAvaibleDateService avaibleDateService) {
        this.avaibleDateService = avaibleDateService;


    }


    //Değerlendrime formu 13
    @PostMapping()//doktor müsait günü kaydetme
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AvaibleDateResponse> save(@Valid @RequestBody AvaibleDateSaveRequest avaibleDateSaveRequest) {

        return ResultHelper.created(this.avaibleDateService.save(avaibleDateSaveRequest));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result get(@PathVariable("id") long id){
        return ResultHelper.success(this.avaibleDateService.get(id).getAvailableDate());
    }


    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<AvaibleDateResponse> getAll(){

        return this.avaibleDateService.getAll();
    }



    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AvaibleDateResponse> update(@Valid @RequestBody AvaibleDateUpdateRequest avaibledateUpdateRequest) {

        return ResultHelper.success(this.avaibleDateService.update(avaibledateUpdateRequest));
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") long id) {

        this.avaibleDateService.delete(id);
        return ResultHelper.ok();

    }

}
