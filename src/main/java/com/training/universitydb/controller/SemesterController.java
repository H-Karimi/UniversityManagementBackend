package com.training.universitydb.controller;

import com.training.universitydb.model.dto.SemesterDTORequest;
import com.training.universitydb.model.dto.SemesterDTOResponse;
import com.training.universitydb.service.SemesterService;
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
@RequestMapping(value = "/semester")
public class SemesterController {
    SemesterService semesterService;

    @GetMapping
    public ResponseEntity<List<SemesterDTOResponse>> getAll(){
        return ResponseEntity.ok().body(semesterService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SemesterDTOResponse> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(semesterService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeById(@PathVariable("id") Long id){
        semesterService.removeById(id);
        return ResponseEntity.ok().body(null);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody SemesterDTORequest semesterDTORequest){
        semesterService.create(semesterDTORequest);
        return ResponseEntity.ok().body(null);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "{/id}")
    public ResponseEntity<Void> update(@RequestBody SemesterDTORequest semesterDTORequest, @PathVariable("id") Long id){
        semesterService.update(semesterDTORequest, id);
        return ResponseEntity.ok().body(null);
    }
}
