package dev.patika.VeterinaryManagementSystem.core.config.ModelMapper;

import org.modelmapper.ModelMapper;

public interface IModelMapperService {
    ModelMapper forResponse();
    ModelMapper forRequest();
}
