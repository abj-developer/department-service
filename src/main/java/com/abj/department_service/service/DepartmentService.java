package com.abj.department_service.service;

import com.abj.department_service.dto.DepartmentRequestDTO;
import com.abj.department_service.dto.DepartmentResponseDTO;
import com.abj.department_service.dto.DepartmentUpdateRequestDTO;
import com.abj.department_service.entity.Department;
import com.abj.department_service.exception.DepartmentNotFoundException;
import com.abj.department_service.exception.DuplicateDepartmentCodeException;
import com.abj.department_service.repository.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DepartmentService {

   private final DepartmentRepository departmentRepository;

   public DepartmentService(DepartmentRepository departmentRepository) {
       this.departmentRepository = departmentRepository;
   }

   public DepartmentResponseDTO saveDepartment(DepartmentRequestDTO request) {
       log.info("inside saveDepartment method of DepartmentService");

       String departmentCode = request.getDepartmentCode() == null ? null : request.getDepartmentCode().trim();
       if (departmentCode != null && departmentRepository.existsByDepartmentCode(departmentCode)) {
           throw new DuplicateDepartmentCodeException(departmentCode);
       }

       Department department = new Department();
       department.setDepartmentName(request.getDepartmentName().trim());
       department.setDepartmentAddress(request.getDepartmentAddress().trim());
       department.setDepartmentCode(departmentCode);

       Department savedDepartment = departmentRepository.save(department);
       return mapToDepartmentResponse(savedDepartment);
   }

   public List<DepartmentResponseDTO> getAllDepartments() {
       log.info("inside getAllDepartments method of DepartmentService");
       return departmentRepository.findAll().stream()
               .map(this::mapToDepartmentResponse)
               .toList();
   }

   public DepartmentResponseDTO getDepartmentById(Long departmentId) {
       log.info("inside getDepartmentById method of DepartmentService for departmentId={}", departmentId);
       Department department = departmentRepository.findByDepartmentId(departmentId)
               .orElseThrow(() -> new DepartmentNotFoundException(departmentId));
       return mapToDepartmentResponse(department);
   }

   public DepartmentResponseDTO updateDepartment(Long departmentId, DepartmentUpdateRequestDTO request) {
       log.info("inside updateDepartment method of DepartmentService for departmentId={}", departmentId);
       Department department = departmentRepository.findByDepartmentId(departmentId)
               .orElseThrow(() -> new DepartmentNotFoundException(departmentId));

       if (request.getDepartmentName() != null && !request.getDepartmentName().isBlank()) {
           department.setDepartmentName(request.getDepartmentName().trim());
       }

       if (request.getDepartmentAddress() != null && !request.getDepartmentAddress().isBlank()) {
           department.setDepartmentAddress(request.getDepartmentAddress().trim());
       }

       if (request.getDepartmentCode() != null && !request.getDepartmentCode().isBlank()) {
           String departmentCode = request.getDepartmentCode().trim();
           if (!departmentCode.equalsIgnoreCase(department.getDepartmentCode())
                   && departmentRepository.existsByDepartmentCode(departmentCode)) {
               throw new DuplicateDepartmentCodeException(departmentCode);
           }
           department.setDepartmentCode(departmentCode);
       }

       Department updatedDepartment = departmentRepository.save(department);
       return mapToDepartmentResponse(updatedDepartment);
   }

   public void deleteDepartment(Long departmentId) {
       log.info("inside deleteDepartment method of DepartmentService for departmentId={}", departmentId);
       if (!departmentRepository.existsById(departmentId)) {
           throw new DepartmentNotFoundException(departmentId);
       }
       departmentRepository.deleteById(departmentId);
   }

   private DepartmentResponseDTO mapToDepartmentResponse(Department department) {
       return new DepartmentResponseDTO(
               department.getDepartmentId(),
               department.getDepartmentName(),
               department.getDepartmentAddress(),
               department.getDepartmentCode()
       );
   }
}
