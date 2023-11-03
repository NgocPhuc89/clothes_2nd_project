package com.example.clothes_2nd.repository;

import com.example.clothes_2nd.model.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}
