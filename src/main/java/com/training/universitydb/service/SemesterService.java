package com.training.universitydb.service;

import com.training.universitydb.model.SemesterEntity;
import com.training.universitydb.model.dto.SemesterDTORequest;
import com.training.universitydb.model.dto.SemesterDTOResponse;
import com.training.universitydb.repository.SemesterRepository;
import com.training.universitydb.controller.exception.DataNotFoundException;
import com.training.universitydb.service.mapper.SemesterDTOMapper;
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
public class SemesterService {
    SemesterRepository semesterRepository;
    SemesterDTOMapper semesterDTOMapper;

    TeachingCourseService teachingCourseService;

    public List<SemesterDTOResponse> getAll(){
        return semesterRepository.findAll().stream().map(semesterDTOMapper::entityToDto).collect(Collectors.toList());
    }

    public SemesterDTOResponse getById(Long id){
        return semesterDTOMapper.entityToDto(semesterRepository.findById(id).orElseThrow(DataNotFoundException::new));
    }

    @Transactional
    public Void removeById(Long id){
        SemesterEntity semesterEntity = semesterRepository.findById(id).orElseThrow(DataNotFoundException::new);
        semesterEntity.getTeachingCourseEntitySet().stream().forEach(teachingCourseService::remove);
        semesterRepository.delete(semesterEntity);
        return null;
    }

    public Void create(SemesterDTORequest semesterDTORequest){
        SemesterEntity semesterEntity = semesterDTOMapper.dtoToEntity(semesterDTORequest);
        semesterRepository.save(semesterEntity);
        return null;
    }

    public Void update(SemesterDTORequest semesterDTORequest, Long id){
        SemesterEntity semesterEntity = semesterRepository.findById(id).orElseThrow(DataNotFoundException::new);
        semesterEntity.setStartDate(semesterDTORequest.getStartDate());
        semesterEntity.setEndDate(semesterDTORequest.getEndDate());
        semesterRepository.save(semesterEntity);
        return null;
    }
}
