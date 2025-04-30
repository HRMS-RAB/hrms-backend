package com.hrms.employee.mapper;

import com.hrms.dept.entity.Department;
import com.hrms.dept.repository.DepartmentRepository;
import com.hrms.employee.dto.EmployeeDTO;
import com.hrms.employee.entity.Employee;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", uses = EmployeeMapper.Helper.class)
public interface EmployeeMapper {

  //  @Mapping(source = "departmentId", target = "department", qualifiedByName = "idToDepartment")
	@Mappings({
		  @Mapping(source = "personalEmail",    target = "personalEmail"),
		  @Mapping(source = "workEmail",        target = "workEmail"),
		  @Mapping(source = "employeeType",     target = "employeeType"),
		  @Mapping(source = "employmentStatus", target = "employmentStatus"),
		  @Mapping(source = "contractType",     target = "contractType"),
		  @Mapping(source = "departmentId",     target = "department", qualifiedByName = "idToDepartment")
		})
    
    
    Employee toEntity(EmployeeDTO dto);

    @Mapping(source = "department.departmentId",   target = "departmentId")
    @Mapping(source = "department.departmentName", target = "departmentName")
    EmployeeDTO toDTO(Employee entity);

    @Component
    class Helper {
        @Autowired
        private DepartmentRepository repo;

        @Named("idToDepartment")
        public Department idToDepartment(Long id) {
            return (id == null ? null : repo.getReferenceById(id));
        }
    }
}

//++++++++++++++++++++++
/*package com.hrms.employee.mapper;

import com.hrms.employee.dto.EmployeeDTO;
import com.hrms.employee.entity.Employee;
import com.hrms.dept.entity.Department;
import com.hrms.dept.repository.DepartmentRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;   // ← add

@Mapper(componentModel = "spring", uses = EmployeeMapper.Helper.class)
public interface EmployeeMapper {

    @Mapping(target = "department",
             source = "departmentId",
             qualifiedByName = "idToDepartment")
    Employee toEntity(EmployeeDTO dto);

    @Mapping(target = "departmentId",   source = "department.departmentId")
    @Mapping(target = "departmentName", source = "department.departmentName")
    EmployeeDTO toDTO(Employee entity);

    //* Helper must be a Spring bean *
    @Component                     // ← add this annotation
    class Helper {

        @Autowired
        private DepartmentRepository repo;

        @Named("idToDepartment")
        public Department idToDepartment(Long id) {
            return id == null ? null : repo.getReferenceById(id);
        }
    }
}
*/