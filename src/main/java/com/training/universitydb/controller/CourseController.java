package com.training.universitydb.controller;

import com.training.universitydb.model.dto.CourseDTORequest;
import com.training.universitydb.model.dto.CourseDTOResponse;
import com.training.universitydb.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
@RequiredArgsConstructor
@RequestMapping(value = "/course")
@Api(value = "For all course actions")
public class CourseController {
    CourseService courseService;

    @GetMapping
    @ApiIgnore
    public ResponseEntity<List<CourseDTOResponse>> getAll(){
        return ResponseEntity.ok().body(courseService.getAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Call for a course by its ID", response = ResponseEntity.class)
    public ResponseEntity<CourseDTOResponse> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(courseService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeById(@PathVariable("id") Long id){
        courseService.removeById(id);
        return ResponseEntity.ok().body(null);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody CourseDTORequest courseDTORequest){
        courseService.create(courseDTORequest);
        return ResponseEntity.ok().body(null);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody CourseDTORequest courseDTORequest, @PathVariable("id") Long id){
        courseService.update(courseDTORequest, id);
        return ResponseEntity.ok().body(null);
    }

}
