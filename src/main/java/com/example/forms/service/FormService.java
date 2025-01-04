package com.example.forms.service;

import com.example.forms.model.Field;
import com.example.forms.model.Form;
import com.example.forms.repository.FormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

@Service
public class FormService {
    private final FormRepository formRepository;

    @Autowired
    public FormService(FormRepository formRepository) {
        this.formRepository = formRepository;
    }

    public List<Form> getAllForms() {
        return formRepository.findAll();
    }

    public Form createForm(Form form) {
        try {
            for (Field field : form.getFields()) {
                if (field.getId() == null || field.getId().isEmpty()) {
                    field.setId(new ObjectId().toString());
                }
            }
            Form savedForm = formRepository.save(form);
            System.out.println("Form saved successfully: " + savedForm);
            return savedForm;
        } catch (Exception e) {
            System.out.println("Error while saving form: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
    
    
    

    public Form getFormById(String id) {
        return formRepository.findById(id).orElse(null);
    }

    public Form updateForm(String id, Form formDetails) {
        Optional<Form> optionalForm = formRepository.findById(id);
        if (optionalForm.isPresent()) {
            Form form = optionalForm.get();
            form.setName(formDetails.getName());
            form.setSubmitMethod(formDetails.getSubmitMethod());
            form.setSubmitUrl(formDetails.getSubmitUrl());
            form.setFields(formDetails.getFields());
            form.setPublished(formDetails.isPublished());
            return formRepository.save(form);
        } else {
            return null;
        }
    }

    public boolean deleteForm(String id) {
        if (formRepository.existsById(id)) {
            formRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public List<Field> getFormFields(String id) {
        Optional<Form> optionalForm = formRepository.findById(id);
        if (optionalForm.isPresent()) {
            return optionalForm.get().getFields();
        } else {
            return null;
        }
    }



public boolean updateFormFields(String id, List<Field> fields) {
    try {
        Optional<Form> optionalForm = formRepository.findById(id);
        if (optionalForm.isPresent()) {
            Form form = optionalForm.get();
            for (Field field : fields) {
                if (field.getId() == null || field.getId().isEmpty()) {
                    field.setId(new ObjectId().toString());
                }
            }
            form.setFields(fields);
            formRepository.save(form);
            System.out.println("Form fields updated successfully for form ID: " + id);
            return true;
        } else {
            System.out.println("Form not found for ID: " + id);
            return false;
        }
    } catch (Exception e) {
        System.out.println("Error while updating form fields: " + e.getMessage());
        e.printStackTrace();
        return false;
    }
}

    
    

    public boolean changeFormPublishStatus(String id) {
        Optional<Form> optionalForm = formRepository.findById(id);
        if (optionalForm.isPresent()) {
            Form form = optionalForm.get();
            form.setPublished(!form.isPublished());
            formRepository.save(form);
            return true;
        } else {
            return false;
        }
    }

    public List<Form> getPublishedForms() {
        return formRepository.findByPublished(true);
    }
}
