package com.hrms.employee.dto;

import com.hrms.enums.EmployeeType; // Added import
import java.time.LocalDate;
import java.time.LocalDateTime;

public class EmployeeDTO {

    private Long employeeId;

    // Personal Information
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private String maritalStatus;
    private String nationality;
    private String ssn;
    private String personalEmail;
    private String personalPhoneNumber;
    private String streetAddress;
    private String city;
    private String stateProvince;
    private String postalCode;
    private String country;
    private String bloodGroup;
    private String photo;

    // Professional Information
    private String jobTitle;
    private EmployeeType employeeType; // Changed from String to EmployeeType
    private String employmentStatus;
    private LocalDate hireDate;
    private LocalDate terminationDate;

    // Department Information
    private Long departmentId;
    private String departmentName;

    // Manager ID
    private Long managerId;

    private String workLocation;
    private String workEmail;
    private String workPhoneNumber;
    private String gradeLevel;
    private String shiftDetails;
    private LocalDate probationStartDate;
    private LocalDate probationEndDate;
    private String contractType;
    private LocalDate contractEndDate;

    // Emergency Contact Information
    private String emergencyContactName;
    private String emergencyRelationship;
    private String emergencyPhone;
    private String emergencyEmail;
    private String emergencyAddress;

    // Compliance and Legal
    private String visaType;
    private String visaNumber;
    private LocalDate visaIssueDate;
    private LocalDate visaExpiryDate;
    private String taxIdentificationNumber;
    private String backgroundCheckStatus;
    private LocalDate backgroundCheckDate;
    private Boolean dataConsent;
    private LocalDate dataConsentDate;

    // Metadata
    private String createdBy;
    private LocalDateTime createdDate;
    private String lastUpdatedBy;
    private LocalDateTime lastUpdatedDate;

    public EmployeeDTO() {
    }

