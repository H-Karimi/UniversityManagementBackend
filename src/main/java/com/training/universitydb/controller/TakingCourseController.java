package com.training.universitydb.controller;

import com.training.universitydb.model.StudentEntity;
import com.training.universitydb.model.dto.StudentDTOResponse;
import com.training.universitydb.model.dto.TakingCourseDTORequest;
import com.training.universitydb.model.dto.TakingCourseDTOResponse;
import com.training.universitydb.service.TakingCourseService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
@RequiredArgsConstructor
@RequestMapping(value = "/taking-course")
public class TakingCourseController {
    TakingCourseService takingCourseService;

    @GetMapping
    public ResponseEntity<List<TakingCourseDTOResponse>> getAll(){
        return ResponseEntity.ok().body(takingCourseService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TakingCourseDTOResponse> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(takingCourseService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeById(@PathVariable("id") Long id){
        takingCourseService.removeById(id);
        return ResponseEntity.ok().body(null);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody TakingCourseDTORequest takingCourseDTORequest){
        takingCourseService.create(takingCourseDTORequest);
        return ResponseEntity.ok().body(null);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody TakingCourseDTORequest takingCourseDTORequest, @PathVariable("id") Long id){
        takingCourseService.update(takingCourseDTORequest, id);
        return ResponseEntity.ok().body(null);
    }

    @GetMapping(value = "/{id}/students")
    public ResponseEntity<List<StudentDTOResponse>> getAllStudents(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(takingCourseService.getAllStudents(id));
    }
}
