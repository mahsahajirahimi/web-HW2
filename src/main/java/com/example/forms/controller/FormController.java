package com.example.forms.controller;

import com.example.forms.model.Field;
import com.example.forms.model.Form;
import com.example.forms.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/forms")
public class FormController {
    private final FormService formService;

    @Autowired
    public FormController(FormService formService) {
        this.formService = formService;
    }

    @GetMapping
    public List<Form> getAllForms() {
        return formService.getAllForms();
    }

    @PostMapping
    public ResponseEntity<Form> createForm(@RequestBody Form form) {
        try {
            Form createdForm = formService.createForm(form);
            return ResponseEntity.ok(createdForm);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFormById(@PathVariable String id) {
        Form form = formService.getFormById(id);
        if (form != null) {
            return ResponseEntity.ok(form);
        } else {
            return ResponseEntity.status(404).body("Form not found!");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateForm(@PathVariable String id, @RequestBody Form formDetails) {
        Form updatedForm = formService.updateForm(id, formDetails);
        if (updatedForm != null) {
            return ResponseEntity.ok(updatedForm);
        } else {
            return ResponseEntity.status(404).body("Form not found!");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteForm(@PathVariable String id) {
        boolean deleted = formService.deleteForm(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(404).body("Form not found!");
        }
    }

    @GetMapping("/{id}/fields")
    public ResponseEntity<?> getFormFields(@PathVariable String id) {
        List<Field> fields = formService.getFormFields(id);
        if (fields != null) {
            return ResponseEntity.ok(fields);
        } else {
            return ResponseEntity.status(404).body("Form not found!");
        }
    }

    @PutMapping("/{id}/fields")
    public ResponseEntity<?> updateFormFields(@PathVariable String id, @RequestBody List<Field> fields) {
        boolean updated = formService.updateFormFields(id, fields);
        if (updated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(404).body("Form not found!");
        }
    }
    

    @PostMapping("/{id}/publish")
    public ResponseEntity<?> changeFormPublishStatus(@PathVariable String id) {
        boolean changed = formService.changeFormPublishStatus(id);
        if (changed) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(404).body("Form not found!");
        }
    }

    @GetMapping("/published")
    public ResponseEntity<?> getPublishedForms() {
        List<Form> publishedForms = formService.getPublishedForms();
        return ResponseEntity.ok(publishedForms);
    }
}
