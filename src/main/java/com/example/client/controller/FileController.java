package com.example.client.controller;

import com.example.client.dto.FileUploadRequest;
import com.example.client.service.RmiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.io.File;

@RestController
public class FileController {

    private final RmiService rmiService;

    @Autowired
    public FileController(RmiService rmiService) {
        this.rmiService = rmiService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestBody FileUploadRequest request) {
        File file = new File(request.getFilePath());
        rmiService.uploadFile(file);
        return ResponseEntity.status(HttpStatus.CREATED).body("File uploaded successfully");
    }

    @PostMapping("/getFile")
    public ResponseEntity<byte[]> getFile(@RequestBody int fileId) {
        byte[] fileData = rmiService.getFile(fileId);
        return ResponseEntity.ok().body(fileData);
    }
}
