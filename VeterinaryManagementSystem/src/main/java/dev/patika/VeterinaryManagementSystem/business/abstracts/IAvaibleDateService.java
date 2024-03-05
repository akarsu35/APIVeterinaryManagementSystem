package dev.patika.VeterinaryManagementSystem.business.abstracts;

import dev.patika.VeterinaryManagementSystem.entities.AvaibleDate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public interface IAvaibleDateService {
    AvaibleDate save(AvaibleDate date);
    AvaibleDate get(long id);
    Page<AvaibleDate> cursor(int page, int pageSize);
    AvaibleDate update(AvaibleDate date);
    boolean delete(long id);

}
