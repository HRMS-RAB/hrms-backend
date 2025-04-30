package com.hrms.employee.event;

import java.io.Serializable;

public class EmployeeEvent implements Serializable {

    private Long   employeeId;
    private Long   departmentId;
    private String departmentName;
    private String fullName;
    private String workEmail;
    private String gradeLevel;
    private String employeeType;      // FULL_TIME / PART_TIME …
    private String eventType;         // CREATED / UPDATED

    public EmployeeEvent() {}

    public EmployeeEvent(Long employeeId,
                         Long departmentId,
                         String departmentName,
                         String fullName,
                         String workEmail,
                         String gradeLevel,
                         String employeeType,
                         String eventType) {
        this.employeeId     = employeeId;
        this.departmentId   = departmentId;
        this.departmentName = departmentName;
        this.fullName       = fullName;
        this.workEmail      = workEmail;
        this.gradeLevel     = gradeLevel;
        this.employeeType   = employeeType;
        this.eventType      = eventType;
    }

    /* ───── getters / setters ───── */

    public Long   getEmployeeId()       { return employeeId; }
    public void   setEmployeeId(Long employeeId) { this.employeeId = employeeId; }

    public Long   getDepartmentId()     { return departmentId; }
    public void   setDepartmentId(Long departmentId) { this.departmentId = departmentId; }

    public String getDepartmentName()   { return departmentName; }
    public void   setDepartmentName(String departmentName) { this.departmentName = departmentName; }

    public String getFullName()         { return fullName; }
    public void   setFullName(String fullName) { this.fullName = fullName; }

    public String getWorkEmail()        { return workEmail; }
    public void   setWorkEmail(String workEmail) { this.workEmail = workEmail; }

    public String getGradeLevel()       { return gradeLevel; }
    public void   setGradeLevel(String gradeLevel) { this.gradeLevel = gradeLevel; }

    public String getEmployeeType()     { return employeeType; }
    public void   setEmployeeType(String employeeType) { this.employeeType = employeeType; }

    public String getEventType()        { return eventType; }
    public void   setEventType(String eventType) { this.eventType = eventType; }
}


/*package com.hrms.employee.event;            // <-- keep this package if you moved it

import java.io.Serializable;

public class EmployeeEvent implements Serializable {

    private Long employeeId;
    private Long departmentId;
    private String departmentName;
    private String fullName;
    private String workEmail;
    private String gradeLevel;
    private String eventType;            // CREATED | UPDATED

    //** No-args ctor for Jackson 
    public EmployeeEvent() {}

   //** All-args ctor used by EmployeeServiceImpl 
    public EmployeeEvent(Long employeeId,
                         Long departmentId,
                         String departmentName,
                         String fullName,
                         String workEmail,
                         String gradeLevel,
                         String eventType) {
        this.employeeId     = employeeId;
        this.departmentId   = departmentId;
        this.departmentName = departmentName;
        this.fullName       = fullName;
        this.workEmail      = workEmail;
        this.gradeLevel     = gradeLevel;
        this.eventType      = eventType;
    }

    //* ===== getters & setters ===== 
    public Long getEmployeeId()            { return employeeId;   }
    public void setEmployeeId(Long id)     { this.employeeId = id; }

    public Long getDepartmentId()          { return departmentId; }
    public void setDepartmentId(Long id)   { this.departmentId = id; }

    public String getDepartmentName()      { return departmentName; }
    public void setDepartmentName(String n){ this.departmentName = n; }

    public String getFullName()            { return fullName; }
    public void setFullName(String n)      { this.fullName = n; }

    public String getWorkEmail()           { return workEmail; }
    public void setWorkEmail(String e)     { this.workEmail = e; }

    public String getGradeLevel()          { return gradeLevel; }
    public void setGradeLevel(String g)    { this.gradeLevel = g; }

    public String getEventType()           { return eventType; }
    public void setEventType(String t)     { this.eventType = t; }
}
*/

