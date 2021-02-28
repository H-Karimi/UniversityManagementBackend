package com.training.universitydb.service.mapper;

import com.training.universitydb.model.CourseEntity;
import com.training.universitydb.model.dto.CourseDTORequest;
import com.training.universitydb.model.dto.CourseDTOResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE, collectionMappingStrategy = CollectionMappingStrategy.SETTER_PREFERRED )
public interface CourseDTOMapper {
    @Mappings({
            @Mapping(source = "courseEntity.id" , target = "id"),
            @Mapping(source = "courseEntity.credit" , target = "credit"),
            @Mapping(source = "courseEntity.title" , target = "title")
    })
    CourseDTOResponse entityToDto(CourseEntity courseEntity);

    @Mappings({
            @Mapping(source = "courseDTORequest.credit" , target = "credit"),
            @Mapping(source = "courseDTORequest.title" , target = "title")
    })
    CourseEntity dtoToEntity(CourseDTORequest courseDTORequest);
}
