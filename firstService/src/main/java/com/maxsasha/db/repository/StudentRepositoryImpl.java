package com.maxsasha.db.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.maxsasha.entity.Student;

public class StudentRepositoryImpl implements StudentRepositoryCustom {
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void updateDocumentFirstName(String id, String firstName) {
        Query query = new Query(Criteria.where("id").is(id));
        Update update = new Update();
        update.set("firstName", firstName);
        mongoTemplate.findAndModify(query, update, Student.class);
    }
}