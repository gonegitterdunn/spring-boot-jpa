package com.springbootrestgraphjpa.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
public class CreateStudentRequest {

 @NotBlank(message = "First name is required")
 private String firstName;

 private String lastName;

 private String email;

 private String street;

 private String city;

 private List<CreateSubjectsRequest> subjects;
}
