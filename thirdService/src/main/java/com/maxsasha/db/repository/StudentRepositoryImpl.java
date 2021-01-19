package com.maxsasha.db.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.maxsasha.entity.Student;

public class StudentRepositoryImpl implements StudentRepositoryCustom {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void updateDocumentLastName(String id, String lastName) {
        Query query = new Query(Criteria.where("id").is(id));
        Update update = new Update().set("lastName", lastName);
        mongoTemplate.findAndModify(query, update, Student.class);
    }
}