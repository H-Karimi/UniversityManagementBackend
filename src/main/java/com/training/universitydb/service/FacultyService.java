package com.training.universitydb.service;

import com.training.universitydb.model.FacultyEntity;
import com.training.universitydb.model.ProfessorEntity;
import com.training.universitydb.model.dto.FacultyDTORequest;
import com.training.universitydb.model.dto.FacultyDTOResponse;
import com.training.universitydb.repository.FacultyRepository;
import com.training.universitydb.controller.exception.DataNotFoundException;
import com.training.universitydb.repository.ProfessorRepository;
import com.training.universitydb.service.mapper.FacultyDTOMapper;
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
public class FacultyService {
    FacultyRepository facultyRepository;
    ProfessorRepository professorRepository;

    FacultyDTOMapper facultyDTOMapper;

    public List<FacultyDTOResponse> getAll(){
        return facultyRepository.findAll().stream().map(facultyDTOMapper::entityToDto).collect(Collectors.toList());
    }

    public FacultyDTOResponse getById(Long id){
        return facultyDTOMapper.entityToDto(facultyRepository.findById(id).orElseThrow(DataNotFoundException::new));
    }

    @Transactional
    public Void removeById(Long id){
        FacultyEntity facultyEntity = facultyRepository.findById(id).orElseThrow(DataNotFoundException::new);
        facultyEntity.getProfessors().stream().forEach(professorEntity -> professorEntity.setFaculty(null));
        facultyEntity.getStudents().stream().forEach(studentEntity -> studentEntity.setFaculty(null));
        facultyRepository.delete(facultyEntity);
        return null;
    }

    public Void create(FacultyDTORequest facultyDTORequest){
        FacultyEntity facultyEntity = facultyDTOMapper.dtoToEntity(facultyDTORequest);
        facultyEntity.setHeadProfessor(professorRepository.findById(facultyDTORequest.getHeadProfessor()).orElseThrow(DataNotFoundException::new));
        facultyRepository.save(facultyEntity);
        return null;
    }

    public Void update(FacultyDTORequest facultyDTORequest, Long id){
        ProfessorEntity headProfessor = professorRepository.findById(facultyDTORequest.getHeadProfessor()).orElseThrow(DataNotFoundException::new);

        FacultyEntity facultyEntity = facultyRepository.findById(id).orElseThrow(DataNotFoundException::new);
        facultyEntity.setTitle(facultyDTORequest.getTitle());
        facultyEntity.setHeadProfessor(headProfessor);
        facultyRepository.save(facultyEntity);
        return null;
    }
}
