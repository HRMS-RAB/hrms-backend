package com.hrms.employee.service.impl;

import com.hrms.dept.repository.DepartmentRepository;
import com.hrms.employee.dto.EmployeeDTO;
import com.hrms.employee.entity.Employee;
import com.hrms.employee.event.EmployeeEvent;
import com.hrms.employee.mapper.EmployeeMapper;
import com.hrms.employee.repository.EmployeeRepository;
import com.hrms.employee.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository      employeeRepo;
    private final EmployeeMapper          mapper;
    private final RabbitTemplate          rabbitTemplate;
    private final DepartmentRepository    deptRepo;

    private final String exchange   = "hrms.exchange";
    private final String routingKey = "hrms.employee";

    @Override
    public EmployeeDTO createEmployee(@Valid EmployeeDTO dto) {
    	log.info("createEmployee() called with employeeType={} employmentStatus={}", 
                dto.getEmployeeType(), dto.getEmploymentStatus());
        if (employeeRepo.existsByWorkEmail(dto.getWorkEmail())) {
            throw new IllegalArgumentException(
                "Employee with email " + dto.getWorkEmail() + " already exists."
            );
        }
        Employee entity = mapper.toEntity(dto);
        entity.setCreatedBy("SYSTEM");
        entity.setCreatedDate(LocalDateTime.now());
        Employee saved = employeeRepo.save(entity);
        publishEvent(saved, "CREATED");
        return mapper.toDTO(saved);
    }

    @Override
    public List<EmployeeDTO> createEmployees(List<@Valid EmployeeDTO> dtos) {
        return dtos.stream()
                   .map(this::createEmployee)
                   .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeDTO getEmployeeById(Long id) {
        Employee emp = employeeRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Employee not found: " + id));
        return mapper.toDTO(emp);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepo.findAll()
                           .stream()
                           .map(mapper::toDTO)
                           .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO dto) {
        Employee existing = employeeRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Employee not found: " + id));

        if (dto.getFirstName()        != null) existing.setFirstName(dto.getFirstName());
        if (dto.getMiddleName()       != null) existing.setMiddleName(dto.getMiddleName());
        if (dto.getLastName()         != null) existing.setLastName(dto.getLastName());
        if (dto.getEmployeeType()     != null) existing.setEmployeeType(dto.getEmployeeType());
        if (dto.getEmploymentStatus() != null) existing.setEmploymentStatus(dto.getEmploymentStatus());
        if (dto.getContractType()     != null) existing.setContractType(dto.getContractType());
        if (dto.getDepartmentId()     != null) {
            existing.setDepartment(deptRepo.getReferenceById(dto.getDepartmentId()));
        }
        if (dto.getWorkEmail()        != null) existing.setWorkEmail(dto.getWorkEmail());
        if (dto.getGradeLevel()       != null) existing.setGradeLevel(dto.getGradeLevel());
        // … add more fields here as needed …

        existing.setLastUpdatedBy("SYSTEM");
        existing.setLastUpdatedDate(LocalDateTime.now());

        Employee saved = employeeRepo.save(existing);
        publishEvent(saved, "UPDATED");
        return mapper.toDTO(saved);
    }

    @Override
    public void deleteEmployee(Long id) {
        if (!employeeRepo.existsById(id)) {
            throw new IllegalArgumentException("Employee not found: " + id);
        }
        employeeRepo.deleteById(id);
        log.info("Employee {} deleted", id);
    }

    private void publishEvent(Employee e, String type) {
        EmployeeEvent evt = new EmployeeEvent(
            e.getEmployeeId(),
            e.getDepartment() != null ? e.getDepartment().getDepartmentId() : null,
            e.getDepartment() != null ? e.getDepartment().getDepartmentName() : null,
            e.getFullName(),
            e.getWorkEmail(),
            e.getGradeLevel(),
            e.getEmployeeType().name(),
            type
        );
        rabbitTemplate.convertAndSend(exchange, routingKey, evt);
    }
}



/*package com.hrms.employee.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrms.employee.dto.EmployeeDTO;
import com.hrms.employee.entity.Employee;
import com.hrms.employee.event.EmployeeEvent;
import com.hrms.employee.mapper.EmployeeMapper;
import com.hrms.employee.repository.EmployeeRepository;
import com.hrms.employee.service.EmployeeService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepo;
    private final EmployeeMapper     mapper;
    private final RabbitTemplate     rabbitTemplate;
    private final ObjectMapper       json;

    @Value("${hrms.rabbitmq.employee.exchange}")
    private String exchange;

    @Value("${hrms.rabbitmq.employee.routing-key}")
    private String routingKey;

    public EmployeeServiceImpl(EmployeeRepository employeeRepo,
                               EmployeeMapper mapper,
                               RabbitTemplate rabbitTemplate,
                               ObjectMapper json) {
        this.employeeRepo   = employeeRepo;
        this.mapper         = mapper;
        this.rabbitTemplate = rabbitTemplate;
        this.json           = json;
    }

    // ─── SINGLE CREATE ───────────────────────────────────────
    @Override
    public EmployeeDTO createEmployee(EmployeeDTO dto) {
        // Optional: enforce unique work‐email
        if (employeeRepo.existsByWorkEmail(dto.getWorkEmail())) {
            throw new IllegalArgumentException("Employee with email " + dto.getWorkEmail() + " already exists.");
        }

        Employee entity = mapper.toEntity(dto);
        entity.setCreatedBy("SYSTEM");
        entity.setCreatedDate(LocalDateTime.now());

        Employee saved = employeeRepo.save(entity);
        publishEvent(saved, "CREATED");
        return mapper.toDTO(saved);
    }

    // ─── BULK CREATE ─────────────────────────────────────────
    @Override
    public List<EmployeeDTO> createEmployees(List<EmployeeDTO> dtos) {
        return dtos.stream()
                   .map(this::createEmployee)
                   .collect(Collectors.toList());
    }

    // ─── UPDATE ──────────────────────────────────────────────
    @Override
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO dto) {
        Employee existing = employeeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found: " + id));

        // map all updatable fields
        mapper.toEntity(dto);  // assuming your mapper merges into existing
        existing.setLastUpdatedBy("SYSTEM");
        existing.setLastUpdatedDate(LocalDateTime.now());

        Employee saved = employeeRepo.save(existing);
        publishEvent(saved, "UPDATED");
        return mapper.toDTO(saved);
    }

    // ─── READ ────────────────────────────────────────────────
    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        return employeeRepo.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Employee not found: " + id));
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepo.findAll()
                .stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    // ─── DELETE ──────────────────────────────────────────────
    @Override
    public void deleteEmployee(Long id) {
        if (!employeeRepo.existsById(id)) {
            throw new RuntimeException("Employee not found: " + id);
        }
        employeeRepo.deleteById(id);
    }

    // ─── HELPER ──────────────────────────────────────────────
    private void publishEvent(Employee e, String eventType) {
        try {
            EmployeeEvent evt = new EmployeeEvent(
                    e.getEmployeeId(),
                    e.getDepartment() != null ? e.getDepartment().getDepartmentId() : null,
                    e.getDepartment() != null ? e.getDepartment().getDepartmentName() : null,
                    e.getFullName(),
                    e.getWorkEmail(),
                    e.getGradeLevel(),
                    eventType
            );
            rabbitTemplate.convertAndSend(exchange, routingKey, json.writeValueAsString(evt));
        } catch (Exception ex) {
            System.err.println("Failed to publish employee event: " + ex.getMessage());
        }
    }
}*/
