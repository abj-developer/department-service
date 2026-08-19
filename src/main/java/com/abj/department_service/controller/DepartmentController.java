package com.abj.department_service.controller;

import com.abj.department_service.entity.Department;
import com.abj.department_service.service.DepartmentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departments")
@Slf4j
@SecurityRequirement(name = "bearerAuth")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/")
    public Department saveDepartment(@RequestBody  Department department){
        log.info("inside saveDepartment method of department controller");
        return  departmentService.saveDepartment(department);

    }

   @GetMapping("/{id}")
    public Department findDepartmentById(@PathVariable("id") Long departmentId){
        log.info("inside findDepartmentById method of department controller");
        return departmentService.getDepartmentById(departmentId);
    }

}
