package com.springbootrestgraphjpa.service;

import com.springbootrestgraphjpa.entity.Student;
import com.springbootrestgraphjpa.repository.StudentRepository;
import com.springbootrestgraphjpa.request.CreateStudentRequest;
import com.springbootrestgraphjpa.request.UpdateStudentRequest;
import org.springframework.beans.factory.annotation.Autowired;
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

  public String deleteStudent(long id) {
    studentRepository.deleteById(id);
    return "Student has been deleted successfully";
  }
}
