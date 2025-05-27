package com.hrms.employee.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;

@Service
public class FileStorageService {
    private final Path storageDir;

    public FileStorageService(@Value("${employee.photos.dir}") String dir) {
        this.storageDir = Paths.get(dir).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.storageDir);
        } catch (IOException ex) {
            throw new RuntimeException("Could not create photo storage directory.", ex);
        }
    }

    /**
     * Saves the uploaded file under name "emp_{id}.{ext}"
     * and returns that filename (not full path).
     */
    public String storeFile(Long employeeId, MultipartFile file) {
        String original = StringUtils.cleanPath(file.getOriginalFilename());
        String ext = "";
        int dot = original.lastIndexOf('.');
        if (dot > 0) ext = original.substring(dot);
        String filename = "emp_" + employeeId + ext;

        try {
            Path target = storageDir.resolve(filename);
            Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
            return filename;
        } catch (IOException ex) {
            throw new RuntimeException("Failed to store file " + filename, ex);
        }
    }
}
