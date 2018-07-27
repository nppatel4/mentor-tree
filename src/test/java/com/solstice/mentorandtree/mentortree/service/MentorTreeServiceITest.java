package com.solstice.mentorandtree.mentortree.service;

import com.solstice.mentorandtree.mentortree.domain.MentorTree;
import com.solstice.mentorandtree.mentortree.model.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MentorTreeServiceITest {

    private static final String ROOT_PATH = "/mentor-trees";

    @Autowired
    private TestRestTemplate testRestTemplate;

    /*
     * This test requires the employee-tree service and eureka service to be running.
     */
    @Test
    public void testGetEmployeesByMentorId() {
        ResponseEntity<List<Employee>> employees = testRestTemplate.exchange(
                ROOT_PATH + "/?mentor=2",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Employee>>() {});

        for (Employee employee : employees.getBody()) {
            assertThat(employee.getId(), anyOf(is(1L), is(3L)));
        }
    }

    /*
     * This test requires the employee-tree service and eureka service to be running.
     */
    @Test
    public void testGetEmployeesByTreeLeadId() {
        ResponseEntity<List<Employee>> employees = testRestTemplate.exchange(
                ROOT_PATH + "/?tree-lead=1",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Employee>>() {});

        for (Employee employee : employees.getBody()) {
            assertThat(employee.getId(), anyOf(is(2L), is(3L)));
        }
    }

    @Test
    public void testAddMentorTree() {
        MentorTree mentorTree = new MentorTree(10L, 20L, 30L);

        ResponseEntity postResponse = testRestTemplate.postForEntity(ROOT_PATH, mentorTree, MentorTree.class);

        assertThat(postResponse.getStatusCode(), is(HttpStatus.CREATED));

        ResponseEntity<MentorTree> result = testRestTemplate.getForEntity(ROOT_PATH + "/10", MentorTree.class);

        assertThat(result.getBody(), notNullValue());
        assertThat(result.getBody().getEmployeeId(), is(10L));
        assertThat(result.getBody().getMentorId(), is (20L));
        assertThat(result.getBody().getTreeLeadId(), is(30L));
    }

    @Test
    public void testUpdateMentorTree() {
        // create mentor tree
        MentorTree mentorTree = new MentorTree(10L, 20L, 30L);
        ResponseEntity postResponse = testRestTemplate.postForEntity(ROOT_PATH, mentorTree, MentorTree.class);
        assertThat(postResponse.getStatusCode(), is(HttpStatus.CREATED));

        // update mentor tree
        mentorTree.setMentorId(40L);
        mentorTree.setTreeLeadId(50L);
        testRestTemplate.put(ROOT_PATH, mentorTree);

        // verify
        ResponseEntity<MentorTree> result = testRestTemplate.getForEntity(ROOT_PATH + "/10", MentorTree.class);
        assertThat(result.getStatusCode(), is(HttpStatus.OK));
        assertThat(result.getBody().getMentorId(), is(40L));
        assertThat(result.getBody().getTreeLeadId(), is(50L));
    }

    @Test
    public void testDeleteMentorTree() {
        // create mentor tree
        MentorTree mentorTree = new MentorTree(10L, 20L, 30L);
        ResponseEntity postResponse = testRestTemplate.postForEntity(ROOT_PATH, mentorTree, MentorTree.class);
        assertThat(postResponse.getStatusCode(), is(HttpStatus.CREATED));

        // delete mentor tree
        testRestTemplate.delete(ROOT_PATH + "/10");

        // verify mentor tree was deleted
        ResponseEntity<MentorTree> result = testRestTemplate.getForEntity(ROOT_PATH + "/10", MentorTree.class);
        assertThat(result.getStatusCode(), is(HttpStatus.NOT_FOUND));
    }
}
