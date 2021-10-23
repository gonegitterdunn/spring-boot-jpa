package com.springbootrestgraphjpa.controller;

import com.springbootrestgraphjpa.response.AddressResponse;
import com.springbootrestgraphjpa.service.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/address/")
public class AddressController {

 Logger logger = LoggerFactory.getLogger(AddressController.class);
 @Autowired
 AddressService addressService;

 @GetMapping(value = "getByAddress/{id}")
 public AddressResponse getByAddress(@PathVariable final Long id) {
  logger.debug("Inside debug");
  return new AddressResponse(addressService.getByAddress(id));
 }
}
