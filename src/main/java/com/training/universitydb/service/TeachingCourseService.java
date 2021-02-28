package com.training.universitydb.service;

import com.training.universitydb.model.*;
import com.training.universitydb.model.dto.TeachingCourseDTORequest;
import com.training.universitydb.model.dto.TeachingCourseDTOResponse;
import com.training.universitydb.repository.*;
import com.training.universitydb.controller.exception.DataNotFoundException;
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
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class TeachingCourseService {
    TeachingCourseRepository teachingCourseRepository;
    TakingCourseRepository takingCourseRepository;
    SemesterRepository semesterRepository;
    CourseRepository courseRepository;
    ProfessorRepository professorRepository;

    TeachingCourseDTOMapper teachingCourseDTOMapper;


    public List<TeachingCourseDTOResponse> getAll() {
        return teachingCourseRepository.findAll().stream().map(teachingCourseDTOMapper::entityToDto).collect(Collectors.toList());
    }

    public TeachingCourseDTOResponse getById(Long id) {
        return teachingCourseDTOMapper.entityToDto(teachingCourseRepository.findById(id).orElseThrow(DataNotFoundException::new));
    }

    public Void removeById(Long id) {
        TeachingCourseEntity teachingCourseEntity = teachingCourseRepository.findById(id).orElseThrow(DataNotFoundException::new);
        return remove(teachingCourseEntity);
    }

    @Transactional
    public Void remove(TeachingCourseEntity teachingCourseEntity){
        TakingCourseEntity takingCourseEntity = teachingCourseEntity.getTakingCourseEntity();
        Set<StudentEntity> students = takingCourseEntity.getStudents();
        students.stream().forEach(studentEntity -> {
            studentEntity.getCourses().remove(takingCourseEntity);
        });
        takingCourseRepository.delete(takingCourseEntity);
        teachingCourseRepository.delete(teachingCourseEntity);
        return null;
    }

    public Void create(TeachingCourseDTORequest teachingCourseDTORequest) {
        CourseEntity course = courseRepository.findById(teachingCourseDTORequest.getCourse()).orElseThrow(DataNotFoundException::new);
        SemesterEntity semester = semesterRepository.findById(teachingCourseDTORequest.getSemester()).orElseThrow(DataNotFoundException::new);
        ProfessorEntity professor = professorRepository.findById(teachingCourseDTORequest.getProfessor()).orElseThrow(DataNotFoundException::new);
        TakingCourseEntity takingCourse = takingCourseRepository.findById(teachingCourseDTORequest.getTakingCourse()).orElseThrow(DataNotFoundException::new);

        TeachingCourseEntity teachingCourseEntity = new TeachingCourseEntity();
        teachingCourseEntity.setCourse(course);
        teachingCourseEntity.setSemester(semester);
        teachingCourseEntity.setProfessor(professor);
        teachingCourseEntity.setTakingCourseEntity(takingCourse);
        teachingCourseRepository.save(teachingCourseEntity);
        return null;
    }

    public Void update(TeachingCourseDTORequest teachingCourseDTORequest, Long id) {
        CourseEntity course = courseRepository.findById(teachingCourseDTORequest.getCourse()).orElseThrow(DataNotFoundException::new);
        SemesterEntity semester = semesterRepository.findById(teachingCourseDTORequest.getSemester()).orElseThrow(DataNotFoundException::new);
        ProfessorEntity professor = professorRepository.findById(teachingCourseDTORequest.getProfessor()).orElseThrow(DataNotFoundException::new);
        TakingCourseEntity takingCourse = takingCourseRepository.findById(teachingCourseDTORequest.getTakingCourse()).orElseThrow(DataNotFoundException::new);

        TeachingCourseEntity teachingCourseEntity = teachingCourseRepository.findById(id).orElseThrow(DataNotFoundException::new);
        teachingCourseEntity.setCourse(course);
        teachingCourseEntity.setSemester(semester);
        teachingCourseEntity.setProfessor(professor);
        teachingCourseEntity.setTakingCourseEntity(takingCourse);
        teachingCourseRepository.save(teachingCourseEntity);
        return null;
    }
}
