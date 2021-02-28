package com.training.universitydb.service.mapper;

import com.training.universitydb.model.FacultyEntity;
import com.training.universitydb.model.dto.FacultyDTORequest;
import com.training.universitydb.model.dto.FacultyDTOResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE, collectionMappingStrategy = CollectionMappingStrategy.SETTER_PREFERRED)
public interface FacultyDTOMapper {
    @Mappings({
            @Mapping(source = "facultyEntity.id", target = "id"),
            @Mapping(source = "facultyEntity.title" , target = "title"),
            @Mapping(source = "facultyEntity.headProfessor.name" , target = "headProfessor")
    })
    FacultyDTOResponse entityToDto(FacultyEntity facultyEntity);

    @Mappings({
            @Mapping(source = "facultyDTORequest.title" , target = "title"),
            @Mapping(source = "facultyDTORequest.headProfessor" , target = "headProfessor.id")
    })
    FacultyEntity dtoToEntity(FacultyDTORequest facultyDTORequest);
}
