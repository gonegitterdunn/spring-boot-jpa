package com.springbootrestgraphjpa.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.springbootrestgraphjpa.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StudentResponse {

 private long id;

 @JsonProperty("first_name")
 private String firstName;

 @JsonProperty("last_name")
 private String lastName;

 private String email;

 public StudentResponse(final Student student) {
  this.id = student.getId();
  this.firstName = student.getFirstName();
  this.lastName = student.getLastName();
  this.email = student.getEmail();
 }

}
