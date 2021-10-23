package com.springbootrestgraphjpa.controller;

import com.springbootrestgraphjpa.entity.Student;
import com.springbootrestgraphjpa.request.CreateStudentRequest;
import com.springbootrestgraphjpa.request.InQueryRequest;
import com.springbootrestgraphjpa.request.UpdateStudentRequest;
import com.springbootrestgraphjpa.response.StudentResponse;
import com.springbootrestgraphjpa.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/student/")
public class StudentController {

  Logger logger = LoggerFactory.getLogger(StudentController.class);

  @Autowired StudentService studentService;

  @GetMapping("getAll")
  public List<StudentResponse> getAllStudents() {
    logger.error("Inside Error");
    List<Student> studentList = studentService.getAllStudents();
    return getStudentResponses(studentList);
  }

  @GetMapping("getByFirstName/{firstName}")
  public List<StudentResponse> getByFirstName(@PathVariable final String firstName) {
    return getStudentResponses(studentService.getByFirstName(firstName));
  }

  @GetMapping("getByFirstNameAndLastName/{firstName}/{lastName}")
  public StudentResponse getByFirstNameAndLastName(
      @PathVariable final String firstName, @PathVariable final String lastName) {
    return new StudentResponse(studentService.getByFirstNameAndLastName(firstName, lastName));
  }

  @GetMapping("getByFirstNameOrLastName/{firstName}/{lastName}")
  public List<StudentResponse> getByFirstNameOrLastName(
      @PathVariable final String firstName, @PathVariable final String lastName) {
    return getStudentResponses(studentService.getByFirstNameOrLastName(firstName, lastName));
  }

  @GetMapping("getByFirstNameIn")
  public List<StudentResponse> getByFirstNameIn(@RequestBody InQueryRequest inQueryRequest) {
    logger.info("inQueryRequest" + inQueryRequest);

    List<StudentResponse> response =
        getStudentResponses(studentService.getByFirstNameIn(inQueryRequest));

    logger.info("response: " + response);
    return response;
  }

  @GetMapping("getAllStudentsWithPagination")
  public List<StudentResponse> getAllStudentsWithPagination(
      @RequestParam final int pageNo, @RequestParam final int pageSize) {
    return getStudentResponses(studentService.getAllStudentsWithPagination(pageNo, pageSize));
  }

  @GetMapping("getAllWithSorting")
  public List<StudentResponse> getAllWithSorting() {
    return getStudentResponses(studentService.getAllStudentsWithSorting());
  }

  @GetMapping("like/{firstName}")
  public List<StudentResponse> like(@PathVariable final String firstName) {
    return getStudentResponses(studentService.like(firstName));
  }

  @GetMapping("startsWith/{firstName}")
  public List<StudentResponse> startsWith(@PathVariable final String firstName) {
    return getStudentResponses(studentService.startsWith(firstName));
  }

  @GetMapping("endsWith/{firstName}")
  public List<StudentResponse> endsWith(@PathVariable final String firstName) {
    return getStudentResponses(studentService.endsWith(firstName));
  }

  @GetMapping("getByCity/{city}")
  public List<StudentResponse> getByCity(@PathVariable final String city) {
    return getStudentResponses(studentService.getByCity(city));
  }

  @PostMapping("create")
  public StudentResponse createStudent(
      @Valid @RequestBody final CreateStudentRequest createStudentRequest) {
    return new StudentResponse(studentService.createStudent(createStudentRequest));
  }

  @PutMapping("update")
  public StudentResponse updateStudent(
      @Valid @RequestBody final UpdateStudentRequest updateStudentRequest) {
    return new StudentResponse(studentService.updateStudent(updateStudentRequest));
  }

  @PutMapping("updateFirstName/{id}/{firstName}")
  public String updateStudentWithJpql(
      @PathVariable final long id, @PathVariable final String firstName) {
    return String.format(
        "%s student(s) updated", studentService.updateStudentWithJpql(id, firstName));
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

  private List<StudentResponse> getStudentResponses(final List<Student> studentList) {
    List<StudentResponse> studentResponseList = new ArrayList<>();

    studentList.forEach(student -> studentResponseList.add(new StudentResponse(student)));
    return studentResponseList;
  }
}
