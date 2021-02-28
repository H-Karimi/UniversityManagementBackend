package com.training.universitydb.controller;

import com.training.universitydb.model.dto.ProfessorDTORequest;
import com.training.universitydb.model.dto.ProfessorDTOResponse;
import com.training.universitydb.service.ProfessorService;
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
@RequestMapping(value = "/professor")
public class ProfessorController {
    ProfessorService professorService;

    @GetMapping
    public ResponseEntity<List<ProfessorDTOResponse>> getAll(){
        return ResponseEntity.ok().body(professorService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorDTOResponse> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(professorService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeById(@PathVariable("id") Long id){
        professorService.removeById(id);
        return ResponseEntity.ok().body(null);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody ProfessorDTORequest professorDTORequest){
        professorService.create(professorDTORequest);
        return ResponseEntity.ok().body(null);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody ProfessorDTORequest professorDTORequest, @PathVariable("id") Long id){
        professorService.update(professorDTORequest, id);
        return ResponseEntity.ok().body(null);
    }
}
