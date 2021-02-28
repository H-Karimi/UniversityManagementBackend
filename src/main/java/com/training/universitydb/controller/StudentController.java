package com.training.universitydb.controller;

import com.training.universitydb.model.dto.*;
import com.training.universitydb.service.StudentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {
    StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentDTOResponse>> getAll(){
        return ResponseEntity.ok().body(studentService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTOResponse> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(studentService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeById(@PathVariable("id") Long id){
        studentService.removeById(id);
        return ResponseEntity.ok().body(null);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody StudentDTORequest studentDTORequest){
        studentService.create(studentDTORequest);
        return ResponseEntity.ok().body(null);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody StudentDTORequest studentDTORequest, @PathVariable("id") Long id){
        studentService.update(studentDTORequest, id);
        return ResponseEntity.ok().body(null);
    }

    @GetMapping("/{id}/taken-courses")
    public ResponseEntity<Set<TeachingCourseDTOResponse>> getAllTakenCoursesByStudent(@PathVariable("id") Long studentId){
        return ResponseEntity.ok().body(studentService.getAllTakenCoursesByStudent(studentId));
    }

    @PutMapping("{id}/add-course/{taking_course_id}")
    public ResponseEntity<Void> addCourse(@PathVariable("id") Long id, @PathVariable("taking_course_id") Long takingCourseId){
        studentService.addCourse(id, takingCourseId);
        return ResponseEntity.ok().body(null);
    }
}
