package com.solstice.mentorandtree.mentortree.service;

import com.solstice.mentorandtree.mentortree.Employee;
import com.solstice.mentorandtree.mentortree.client.EmployeeClient;
import com.solstice.mentorandtree.mentortree.domain.MentorTree;
import com.solstice.mentorandtree.mentortree.repository.MentorTreeRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@Api(tags = "mentorTree")
@RestController
@RequestMapping("/mentor-trees")
public class MentorTreeService {
    @Autowired
    private MentorTreeRepository mentorTreeRepository;
    @Autowired
    private EmployeeClient employeeClient;

    /**
     * gets mentee by mentor Id
     * @param id
     * @return the mentor tree for mentorID
     */
    @ApiOperation(value= "find employees by mentor Id", response = List.class)
    @ApiResponses( value = {
            @ApiResponse(code = 200, message="search completed successfully")
    })
    @GetMapping("/mentor-id/{id}")
    List<Employee> getEmployeesByMentorId(@PathVariable("id") Long id){

        List<MentorTree> mentorTrees =  mentorTreeRepository.findByMentorId(id);
        List<Employee> employees = new ArrayList<>();
        for (MentorTree mentorTree: mentorTrees){
            employees.add(employeeClient.getEmployeeById(mentorTree.getEmployeeId()));
        }
        return employees;
    }
    @ApiOperation(value= "find employees by tree lead Id", response = List.class)
    @ApiResponses( value = {
            @ApiResponse(code = 200, message="search completed successfully")
    })
    @GetMapping("/tree-lead-id/{id}")
    List<Employee> getEmployeeByTreeLead(@PathVariable("id") Long id) {

        List<MentorTree> mentorTrees = mentorTreeRepository.findByTreeLeadId(id);
        List<Employee> employees = new ArrayList<>();
        for (MentorTree mentorTree: mentorTrees){
            employees.add(employeeClient.getEmployeeById(mentorTree.getEmployeeId()));
        }
        return employees;
    }
    @ApiOperation(value= "update mentor tree", response = List.class)
    @ApiResponses( value = {
            @ApiResponse(code = 200, message="update completed successfully")
    })
    @PutMapping
    void updateMentorTree(@RequestBody MentorTree mentorTree){
        mentorTreeRepository.setMentorTreeById(
                mentorTree.getMentorId(), mentorTree.getTreeLeadId(), mentorTree.getEmployeeId());
    }
    @ApiOperation(value= "delete mentor tree", response = List.class)
    @ApiResponses( value = {
            @ApiResponse(code = 200, message="delete completed successfully")
    })
    @DeleteMapping("/{id}")
    void deleteMentorTree(@PathVariable("id") Long id){
        mentorTreeRepository.deleteByEmployeeId(id);
    }
}
