package com.springbootrestgraphjpa.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.springbootrestgraphjpa.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AddressResponse {
  private Long id;

  private String street;

  private String city;

  @JsonProperty("student_name")
  private String studentName;

  public AddressResponse(final Address address) {
    this.id = address.getId();
    this.street = address.getStreet();
    this.city = address.getCity();
    this.studentName =
        String.format(
            "%s %s", address.getStudent().getFirstName(), address.getStudent().getLastName());
  }
}
