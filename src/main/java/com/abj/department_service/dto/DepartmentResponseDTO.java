package com.abj.department_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Department response payload")
public class DepartmentResponseDTO {
    @Schema(description = "Unique department ID", example = "101")
    private Long departmentId;

    @Schema(description = "Department name", example = "Computer Science")
    private String departmentName;

    @Schema(description = "Department address", example = "Main Campus, Block A")
    private String departmentAddress;

    @Schema(description = "Department code", example = "CS")
    private String departmentCode;
}
