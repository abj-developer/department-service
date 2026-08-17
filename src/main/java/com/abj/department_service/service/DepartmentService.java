package com.abj.department_service.service;


import com.abj.department_service.entity.Department;
import com.abj.department_service.repository.DepartmentRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Tag(
        name = "Department APIs",
        description = "APIs related to department management"
)
@Service
@Slf4j
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;



    @Operation(
            summary = "save department",
            description = "saves a new department"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Department saved successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid department data"
            )
    })
    public Department saveDepartment(Department department) {
        log.info("inside saveDepartment method of department service");
        return departmentRepository.save(department);
    }


    @Operation(
            summary = "Get department by ID",
            description = "Fetches department details using department ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Department found successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Department not found"
            )
    })
    public Department getDepartmentById(Long departmentId) {
        log.info("inside getDepartmentById method of department service");
        return departmentRepository.findByDepartmentId(departmentId);
    }
}
