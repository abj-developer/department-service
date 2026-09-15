package com.abj.department_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Department information")
public class DepartmentRequestDTO {

    @Schema(
            description = "Name of the department",
            example = "Computer Science"
    )
    @NotBlank(message = "departmentName must not be blank")
    @Size(min = 2, max = 100, message = "departmentName must be between 2 and 100 characters")
    private String departmentName;

    @Schema(
            description = "Physical address of the department",
            example = "Main Campus, Block A"
    )
    @NotBlank(message = "departmentAddress must not be blank")
    @Size(min = 2, max = 200, message = "departmentAddress must be between 2 and 200 characters")
    private String departmentAddress;

    @Schema(
            description = "Unique code for the department",
            example = "CS"
    )
    @NotBlank(message = "departmentCode must not be blank")
    @Size(min = 2, max = 20, message = "departmentCode must be between 2 and 20 characters")
    private String departmentCode;
}
