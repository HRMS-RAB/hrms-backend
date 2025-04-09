package com.hrms.dept.dto;

import java.time.LocalDateTime;

public class DepartmentDTO {

    private Long departmentId;
    private String departmentName;
    private String departmentCode;
    private Long parentDepartmentId;
    private Long departmentHeadId;
    private String description;
    private String location;
    private Double budgetAllocation;
    private String departmentEmail;
    private String departmentPhoneNumber;
    private String createdBy;
    private LocalDateTime createdDate;
    private String lastUpdatedBy;
    private LocalDateTime lastUpdatedDate;

    // Constructors
    public DepartmentDTO() { }

    // Another constructor (optional)
    public DepartmentDTO(Long departmentId, String departmentName, String departmentCode, 
                         Long parentDepartmentId, Long departmentHeadId, String description, 
                         String location, Double budgetAllocation, String departmentEmail, 
                         String departmentPhoneNumber, String createdBy, LocalDateTime createdDate, 
                         String lastUpdatedBy, LocalDateTime lastUpdatedDate) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.departmentCode = departmentCode;
        this.parentDepartmentId = parentDepartmentId;
        this.departmentHeadId = departmentHeadId;
        this.description = description;
        this.location = location;
        this.budgetAllocation = budgetAllocation;
        this.departmentEmail = departmentEmail;
        this.departmentPhoneNumber = departmentPhoneNumber;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.lastUpdatedDate = lastUpdatedDate;
    }

    // Getters and Setters
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

    public String getDepartmentCode() {
        return departmentCode;
    }
    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public Long getParentDepartmentId() {
        return parentDepartmentId;
    }
    public void setParentDepartmentId(Long parentDepartmentId) {
        this.parentDepartmentId = parentDepartmentId;
    }

    public Long getDepartmentHeadId() {
        return departmentHeadId;
    }
    public void setDepartmentHeadId(Long departmentHeadId) {
        this.departmentHeadId = departmentHeadId;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public Double getBudgetAllocation() {
        return budgetAllocation;
    }
    public void setBudgetAllocation(Double budgetAllocation) {
        this.budgetAllocation = budgetAllocation;
    }

    public String getDepartmentEmail() {
        return departmentEmail;
    }
    public void setDepartmentEmail(String departmentEmail) {
        this.departmentEmail = departmentEmail;
    }

    public String getDepartmentPhoneNumber() {
        return departmentPhoneNumber;
    }
    public void setDepartmentPhoneNumber(String departmentPhoneNumber) {
        this.departmentPhoneNumber = departmentPhoneNumber;
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
