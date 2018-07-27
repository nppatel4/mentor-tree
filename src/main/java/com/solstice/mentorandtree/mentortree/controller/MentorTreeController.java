package com.solstice.mentorandtree.mentortree.controller;

import com.solstice.mentorandtree.mentortree.client.EmployeeClient;
import com.solstice.mentorandtree.mentortree.domain.MentorTree;
import com.solstice.mentorandtree.mentortree.model.Employee;
import com.solstice.mentorandtree.mentortree.service.MentorTreeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Api(tags = "mentorTree")
@RestController
@RequestMapping("/mentor-trees")
public class MentorTreeController {

    @Autowired
    MentorTreeService mentorTreeService;

    @Autowired
    EmployeeClient employeeClient;

    @ApiOperation("create a new mentor tree")
    @ApiResponses({
            @ApiResponse(code = 201, message = "mentor tree created"),
            @ApiResponse(code = 400, message = "bad request")
    })
    @PostMapping
    public HttpEntity<MentorTree> createMentorTree(@RequestBody MentorTree mentorTree) {
        return new ResponseEntity<>(mentorTreeService.save(mentorTree), HttpStatus.CREATED);
    }

    @ApiOperation("get a mentor user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "mentor tree found"),
            @ApiResponse(code = 404, message = "mentor tree not found")
    })
    @GetMapping("/{id}")
    public HttpEntity<MentorTree> getMentorTree(@PathVariable("id") Long id) {
        Optional<MentorTree> mentorTree = mentorTreeService.findByEmployeeId(id);

        if (mentorTree.isPresent()) {
            return new ResponseEntity<>(mentorTree.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @ApiOperation("update mentor tree")
    @ApiResponses({
            @ApiResponse(code = 204, message = "update completed successfully"),
            @ApiResponse(code = 400, message = "bad request")
    })
    @PutMapping
    public HttpEntity updateMentorTree(@RequestBody MentorTree mentorTree) {
        mentorTreeService.save(mentorTree);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "find employees by mentor Id", response = List.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "search completed successfully")
    })
    @GetMapping("/mentor/{id}")
    public HttpEntity<List<Employee>> findEmployeesByMentorId(@PathVariable("id") Long id) {
        List<MentorTree> mentorTrees = mentorTreeService.findByMentorId(id);

        return new ResponseEntity<>(getEmployeesForMentorTrees(mentorTrees), HttpStatus.OK);
    }

    @ApiOperation(value = "find employees by tree lead Id", response = List.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "search completed successfully")
    })
    @GetMapping("/tree-lead/{id}")
    public HttpEntity<List<Employee>> findEmployeesByTreeLeadId(@PathVariable("id") Long id) {
        List<MentorTree> mentorTrees = mentorTreeService.findByTreeLeadId(id);

        return new ResponseEntity<>(getEmployeesForMentorTrees(mentorTrees), HttpStatus.OK);
    }

    @ApiOperation("delete mentor tree")
    @ApiResponses({
            @ApiResponse(code = 204, message = "delete completed successfully"),
            @ApiResponse(code = 404, message = "mentor tree not found")
    })
    @DeleteMapping("/{id}")
    public HttpEntity deleteMentorTree(@PathVariable("id") Long id) {
        mentorTreeService.deleteByEmployeeId(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private List<Employee> getEmployeesForMentorTrees(List<MentorTree> mentorTrees) {
        List<Employee> employees = new ArrayList<>();

        for (MentorTree mentorTree : mentorTrees) {
            employees.add(employeeClient.getEmployeeById(mentorTree.getEmployeeId()));
        }

        return employees;
    }
}

