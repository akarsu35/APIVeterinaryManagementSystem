package dev.patika.VeterinaryManagementSystem.core.exception;

public class TimeException extends RuntimeException {//randevuların tam saatlerde alıncağı
    public TimeException(String message) {
        super(message);
    }
}
