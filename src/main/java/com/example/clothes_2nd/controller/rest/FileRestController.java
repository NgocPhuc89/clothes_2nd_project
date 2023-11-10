package com.example.clothes_2nd.controller.rest;

import com.example.clothes_2nd.model.File;
import com.example.clothes_2nd.service.file.UploadFileService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/files")
@AllArgsConstructor
public class FileRestController {
    private final UploadFileService uploadFileService;

    @PostMapping
    public File upload(@RequestParam("images") MultipartFile image) throws IOException {
        return uploadFileService.saveImage(image);
    }

    @PostMapping("/images")
    public File uploadImage(@RequestParam("files") MultipartFile image) throws IOException {
        return uploadFileService.saveImage(image);
    }
}
