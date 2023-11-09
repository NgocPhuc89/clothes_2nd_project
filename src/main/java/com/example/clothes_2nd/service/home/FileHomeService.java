package com.example.clothes_2nd.service.home;

import com.example.clothes_2nd.repository.FileRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FileHomeService {
    private FileRepository fileRepository;

}
