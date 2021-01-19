package com.maxsasha.services.currentService.db.repository;

import com.maxsasha.services.currentService.entity.Student;

public interface StudentRepositoryCustom {
    Student updateDocumentFirstName(String id, String firstName);
    String exceptionMaker();
}