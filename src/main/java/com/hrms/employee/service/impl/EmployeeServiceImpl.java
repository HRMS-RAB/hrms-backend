package com.hrms.employee.service.impl;

import com.hrms.dept.repository.DepartmentRepository;
import com.hrms.employee.dto.EmployeeDTO;
import com.hrms.employee.entity.Employee;
import com.hrms.employee.event.EmployeeEvent;
import com.hrms.employee.mapper.EmployeeMapper;
import com.hrms.employee.repository.EmployeeRepository;
import com.hrms.employee.service.EmployeeService;
import com.hrms.employee.service.FileStorageService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository   employeeRepo;
    private final EmployeeMapper       mapper;
    private final DepartmentRepository deptRepo;
    private final FileStorageService   fileStorageService;
    private final RabbitTemplate       rabbitTemplate;

    @Value("${hrms.rabbitmq.employee.exchange}")
    private String exchange;

    @Value("${hrms.rabbitmq.employee.routing-key}")
    private String routingKey;

    /*───────────────────────────── CRUD ─────────────────────────────*/

    @Override
    public EmployeeDTO createEmployee(@Valid EmployeeDTO dto) {
        if (employeeRepo.existsByWorkEmail(dto.getWorkEmail())) {
            throw new IllegalArgumentException(
                    "Employee with email " + dto.getWorkEmail() + " already exists");
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
                   .map(this::createEmployee)     // validation + event per record
                   .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeDTO getEmployeeById(Long id) {
        return mapper.toDTO(employeeRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found: " + id)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepo.findAll().stream()
                           .map(mapper::toDTO)
                           .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO dto) {
        Employee existing = employeeRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found: " + id));

        // copy only non-null fields from DTO onto the entity
        mapper.partialUpdate(existing, dto);

        existing.setLastUpdatedBy("SYSTEM");
        existing.setLastUpdatedDate(LocalDateTime.now());

        Employee saved = employeeRepo.save(existing);
        publishEvent(saved, "UPDATED");
        return mapper.toDTO(saved);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepo.deleteById(id);
        log.info("Employee {} deleted", id);
    }

    @Override
    public EmployeeDTO uploadPhoto(Long id, MultipartFile file) {
        Employee emp = employeeRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found: " + id));
        String storedName = fileStorageService.storeFile(id, file);
        emp.setPhotoPath(storedName);
        return mapper.toDTO(employeeRepo.save(emp));
    }

    /*───────────────────── private helper ───────────────────────*/

    private void publishEvent(Employee e, String eventType) {
        EmployeeEvent evt = new EmployeeEvent(
                e.getEmployeeId(),
                e.getDepartment() != null ? e.getDepartment().getDepartmentId() : null,
                e.getDepartment() != null ? e.getDepartment().getDepartmentName() : null,
                e.getFullName(),
                e.getWorkEmail(),
                e.getGradeLevel(),
                e.getEmployeeType().name(),
                eventType
        );
        rabbitTemplate.convertAndSend(exchange, routingKey, evt);
    }
}




//--------------------------below one is working  


/*package com.hrms.employee.service.impl;

import com.hrms.dept.repository.DepartmentRepository;
import com.hrms.employee.dto.EmployeeDTO;
import com.hrms.employee.entity.Employee;
import com.hrms.employee.event.EmployeeEvent;
import com.hrms.employee.mapper.EmployeeMapper;
import com.hrms.employee.repository.EmployeeRepository;
import com.hrms.employee.service.EmployeeService;
import com.hrms.employee.service.FileStorageService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
    private final FileStorageService      fileStorageService;

    @Value("${hrms.rabbitmq.employee.exchange}")
    private String exchange;

    @Value("${hrms.rabbitmq.employee.routing-key}")
    private String routingKey;

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

    @Override
    public EmployeeDTO uploadPhoto(Long id, MultipartFile file) {
        Employee emp = employeeRepo.findById(id)
           .orElseThrow(() -> new EntityNotFoundException("Employee not found: " + id));
        // 1) store on disk
        String storedName = fileStorageService.storeFile(id, file);
        // 2) save path in DB
        emp.setPhotoPath(storedName);
        Employee updated = employeeRepo.save(emp);
        // 3) map to DTO and return
        return mapper.toDTO(updated);
    }

    private void publishEvent(Employee e, String eventType) {
        EmployeeEvent evt = new EmployeeEvent(
            e.getEmployeeId(),
            e.getDepartment() != null ? e.getDepartment().getDepartmentId() : null,
            e.getDepartment() != null ? e.getDepartment().getDepartmentName() : null,
            e.getFullName(),
            e.getWorkEmail(),
            e.getGradeLevel(),
            e.getEmployeeType().name(),
            eventType
        );
        rabbitTemplate.convertAndSend(exchange, routingKey, evt);
    }
}

*/

 //--------------------------above one is working  

