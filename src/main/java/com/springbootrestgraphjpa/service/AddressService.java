package com.springbootrestgraphjpa.service;

import com.springbootrestgraphjpa.entity.Address;
import com.springbootrestgraphjpa.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

 @Autowired
 AddressRepository addressRepository;

 public Address getByAddress(final Long id) {
  return addressRepository.getById(id);
 }
}
