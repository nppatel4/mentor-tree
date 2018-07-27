package com.solstice.mentorandtree.mentortree.repository;

import com.solstice.mentorandtree.mentortree.domain.MentorTree;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MentorTreeRepository extends CrudRepository<MentorTree, Long> {

    List<MentorTree> findByMentorId(Long id);
    List<MentorTree> findByTreeLeadId(Long id);

}
