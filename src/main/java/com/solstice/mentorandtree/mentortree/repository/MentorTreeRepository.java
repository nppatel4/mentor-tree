package com.solstice.mentorandtree.mentortree.repository;

import com.solstice.mentorandtree.mentortree.domain.MentorTree;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MentorTreeRepository extends Repository<MentorTree, Long> {

    List<MentorTree> findByMentorId(Long id);

    List<MentorTree> findByTreeLeadId(Long id);

    @Transactional
    @Modifying
    @Query("update MentorTree mt set mt.mentorId = ?1, mt.treeLeadId = ?2 where mt.employeeId = ?3")
    void setMentorTreeById(Long mentorId, Long treeLeadId, Long employeeId);

    @Transactional
    void deleteByEmployeeId(Long id);

}
