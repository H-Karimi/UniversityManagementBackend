package com.training.universitydb.service.mapper;

import com.training.universitydb.model.StudentEntity;
import com.training.universitydb.model.dto.StudentDTORequest;
import com.training.universitydb.model.dto.StudentDTOResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE, collectionMappingStrategy = CollectionMappingStrategy.SETTER_PREFERRED)
public interface StudentDTOMapper {
    @Mappings({
            @Mapping(source = "studentEntity.id" , target = "id"),
            @Mapping(source = "studentEntity.name" , target = "name"),
            @Mapping(source = "studentEntity.birthDate" , target = "birthDate"),
            @Mapping(source = "studentEntity.year" , target = "year"),
            @Mapping(source = "studentEntity.faculty.title" , target = "faculty")
    })
    StudentDTOResponse entityToDto(StudentEntity studentEntity);

    @Mappings({
            @Mapping(source = "studentDTORequest.name" , target = "name"),
            @Mapping(source = "studentDTORequest.birthDate" , target = "birthDate"),
            @Mapping(source = "studentDTORequest.year" , target = "year"),
            @Mapping(source = "studentDTORequest.faculty" , target = "faculty.title")
    })
    StudentEntity dtoToEntity(StudentDTORequest studentDTORequest);
}
