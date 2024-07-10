package com.assignment.student.response;

import com.assignment.student.entity.Student;
import java.util.*;

public class StudentPaginationResponse {

	private List<Student> students;

	private long totalElements;

	private int totalPages;

	public List<Student> getStudents() {

		return students;

	}

	public void setStudents(List<Student> students) {

		this.students = students;

	}

	public long getTotalElements() {

		return totalElements;

	}

	public void setTotalElements(long totalElements) {

		this.totalElements = totalElements;

	}

	public int getTotalPages() {

		return totalPages;

	}

	public void setTotalPages(int totalPages) {

		this.totalPages = totalPages;

	}
}
