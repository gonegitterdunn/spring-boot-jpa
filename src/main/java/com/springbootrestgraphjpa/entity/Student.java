package com.springbootrestgraphjpa.entity;

import com.springbootrestgraphjpa.request.CreateStudentRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "student")
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "email")
  private String email;

  @Transient // Is not a column in the 'Student' table in the db
  private String fullName;

  public Student(CreateStudentRequest createStudentRequest) {
    this.firstName = createStudentRequest.getFirstName();
    this.lastName = createStudentRequest.getLastName();
    this.email = createStudentRequest.getEmail();
    this.fullName =
        String.format(
            "%s %s", createStudentRequest.getFirstName(), createStudentRequest.getLastName());
  }
}
