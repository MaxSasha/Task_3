package com.maxsasha.services.second.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class StudentDto {
    private String id;
    private String middleName;
    private String lastName;
}