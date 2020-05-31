package ru.egprojects.sw1_springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.egprojects.sw1_springboot.model.FileInfo;
import ru.egprojects.sw1_springboot.service.FileStorageService;

import javax.servlet.http.HttpServletResponse;

@Controller
public class FileController {

    @Autowired
    private FileStorageService service;


    @PostMapping("/files")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        FileInfo fileInfo = service.saveFile(file);

        return ResponseEntity
                .ok()
                .body("/files/" + fileInfo.getStorageFileName());
    }

    @GetMapping("/files/{file-name:.+}")
    public void getFile(@PathVariable("file-name") String fileName,
                        HttpServletResponse response) {
        service.writeFileToResponse(fileName, response);
    }

}
