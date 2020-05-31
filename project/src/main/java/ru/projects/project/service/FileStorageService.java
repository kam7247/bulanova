package ru.egprojects.sw1_springboot.service;

import org.springframework.web.multipart.MultipartFile;
import ru.egprojects.sw1_springboot.model.FileInfo;

import javax.servlet.http.HttpServletResponse;

public interface FileStorageService {
    FileInfo saveFile(MultipartFile file);

    void writeFileToResponse(String fileName, HttpServletResponse response);
}
