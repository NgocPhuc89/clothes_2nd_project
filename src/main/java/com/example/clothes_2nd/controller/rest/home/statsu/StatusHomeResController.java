package com.example.clothes_2nd.controller.rest.home.statsu;

import com.example.clothes_2nd.service.home.statusHome.StatusHomeService;
import com.example.clothes_2nd.service.home.statusHome.response.StatusHomeResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/home/status")
public class StatusHomeResController {
    private final StatusHomeService statusHomeService;

    @GetMapping()
    public List<StatusHomeResponse> findAll() {
        return statusHomeService.findAll();
    }

}
