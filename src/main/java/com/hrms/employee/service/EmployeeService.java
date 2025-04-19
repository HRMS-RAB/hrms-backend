
package com.hrms.employee.service;

import com.hrms.employee.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {

    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);

    EmployeeDTO updateEmployee(Long employeeId, EmployeeDTO employeeDTO);

    EmployeeDTO getEmployeeById(Long employeeId);

    List<EmployeeDTO> getAllEmployees();

    void deleteEmployee(Long employeeId);
}






/*package com.hrms.employee.service;

import com.hrms.employee.dto.EmployeeDTO;
import com.hrms.employee.entity.Employee;
import com.hrms.employee.repository.EmployeeRepository;
import com.hrms.dept.entity.Department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Convert Entity → DTO
    private EmployeeDTO convertToDTO(Employee emp) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setEmployeeId(emp.getEmployeeId());
        dto.setFirstName(emp.getFirstName());
        dto.setMiddleName(emp.getMiddleName());
        dto.setLastName(emp.getLastName());
        dto.setDateOfBirth(emp.getDateOfBirth());
        dto.setGender(emp.getGender());
        dto.setMaritalStatus(emp.getMaritalStatus());
        dto.setNationality(emp.getNationality());
        dto.setSsn(emp.getSsn());
        dto.setPersonalEmail(emp.getPersonalEmail());
        dto.setPersonalPhone(emp.getPersonalPhone());
        dto.setStreetAddress(emp.getStreetAddress());
        dto.setCity(emp.getCity());
        dto.setStateProvince(emp.getStateProvince());
        dto.setPostalCode(emp.getPostalCode());
        dto.setCountry(emp.getCountry());
        dto.setBloodGroup(emp.getBloodGroup());
        dto.setPhoto(emp.getPhoto());
        dto.setJobTitle(emp.getJobTitle());
        dto.setEmployeeType(emp.getEmployeeType());
        dto.setEmploymentStatus(emp.getEmploymentStatus());
        dto.setHireDate(emp.getHireDate());
        dto.setTerminationDate(emp.getTerminationDate());
        dto.setManagerId(emp.getManagerId());
        dto.setWorkLocation(emp.getWorkLocation());
        dto.setWorkEmail(emp.getWorkEmail());
        dto.setWorkPhone(emp.getWorkPhone());
        dto.setGradeLevel(emp.getGradeLevel());
        dto.setShiftDetails(emp.getShiftDetails());
        dto.setProbationStartDate(emp.getProbationStartDate());
        dto.setProbationEndDate(emp.getProbationEndDate());
        dto.setContractType(emp.getContractType());
        dto.setContractEndDate(emp.getContractEndDate());
        dto.setEmergencyContactName(emp.getEmergencyContactName());
        dto.setEmergencyContactRelation(emp.getEmergencyContactRelation());
        dto.setEmergencyContactPhone(emp.getEmergencyContactPhone());
        dto.setEmergencyContactEmail(emp.getEmergencyContactEmail());
        dto.setEmergencyContactAddress(emp.getEmergencyContactAddress());
        dto.setVisaType(emp.getVisaType());
        dto.setVisaNumber(emp.getVisaNumber());
        dto.setVisaIssueDate(emp.getVisaIssueDate());
        dto.setVisaExpiryDate(emp.getVisaExpiryDate());
        dto.setTaxIdentificationNumber(emp.getTaxIdentificationNumber());
        dto.setBackgroundCheckStatus(emp.getBackgroundCheckStatus());
        dto.setBackgroundCheckDate(emp.getBackgroundCheckDate());
        dto.setDataConsent(emp.getDataConsent());
        dto.setDataConsentDate(emp.getDataConsentDate());
        dto.setCreatedBy(emp.getCreatedBy());
        dto.setCreatedDate(emp.getCreatedDate());
        dto.setLastUpdatedBy(emp.getLastUpdatedBy());
        dto.setLastUpdatedDate(emp.getLastUpdatedDate());

        // Department
        if (emp.getDepartment() != null) {
            dto.setDepartmentId(emp.getDepartment().getDepartmentId());
            dto.setDepartmentName(emp.getDepartment().getDepartmentName());
        }

        return dto;
    }

    // Convert DTO → Entity
    private Employee convertToEntity(EmployeeDTO dto) {
        Employee emp = new Employee();

        emp.setEmployeeId(dto.getEmployeeId());
        emp.setFirstName(dto.getFirstName());
        emp.setMiddleName(dto.getMiddleName());
        emp.setLastName(dto.getLastName());
        emp.setDateOfBirth(dto.getDateOfBirth());
        emp.setGender(dto.getGender());
        emp.setMaritalStatus(dto.getMaritalStatus());
        emp.setNationality(dto.getNationality());
        emp.setSsn(dto.getSsn());
        emp.setPersonalEmail(dto.getPersonalEmail());
        emp.setPersonalPhone(dto.getPersonalPhone());
        emp.setStreetAddress(dto.getStreetAddress());
        emp.setCity(dto.getCity());
        emp.setStateProvince(dto.getStateProvince());
        emp.setPostalCode(dto.getPostalCode());
        emp.setCountry(dto.getCountry());
        emp.setBloodGroup(dto.getBloodGroup());
        emp.setPhoto(dto.getPhoto());
        emp.setJobTitle(dto.getJobTitle());
        emp.setEmployeeType(dto.getEmployeeType());
        emp.setEmploymentStatus(dto.getEmploymentStatus());
        emp.setHireDate(dto.getHireDate());
        emp.setTerminationDate(dto.getTerminationDate());
        emp.setManagerId(dto.getManagerId());
        emp.setWorkLocation(dto.getWorkLocation());
        emp.setWorkEmail(dto.getWorkEmail());
        emp.setWorkPhone(dto.getWorkPhone());
        emp.setGradeLevel(dto.getGradeLevel());
        emp.setShiftDetails(dto.getShiftDetails());
        emp.setProbationStartDate(dto.getProbationStartDate());
        emp.setProbationEndDate(dto.getProbationEndDate());
        emp.setContractType(dto.getContractType());
        emp.setContractEndDate(dto.getContractEndDate());
        emp.setEmergencyContactName(dto.getEmergencyContactName());
        emp.setEmergencyContactRelation(dto.getEmergencyContactRelation());
        emp.setEmergencyContactPhone(dto.getEmergencyContactPhone());
        emp.setEmergencyContactEmail(dto.getEmergencyContactEmail());
        emp.setEmergencyContactAddress(dto.getEmergencyContactAddress());
        emp.setVisaType(dto.getVisaType());
        emp.setVisaNumber(dto.getVisaNumber());
        emp.setVisaIssueDate(dto.getVisaIssueDate());
        emp.setVisaExpiryDate(dto.getVisaExpiryDate());
        emp.setTaxIdentificationNumber(dto.getTaxIdentificationNumber());
        emp.setBackgroundCheckStatus(dto.getBackgroundCheckStatus());
        emp.setBackgroundCheckDate(dto.getBackgroundCheckDate());
        emp.setDataConsent(dto.getDataConsent());
        emp.setDataConsentDate(dto.getDataConsentDate());
        emp.setCreatedBy(dto.getCreatedBy());
        emp.setCreatedDate(dto.getCreatedDate());
        emp.setLastUpdatedBy(dto.getLastUpdatedBy());
        emp.setLastUpdatedDate(dto.getLastUpdatedDate());

        // Create and attach Department (only ID needed to link)
        if (dto.getDepartmentId() != null) {
            Department dept = new Department();
            dept.setDepartmentId(dto.getDepartmentId());
            emp.setDepartment(dept);
        }

        return emp;
    }

    // Create/Update
    public EmployeeDTO saveEmployee(EmployeeDTO dto) {
        Employee emp = convertToEntity(dto);
        Employee saved = employeeRepository.save(emp);
        return convertToDTO(saved);
    }

    // Read All
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Read by ID
    public EmployeeDTO getEmployeeById(Long id) {
        Optional<Employee> empOpt = employeeRepository.findById(id);
        return empOpt.map(this::convertToDTO).orElse(null);
    }

    // Delete
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
*/

//=================================================================