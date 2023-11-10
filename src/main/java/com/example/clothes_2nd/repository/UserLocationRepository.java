package com.example.clothes_2nd.repository;

import com.example.clothes_2nd.model.LocationRegion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLocationRepository extends JpaRepository<LocationRegion, Long> {
}
