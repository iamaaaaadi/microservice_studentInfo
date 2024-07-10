package com.assignment.student.service;
import java.util.*;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.assignment.student.entity.Student;
import com.assignment.student.response.StudentPaginationResponse;


public interface StudentService {
	String saveStudents(MultipartFile file) throws FileNotFoundException, IOException;
	StudentPaginationResponse fetchAllStudents(int pageNumber, int pageSize); 
}
