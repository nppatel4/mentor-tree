package com.solstice.mentorandtree.mentortree.service;

import com.solstice.mentorandtree.mentortree.domain.MentorTree;
import com.solstice.mentorandtree.mentortree.repository.MentorTreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MentorTreeService {

    @Autowired
    private MentorTreeRepository mentorTreeRepository;


    /**
     * Creates or updates an existing {@link MentorTree}.
     *
     * @param mentorTree
     * @return persisted {@link MentorTree}
     */
    public MentorTree save(MentorTree mentorTree) {
        return mentorTreeRepository.save(mentorTree);
    }

    /**
     * Finds a {@link MentorTree} by employee id.
     *
     * @param employeeId
     * @return the {@link MentorTree}
     */
    public Optional<MentorTree> findByEmployeeId(Long employeeId) {
        return mentorTreeRepository.findById(employeeId);
    }

    /**
     * Finds {@link MentorTree}s by mentor id.
     * @param mentorId
     * @return {@link List} of {@link MentorTree}s
     */
    public List<MentorTree> findByMentorId(Long mentorId) {
        return mentorTreeRepository.findByMentorId(mentorId);
    }

    /**
     * Finds {@link MentorTree}s by tree lead id.
     *
     * @param treeLeadId
     * @return {@link List} of {@link MentorTree}s
     */
    public List<MentorTree> findByTreeLeadId(Long treeLeadId) {
        return mentorTreeRepository.findByTreeLeadId(treeLeadId);
    }

    /**
     * Delete a {@link MentorTree} by employee id.
     * @param id
     */
    public void deleteByEmployeeId(Long id) {
        mentorTreeRepository.deleteById(id);
    }

}
