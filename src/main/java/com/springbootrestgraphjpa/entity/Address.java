package com.springbootrestgraphjpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    // mappedBy (used on 'owned' side of relationship)
    // field name from the owning class
    @OneToOne(mappedBy = "address")
    private Student student;

}
