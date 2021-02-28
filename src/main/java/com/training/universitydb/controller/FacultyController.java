package com.training.universitydb.controller;

import com.training.universitydb.model.dto.FacultyDTORequest;
import com.training.universitydb.model.dto.FacultyDTOResponse;
import com.training.universitydb.service.FacultyService;
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
@RequestMapping(value = "/faculty")
public class FacultyController {
    FacultyService facultyService;

    @GetMapping
    public ResponseEntity<List<FacultyDTOResponse>> getAll(){
        return ResponseEntity.ok().body(facultyService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacultyDTOResponse> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(facultyService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeById(@PathVariable("id") Long id){
        facultyService.removeById(id);
        return ResponseEntity.ok().body(null);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody FacultyDTORequest facultyDTORequest){
        facultyService.create(facultyDTORequest);
        return ResponseEntity.ok().body(null);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody FacultyDTORequest facultyDTORequest, @PathVariable("id") Long id){
        facultyService.update(facultyDTORequest, id);
        return ResponseEntity.ok().body(null);
    }
}
