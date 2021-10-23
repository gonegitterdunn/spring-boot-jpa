package com.springbootrestgraphjpa.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.springbootrestgraphjpa.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class StudentResponse {

  private long id;

  @JsonProperty("first_name")
  private String firstName;

  @JsonProperty("last_name")
  private String lastName;

  private String email;

  @JsonProperty("full_name")
  private String fullName;

  private String street;

  private String city;

  private List<SubjectResponse> subjects;

  public StudentResponse(final Student student) {
    this.id = student.getId();
    this.firstName = student.getFirstName();
    this.lastName = student.getLastName();
    this.email = student.getEmail();
    this.fullName = String.format("%s %s", student.getFirstName(), student.getLastName());

    this.street = student.getAddress().getStreet();
    this.city = student.getAddress().getCity();

    if(Objects.nonNull(student.getSubjects())) {
      subjects = new ArrayList<>();
      student.getSubjects().forEach(subject -> subjects.add(new SubjectResponse(subject)));
    }
  }
}
