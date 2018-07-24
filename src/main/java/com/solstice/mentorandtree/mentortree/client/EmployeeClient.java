package com.solstice.mentorandtree.mentortree.client;

import com.solstice.mentorandtree.mentortree.Employee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Component
@FeignClient(name = "employees", url = "http://localHost:8080")
public interface EmployeeClient {
    @RequestMapping(method = RequestMethod.GET, value = "/employees/{id}")
    Employee  getEmployeeById(@PathVariable("id") Long id);
}
