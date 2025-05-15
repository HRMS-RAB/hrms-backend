package com.hrms.employee.mapper;

import com.hrms.dept.entity.Department;
import com.hrms.dept.repository.DepartmentRepository;
import com.hrms.employee.dto.EmployeeDTO;
import com.hrms.employee.entity.Employee;
import jakarta.persistence.EntityNotFoundException;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * MapStruct mapper between EmployeeDTO and Employee.
 * Includes a partialUpdate method so the service can patch only changed fields.
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class EmployeeMapper {

    /*──────────── DTO ➜ Entity (create) ────────────*/
    @Mapping(target = "department",
             source = "departmentId",
             qualifiedByName = "idToDepartment")
    public abstract Employee toEntity(EmployeeDTO dto);

    /*──────────── Entity ➜ DTO (response) ───────────*/
    @Mapping(target = "departmentId",   source = "department.departmentId")
    @Mapping(target = "departmentName", source = "department.departmentName")
    public abstract EmployeeDTO toDTO(Employee entity);

    /*──────────── Patch existing entity ────────────*/
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void partialUpdate(@MappingTarget Employee entity, EmployeeDTO dto);

    /*──────────── Helpers ────────────*/

    @Autowired
    private DepartmentRepository deptRepo;

    @Named("idToDepartment")
    protected Department mapDept(Long id) {
        return id == null ? null : deptRepo.getReferenceById(id);
    }

    /** After MapStruct copies scalar fields, attach Department if provided. */
    @AfterMapping
    protected void handleDepartment(EmployeeDTO dto, @MappingTarget Employee entity) {
        if (dto.getDepartmentId() != null) {
            entity.setDepartment(
                deptRepo.findById(dto.getDepartmentId())
                        .orElseThrow(() -> new EntityNotFoundException(
                                "Department not found: " + dto.getDepartmentId()))
            );
        }
    }
}
