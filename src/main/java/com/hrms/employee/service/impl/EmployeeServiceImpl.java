package com.hrms.employee.service.impl;

import com.hrms.dept.entity.Department;
import com.hrms.dept.repository.DepartmentRepository;
import com.hrms.employee.dto.EmployeeDTO;
import com.hrms.employee.entity.Employee;
import com.hrms.employee.repository.EmployeeRepository;
import com.hrms.employee.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;  // Needed for department lookup

    // Constructor injection: Notice both repositories are injected.
    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = convertToEntity(employeeDTO);
        employee.setCreatedDate(LocalDateTime.now());
        employee.setCreatedBy("SYSTEM");
        Employee savedEmployee = employeeRepository.save(employee);
        return convertToDTO(savedEmployee);
    }

    @Override
    public EmployeeDTO updateEmployee(Long employeeId, EmployeeDTO employeeDTO) {
        Employee existingEmployee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + employeeId));

        // Update all simple fields
        existingEmployee.setFirstName(employeeDTO.getFirstName());
        existingEmployee.setMiddleName(employeeDTO.getMiddleName());
        existingEmployee.setLastName(employeeDTO.getLastName());
        existingEmployee.setDateOfBirth(employeeDTO.getDateOfBirth());
        existingEmployee.setGender(employeeDTO.getGender());
        existingEmployee.setMaritalStatus(employeeDTO.getMaritalStatus());
        existingEmployee.setNationality(employeeDTO.getNationality());
        existingEmployee.setSsn(employeeDTO.getSsn());
        existingEmployee.setPersonalEmail(employeeDTO.getPersonalEmail());
        existingEmployee.setPersonalPhoneNumber(employeeDTO.getPersonalPhoneNumber());
        existingEmployee.setStreetAddress(employeeDTO.getStreetAddress());
        existingEmployee.setCity(employeeDTO.getCity());
        existingEmployee.setStateProvince(employeeDTO.getStateProvince());
        existingEmployee.setPostalCode(employeeDTO.getPostalCode());
        existingEmployee.setCountry(employeeDTO.getCountry());
        existingEmployee.setBloodGroup(employeeDTO.getBloodGroup());
        existingEmployee.setPhoto(employeeDTO.getPhoto());
        existingEmployee.setJobTitle(employeeDTO.getJobTitle());
        existingEmployee.setEmployeeType(employeeDTO.getEmployeeType());
        existingEmployee.setEmploymentStatus(employeeDTO.getEmploymentStatus());
        existingEmployee.setHireDate(employeeDTO.getHireDate());
        existingEmployee.setTerminationDate(employeeDTO.getTerminationDate());
        existingEmployee.setWorkLocation(employeeDTO.getWorkLocation());
        existingEmployee.setWorkEmail(employeeDTO.getWorkEmail());
        existingEmployee.setWorkPhoneNumber(employeeDTO.getWorkPhoneNumber());
        existingEmployee.setGradeLevel(employeeDTO.getGradeLevel());
        existingEmployee.setShiftDetails(employeeDTO.getShiftDetails());
        existingEmployee.setProbationStartDate(employeeDTO.getProbationStartDate());
        existingEmployee.setProbationEndDate(employeeDTO.getProbationEndDate());
        existingEmployee.setContractType(employeeDTO.getContractType());
        existingEmployee.setContractEndDate(employeeDTO.getContractEndDate());
        existingEmployee.setEmergencyContactName(employeeDTO.getEmergencyContactName());
        existingEmployee.setEmergencyRelationship(employeeDTO.getEmergencyRelationship());
        existingEmployee.setEmergencyPhone(employeeDTO.getEmergencyPhone());
        existingEmployee.setEmergencyEmail(employeeDTO.getEmergencyEmail());
        existingEmployee.setEmergencyAddress(employeeDTO.getEmergencyAddress());
        existingEmployee.setVisaType(employeeDTO.getVisaType());
        existingEmployee.setVisaNumber(employeeDTO.getVisaNumber());
        existingEmployee.setVisaIssueDate(employeeDTO.getVisaIssueDate());
        existingEmployee.setVisaExpiryDate(employeeDTO.getVisaExpiryDate());
        existingEmployee.setTaxIdentificationNumber(employeeDTO.getTaxIdentificationNumber());
        existingEmployee.setBackgroundCheckStatus(employeeDTO.getBackgroundCheckStatus());
        existingEmployee.setBackgroundCheckDate(employeeDTO.getBackgroundCheckDate());
        existingEmployee.setDataConsent(employeeDTO.getDataConsent());
        existingEmployee.setDataConsentDate(employeeDTO.getDataConsentDate());

        // Validate and set Department if provided
        if (employeeDTO.getDepartmentId() != null) {
            Department dept = departmentRepository.findById(employeeDTO.getDepartmentId())
                    .orElseThrow(() -> new RuntimeException("Department not found with id: " + employeeDTO.getDepartmentId()));
            existingEmployee.setDepartment(dept);
        } else {
            existingEmployee.setDepartment(null);
        }

        // Validate and set Manager if provided
        if (employeeDTO.getManagerId() != null) {
            if (employeeDTO.getManagerId().equals(existingEmployee.getEmployeeId())) {
                throw new RuntimeException("An employee cannot be their own manager.");
            }
            Employee manager = employeeRepository.findById(employeeDTO.getManagerId())
                    .orElseThrow(() -> new RuntimeException("Manager not found with id: " + employeeDTO.getManagerId()));
            existingEmployee.setManager(manager);
        } else {
            existingEmployee.setManager(null);
        }
        
        existingEmployee.setLastUpdatedBy("SYSTEM");
        existingEmployee.setLastUpdatedDate(LocalDateTime.now());

        Employee updatedEmployee = employeeRepository.save(existingEmployee);
        return convertToDTO(updatedEmployee);
    }

    @Override
    public EmployeeDTO getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + employeeId));
        return convertToDTO(employee);
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDTO> dtos = new ArrayList<>();
        for (Employee emp : employees) {
            dtos.add(convertToDTO(emp));
        }
        return dtos;
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        if (!employeeRepository.existsById(employeeId)) {
            throw new RuntimeException("Employee not found with id: " + employeeId);
        }
        employeeRepository.deleteById(employeeId);
    }

    // Conversion: Entity → DTO
    private EmployeeDTO convertToDTO(Employee employee) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setEmployeeId(employee.getEmployeeId());
        dto.setFirstName(employee.getFirstName());
        dto.setMiddleName(employee.getMiddleName());
        dto.setLastName(employee.getLastName());
        dto.setDateOfBirth(employee.getDateOfBirth());
        dto.setGender(employee.getGender());
        dto.setMaritalStatus(employee.getMaritalStatus());
        dto.setNationality(employee.getNationality());
        dto.setSsn(employee.getSsn());
        dto.setPersonalEmail(employee.getPersonalEmail());
        dto.setPersonalPhoneNumber(employee.getPersonalPhoneNumber());
        dto.setStreetAddress(employee.getStreetAddress());
        dto.setCity(employee.getCity());
        dto.setStateProvince(employee.getStateProvince());
        dto.setPostalCode(employee.getPostalCode());
        dto.setCountry(employee.getCountry());
        dto.setBloodGroup(employee.getBloodGroup());
        dto.setPhoto(employee.getPhoto());
        dto.setJobTitle(employee.getJobTitle());
        dto.setEmployeeType(employee.getEmployeeType());
        dto.setEmploymentStatus(employee.getEmploymentStatus());
        dto.setHireDate(employee.getHireDate());
        dto.setTerminationDate(employee.getTerminationDate());
        
        if (employee.getDepartment() != null) {
            dto.setDepartmentId(employee.getDepartment().getDepartmentId());
            dto.setDepartmentName(employee.getDepartment().getDepartmentName());
        }
        if (employee.getManager() != null) {
            dto.setManagerId(employee.getManager().getEmployeeId());
        }
        
        dto.setWorkLocation(employee.getWorkLocation());
        dto.setWorkEmail(employee.getWorkEmail());
        dto.setWorkPhoneNumber(employee.getWorkPhoneNumber());
        dto.setGradeLevel(employee.getGradeLevel());
        dto.setShiftDetails(employee.getShiftDetails());
        dto.setProbationStartDate(employee.getProbationStartDate());
        dto.setProbationEndDate(employee.getProbationEndDate());
        dto.setContractType(employee.getContractType());
        dto.setContractEndDate(employee.getContractEndDate());
        dto.setEmergencyContactName(employee.getEmergencyContactName());
        dto.setEmergencyRelationship(employee.getEmergencyRelationship());
        dto.setEmergencyPhone(employee.getEmergencyPhone());
        dto.setEmergencyEmail(employee.getEmergencyEmail());
        dto.setEmergencyAddress(employee.getEmergencyAddress());
        dto.setVisaType(employee.getVisaType());
        dto.setVisaNumber(employee.getVisaNumber());
        dto.setVisaIssueDate(employee.getVisaIssueDate());
        dto.setVisaExpiryDate(employee.getVisaExpiryDate());
        dto.setTaxIdentificationNumber(employee.getTaxIdentificationNumber());
        dto.setBackgroundCheckStatus(employee.getBackgroundCheckStatus());
        dto.setBackgroundCheckDate(employee.getBackgroundCheckDate());
        dto.setDataConsent(employee.getDataConsent());
        dto.setDataConsentDate(employee.getDataConsentDate());
        dto.setCreatedBy(employee.getCreatedBy());
        dto.setCreatedDate(employee.getCreatedDate());
        dto.setLastUpdatedBy(employee.getLastUpdatedBy());
        dto.setLastUpdatedDate(employee.getLastUpdatedDate());
        return dto;
    }

    // Conversion: DTO → Entity, with validations and relationship lookups
    private Employee convertToEntity(EmployeeDTO dto) {
        Employee employee = new Employee();
        // For update, employeeId might be null on create, or non-null on update.
        employee.setEmployeeId(dto.getEmployeeId());
        employee.setFirstName(dto.getFirstName());
        employee.setMiddleName(dto.getMiddleName());
        employee.setLastName(dto.getLastName());
        employee.setDateOfBirth(dto.getDateOfBirth());
        employee.setGender(dto.getGender());
        employee.setMaritalStatus(dto.getMaritalStatus());
        employee.setNationality(dto.getNationality());
        employee.setSsn(dto.getSsn());
        employee.setPersonalEmail(dto.getPersonalEmail());
        employee.setPersonalPhoneNumber(dto.getPersonalPhoneNumber());
        employee.setStreetAddress(dto.getStreetAddress());
        employee.setCity(dto.getCity());
        employee.setStateProvince(dto.getStateProvince());
        employee.setPostalCode(dto.getPostalCode());
        employee.setCountry(dto.getCountry());
        employee.setBloodGroup(dto.getBloodGroup());
        employee.setPhoto(dto.getPhoto());
        employee.setJobTitle(dto.getJobTitle());
        employee.setEmployeeType(dto.getEmployeeType());
        employee.setEmploymentStatus(dto.getEmploymentStatus());
        employee.setHireDate(dto.getHireDate());
        employee.setTerminationDate(dto.getTerminationDate());
        employee.setWorkLocation(dto.getWorkLocation());
        employee.setWorkEmail(dto.getWorkEmail());
        employee.setWorkPhoneNumber(dto.getWorkPhoneNumber());
        employee.setGradeLevel(dto.getGradeLevel());
        employee.setShiftDetails(dto.getShiftDetails());
        employee.setProbationStartDate(dto.getProbationStartDate());
        employee.setProbationEndDate(dto.getProbationEndDate());
        employee.setContractType(dto.getContractType());
        employee.setContractEndDate(dto.getContractEndDate());
        employee.setEmergencyContactName(dto.getEmergencyContactName());
        employee.setEmergencyRelationship(dto.getEmergencyRelationship());
        employee.setEmergencyPhone(dto.getEmergencyPhone());
        employee.setEmergencyEmail(dto.getEmergencyEmail());
        employee.setEmergencyAddress(dto.getEmergencyAddress());
        employee.setVisaType(dto.getVisaType());
        employee.setVisaNumber(dto.getVisaNumber());
        employee.setVisaIssueDate(dto.getVisaIssueDate());
        employee.setVisaExpiryDate(dto.getVisaExpiryDate());
        employee.setTaxIdentificationNumber(dto.getTaxIdentificationNumber());
        employee.setBackgroundCheckStatus(dto.getBackgroundCheckStatus());
        employee.setBackgroundCheckDate(dto.getBackgroundCheckDate());
        employee.setDataConsent(dto.getDataConsent());
        employee.setDataConsentDate(dto.getDataConsentDate());
        employee.setCreatedBy(dto.getCreatedBy());
        employee.setCreatedDate(dto.getCreatedDate());
        employee.setLastUpdatedBy(dto.getLastUpdatedBy());
        employee.setLastUpdatedDate(dto.getLastUpdatedDate());

        // Lookup and set Department if departmentId is provided
        if (dto.getDepartmentId() != null) {
            Department dept = departmentRepository.findById(dto.getDepartmentId())
                    .orElseThrow(() -> new RuntimeException("Department not found with id: " + dto.getDepartmentId()));
            employee.setDepartment(dept);
        } else {
            employee.setDepartment(null);
        }

        // Lookup and set Manager if managerId is provided; also check for self-reference
        if (dto.getManagerId() != null) {
            // Prevent self-reference (if employeeId is provided and equals managerId)
            if (dto.getEmployeeId() != null && dto.getEmployeeId().equals(dto.getManagerId())) {
                throw new RuntimeException("An employee cannot be their own manager.");
            }
            Employee manager = employeeRepository.findById(dto.getManagerId())
                    .orElseThrow(() -> new RuntimeException("Manager not found with id: " + dto.getManagerId()));
            employee.setManager(manager);
        } else {
            employee.setManager(null);
        }

        return employee;
    }
}






