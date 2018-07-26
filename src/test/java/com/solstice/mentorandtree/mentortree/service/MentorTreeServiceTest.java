package com.solstice.mentorandtree.mentortree.service;

import com.solstice.mentorandtree.mentortree.client.EmployeeClient;
import com.solstice.mentorandtree.mentortree.domain.MentorTree;
import com.solstice.mentorandtree.mentortree.model.Employee;
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
    MentorTreeRepository mentorTreeRepository;
    @InjectMocks
    MentorTreeService mentorTreeService;
    @Mock
    EmployeeClient employeeClient;


    @Test
    public void testDeleteMentorTree() {
        mentorTreeService.deleteMentorTree(1232L);
        verify(mentorTreeRepository).deleteByEmployeeId(1232L);
    }

    @Test
    public void testUpdateMentorTree() {
        MentorTree mentorTree = new MentorTree(1L, 2L, 3L);
        mentorTreeService.updateMentorTree(mentorTree);
        verify(mentorTreeRepository).setMentorTreeById(2L, 3L, 1L);
    }

    @Test
    public void testGetEmployeeByTreeLead() {
        List<MentorTree> mentorTrees = new ArrayList<>();
        mentorTrees.add(new MentorTree(1L, 2L, 3L));
        mentorTrees.add(new MentorTree(3L, 2L, 3L));
        mentorTrees.add(new MentorTree(4L, 1L, 3L));
        Employee employee1 = new Employee();
        Employee employee3 = new Employee();
        Employee employee4 = new Employee();
        employee1.setEmployeeId(1L);
        employee3.setEmployeeId(3L);
        employee4.setEmployeeId(4L);


        when(mentorTreeRepository.findByTreeLeadId(3L)).thenReturn(mentorTrees);
        when(employeeClient.getEmployeeById(1L)).thenReturn(employee1);
        when(employeeClient.getEmployeeById(3L)).thenReturn(employee3);
        when(employeeClient.getEmployeeById(4L)).thenReturn(employee4);

        List<Employee> employees = mentorTreeService.getEmployeeByTreeLead(3L);

        Assert.assertThat(employees.get(0).getId(), Matchers.is(1L));
        Assert.assertThat(employees.get(1).getId(), Matchers.is(3L));
        Assert.assertThat(employees.get(2).getId(), Matchers.is(4L));

    }

    @Test
    public void testGetMentorByMentorId() {
        List<MentorTree> mentorTrees = new ArrayList<>();
        mentorTrees.add(new MentorTree(1L, 2L, 3L));
        mentorTrees.add(new MentorTree(3L, 2L, 3L));
        mentorTrees.add(new MentorTree(4L, 2L, 3L));
        Employee employee1 = new Employee();
        Employee employee3 = new Employee();
        Employee employee4 = new Employee();
        employee1.setEmployeeId(1L);
        employee3.setEmployeeId(3L);
        employee4.setEmployeeId(4L);


        when(mentorTreeRepository.findByMentorId(2L)).thenReturn(mentorTrees);
        when(employeeClient.getEmployeeById(1L)).thenReturn(employee1);
        when(employeeClient.getEmployeeById(3L)).thenReturn(employee3);
        when(employeeClient.getEmployeeById(4L)).thenReturn(employee4);

        List<Employee> employees = mentorTreeService.getEmployeesByMentorId(2L);

        Assert.assertThat(employees.get(0).getId(), Matchers.is(1L));
        Assert.assertThat(employees.get(1).getId(), Matchers.is(3L));
        Assert.assertThat(employees.get(2).getId(), Matchers.is(4L));
    }

}
