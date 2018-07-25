package com.solstice.mentorandtree.mentortree.model;

import lombok.Data;

@Data
public class Employee {

    private Long id;
    private String firstName;
    private String lastName;
    private Integer employeeNumber;
    private String office;
    private String title;
    private String email;
    private String imageUrl;

}
