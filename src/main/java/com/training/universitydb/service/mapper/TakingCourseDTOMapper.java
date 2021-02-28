package com.training.universitydb.service.mapper;

import com.training.universitydb.model.TakingCourseEntity;
import com.training.universitydb.model.dto.TakingCourseDTORequest;
import com.training.universitydb.model.dto.TakingCourseDTOResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE, collectionMappingStrategy = CollectionMappingStrategy.SETTER_PREFERRED)
public interface TakingCourseDTOMapper {
    @Mappings({
            @Mapping(source = "takingCourseEntity.id" , target = "id"),
            @Mapping(source = "takingCourseEntity.teachingCourseEntity" , target = "teachingCourseEntity")
    })
    TakingCourseDTOResponse entityToDto(TakingCourseEntity takingCourseEntity);
}
