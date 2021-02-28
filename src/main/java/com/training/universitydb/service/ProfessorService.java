package com.training.universitydb.service;

import com.training.universitydb.model.FacultyEntity;
import com.training.universitydb.model.ProfessorEntity;
import com.training.universitydb.model.dto.ProfessorDTORequest;
import com.training.universitydb.model.dto.ProfessorDTOResponse;
import com.training.universitydb.repository.FacultyRepository;
import com.training.universitydb.repository.ProfessorRepository;
import com.training.universitydb.controller.exception.DataNotFoundException;
import com.training.universitydb.service.mapper.ProfessorDTOMapper;
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
public class ProfessorService {
    ProfessorRepository professorRepository;
    FacultyRepository facultyRepository;

    ProfessorDTOMapper professorDTOMapper;

    TeachingCourseService teachingCourseService;

    public List<ProfessorDTOResponse> getAll(){
        return professorRepository.findAll().stream().map(professorDTOMapper::entityToDto).collect(Collectors.toList());
    }

    public ProfessorDTOResponse getById(Long id){
        return professorDTOMapper.entityToDto(professorRepository.findById(id).orElseThrow(DataNotFoundException::new));
    }

    @Transactional
    public Void removeById(Long id){
        ProfessorEntity professorEntity = professorRepository.findById(id).orElseThrow(DataNotFoundException::new);
        professorEntity.getTeachingCourseEntitySet().stream().forEach(teachingCourseService::remove);
        professorRepository.delete(professorEntity);
        return null;
    }

    public Void create(ProfessorDTORequest professorDTORequest){
        ProfessorEntity professorEntity = professorDTOMapper.dtoToEntity(professorDTORequest);
        professorEntity.setFaculty(facultyRepository.findById(professorDTORequest.getFaculty()).orElseThrow(DataNotFoundException::new));
        professorRepository.save(professorEntity);
        return null;
    }

    public Void update(ProfessorDTORequest professorDTORequest, Long id){
        FacultyEntity faculty = facultyRepository.findById(professorDTORequest.getFaculty()).orElseThrow(DataNotFoundException::new);

        ProfessorEntity professorEntity = professorRepository.findById(id).orElseThrow(DataNotFoundException::new);
        professorEntity.setName(professorDTORequest.getName());
        professorEntity.setBirthDate(professorDTORequest.getBirthDate());
        professorEntity.setFaculty(faculty);
        professorRepository.save(professorEntity);
        return null;
    }
}
