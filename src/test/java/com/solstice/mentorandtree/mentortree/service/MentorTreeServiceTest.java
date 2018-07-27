package com.solstice.mentorandtree.mentortree.service;

import com.solstice.mentorandtree.mentortree.domain.MentorTree;
import com.solstice.mentorandtree.mentortree.repository.MentorTreeRepository;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MentorTreeServiceTest {
    @Mock
    private MentorTreeRepository mentorTreeRepository;

    @InjectMocks
    private MentorTreeService mentorTreeService;

    @Test
    public void testDeleteMentorTree() throws Exception {
        mentorTreeService.deleteByEmployeeId(1232L);
        verify(mentorTreeRepository).deleteById(1232L);
    }

    @Test
    public void testUpdateMentorTree() {
        MentorTree mentorTree = new MentorTree(1L, 2L, 3L);
        mentorTreeService.save(mentorTree);
        verify(mentorTreeRepository).save(mentorTree);
    }

    @Test
    public void testGetEmployeeByTreeLead() {
        List<MentorTree> mentorTrees = new ArrayList<>();
        mentorTrees.add(new MentorTree(1L, 2L, 3L));
        mentorTrees.add(new MentorTree(3L, 2L, 3L));
        mentorTrees.add(new MentorTree(4L, 1L, 3L));

        when(mentorTreeRepository.findByTreeLeadId(3L)).thenReturn(mentorTrees);

        List<MentorTree> result = mentorTreeService.findByTreeLeadId(3L);

        Assert.assertThat(result.get(0).getEmployeeId(), Matchers.is(1L));
        Assert.assertThat(result.get(1).getEmployeeId(), Matchers.is(3L));
        Assert.assertThat(result.get(2).getEmployeeId(), Matchers.is(4L));
    }

    @Test
    public void testGetMentorByMentorId() {
        List<MentorTree> mentorTrees = new ArrayList<>();
        mentorTrees.add(new MentorTree(1L, 2L, 3L));
        mentorTrees.add(new MentorTree(3L, 2L, 3L));
        mentorTrees.add(new MentorTree(4L, 2L, 3L));

        when(mentorTreeRepository.findByMentorId(2L)).thenReturn(mentorTrees);

        List<MentorTree> result = mentorTreeService.findByMentorId(2L);

        Assert.assertThat(result.get(0).getEmployeeId(), Matchers.is(1L));
        Assert.assertThat(result.get(1).getEmployeeId(), Matchers.is(3L));
        Assert.assertThat(result.get(2).getEmployeeId(), Matchers.is(4L));
    }

}
