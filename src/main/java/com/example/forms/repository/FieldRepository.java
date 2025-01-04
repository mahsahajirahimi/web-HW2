package com.example.forms.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.forms.model.Field;

@Repository
public interface FieldRepository extends MongoRepository<Field, String> {
}
