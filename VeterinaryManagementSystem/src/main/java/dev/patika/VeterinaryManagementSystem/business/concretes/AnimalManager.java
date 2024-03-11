package dev.patika.VeterinaryManagementSystem.business.concretes;

import dev.patika.VeterinaryManagementSystem.business.abstracts.IAnimalService;
import dev.patika.VeterinaryManagementSystem.core.config.ModelMapper.IModelMapperService;
import dev.patika.VeterinaryManagementSystem.core.exception.AlreadyExistsAnimalException;
import dev.patika.VeterinaryManagementSystem.core.exception.AlreadyExistsException;
import dev.patika.VeterinaryManagementSystem.core.exception.NotFoundException;
import dev.patika.VeterinaryManagementSystem.core.utilities.Msg;
import dev.patika.VeterinaryManagementSystem.dao.AnimalRepo;
import dev.patika.VeterinaryManagementSystem.dao.CustomerRepo;
import dev.patika.VeterinaryManagementSystem.dto.request.animal.AnimalSaveRequest;
import dev.patika.VeterinaryManagementSystem.dto.response.animal.AnimalResponse;
import dev.patika.VeterinaryManagementSystem.dto.response.vaccine.VaccineResponse;
import dev.patika.VeterinaryManagementSystem.entities.Animal;
import dev.patika.VeterinaryManagementSystem.entities.Customer;
import dev.patika.VeterinaryManagementSystem.entities.Vaccine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AnimalManager implements IAnimalService {
    private final AnimalRepo animalRepo;
    private final CustomerRepo customerRepo;
    private final IModelMapperService modelMapper;

    public AnimalManager(AnimalRepo animalRepo, CustomerRepo customerRepo, IModelMapperService modelMapper) {
        this.animalRepo = animalRepo;
        this.customerRepo = customerRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public Animal save(Animal animal) {
        if (animal.getName()!=null&&this.animalRepo.existsByCustomerAndName(animal.getCustomer(), animal.getName())) {
            throw new AlreadyExistsAnimalException(Msg.NAME_ALREADY_EXIST);
        }
        return this.animalRepo.save(animal);
    }

    @Override
    public Animal get(Long id) {
        Animal animal = this.animalRepo.findById(id).orElseThrow(()->new NotFoundException(Msg.NOT_FOUND));
        this.modelMapper.forResponse().map(animal, AnimalResponse.class);
        if(!this.animalRepo.existsById(id)){
            throw new NotFoundException(Msg.NOT_FOUND);
        }
        return animal;

    }

    @Override
    public List<Animal> getAll() {
        return this.animalRepo.findAll();

    }

    @Override
    public List<Animal> getAnimalByCustomerName(String name) {
        List<Animal> filteredAnimals = this.animalRepo.findByCustomerName(name);
        if (filteredAnimals.isEmpty()) {
            throw new NotFoundException(Msg.NOT_FOUND);
        }
        return this.animalRepo.findByCustomerName(name);
    }


    @Override
    public Animal update(Animal animal) {
        this.get(animal.getId());
        if(this.get(animal.getId())==null){
            throw new NotFoundException(Msg.NOT_FOUND);
        }
        return this.animalRepo.save(animal);
    }

    @Override
    public boolean delete(long id) {
        Animal animal = this.get(id);
        this.animalRepo.delete(animal);
        return true;
    }

    @Override
    public List<Animal> filterByName(String name) {
        return animalRepo.findByNameContaining(name);
    }



    @Override
    public List<Animal> filterByAnimalName(String animalName) {
        List<Animal> filteredAnimals = this.animalRepo.findByNameContainingIgnoreCase(animalName);
        if (filteredAnimals.isEmpty()) {
            throw new NotFoundException(Msg.NOT_FOUND);
        }

        return filteredAnimals;

    }

}
