package com.hrms.employee.controller;

import com.hrms.employee.dto.EmployeeDTO;
import com.hrms.employee.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // ─── SINGLE CREATE ───────────────────────────────────────
    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO created = employeeService.createEmployee(employeeDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // ─── BULK CREATE ─────────────────────────────────────────
    @PostMapping("/bulk")
    public ResponseEntity<List<EmployeeDTO>> createEmployees(@RequestBody List<EmployeeDTO> employeeDTOs) {
        List<EmployeeDTO> created = employeeService.createEmployees(employeeDTOs);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // ─── UPDATE ──────────────────────────────────────────────
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(
            @PathVariable Long id,
            @RequestBody EmployeeDTO employeeDTO
    ) {
        EmployeeDTO updated = employeeService.updateEmployee(id, employeeDTO);
        return ResponseEntity.ok(updated);
    }

    // ─── GET ONE ─────────────────────────────────────────────
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        EmployeeDTO dto = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(dto);
    }

    // ─── GET ALL ─────────────────────────────────────────────
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        List<EmployeeDTO> all = employeeService.getAllEmployees();
        return ResponseEntity.ok(all);
    }

    // ─── DELETE ──────────────────────────────────────────────
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Employee with ID " + id + " deleted successfully.");
    }
}
