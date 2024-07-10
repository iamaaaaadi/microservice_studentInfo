package com.assignment.student.service.impl;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.assignment.student.dao.StudentRepository;
import com.assignment.student.entity.Student;
import com.assignment.student.response.StudentPaginationResponse;
import com.assignment.student.service.StudentService;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;

@Service
public class StudentServiceImpl implements StudentService {

	private final StudentRepository studentRepository;

	@Autowired
	public StudentServiceImpl(StudentRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}

	@Override
	public String saveStudents(MultipartFile file) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub

		Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
		HeaderColumnNameTranslateMappingStrategy<Student> strategy = new HeaderColumnNameTranslateMappingStrategy<>();

		strategy.setType(Student.class);
		Map<String, String> mapping = new HashMap<>();
		mapping.put("id", "id");
		mapping.put("name", "name");
		mapping.put("department", "department");
		mapping.put("address", "address");
		mapping.put("phoneNumber", "phoneNumber");
		strategy.setColumnMapping(mapping);

		List<Student> students = new CsvToBeanBuilder<Student>(reader).withType(Student.class)
				.withMappingStrategy(strategy).withIgnoreLeadingWhiteSpace(true).build().parse();

		studentRepository.saveAll(students);
		return "Students Uploaded Successfully";
	}


	@Override
	public StudentPaginationResponse fetchAllStudents(int pageNumber, int pageSize) {
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

}
