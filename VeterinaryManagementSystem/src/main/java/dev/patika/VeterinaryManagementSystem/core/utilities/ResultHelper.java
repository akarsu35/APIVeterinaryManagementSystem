package dev.patika.VeterinaryManagementSystem.core.utilities;

import dev.patika.VeterinaryManagementSystem.core.result.Result;
import dev.patika.VeterinaryManagementSystem.core.result.ResultData;
import dev.patika.VeterinaryManagementSystem.dto.response.CursorResponse;
import org.springframework.data.domain.Page;


public class ResultHelper {
    public static <T> ResultData<T> created(T data) {

        return new ResultData<>(true, Msg.CREATED, "201", data);
    }
    public static <T> ResultData<T> validateError(T data) {
        return new ResultData<>(false, Msg.VALIDATE_ERROR, "400", data);
    }
    public static <T> ResultData<T> notFoundError(T data) {
        return new ResultData<>(false, Msg.NOT_FOUND, "404", data);
    }
    public static <T> ResultData<T> success(T data) {
        return new ResultData<>(true, Msg.OK, "200", data);
    }
    public static Result ok(){

        return new Result(true, Msg.OK, "200");
    }
    public static Result beforeSave(String msg) {
        return new Result( false,Msg.MAIL_ALREADY_EXIST,  "400");
    }
    public static Result beforeSaved(String msg) {
        return new Result( false,msg,  "400");
    }

    public static Result NameExist(String msg) {//isim daha önce kayıtlı
        return new Result( false,msg,  "400");
    }
    public static Result avaibleDateExist(String msg) {//isim daha önce kayıtlı
        return new Result( false,msg,  "400");
    }
    public static Result notFoundError(String msg) {
        return new Result(false, msg, "404");
    }
    public static Result timeError(String msg) {
        return new Result(false, msg, "400");
    }//randevuların tam saatlerde alıncağı

    public static <T> ResultData<CursorResponse<T>> cursor (Page<T> pageData){
        CursorResponse<T> cursor = new CursorResponse<>();
        cursor.setItems(pageData.getContent());
        cursor.setTotalElements(pageData.getTotalElements());
        cursor.setPageSize(pageData.getSize());
        cursor.setPageNumber(pageData.getNumber());
        return ResultHelper.success(cursor);

    }


}
