package com.solstice.mentorandtree.mentortree.client;

import com.solstice.mentorandtree.mentortree.model.Employee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@FeignClient(name = "${feign.employee-client.name}", url = "${feign.employee-client.url}")
public interface EmployeeClient {

    @RequestMapping(method = RequestMethod.GET, value = "/employees/{id}")
    Employee getEmployeeById(@PathVariable("id") Long id);

}