    // Getters and Setters
    public Long getEmployeeId() { return employeeId; }
    public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getMiddleName() { return middleName; }
    public void setMiddleName(String middleName) { this.middleName = middleName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getMaritalStatus() { return maritalStatus; }
    public void setMaritalStatus(String maritalStatus) { this.maritalStatus = maritalStatus; }
    public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }
    public String getSsn() { return ssn; }
    public void setSsn(String ssn) { this.ssn = ssn; }
    public String getPersonalEmail() { return personalEmail; }
    public void setPersonalEmail(String personalEmail) { this.personalEmail = personalEmail; }
    public String getPersonalPhoneNumber() { return personalPhoneNumber; }
    public void setPersonalPhoneNumber(String personalPhoneNumber) { this.personalPhoneNumber = personalPhoneNumber; }
    public String getStreetAddress() { return streetAddress; }
    public void setStreetAddress(String streetAddress) { this.streetAddress = streetAddress; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getStateProvince() { return stateProvince; }
    public void setStateProvince(String stateProvince) { this.stateProvince = stateProvince; }
    public String getPostalCode() { return postalCode; }
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
    public String getBloodGroup() { return bloodGroup; }
    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }
    public String getPhoto() { return photo; }
    public void setPhoto(String photo) { this.photo = photo; }
    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }
    public EmployeeType getEmployeeType() { return employeeType; } // Fixed to EmployeeType
    public void setEmployeeType(EmployeeType employeeType) { this.employeeType = employeeType; } // Fixed to EmployeeType
    public String getEmploymentStatus() { return employmentStatus; }
    public void setEmploymentStatus(String employmentStatus) { this.employmentStatus = employmentStatus; }
    public LocalDate getHireDate() { return hireDate; }
    public void setHireDate(LocalDate hireDate) { this.hireDate = hireDate; }
    public LocalDate getTerminationDate() { return terminationDate; }
    public void setTerminationDate(LocalDate terminationDate) { this.terminationDate = terminationDate; }
    public Long getDepartmentId() { return departmentId; }
    public void setDepartmentId(Long departmentId) { this.departmentId = departmentId; }
    public String getDepartmentName() { return departmentName; }
    public void setDepartmentName(String departmentName) { this.departmentName = departmentName; }
    public Long getManagerId() { return managerId; }
    public void setManagerId(Long managerId) { this.managerId = managerId; }
    public String getWorkLocation() { return workLocation; }
    public void setWorkLocation(String workLocation) { this.workLocation = workLocation; }
    public String getWorkEmail() { return workEmail; }
    public void setWorkEmail(String workEmail) { this.workEmail = workEmail; }
    public String getWorkPhoneNumber() { return workPhoneNumber; }
    public void setWorkPhoneNumber(String workPhoneNumber) { this.workPhoneNumber = workPhoneNumber; }
    public String getGradeLevel() { return gradeLevel; }
    public void setGradeLevel(String gradeLevel) { this.gradeLevel = gradeLevel; }
    public String getShiftDetails() { return shiftDetails; }
    public void setShiftDetails(String shiftDetails) { this.shiftDetails = shiftDetails; }
    public LocalDate getProbationStartDate() { return probationStartDate; }
    public void setProbationStartDate(LocalDate probationStartDate) { this.probationStartDate = probationStartDate; }
    public LocalDate getProbationEndDate() { return probationEndDate; }
    public void setProbationEndDate(LocalDate probationEndDate) { this.probationEndDate = probationEndDate; }
    public String getContractType() { return contractType; }
    public void setContractType(String contractType) { this.contractType = contractType; }
    public LocalDate getContractEndDate() { return contractEndDate; }
    public void setContractEndDate(LocalDate contractEndDate) { this.contractEndDate = contractEndDate; }
    public String getEmergencyContactName() { return emergencyContactName; }
    public void setEmergencyContactName(String emergencyContactName) { this.emergencyContactName = emergencyContactName; }
    public String getEmergencyRelationship() { return emergencyRelationship; }
    public void setEmergencyRelationship(String emergencyRelationship) { this.emergencyRelationship = emergencyRelationship; }
    public String getEmergencyPhone() { return emergencyPhone; }
    public void setEmergencyPhone(String emergencyPhone) { this.emergencyPhone = emergencyPhone; }
    public String getEmergencyEmail() { return emergencyEmail; }
    public void setEmergencyEmail(String emergencyEmail) { this.emergencyEmail = emergencyEmail; }
    public String getEmergencyAddress() { return emergencyAddress; }
    public void setEmergencyAddress(String emergencyAddress) { this.emergencyAddress = emergencyAddress; }
    public String getVisaType() { return visaType; }
    public void setVisaType(String visaType) { this.visaType = visaType; }
    public String getVisaNumber() { return visaNumber; }
    public void setVisaNumber(String visaNumber) { this.visaNumber = visaNumber; }
    public LocalDate getVisaIssueDate() { return visaIssueDate; }
    public void setVisaIssueDate(LocalDate visaIssueDate) { this.visaIssueDate = visaIssueDate; }
    public LocalDate getVisaExpiryDate() { return visaExpiryDate; }
    public void setVisaExpiryDate(LocalDate visaExpiryDate) { this.visaExpiryDate = visaExpiryDate; }
    public String getTaxIdentificationNumber() { return taxIdentificationNumber; }
    public void setTaxIdentificationNumber(String taxIdentificationNumber) { this.taxIdentificationNumber = taxIdentificationNumber; }
    public String getBackgroundCheckStatus() { return backgroundCheckStatus; }
    public void setBackgroundCheckStatus(String backgroundCheckStatus) { this.backgroundCheckStatus = backgroundCheckStatus; }
    public LocalDate getBackgroundCheckDate() { return backgroundCheckDate; }
    public void setBackgroundCheckDate(LocalDate backgroundCheckDate) { this.backgroundCheckDate = backgroundCheckDate; }
    public Boolean getDataConsent() { return dataConsent; }
    public void setDataConsent(Boolean dataConsent) { this.dataConsent = dataConsent; }
    public LocalDate getDataConsentDate() { return dataConsentDate; }
    public void setDataConsentDate(LocalDate dataConsentDate) { this.dataConsentDate = dataConsentDate; }
    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }
    public LocalDateTime getCreatedDate() { return createdDate; }
    public void setCreatedDate(LocalDateTime createdDate) { this.createdDate = createdDate; }
    public String getLastUpdatedBy() { return lastUpdatedBy; }
    public void setLastUpdatedBy(String lastUpdatedBy) { this.lastUpdatedBy = lastUpdatedBy; }
    public LocalDateTime getLastUpdatedDate() { return lastUpdatedDate; }
    public void setLastUpdatedDate(LocalDateTime lastUpdatedDate) { this.lastUpdatedDate = lastUpdatedDate; }
}

