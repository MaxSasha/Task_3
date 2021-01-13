package com.maxsasha.db.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.maxsasha.entity.Student;

@Repository
public interface StudentRepository extends MongoRepository<Student, String>, StudentRepositoryCustom {
    Page<Student> findAll(Pageable pageable);
}