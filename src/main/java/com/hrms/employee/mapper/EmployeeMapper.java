package com.hrms.employee.mapper;

import com.hrms.employee.dto.EmployeeDTO;
import com.hrms.employee.entity.Employee;

public class EmployeeMapper {

    /**
     * Converts an Employee entity to an EmployeeDTO.
     * @param employee The Employee entity to convert.
     * @return EmployeeDTO with all fields populated from the Employee entity, or null if the input is null.
     */
    public static EmployeeDTO toDto(Employee employee) {
        if (employee == null) return null;

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

        // Set department information if available
        if (employee.getDepartment() != null) {
            dto.setDepartmentId(employee.getDepartment().getDepartmentId());
            dto.setDepartmentName(employee.getDepartment().getDepartmentName());
        }

        // Set manager ID if available
        if (employee.getManager() != null) {
            dto.setManagerId(employee.getManager().getEmployeeId());
        }

        return dto;
    }

    /**
     * Converts an EmployeeDTO to an Employee entity.
     * @param dto The EmployeeDTO to convert.
     * @return Employee entity with all fields populated from the EmployeeDTO.
     */
    public static Employee toEntity(EmployeeDTO dto) {
        if (dto == null) return null;

        Employee employee = new Employee();
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

        // Note: Department and Manager entities need to be set separately in the service layer
        // because they require fetching the actual entities from the database.
        // Example:
        // employee.setDepartment(departmentService.findById(dto.getDepartmentId()));
        // employee.setManager(employeeService.findById(dto.getManagerId()));

        return employee;
    }
}