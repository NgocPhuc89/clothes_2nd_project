package com.example.clothes_2nd.controller.rest.admin.product;

import com.example.clothes_2nd.model.File;
import com.example.clothes_2nd.service.admin.file.UploadFileService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/files")
@AllArgsConstructor
@CrossOrigin
public class FileRestController {
    private final UploadFileService uploadFileService;

    @PostMapping("/images")
    public File uploadImage(@RequestParam("files") MultipartFile image) throws IOException {
        return uploadFileService.saveImage(image);
    }
}
