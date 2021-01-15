package com.maxsasha.services.currentService.db.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.maxsasha.services.currentService.entity.Student;

public class StudentRepositoryImpl implements StudentRepositoryCustom {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void updateDocumentMiddleName(String id, String middleName) {
        Query query = new Query(Criteria.where("id").is(id));
        Update update = new Update().set("middleName", middleName);
        mongoTemplate.findAndModify(query, update, Student.class);
    }
}