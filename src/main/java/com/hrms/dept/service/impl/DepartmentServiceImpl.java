package com.hrms.dept.service.impl;

import com.hrms.dept.dto.DepartmentDTO;
import com.hrms.dept.entity.Department;
import com.hrms.dept.repository.DepartmentRepository;
import com.hrms.dept.service.DepartmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) {
        Department department = convertToEntity(departmentDTO);
        // set creation metadata
        department.setCreatedDate(LocalDateTime.now());
        // For now, we can hardcode createdBy. Later, fetch from logged in user
        department.setCreatedBy("SYSTEM");
        Department savedDept = departmentRepository.save(department);
        return convertToDTO(savedDept);
    }

    @Override
    public DepartmentDTO updateDepartment(Long departmentId, DepartmentDTO departmentDTO) {
        Department existingDept = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + departmentId));

        // Update fields
        existingDept.setDepartmentName(departmentDTO.getDepartmentName());
        existingDept.setDepartmentCode(departmentDTO.getDepartmentCode());
        existingDept.setParentDepartmentId(departmentDTO.getParentDepartmentId());
        existingDept.setDepartmentHeadId(departmentDTO.getDepartmentHeadId());
        existingDept.setDescription(departmentDTO.getDescription());
        existingDept.setLocation(departmentDTO.getLocation());
        existingDept.setBudgetAllocation(departmentDTO.getBudgetAllocation());
        existingDept.setDepartmentEmail(departmentDTO.getDepartmentEmail());
        existingDept.setDepartmentPhoneNumber(departmentDTO.getDepartmentPhoneNumber());
        existingDept.setLastUpdatedBy("SYSTEM"); // or from logged in user
        existingDept.setLastUpdatedDate(LocalDateTime.now());

        Department updatedDept = departmentRepository.save(existingDept);
        return convertToDTO(updatedDept);
    }

    @Override
    public DepartmentDTO getDepartmentById(Long departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + departmentId));
        return convertToDTO(department);
    }

    @Override
    public List<DepartmentDTO> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        List<DepartmentDTO> result = new ArrayList<>();
        for (Department dept : departments) {
            result.add(convertToDTO(dept));
        }
        return result;
    }

    @Override
    public void deleteDepartment(Long departmentId) {
        if(!departmentRepository.existsById(departmentId)) {
            throw new RuntimeException("Department not found with id: " + departmentId);
        }
        departmentRepository.deleteById(departmentId);
    }

    // =========================
    // Private Conversion Methods
    // =========================
    private Department convertToEntity(DepartmentDTO dto) {
        Department entity = new Department();
        entity.setDepartmentName(dto.getDepartmentName());
        entity.setDepartmentCode(dto.getDepartmentCode());
        entity.setParentDepartmentId(dto.getParentDepartmentId());
        entity.setDepartmentHeadId(dto.getDepartmentHeadId());
        entity.setDescription(dto.getDescription());
        entity.setLocation(dto.getLocation());
        entity.setBudgetAllocation(dto.getBudgetAllocation());
        entity.setDepartmentEmail(dto.getDepartmentEmail());
        entity.setDepartmentPhoneNumber(dto.getDepartmentPhoneNumber());
        // createdBy, createdDate, etc. can be set in the create methods
        return entity;
    }

    private DepartmentDTO convertToDTO(Department entity) {
        DepartmentDTO dto = new DepartmentDTO();
        dto.setDepartmentId(entity.getDepartmentId());
        dto.setDepartmentName(entity.getDepartmentName());
        dto.setDepartmentCode(entity.getDepartmentCode());
        dto.setParentDepartmentId(entity.getParentDepartmentId());
        dto.setDepartmentHeadId(entity.getDepartmentHeadId());
        dto.setDescription(entity.getDescription());
        dto.setLocation(entity.getLocation());
        dto.setBudgetAllocation(entity.getBudgetAllocation());
        dto.setDepartmentEmail(entity.getDepartmentEmail());
        dto.setDepartmentPhoneNumber(entity.getDepartmentPhoneNumber());
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setLastUpdatedBy(entity.getLastUpdatedBy());
        dto.setLastUpdatedDate(entity.getLastUpdatedDate());
        return dto;
    }
}
