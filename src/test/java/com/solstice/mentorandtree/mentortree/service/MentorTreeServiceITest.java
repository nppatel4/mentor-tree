package com.solstice.mentorandtree.mentortree.service;

import com.solstice.mentorandtree.mentortree.model.Employee;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MentorTreeServiceITest {
    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void testGetEmployeesByMentorId(){
        ResponseEntity<List<Employee>> employees = testRestTemplate.exchange("/mentor-trees/mentor-id/2", HttpMethod.GET, null, new ParameterizedTypeReference<List<Employee>>(){});


        for (Employee employee:  employees.getBody()){
            Assert.assertThat(employee.getId(), Matchers.anyOf(Matchers.is(1L), Matchers.is(3L)));
        }

    }

}
