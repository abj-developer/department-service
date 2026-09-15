package com.abj.department_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentUpdateRequestDTO {

    @Schema(
            description = "Updated name of the department",
            example = "Computer Science"
    )
    @Size(min = 2, max = 100, message = "departmentName must be between 2 and 100 characters")
    private String departmentName;

    @Schema(
            description = "Updated address of the department",
            example = "Main Campus, Block B"
    )
    @Size(min = 2, max = 200, message = "departmentAddress must be between 2 and 200 characters")
    private String departmentAddress;

    @Schema(
            description = "Updated code for the department",
            example = "CS"
    )
    @Size(min = 2, max = 20, message = "departmentCode must be between 2 and 20 characters")
    private String departmentCode;
}
