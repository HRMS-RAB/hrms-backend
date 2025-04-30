
package com.hrms.employee.service;

import com.hrms.employee.dto.EmployeeDTO;
import java.util.List;

public interface EmployeeService {

    /* ─── C R U D ───────────────────────────────────────── */

    //** create ONE employee 
    EmployeeDTO createEmployee(EmployeeDTO dto);

    //** create MANY employees in bulk (transactional) 
    List<EmployeeDTO> createEmployees(List<EmployeeDTO> dtos);

    //** read ONE by id 
    EmployeeDTO getEmployeeById(Long id);

    //** read ALL (simple list) */
    List<EmployeeDTO> getAllEmployees();

    //** update selected fields */
    EmployeeDTO updateEmployee(Long id, EmployeeDTO dto);

    //** delete hard */
    void deleteEmployee(Long id);
}



/*package com.hrms.employee.service;

import com.hrms.employee.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {

  EmployeeDTO createEmployee(EmployeeDTO employeeDTO);

    EmployeeDTO updateEmployee(Long employeeId, EmployeeDTO employeeDTO);

    EmployeeDTO getEmployeeById(Long employeeId);

    List<EmployeeDTO> getAllEmployees();
    
List<EmployeeDTO> createEmployees(List<EmployeeDTO> employeeDTOs);

    
   // EmployeeDTO createEmployee(EmployeeDTO dto);
    //List<EmployeeDTO> createEmployees(List<EmployeeDTO> dtos);

    
    void deleteEmployee(Long employeeId);
}
*/