/*package com.hrms.employee.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class EmployeeDTO {

    private Long employeeId;

    // Personal Information
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private String maritalStatus;
    private String nationality;
    private String ssn;
    private String personalEmail;
    private String personalPhoneNumber;
    private String streetAddress;
    private String city;
    private String stateProvince;
    private String postalCode;
    private String country;
    private String bloodGroup;
    private String photo;

    // Professional Information
    private String jobTitle;
    private String employeeType;
    private String employmentStatus;
    private LocalDate hireDate;
    private LocalDate terminationDate;
    
    // Department Information
    private Long departmentId;
    private String departmentName;
    
    // Manager ID
    private Long managerId;
    
    private String workLocation;
    private String workEmail;
    private String workPhoneNumber;
    private String gradeLevel;
    private String shiftDetails;
    private LocalDate probationStartDate;
    private LocalDate probationEndDate;
    private String contractType;
    private LocalDate contractEndDate;

    // Emergency Contact Information
    private String emergencyContactName;
    private String emergencyRelationship;
    private String emergencyPhone;
    private String emergencyEmail;
    private String emergencyAddress;

    // Compliance and Legal
    private String visaType;
    private String visaNumber;
    private LocalDate visaIssueDate;
    private LocalDate visaExpiryDate;
    private String taxIdentificationNumber;
    private String backgroundCheckStatus;
    private LocalDate backgroundCheckDate;
    private Boolean dataConsent;
    private LocalDate dataConsentDate;

    // Metadata
    private String createdBy;
    private LocalDateTime createdDate;
    private String lastUpdatedBy;
    private LocalDateTime lastUpdatedDate;

    public EmployeeDTO() {
    }

    // Getters and Setters

    public Long getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getMiddleName() {
        return middleName;
    }
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getMaritalStatus() {
        return maritalStatus;
    }
    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }
    public String getNationality() {
        return nationality;
    }
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    public String getSsn() {
        return ssn;
    }
    public void setSsn(String ssn) {
        this.ssn = ssn;
    }
    public String getPersonalEmail() {
        return personalEmail;
    }
    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }
    public String getPersonalPhoneNumber() {
        return personalPhoneNumber;
    }
    public void setPersonalPhoneNumber(String personalPhoneNumber) {
        this.personalPhoneNumber = personalPhoneNumber;
    }
    public String getStreetAddress() {
        return streetAddress;
    }
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getStateProvince() {
        return stateProvince;
    }
    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }
    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getBloodGroup() {
        return bloodGroup;
    }
    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }
    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }
    public String getJobTitle() {
        return jobTitle;
    }
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
   
    public String getEmployeeType() {
        return employeeType;
    }
    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }
    public String getEmploymentStatus() {
        return employmentStatus;
    }
    public void setEmploymentStatus(String employmentStatus) {
        this.employmentStatus = employmentStatus;
    }
    public LocalDate getHireDate() {
        return hireDate;
    }
    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }
    public LocalDate getTerminationDate() {
        return terminationDate;
    }
    public void setTerminationDate(LocalDate terminationDate) {
        this.terminationDate = terminationDate;
    }
    public Long getDepartmentId() {
        return departmentId;
    }
    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
    public String getDepartmentName() {
        return departmentName;
    }
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    public Long getManagerId() {
        return managerId;
    }
    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }
    public String getWorkLocation() {
        return workLocation;
    }
    public void setWorkLocation(String workLocation) {
        this.workLocation = workLocation;
    }
    public String getWorkEmail() {
        return workEmail;
    }
    public void setWorkEmail(String workEmail) {
        this.workEmail = workEmail;
    }
    public String getWorkPhoneNumber() {
        return workPhoneNumber;
    }
    public void setWorkPhoneNumber(String workPhoneNumber) {
        this.workPhoneNumber = workPhoneNumber;
    }
    public String getGradeLevel() {
        return gradeLevel;
    }
    public void setGradeLevel(String gradeLevel) {
        this.gradeLevel = gradeLevel;
    }
    public String getShiftDetails() {
        return shiftDetails;
    }
    public void setShiftDetails(String shiftDetails) {
        this.shiftDetails = shiftDetails;
    }
    public LocalDate getProbationStartDate() {
        return probationStartDate;
    }
    public void setProbationStartDate(LocalDate probationStartDate) {
        this.probationStartDate = probationStartDate;
    }
    public LocalDate getProbationEndDate() {
        return probationEndDate;
    }
    public void setProbationEndDate(LocalDate probationEndDate) {
        this.probationEndDate = probationEndDate;
    }
    public String getContractType() {
        return contractType;
    }
    public void setContractType(String contractType) {
        this.contractType = contractType;
    }
    public LocalDate getContractEndDate() {
        return contractEndDate;
    }
    public void setContractEndDate(LocalDate contractEndDate) {
        this.contractEndDate = contractEndDate;
    }
    public String getEmergencyContactName() {
        return emergencyContactName;
    }
    public void setEmergencyContactName(String emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
    }
    public String getEmergencyRelationship() {
        return emergencyRelationship;
    }
    public void setEmergencyRelationship(String emergencyRelationship) {
        this.emergencyRelationship = emergencyRelationship;
    }
    public String getEmergencyPhone() {
        return emergencyPhone;
    }
    public void setEmergencyPhone(String emergencyPhone) {
        this.emergencyPhone = emergencyPhone;
    }
    public String getEmergencyEmail() {
        return emergencyEmail;
    }
    public void setEmergencyEmail(String emergencyEmail) {
        this.emergencyEmail = emergencyEmail;
    }
    public String getEmergencyAddress() {
        return emergencyAddress;
    }
    public void setEmergencyAddress(String emergencyAddress) {
        this.emergencyAddress = emergencyAddress;
    }
    public String getVisaType() {
        return visaType;
    }
    public void setVisaType(String visaType) {
        this.visaType = visaType;
    }
    public String getVisaNumber() {
        return visaNumber;
    }
    public void setVisaNumber(String visaNumber) {
        this.visaNumber = visaNumber;
    }
    public LocalDate getVisaIssueDate() {
        return visaIssueDate;
    }
    public void setVisaIssueDate(LocalDate visaIssueDate) {
        this.visaIssueDate = visaIssueDate;
    }
    public LocalDate getVisaExpiryDate() {
        return visaExpiryDate;
    }
    public void setVisaExpiryDate(LocalDate visaExpiryDate) {
        this.visaExpiryDate = visaExpiryDate;
    }
    public String getTaxIdentificationNumber() {
        return taxIdentificationNumber;
    }
    public void setTaxIdentificationNumber(String taxIdentificationNumber) {
        this.taxIdentificationNumber = taxIdentificationNumber;
    }
    public String getBackgroundCheckStatus() {
        return backgroundCheckStatus;
    }
    public void setBackgroundCheckStatus(String backgroundCheckStatus) {
        this.backgroundCheckStatus = backgroundCheckStatus;
    }
    public LocalDate getBackgroundCheckDate() {
        return backgroundCheckDate;
    }
    public void setBackgroundCheckDate(LocalDate backgroundCheckDate) {
        this.backgroundCheckDate = backgroundCheckDate;
    }
    public Boolean getDataConsent() {
        return dataConsent;
    }
    public void setDataConsent(Boolean dataConsent) {
        this.dataConsent = dataConsent;
    }
    public LocalDate getDataConsentDate() {
        return dataConsentDate;
    }
    public void setDataConsentDate(LocalDate dataConsentDate) {
        this.dataConsentDate = dataConsentDate;
    }
    public String getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
    public LocalDateTime getLastUpdatedDate() {
        return lastUpdatedDate;
    }
    public void setLastUpdatedDate(LocalDateTime lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }
}
*/