/*package com.hrms.employee.service.impl;

import com.hrms.dept.entity.Department;
import com.hrms.employee.dto.EmployeeDTO;
import com.hrms.employee.entity.Employee;
import com.hrms.employee.repository.EmployeeRepository;
import com.hrms.employee.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    // In a full application, you'd also inject DepartmentRepository and EmployeeRepository (for manager lookup)
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = convertToEntity(employeeDTO);
        employee.setCreatedDate(LocalDateTime.now());
        employee.setCreatedBy("SYSTEM");
        Employee savedEmployee = employeeRepository.save(employee);
        return convertToDTO(savedEmployee);
    }

    @Override
    public EmployeeDTO updateEmployee(Long employeeId, EmployeeDTO employeeDTO) {
        Employee existingEmployee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + employeeId));
        
        // Update fields; in real code, consider mapping libraries for brevity
        existingEmployee.setFirstName(employeeDTO.getFirstName());
        existingEmployee.setMiddleName(employeeDTO.getMiddleName());
        existingEmployee.setLastName(employeeDTO.getLastName());
        existingEmployee.setDateOfBirth(employeeDTO.getDateOfBirth());
        existingEmployee.setGender(employeeDTO.getGender());
        existingEmployee.setMaritalStatus(employeeDTO.getMaritalStatus());
        existingEmployee.setNationality(employeeDTO.getNationality());
        existingEmployee.setSsn(employeeDTO.getSsn());
        existingEmployee.setPersonalEmail(employeeDTO.getPersonalEmail());
        existingEmployee.setPersonalPhoneNumber(employeeDTO.getPersonalPhoneNumber());
        existingEmployee.setStreetAddress(employeeDTO.getStreetAddress());
        existingEmployee.setCity(employeeDTO.getCity());
        existingEmployee.setStateProvince(employeeDTO.getStateProvince());
        existingEmployee.setPostalCode(employeeDTO.getPostalCode());
        existingEmployee.setCountry(employeeDTO.getCountry());
        existingEmployee.setBloodGroup(employeeDTO.getBloodGroup());
        existingEmployee.setPhoto(employeeDTO.getPhoto());
        existingEmployee.setJobTitle(employeeDTO.getJobTitle());
        existingEmployee.setEmployeeType(employeeDTO.getEmployeeType());
        existingEmployee.setEmploymentStatus(employeeDTO.getEmploymentStatus());
        existingEmployee.setHireDate(employeeDTO.getHireDate());
        existingEmployee.setTerminationDate(employeeDTO.getTerminationDate());
        // For department and manager, you would typically fetch the entities and set them; skipping here for brevity.
        existingEmployee.setWorkLocation(employeeDTO.getWorkLocation());
        existingEmployee.setWorkEmail(employeeDTO.getWorkEmail());
        existingEmployee.setWorkPhoneNumber(employeeDTO.getWorkPhoneNumber());
        existingEmployee.setGradeLevel(employeeDTO.getGradeLevel());
        existingEmployee.setShiftDetails(employeeDTO.getShiftDetails());
        existingEmployee.setProbationStartDate(employeeDTO.getProbationStartDate());
        existingEmployee.setProbationEndDate(employeeDTO.getProbationEndDate());
        existingEmployee.setContractType(employeeDTO.getContractType());
        existingEmployee.setContractEndDate(employeeDTO.getContractEndDate());
        existingEmployee.setEmergencyContactName(employeeDTO.getEmergencyContactName());
        existingEmployee.setEmergencyRelationship(employeeDTO.getEmergencyRelationship());
        existingEmployee.setEmergencyPhone(employeeDTO.getEmergencyPhone());
        existingEmployee.setEmergencyEmail(employeeDTO.getEmergencyEmail());
        existingEmployee.setEmergencyAddress(employeeDTO.getEmergencyAddress());
        existingEmployee.setVisaType(employeeDTO.getVisaType());
        existingEmployee.setVisaNumber(employeeDTO.getVisaNumber());
        existingEmployee.setVisaIssueDate(employeeDTO.getVisaIssueDate());
        existingEmployee.setVisaExpiryDate(employeeDTO.getVisaExpiryDate());
        existingEmployee.setTaxIdentificationNumber(employeeDTO.getTaxIdentificationNumber());
        existingEmployee.setBackgroundCheckStatus(employeeDTO.getBackgroundCheckStatus());
        existingEmployee.setBackgroundCheckDate(employeeDTO.getBackgroundCheckDate());
        existingEmployee.setDataConsent(employeeDTO.getDataConsent());
        existingEmployee.setDataConsentDate(employeeDTO.getDataConsentDate());
        existingEmployee.setLastUpdatedBy("SYSTEM");
        existingEmployee.setLastUpdatedDate(LocalDateTime.now());

        Employee updatedEmployee = employeeRepository.save(existingEmployee);
        return convertToDTO(updatedEmployee);
    }

    @Override
    public EmployeeDTO getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + employeeId));
        return convertToDTO(employee);
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDTO> dtos = new ArrayList<>();
        for (Employee emp : employees) {
            dtos.add(convertToDTO(emp));
        }
        return dtos;
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        if (!employeeRepository.existsById(employeeId)) {
            throw new RuntimeException("Employee not found with id: " + employeeId);
        }
        employeeRepository.deleteById(employeeId);
    }

    // Simple conversion methods. In a real app consider using a library (like MapStruct).
    private EmployeeDTO convertToDTO(Employee employee) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setEmployeeId(employee.getEmployeeId());
        dto.setFirstName(employee.getFirstName());
        dto.setMiddleName(employee.getMiddleName());
        dto.setLastName(employee.getLastName());
        dto.setDateOfBirth(employee.getDateOfBirth());
        dto.setGender(employee.getGender());
        dto.setMaritalStatus(employee.getMaritalStatus());
        dto.setNationality(employee.getNationality());
        dto.setSsn(employee.getSsn());
        dto.setPersonalEmail(employee.getPersonalEmail());
        dto.setPersonalPhoneNumber(employee.getPersonalPhoneNumber());
        dto.setStreetAddress(employee.getStreetAddress());
        dto.setCity(employee.getCity());
        dto.setStateProvince(employee.getStateProvince());
        dto.setPostalCode(employee.getPostalCode());
        dto.setCountry(employee.getCountry());
        dto.setBloodGroup(employee.getBloodGroup());
        dto.setPhoto(employee.getPhoto());
        dto.setJobTitle(employee.getJobTitle());
        dto.setEmployeeType(employee.getEmployeeType());
        dto.setEmploymentStatus(employee.getEmploymentStatus());
        dto.setHireDate(employee.getHireDate());
        dto.setTerminationDate(employee.getTerminationDate());
        if (employee.getDepartment() != null) {
            dto.setDepartmentId(employee.getDepartment().getDepartmentId());
            dto.setDepartmentName(employee.getDepartment().getDepartmentName());
        }
        if (employee.getManager() != null) {
            dto.setManagerId(employee.getManager().getEmployeeId());
        }
        dto.setWorkLocation(employee.getWorkLocation());
        dto.setWorkEmail(employee.getWorkEmail());
        dto.setWorkPhoneNumber(employee.getWorkPhoneNumber());
        dto.setGradeLevel(employee.getGradeLevel());
        dto.setShiftDetails(employee.getShiftDetails());
        dto.setProbationStartDate(employee.getProbationStartDate());
        dto.setProbationEndDate(employee.getProbationEndDate());
        dto.setContractType(employee.getContractType());
        dto.setContractEndDate(employee.getContractEndDate());
        dto.setEmergencyContactName(employee.getEmergencyContactName());
        dto.setEmergencyRelationship(employee.getEmergencyRelationship());
        dto.setEmergencyPhone(employee.getEmergencyPhone());
        dto.setEmergencyEmail(employee.getEmergencyEmail());
        dto.setEmergencyAddress(employee.getEmergencyAddress());
        dto.setVisaType(employee.getVisaType());
        dto.setVisaNumber(employee.getVisaNumber());
        dto.setVisaIssueDate(employee.getVisaIssueDate());
        dto.setVisaExpiryDate(employee.getVisaExpiryDate());
        dto.setTaxIdentificationNumber(employee.getTaxIdentificationNumber());
        dto.setBackgroundCheckStatus(employee.getBackgroundCheckStatus());
        dto.setBackgroundCheckDate(employee.getBackgroundCheckDate());
        dto.setDataConsent(employee.getDataConsent());
        dto.setDataConsentDate(employee.getDataConsentDate());
        dto.setCreatedBy(employee.getCreatedBy());
        dto.setCreatedDate(employee.getCreatedDate());
        dto.setLastUpdatedBy(employee.getLastUpdatedBy());
        dto.setLastUpdatedDate(employee.getLastUpdatedDate());
        return dto;
    }

    private Employee convertToEntity(EmployeeDTO dto) {
        Employee employee = new Employee();
        employee.setFirstName(dto.getFirstName());
        employee.setMiddleName(dto.getMiddleName());
        employee.setLastName(dto.getLastName());
        employee.setDateOfBirth(dto.getDateOfBirth());
        employee.setGender(dto.getGender());
        employee.setMaritalStatus(dto.getMaritalStatus());
        employee.setNationality(dto.getNationality());
        employee.setSsn(dto.getSsn());
        employee.setPersonalEmail(dto.getPersonalEmail());
        employee.setPersonalPhoneNumber(dto.getPersonalPhoneNumber());
        employee.setStreetAddress(dto.getStreetAddress());
        employee.setCity(dto.getCity());
        employee.setStateProvince(dto.getStateProvince());
        employee.setPostalCode(dto.getPostalCode());
        employee.setCountry(dto.getCountry());
        employee.setBloodGroup(dto.getBloodGroup());
        employee.setPhoto(dto.getPhoto());
        employee.setJobTitle(dto.getJobTitle());
        employee.setEmployeeType(dto.getEmployeeType());
        employee.setEmploymentStatus(dto.getEmploymentStatus());
        employee.setHireDate(dto.getHireDate());
        employee.setTerminationDate(dto.getTerminationDate());
        // For department and manager, you'll need to retrieve the actual entities based on IDs.\n        // Here we leave them null and assume you'll set them via a separate lookup.\n        employee.setWorkLocation(dto.getWorkLocation());
        employee.setWorkEmail(dto.getWorkEmail());
        employee.setWorkPhoneNumber(dto.getWorkPhoneNumber());
        employee.setGradeLevel(dto.getGradeLevel());
        employee.setShiftDetails(dto.getShiftDetails());
        employee.setProbationStartDate(dto.getProbationStartDate());
        employee.setProbationEndDate(dto.getProbationEndDate());
        employee.setContractType(dto.getContractType());
        employee.setContractEndDate(dto.getContractEndDate());
        employee.setEmergencyContactName(dto.getEmergencyContactName());
        employee.setEmergencyRelationship(dto.getEmergencyRelationship());
        employee.setEmergencyPhone(dto.getEmergencyPhone());
        employee.setEmergencyEmail(dto.getEmergencyEmail());
        employee.setEmergencyAddress(dto.getEmergencyAddress());
        employee.setVisaType(dto.getVisaType());
        employee.setVisaNumber(dto.getVisaNumber());
        employee.setVisaIssueDate(dto.getVisaIssueDate());
        employee.setVisaExpiryDate(dto.getVisaExpiryDate());
        employee.setTaxIdentificationNumber(dto.getTaxIdentificationNumber());
        employee.setBackgroundCheckStatus(dto.getBackgroundCheckStatus());
        employee.setBackgroundCheckDate(dto.getBackgroundCheckDate());
        employee.setDataConsent(dto.getDataConsent());
        employee.setDataConsentDate(dto.getDataConsentDate());
        return employee;
    }
}*/
