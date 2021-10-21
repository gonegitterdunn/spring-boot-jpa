package com.springbootrestgraphjpa.service;

import com.springbootrestgraphjpa.entity.Student;
import com.springbootrestgraphjpa.repository.StudentRepository;
import com.springbootrestgraphjpa.request.CreateStudentRequest;
import com.springbootrestgraphjpa.request.InQueryRequest;
import com.springbootrestgraphjpa.request.UpdateStudentRequest;
import com.springbootrestgraphjpa.response.StudentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

  @Autowired StudentRepository studentRepository;

  public List<Student> getAllStudents() {
    return studentRepository.findAll();
  }

  public Student createStudent(final CreateStudentRequest createStudentRequest) {
    Student student = new Student(createStudentRequest);

    return studentRepository.save(student);
  }

  public Student updateStudent(final UpdateStudentRequest updateStudentRequest) {
    Student student =
        studentRepository.findById(updateStudentRequest.getId()).orElse(new Student());

    if (!Objects.isNull(updateStudentRequest.getFirstName())
        && !ObjectUtils.isEmpty(updateStudentRequest.getFirstName())) {
      student.setFirstName(updateStudentRequest.getFirstName());
    }

    if (!Objects.isNull(updateStudentRequest.getLastName())
            && !ObjectUtils.isEmpty(updateStudentRequest.getLastName())) {
      student.setLastName(updateStudentRequest.getLastName());
    }

    if (!Objects.isNull(updateStudentRequest.getEmail())
            && !ObjectUtils.isEmpty(updateStudentRequest.getEmail())) {
      student.setEmail(updateStudentRequest.getEmail());
    }

    return studentRepository.save(student);
  }

  public String deleteStudent(final long id) {
    studentRepository.deleteById(id);
    return "Student has been deleted successfully";
  }

  public List<Student> getByFirstName(final String firstName){
    return studentRepository.findByFirstName(firstName);
  }

  public Student getByFirstNameAndLastName(final String firstName, final String lastName) {
    return studentRepository.getByLastNameAndFirstName(firstName, lastName);
  }

  public List<Student> getByFirstNameOrLastName(final String firstName, final String lastName) {
    return studentRepository.findByFirstNameOrLastName(firstName, lastName);
  }

  public List<Student> getByFirstNameIn(final InQueryRequest inQueryRequest) {
    return studentRepository.findByFirstNameIn(inQueryRequest.getFirstNames());
  }

  // select * from student limit <pageSize> offset <pageNo * pageSize>
  public List<Student> getAllStudentsWithPagination(final int pageNo, final int pageSize) {
    Pageable pageable = PageRequest.of(pageNo - 1, pageSize);

    return studentRepository.findAll(pageable).getContent();
  }

  // select * from student order by first_name, last_name asc
  public List<Student> getAllStudentsWithSorting() {
    Sort sort = Sort.by(Sort.Direction.ASC, "firstName", "lastName");

    return studentRepository.findAll(sort);
  }

  public List<Student> like(final String firstName) {
    return studentRepository.findByFirstNameContains(firstName);
  }

  public List<Student> startsWith(final String firstName) {
    return studentRepository.findByFirstNameStartsWith(firstName);
  }

  public List<Student> endsWith(final String firstName) {
    return studentRepository.findByFirstNameEndsWith(firstName);
  }

  public Integer updateStudentWithJpql(final long id, final String firstName) {
    return studentRepository.updateFirstName(id, firstName);
  }

  public Integer deleteByFirstName(final String firstname) {
    return studentRepository.deleteByFirstName(firstname);
  }
}
