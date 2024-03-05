package dev.patika.VeterinaryManagementSystem.core.config;

import dev.patika.VeterinaryManagementSystem.core.exception.*;
import dev.patika.VeterinaryManagementSystem.core.result.Result;
import dev.patika.VeterinaryManagementSystem.core.result.ResultData;
import dev.patika.VeterinaryManagementSystem.core.utilities.Msg;
import dev.patika.VeterinaryManagementSystem.core.utilities.ResultHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Result> handleNotFoundException(NotFoundException e) {

        return new ResponseEntity<>(ResultHelper.notFoundError(e.getMessage()), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResultData<List<String>>> handleValidationException(MethodArgumentNotValidException e) {
        List<String> validationErrorList = e.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());
        return new ResponseEntity<>(ResultHelper.validateError(validationErrorList), HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<Result> handleAlreadyExistsException(AlreadyExistsException e) {

        return new ResponseEntity<>(ResultHelper.beforeSaved(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(AlreadyExistsAnimalException.class)
    public ResponseEntity<Result> handleAlreadyExistsAnimalException(AlreadyExistsAnimalException e) {

        return new ResponseEntity<>(ResultHelper.NameExist(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(AlreadyExistsAvaibleDateException.class)
    public ResponseEntity<Result> handleAlreadyExistsAvaibleDateException(AlreadyExistsAvaibleDateException e) {

        return new ResponseEntity<>(ResultHelper.avaibleDateExist(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(TimeException.class)
    public ResponseEntity<Result> handleTimeException(TimeException e) {

        return new ResponseEntity<>(ResultHelper.timeError(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

}
