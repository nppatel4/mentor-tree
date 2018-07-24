package com.solstice.mentorandtree.mentortree;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
