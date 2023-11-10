package com.example.clothes_2nd.service.admin.file;

import com.cloudinary.Cloudinary;
import com.example.clothes_2nd.model.File;
import com.example.clothes_2nd.model.emun.FileType;
import com.example.clothes_2nd.repository.FileRepository;
import com.example.clothes_2nd.util.UploadUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@AllArgsConstructor
public class UploadFileService {
private  final Cloudinary cloudinary;
private final FileRepository fileRepository;
private final UploadUtil uploadUtil;
    public File saveImage(MultipartFile image) throws IOException {
        var file = new File();
        fileRepository.save(file);

        var uploadResult = cloudinary.uploader().upload(image.getBytes(), uploadUtil.buildImageUploadParams(file));

        String fileUrl = (String) uploadResult.get("secure_url");
        String fileFormat = (String) uploadResult.get("format");

        file.setFileName(file.getId() + "." + fileFormat);
        file.setUrl(fileUrl);
        file.setFileType(FileType.valueOf("IMAGE"));
        file.setFileFolder(UploadUtil.IMAGE_UPLOAD_FOLDER);
        file.setCloudId(file.getFileFolder() + "/" + file.getId());
        fileRepository.save(file);
        return file;
    }

}
