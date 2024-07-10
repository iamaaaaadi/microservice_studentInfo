package com.assignment.student.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.student.entity.Student;
import com.assignment.student.response.StudentPaginationResponse;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    
	
}



