package com.training.universitydb.controller;

import com.training.universitydb.model.dto.TeachingCourseDTORequest;
import com.training.universitydb.model.dto.TeachingCourseDTOResponse;
import com.training.universitydb.service.TeachingCourseService;
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
@RequestMapping(value = "/teaching-course")
public class TeachingCourseController {
    TeachingCourseService teachingCourseService;

    @GetMapping
    public ResponseEntity<List<TeachingCourseDTOResponse>> getAll(){
        return ResponseEntity.ok().body(teachingCourseService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeachingCourseDTOResponse> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(teachingCourseService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeById(@PathVariable("id") Long id){
        teachingCourseService.removeById(id);
        return ResponseEntity.ok().body(null);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody TeachingCourseDTORequest teachingCourseDTORequest){
        teachingCourseService.create(teachingCourseDTORequest);
        return ResponseEntity.ok().body(null);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody TeachingCourseDTORequest teachingCourseDTORequest, @PathVariable("id") Long id){
        teachingCourseService.update(teachingCourseDTORequest, id);
        return ResponseEntity.ok().body(null);
    }
}
