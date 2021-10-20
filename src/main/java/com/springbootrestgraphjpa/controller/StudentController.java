package com.springbootrestgraphjpa.controller;

import com.springbootrestgraphjpa.entity.Student;
import com.springbootrestgraphjpa.request.CreateStudentRequest;
import com.springbootrestgraphjpa.request.UpdateStudentRequest;
import com.springbootrestgraphjpa.response.StudentResponse;
import com.springbootrestgraphjpa.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/student/")
public class StudentController {

  @Autowired StudentService studentService;

  @GetMapping("getAll")
  public List<StudentResponse> getAllStudents() {
    List<Student> studentList = studentService.getAllStudents();
    List<StudentResponse> studentResponseList = new ArrayList<>();

    studentList.forEach(student -> studentResponseList.add(new StudentResponse(student)));
    return studentResponseList;
  }

  @PostMapping("create")
  public StudentResponse createStudent(
      @Valid @RequestBody final CreateStudentRequest createStudentRequest) {
    Student student = studentService.createStudent(createStudentRequest);

    return new StudentResponse(student);
  }

  @PutMapping("update")
  public StudentResponse updateStudent(
      @Valid @RequestBody final UpdateStudentRequest updateStudentRequest) {
    Student student = studentService.updateStudent(updateStudentRequest);

    return new StudentResponse(student);
  }

  @DeleteMapping("delete")
  public String deleteStudentRequestParam(@RequestParam final long id) {
    return studentService.deleteStudent(id);
  }

  @DeleteMapping("delete/{id}")
  public String deleteStudentPathVariable(@PathVariable("id") long idd) {
    return studentService.deleteStudent(idd);
  }
}
