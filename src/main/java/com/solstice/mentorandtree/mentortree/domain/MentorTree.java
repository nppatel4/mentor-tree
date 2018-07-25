package com.solstice.mentorandtree.mentortree.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class MentorTree {

    @Id
    private Long employeeId;
    private Long mentorId;
    private Long treeLeadId;

}
