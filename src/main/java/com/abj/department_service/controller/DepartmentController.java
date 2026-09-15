package com.abj.department_service.controller;

import com.abj.department_service.dto.DepartmentRequestDTO;
import com.abj.department_service.dto.DepartmentResponseDTO;
import com.abj.department_service.dto.DepartmentUpdateRequestDTO;
import com.abj.department_service.dto.ResponseDTO;
import com.abj.department_service.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
       name = "Department APIs",
       description = "APIs related to department management"
)
@RestController
@Slf4j
@SecurityRequirement(name = "bearerAuth")
public class DepartmentController {

   private final DepartmentService departmentService;

   public DepartmentController(DepartmentService departmentService) {
       this.departmentService = departmentService;
   }

   @Operation(
           summary = "Get departments",
           description = "Fetches all departments"
   )
   @ApiResponses({
           @ApiResponse(responseCode = "200", description = "Departments fetched successfully")
   })
   @GetMapping("/departments")
   public ResponseEntity<ResponseDTO<List<DepartmentResponseDTO>>> getDepartments() {
       log.info("inside getDepartments method of DepartmentController");
       return ResponseEntity.ok(ResponseDTO.success(departmentService.getAllDepartments()));
   }

   @Operation(
           summary = "Save department",
           description = "Creates a new department"
   )
   @ApiResponses({
           @ApiResponse(responseCode = "201", description = "Department saved successfully"),
           @ApiResponse(responseCode = "400", description = "Invalid department data")
   })
   @PostMapping("/departments")
   public ResponseEntity<ResponseDTO<DepartmentResponseDTO>> saveDepartment(@Valid @RequestBody DepartmentRequestDTO request) {
       log.info("inside saveDepartment method of DepartmentController");
       DepartmentResponseDTO savedDepartment = departmentService.saveDepartment(request);
       return ResponseEntity.status(HttpStatus.CREATED).body(ResponseDTO.success(savedDepartment));
   }

   @Operation(
           summary = "Get department by ID",
           description = "Fetches department details using department ID"
   )
   @ApiResponses({
           @ApiResponse(responseCode = "200", description = "Department found successfully"),
           @ApiResponse(responseCode = "404", description = "Department not found")
   })
   @GetMapping("/departments/{id}")
   public ResponseEntity<ResponseDTO<DepartmentResponseDTO>> getDepartmentById(@PathVariable("id") Long departmentId) {
       log.info("inside getDepartmentById method of DepartmentController");
       return ResponseEntity.ok(ResponseDTO.success(departmentService.getDepartmentById(departmentId)));
   }

   @Operation(
           summary = "Update department",
           description = "Updates an existing department"
   )
   @ApiResponses({
           @ApiResponse(responseCode = "200", description = "Department updated successfully"),
           @ApiResponse(responseCode = "404", description = "Department not found")
   })
   @PutMapping("/departments/{id}")
   public ResponseEntity<ResponseDTO<DepartmentResponseDTO>> updateDepartment(@PathVariable("id") Long departmentId,
                                                                             @Valid @RequestBody DepartmentUpdateRequestDTO request) {
       log.info("inside updateDepartment method of DepartmentController for departmentId={}", departmentId);
       return ResponseEntity.ok(ResponseDTO.success(departmentService.updateDepartment(departmentId, request)));
   }

   @Operation(
           summary = "Delete department",
           description = "Deletes a department by ID"
   )
   @ApiResponses({
           @ApiResponse(responseCode = "204", description = "Department deleted successfully"),
           @ApiResponse(responseCode = "404", description = "Department not found")
   })
   @DeleteMapping("/departments/{id}")
   public ResponseEntity<ResponseDTO<Void>> deleteDepartment(@PathVariable("id") Long departmentId) {
       log.info("inside deleteDepartment method of DepartmentController for departmentId={}", departmentId);
       departmentService.deleteDepartment(departmentId);
       return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ResponseDTO.success(null));
   }
}
