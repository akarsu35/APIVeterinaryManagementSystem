package dev.patika.VeterinaryManagementSystem.core.exception;

public class AlreadyExistsAnimalException extends RuntimeException {
    public AlreadyExistsAnimalException(String message) {
        super(message);
    }
}
