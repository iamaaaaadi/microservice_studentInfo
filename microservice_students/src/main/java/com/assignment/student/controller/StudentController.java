package com.assignment.student.controller;

import java.io.IOException;
import java.util.List;

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
import com.assignment.student.exception.StudentApplicationException;
import com.assignment.student.response.StudentPaginationResponse;
import com.assignment.student.service.StudentService;
import com.assignment.student.util.ConstantUtil;

@RestController
@RequestMapping("/api/v1/")
public class StudentController {

	private StudentService studentService;

	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	
	//To Upload a CSV file
	@PostMapping("upload")
	public ResponseEntity<String> uploadCSV(@RequestParam("file") MultipartFile file)
			throws StudentApplicationException, IOException {
		if (file.isEmpty()) {
			throw new StudentApplicationException(HttpStatus.NO_CONTENT, ConstantUtil.CSV_EMPTY);
		}
		String response = studentService.saveStudents(file);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
    
	//To fetch students info by pagination 
	@GetMapping("/pagination/all")
	public ResponseEntity<StudentPaginationResponse> getAllStudents(
			@RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
			@RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
		StudentPaginationResponse response = studentService.fetchByPagination(pageNumber, pageSize);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
    
	
	//To fetch all students info
	@GetMapping
	public ResponseEntity<List<Student>> getAllStudents() {
		List<Student> students = studentService.getAllStudents();
		return new ResponseEntity<>(students, HttpStatus.OK);
	}

}
