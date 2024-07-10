package com.assignment.student.request;

import com.opencsv.bean.CsvBindByPosition;

public class StudentCsvDto {

	@CsvBindByPosition(position = 0)
	String id;
	@CsvBindByPosition(position = 1)
	String name;
	@CsvBindByPosition(position = 2)
	String department;
	@CsvBindByPosition(position = 2)
	String address;
	@CsvBindByPosition(position = 2)
	String phoneNumber;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

//	@Override
//	public String toString() {
//		return "StudentCsvDto [id=" + id + ", name=" + name + ", age=" + age + "]";
//	}

}