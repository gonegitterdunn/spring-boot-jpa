package com.springbootrestgraphjpa.controller;

import com.springbootrestgraphjpa.entity.Student;
import com.springbootrestgraphjpa.request.CreateStudentRequest;
import com.springbootrestgraphjpa.request.InQueryRequest;
import com.springbootrestgraphjpa.request.UpdateStudentRequest;
import com.springbootrestgraphjpa.response.StudentResponse;
import com.springbootrestgraphjpa.service.StudentService;
import org.hibernate.validator.constraints.ParameterScriptAssert;
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
    return getStudentResponses(studentList);
  }

  @GetMapping("getByFirstName/{firstName}")
  public List<StudentResponse> getByFirstName(@PathVariable final String firstName) {
    List<Student> studentList = studentService.getByFirstName(firstName);
    return getStudentResponses(studentList);
  }

  @GetMapping("getByFirstNameAndLastName/{firstName}/{lastName}")
  public StudentResponse getByFirstNameAndLastName(
      @PathVariable final String firstName, @PathVariable final String lastName) {

    return new StudentResponse(studentService.getByFirstNameAndLastName(firstName, lastName));
  }

  @GetMapping("getByFirstNameOrLastName/{firstName}/{lastName}")
  public List<StudentResponse> getByFirstNameOrLastName(
      @PathVariable final String firstName, @PathVariable final String lastName) {
    List<Student> studentList = studentService.getByFirstNameOrLastName(firstName, lastName);
    return getStudentResponses(studentList);
  }

  @GetMapping("getByFirstNameIn")
  public List<StudentResponse> getByFirstNameIn(@RequestBody InQueryRequest inQueryRequest) {
    List<Student> studentList = studentService.getByFirstNameIn(inQueryRequest);
    return getStudentResponses(studentList);
  }

  @GetMapping("getAllStudentsWithPagination")
  public List<StudentResponse> getAllStudentsWithPagination(
      @RequestParam final int pageNo, @RequestParam final int pageSize) {
    List<Student> studentList = studentService.getAllStudentsWithPagination(pageNo, pageSize);
    return getStudentResponses(studentList);
  }

  @GetMapping("getAllWithSorting")
  public List<StudentResponse> getAllWithSorting() {
    List<Student> studentList = studentService.getAllStudentsWithSorting();
    return getStudentResponses(studentList);
  }

  @GetMapping("like/{firstName}")
  public List<StudentResponse> like(@PathVariable final String firstName) {
    List<Student> studentList = studentService.like(firstName);
    return getStudentResponses(studentList);
  }

  @GetMapping("startsWith/{firstName}")
  public List<StudentResponse> startsWith(@PathVariable final String firstName) {
    List<Student> studentList = studentService.startsWith(firstName);
    return getStudentResponses(studentList);
  }

  @GetMapping("endsWith/{firstName}")
  public List<StudentResponse> endsWith(@PathVariable final String firstName) {
    List<Student> studentList = studentService.endsWith(firstName);
    return getStudentResponses(studentList);
  }

  private List<StudentResponse> getStudentResponses(final List<Student> studentList) {
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

  @PutMapping("updateFirstName/{id}/{firstName}")
  public String updateStudentWithJpql(@PathVariable final long id, @PathVariable final String firstName) {
    return String.format("%s student(s) updated",studentService.updateStudentWithJpql(id, firstName));
  }

  @DeleteMapping("delete")
  public String deleteStudentRequestParam(@RequestParam final long id) {
    return studentService.deleteStudent(id);
  }

  @DeleteMapping("delete/{id}")
  public String deleteStudentPathVariable(@PathVariable("id") final long idd) {
    return studentService.deleteStudent(idd);
  }

  @DeleteMapping("deleteByFirstName/{firstName}")
  public String deleteByFirstName(@PathVariable("firstName") final String firstname) {
    return String.format("%s student(s) got deleted", studentService.deleteByFirstName(firstname));
  }
}
