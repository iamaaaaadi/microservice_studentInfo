package com.assignment.student.service;


import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.assignment.student.entity.Student;
import com.assignment.student.exception.StudentApplicationException;
import com.assignment.student.response.StudentPaginationResponse;

public interface StudentService {
	String saveStudents(MultipartFile file) throws StudentApplicationException, IOException;

	StudentPaginationResponse fetchByPagination(int pageNumber, int pageSize);
	
	List<Student> getAllStudents(); 
}
