package com.demo.springjwt.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.springjwt.demo.Config.NewOuput;
import com.demo.springjwt.demo.dto.StudentDTO;
import com.demo.springjwt.demo.exception.ResourceNotFoundExeption;
import com.demo.springjwt.demo.mapper.Convert;
import com.demo.springjwt.demo.model.Student;
import com.demo.springjwt.demo.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {
	@Autowired
	private Convert convert;
	
	@Autowired
	private StudentService studentservice;
	
	@PreAuthorize("hasAnyRole('ADMIN','MODERATOR')")
	@PostMapping
	public ResponseEntity<StudentDTO> saveStudent(@Valid @RequestBody StudentDTO studentdto) {
		try {
		StudentDTO studentdtorequest = studentservice.saveStudent(studentdto);
		return new ResponseEntity<StudentDTO>(studentdtorequest, HttpStatus.CREATED);
		}
		catch(Exception e) {
		return new ResponseEntity<>(null, HttpStatus.CREATED);
		}
	}
	@PreAuthorize("hasAnyRole('ADMIN','MODERATOR')")
	@GetMapping
	public List<StudentDTO> findAllStudent(){
		return studentservice.findAll();
		
	}
	
	@PreAuthorize("hasAnyRole('ADMIN','MODERATOR')")
	@GetMapping("/page/")
	public NewOuput showNew(@RequestParam("page") int page, @RequestParam("limit") int limit, @RequestParam String sort) {
		NewOuput result= new NewOuput();
		result.setPage(page);
		result.setListResult(studentservice.findAllPagingAndSorting(page,limit, sort));
		return result;
	}
	
	@PreAuthorize("hasAnyRole('ADMIN','MODERATOR','USER')")
	@GetMapping("/{id}")
	public ResponseEntity<StudentDTO> getStudentById(@PathVariable(name = "id") Long id) throws ResourceNotFoundExeption {
		try {
		Student student = studentservice.findStudentById(id);

		StudentDTO studentResponse = convert.toDTO(student);

		return ResponseEntity.ok().body(studentResponse);
	}
		catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PreAuthorize("hasAnyRole('ADMIN','MODERATOR')")
	@PutMapping("/{id}")
	public ResponseEntity<StudentDTO> updateStudent(@PathVariable long id, @RequestBody StudentDTO studentDto) throws ResourceNotFoundExeption {
		try {
			
		StudentDTO studentdtorequest = studentservice.updateStudent(id,studentDto);

		return ResponseEntity.ok().body(studentdtorequest);
		}
		catch(Exception e){
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasAnyRole('ADMIN','MODERATOR')")
	@DeleteMapping
	public ResponseEntity<String> deleteAllStudent(){
		try {
			studentservice.deleteAllStudent();
			return ResponseEntity.ok().body("Student deleted with success");
	}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PreAuthorize("hasAnyRole('MODERATOR')")
	@DeleteMapping("{id}")
	public ResponseEntity<HttpStatus> deleteStudent(@PathVariable(name = "id") Long id){
		try {
			
		studentservice.deleteStudentById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		catch(Exception e){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}