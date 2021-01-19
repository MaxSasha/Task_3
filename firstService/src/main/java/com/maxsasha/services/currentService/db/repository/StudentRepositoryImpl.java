package com.maxsasha.services.currentService.db.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.maxsasha.services.currentService.entity.Student;

public class StudentRepositoryImpl implements StudentRepositoryCustom {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Student updateDocumentFirstName(String id, String firstName) {
        Query query = new Query(Criteria.where("id").is(id));
        Update update = new Update().set("firstName", firstName);
        return mongoTemplate.findAndModify(query, update, Student.class);
    }

    @Override
    public String exceptionMaker() {
        throw new RuntimeException();
    }
}