package com.training.universitydb.service.mapper;

import com.training.universitydb.model.SemesterEntity;
import com.training.universitydb.model.dto.SemesterDTORequest;
import com.training.universitydb.model.dto.SemesterDTOResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE, collectionMappingStrategy = CollectionMappingStrategy.SETTER_PREFERRED)
public interface SemesterDTOMapper {
    @Mappings({
            @Mapping(source = "semesterEntity.id" , target = "id"),
            @Mapping(source = "semesterEntity.startDate" , target = "startDate"),
            @Mapping(source = "semesterEntity.endDate" , target = "endDate")
    })
    SemesterDTOResponse entityToDto(SemesterEntity semesterEntity);

    @Mappings({
            @Mapping(source = "semesterDTORequest.startDate" , target = "startDate"),
            @Mapping(source = "semesterDTORequest.endDate" , target = "endDate")
    })
    SemesterEntity dtoToEntity(SemesterDTORequest semesterDTORequest);
}
