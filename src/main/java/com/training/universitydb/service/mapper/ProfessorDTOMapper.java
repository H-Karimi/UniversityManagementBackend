package com.training.universitydb.service.mapper;

import com.training.universitydb.model.ProfessorEntity;
import com.training.universitydb.model.dto.ProfessorDTORequest;
import com.training.universitydb.model.dto.ProfessorDTOResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE, collectionMappingStrategy = CollectionMappingStrategy.SETTER_PREFERRED)
public interface ProfessorDTOMapper {
    @Mappings({
            @Mapping(source = "professorEntity.id" , target = "id"),
            @Mapping(source = "professorEntity.name" , target = "name"),
            @Mapping(source = "professorEntity.birthDate" , target = "birthDate"),
            @Mapping(source = "professorEntity.faculty.title" , target = "faculty")
    })
    ProfessorDTOResponse entityToDto(ProfessorEntity professorEntity);

    @Mappings({
            @Mapping(source = "professorDTORequest.name" , target = "name"),
            @Mapping(source = "professorDTORequest.birthDate" , target = "birthDate"),
            @Mapping(source = "professorDTORequest.faculty" , target = "faculty.title")
    })
    ProfessorEntity dtoToEntity(ProfessorDTORequest professorDTORequest);
}
