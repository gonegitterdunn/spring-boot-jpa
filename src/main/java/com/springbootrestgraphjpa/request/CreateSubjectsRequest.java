package com.springbootrestgraphjpa.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateSubjectsRequest {
 @JsonProperty("subject_name")
 private String subjectName;

 @JsonProperty("marks_obtained")
 private Double marksObtained;
}
