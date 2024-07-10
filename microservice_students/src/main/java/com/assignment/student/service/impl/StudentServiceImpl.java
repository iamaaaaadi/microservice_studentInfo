package com.assignment.student.service.impl;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.assignment.student.dao.StudentRepository;
import com.assignment.student.entity.Student;
import com.assignment.student.exception.StudentApplicationException;
import com.assignment.student.request.StudentCsvDto;
import com.assignment.student.response.StudentPaginationResponse;
import com.assignment.student.service.StudentService;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;

@Service
public class StudentServiceImpl implements StudentService {

	private final StudentRepository studentRepository;

	@Autowired
	public StudentServiceImpl(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@Override
	public String saveStudents(MultipartFile file) throws StudentApplicationException, IOException {
		
	    Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
	    HeaderColumnNameTranslateMappingStrategy<StudentCsvDto> strategy = new HeaderColumnNameTranslateMappingStrategy<>();

	    strategy.setType(StudentCsvDto.class);
	    
	    //Mapping the data
	    
	    Map<String, String> mapping = new HashMap<>();
	    mapping.put("id", "id");
	    mapping.put("name", "name");
	    mapping.put("department", "department");
	    mapping.put("address", "address");
	    mapping.put("phoneNumber", "phoneNumber");
	    strategy.setColumnMapping(mapping);
           
	    List<StudentCsvDto> studentCsvDtos = new CsvToBeanBuilder<StudentCsvDto>(reader)
	            .withType(StudentCsvDto.class)
	            .withMappingStrategy(strategy)
	            .withIgnoreLeadingWhiteSpace(true)
	            .build()
	            .parse();
        
	    //Creating List of Student to insert in Database
	    
	    List<Student> students = studentCsvDtos.stream()
	            .map(dto -> {
	                Student student = new Student();
	                student.setId(Long.parseLong(dto.getId())); // Assuming id is of type Long in Student entity
	                student.setName(dto.getName());
	                student.setDepartment(dto.getDepartment());
	                student.setAddress(dto.getAddress());
	                student.setPhoneNumber(dto.getPhoneNumber());
	                return student;
	            })
	            .collect(Collectors.toList());

	    studentRepository.saveAll(students);
	    return "Students Data Uploaded Successfully";

	}

	
	//Fetching Student Info by Pagination

	@Override
	public StudentPaginationResponse fetchByPagination(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Student> page = studentRepository.findAll(pageable);
 
        List<Student> students = page.getContent();
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();
 
        StudentPaginationResponse response = new StudentPaginationResponse();
        response.setStudents(students);
        response.setTotalElements(totalItems);
        response.setTotalPages(totalPages);
        return response;
    }
	
	//Fetching all Student Info 
	
	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

}
