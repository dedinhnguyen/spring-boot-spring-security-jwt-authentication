package com.demo.springjwt.demo.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.springjwt.demo.dto.StudentDTO;
import com.demo.springjwt.demo.model.Student;
@Service
@Transactional
public interface StudentService {
	Student saveStudent(Student student);
	void deleteStudentById(long id);
	
	Student findStudentById(long studentid);
	
	void deleteAllStudent();
	List<StudentDTO> findAll();
	StudentDTO saveStudent(StudentDTO studentdto);
	StudentDTO updateStudent(long studentid,StudentDTO studentdto);
	List<StudentDTO> findAllPagingAndSorting(int pageNum, int pageSize, String sort);
	
	List<StudentDTO> findAll(Pageable pageable);
}
