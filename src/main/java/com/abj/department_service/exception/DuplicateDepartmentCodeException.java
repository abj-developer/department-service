package com.abj.department_service.exception;

public class DuplicateDepartmentCodeException extends RuntimeException {
    public DuplicateDepartmentCodeException(String departmentCode) {
        super("Department already exists with code: " + departmentCode);
    }
}
