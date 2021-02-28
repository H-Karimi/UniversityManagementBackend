package com.training.universitydb.service;

import com.training.universitydb.model.StudentEntity;
import com.training.universitydb.model.TakingCourseEntity;
import com.training.universitydb.model.TeachingCourseEntity;
import com.training.universitydb.model.dto.StudentDTOResponse;
import com.training.universitydb.model.dto.TakingCourseDTORequest;
import com.training.universitydb.model.dto.TakingCourseDTOResponse;
import com.training.universitydb.repository.StudentRepository;
import com.training.universitydb.repository.TakingCourseRepository;
import com.training.universitydb.controller.exception.DataNotFoundException;
import com.training.universitydb.repository.TeachingCourseRepository;
import com.training.universitydb.service.mapper.StudentDTOMapper;
import com.training.universitydb.service.mapper.TakingCourseDTOMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
@RequiredArgsConstructor
public class TakingCourseService {
    TakingCourseRepository takingCourseRepository;
    TeachingCourseRepository teachingCourseRepository;
    StudentRepository studentRepository;

    TakingCourseDTOMapper takingCourseDTOMapper;
    StudentDTOMapper studentDTOMapper;

    public List<TakingCourseDTOResponse> getAll(){
        return takingCourseRepository.findAll().stream().map(takingCourseDTOMapper::entityToDto).collect(Collectors.toList());
    }

    public TakingCourseDTOResponse getById(Long id){
        return takingCourseDTOMapper.entityToDto(takingCourseRepository.findById(id).orElseThrow(DataNotFoundException::new));
    }

    @Transactional
    public Void removeById(Long id){
        TakingCourseEntity takingCourseEntity = takingCourseRepository.findById(id).orElseThrow(DataNotFoundException::new);
        Set<StudentEntity> students = takingCourseEntity.getStudents();
        students.stream().forEach(studentEntity -> {
            studentEntity.getCourses().remove(takingCourseEntity);
        });
        takingCourseRepository.delete(takingCourseEntity);
        return null;
    }

    public Void create(TakingCourseDTORequest takingCourseDTORequest){
        TakingCourseEntity takingCourseEntity = new TakingCourseEntity();
        takingCourseEntity.setTeachingCourseEntity(teachingCourseRepository.findById(takingCourseDTORequest.getTeachingCourseEntity()).orElseThrow(DataNotFoundException::new));
        takingCourseRepository.save(takingCourseEntity);
        return null;
    }

    public Void update(TakingCourseDTORequest takingCourseDTORequest, Long id){
        TeachingCourseEntity teachingCourseEntity = teachingCourseRepository.findById(takingCourseDTORequest.getTeachingCourseEntity()).orElseThrow(DataNotFoundException::new);

        TakingCourseEntity takingCourseEntity = takingCourseRepository.findById(id).orElseThrow(DataNotFoundException::new);
        takingCourseEntity.setTeachingCourseEntity(teachingCourseEntity);
        takingCourseRepository.save(takingCourseEntity);
        return null;
    }

    public List<StudentDTOResponse> getAllStudents(Long id){
        TakingCourseEntity takingCourseEntity = takingCourseRepository.findById(id).orElseThrow(DataNotFoundException::new);
        return takingCourseEntity.getStudents().stream().map(studentDTOMapper::entityToDto).collect(Collectors.toList());
    }
}
