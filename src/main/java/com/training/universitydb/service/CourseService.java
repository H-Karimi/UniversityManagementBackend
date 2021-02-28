package com.training.universitydb.service;

import com.training.universitydb.model.CourseEntity;
import com.training.universitydb.model.dto.CourseDTORequest;
import com.training.universitydb.model.dto.CourseDTOResponse;
import com.training.universitydb.repository.CourseRepository;
import com.training.universitydb.controller.exception.DataNotFoundException;
import com.training.universitydb.service.mapper.CourseDTOMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
@RequiredArgsConstructor
public class CourseService {
    CourseRepository courseRepository;
    CourseDTOMapper courseDTOMapper;

    TeachingCourseService teachingCourseService;

    public List<CourseDTOResponse> getAll(){
        return courseRepository.findAll().stream().map(courseDTOMapper::entityToDto).collect(Collectors.toList());
    }

    public CourseDTOResponse getById(Long id){
        return courseDTOMapper.entityToDto(courseRepository.findById(id).orElseThrow(DataNotFoundException::new));
    }

    @Transactional
    public Void removeById(Long id){
        CourseEntity courseEntity = courseRepository.findById(id).orElseThrow(DataNotFoundException::new);
        courseEntity.getTeachingCourseEntitySet().stream().forEach(teachingCourseService::remove);
        courseRepository.delete(courseEntity);
        return null;
    }

    public Void create(CourseDTORequest courseDTORequest){
        CourseEntity courseEntity = courseDTOMapper.dtoToEntity(courseDTORequest);
        courseRepository.save(courseEntity);
        return null;
    }

    public Void update(CourseDTORequest courseDTORequest, Long id){
        CourseEntity courseEntity = courseRepository.findById(id).orElseThrow(DataNotFoundException::new);
        courseEntity.setTitle(courseDTORequest.getTitle());
        courseEntity.setCredit(courseDTORequest.getCredit());
        courseRepository.save(courseEntity);
        return null;
    }

}
