package com.example.forms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.forms.model.Field;
import com.example.forms.repository.FieldRepository;

@RestController
@RequestMapping("/fields")
public class FieldController {
    private final FieldRepository fieldRepository;

    @Autowired
    public FieldController(FieldRepository fieldRepository) {
        this.fieldRepository = fieldRepository;
    }

    @GetMapping
    public List<Field> getAllFields() {
        return fieldRepository.findAll();
    }

    @PostMapping
    public Field createField(@RequestBody Field field) {
        return fieldRepository.save(field);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFieldById(@PathVariable String id) {
        if (fieldRepository.existsById(id)) {
            Field field = fieldRepository.findById(id).get();
            return ResponseEntity.ok(field);
        } else {
            return ResponseEntity.status(404).body("Field not found!");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateField(@PathVariable String id, @RequestBody Field fieldDetails) {
        if (fieldRepository.existsById(id)) {
            Field field = fieldRepository.findById(id).get();
            field.setFieldName(fieldDetails.getFieldName());
            field.setType(fieldDetails.getType());
            fieldRepository.save(field);
            return ResponseEntity.ok(field);
        } else {
            return ResponseEntity.status(404).body("Field not found!");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteField(@PathVariable String id) {
        if (fieldRepository.existsById(id)) {
            fieldRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(404).body("Field not found!");
        }
    }
}
