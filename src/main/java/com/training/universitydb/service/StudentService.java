package com.training.universitydb.service;

import com.training.universitydb.model.FacultyEntity;
import com.training.universitydb.model.StudentEntity;
import com.training.universitydb.model.TakingCourseEntity;
import com.training.universitydb.model.dto.StudentDTORequest;
import com.training.universitydb.model.dto.StudentDTOResponse;
import com.training.universitydb.model.dto.TeachingCourseDTOResponse;
import com.training.universitydb.repository.FacultyRepository;
import com.training.universitydb.repository.StudentRepository;
import com.training.universitydb.repository.TakingCourseRepository;
import com.training.universitydb.controller.exception.DataNotFoundException;
import com.training.universitydb.service.mapper.StudentDTOMapper;
import com.training.universitydb.service.mapper.TeachingCourseDTOMapper;
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
public class StudentService {
    StudentRepository studentRepository;
    TakingCourseRepository takingCourseRepository;
    FacultyRepository facultyRepository;

    StudentDTOMapper studentDTOMapper;
    TeachingCourseDTOMapper teachingCourseDTOMapper;

    public List<StudentDTOResponse> getAll(){
        return studentRepository.findAll().stream().map(studentDTOMapper::entityToDto).collect(Collectors.toList());
    }

    public StudentDTOResponse getById(Long id){
        return studentDTOMapper.entityToDto(studentRepository.findById(id).orElseThrow(DataNotFoundException::new));
    }

    @Transactional
    public Void removeById(Long id){
        StudentEntity studentEntity = studentRepository.findById(id).orElseThrow(DataNotFoundException::new);
        studentRepository.delete(studentEntity);
        return null;
    }

    public Void create(StudentDTORequest studentDTORequest){
        StudentEntity studentEntity = studentDTOMapper.dtoToEntity(studentDTORequest);
        studentEntity.setFaculty(facultyRepository.findById(studentDTORequest.getFaculty()).orElseThrow(DataNotFoundException::new));
        studentRepository.save(studentEntity);
        return null;
    }

    public Void update(StudentDTORequest studentDTORequest, Long id){
        FacultyEntity faculty = facultyRepository.findById(studentDTORequest.getFaculty()).orElseThrow(DataNotFoundException::new);

        StudentEntity studentEntity = studentRepository.findById(id).orElseThrow(DataNotFoundException::new);
        studentEntity.setName(studentDTORequest.getName());
        studentEntity.setBirthDate(studentDTORequest.getBirthDate());
        studentEntity.setYear(studentDTORequest.getYear());
        studentEntity.setFaculty(faculty);
        studentRepository.save(studentEntity);
        return null;
    }

    public Set<TeachingCourseDTOResponse> getAllTakenCoursesByStudent(Long studentId){
        StudentEntity studentEntity = studentRepository.findById(studentId).orElseThrow(DataNotFoundException::new);
        return studentEntity.getCourses().stream().map(TakingCourseEntity::getTeachingCourseEntity).map(teachingCourseDTOMapper::entityToDto).collect(Collectors.toSet());
    }

    public Void addCourse(Long id, Long takingCourseId){
        TakingCourseEntity takingCourseEntity = takingCourseRepository.findById(takingCourseId).orElseThrow(DataNotFoundException::new);
        StudentEntity studentEntity = studentRepository.findById(id).orElseThrow(DataNotFoundException::new);
        studentEntity.getCourses().add(takingCourseEntity);
        takingCourseEntity.getStudents().add(studentEntity);
        return null;
    }
}
