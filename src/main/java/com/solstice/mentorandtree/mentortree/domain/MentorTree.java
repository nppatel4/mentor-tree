package com.solstice.mentorandtree.mentortree.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class MentorTree {

    @Id
    @NotNull
    private Long employeeId;

    @NotNull
    private Long mentorId;

    @NotNull
    private Long treeLeadId;

    public MentorTree(Long employeeId, Long mentorId, Long treeLeadId) {
        this.employeeId = employeeId;
        this.mentorId = mentorId;
        this.treeLeadId = treeLeadId;
    }

}
