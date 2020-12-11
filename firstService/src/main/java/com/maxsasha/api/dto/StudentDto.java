package com.maxsasha.api.dto;

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
	private String firstName;
	private String midleName;
	private String lastName;
}