package com.example.forms.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.forms.model.Form;
import java.util.List;

@Repository
public interface FormRepository extends MongoRepository<Form, String> {
    List<Form> findByPublished(boolean published);
}
