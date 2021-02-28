package com.training.universitydb.service.mapper;

import com.training.universitydb.model.TeachingCourseEntity;
import com.training.universitydb.model.dto.TeachingCourseDTORequest;
import com.training.universitydb.model.dto.TeachingCourseDTOResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE, collectionMappingStrategy = CollectionMappingStrategy.SETTER_PREFERRED)
public interface TeachingCourseDTOMapper {
    @Mappings({
            @Mapping(source = "teachingCourseEntity.id" , target = "id"),
            @Mapping(source = "teachingCourseEntity.course.title" , target = "course"),
            @Mapping(source = "teachingCourseEntity.professor.name" , target = "professor"),
            @Mapping(source = "teachingCourseEntity.semester.startDate" , target = "semesterStartDate"),
            @Mapping(source = "teachingCourseEntity.semester.endDate" , target = "semesterEndDate"),
            @Mapping(source = "teachingCourseEntity.takingCourseEntity" , target = "takingCourseEntity"),
    })
    TeachingCourseDTOResponse entityToDto(TeachingCourseEntity teachingCourseEntity);
}
