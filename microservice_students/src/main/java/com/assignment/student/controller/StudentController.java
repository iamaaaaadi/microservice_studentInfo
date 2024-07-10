package com.assignment.student.controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.assignment.student.entity.Student;
import com.assignment.student.response.StudentPaginationResponse;
import com.assignment.student.service.StudentService;
import java.util.*;
@RestController
@RequestMapping("/api/v1/")
public class StudentController {
    
	private StudentService studentService; 
	
	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}
	
    @PostMapping("upload")
    public ResponseEntity<String> uploadCSV(@RequestParam("file") MultipartFile file) throws FileNotFoundException, IOException {
    	System.out.println("Request Coming");
    	
    	String response = studentService.saveStudents(file);
    	return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping
    public ResponseEntity<StudentPaginationResponse> getAllStudents(
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
    	StudentPaginationResponse response = studentService.fetchAllStudents(pageNumber, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
	
}
