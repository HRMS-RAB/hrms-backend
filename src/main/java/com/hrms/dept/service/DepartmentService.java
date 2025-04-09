package com.hrms.dept.service;

import com.hrms.dept.dto.DepartmentDTO;
import java.util.List;

public interface DepartmentService {

    DepartmentDTO createDepartment(DepartmentDTO departmentDTO);

    DepartmentDTO updateDepartment(Long departmentId, DepartmentDTO departmentDTO);

    DepartmentDTO getDepartmentById(Long departmentId);

    List<DepartmentDTO> getAllDepartments();

    void deleteDepartment(Long departmentId);
}
